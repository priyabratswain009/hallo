package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProcedureCodeMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProcedureCodeMaster.class);
        ProcedureCodeMaster procedureCodeMaster1 = new ProcedureCodeMaster();
        procedureCodeMaster1.setProcedureCodeId(1L);
        ProcedureCodeMaster procedureCodeMaster2 = new ProcedureCodeMaster();
        procedureCodeMaster2.setProcedureCodeId(procedureCodeMaster1.getProcedureCodeId());
        assertThat(procedureCodeMaster1).isEqualTo(procedureCodeMaster2);
        procedureCodeMaster2.setProcedureCodeId(2L);
        assertThat(procedureCodeMaster1).isNotEqualTo(procedureCodeMaster2);
        procedureCodeMaster1.setProcedureCodeId(null);
        assertThat(procedureCodeMaster1).isNotEqualTo(procedureCodeMaster2);
    }
}
