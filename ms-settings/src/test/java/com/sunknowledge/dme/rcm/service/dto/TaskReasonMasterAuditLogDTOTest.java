package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaskReasonMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskReasonMasterAuditLogDTO.class);
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO1 = new TaskReasonMasterAuditLogDTO();
        taskReasonMasterAuditLogDTO1.setId(1L);
        TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO2 = new TaskReasonMasterAuditLogDTO();
        assertThat(taskReasonMasterAuditLogDTO1).isNotEqualTo(taskReasonMasterAuditLogDTO2);
        taskReasonMasterAuditLogDTO2.setId(taskReasonMasterAuditLogDTO1.getId());
        assertThat(taskReasonMasterAuditLogDTO1).isEqualTo(taskReasonMasterAuditLogDTO2);
        taskReasonMasterAuditLogDTO2.setId(2L);
        assertThat(taskReasonMasterAuditLogDTO1).isNotEqualTo(taskReasonMasterAuditLogDTO2);
        taskReasonMasterAuditLogDTO1.setId(null);
        assertThat(taskReasonMasterAuditLogDTO1).isNotEqualTo(taskReasonMasterAuditLogDTO2);
    }
}
