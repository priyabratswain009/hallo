package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskTypeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskTypeMaster.class);
        TaskTypeMaster taskTypeMaster1 = new TaskTypeMaster();
        taskTypeMaster1.setTaskId(1L);
        TaskTypeMaster taskTypeMaster2 = new TaskTypeMaster();
        taskTypeMaster2.setTaskId(taskTypeMaster1.getTaskId());
        assertThat(taskTypeMaster1).isEqualTo(taskTypeMaster2);
        taskTypeMaster2.setTaskId(2L);
        assertThat(taskTypeMaster1).isNotEqualTo(taskTypeMaster2);
        taskTypeMaster1.setTaskId(null);
        assertThat(taskTypeMaster1).isNotEqualTo(taskTypeMaster2);
    }
}
