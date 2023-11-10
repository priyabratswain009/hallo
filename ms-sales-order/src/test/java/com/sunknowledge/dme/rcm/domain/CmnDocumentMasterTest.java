package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmnDocumentMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmnDocumentMaster.class);
        CmnDocumentMaster cmnDocumentMaster1 = new CmnDocumentMaster();
        cmnDocumentMaster1.setCmnDocumentId(1L);
        CmnDocumentMaster cmnDocumentMaster2 = new CmnDocumentMaster();
        cmnDocumentMaster2.setCmnDocumentId(cmnDocumentMaster1.getCmnDocumentId());
        assertThat(cmnDocumentMaster1).isEqualTo(cmnDocumentMaster2);
        cmnDocumentMaster2.setCmnDocumentId(2L);
        assertThat(cmnDocumentMaster1).isNotEqualTo(cmnDocumentMaster2);
        cmnDocumentMaster1.setCmnDocumentId(null);
        assertThat(cmnDocumentMaster1).isNotEqualTo(cmnDocumentMaster2);
    }
}
