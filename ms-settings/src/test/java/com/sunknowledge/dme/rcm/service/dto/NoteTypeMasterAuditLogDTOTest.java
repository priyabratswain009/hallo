package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteTypeMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteTypeMasterAuditLogDTO.class);
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO1 = new NoteTypeMasterAuditLogDTO();
        noteTypeMasterAuditLogDTO1.setId(1L);
        NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO2 = new NoteTypeMasterAuditLogDTO();
        assertThat(noteTypeMasterAuditLogDTO1).isNotEqualTo(noteTypeMasterAuditLogDTO2);
        noteTypeMasterAuditLogDTO2.setId(noteTypeMasterAuditLogDTO1.getId());
        assertThat(noteTypeMasterAuditLogDTO1).isEqualTo(noteTypeMasterAuditLogDTO2);
        noteTypeMasterAuditLogDTO2.setId(2L);
        assertThat(noteTypeMasterAuditLogDTO1).isNotEqualTo(noteTypeMasterAuditLogDTO2);
        noteTypeMasterAuditLogDTO1.setId(null);
        assertThat(noteTypeMasterAuditLogDTO1).isNotEqualTo(noteTypeMasterAuditLogDTO2);
    }
}
