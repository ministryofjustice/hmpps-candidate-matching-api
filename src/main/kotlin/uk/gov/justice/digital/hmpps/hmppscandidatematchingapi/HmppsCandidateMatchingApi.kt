package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication()
@ConfigurationPropertiesScan
class HmppsCandidateMatchingApi

fun main(args: Array<String>) {
  runApplication<HmppsCandidateMatchingApi>(*args)
}
