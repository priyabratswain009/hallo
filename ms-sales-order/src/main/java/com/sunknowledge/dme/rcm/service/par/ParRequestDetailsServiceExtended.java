package com.sunknowledge.dme.rcm.service.par;

import com.sunknowledge.dme.rcm.service.ParRequestDetailsService;
import reactor.core.publisher.Mono;

public interface ParRequestDetailsServiceExtended extends ParRequestDetailsService {
    Mono<String> getParRequestDetailsByFaxNo(String faxNo);
}
