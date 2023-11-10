package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepositMasterDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepositMasterDetails.class);
        DepositMasterDetails depositMasterDetails1 = new DepositMasterDetails();
        depositMasterDetails1.setDepositId(1L);
        DepositMasterDetails depositMasterDetails2 = new DepositMasterDetails();
        depositMasterDetails2.setDepositId(depositMasterDetails1.getDepositId());
        assertThat(depositMasterDetails1).isEqualTo(depositMasterDetails2);
        depositMasterDetails2.setDepositId(2L);
        assertThat(depositMasterDetails1).isNotEqualTo(depositMasterDetails2);
        depositMasterDetails1.setDepositId(null);
        assertThat(depositMasterDetails1).isNotEqualTo(depositMasterDetails2);
    }
}
