package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.NoteTypeMaster;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoteTypeMaster} and its DTO {@link NoteTypeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoteTypeMasterMapper extends EntityMapper<NoteTypeMasterDTO, NoteTypeMaster> {}
