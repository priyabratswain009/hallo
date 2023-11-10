package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HcpcsDocTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HcpcsDocTypeDTO.class);
        HcpcsDocTypeDTO hcpcsDocTypeDTO1 = new HcpcsDocTypeDTO();
        hcpcsDocTypeDTO1.setHcpcsDoctypeId(1L);
        HcpcsDocTypeDTO hcpcsDocTypeDTO2 = new HcpcsDocTypeDTO();
        assertThat(hcpcsDocTypeDTO1).isNotEqualTo(hcpcsDocTypeDTO2);
        hcpcsDocTypeDTO2.setHcpcsDoctypeId(hcpcsDocTypeDTO1.getHcpcsDoctypeId());
        assertThat(hcpcsDocTypeDTO1).isEqualTo(hcpcsDocTypeDTO2);
        hcpcsDocTypeDTO2.setHcpcsDoctypeId(2L);
        assertThat(hcpcsDocTypeDTO1).isNotEqualTo(hcpcsDocTypeDTO2);
        hcpcsDocTypeDTO1.setHcpcsDoctypeId(null);
        assertThat(hcpcsDocTypeDTO1).isNotEqualTo(hcpcsDocTypeDTO2);
    }
}
