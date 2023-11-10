package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDTO.class);
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setProductId(1L);
        ProductDTO productDTO2 = new ProductDTO();
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO2.setProductId(productDTO1.getProductId());
        assertThat(productDTO1).isEqualTo(productDTO2);
        productDTO2.setProductId(2L);
        assertThat(productDTO1).isNotEqualTo(productDTO2);
        productDTO1.setProductId(null);
        assertThat(productDTO1).isNotEqualTo(productDTO2);
    }
}
