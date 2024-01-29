package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.dto.lcd.CoverageCriteriaFileMapExtendedDTO;
import com.sunknowledge.dme.rcm.repository.CoverageCriteriaFileMapRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface CoverageCriteriaFileMapRepo extends CoverageCriteriaFileMapRepository {
    @Query(value = "select * from so.t_coverage_criteria_file_map where so_id = :soId and item_group_id = :itemGroupId and coverage_criteria_id = :coverageCriteriaId and lower(status) = 'active' order by coverage_criteria_id")
    Flux<CoverageCriteriaFileMapExtendedDTO> getCoverageCriteriaFileMap(@Param("soId") Long soId, @Param("itemGroupId") Long itemGroupId, @Param("coverageCriteriaId") Long coverageCriteriaId);

    @Query(value = "select * from so.t_coverage_criteria_file_map where so_id = :soId and item_group_id = :itemGroupId and lower(status) = 'active' order by coverage_criteria_id")
    Flux<CoverageCriteriaFileMapExtendedDTO> getAllCoverageCriteriaFileMap(@Param("soId") Long soId, @Param("itemGroupId") Long itemGroupId);
}
