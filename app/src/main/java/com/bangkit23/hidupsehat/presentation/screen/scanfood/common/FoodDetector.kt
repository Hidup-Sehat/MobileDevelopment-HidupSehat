package com.bangkit23.hidupsehat.presentation.screen.scanfood.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import com.bangkit23.hidupsehat.util.rotateBitmap
import com.bangkit23.hidupsehat.util.toBitmap
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.io.File

fun Context.runObjectDetection(
    imageFile: File?,
    isFromCamera: Boolean,
    onDetectedImage: (Bitmap, List<DetectionResult>) -> Unit,
) {
    imageFile?.run {
        val source = imageFile.toBitmap()
        val rotatedBitmap = source.rotateBitmap()
        val image = TensorImage.fromBitmap(
            if (isFromCamera) rotatedBitmap else source
        )

        val options = ObjectDetector.ObjectDetectorOptions.builder()
            .setMaxResults(5)
            .setScoreThreshold(0.5f)
            .build()
        val detector = ObjectDetector.createFromFileAndOptions(
            this@runObjectDetection,
            "model.tflite",
            options
        )
        val results = detector.detect(image)

        val resultToDisplay = results.map {
            val category = it.categories.first()
            val text = category.label
            DetectionResult(it.boundingBox, text)
        }
        val imageWithResult = drawDetectionResult(
            if (isFromCamera) rotatedBitmap else source,
            resultToDisplay
        )
        onDetectedImage(imageWithResult, resultToDisplay)
    }
}

private fun drawDetectionResult(bitmap: Bitmap, result: List<DetectionResult>): Bitmap {
    val outputBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(outputBitmap)
    val pen = Paint()
    pen.textAlign = Paint.Align.LEFT

    result.forEach {
        pen.color = Color.RED
        pen.strokeWidth = 5F
        pen.style = Paint.Style.STROKE
        val box = it.boundingBox
        canvas.drawRect(box, pen)

        val tagSize = Rect(0, 0, 0, 0)

        pen.style = Paint.Style.FILL_AND_STROKE
        pen.color = Color.YELLOW
        pen.strokeWidth = 2F

        pen.textSize = 64F
        pen.getTextBounds(it.text, 0, it.text.length, tagSize)
        val fontSize: Float = pen.textSize * box.width() / tagSize.width()

        if (fontSize < pen.textSize) pen.textSize = fontSize

        var margin = (box.width() - tagSize.width()) / 2.0F
        if (margin < 0F) margin = 0F
        canvas.drawText(
            it.text, box.left + margin,
            box.top + tagSize.height().times(1F), pen
        )
    }
    return outputBitmap
}