package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderDocumentsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderDocumentsDTO.class);
        SalesOrderDocumentsDTO salesOrderDocumentsDTO1 = new SalesOrderDocumentsDTO();
        salesOrderDocumentsDTO1.setSalesOrderDocumentId(1L);
        SalesOrderDocumentsDTO salesOrderDocumentsDTO2 = new SalesOrderDocumentsDTO();
        assertThat(salesOrderDocumentsDTO1).isNotEqualTo(salesOrderDocumentsDTO2);
        salesOrderDocumentsDTO2.setSalesOrderDocumentId(salesOrderDocumentsDTO1.getSalesOrderDocumentId());
        assertThat(salesOrderDocumentsDTO1).isEqualTo(salesOrderDocumentsDTO2);
        salesOrderDocumentsDTO2.setSalesOrderDocumentId(2L);
        assertThat(salesOrderDocumentsDTO1).isNotEqualTo(salesOrderDocumentsDTO2);
        salesOrderDocumentsDTO1.setSalesOrderDocumentId(null);
        assertThat(salesOrderDocumentsDTO1).isNotEqualTo(salesOrderDocumentsDTO2);
    }
}
