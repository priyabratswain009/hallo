package com.sunknowledge.dme.rcm.service.impl.pickupExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.pickupExchange.PickUpExchangeRepository;
import com.sunknowledge.dme.rcm.repository.pickupExchange.PickupExchangeItemsRepo;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeCustomReqDTO;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeCustomRespDTO;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeDataForListDTO;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PickUpExchangeServiceImpl implements PickUpExchangeService {

	@Autowired
	private PickUpExchangeRepository pickUpExchangeRepository;
	@Autowired
	private PickupExchangeItemsRepo pickupExchangeItemsRepository;

	@Override
	public Mono<PickupExchange> pickupExchangeCreation(PickupExchangeCustomReqDTO pickupExchangeCustomDTO) {
		// TODO Auto-generated method stub
		return pickUpExchangeRepository.pickupExchangeCreation(pickupExchangeCustomDTO.getSoId(),
				pickupExchangeCustomDTO.getItemid(), pickupExchangeCustomDTO.getReplacementItemSerialNo(),
				pickupExchangeCustomDTO.getReplacementItemAssetNo(), pickupExchangeCustomDTO.getPickupExchangeType(),
				pickupExchangeCustomDTO.getPickupExchangeScheduleDateTime(), Long.valueOf("5501"), "ANDROKTASIAI",
				pickupExchangeCustomDTO.getPickupExchangeSupportingDocument1(),
				pickupExchangeCustomDTO.getPickupExchangeSupportingDocument2(),
				pickupExchangeCustomDTO.getPickupExchangeReason());
	}

	@Override
	public Flux<ServiceOutcome<List<PickupExchange>>> getPickupExchangeByStatus(String status)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		ServiceOutcome<List<PickupExchange>> outCome = new ServiceOutcome<List<PickupExchange>>();

		List<PickupExchange> pickupExchangeList = pickUpExchangeRepository.getPickupExchangeByStatus(status)
				.collectList().toFuture().get();

		if (pickupExchangeList == null) {
			outCome.setData(null);
			outCome.setMessage("Data Not Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(pickupExchangeList);
			outCome.setMessage("");
			outCome.setOutcome(true);
		}

		return Flux.just(outCome);
	}

	@Override
	public Flux<ServiceOutcome> getPickupExchangeByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ServiceOutcome outCome = new ServiceOutcome();
		PickupExchangeCustomRespDTO PickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();
		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByPickupExchangeNo(pickupExchangeNo)
				.toFuture().get();

		if (pickupExchange != null) {
			PickupExchangeCustomRespDTO = setDTOData(pickupExchange);
			outCome.setMessage("");
			outCome.setOutcome(true);
			outCome.setStatusCode("200");
		} else {
			outCome.setMessage("Data Not Found");
			outCome.setOutcome(false);
			outCome.setStatusCode("204");
		}
		outCome.setData(PickupExchangeCustomRespDTO);

		return Flux.just(outCome);
	}

	@Override
	public Flux<ServiceOutcome> getPickupExchangeDocumentsByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ServiceOutcome outCome = new ServiceOutcome();
		PickupExchangeCustomRespDTO PickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();
		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByPickupExchangeNo(pickupExchangeNo)
				.toFuture().get();

		if (pickupExchange != null) {
			PickupExchangeCustomRespDTO = setDTODocData(pickupExchange);
			outCome.setMessage("");
			outCome.setOutcome(true);
			outCome.setStatusCode("200");
		} else {
			outCome.setMessage("Data Not Found");
			outCome.setOutcome(false);
			outCome.setStatusCode("204");
		}
		outCome.setData(PickupExchangeCustomRespDTO);

		return Flux.just(outCome);
	}

	@Override
	public Flux<ServiceOutcome> getPickupExchangeItemsByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ServiceOutcome outCome = new ServiceOutcome();
		PickupExchangeCustomRespDTO PickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();
		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByPickupExchangeNo(pickupExchangeNo)
				.toFuture().get();
		List<PickupExchangeItems> pickupExchangeItems = pickupExchangeItemsRepository
				.getPickupExchangeItemsByPickupExchangeId(pickupExchange.getPickupExchangeId()).collectList().toFuture()
				.get();

		if (pickupExchange != null) {
			PickupExchangeCustomRespDTO = setDTOItemsData(pickupExchangeItems);
			outCome.setMessage("");
			outCome.setOutcome(true);
			outCome.setStatusCode("200");
		} else {
			outCome.setMessage("Data Not Found");
			outCome.setOutcome(false);
			outCome.setStatusCode("204");
		}
		outCome.setData(PickupExchangeCustomRespDTO);

		return Flux.just(outCome);
	}

	@Override
	public Flux<ServiceOutcome> getPickupExchangeDataForList(String pickupExchangeNo, String pickupexchangetype,
			String sono, String patientidno, String patientfirstname, String patientlastname,
			String pickupexchangeagentidno, String pickupexchangeagentname, String pickupexchangestatus)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ServiceOutcome outCome = new ServiceOutcome();

		if (!CommonUtilities.isStringNullOrBlank(pickupExchangeNo)) {
			pickupExchangeNo = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(pickupexchangetype)) {
			pickupexchangetype = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(sono)) {
			sono = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(patientidno)) {
			patientidno = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(patientfirstname)) {
			patientfirstname = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(patientlastname)) {
			patientlastname = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(pickupexchangeagentidno)) {
			pickupexchangeagentidno = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(pickupexchangeagentname)) {
			pickupexchangeagentname = null;
		}
		if (!CommonUtilities.isStringNullOrBlank(pickupexchangestatus)) {
			pickupexchangestatus = null;
		}

		List<PickupExchangeDataForListDTO> pickupExchangeDataForListDTO = pickUpExchangeRepository
				.getPickupExchangeDataForList(pickupExchangeNo, pickupexchangetype, sono, patientidno, patientfirstname,
						patientlastname, pickupexchangeagentidno, pickupexchangeagentname, pickupexchangestatus)
				.collectList().toFuture().get();

		if (pickupExchangeDataForListDTO.size() > 0) {
			outCome.setData(pickupExchangeDataForListDTO);
			outCome.setMessage("");
			outCome.setOutcome(true);
			outCome.setStatusCode("200");
		} else {
			outCome.setData(new JSONObject());
			outCome.setMessage("");
			outCome.setOutcome(true);
			outCome.setStatusCode("200");
		}

		return Flux.just(outCome);
	}

	PickupExchangeCustomRespDTO setDTOData(PickupExchange pickupExchange) {
		PickupExchangeCustomRespDTO pickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();

		pickupExchangeCustomRespDTO.setPickupExchangeNo(pickupExchange.getPickupExchangeNo());
		pickupExchangeCustomRespDTO.setPickupExchangeType(pickupExchange.getPickupExchangeType());
		pickupExchangeCustomRespDTO.setPickupExchangeStatus(pickupExchange.getPickupExchangeStatus());
		pickupExchangeCustomRespDTO.setPickupExchangeReason(pickupExchange.getPickupExchangeReason());
		pickupExchangeCustomRespDTO.setPatientNotsignedReason(pickupExchange.getPatientNotsignedReason());
		pickupExchangeCustomRespDTO.setStatus(pickupExchange.getStatus());
		pickupExchangeCustomRespDTO
				.setPickupExchangeScheduleDateTime(pickupExchange.getPickupExchangeScheduleDateTime());
		pickupExchangeCustomRespDTO.setPickupExchangeActualDateTime(pickupExchange.getPickupExchangeActualDateTime());
		pickupExchangeCustomRespDTO.setLastBillingDate(pickupExchange.getLastBillingDate());
		pickupExchangeCustomRespDTO.setDateOfDeath(pickupExchange.getDateOfDeath());
		pickupExchangeCustomRespDTO.setIsPatientSigned(pickupExchange.getIsPatientSigned());
		pickupExchangeCustomRespDTO.setPatientSignedDateTime(pickupExchange.getPatientSignedDateTime());
		pickupExchangeCustomRespDTO.setSoNo(pickupExchange.getSoNo());
		pickupExchangeCustomRespDTO.setPickupExchangeAgentName(pickupExchange.getPickupExchangeAgentName());
		pickupExchangeCustomRespDTO.setIsAgentSigned(pickupExchange.getIsAgentSigned());
		pickupExchangeCustomRespDTO.setPickupExchangeRequest(pickupExchange.getPickupExchangeRequest());
		pickupExchangeCustomRespDTO.setPickupExchangeComments(pickupExchange.getPickupExchangeComments());
		pickupExchangeCustomRespDTO.setPickupExchangeNote(pickupExchange.getPickupExchangeNote());

		return pickupExchangeCustomRespDTO;
	}

	PickupExchangeCustomRespDTO setDTODocData(PickupExchange pickupExchange) {
		PickupExchangeCustomRespDTO pickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();

		pickupExchangeCustomRespDTO.setPickupExchangeDocumentNo(pickupExchange.getPickupExchangeDocumentNo());
		pickupExchangeCustomRespDTO.setPickupExchangeDocumentName(pickupExchange.getPickupExchangeDocumentName());
		pickupExchangeCustomRespDTO
				.setPickupExchangeSupportingDocument1(pickupExchange.getPickupExchangeSupportingDocument1());
		pickupExchangeCustomRespDTO
				.setPickupExchangeSupportingDocument2(pickupExchange.getPickupExchangeSupportingDocument2());

		return pickupExchangeCustomRespDTO;
	}

	PickupExchangeCustomRespDTO setDTOItemsData(List<PickupExchangeItems> pickupExchangeItems) {
		PickupExchangeCustomRespDTO pickupExchangeCustomRespDTO = new PickupExchangeCustomRespDTO();
		List<PickupExchangeItems> pickupExchangeItemsList = new ArrayList<>();

		for (PickupExchangeItems obj : pickupExchangeItems) {
			PickupExchangeItems objPickupExchangeItems = new PickupExchangeItems();
			objPickupExchangeItems.setItemNo(obj.getItemNo());
			objPickupExchangeItems.setItemName(obj.getItemName());
			objPickupExchangeItems.setQuantity(obj.getQuantity());
			objPickupExchangeItems.setPickupItemSerialNo(obj.getPickupItemSerialNo());
			objPickupExchangeItems.setPickupItemAssetNo(obj.getPickupItemAssetNo());
			objPickupExchangeItems.setReplacementItemSerialNo(obj.getReplacementItemSerialNo());
			objPickupExchangeItems.setReplacementItemAssetNo(obj.getReplacementItemAssetNo());
			objPickupExchangeItems.setItemPickupExchangeComment(obj.getItemPickupExchangeComment());
			objPickupExchangeItems.setItemPickupExchangeNote(obj.getItemPickupExchangeNote());
			pickupExchangeItemsList.add(objPickupExchangeItems);
		}
		pickupExchangeCustomRespDTO.setPickupExchangeItems(pickupExchangeItemsList);

		return pickupExchangeCustomRespDTO;
	}

}
