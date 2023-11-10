package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteTypeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteTypeMaster.class);
        NoteTypeMaster noteTypeMaster1 = new NoteTypeMaster();
        noteTypeMaster1.setNoteTypeId(1L);
        NoteTypeMaster noteTypeMaster2 = new NoteTypeMaster();
        noteTypeMaster2.setNoteTypeId(noteTypeMaster1.getNoteTypeId());
        assertThat(noteTypeMaster1).isEqualTo(noteTypeMaster2);
        noteTypeMaster2.setNoteTypeId(2L);
        assertThat(noteTypeMaster1).isNotEqualTo(noteTypeMaster2);
        noteTypeMaster1.setNoteTypeId(null);
        assertThat(noteTypeMaster1).isNotEqualTo(noteTypeMaster2);
    }
}
