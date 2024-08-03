package ms.homemonitor.infra.homewizard.rest

import ms.homemonitor.config.HomeWizardProperties
import ms.homemonitor.infra.homewizard.model.HomeWizardMeasurementData
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class HomeWizard(
    private val homeWizardProperties: HomeWizardProperties) {

    private val restTemplate = RestTemplate()

    fun getHomeWizardData(): HomeWizardMeasurementData {
        val response = restTemplate
            .getForObject("${homeWizardProperties.baseRestUrl}/data", HomeWizardMeasurementData::class.java)
            ?: throw IllegalStateException("Could not get data from HomeWizard. - response is null")

        return response
    }

}


