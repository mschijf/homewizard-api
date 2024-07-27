package ms.powermonitoring.service

import ms.powermonitoring.homewizard.rest.HomeWizard
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
class PowerMonitoringService(
    private val homeWizard: HomeWizard,
    private val repository: Repository,
    private val measurement: MicroMeterMeasurement
) {

    @Scheduled(fixedRate = 10_000)
    fun detailedPowerMeasurement() {
        val homeWizardData = homeWizard.getHomeWizardData()
        repository.appendToDetailedFile(homeWizardData)
        measurement.setMetrics(homeWizardData)
    }

    @Scheduled(cron = "0 0 0 * * *")
    fun hourPowerMeasurement() {
        val homeWizardData = homeWizard.getHomeWizardData()
        repository.appendToHourFile(homeWizardData)
    }

    @Scheduled(cron = "0 0 * * * *")
    fun dayPowerMeasurement() {
        val homeWizardData = homeWizard.getHomeWizardData()
        repository.appendToDayFile(homeWizardData)
    }
}