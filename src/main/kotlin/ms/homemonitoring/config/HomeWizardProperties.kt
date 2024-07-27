package ms.homemonitoring.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "homewizard")
data class HomeWizardProperties(
    val url: String,
)