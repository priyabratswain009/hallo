package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CmnDocumentMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CmnDocumentMasterDTO.class);
        CmnDocumentMasterDTO cmnDocumentMasterDTO1 = new CmnDocumentMasterDTO();
        cmnDocumentMasterDTO1.setCmnDocumentId(1L);
        CmnDocumentMasterDTO cmnDocumentMasterDTO2 = new CmnDocumentMasterDTO();
        assertThat(cmnDocumentMasterDTO1).isNotEqualTo(cmnDocumentMasterDTO2);
        cmnDocumentMasterDTO2.setCmnDocumentId(cmnDocumentMasterDTO1.getCmnDocumentId());
        assertThat(cmnDocumentMasterDTO1).isEqualTo(cmnDocumentMasterDTO2);
        cmnDocumentMasterDTO2.setCmnDocumentId(2L);
        assertThat(cmnDocumentMasterDTO1).isNotEqualTo(cmnDocumentMasterDTO2);
        cmnDocumentMasterDTO1.setCmnDocumentId(null);
        assertThat(cmnDocumentMasterDTO1).isNotEqualTo(cmnDocumentMasterDTO2);
    }
}
