package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxonomyDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxonomyDetails.class);
        TaxonomyDetails taxonomyDetails1 = new TaxonomyDetails();
        taxonomyDetails1.setTaxonomyDetailsId(1L);
        TaxonomyDetails taxonomyDetails2 = new TaxonomyDetails();
        taxonomyDetails2.setTaxonomyDetailsId(taxonomyDetails1.getTaxonomyDetailsId());
        assertThat(taxonomyDetails1).isEqualTo(taxonomyDetails2);
        taxonomyDetails2.setTaxonomyDetailsId(2L);
        assertThat(taxonomyDetails1).isNotEqualTo(taxonomyDetails2);
        taxonomyDetails1.setTaxonomyDetailsId(null);
        assertThat(taxonomyDetails1).isNotEqualTo(taxonomyDetails2);
    }
}
