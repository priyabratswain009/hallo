package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderMasterDTO.class);
        SalesOrderMasterDTO salesOrderMasterDTO1 = new SalesOrderMasterDTO();
        salesOrderMasterDTO1.setSalesOrderId(1L);
        SalesOrderMasterDTO salesOrderMasterDTO2 = new SalesOrderMasterDTO();
        assertThat(salesOrderMasterDTO1).isNotEqualTo(salesOrderMasterDTO2);
        salesOrderMasterDTO2.setSalesOrderId(salesOrderMasterDTO1.getSalesOrderId());
        assertThat(salesOrderMasterDTO1).isEqualTo(salesOrderMasterDTO2);
        salesOrderMasterDTO2.setSalesOrderId(2L);
        assertThat(salesOrderMasterDTO1).isNotEqualTo(salesOrderMasterDTO2);
        salesOrderMasterDTO1.setSalesOrderId(null);
        assertThat(salesOrderMasterDTO1).isNotEqualTo(salesOrderMasterDTO2);
    }
}
