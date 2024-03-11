package uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository.impl

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.entity.CandidatematchingProfile
import uk.gov.justice.digital.hmpps.hmppscandidatematchingapi.repository.CustomRepository

class CustomRepositoryImpl : CustomRepository {
  @PersistenceContext
  private val entityManager: EntityManager? = null

  override fun findCandidatematchingProfileByEntityGraph(offenderId: String): CandidatematchingProfile? {
    val entityGraph = entityManager?.createEntityGraph(CandidatematchingProfile::class.java)
    entityGraph?.addAttributeNodes("abilityToWork", "reasonToNotGetWork", "workExperience", "skillsAndInterests", "qualificationsAndTraining", "inPrisonInterests")
    val properties = mutableMapOf<String, Any>()

    properties["jakarta.persistence.fetchgraph"] = entityGraph!!
    return entityManager?.find(CandidatematchingProfile::class.java, offenderId, properties)
  }
}
