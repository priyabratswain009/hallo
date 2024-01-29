package com.sunknowledge.dme.rcm.service.impl.par;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.repository.par.ElectronicPARRequestRepo;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import com.sunknowledge.dme.rcm.service.mapper.EparRequestMapper;
import com.sunknowledge.dme.rcm.service.par.ElectronicPARRequestService;

import reactor.core.publisher.Mono;

@Service
public class ElectronicPARRequestServiceImpl implements ElectronicPARRequestService {
	
	@Autowired
	private ElectronicPARRequestRepo electronicPARRequestRepository;
	@Autowired
	private EparRequestMapper eparRequestMapper;

	@Override
	public Mono<EparRequest> createServiceReview(ServiceReviewInput serviceReviewInput, Long salesOrderId, String soNo,
			String message, String data) throws JsonProcessingException {
		// TODO Auto-generated method stub
		EparRequest epaRequest = new EparRequest();
		ObjectMapper objectMapper = new ObjectMapper();

		epaRequest.setEparRequestId(null);
		epaRequest.setSoId(salesOrderId);
		epaRequest.setSoNo(soNo);
		epaRequest.setRequestDatetime(LocalDate.now());
		epaRequest.setResponseStatus(message);
		epaRequest.setResponseUrl(data);
		epaRequest.setStatus("Active");
		epaRequest.setCreatedById(Long.valueOf("5501"));
		epaRequest.createdByName("ANDROKTASIAI");
		epaRequest.setCreatedDate(LocalDate.now());
		epaRequest.setRequestJson(serviceReviewInput.toString());
		
		System.out.println("JSON - "+objectMapper.writeValueAsString(serviceReviewInput));
		
		return electronicPARRequestRepository.save(epaRequest);
		

	}

	@Override
	public Mono<ServiceOutcome<EparRequestDTO>> getEpaRequestonSoId(Long soId) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		ServiceOutcome<EparRequestDTO> outCome = new ServiceOutcome<EparRequestDTO>();
		
		EparRequest epaRequest = electronicPARRequestRepository.getEpaRequestonSoId(soId).toFuture().get();
		
		if(epaRequest==null) {
			outCome.setData(null);
			outCome.setMessage("Data not Found");
			outCome.setOutcome(false);
		}else {
			outCome.setData(eparRequestMapper.toDto(epaRequest));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}
		
		return Mono.just(outCome);
	}

}
