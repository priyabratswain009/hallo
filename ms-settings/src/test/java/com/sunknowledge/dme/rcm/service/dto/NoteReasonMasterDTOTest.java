package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NoteReasonMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoteReasonMasterDTO.class);
        NoteReasonMasterDTO noteReasonMasterDTO1 = new NoteReasonMasterDTO();
        noteReasonMasterDTO1.setNoteReasonId(1L);
        NoteReasonMasterDTO noteReasonMasterDTO2 = new NoteReasonMasterDTO();
        assertThat(noteReasonMasterDTO1).isNotEqualTo(noteReasonMasterDTO2);
        noteReasonMasterDTO2.setNoteReasonId(noteReasonMasterDTO1.getNoteReasonId());
        assertThat(noteReasonMasterDTO1).isEqualTo(noteReasonMasterDTO2);
        noteReasonMasterDTO2.setNoteReasonId(2L);
        assertThat(noteReasonMasterDTO1).isNotEqualTo(noteReasonMasterDTO2);
        noteReasonMasterDTO1.setNoteReasonId(null);
        assertThat(noteReasonMasterDTO1).isNotEqualTo(noteReasonMasterDTO2);
    }
}
