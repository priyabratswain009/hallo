package com.sunknowledge.dme.rcm.service.par;

import java.util.concurrent.ExecutionException;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;

import reactor.core.publisher.Mono;

public interface ElectronicPARResponseService {

	Mono<ServiceOutcome<EparResponseDTO>> saveElectronicPar(EparResponse eParResponse) throws InterruptedException, ExecutionException;
	
}
