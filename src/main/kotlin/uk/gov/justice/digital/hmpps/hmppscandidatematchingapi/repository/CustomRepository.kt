package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository

import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.entity.CandidatematchingProfile

interface CustomRepository {
  fun findCandidatematchingProfileByEntityGraph(offenderId: String): CandidatematchingProfile?
}
