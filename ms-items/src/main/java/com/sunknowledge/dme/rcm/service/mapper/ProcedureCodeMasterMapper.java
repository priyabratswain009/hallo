package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster;
import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProcedureCodeMaster} and its DTO {@link ProcedureCodeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProcedureCodeMasterMapper extends EntityMapper<ProcedureCodeMasterDTO, ProcedureCodeMaster> {}
