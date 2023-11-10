package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ProcedureCodeParameterDTO;

import java.util.List;

public interface ProcedureCodeMasterServiceExtended {

    ResponseDTO saveProcedureCode(ProcedureCodeParameterDTO procedureCodeParameterDTO);

    ResponseDTO saveProcedureCodeByExternalAPI(String procedureCode);

    ProcedureCodeMasterDTO getProcedureCodeByCode(String procedureCode);

    ProcedureCodeMasterDTO getProcedureCodeById(Long procedureId);

    List<ProcedureCodeMasterDTO> getProcedureCodeByName(String procedureName);

    List<ProcedureCodeMasterDTO> getAllProcedureCode();
}

