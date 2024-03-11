package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.telemetry

import com.microsoft.applicationinsights.TelemetryClient
import org.springframework.stereotype.Component
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.entity.CandidatematchingProfile
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.messaging.EventType
import java.time.ZoneOffset

@Component
class TelemetryService(
  private val telemetryClient: TelemetryClient,
) {

  fun createAndPublishTelemetryEventMessage(candidatematchingProfile: CandidatematchingProfile, eventType: EventType) {
    val logMap = createTelemetryEventMap_induction_created_updated(candidatematchingProfile)
  }

  fun createTelemetryEventMap_induction_created_updated(candidatematchingProfile: CandidatematchingProfile): MutableMap<String, String> {
    val logMap: MutableMap<String, String> = HashMap()
    logMap["prisonId"] = candidatematchingProfile.prisonId.toString()
    logMap["userId"] = candidatematchingProfile.modifiedBy.toString()
    logMap["timestamp"] = candidatematchingProfile.modifiedDateTime?.toInstant(
      ZoneOffset.UTC,
    )!!.toString()
    return logMap
  }
}
