package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClinicalDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClinicalDetailsAuditLogDTO.class);
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO1 = new SalesOrderClinicalDetailsAuditLogDTO();
        salesOrderClinicalDetailsAuditLogDTO1.setId(1L);
        SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO2 = new SalesOrderClinicalDetailsAuditLogDTO();
        assertThat(salesOrderClinicalDetailsAuditLogDTO1).isNotEqualTo(salesOrderClinicalDetailsAuditLogDTO2);
        salesOrderClinicalDetailsAuditLogDTO2.setId(salesOrderClinicalDetailsAuditLogDTO1.getId());
        assertThat(salesOrderClinicalDetailsAuditLogDTO1).isEqualTo(salesOrderClinicalDetailsAuditLogDTO2);
        salesOrderClinicalDetailsAuditLogDTO2.setId(2L);
        assertThat(salesOrderClinicalDetailsAuditLogDTO1).isNotEqualTo(salesOrderClinicalDetailsAuditLogDTO2);
        salesOrderClinicalDetailsAuditLogDTO1.setId(null);
        assertThat(salesOrderClinicalDetailsAuditLogDTO1).isNotEqualTo(salesOrderClinicalDetailsAuditLogDTO2);
    }
}
