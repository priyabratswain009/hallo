package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoteReasonMasterAuditLog} and its DTO {@link NoteReasonMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoteReasonMasterAuditLogMapper extends EntityMapper<NoteReasonMasterAuditLogDTO, NoteReasonMasterAuditLog> {}
