package com.sunknowledge.dme.rcm.service.coverage;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageInput;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageOutput;
import com.sunknowledge.dme.rcm.service.BenefitCoverageRequestService;
import reactor.core.publisher.Mono;

public interface BenefitCoverageRequestServiceExtended extends BenefitCoverageRequestService {
    Mono<ServiceOutcome<BenefitCoverageResponse>> getSOBenefitCoverage(CoverageInput objCoverageInput);
}
