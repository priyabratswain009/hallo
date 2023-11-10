package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesRepTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesRep.class);
        SalesRep salesRep1 = new SalesRep();
        salesRep1.setSalesRepId(1L);
        SalesRep salesRep2 = new SalesRep();
        salesRep2.setSalesRepId(salesRep1.getSalesRepId());
        assertThat(salesRep1).isEqualTo(salesRep2);
        salesRep2.setSalesRepId(2L);
        assertThat(salesRep1).isNotEqualTo(salesRep2);
        salesRep1.setSalesRepId(null);
        assertThat(salesRep1).isNotEqualTo(salesRep2);
    }
}
