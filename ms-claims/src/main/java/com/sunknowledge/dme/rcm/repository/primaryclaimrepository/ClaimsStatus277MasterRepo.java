package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master;
import com.sunknowledge.dme.rcm.repository.ClaimsStatus277MasterRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Spring Data JPA repository for the ClaimsStatus277Master entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsStatus277MasterRepo extends ClaimsStatus277MasterRepository {
    @Query("FROM ClaimsStatus277Master WHERE tradingPartnerClaimNumber = :tradingPartnerClaimNumber AND statusInformationEffectiveDate = :statusInformationEffectiveDate")
    ClaimsStatus277Master getInformationClaimStatusByEffectiveDateNClaimNumber(@Param("tradingPartnerClaimNumber") String tradingPartnerClaimNumber, @Param("statusInformationEffectiveDate") LocalDate statusInformationEffectiveDate);
}
