package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.service

import org.springframework.stereotype.Service
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.messaging.OutboundEventsService
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository.CandidatematchingProfileRepository
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.telemetry.TelemetryService

@Service
class CandidatematchingProfileService(
  private val candidatematchingProfileRepository: CandidatematchingProfileRepository,
  private val outboundEventsService: OutboundEventsService,
  private val telemetryService: TelemetryService,
)
