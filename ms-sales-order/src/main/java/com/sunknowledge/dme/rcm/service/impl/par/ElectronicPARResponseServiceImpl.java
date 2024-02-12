package com.sunknowledge.dme.rcm.service.impl.par;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.repository.par.ElectronicPARResponseRepo;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparResponseMapper;
import com.sunknowledge.dme.rcm.service.par.ElectronicPARResponseService;

import reactor.core.publisher.Mono;

@Service
public class ElectronicPARResponseServiceImpl implements ElectronicPARResponseService {

	@Autowired
	private ElectronicPARResponseRepo electronicPARResponseRepo;
	@Autowired
	private EparResponseMapper eparResponseMapper;

	@Override
	public Mono<ServiceOutcome<EparResponseDTO>> saveElectronicPar(EparResponse eParResponse) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		ServiceOutcome<EparResponseDTO> outCome = new ServiceOutcome<EparResponseDTO>();
		
		EparResponse eParResponseout = electronicPARResponseRepo.save(eParResponse).toFuture().get();
		
		outCome.setData(eparResponseMapper.toDto(eParResponseout));
		outCome.setMessage("Data Inserted Successfully");
		outCome.setOutcome(true);
		
		return Mono.just(outCome);
	}
	
}
