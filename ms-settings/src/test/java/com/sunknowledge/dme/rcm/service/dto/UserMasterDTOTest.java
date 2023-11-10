package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserMasterDTO.class);
        UserMasterDTO userMasterDTO1 = new UserMasterDTO();
        userMasterDTO1.setUserId(1L);
        UserMasterDTO userMasterDTO2 = new UserMasterDTO();
        assertThat(userMasterDTO1).isNotEqualTo(userMasterDTO2);
        userMasterDTO2.setUserId(userMasterDTO1.getUserId());
        assertThat(userMasterDTO1).isEqualTo(userMasterDTO2);
        userMasterDTO2.setUserId(2L);
        assertThat(userMasterDTO1).isNotEqualTo(userMasterDTO2);
        userMasterDTO1.setUserId(null);
        assertThat(userMasterDTO1).isNotEqualTo(userMasterDTO2);
    }
}
