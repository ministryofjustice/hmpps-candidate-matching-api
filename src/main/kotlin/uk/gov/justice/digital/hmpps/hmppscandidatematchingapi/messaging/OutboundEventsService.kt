package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.messaging

import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.config.CapturedSpringConfigValues
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.entity.CandidatematchingProfile
import java.time.Instant
import java.time.ZoneOffset

@Service
class OutboundEventsService(
  var outboundEventsPublisher: OutboundEventsPublisher?,
) {

  fun createAndPublishEventMessage(candidatematchingProfile: CandidatematchingProfile, eventType: EventType) {
    val outboundEvent = createValidCandidateMatchingEvent(
      candidatematchingProfile.offenderId,
      candidatematchingProfile.prisonId,
      eventType,
      candidatematchingProfile.modifiedDateTime?.toInstant(
        ZoneOffset.UTC,
      )!!,
    )
    outboundEventsPublisher?.publishToTopic(outboundEvent)
  }
  fun createValidCandidateMatchingEvent(
    reference: String,
    prisonName: String?,
    eventType: EventType,
    instant: Instant,
  ): OutboundEvent =
    OutboundEvent(
      eventType = eventType,
      personReference = PersonReference(listOf(Identifier("NOMS", reference))),
      additionalInformation = AdditionalInformation(
        reference = reference,
        prisonId = prisonName,
        userId = CapturedSpringConfigValues.getDPSPrincipal().name,
        userDisplayName = CapturedSpringConfigValues.getDPSPrincipal().displayName,
      ),
      occurredAt = instant,
      version = 1,
    )
}
