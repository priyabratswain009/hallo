package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderInsuranceDetailsAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderInsuranceDetailsAuditLogDTO.class);
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO1 = new SalesOrderInsuranceDetailsAuditLogDTO();
        salesOrderInsuranceDetailsAuditLogDTO1.setId(1L);
        SalesOrderInsuranceDetailsAuditLogDTO salesOrderInsuranceDetailsAuditLogDTO2 = new SalesOrderInsuranceDetailsAuditLogDTO();
        assertThat(salesOrderInsuranceDetailsAuditLogDTO1).isNotEqualTo(salesOrderInsuranceDetailsAuditLogDTO2);
        salesOrderInsuranceDetailsAuditLogDTO2.setId(salesOrderInsuranceDetailsAuditLogDTO1.getId());
        assertThat(salesOrderInsuranceDetailsAuditLogDTO1).isEqualTo(salesOrderInsuranceDetailsAuditLogDTO2);
        salesOrderInsuranceDetailsAuditLogDTO2.setId(2L);
        assertThat(salesOrderInsuranceDetailsAuditLogDTO1).isNotEqualTo(salesOrderInsuranceDetailsAuditLogDTO2);
        salesOrderInsuranceDetailsAuditLogDTO1.setId(null);
        assertThat(salesOrderInsuranceDetailsAuditLogDTO1).isNotEqualTo(salesOrderInsuranceDetailsAuditLogDTO2);
    }
}
