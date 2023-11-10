package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskReasonMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskReasonMaster.class);
        TaskReasonMaster taskReasonMaster1 = new TaskReasonMaster();
        taskReasonMaster1.setTaskReasonId(1L);
        TaskReasonMaster taskReasonMaster2 = new TaskReasonMaster();
        taskReasonMaster2.setTaskReasonId(taskReasonMaster1.getTaskReasonId());
        assertThat(taskReasonMaster1).isEqualTo(taskReasonMaster2);
        taskReasonMaster2.setTaskReasonId(2L);
        assertThat(taskReasonMaster1).isNotEqualTo(taskReasonMaster2);
        taskReasonMaster1.setTaskReasonId(null);
        assertThat(taskReasonMaster1).isNotEqualTo(taskReasonMaster2);
    }
}
