package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus;
import com.sunknowledge.dme.rcm.repository.ClaimsReportFileProcessStatusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the ClaimsReportFileProcessStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsReportFileProcessStatusRepo extends ClaimsReportFileProcessStatusRepository {
    @Query("FROM ClaimsReportFileProcessStatus WHERE fileName = :fileName")
    List<ClaimsReportFileProcessStatus> getClaimFileProcessStatusOnFileName(@Param("fileName") String fileName);
}
