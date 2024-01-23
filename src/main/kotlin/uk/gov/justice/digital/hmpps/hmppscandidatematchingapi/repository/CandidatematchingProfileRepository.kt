package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.entity.CandidatematchingProfile

@Repository
interface CandidatematchingProfileRepository : JpaRepository<CandidatematchingProfile, String>, CustomRepository {

//  fun findById(offenderId: String): CandidatematchingProfile?
}
