package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class Claim835277ExceptionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Claim835277ExceptionDTO.class);
        Claim835277ExceptionDTO claim835277ExceptionDTO1 = new Claim835277ExceptionDTO();
        claim835277ExceptionDTO1.setExceptionId(1L);
        Claim835277ExceptionDTO claim835277ExceptionDTO2 = new Claim835277ExceptionDTO();
        assertThat(claim835277ExceptionDTO1).isNotEqualTo(claim835277ExceptionDTO2);
        claim835277ExceptionDTO2.setExceptionId(claim835277ExceptionDTO1.getExceptionId());
        assertThat(claim835277ExceptionDTO1).isEqualTo(claim835277ExceptionDTO2);
        claim835277ExceptionDTO2.setExceptionId(2L);
        assertThat(claim835277ExceptionDTO1).isNotEqualTo(claim835277ExceptionDTO2);
        claim835277ExceptionDTO1.setExceptionId(null);
        assertThat(claim835277ExceptionDTO1).isNotEqualTo(claim835277ExceptionDTO2);
    }
}
