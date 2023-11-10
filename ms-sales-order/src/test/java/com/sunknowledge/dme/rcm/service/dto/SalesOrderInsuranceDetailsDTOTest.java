package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderInsuranceDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderInsuranceDetailsDTO.class);
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO1 = new SalesOrderInsuranceDetailsDTO();
        salesOrderInsuranceDetailsDTO1.setSalesOrderInsuranceDetailsId(1L);
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO2 = new SalesOrderInsuranceDetailsDTO();
        assertThat(salesOrderInsuranceDetailsDTO1).isNotEqualTo(salesOrderInsuranceDetailsDTO2);
        salesOrderInsuranceDetailsDTO2.setSalesOrderInsuranceDetailsId(salesOrderInsuranceDetailsDTO1.getSalesOrderInsuranceDetailsId());
        assertThat(salesOrderInsuranceDetailsDTO1).isEqualTo(salesOrderInsuranceDetailsDTO2);
        salesOrderInsuranceDetailsDTO2.setSalesOrderInsuranceDetailsId(2L);
        assertThat(salesOrderInsuranceDetailsDTO1).isNotEqualTo(salesOrderInsuranceDetailsDTO2);
        salesOrderInsuranceDetailsDTO1.setSalesOrderInsuranceDetailsId(null);
        assertThat(salesOrderInsuranceDetailsDTO1).isNotEqualTo(salesOrderInsuranceDetailsDTO2);
    }
}
