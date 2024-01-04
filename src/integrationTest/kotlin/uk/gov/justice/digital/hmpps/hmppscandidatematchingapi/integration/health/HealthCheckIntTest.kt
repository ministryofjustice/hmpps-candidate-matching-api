package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.integration.health

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.config.CapturedSpringConfigValues
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.integration.IntegrationTestBase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HealthCheckIntTest : IntegrationTestBase() {

  @Test
  fun `Health page reports ok`() {
    val result = restTemplate.exchange("/health", HttpMethod.GET, HttpEntity<HttpHeaders>(null, null), String::class.java)
    assert(result != null)
    assert(result.hasBody())
    assert(result.statusCode.is2xxSuccessful)
  }

  @Test
  fun `Health info reports version`() {
    val result = restTemplate.exchange("/health", HttpMethod.GET, HttpEntity<HttpHeaders>(null, null), String::class.java)
    assert(result != null)
    assert(result.hasBody())
    assert(result.statusCode.is2xxSuccessful)
    var stringcompanion = CapturedSpringConfigValues.OBJECT_MAPPER.readTree(result.body.toString())
    var version = stringcompanion.get("components").get("healthInfo").get("details").get("version")
    assertThat(version.asText().toString()).startsWith(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE))
  }

  @Test
  fun `Health ping page is accessible`() {
    val result = restTemplate.exchange("/health/ping", HttpMethod.GET, HttpEntity<HttpHeaders>(setAuthorisation(roles = listOf("ROLE_EDUCATION_WORK_PLAN_EDITOR", "ROLE_EDUCATION_WORK_PLAN_VIEWER"))), String::class.java)
    assert(result != null)
    assert(result.hasBody())
    assert(result.statusCode.is2xxSuccessful)
    var stringcompanion = CapturedSpringConfigValues.OBJECT_MAPPER.readTree(result.body.toString())
    var status = stringcompanion.get("status")
    assertThat(status.asText().toString()).isEqualTo("UP")
  }

  @Test
  fun `readiness reports ok`() {
    val result = restTemplate.exchange("/health/readiness", HttpMethod.GET, HttpEntity<HttpHeaders>(setAuthorisation(roles = listOf("ROLE_EDUCATION_WORK_PLAN_EDITOR", "ROLE_EDUCATION_WORK_PLAN_VIEWER"))), String::class.java)
    assert(result != null)
    assert(result.hasBody())
    assert(result.statusCode.is2xxSuccessful)
    var stringcompanion = CapturedSpringConfigValues.OBJECT_MAPPER.readTree(result.body.toString())
    var status = stringcompanion.get("status")
    assertThat(status.asText().toString()).isEqualTo("UP")
  }

  @Test
  fun `liveness reports ok`() {
    val result = restTemplate.exchange("/health/liveness", HttpMethod.GET, HttpEntity<HttpHeaders>(setAuthorisation(roles = listOf("ROLE_EDUCATION_WORK_PLAN_EDITOR", "ROLE_EDUCATION_WORK_PLAN_VIEWER"))), String::class.java)
    assert(result != null)
    assert(result.hasBody())
    assert(result.statusCode.is2xxSuccessful)
    var stringcompanion = CapturedSpringConfigValues.OBJECT_MAPPER.readTree(result.body.toString())
    var status = stringcompanion.get("status")
    assertThat(status.asText().toString()).isEqualTo("UP")
  }
}
