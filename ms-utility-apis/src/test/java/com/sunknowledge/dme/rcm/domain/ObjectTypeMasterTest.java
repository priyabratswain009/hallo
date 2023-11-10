package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectTypeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectTypeMaster.class);
        ObjectTypeMaster objectTypeMaster1 = new ObjectTypeMaster();
        objectTypeMaster1.setObjectId(1L);
        ObjectTypeMaster objectTypeMaster2 = new ObjectTypeMaster();
        objectTypeMaster2.setObjectId(objectTypeMaster1.getObjectId());
        assertThat(objectTypeMaster1).isEqualTo(objectTypeMaster2);
        objectTypeMaster2.setObjectId(2L);
        assertThat(objectTypeMaster1).isNotEqualTo(objectTypeMaster2);
        objectTypeMaster1.setObjectId(null);
        assertThat(objectTypeMaster1).isNotEqualTo(objectTypeMaster2);
    }
}
