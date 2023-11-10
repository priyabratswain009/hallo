package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskDetails.class);
        TaskDetails taskDetails1 = new TaskDetails();
        taskDetails1.setTaskDetailsId(1L);
        TaskDetails taskDetails2 = new TaskDetails();
        taskDetails2.setTaskDetailsId(taskDetails1.getTaskDetailsId());
        assertThat(taskDetails1).isEqualTo(taskDetails2);
        taskDetails2.setTaskDetailsId(2L);
        assertThat(taskDetails1).isNotEqualTo(taskDetails2);
        taskDetails1.setTaskDetailsId(null);
        assertThat(taskDetails1).isNotEqualTo(taskDetails2);
    }
}
