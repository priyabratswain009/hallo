package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HcpcsDocTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HcpcsDocType.class);
        HcpcsDocType hcpcsDocType1 = new HcpcsDocType();
        hcpcsDocType1.setHcpcsDoctypeId(1L);
        HcpcsDocType hcpcsDocType2 = new HcpcsDocType();
        hcpcsDocType2.setHcpcsDoctypeId(hcpcsDocType1.getHcpcsDoctypeId());
        assertThat(hcpcsDocType1).isEqualTo(hcpcsDocType2);
        hcpcsDocType2.setHcpcsDoctypeId(2L);
        assertThat(hcpcsDocType1).isNotEqualTo(hcpcsDocType2);
        hcpcsDocType1.setHcpcsDoctypeId(null);
        assertThat(hcpcsDocType1).isNotEqualTo(hcpcsDocType2);
    }
}
