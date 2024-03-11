package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.unit.service

import com.microsoft.applicationinsights.TelemetryClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.telemetry.TelemetryService

class TelemetryServiceTest {
  private val telemetryClient: TelemetryClient = mock()

  private lateinit var telemetryService: TelemetryService

  @BeforeEach
  fun beforeEach() {
    telemetryService = TelemetryService(
      telemetryClient,
    )
  }

  @Test
  fun `makes a call to the telemetryclient  when a induction is created`() {
  }

  @Test
  fun `makes a call to the telemetryclient  when a induction is updated`() {
  }
}
