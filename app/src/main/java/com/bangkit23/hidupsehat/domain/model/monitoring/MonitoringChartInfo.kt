package com.bangkit23.hidupsehat.domain.model.monitoring

import java.time.LocalDateTime

data class MonitoringChartInfo(
    val date: LocalDateTime,
    val close: Double
)