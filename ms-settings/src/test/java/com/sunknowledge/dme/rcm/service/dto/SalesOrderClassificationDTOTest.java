package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderClassificationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderClassificationDTO.class);
        SalesOrderClassificationDTO salesOrderClassificationDTO1 = new SalesOrderClassificationDTO();
        salesOrderClassificationDTO1.setOrderClassificationId(1L);
        SalesOrderClassificationDTO salesOrderClassificationDTO2 = new SalesOrderClassificationDTO();
        assertThat(salesOrderClassificationDTO1).isNotEqualTo(salesOrderClassificationDTO2);
        salesOrderClassificationDTO2.setOrderClassificationId(salesOrderClassificationDTO1.getOrderClassificationId());
        assertThat(salesOrderClassificationDTO1).isEqualTo(salesOrderClassificationDTO2);
        salesOrderClassificationDTO2.setOrderClassificationId(2L);
        assertThat(salesOrderClassificationDTO1).isNotEqualTo(salesOrderClassificationDTO2);
        salesOrderClassificationDTO1.setOrderClassificationId(null);
        assertThat(salesOrderClassificationDTO1).isNotEqualTo(salesOrderClassificationDTO2);
    }
}
