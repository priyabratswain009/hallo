package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserMaster.class);
        UserMaster userMaster1 = new UserMaster();
        userMaster1.setUserId(1L);
        UserMaster userMaster2 = new UserMaster();
        userMaster2.setUserId(userMaster1.getUserId());
        assertThat(userMaster1).isEqualTo(userMaster2);
        userMaster2.setUserId(2L);
        assertThat(userMaster1).isNotEqualTo(userMaster2);
        userMaster1.setUserId(null);
        assertThat(userMaster1).isNotEqualTo(userMaster2);
    }
}
