package com.sunknowledge.dme.rcm.service.impl.abn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.domain.abn.AbnRequestandResponseData;
import com.sunknowledge.dme.rcm.domain.abn.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.domain.abn.Equipment;
import com.sunknowledge.dme.rcm.repository.abn.AbnDeliveryRepo;
import com.sunknowledge.dme.rcm.repository.abn.DeliveryAbnDataRepo;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.abn.AbnDeliveryServices;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;
import com.sunknowledge.dme.rcm.service.mapper.AbnDeliveryMapper;

import net.minidev.json.parser.ParseException;

@Service
public class AbnDeliveryServicesImpl implements AbnDeliveryServices {

	@Autowired
	private FileUploadConfigProperties fileUploadConfigProperties;

	@Autowired
	private AbnDeliveryRepo abnDeliveryRepository;

	@Autowired
	private DeliveryAbnDataRepo deliveryAbnDataRepository;

	@Autowired
	private AbnDeliveryMapper abnDeliveryMapper;

	@Override
	public ServiceOutcome<AbnDeliveryDTO> updatedeliveryagentdata(UUID abnDeliveryUuid, String scheduleDeliveryDateTime,
			Long deliveryAgentId, String deliveryAgentName) {
		// TODO Auto-generated method stub
		ServiceOutcome<AbnDeliveryDTO> outCome = new ServiceOutcome<AbnDeliveryDTO>();
		AbnDelivery abnDelivery = abnDeliveryRepository.getAbnDeliveryByUUID(abnDeliveryUuid);

		if (abnDelivery == null) {
			outCome.setData(null);
			outCome.setMessage("Data not Found");
			outCome.setOutcome(false);
		} else {

			abnDelivery.setStatus("Inactive");
			abnDeliveryRepository.save(abnDelivery);

			if (CommonUtilities.isStringNullOrBlank(scheduleDeliveryDateTime)) {
				abnDelivery
						.setScheduleDeliveryDateTime(CommonUtilities.stringtodateConverter(scheduleDeliveryDateTime));
			}

			if (!(deliveryAgentId == null)) {
				abnDelivery.setDeliveryAgentId(deliveryAgentId);
			}

			if (CommonUtilities.isStringNullOrBlank(deliveryAgentName)) {
				abnDelivery.setDeliveryAgentName(deliveryAgentName);
			}

			abnDelivery.setStatus("Active");
			abnDelivery.setUpdatedById(Long.valueOf("5501"));
			abnDelivery.setUpdatedByName("ANDROKTASIAI");
			abnDelivery.setUpdatedDate(LocalDate.now());
			abnDeliveryRepository.save(abnDelivery);

			outCome.setData(abnDeliveryMapper.toDto(abnDelivery));
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);

		}

