package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecondaryServiceLinesMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecondaryServiceLinesMaster.class);
        SecondaryServiceLinesMaster secondaryServiceLinesMaster1 = new SecondaryServiceLinesMaster();
        secondaryServiceLinesMaster1.setChangeHealthSecondarySubmisionServicelinesId(1L);
        SecondaryServiceLinesMaster secondaryServiceLinesMaster2 = new SecondaryServiceLinesMaster();
        secondaryServiceLinesMaster2.setChangeHealthSecondarySubmisionServicelinesId(
            secondaryServiceLinesMaster1.getChangeHealthSecondarySubmisionServicelinesId()
        );
        assertThat(secondaryServiceLinesMaster1).isEqualTo(secondaryServiceLinesMaster2);
        secondaryServiceLinesMaster2.setChangeHealthSecondarySubmisionServicelinesId(2L);
        assertThat(secondaryServiceLinesMaster1).isNotEqualTo(secondaryServiceLinesMaster2);
        secondaryServiceLinesMaster1.setChangeHealthSecondarySubmisionServicelinesId(null);
        assertThat(secondaryServiceLinesMaster1).isNotEqualTo(secondaryServiceLinesMaster2);
    }
}
