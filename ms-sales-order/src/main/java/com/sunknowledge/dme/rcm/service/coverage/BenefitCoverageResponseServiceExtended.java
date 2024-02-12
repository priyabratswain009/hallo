package com.sunknowledge.dme.rcm.service.coverage;

import com.sunknowledge.dme.rcm.service.BenefitCoverageResponseService;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import reactor.core.publisher.Flux;

public interface BenefitCoverageResponseServiceExtended extends BenefitCoverageResponseService {
    Flux<BenefitCoverageResponseDTO> getAllPatientBenefitCoverage();

    Flux<String> getPatientBenefitCoverageByMemberId(String memberId);
}
