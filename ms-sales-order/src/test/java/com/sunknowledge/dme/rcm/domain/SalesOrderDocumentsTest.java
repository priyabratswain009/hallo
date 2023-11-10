package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesOrderDocumentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesOrderDocuments.class);
        SalesOrderDocuments salesOrderDocuments1 = new SalesOrderDocuments();
        salesOrderDocuments1.setSalesOrderDocumentId(1L);
        SalesOrderDocuments salesOrderDocuments2 = new SalesOrderDocuments();
        salesOrderDocuments2.setSalesOrderDocumentId(salesOrderDocuments1.getSalesOrderDocumentId());
        assertThat(salesOrderDocuments1).isEqualTo(salesOrderDocuments2);
        salesOrderDocuments2.setSalesOrderDocumentId(2L);
        assertThat(salesOrderDocuments1).isNotEqualTo(salesOrderDocuments2);
        salesOrderDocuments1.setSalesOrderDocumentId(null);
        assertThat(salesOrderDocuments1).isNotEqualTo(salesOrderDocuments2);
    }
}
