package com.sunknowledge.dme.rcm.service.impl.pickupExchange;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.documentutil.DeliveryPickupExchangeHeaderFooterPageEvent;
import com.sunknowledge.dme.rcm.documentutil.PickupExchangeDocumentTableBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.domain.abn.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.domain.abn.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeItem;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeReqandResp;
import com.sunknowledge.dme.rcm.repository.pickupExchange.PickUpExchangeRepo;
import com.sunknowledge.dme.rcm.repository.pickupExchange.PickupExchangeItemsRepo;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.impl.delivery.DeliveryServiceImpl;
import com.sunknowledge.dme.rcm.service.mapper.PickupExchangeMapper;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;

@Service
@Slf4j
public class PickUpExchangeServiceImpl implements PickUpExchangeService {

	@Autowired
	private PickUpExchangeRepo pickUpExchangeRepository;
	@Autowired
	private PickupExchangeItemsRepo pickupExchangeItemsRepository;
	@Autowired
	private PickupExchangeMapper pickupExchangeMapper;

	@Autowired
	private FileUploadConfigProperties fileUploadConfigProperties;

	@Override
	public ServiceOutcome<PickupExchangeDTO> updatePickupExchangedeliveryagentdata(UUID pickupexchangeuuid,
			String pickupexchangescheduledatetime, String pickupexchangeagentidno, String pickupexchangeagentname,
			String noteForDeliveryAgent) {
		// TODO Auto-generated method stub

		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByUUID(pickupexchangeuuid);
		ServiceOutcome<PickupExchangeDTO> outCome = new ServiceOutcome<PickupExchangeDTO>();

		if (pickupExchange == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {

			pickupExchange.setStatus("InActive");
			pickUpExchangeRepository.save(pickupExchange);

			pickupExchange.setPickupExchangeScheduleDateTime(
					CommonUtilities.stringtodateConverter(pickupexchangescheduledatetime));
			pickupExchange.setPickupExchangeAgentIdNo(pickupexchangeagentidno);
			pickupExchange.setPickupExchangeAgentName(pickupexchangeagentname);
			pickupExchange.setPickupExchangeNote(noteForDeliveryAgent);
			pickupExchange.setStatus("Active");
			pickUpExchangeRepository.save(pickupExchange);

			outCome.setData(pickupExchangeMapper.toDto(pickupExchange));
			outCome.setMessage("Agent assigned to a Pickup/Exchange");
			outCome.setOutcome(true);

		}

		return outCome;
	}

	@Override
	public ServiceOutcome<PickupExchangeDTO> getPickupExchangeByUUID(UUID pickupexchangeuuid) {
		// TODO Auto-generated method stub

		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByUUID(pickupexchangeuuid);
		ServiceOutcome<PickupExchangeDTO> outCome = new ServiceOutcome<PickupExchangeDTO>();

		if (pickupExchange == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(pickupExchangeMapper.toDto(pickupExchange));
			outCome.setMessage("No Data Found");
			outCome.setOutcome(true);

		}

		return outCome;
	}

	@Override
	public ServiceOutcome<PickupExchangeReqandResp> getPickupExchangeDataForReport(UUID pickupexchangeuuid)
			throws Exception {
		// TODO Auto-generated method stub
		PickupExchangeReqandResp pickupExchangeReqandResp = new PickupExchangeReqandResp();
		ServiceOutcome<PickupExchangeReqandResp> outCome = new ServiceOutcome<PickupExchangeReqandResp>();

		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeByUUID(pickupexchangeuuid);
		List<PickupExchangeItems> pickupExchangeItems = pickupExchangeItemsRepository
				.getPickupExchangeItemsByPickupId(pickupExchange.getPickupExchangeId());
		if (pickupExchange == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			pickupExchangeReqandResp = setResponseData(pickupExchange, pickupExchangeItems);
			outCome.setData(pickupExchangeReqandResp);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);

		}

		return outCome;
	}