		return outCome;
	}

	@Override
	public ServiceOutcome<AbnRequestandResponseData> getABNdataByUUID(UUID deliveryAbnDataUuid)
			throws JsonMappingException, JsonProcessingException, ParseException,
			org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
		ServiceOutcome<AbnRequestandResponseData> outCome = new ServiceOutcome<AbnRequestandResponseData>();
		AbnRequestandResponseData abnRequestandResponseData = new AbnRequestandResponseData();
		DeliveryAbnData deliveryAbnData = deliveryAbnDataRepository.getAbnDeliveryByUUID(deliveryAbnDataUuid);
		AbnDelivery abnDelivery = abnDeliveryRepository
				.getAbnDeliveryByabnDeliveryDataId(deliveryAbnData.getDeliveryAbnDataId());

		if (abnDelivery == null) {
			outCome.setData(null);
			outCome.setMessage("Data not Found");
			outCome.setOutcome(false);
		} else {

			abnRequestandResponseData = setDatainJson(abnDelivery, deliveryAbnData);

			outCome.setData(abnRequestandResponseData);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<AbnRequestandResponseData> updateABNDeliveryData(
			AbnRequestandResponseData abnRequestandResponseData)
			throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
		ServiceOutcome<AbnRequestandResponseData> outCome = new ServiceOutcome<AbnRequestandResponseData>();
		AbnRequestandResponseData objabnRequestandResponseData = new AbnRequestandResponseData();
		DeliveryAbnData objDeliveryAbnData = null;
		AbnDelivery objAbnDelivery = null;

		if (!(abnRequestandResponseData == null)) {
			objAbnDelivery = setAbnDelivery(abnRequestandResponseData);
			abnDeliveryRepository.save(objAbnDelivery);
		}

		if (!(abnRequestandResponseData == null)) {
			objDeliveryAbnData = setDeliveryAbnData(abnRequestandResponseData);
			deliveryAbnDataRepository.save(objDeliveryAbnData);
		}

		objabnRequestandResponseData = setDatainJson(objAbnDelivery, objDeliveryAbnData);

		outCome.setData(objabnRequestandResponseData);
		outCome.setMessage("Data Update Successfully");
		outCome.setOutcome(true);

		return outCome;
	}

	@Override
	public void generateABNDocument(AbnRequestandResponseData abnRequestandResponseData)
			throws IOException, DocumentException, ParseException, WriterException, com.google.zxing.WriterException,
			org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub

		CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
		AbnDelivery objAbnDelivery = setAbnDelivery(abnRequestandResponseData);
		AbnDelivery updatedocNameData = abnDeliveryRepository
				.getAbnDeliveryByabnDeliveryId(objAbnDelivery.getAbnDeliveryId());
		DeliveryAbnData objDeliveryAbnData = setDeliveryAbnData(abnRequestandResponseData);
		String signaturePath = "", qrPath = "";
		// Generating Patient Signature
		if (CommonUtilities.isStringNullOrBlank(abnRequestandResponseData.getPatientSignature())) {
			String imageOutputPath = fileUploadConfigProperties.getSignatureImageProperties().getLocation() + "/"
					+ objDeliveryAbnData.getSalesOrderId() + "_" + objDeliveryAbnData.getPatientId() + "_"
					+ objDeliveryAbnData.getPrimaryInsurancePolicyNumber() + "patn.png";
			signaturePath = imageOutputPath;
			int begin = abnRequestandResponseData.getPatientSignature().indexOf(",");
			int last = abnRequestandResponseData.getPatientSignature().length();
			String b_64 = abnRequestandResponseData.getPatientSignature().substring(begin + 1, last);
			commonPDFStubs.jsonToSignatureConverter(b_64, imageOutputPath);

		}
		// Generating Agent Signature
		if (CommonUtilities.isStringNullOrBlank(abnRequestandResponseData.getDeliveryAgentSignature())) {
			String imageOutputPath = fileUploadConfigProperties.getSignatureImageProperties().getLocation() + "/"
					+ objDeliveryAbnData.getSalesOrderId() + "_" + objDeliveryAbnData.getPatientId() + "_"
					+ objDeliveryAbnData.getPrimaryInsurancePolicyNumber() + "agnt.png";
			int begin = abnRequestandResponseData.getDeliveryAgentSignature().indexOf(",");
			int last = abnRequestandResponseData.getDeliveryAgentSignature().length();
			String b_64 = abnRequestandResponseData.getDeliveryAgentSignature().substring(begin + 1, last);
			commonPDFStubs.jsonToSignatureConverter(b_64, imageOutputPath);

		}

		// Generate QR Code
		qrPath = objDeliveryAbnData.getAbnNumber();
		commonPDFStubs.generateQRCode(qrPath, fileUploadConfigProperties.getSignatureImageProperties().getLocation());

		File source = new File(
				fileUploadConfigProperties.getAbnTemplateProperties().getLocation() + "/" + qrPath + ".pdf");
		String fileName = qrPath;
		// Updating the generated File Name
		updatedocNameData.setAbnDocumentName(fileName);
		abnDeliveryRepository.save(updatedocNameData);

		File destination = new File(
				fileUploadConfigProperties.getAbnDeliveryDocumentProperties().getLocation() + "/" + fileName + ".pdf");
		PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
		PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
		String name = "";

		if (CommonUtilities.isStringNullOrBlank(objDeliveryAbnData.getPatientMiddleName())) {
			name = objDeliveryAbnData.getPatientFirstName() + " " + objDeliveryAbnData.getPatientMiddleName() + " "
					+ objDeliveryAbnData.getPatientLastName();
		} else {
			name = objDeliveryAbnData.getPatientFirstName() + " " + objDeliveryAbnData.getPatientLastName();
		}

		// branchOfficeDTO = getBranchOfficeData(objDeliveryAbnData.getBranchId());
		stamper.getAcroFields().setField("Notifier_1", objDeliveryAbnData.getBranchName());
		String notifier2 = abnRequestandResponseData.getNotifier().substring(
				abnRequestandResponseData.getNotifier().indexOf(" "), abnRequestandResponseData.getNotifier().length());
		stamper.getAcroFields().setField("Notifier_2", notifier2);
		stamper.getAcroFields().setField("Date_of_Issuance", objAbnDelivery.getActualDeliveryDateTime().toString());
		stamper.getAcroFields().setField("Patient_Name", name);
		stamper.getAcroFields().setField("Identification_Number", objDeliveryAbnData.getPatientIdNo());

		// Items
		if (objDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode().indexOf(",") == -1) {
			stamper.getAcroFields().setField("Equipment_1", objDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode());
			stamper.getAcroFields().setField("Reason_1", objDeliveryAbnData.getSalesOrderDetailsAbnReason());
			stamper.getAcroFields().setField("Cost_1",
					objDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount().toString());
		} else {
			String[] procCode = objDeliveryAbnData.getSalesOrderDetailsAbnItemProcCode().split(",");
			String[] chargeAmt = objDeliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount().split(",");
			for (int i = 0; i < procCode.length; i++) {
				stamper.getAcroFields().setField("Equipment_" + (i + 1), procCode[i]);
				stamper.getAcroFields().setField("Reason_" + (i + 1),
						objDeliveryAbnData.getSalesOrderDetailsAbnReason());
				stamper.getAcroFields().setField("Cost_" + (i + 1), chargeAmt[i]);
			}
		}
		if (objDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse().equalsIgnoreCase("1")) {
			stamper.getAcroFields().setField("Radio_Button_1", "Yes");
		} else if (objDeliveryAbnData.getSalesOrderDetailsPatientAbnResponse().equalsIgnoreCase("2")) {
			stamper.getAcroFields().setField("Radio_Button_2", "Yes");
		} else {
			stamper.getAcroFields().setField("Radio_Button_3", "Yes");
		}
		stamper.getAcroFields().setField("J_Date", objDeliveryAbnData.getSalesOrderDetailsAbnPrintDate().toString());
		stamper.close();
		addSignatureToDocument(destination, signaturePath, qrPath);
	}

	public void addSignatureToDocument(File source, String signaturePath, String qrPath) throws IOException {
		PDDocument document = PDDocument.load(source);
		PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
		PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0),
				PDPageContentStream.AppendMode.APPEND, true, true);

		PDSignatureField pdSignatureField = (PDSignatureField) acroForm.getField("Signature");
		PDSignatureField pdQrField1 = (PDSignatureField) acroForm.getField("qr");

		PDImageXObject image1 = PDImageXObject.createFromFile(signaturePath, document);
		PDImageXObject image2 = PDImageXObject.createFromFile(
				fileUploadConfigProperties.getSignatureImageProperties().getLocation() + "/" + qrPath + ".PNG",
				document);

		float x = pdSignatureField.getWidget().getRectangle().getLowerLeftX();
		float y = pdSignatureField.getWidget().getRectangle().getLowerLeftY();
		float width = pdSignatureField.getWidget().getRectangle().getWidth();
		float height = pdSignatureField.getWidget().getRectangle().getHeight();

		contentStream.drawImage(image1, x, y, width, height);

		float x1 = pdQrField1.getWidget().getRectangle().getLowerLeftX();
		float y1 = pdQrField1.getWidget().getRectangle().getLowerLeftY();
		float width1 = pdQrField1.getWidget().getRectangle().getWidth();
		float height1 = pdQrField1.getWidget().getRectangle().getHeight();

		contentStream.drawImage(image2, x1, y1, width1, height1);

		contentStream.close();

		// Flatten the AcroForm
		acroForm.flatten();

		document.save(source);
		document.close();
	}

	public BranchOfficeDTO getBranchOfficeData(String branchId) throws ParseException,
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

	AbnDelivery setAbnDelivery(AbnRequestandResponseData abnRequestandResponseData) {
		AbnDelivery objAbnDelivery = new AbnDelivery();

		objAbnDelivery.setAbnDeliveryId(abnRequestandResponseData.getAbnDeliveryId());
		objAbnDelivery.setAbnDeliveryDataId(abnRequestandResponseData.getAbnDeliveryDataId());
		objAbnDelivery.setAbnDocumentName(abnRequestandResponseData.getAbnDocumentName());
		objAbnDelivery.setScheduleDeliveryDateTime(abnRequestandResponseData.getScheduleDeliveryDateTime());
		objAbnDelivery.setActualDeliveryDateTime(abnRequestandResponseData.getActualDeliveryDateTime());
		objAbnDelivery.setDeliveryAgentId(abnRequestandResponseData.getDeliveryAgentId());
		objAbnDelivery.setDeliveryAgentName(abnRequestandResponseData.getDeliveryAgentName());
		objAbnDelivery.setAbnDeliveryStatus(abnRequestandResponseData.getAbnDeliveryStatus());
		objAbnDelivery.setAbnDeliveryDocumentAckStatus(abnRequestandResponseData.getAbnDeliveryDocumentAckStatus());
		objAbnDelivery
				.setAbnDeliveryDocPatientReplyStatus(abnRequestandResponseData.getAbnDeliveryDocPatientReplyStatus());
		objAbnDelivery.setAbnDeliveryDocPatientReplyDateTime(
				abnRequestandResponseData.getAbnDeliveryDocPatientReplyDateTime());
		objAbnDelivery.setAbnResponseJsonData(abnRequestandResponseData.toString());
		objAbnDelivery.setStatus(abnRequestandResponseData.getStatus());
		objAbnDelivery.setCreatedById(abnRequestandResponseData.getCreatedById());
		objAbnDelivery.setCreatedByName(abnRequestandResponseData.getCreatedByName());
		objAbnDelivery.setCreatedDate(abnRequestandResponseData.getCreatedDate());
		objAbnDelivery.setUpdatedById(Long.valueOf("5501"));
		objAbnDelivery.setUpdatedByName("ANDROKTASIAI");
		objAbnDelivery.setUpdatedDate(LocalDate.now());
		objAbnDelivery.setAbnDeliveryUuid(abnRequestandResponseData.getAbnDeliveryUuid());

		return objAbnDelivery;
	}

	DeliveryAbnData setDeliveryAbnData(AbnRequestandResponseData abnRequestandResponseData) {
		DeliveryAbnData objDeliveryAbnData = new DeliveryAbnData();
		objDeliveryAbnData.setDeliveryAbnDataId(abnRequestandResponseData.getDeliveryAbnDataId());
		objDeliveryAbnData.setSalesOrderId(abnRequestandResponseData.getSalesOrderId());
		objDeliveryAbnData.setPatientId(abnRequestandResponseData.getPatientId());
		objDeliveryAbnData.setPrimaryInsuranceName(abnRequestandResponseData.getPrimaryInsuranceName());
		objDeliveryAbnData.setPrimaryInsurancePolicyNumber(abnRequestandResponseData.getPrimaryInsurancePolicyNumber());
		objDeliveryAbnData.setPatientFirstName(abnRequestandResponseData.getPatientFirstName());
		objDeliveryAbnData.setPatientMiddleName(abnRequestandResponseData.getPatientMiddleName());
		objDeliveryAbnData.setPatientLastName(abnRequestandResponseData.getPatientLastName());
		objDeliveryAbnData
				.setSalesOrderDetailsAbnPrintDate(abnRequestandResponseData.getSalesOrderDetailsAbnPrintDate());
		objDeliveryAbnData.setSalesOrderDetailsAbnItemName(abnRequestandResponseData.getSalesOrderDetailsAbnItemName());
		objDeliveryAbnData.setSalesOrderDetailsAbnItemProcCode(abnRequestandResponseData.getProcCode());
		objDeliveryAbnData.setSalesOrderDetailsAbnItemChargeAmount(abnRequestandResponseData.getChargeAmount());
		objDeliveryAbnData.setSalesOrderDetailsPatientAbnResponse(
				abnRequestandResponseData.getSalesOrderDetailsPatientAbnResponse());
		objDeliveryAbnData.setSalesOrderDetailsAbnDeliveryStatus(
				abnRequestandResponseData.getSalesOrderDetailsAbnDeliveryStatus());
		objDeliveryAbnData.setSalesOrderDetailsAbnPatientSignatureStatus(
				abnRequestandResponseData.getSalesOrderDetailsAbnPatientSignatureStatus());
		objDeliveryAbnData.setSalesOrderDetailsAbnStatus(abnRequestandResponseData.getSalesOrderDetailsAbnStatus());
		objDeliveryAbnData.setSalesOrderDetailsAbnReason(abnRequestandResponseData.getSalesOrderDetailsAbnReason());
		objDeliveryAbnData
				.setSalesOrderDetailsAbnModifier1(abnRequestandResponseData.getSalesOrderDetailsAbnModifier1());
		objDeliveryAbnData
				.setSalesOrderDetailsAbnModifier2(abnRequestandResponseData.getSalesOrderDetailsAbnModifier2());
		objDeliveryAbnData.setBranchId(abnRequestandResponseData.getBranchId());
		objDeliveryAbnData.setBranchName(abnRequestandResponseData.getBranchName());
		objDeliveryAbnData.setPatientIdNo(abnRequestandResponseData.getPatientIdNo());
		objDeliveryAbnData.setStatus(abnRequestandResponseData.getStatus());
		objDeliveryAbnData.setCreatedById(abnRequestandResponseData.getCreatedById());
		objDeliveryAbnData.setCreatedByName(abnRequestandResponseData.getCreatedByName());
		objDeliveryAbnData.setCreatedDate(abnRequestandResponseData.getCreatedDate());
		objDeliveryAbnData.setUpdatedById(Long.valueOf("5501"));
		objDeliveryAbnData.setUpdatedByName("ANDROKTASIAI");
		objDeliveryAbnData.setUpdatedDate(LocalDate.now());
		objDeliveryAbnData.setDeliveryAbnDataUuid(abnRequestandResponseData.getDeliveryAbnDataUuid());

		return objDeliveryAbnData;

	}

	AbnRequestandResponseData setDatainJson(AbnDelivery abnDelivery, DeliveryAbnData deliveryAbnData)
			throws JsonMappingException, JsonProcessingException, ParseException,
			org.json.simple.parser.ParseException {

		AbnRequestandResponseData abnRequestandResponseData = new AbnRequestandResponseData();
		List<Equipment> listequipment = new ArrayList<Equipment>();
		BranchOfficeDTO branchOfficeDTO = new BranchOfficeDTO();
		branchOfficeDTO = getBranchOfficeData(deliveryAbnData.getBranchId());

		if (!(abnDelivery.getAbnDeliveryId() == null)) {
			abnRequestandResponseData.setAbnDeliveryId(abnDelivery.getAbnDeliveryId());
		} else {
			abnRequestandResponseData.setAbnDeliveryId(Long.valueOf(""));
		}
		if (!(abnDelivery.getAbnDeliveryDataId() == null)) {
			abnRequestandResponseData.setAbnDeliveryDataId(abnDelivery.getAbnDeliveryDataId());
		} else {
			abnRequestandResponseData.setAbnDeliveryDataId(null);
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getAbnDocumentName())) {
			abnRequestandResponseData.setAbnDocumentName(abnDelivery.getAbnDocumentName());
		} else {
			abnRequestandResponseData.setAbnDocumentName("");
		}
		if (!(abnDelivery.getScheduleDeliveryDateTime() == null)) {
			abnRequestandResponseData.setScheduleDeliveryDateTime(abnDelivery.getScheduleDeliveryDateTime());
		} else {
			abnRequestandResponseData.setScheduleDeliveryDateTime(null);
		}
		if (!(abnDelivery.getActualDeliveryDateTime() == null)) {
			abnRequestandResponseData.setActualDeliveryDateTime(abnDelivery.getActualDeliveryDateTime());
		} else {
			abnRequestandResponseData.setActualDeliveryDateTime(null);
		}
		if (!(abnDelivery.getDeliveryAgentId() == null)) {
			abnRequestandResponseData.setDeliveryAgentId(abnDelivery.getDeliveryAgentId());
		} else {
			abnRequestandResponseData.setDeliveryAgentId(null);
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getDeliveryAgentName())) {
			abnRequestandResponseData.setDeliveryAgentName(abnDelivery.getDeliveryAgentName());
		} else {
			abnRequestandResponseData.setDeliveryAgentName("");
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getAbnDeliveryStatus())) {
			abnRequestandResponseData.setAbnDeliveryStatus(abnDelivery.getAbnDeliveryStatus());
		} else {
			abnRequestandResponseData.setAbnDeliveryStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getAbnDeliveryDocumentAckStatus())) {
			abnRequestandResponseData.setAbnDeliveryDocumentAckStatus(abnDelivery.getAbnDeliveryDocumentAckStatus());
		} else {
			abnRequestandResponseData.setAbnDeliveryDocumentAckStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getAbnDeliveryDocPatientReplyStatus())) {
			abnRequestandResponseData
					.setAbnDeliveryDocPatientReplyStatus(abnDelivery.getAbnDeliveryDocPatientReplyStatus());
		} else {
			abnRequestandResponseData.setAbnDeliveryDocPatientReplyStatus("");
		}
		if (!(abnDelivery.getAbnDeliveryDocPatientReplyDateTime() == null)) {
			abnRequestandResponseData
					.setAbnDeliveryDocPatientReplyDateTime(abnDelivery.getAbnDeliveryDocPatientReplyDateTime());
		} else {
			abnRequestandResponseData.setAbnDeliveryDocPatientReplyDateTime(null);
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getStatus())) {
			abnRequestandResponseData.setStatus(abnDelivery.getStatus());
		} else {
			abnRequestandResponseData.setStatus(null);
		}
		if (!(abnDelivery.getCreatedById() == null)) {
			abnRequestandResponseData.setCreatedById(abnDelivery.getCreatedById());
		} else {
			abnRequestandResponseData.setCreatedById(null);
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getCreatedByName())) {
			abnRequestandResponseData.setCreatedByName(abnDelivery.getCreatedByName());
		} else {
			abnRequestandResponseData.setCreatedByName("");
		}
		if (!(abnDelivery.getCreatedDate() == null)) {
			abnRequestandResponseData.setCreatedDate(abnDelivery.getCreatedDate());
		} else {
			abnRequestandResponseData.setCreatedDate(null);
		}
		if (!(abnDelivery.getUpdatedById() == null)) {
			abnRequestandResponseData.setUpdatedById(abnDelivery.getUpdatedById());
		} else {
			abnRequestandResponseData.setUpdatedById(null);
		}
		if (CommonUtilities.isStringNullOrBlank(abnDelivery.getUpdatedByName())) {
			abnRequestandResponseData.setUpdatedByName(abnDelivery.getUpdatedByName());
		} else {
			abnRequestandResponseData.setUpdatedByName("");
		}
		if (!(abnDelivery.getUpdatedDate() == null)) {
			abnRequestandResponseData.setUpdatedDate(abnDelivery.getUpdatedDate());
		} else {
			abnRequestandResponseData.setUpdatedDate(null);
		}
		if (!(abnDelivery.getAbnDeliveryUuid() == null)) {
			abnRequestandResponseData.setAbnDeliveryUuid(abnDelivery.getAbnDeliveryUuid());
		} else {
			abnRequestandResponseData.setAbnDeliveryUuid(null);
		}
		if (!(deliveryAbnData.getDeliveryAbnDataId() == null)) {
			abnRequestandResponseData.setDeliveryAbnDataId(deliveryAbnData.getDeliveryAbnDataId());
		} else {
			abnRequestandResponseData.setDeliveryAbnDataId(null);
		}
		if (!(deliveryAbnData.getSalesOrderId() == null)) {
			abnRequestandResponseData.setSalesOrderId(deliveryAbnData.getSalesOrderId());
		} else {
			abnRequestandResponseData.setSalesOrderId(null);
		}
		if (!(deliveryAbnData.getPatientId() == null)) {
			abnRequestandResponseData.setPatientId(deliveryAbnData.getPatientId());
		} else {
			abnRequestandResponseData.setPatientId(null);
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPrimaryInsuranceName())) {
			abnRequestandResponseData.setPrimaryInsuranceName(deliveryAbnData.getPrimaryInsuranceName());
		} else {
			abnRequestandResponseData.setPrimaryInsuranceName("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPrimaryInsurancePolicyNumber())) {
			abnRequestandResponseData
					.setPrimaryInsurancePolicyNumber(deliveryAbnData.getPrimaryInsurancePolicyNumber());
		} else {
			abnRequestandResponseData.setPrimaryInsurancePolicyNumber("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPatientFirstName())) {
			abnRequestandResponseData.setPatientFirstName(deliveryAbnData.getPatientFirstName());
		} else {
			abnRequestandResponseData.setPatientFirstName("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPatientMiddleName())) {
			abnRequestandResponseData.setPatientMiddleName(deliveryAbnData.getPatientMiddleName());
		} else {
			abnRequestandResponseData.setPatientMiddleName("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPatientLastName())) {
			abnRequestandResponseData.setPatientLastName(deliveryAbnData.getPatientLastName());
		} else {
			abnRequestandResponseData.setPatientLastName("");
		}
		if (!(deliveryAbnData.getSalesOrderDetailsAbnPrintDate() == null)) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnPrintDate(deliveryAbnData.getSalesOrderDetailsAbnPrintDate());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnPrintDate(null);
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnItemName())) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnItemName(deliveryAbnData.getSalesOrderDetailsAbnItemName());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnItemName("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnItemProcCode())) {
			abnRequestandResponseData
					.setProcCode(deliveryAbnData.getSalesOrderDetailsAbnItemProcCode());
		} else {
			abnRequestandResponseData.setProcCode("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount())) {
			abnRequestandResponseData
					.setChargeAmount(deliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount());
		} else {
			abnRequestandResponseData.setChargeAmount("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnReason())) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnReason(deliveryAbnData.getSalesOrderDetailsAbnReason());
		} else {
			abnRequestandResponseData.setChargeAmount("");
		}
		// Items
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnItemProcCode())) {
			String[] procCode = deliveryAbnData.getSalesOrderDetailsAbnItemProcCode().split(",");
			String[] chargeAmt = deliveryAbnData.getSalesOrderDetailsAbnItemChargeAmount().split(",");
			for (int i = 0; i < procCode.length; i++) {
				Equipment Equipment = new Equipment();

				Equipment.setProcCode(procCode[i]);
				Equipment.setChargeAmount(chargeAmt[i]);
				Equipment.setReason(deliveryAbnData.getSalesOrderDetailsAbnReason());

				listequipment.add(Equipment);
			}
		}
		abnRequestandResponseData.setEquipment(listequipment);
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsPatientAbnResponse())) {
			abnRequestandResponseData
					.setSalesOrderDetailsPatientAbnResponse(deliveryAbnData.getSalesOrderDetailsPatientAbnResponse());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsPatientAbnResponse("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus())) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnDeliveryStatus(deliveryAbnData.getSalesOrderDetailsAbnDeliveryStatus());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnDeliveryStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus())) {
			abnRequestandResponseData.setSalesOrderDetailsAbnPatientSignatureStatus(
					deliveryAbnData.getSalesOrderDetailsAbnPatientSignatureStatus());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnPatientSignatureStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnStatus())) {
			abnRequestandResponseData.setSalesOrderDetailsAbnStatus(deliveryAbnData.getSalesOrderDetailsAbnStatus());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnStatus("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnModifier1())) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnModifier1(deliveryAbnData.getSalesOrderDetailsAbnModifier1());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnModifier1("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getSalesOrderDetailsAbnModifier2())) {
			abnRequestandResponseData
					.setSalesOrderDetailsAbnModifier2(deliveryAbnData.getSalesOrderDetailsAbnModifier2());
		} else {
			abnRequestandResponseData.setSalesOrderDetailsAbnModifier2("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getBranchName())) {
			abnRequestandResponseData.setBranchName(deliveryAbnData.getBranchName());
		} else {
			abnRequestandResponseData.setBranchName("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getBranchId())) {
			abnRequestandResponseData.setBranchId(deliveryAbnData.getBranchId());
		} else {
			abnRequestandResponseData.setBranchId("");
		}
		if (CommonUtilities.isStringNullOrBlank(deliveryAbnData.getPatientIdNo())) {
			abnRequestandResponseData.setPatientIdNo(deliveryAbnData.getPatientIdNo());
		} else {
			abnRequestandResponseData.setPatientIdNo("");
		}
		if (deliveryAbnData.getDeliveryAbnDataUuid() == null) {
			abnRequestandResponseData.setDeliveryAbnDataUuid(deliveryAbnData.getDeliveryAbnDataUuid());
		} else {
			abnRequestandResponseData.setDeliveryAbnDataUuid(null);
		}
		abnRequestandResponseData.setCurrentDate(LocalDate.now().toString());
		abnRequestandResponseData.setNotifier(branchOfficeDTO.getBranchName() + " "
				+ branchOfficeDTO.getBillingAddressLine1() + " " + branchOfficeDTO.getBillingAddressLine2() + " "
				+ branchOfficeDTO.getBillingCity() + " " + branchOfficeDTO.getBillingState());
		abnRequestandResponseData.setSignatureBy("");
		abnRequestandResponseData.setRelationship("");
		abnRequestandResponseData.setReason("");
		abnRequestandResponseData.setPatientSignature("");
		abnRequestandResponseData.setDeliveryAgentSignature("");

		return abnRequestandResponseData;
	}

}
