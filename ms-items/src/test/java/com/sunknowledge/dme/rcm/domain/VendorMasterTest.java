package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VendorMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorMaster.class);
        VendorMaster vendorMaster1 = new VendorMaster();
        vendorMaster1.setVendorId(1L);
        VendorMaster vendorMaster2 = new VendorMaster();
        vendorMaster2.setVendorId(vendorMaster1.getVendorId());
        assertThat(vendorMaster1).isEqualTo(vendorMaster2);
        vendorMaster2.setVendorId(2L);
        assertThat(vendorMaster1).isNotEqualTo(vendorMaster2);
        vendorMaster1.setVendorId(null);
        assertThat(vendorMaster1).isNotEqualTo(vendorMaster2);
    }
}
