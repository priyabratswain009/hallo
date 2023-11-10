package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VendorMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VendorMasterDTO.class);
        VendorMasterDTO vendorMasterDTO1 = new VendorMasterDTO();
        vendorMasterDTO1.setVendorId(1L);
        VendorMasterDTO vendorMasterDTO2 = new VendorMasterDTO();
        assertThat(vendorMasterDTO1).isNotEqualTo(vendorMasterDTO2);
        vendorMasterDTO2.setVendorId(vendorMasterDTO1.getVendorId());
        assertThat(vendorMasterDTO1).isEqualTo(vendorMasterDTO2);
        vendorMasterDTO2.setVendorId(2L);
        assertThat(vendorMasterDTO1).isNotEqualTo(vendorMasterDTO2);
        vendorMasterDTO1.setVendorId(null);
        assertThat(vendorMasterDTO1).isNotEqualTo(vendorMasterDTO2);
    }
}
