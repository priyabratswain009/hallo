package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepreciationMethodTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepreciationMethod.class);
        DepreciationMethod depreciationMethod1 = new DepreciationMethod();
        depreciationMethod1.setDepreciationMethodId(1L);
        DepreciationMethod depreciationMethod2 = new DepreciationMethod();
        depreciationMethod2.setDepreciationMethodId(depreciationMethod1.getDepreciationMethodId());
        assertThat(depreciationMethod1).isEqualTo(depreciationMethod2);
        depreciationMethod2.setDepreciationMethodId(2L);
        assertThat(depreciationMethod1).isNotEqualTo(depreciationMethod2);
        depreciationMethod1.setDepreciationMethodId(null);
        assertThat(depreciationMethod1).isNotEqualTo(depreciationMethod2);
    }
}
