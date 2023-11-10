package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskCommentsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskCommentsDTO.class);
        TaskCommentsDTO taskCommentsDTO1 = new TaskCommentsDTO();
        taskCommentsDTO1.setTaskCommentId(1L);
        TaskCommentsDTO taskCommentsDTO2 = new TaskCommentsDTO();
        assertThat(taskCommentsDTO1).isNotEqualTo(taskCommentsDTO2);
        taskCommentsDTO2.setTaskCommentId(taskCommentsDTO1.getTaskCommentId());
        assertThat(taskCommentsDTO1).isEqualTo(taskCommentsDTO2);
        taskCommentsDTO2.setTaskCommentId(2L);
        assertThat(taskCommentsDTO1).isNotEqualTo(taskCommentsDTO2);
        taskCommentsDTO1.setTaskCommentId(null);
        assertThat(taskCommentsDTO1).isNotEqualTo(taskCommentsDTO2);
    }
}
