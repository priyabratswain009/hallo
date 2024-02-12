package com.sunknowledge.dme.rcm.service.claimssubmissiondata;

import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import reactor.core.publisher.Mono;

public interface TokenGenerationService {
	TokenOutCome getToken();

    String getCoverageToken();

    Mono<TokenOutCome> getTokenMono();
}
