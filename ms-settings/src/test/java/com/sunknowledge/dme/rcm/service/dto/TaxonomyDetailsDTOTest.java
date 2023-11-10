package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxonomyDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxonomyDetailsDTO.class);
        TaxonomyDetailsDTO taxonomyDetailsDTO1 = new TaxonomyDetailsDTO();
        taxonomyDetailsDTO1.setTaxonomyDetailsId(1L);
        TaxonomyDetailsDTO taxonomyDetailsDTO2 = new TaxonomyDetailsDTO();
        assertThat(taxonomyDetailsDTO1).isNotEqualTo(taxonomyDetailsDTO2);
        taxonomyDetailsDTO2.setTaxonomyDetailsId(taxonomyDetailsDTO1.getTaxonomyDetailsId());
        assertThat(taxonomyDetailsDTO1).isEqualTo(taxonomyDetailsDTO2);
        taxonomyDetailsDTO2.setTaxonomyDetailsId(2L);
        assertThat(taxonomyDetailsDTO1).isNotEqualTo(taxonomyDetailsDTO2);
        taxonomyDetailsDTO1.setTaxonomyDetailsId(null);
        assertThat(taxonomyDetailsDTO1).isNotEqualTo(taxonomyDetailsDTO2);
    }
}
