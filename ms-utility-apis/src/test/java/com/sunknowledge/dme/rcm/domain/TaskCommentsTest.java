package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskCommentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskComments.class);
        TaskComments taskComments1 = new TaskComments();
        taskComments1.setTaskCommentId(1L);
        TaskComments taskComments2 = new TaskComments();
        taskComments2.setTaskCommentId(taskComments1.getTaskCommentId());
        assertThat(taskComments1).isEqualTo(taskComments2);
        taskComments2.setTaskCommentId(2L);
        assertThat(taskComments1).isNotEqualTo(taskComments2);
        taskComments1.setTaskCommentId(null);
        assertThat(taskComments1).isNotEqualTo(taskComments2);
    }
}
