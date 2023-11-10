package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProcedureCodeMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProcedureCodeMasterDTO.class);
        ProcedureCodeMasterDTO procedureCodeMasterDTO1 = new ProcedureCodeMasterDTO();
        procedureCodeMasterDTO1.setProcedureCodeId(1L);
        ProcedureCodeMasterDTO procedureCodeMasterDTO2 = new ProcedureCodeMasterDTO();
        assertThat(procedureCodeMasterDTO1).isNotEqualTo(procedureCodeMasterDTO2);
        procedureCodeMasterDTO2.setProcedureCodeId(procedureCodeMasterDTO1.getProcedureCodeId());
        assertThat(procedureCodeMasterDTO1).isEqualTo(procedureCodeMasterDTO2);
        procedureCodeMasterDTO2.setProcedureCodeId(2L);
        assertThat(procedureCodeMasterDTO1).isNotEqualTo(procedureCodeMasterDTO2);
        procedureCodeMasterDTO1.setProcedureCodeId(null);
        assertThat(procedureCodeMasterDTO1).isNotEqualTo(procedureCodeMasterDTO2);
    }
}
