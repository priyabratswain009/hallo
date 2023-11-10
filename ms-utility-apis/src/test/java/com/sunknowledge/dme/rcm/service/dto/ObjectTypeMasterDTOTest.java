package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ObjectTypeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectTypeMasterDTO.class);
        ObjectTypeMasterDTO objectTypeMasterDTO1 = new ObjectTypeMasterDTO();
        objectTypeMasterDTO1.setObjectId(1L);
        ObjectTypeMasterDTO objectTypeMasterDTO2 = new ObjectTypeMasterDTO();
        assertThat(objectTypeMasterDTO1).isNotEqualTo(objectTypeMasterDTO2);
        objectTypeMasterDTO2.setObjectId(objectTypeMasterDTO1.getObjectId());
        assertThat(objectTypeMasterDTO1).isEqualTo(objectTypeMasterDTO2);
        objectTypeMasterDTO2.setObjectId(2L);
        assertThat(objectTypeMasterDTO1).isNotEqualTo(objectTypeMasterDTO2);
        objectTypeMasterDTO1.setObjectId(null);
        assertThat(objectTypeMasterDTO1).isNotEqualTo(objectTypeMasterDTO2);
    }
}
