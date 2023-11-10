package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepositMasterDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepositMasterDetailsDTO.class);
        DepositMasterDetailsDTO depositMasterDetailsDTO1 = new DepositMasterDetailsDTO();
        depositMasterDetailsDTO1.setDepositId(1L);
        DepositMasterDetailsDTO depositMasterDetailsDTO2 = new DepositMasterDetailsDTO();
        assertThat(depositMasterDetailsDTO1).isNotEqualTo(depositMasterDetailsDTO2);
        depositMasterDetailsDTO2.setDepositId(depositMasterDetailsDTO1.getDepositId());
        assertThat(depositMasterDetailsDTO1).isEqualTo(depositMasterDetailsDTO2);
        depositMasterDetailsDTO2.setDepositId(2L);
        assertThat(depositMasterDetailsDTO1).isNotEqualTo(depositMasterDetailsDTO2);
        depositMasterDetailsDTO1.setDepositId(null);
        assertThat(depositMasterDetailsDTO1).isNotEqualTo(depositMasterDetailsDTO2);
    }
}
