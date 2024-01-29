package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.dto.lcd.CheckListDocumentReferenceMapExtendedDTO;
import com.sunknowledge.dme.rcm.repository.ChecklistDocumentReferenceMapRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ChecklistDocumentReferenceMapRepo extends ChecklistDocumentReferenceMapRepository {
    @Query(value = "select * from so.t_checklist_document_reference_map where item_group_id = :itemGroupId and lower(status) = 'active'")
    Flux<CheckListDocumentReferenceMapExtendedDTO> getCheckListDocumentReference(@Param("itemGroupId") Long itemGroupId);
}
