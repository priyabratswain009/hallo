package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.NoteReasonMaster;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoteReasonMaster} and its DTO {@link NoteReasonMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoteReasonMasterMapper extends EntityMapper<NoteReasonMasterDTO, NoteReasonMaster> {}
