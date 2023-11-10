package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoteTypeMasterAuditLog} and its DTO {@link NoteTypeMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoteTypeMasterAuditLogMapper extends EntityMapper<NoteTypeMasterAuditLogDTO, NoteTypeMasterAuditLog> {}
