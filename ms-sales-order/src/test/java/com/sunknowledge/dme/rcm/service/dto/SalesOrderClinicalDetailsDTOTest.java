package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClinicalDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClinicalDetailsDTO.class);
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO1 = new SalesOrderClinicalDetailsDTO();
        salesOrderClinicalDetailsDTO1.setSalesOrderClinicalDetailsId(1L);
        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO2 = new SalesOrderClinicalDetailsDTO();
        assertThat(salesOrderClinicalDetailsDTO1).isNotEqualTo(salesOrderClinicalDetailsDTO2);
        salesOrderClinicalDetailsDTO2.setSalesOrderClinicalDetailsId(salesOrderClinicalDetailsDTO1.getSalesOrderClinicalDetailsId());
        assertThat(salesOrderClinicalDetailsDTO1).isEqualTo(salesOrderClinicalDetailsDTO2);
        salesOrderClinicalDetailsDTO2.setSalesOrderClinicalDetailsId(2L);
        assertThat(salesOrderClinicalDetailsDTO1).isNotEqualTo(salesOrderClinicalDetailsDTO2);
        salesOrderClinicalDetailsDTO1.setSalesOrderClinicalDetailsId(null);
        assertThat(salesOrderClinicalDetailsDTO1).isNotEqualTo(salesOrderClinicalDetailsDTO2);
    }
}
