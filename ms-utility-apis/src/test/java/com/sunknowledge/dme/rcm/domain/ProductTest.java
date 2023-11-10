package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setProductId(1L);
        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        assertThat(product1).isEqualTo(product2);
        product2.setProductId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setProductId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