	@Override
	public ServiceOutcome<PickupExchangeReqandResp> generateandUpdatePickupExchangeDocument(
			PickupExchangeReqandResp pickupExchangeReqandResp) throws Exception {
		// TODO Auto-generated method stub
		DeliveryServiceImpl deliveryServiceImpl = new DeliveryServiceImpl();
		ServiceOutcome<PickupExchangeReqandResp> outCome = new ServiceOutcome<PickupExchangeReqandResp>();
		List<PickupExchangeItem> pickupExchangeItems = new ArrayList<>();
		for (PickupExchangeItem item : pickupExchangeReqandResp.getPickupExchangeItems()) {
			PickupExchangeItem pickupExchangeItem = new PickupExchangeItem();

			pickupExchangeItem.setPickupExchangeItemId(pickupExchangeItem.getPickupExchangeItemId());
			pickupExchangeItem.setPickupExchangeId(item.getPickupExchangeId());
			pickupExchangeItem.setSoId(item.getSoId());
			pickupExchangeItem.setItemId(item.getItemId());
			pickupExchangeItem.setItemNo(item.getItemNo());
			pickupExchangeItem.setItemName(item.getItemName());
			pickupExchangeItem.setWhetherSerialized(item.getWhetherSerialized());
			pickupExchangeItem.setPickupItemSerialNo(item.getPickupItemSerialNo());
			pickupExchangeItem.setPickupItemAssetNo(item.getPickupItemAssetNo());
			pickupExchangeItem.setReplacementItemSerialNo(item.getReplacementItemSerialNo());
			pickupExchangeItem.setReplacementItemAssetNo(item.getReplacementItemAssetNo());
			pickupExchangeItem.setQuantity(item.getQuantity());
			pickupExchangeItem.setItemPickupExchangeType(item.getItemPickupExchangeType());
			pickupExchangeItem.setItemPickupExchangeNote(item.getItemPickupExchangeNote());
			pickupExchangeItem.setItemPickupExchangeComment(item.getItemPickupExchangeComment());
			pickupExchangeItem.setItemPickupExchangeStatus(item.getItemPickupExchangeStatus());
			pickupExchangeItem.setStatus(item.getStatus());
			pickupExchangeItem.setCreatedById(item.getCreatedById());
			pickupExchangeItem.setCreatedByName(item.getCreatedByName());
			pickupExchangeItem.setCreatedDate(item.getCreatedDate());
			pickupExchangeItem.setUpdatedById(item.getUpdatedById());
			pickupExchangeItem.setUpdatedByName(item.getUpdatedByName());
			pickupExchangeItem.setUpdatedDate(item.getUpdatedDate());
			pickupExchangeItem.setPickupExchangeItemUuid(item.getPickupExchangeItemUuid());

			pickupExchangeItems.add(pickupExchangeItem);
		}
		if (!CommonUtilities.isStringNullOrBlank(pickupExchangeReqandResp.getPatientSignature())
				&& (!CommonUtilities.isStringNullOrBlank(pickupExchangeReqandResp.getRelationshipWithPatient()))) {

			outCome.setData(null);
			outCome.setMessage("Patient must Sign or Signatory Reation must be defined");
			outCome.setOutcome(false);

		} else {
			String getPOEDocFilePath = fileUploadConfigProperties.getPickupExchangeDocumentProperties().getLocation();
			String getQRCodeForPOEDocFilePath = fileUploadConfigProperties.getPickupExchangeTempQrCodeProperties()
					.getLocation();
			String getPOESignature = fileUploadConfigProperties.getSignatureProperties().getLocation();

			// Report Generation
			preparePickUpExchangeTicketReport(pickupExchangeReqandResp, getPOEDocFilePath,
					getQRCodeForPOEDocFilePath, getPOESignature);

			String itemmessage = setPickupExchangeItems(pickupExchangeItems, pickupExchangeReqandResp);

			if (CommonUtilities.isStringNullOrBlank(itemmessage)) {
				outCome.setData(null);
				outCome.setMessage(itemmessage);
				outCome.setOutcome(false);

				return outCome;
			}

			String pickupmessage = setPickupExchange(pickupExchangeReqandResp);

			if (CommonUtilities.isStringNullOrBlank(pickupmessage)) {
				outCome.setData(null);
				outCome.setMessage(pickupmessage);
				outCome.setOutcome(false);

				return outCome;
			}

			outCome.setData(pickupExchangeReqandResp);
			outCome.setMessage("Generation Successfull");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	private String setPickupExchange(PickupExchangeReqandResp pickupExchangeReqandResp) throws JsonProcessingException {
		String message = "";

		PickupExchange pickupExchange = pickUpExchangeRepository
				.getPickupExchangeByUUID(pickupExchangeReqandResp.getPickupExchangeUuid());

		if (!pickupExchangeReqandResp.getPickupExchangeNo()
				.equalsIgnoreCase(pickupExchangeReqandResp.getPickupExchangeDocumentNo())) {
			message = "Document Name and Exchange No must be same";
			return message;
		}

		pickupExchange.setPickupExchangeId(pickupExchangeReqandResp.getPickupExchangeId());
		pickupExchange.setPickupExchangeNo(pickupExchangeReqandResp.getPickupExchangeNo());
		pickupExchange.setPickupExchangeType(pickupExchangeReqandResp.getPickupExchangeType());
		pickupExchange.setSoId(pickupExchangeReqandResp.getSoId());
		pickupExchange.setSoNo(pickupExchangeReqandResp.getSoNo());
		pickupExchange.setBranchId(pickupExchangeReqandResp.getBranchId());
		pickupExchange.setBranchName(pickupExchangeReqandResp.getBranchName());
		pickupExchange.setInventoryLocationId(pickupExchangeReqandResp.getInventoryLocationId());
		pickupExchange.setInventoryLocationName(pickupExchangeReqandResp.getInventoryLocationName());
		pickupExchange.setPatientId(pickupExchangeReqandResp.getPatientId());
		pickupExchange.setPatientIdNo(pickupExchangeReqandResp.getPatientIdNo());
		pickupExchange.setPatientFirstName(pickupExchangeReqandResp.getPatientFirstName());
		pickupExchange.setPatientMiddleName(pickupExchangeReqandResp.getPatientMiddleName());
		pickupExchange.setPatientLastName(pickupExchangeReqandResp.getPatientLastName());
		pickupExchange.setPatientContact1(pickupExchangeReqandResp.getPatientContact1());
		pickupExchange.setPatientContact2(pickupExchangeReqandResp.getPatientContact2());
		pickupExchange.setPatientBillingAddressLine1(pickupExchangeReqandResp.getPatientBillingAddressLine1());
		pickupExchange.setPatientBillingAddressLine2(pickupExchangeReqandResp.getPatientBillingAddressLine2());
		pickupExchange.setPatientBillingAddressState(pickupExchangeReqandResp.getPatientBillingAddressState());
		pickupExchange.setPatientBillingAddressCity(pickupExchangeReqandResp.getPatientBillingAddressCity());
		pickupExchange.setPatientBillingAddressZip(pickupExchangeReqandResp.getPatientBillingAddressZip());
		pickupExchange.setPatientDeliveyAddressLine1(pickupExchangeReqandResp.getPatientDeliveyAddressLine1());
		pickupExchange.setPatientDeliveyAddressLine2(pickupExchangeReqandResp.getPatientDeliveyAddressLine2());
		pickupExchange.setPatientDeliveyAddressState(pickupExchangeReqandResp.getPatientDeliveyAddressState());
		pickupExchange.setPatientDeliveyAddressCity(pickupExchangeReqandResp.getPatientDeliveyAddressCity());
		pickupExchange.setPatientDeliveyAddressZip(pickupExchangeReqandResp.getPatientDeliveyAddressZip());
		pickupExchange.setPickupExchangeScheduleDateTime(pickupExchangeReqandResp.getPickupExchangeScheduleDateTime());
		pickupExchange.setPickupExchangeActualDateTime(pickupExchangeReqandResp.getPickupExchangeActualDateTime());
		pickupExchange.setPickupExchangeReason(pickupExchangeReqandResp.getPickupExchangeReason());
		pickupExchange.setPickupExchangeRequest(pickupExchangeReqandResp.getPickupExchangeRequest());
		pickupExchange.setPickupExchangeNote(pickupExchangeReqandResp.getPickupExchangeNote());
		pickupExchange.setPickupExchangeAgentIdNo(pickupExchangeReqandResp.getPickupExchangeAgentIdNo());
		pickupExchange.setPickupExchangeAgentName(pickupExchangeReqandResp.getPickupExchangeAgentName());
		pickupExchange.setPickupExchangeDocumentId(pickupExchangeReqandResp.getPickupExchangeDocumentId());
		pickupExchange.setPickupExchangeDocumentNo(pickupExchangeReqandResp.getPickupExchangeDocumentNo());
		pickupExchange.setPickupExchangeDocumentName(pickupExchangeReqandResp.getPickupExchangeDocumentName());
		pickupExchange.setPickupExchangeStatus(pickupExchangeReqandResp.getPickupExchangeStatus());
		pickupExchange.setPickupExchangeComments(pickupExchangeReqandResp.getPickupExchangeComments());
		if (CommonUtilities.isStringNullOrBlank(pickupExchangeReqandResp.getPatientSignature())) {
			pickupExchange.setIsPatientSigned("Y");
		}
		pickupExchange.setRelationshipWithPatient(pickupExchangeReqandResp.getRelationshipWithPatient());
		pickupExchange.setPatientSignedDateTime(pickupExchangeReqandResp.getPatientSignedDateTime());
		if (CommonUtilities.isStringNullOrBlank(pickupExchangeReqandResp.getTechnicianSignature())) {
			pickupExchange.setIsAgentSigned("Y");
		}
		pickupExchange.setLastBillingDate(pickupExchangeReqandResp.getLastBillingDate());
		pickupExchange.setDateOfDeath(pickupExchangeReqandResp.getDateOfDeath());
		pickupExchange
				.setPickupExchangeSupportingDocument1(pickupExchangeReqandResp.getPickupExchangeSupportingDocument1());
		pickupExchange
				.setPickupExchangeSupportingDocument2(pickupExchangeReqandResp.getPickupExchangeSupportingDocument2());
		pickupExchange.setPatientNotsignedReason(pickupExchangeReqandResp.getPatientNotsignedReason());
		pickupExchange.setStatus(pickupExchangeReqandResp.getStatus());
		pickupExchange.setCreatedById(pickupExchangeReqandResp.getCreatedById());
		pickupExchange.setCreatedByName(pickupExchangeReqandResp.getCreatedByName());
		pickupExchange.setCreatedDate(pickupExchangeReqandResp.getCreatedDate());
		pickupExchange.setUpdatedById(Long.valueOf("5501"));
		pickupExchange.setUpdatedByName("ANDROKTASIAI");
		pickupExchange.setUpdatedDate(LocalDate.now());
		pickupExchange.setPickupExchangeUuid(pickupExchangeReqandResp.getPickupExchangeUuid());
		pickupExchange.setPickupExchangeJsonData(pickupExchangeReqandResp.toString());

		pickUpExchangeRepository.save(pickupExchange);

		return message;
	}

	private String setPickupExchangeItems(List<PickupExchangeItem> pickupExchangeItems,
			PickupExchangeReqandResp pickupExchangeReqandResp) throws Exception {

		String message = "";

		for (int i = 0; i < pickupExchangeItems.size(); i++) {
			PickupExchangeItems pickupExchangeItem = new PickupExchangeItems();

			if (pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemPickupExchangeType()
					.equalsIgnoreCase("Exchange")) {
				ItemSerialNumberDTO itemSerialNumberDTO = getItemSerialNumberByserialNo(
						pickupExchangeReqandResp.getPickupExchangeItems().get(i).getReplacementItemSerialNo(),
						pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemId());

				if (CommonUtilities.isStringNullOrBlank(
						pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupItemSerialNo())) {
					if (!CommonUtilities.isStringNullOrBlank(
							pickupExchangeReqandResp.getPickupExchangeItems().get(i).getReplacementItemSerialNo())) {
						message = "Replacement Item Serial No cannot be null";
						return message;
					}
				}
				if (itemSerialNumberDTO == null) {
					message = "Replacement Item Serial No not Found";
					return message;
				}
			}
			//Updating t_item_inventory_status and t_item_serial_number
			updateInventoryStatusQtyandserialNo(pickupExchangeItems.get(i).getItemNo(),
					pickupExchangeItems.get(i).getPickupItemSerialNo(),
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getReplacementItemSerialNo(),
					pickupExchangeItems.get(i).getItemId(), pickupExchangeItems.get(i).getItemPickupExchangeType(),
					pickupExchangeReqandResp.getInventoryLocationId(),pickupExchangeItems.get(i).getQuantity());
			
			// Updating t_so_item_transaction_details
			itemTransactionDetailsDataUpdate(pickupExchangeItems.get(i).getPickupItemSerialNo(), 
					pickupExchangeItems.get(i).getReplacementItemAssetNo(), 
					pickupExchangeItems.get(i).getReplacementItemSerialNo(), pickupExchangeItems.get(i).getItemNo(), 
					pickupExchangeItems.get(i).getItemId(), pickupExchangeReqandResp.getSoNo(), 
					pickupExchangeItems.get(i).getItemPickupExchangeType());

			pickupExchangeItem.setPickupExchangeItemId(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupExchangeItemId());
			pickupExchangeItem.setPickupExchangeId(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupExchangeId());
			pickupExchangeItem.setSoId(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getSoId());
			pickupExchangeItem.setItemId(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemId());
			pickupExchangeItem.setItemNo(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemNo());
			pickupExchangeItem.setItemName(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemName());
			pickupExchangeItem.setWhetherSerialized(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getWhetherSerialized());
			pickupExchangeItem.setPickupItemSerialNo(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupItemSerialNo());
			pickupExchangeItem.setPickupItemAssetNo(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupItemAssetNo());
			pickupExchangeItem.setReplacementItemSerialNo(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getReplacementItemSerialNo());
			pickupExchangeItem.setReplacementItemAssetNo(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getReplacementItemAssetNo());
			pickupExchangeItem.setQuantity(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getQuantity());
			pickupExchangeItem.setItemPickupExchangeType(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemPickupExchangeType());
			pickupExchangeItem.setItemPickupExchangeNote(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemPickupExchangeNote());
			pickupExchangeItem.setItemPickupExchangeComment(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemPickupExchangeComment());
			pickupExchangeItem.setItemPickupExchangeStatus(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getItemPickupExchangeStatus());
			pickupExchangeItem.setStatus(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getStatus());
			pickupExchangeItem
					.setCreatedById(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getCreatedById());
			pickupExchangeItem
					.setCreatedByName(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getCreatedByName());
			pickupExchangeItem
					.setCreatedDate(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getCreatedDate());
			pickupExchangeItem
					.setUpdatedById(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getUpdatedById());
			pickupExchangeItem
					.setUpdatedByName(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getUpdatedByName());
			pickupExchangeItem
					.setUpdatedDate(pickupExchangeReqandResp.getPickupExchangeItems().get(i).getUpdatedDate());
			pickupExchangeItem.setPickupExchangeItemUuid(
					pickupExchangeReqandResp.getPickupExchangeItems().get(i).getPickupExchangeItemUuid());

			pickupExchangeItemsRepository.save(pickupExchangeItem);
		}
		return message;
	}

	private PickupExchangeReqandResp setResponseData(PickupExchange pickupExchange,
			List<PickupExchangeItems> pickupExchangeItems) throws Exception {
		PickupExchangeReqandResp pickupExchangeReqandResp = new PickupExchangeReqandResp();
		List<PickupExchangeItem> listpickupExchangeItems = new ArrayList<>();
		BranchOfficeDTO branchOfficeDTO = getBranchOfficeData(String.valueOf(pickupExchange.getBranchId()));

		pickupExchangeReqandResp.setBranchAddress(branchOfficeDTO.getBillingAddressLine1() + ", "
				+ branchOfficeDTO.getBillingAddressLine2() + ", " + branchOfficeDTO.getBillingState() + ", "
				+ branchOfficeDTO.getBillingCity() + ", " + branchOfficeDTO.getBillingZipCode());

		pickupExchangeReqandResp.setCurrentDate(String.valueOf(LocalDate.now()));

		pickupExchangeReqandResp.setPatientSignature("");

		pickupExchangeReqandResp.setTechnicianSignature("");

		pickupExchangeReqandResp.setPickupExchangeId(pickupExchange.getPickupExchangeId());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeNo())) {
			pickupExchangeReqandResp.setPickupExchangeNo(pickupExchange.getPickupExchangeNo());
		} else {
			pickupExchangeReqandResp.setPickupExchangeNo("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeType())) {
			pickupExchangeReqandResp.setPickupExchangeType(pickupExchange.getPickupExchangeType());
		} else {
			pickupExchangeReqandResp.setPickupExchangeType("");
		}
		pickupExchangeReqandResp.setSoId(pickupExchange.getSoId());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getSoNo())) {
			pickupExchangeReqandResp.setSoNo(pickupExchange.getSoNo());
		} else {
			pickupExchangeReqandResp.setSoNo("");
		}
		pickupExchangeReqandResp.setBranchId(pickupExchange.getBranchId());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getBranchName())) {
			pickupExchangeReqandResp.setBranchName(pickupExchange.getBranchName());
		} else {
			pickupExchangeReqandResp.setBranchName("");
		}
		pickupExchangeReqandResp.setInventoryLocationId(pickupExchange.getInventoryLocationId());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getInventoryLocationName())) {
			pickupExchangeReqandResp.setInventoryLocationName(pickupExchange.getInventoryLocationName());
		} else {
			pickupExchangeReqandResp.setInventoryLocationName("");
		}
		pickupExchangeReqandResp.setPatientId(pickupExchange.getPatientId());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientIdNo())) {
			pickupExchangeReqandResp.setPatientIdNo(pickupExchange.getPatientIdNo());
		} else {
			pickupExchangeReqandResp.setPatientIdNo("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientFirstName())) {
			pickupExchangeReqandResp.setPatientFirstName(pickupExchange.getPatientFirstName());
		} else {
			pickupExchangeReqandResp.setPatientFirstName("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientMiddleName())) {
			pickupExchangeReqandResp.setPatientMiddleName(pickupExchange.getPatientMiddleName());
		} else {
			pickupExchangeReqandResp.setPatientMiddleName("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientLastName())) {
			pickupExchangeReqandResp.setPatientLastName(pickupExchange.getPatientLastName());
		} else {
			pickupExchangeReqandResp.setPatientLastName("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientContact1())) {
			pickupExchangeReqandResp.setPatientContact1(pickupExchange.getPatientContact1());
		} else {
			pickupExchangeReqandResp.setPatientContact1("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientContact2())) {
			pickupExchangeReqandResp.setPatientContact2(pickupExchange.getPatientContact2());
		} else {
			pickupExchangeReqandResp.setPatientContact2("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientBillingAddressLine1())) {
			pickupExchangeReqandResp.setPatientBillingAddressLine1(pickupExchange.getPatientBillingAddressLine1());
		} else {
			pickupExchangeReqandResp.setPatientBillingAddressLine1("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientBillingAddressLine2())) {
			pickupExchangeReqandResp.setPatientBillingAddressLine2(pickupExchange.getPatientBillingAddressLine2());
		} else {
			pickupExchangeReqandResp.setPatientBillingAddressLine2("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientBillingAddressState())) {
			pickupExchangeReqandResp.setPatientBillingAddressState(pickupExchange.getPatientBillingAddressState());
		} else {
			pickupExchangeReqandResp.setPatientBillingAddressState("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientBillingAddressCity())) {
			pickupExchangeReqandResp.setPatientBillingAddressCity(pickupExchange.getPatientBillingAddressCity());
		} else {
			pickupExchangeReqandResp.setPatientBillingAddressCity("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientBillingAddressZip())) {
			pickupExchangeReqandResp.setPatientBillingAddressZip(pickupExchange.getPatientBillingAddressZip());
		} else {
			pickupExchangeReqandResp.setPatientBillingAddressZip("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientDeliveyAddressLine1())) {
			pickupExchangeReqandResp.setPatientDeliveyAddressLine1(pickupExchange.getPatientDeliveyAddressLine1());
		} else {
			pickupExchangeReqandResp.setPatientDeliveyAddressLine1("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientDeliveyAddressLine2())) {
			pickupExchangeReqandResp.setPatientDeliveyAddressLine2(pickupExchange.getPatientDeliveyAddressLine2());
		} else {
			pickupExchangeReqandResp.setPatientDeliveyAddressLine2("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientDeliveyAddressState())) {
			pickupExchangeReqandResp.setPatientDeliveyAddressState(pickupExchange.getPatientDeliveyAddressState());
		} else {
			pickupExchangeReqandResp.setPatientDeliveyAddressState("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientDeliveyAddressCity())) {
			pickupExchangeReqandResp.setPatientDeliveyAddressCity(pickupExchange.getPatientDeliveyAddressCity());
		} else {
			pickupExchangeReqandResp.setPatientDeliveyAddressCity("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientDeliveyAddressZip())) {
			pickupExchangeReqandResp.setPatientDeliveyAddressZip(pickupExchange.getPatientDeliveyAddressZip());
		} else {
			pickupExchangeReqandResp.setPatientDeliveyAddressZip("");
		}
		pickupExchangeReqandResp.setPickupExchangeScheduleDateTime(pickupExchange.getPickupExchangeScheduleDateTime());
		pickupExchangeReqandResp.setPickupExchangeActualDateTime(LocalDate.now());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeReason())) {
			pickupExchangeReqandResp.setPickupExchangeReason(pickupExchange.getPickupExchangeReason());
		} else {
			pickupExchangeReqandResp.setPickupExchangeReason("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeRequest())) {
			pickupExchangeReqandResp.setPickupExchangeRequest(pickupExchange.getPickupExchangeRequest());
		} else {
			pickupExchangeReqandResp.setPickupExchangeRequest("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeNote())) {
			pickupExchangeReqandResp.setPickupExchangeNote(pickupExchange.getPickupExchangeNote());
		} else {
			pickupExchangeReqandResp.setPickupExchangeNote("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeAgentIdNo())) {
			pickupExchangeReqandResp.setPickupExchangeAgentIdNo(pickupExchange.getPickupExchangeAgentIdNo());
		} else {
			pickupExchangeReqandResp.setPickupExchangeAgentIdNo("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeAgentName())) {
			pickupExchangeReqandResp.setPickupExchangeAgentName(pickupExchange.getPickupExchangeAgentName());
		} else {
			pickupExchangeReqandResp.setPickupExchangeAgentName("");
		}
		pickupExchangeReqandResp.setPickupExchangeDocumentId(pickupExchange.getPickupExchangeNo());
		pickupExchangeReqandResp.setPickupExchangeDocumentNo(pickupExchange.getPickupExchangeNo());
		pickupExchangeReqandResp.setPickupExchangeDocumentName(pickupExchange.getPickupExchangeNo());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeStatus())) {
			pickupExchangeReqandResp.setPickupExchangeStatus(pickupExchange.getPickupExchangeStatus());
		} else {
			pickupExchangeReqandResp.setPickupExchangeStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeComments())) {
			pickupExchangeReqandResp.setPickupExchangeComments(pickupExchange.getPickupExchangeComments());
		} else {
			pickupExchangeReqandResp.setPickupExchangeComments("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getIsPatientSigned())) {
			pickupExchangeReqandResp.setIsPatientSigned(pickupExchange.getIsPatientSigned());
		} else {
			pickupExchangeReqandResp.setIsPatientSigned("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getRelationshipWithPatient())) {
			pickupExchangeReqandResp.setRelationshipWithPatient(pickupExchange.getRelationshipWithPatient());
		} else {
			pickupExchangeReqandResp.setRelationshipWithPatient("");
		}
		pickupExchangeReqandResp.setPatientSignedDateTime(LocalDate.now());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getIsAgentSigned())) {
			pickupExchangeReqandResp.setIsAgentSigned(pickupExchange.getIsAgentSigned());
		} else {
			pickupExchangeReqandResp.setIsAgentSigned("");
		}
		pickupExchangeReqandResp.setLastBillingDate(pickupExchange.getLastBillingDate());
		pickupExchangeReqandResp.setDateOfDeath(pickupExchange.getDateOfDeath());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeSupportingDocument1())) {
			pickupExchangeReqandResp
					.setPickupExchangeSupportingDocument1(pickupExchange.getPickupExchangeSupportingDocument1());
		} else {
			pickupExchangeReqandResp.setPickupExchangeSupportingDocument1("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeSupportingDocument2())) {
			pickupExchangeReqandResp
					.setPickupExchangeSupportingDocument2(pickupExchange.getPickupExchangeSupportingDocument2());
		} else {
			pickupExchangeReqandResp.setPickupExchangeSupportingDocument2("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPatientNotsignedReason())) {
			pickupExchangeReqandResp.setPatientNotsignedReason(pickupExchange.getPatientNotsignedReason());
		} else {
			pickupExchangeReqandResp.setPatientNotsignedReason("");
		}
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getStatus())) {
			pickupExchangeReqandResp.setStatus(pickupExchange.getStatus());
		} else {
			pickupExchangeReqandResp.setStatus("");
		}
		pickupExchangeReqandResp.setCreatedById(pickupExchange.getCreatedById());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getCreatedByName())) {
			pickupExchangeReqandResp.setCreatedByName(pickupExchange.getCreatedByName());
		} else {
			pickupExchangeReqandResp.setCreatedByName("");
		}
		pickupExchangeReqandResp.setCreatedDate(pickupExchange.getCreatedDate());
		pickupExchangeReqandResp.setUpdatedById(pickupExchange.getUpdatedById());
		if (CommonUtilities.isStringNullOrBlank(pickupExchange.getPickupExchangeNo())) {
			pickupExchangeReqandResp.setUpdatedByName(pickupExchange.getUpdatedByName());
		} else {
			pickupExchangeReqandResp.setUpdatedByName("");
		}
		pickupExchangeReqandResp.setUpdatedDate(pickupExchange.getUpdatedDate());
		pickupExchangeReqandResp.setPickupExchangeUuid(pickupExchange.getPickupExchangeUuid());

		for (PickupExchangeItems item : pickupExchangeItems) {
			PickupExchangeItem pickupExchangeItem = new PickupExchangeItem();

			pickupExchangeItem.setPickupExchangeItemId(item.getPickupExchangeItemId());
			pickupExchangeItem.setPickupExchangeId(item.getPickupExchangeId());
			pickupExchangeItem.setSoId(item.getSoId());
			pickupExchangeItem.setItemId(item.getItemId());
			if (CommonUtilities.isStringNullOrBlank(item.getItemNo())) {
				pickupExchangeItem.setItemNo(item.getItemNo());
			} else {
				pickupExchangeItem.setItemNo("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getItemName())) {
				pickupExchangeItem.setItemName(item.getItemName());
			} else {
				pickupExchangeItem.setItemName("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getWhetherSerialized())) {
				pickupExchangeItem.setWhetherSerialized(item.getWhetherSerialized());
			} else {
				pickupExchangeItem.setWhetherSerialized("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getPickupItemSerialNo())) {
				pickupExchangeItem.setPickupItemSerialNo(item.getPickupItemSerialNo());
			} else {
				pickupExchangeItem.setPickupItemSerialNo("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getPickupItemAssetNo())) {
				pickupExchangeItem.setPickupItemAssetNo(item.getPickupItemAssetNo());
			} else {
				pickupExchangeItem.setPickupItemAssetNo("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getReplacementItemSerialNo())) {
				pickupExchangeItem.setReplacementItemSerialNo(item.getReplacementItemSerialNo());
			} else {
				pickupExchangeItem.setReplacementItemSerialNo("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getReplacementItemAssetNo())) {
				pickupExchangeItem.setReplacementItemAssetNo(item.getReplacementItemAssetNo());
			} else {
				pickupExchangeItem.setReplacementItemAssetNo("");
			}
			pickupExchangeItem.setQuantity(item.getQuantity());
			if (CommonUtilities.isStringNullOrBlank(item.getItemPickupExchangeType())) {
				pickupExchangeItem.setItemPickupExchangeType(item.getItemPickupExchangeType());
			} else {
				pickupExchangeItem.setItemPickupExchangeType("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getItemPickupExchangeNote())) {
				pickupExchangeItem.setItemPickupExchangeNote(item.getItemPickupExchangeNote());
			} else {
				pickupExchangeItem.setItemPickupExchangeNote("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getItemPickupExchangeComment())) {
				pickupExchangeItem.setItemPickupExchangeComment(item.getItemPickupExchangeComment());
			} else {
				pickupExchangeItem.setItemPickupExchangeComment("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getItemPickupExchangeStatus())) {
				pickupExchangeItem.setItemPickupExchangeStatus(item.getItemPickupExchangeStatus());
			} else {
				pickupExchangeItem.setItemPickupExchangeStatus("");
			}
			if (CommonUtilities.isStringNullOrBlank(item.getStatus())) {
				pickupExchangeItem.setStatus(item.getStatus());
			} else {
				pickupExchangeItem.setStatus("");
			}
			pickupExchangeItem.setCreatedById(item.getCreatedById());
			if (CommonUtilities.isStringNullOrBlank(item.getCreatedByName())) {
				pickupExchangeItem.setCreatedByName(item.getCreatedByName());
			} else {
				pickupExchangeItem.setCreatedByName("");
			}
			pickupExchangeItem.setUpdatedById(item.getUpdatedById());
			if (CommonUtilities.isStringNullOrBlank(item.getUpdatedByName())) {
				pickupExchangeItem.setUpdatedByName(item.getUpdatedByName());
			} else {
				pickupExchangeItem.setUpdatedByName("");
			}
			pickupExchangeItem.setUpdatedDate(item.getUpdatedDate());
			pickupExchangeItem.setPickupExchangeItemUuid(item.getPickupExchangeItemUuid());

			listpickupExchangeItems.add(pickupExchangeItem);
		}

		pickupExchangeReqandResp.setPickupExchangeItems(listpickupExchangeItems);

		return pickupExchangeReqandResp;
	}

	@Override
	public ServiceOutcome<PickupExchangeDTO> getPickupExchangeDataperAgent(String agentIdNo) {
		// TODO Auto-generated method stub

		ServiceOutcome<PickupExchangeDTO> outCome = new ServiceOutcome<PickupExchangeDTO>();
		PickupExchange pickupExchange = pickUpExchangeRepository.getPickupExchangeDataperAgent(agentIdNo,
				LocalDate.now());

		if (pickupExchange == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(pickupExchangeMapper.toDto(pickupExchange));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	private BranchOfficeDTO getBranchOfficeData(String branchId) throws ParseException,
			org.json.simple.parser.ParseException, JsonMappingException, JsonProcessingException {

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		ResponseEntity<BranchOfficeDTO> responseData = restTemplateData.exchange(
				"http://localhost:8080/services/settings/api/getBranchOfficeForABNById?branchId={branchId}",
				HttpMethod.GET, entityData, BranchOfficeDTO.class, branchId);

		return responseData.getBody();
	}

	private ItemSerialNumberDTO getItemSerialNumberByserialNo(String serialNumber, Long itemId)
			throws org.json.simple.parser.ParseException {

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		ResponseEntity<ItemSerialNumberDTO> responseData = restTemplateData.exchange(
				"http://localhost:8080/services/items/api/getItemSerialNumberByserialNo?serialNumber={serialNumber}&itemId={itemId}",
				HttpMethod.GET, entityData, ItemSerialNumberDTO.class, serialNumber, itemId);

		return responseData.getBody();
	}

	public void updateInventoryStatusQtyandserialNo(String itemNo, String pickitemSerialNo, String exchitemSerialNo,
			Long itemId, String itemPickupExchangeType, Long inventoryLocationId, Long qty) throws Exception {

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		if (itemPickupExchangeType.equalsIgnoreCase("Pickup")) {
			ResponseEntity<ItemSerialNumberDTO> responseData = restTemplateData.exchange(
					"http://localhost:8080/services/items/api/updateInventoryStatusQtyandserialNo?itemNo={itemNo}&PickupserialNumber={pickitemSerialNo}&itemId={itemId}&itemPickupExchangeType={itemPickupExchangeType}&itemLocationId={inventoryLocationId}&qty={qty}",
					HttpMethod.POST, entityData, ItemSerialNumberDTO.class, itemNo, pickitemSerialNo, itemId,
					itemPickupExchangeType, inventoryLocationId);

		} else {
			ResponseEntity<ItemSerialNumberDTO> responseData = restTemplateData.exchange(
					"http://localhost:8080/services/items/api/updateInventoryStatusQtyandserialNo?itemNo={itemNo}&PickupserialNumber={pickitemSerialNo}&ExchangeserialNumber={exchitemSerialNo}&itemId={itemId}&itemPickupExchangeType={itemPickupExchangeType}&itemLocationId={inventoryLocationId}&qty={qty}",
					HttpMethod.POST, entityData, ItemSerialNumberDTO.class, itemNo, pickitemSerialNo, exchitemSerialNo,
					itemId, itemPickupExchangeType, inventoryLocationId, qty);

		}

	}

	public void itemTransactionDetailsDataUpdate(String pickitemSerialNo, String exchitemassetNo, String exchitemSerialNo,String itemNo, 
			Long itemId, String sono, String itemPickupExchangeType) throws Exception {

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		if (itemPickupExchangeType.equalsIgnoreCase("Pickup")) {
			ResponseEntity<ItemSerialNumberDTO> responseData = restTemplateData.exchange(
					"http://localhost:8080/services/items/api/itemTransactionDetailsDataUpdate?pickupserialNumber={pickitemSerialNo}&itemNo={itemNo}&itemId={itemId}&sono={sono}&pickupExchangeType={itemPickupExchangeType}",
					HttpMethod.POST, entityData, ItemSerialNumberDTO.class, pickitemSerialNo, itemNo, itemId,
					sono, itemPickupExchangeType);

		} else {
			ResponseEntity<ItemSerialNumberDTO> responseData = restTemplateData.exchange(
					"http://localhost:8080/services/items/api/itemTransactionDetailsDataUpdate?pickupserialNumber={pickitemSerialNo}&exchassetNumber={exchitemassetNo}&exchserialNumber={exchitemSerialNo}&itemNo={itemNo}&itemId={itemId}&sono={sono}&pickupExchangeType={itemPickupExchangeType}",
					HttpMethod.POST, entityData, ItemSerialNumberDTO.class, pickitemSerialNo, exchitemassetNo, exchitemSerialNo,
					itemNo, itemId, sono, itemPickupExchangeType);

		}

	}

    @Override
    public ServiceOutcome<PickupExchangeReqandResp> preparePickUpExchangeTicketReport(PickupExchangeReqandResp pickupExchangeReqandResp, String filePath, String QRCodeOfPOEDocFilePath, String getPOESignature) throws Exception {
        String fileName = pickupExchangeReqandResp.getPickupExchangeNo().trim()+".pdf";

        ServiceOutcome<PickupExchangeReqandResp> serviceOutcome = new ServiceOutcome<PickupExchangeReqandResp>();

        //CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

        Document document = new Document(PageSize.A4, 35, 35, 50, 50);

        String getPOEDocfilePath = null;
        String getQRCodeForPOEFilePath = null;
        if(filePath == "default"){
            //This condition will be executed when delivery-resource/preparePickUpExchangeTicketReport Api is directly called
            getPOEDocfilePath = fileUploadConfigProperties.getPickupExchangeDocumentProperties().getLocation();
            getQRCodeForPOEFilePath = fileUploadConfigProperties.getPickupExchangeTempQrCodeProperties().getLocation();
        }
        else{
            getPOEDocfilePath = filePath;
            getQRCodeForPOEFilePath = QRCodeOfPOEDocFilePath;
        }

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File( getPOEDocfilePath+ "/" + fileName)));
        boolean commonHeader = true;

        //Generate QR Code
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String qrPath = pickupExchangeReqandResp.getPickupExchangeNo().trim();
        commonPDFStubs.generateQRCode(qrPath, getQRCodeForPOEFilePath);

        //Pass responseObject to make Common Header portion Dynamic
        DeliveryPickupExchangeHeaderFooterPageEvent event = new DeliveryPickupExchangeHeaderFooterPageEvent(pickupExchangeReqandResp, getQRCodeForPOEFilePath+"/"+qrPath+".png", true);
        writer.setPageEvent(event);

        event.setHeader(pickupExchangeReqandResp.getPickupExchangeComments()); //Get and then set Comments or Special Instructions

        document.open();
        /********************Pickup Pdf Code Starts************/
        if(pickupExchangeReqandResp.getPickupExchangeType().equals("Pickup")){
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentDynamicRowTableBodyPickUpExchange(pickupExchangeReqandResp));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentDynamicRowTableTotalPickUpExchange());
            document.add(new Paragraph("\n"));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentTableBodyPickUpExchange(pickupExchangeReqandResp, fileUploadConfigProperties, getPOESignature));
            document.add(new Paragraph("\n"));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentTableBodyPickUpExchange1(pickupExchangeReqandResp));
        }

        /********************Pickup Pdf Code Ends************/

        /********************Exchange Pdf Code Starts************/
        if(pickupExchangeReqandResp.getPickupExchangeType().equals("Exchange")) {
            //document.newPage(); // Move to the next page
            document.add(new Paragraph("\n"));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentDynamicRowTableBodyExchange(pickupExchangeReqandResp));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentDynamicRowTableTotalExchange());
            document.add(new Paragraph("\n"));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentTableBodyPickUpExchange(pickupExchangeReqandResp, fileUploadConfigProperties, getPOESignature));
            document.add(new Paragraph("\n"));
            document.add(PickupExchangeDocumentTableBuilder.createDeliveryDocumentTableBodyPickUpExchange1(pickupExchangeReqandResp));
        }
        /********************Exchange Pdf Code Ends************/

        document.close();

        //return new ServiceOutcome<>();
        //return new ServiceOutcome<>(pickupExchangeReqandRespObj, true, "PDF created successfully.");
        serviceOutcome.setData(pickupExchangeReqandResp);
        serviceOutcome.setMessage("PDF created successfully.");
        serviceOutcome.setOutcome(true);

        return serviceOutcome;
    }

	@Override
	public ServiceOutcome<List<PickupExchangeDTO>> getAllPickupExchangeData() {
		// TODO Auto-generated method stub
		
		ServiceOutcome<List<PickupExchangeDTO>> outCome = new ServiceOutcome<List<PickupExchangeDTO>>();
		List<PickupExchangeDTO> listPickupExchangeDTO = pickupExchangeMapper.toDto(pickUpExchangeRepository.getAllPickupExchangeData());
		
		if(listPickupExchangeDTO==null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		}else {
			outCome.setData(listPickupExchangeDTO);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}
		
		return outCome;
	}
}
