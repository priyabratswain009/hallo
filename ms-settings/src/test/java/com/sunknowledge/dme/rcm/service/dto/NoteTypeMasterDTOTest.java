package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteTypeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteTypeMasterDTO.class);
        NoteTypeMasterDTO noteTypeMasterDTO1 = new NoteTypeMasterDTO();
        noteTypeMasterDTO1.setNoteTypeId(1L);
        NoteTypeMasterDTO noteTypeMasterDTO2 = new NoteTypeMasterDTO();
        assertThat(noteTypeMasterDTO1).isNotEqualTo(noteTypeMasterDTO2);
        noteTypeMasterDTO2.setNoteTypeId(noteTypeMasterDTO1.getNoteTypeId());
        assertThat(noteTypeMasterDTO1).isEqualTo(noteTypeMasterDTO2);
        noteTypeMasterDTO2.setNoteTypeId(2L);
        assertThat(noteTypeMasterDTO1).isNotEqualTo(noteTypeMasterDTO2);
        noteTypeMasterDTO1.setNoteTypeId(null);
        assertThat(noteTypeMasterDTO1).isNotEqualTo(noteTypeMasterDTO2);
    }
}
