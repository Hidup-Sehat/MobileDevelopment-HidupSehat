package com.bangkit23.hidupsehat.presentation.screen.exercise_play.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.view.SurfaceView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.BodyPart
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.KeyPoint
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.Person
import java.io.ByteArrayOutputStream
import kotlin.math.abs
import kotlin.math.atan2

class PoseEstimationAnalyzer(
    private val poseDetector: PoseDetector,
    private val surfaceView: SurfaceView,
    private val onAngleChange: (PersonBodyAngle) -> Unit,
    private val onNoPerson: () -> Unit,
) : ImageAnalysis.Analyzer {

    override fun analyze(image: ImageProxy) {
        val rotatedBitmap = image.toRotatedBitmap()
        processImage(rotatedBitmap, poseDetector, surfaceView)
        image.close()
    }

    private fun processImage(bitmap: Bitmap, detector: PoseDetector, surfaceView: SurfaceView) {

        val person = detector.estimateSinglePose(bitmap)
        visualize(person, bitmap, surfaceView)

        if (person.score > MIN_CONFIDENCE) {
            onAngleChange(
                PersonBodyAngle(
                    rightElbow = Joint.RIGHT_ELBOW.getAngleFromPerson(person),
                    rightShoulder = Joint.RIGHT_SHOULDER.getAngleFromPerson(person),
                    rightHip = Joint.RIGHT_HIP.getAngleFromPerson(person),
                    rightKnee = Joint.RIGHT_KNEE.getAngleFromPerson(person),
                    leftElbow = Joint.LEFT_ELBOW.getAngleFromPerson(person),
                    leftShoulder = Joint.LEFT_SHOULDER.getAngleFromPerson(person),
                    leftHip = Joint.LEFT_HIP.getAngleFromPerson(person),
                    leftKnee = Joint.LEFT_KNEE.getAngleFromPerson(person),
                )
            )
        } else onNoPerson()
    }

    private fun <A: BodyPart, B: BodyPart, C: BodyPart> Triple<A, B, C>.getAngleFromPerson(person: Person): Double {
        return run {
            val firstPoint = person.keyPoints[first.position]
            val midPoint = person.keyPoints[second.position]
            val lastPoint = person.keyPoints[third.position]
            getAngle(firstPoint, midPoint, lastPoint)
        }
    }
}

fun getAngle(firstPoint: KeyPoint, midPoint: KeyPoint, lastPoint: KeyPoint): Double {
    var result = Math.toDegrees(
        atan2(
            lastPoint.coordinate.y - midPoint.coordinate.y,
            lastPoint.coordinate.x - midPoint.coordinate.x
        ) - atan2(
            firstPoint.coordinate.y - midPoint.coordinate.y,
            firstPoint.coordinate.x - midPoint.coordinate.x
        ).toDouble()
    )
    result = abs(result)
    if (result > 180) {
        result = 360.0 - result
    }
    return result
}

private const val MIN_CONFIDENCE = .3f

private fun visualize(person: Person, bitmap: Bitmap, surfaceView: SurfaceView) {
    var outputBitmap = bitmap

    if (person.score > MIN_CONFIDENCE) {
        outputBitmap = VisualizationUtils.drawBodyKeyPoints(bitmap, person)
    }

    val holder = surfaceView.holder
    val surfaceCanvas = holder.lockCanvas()

    surfaceCanvas?.let { canvas ->
        val screenWidth: Int
        val screenHeight: Int
        val left: Int
        val top: Int

        if (canvas.height > canvas.width) {
            val ratio = outputBitmap.height.toFloat() / outputBitmap.width
            screenWidth = canvas.width
            left = 0
            screenHeight = (canvas.width * ratio).toInt()
            top = (canvas.height - screenHeight) / 2
        } else {
            val ratio = outputBitmap.width.toFloat() / outputBitmap.height
            screenHeight = canvas.height
            top = 0
            screenWidth = (canvas.height * ratio).toInt()
            left = (canvas.width - screenWidth) / 2
        }
        val right: Int = left + screenWidth
        val bottom: Int = top + screenHeight

        canvas.drawBitmap(
            outputBitmap, Rect(0, 0, outputBitmap.width, outputBitmap.height),
            Rect(left, top, right, bottom), null
        )
        surfaceView.holder.unlockCanvasAndPost(canvas)
    }
}

fun ImageProxy.toRotatedBitmap(): Bitmap {
    val yBuffer = planes[0].buffer
    val vuBuffer = planes[2].buffer

    val ySize = yBuffer.remaining()
    val vuSize = vuBuffer.remaining()

    val nv21 = ByteArray(ySize + vuSize)

    yBuffer.get(nv21, 0, ySize)
    vuBuffer.get(nv21, ySize, vuSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
    val imageBytes = out.toByteArray()
    val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

    val rotateMatrix = Matrix()
    rotateMatrix.preScale(-1.0f, 1.0f)
    rotateMatrix.postRotate(90.0f)

    return Bitmap.createBitmap(
        imageBitmap, 0, 0, this.width, this.height,
        rotateMatrix, false
    )
}