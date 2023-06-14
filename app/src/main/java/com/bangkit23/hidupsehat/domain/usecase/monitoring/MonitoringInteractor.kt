package com.bangkit23.hidupsehat.domain.usecase.monitoring

import com.bangkit23.hidupsehat.domain.reporitory.MonitoringRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonitoringInteractor @Inject constructor(
    private val monitoringRepository: MonitoringRepository
) : MonitoringUseCase {

    override fun getFoodsHistory(date: String) =
        monitoringRepository.getFoodsHistory(date)
}