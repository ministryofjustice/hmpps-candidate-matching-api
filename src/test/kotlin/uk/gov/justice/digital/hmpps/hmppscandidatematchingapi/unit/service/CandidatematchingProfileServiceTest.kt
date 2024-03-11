package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.unit.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.messaging.OutboundEventsService
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository.CandidatematchingProfileRepository
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.service.CandidatematchingProfileService
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.telemetry.TelemetryService

class CandidatematchingProfileServiceTest {
  private val candidatematchingProfileRepository: CandidatematchingProfileRepository = mock()
  private val outboundEventsService: OutboundEventsService = mock()
  private val telemetryService: TelemetryService = mock()

  private lateinit var profileService: CandidatematchingProfileService

  @BeforeEach
  fun beforeEach() {
    profileService = CandidatematchingProfileService(
      candidatematchingProfileRepository,
      outboundEventsService,
      telemetryService,
    )
  }

  @Test
  fun `makes a call to the repository to save the Candidate matching profile`() {
  }
}
