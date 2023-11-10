package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteReasonMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteReasonMaster.class);
        NoteReasonMaster noteReasonMaster1 = new NoteReasonMaster();
        noteReasonMaster1.setNoteReasonId(1L);
        NoteReasonMaster noteReasonMaster2 = new NoteReasonMaster();
        noteReasonMaster2.setNoteReasonId(noteReasonMaster1.getNoteReasonId());
        assertThat(noteReasonMaster1).isEqualTo(noteReasonMaster2);
        noteReasonMaster2.setNoteReasonId(2L);
        assertThat(noteReasonMaster1).isNotEqualTo(noteReasonMaster2);
        noteReasonMaster1.setNoteReasonId(null);
        assertThat(noteReasonMaster1).isNotEqualTo(noteReasonMaster2);
    }
}
