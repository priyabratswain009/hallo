package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteReasonMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteReasonMasterAuditLogDTO.class);
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO1 = new NoteReasonMasterAuditLogDTO();
        noteReasonMasterAuditLogDTO1.setId(1L);
        NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO2 = new NoteReasonMasterAuditLogDTO();
        assertThat(noteReasonMasterAuditLogDTO1).isNotEqualTo(noteReasonMasterAuditLogDTO2);
        noteReasonMasterAuditLogDTO2.setId(noteReasonMasterAuditLogDTO1.getId());
        assertThat(noteReasonMasterAuditLogDTO1).isEqualTo(noteReasonMasterAuditLogDTO2);
        noteReasonMasterAuditLogDTO2.setId(2L);
        assertThat(noteReasonMasterAuditLogDTO1).isNotEqualTo(noteReasonMasterAuditLogDTO2);
        noteReasonMasterAuditLogDTO1.setId(null);
        assertThat(noteReasonMasterAuditLogDTO1).isNotEqualTo(noteReasonMasterAuditLogDTO2);
    }
}
