package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import com.sunknowledge.dme.rcm.repository.ItemGroupRepository;
import com.sunknowledge.dme.rcm.repository.ProcedureCodeMasterRepository;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProcedureCodeRepositoryExtended extends ProcedureCodeMasterRepository {
    @Query(value = "SELECT coalesce(max(procedure_code_id), 0)+1 FROM item.t_procedure_code_master", nativeQuery = true)
    Long findNextId();

    ProcedureCodeMaster findByProcedureCode(String procedureCode);

    List<ProcedureCodeMaster> findByItemProcedureCodeDescLike(String procedureName);

    List<ProcedureCodeMaster> findByItemProcedureCodeDescLikeIgnoreCase(String s);

//    Optional<List<ItemGroup>> findByItemGroupNameLikeAndStatusIgnoreCase(String itemGroupName, String active);
}
