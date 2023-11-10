package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteTypeMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteTypeMasterAuditLog.class);
        NoteTypeMasterAuditLog noteTypeMasterAuditLog1 = new NoteTypeMasterAuditLog();
        noteTypeMasterAuditLog1.setId(1L);
        NoteTypeMasterAuditLog noteTypeMasterAuditLog2 = new NoteTypeMasterAuditLog();
        noteTypeMasterAuditLog2.setId(noteTypeMasterAuditLog1.getId());
        assertThat(noteTypeMasterAuditLog1).isEqualTo(noteTypeMasterAuditLog2);
        noteTypeMasterAuditLog2.setId(2L);
        assertThat(noteTypeMasterAuditLog1).isNotEqualTo(noteTypeMasterAuditLog2);
        noteTypeMasterAuditLog1.setId(null);
        assertThat(noteTypeMasterAuditLog1).isNotEqualTo(noteTypeMasterAuditLog2);
    }
}
