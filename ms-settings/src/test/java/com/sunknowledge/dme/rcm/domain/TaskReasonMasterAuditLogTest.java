package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskReasonMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskReasonMasterAuditLog.class);
        TaskReasonMasterAuditLog taskReasonMasterAuditLog1 = new TaskReasonMasterAuditLog();
        taskReasonMasterAuditLog1.setId(1L);
        TaskReasonMasterAuditLog taskReasonMasterAuditLog2 = new TaskReasonMasterAuditLog();
        taskReasonMasterAuditLog2.setId(taskReasonMasterAuditLog1.getId());
        assertThat(taskReasonMasterAuditLog1).isEqualTo(taskReasonMasterAuditLog2);
        taskReasonMasterAuditLog2.setId(2L);
        assertThat(taskReasonMasterAuditLog1).isNotEqualTo(taskReasonMasterAuditLog2);
        taskReasonMasterAuditLog1.setId(null);
        assertThat(taskReasonMasterAuditLog1).isNotEqualTo(taskReasonMasterAuditLog2);
    }
}
