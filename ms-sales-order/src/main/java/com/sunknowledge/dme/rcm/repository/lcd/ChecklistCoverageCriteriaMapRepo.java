package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.dto.lcd.CheckListCoverageCriteriaMapExtendedDTO;
import com.sunknowledge.dme.rcm.repository.ChecklistCoverageCriteriaMapRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ChecklistCoverageCriteriaMapRepo extends ChecklistCoverageCriteriaMapRepository {
    @Query(value = "select * from so.t_checklist_coverage_criteria_map where item_group_id = :itemGroupId and lower(status) = 'active' order by coverage_criteria_id")
    Flux<CheckListCoverageCriteriaMapExtendedDTO> getCheckListCoverageCriteria(@Param("itemGroupId") Long itemGroupId);
}
