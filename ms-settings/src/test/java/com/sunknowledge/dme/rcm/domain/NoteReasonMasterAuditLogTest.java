package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteReasonMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteReasonMasterAuditLog.class);
        NoteReasonMasterAuditLog noteReasonMasterAuditLog1 = new NoteReasonMasterAuditLog();
        noteReasonMasterAuditLog1.setId(1L);
        NoteReasonMasterAuditLog noteReasonMasterAuditLog2 = new NoteReasonMasterAuditLog();
        noteReasonMasterAuditLog2.setId(noteReasonMasterAuditLog1.getId());
        assertThat(noteReasonMasterAuditLog1).isEqualTo(noteReasonMasterAuditLog2);
        noteReasonMasterAuditLog2.setId(2L);
        assertThat(noteReasonMasterAuditLog1).isNotEqualTo(noteReasonMasterAuditLog2);
        noteReasonMasterAuditLog1.setId(null);
        assertThat(noteReasonMasterAuditLog1).isNotEqualTo(noteReasonMasterAuditLog2);
    }
}
