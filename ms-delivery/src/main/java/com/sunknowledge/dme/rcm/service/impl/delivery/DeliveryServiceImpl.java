package com.sunknowledge.dme.rcm.service.impl.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.exceptions.FileStorageException;
import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import com.sunknowledge.dme.rcm.application.properties.FileConfigUtility;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.application.properties.FileUploadProperties;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.commonutil.CommonPdfTableStubs;
import com.sunknowledge.dme.rcm.commonutil.DateUtilities;
import com.sunknowledge.dme.rcm.documentutil.*;
import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.pojo.PatientHomeAssessmentFormInputs;
import com.sunknowledge.dme.rcm.pojo.deliveryreceipt.BillToAddress;
import com.sunknowledge.dme.rcm.pojo.deliveryreceipt.DeliveredToAddress;
import com.sunknowledge.dme.rcm.pojo.deliveryreceipt.DeliveryReceiptFormInput;
import com.sunknowledge.dme.rcm.pojo.deliveryreceipt.ItemOrderedDetailed;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DMECertificationFormInput;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DeliveryItemDetails;
import com.sunknowledge.dme.rcm.pojo.patientassessment.AlternateContacts;
import com.sunknowledge.dme.rcm.pojo.patientassessment.PatientAssessmentFormInput;
import com.sunknowledge.dme.rcm.pojo.patienthomeassement.PatientHomeAssessmentFormInput;
import com.sunknowledge.dme.rcm.repository.delivery.*;
import com.sunknowledge.dme.rcm.service.delivery.DeliveryService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.dto.DeliveryDocumentsDTO;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.*;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.DeliveryItemData;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.ItemInventoryStatusInputParams;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.ItemInventoryStatusInputRequest;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.ItemInventoryStatusResponse;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryAssignmentMapper;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryDocumentsMapper;
import com.sunknowledge.dme.rcm.service.mapper.DeliveryTicketMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CaseUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class DeliveryServiceImpl implements DeliveryService {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.getDefault());
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;

    @Autowired
    private FileConfigUtility fileConfigUtility;

    @Autowired
    private DeliveryTicketRepo deliveryTicketRepository;

    @Autowired
    private DeliveryItemsRepo deliveryItemsRepository;

    @Autowired
    private HcpcsDocTypeRepo hcpcsDocTypeRepository;

    @Autowired
    private DeliveryDocumentsRepo deliveryDocumentsRepository;

    @Autowired
    private DeliveryDocumentsSignatureRepo deliveryDocumentsSignatureRepository;

    @Autowired
    private DeliveryAssignmentRepo deliveryAssignmentRepository;

    @Autowired
    private DeliveryTicketMapper deliveryTicketMapper;

    @Autowired
    private DeliveryDocumentsMapper deliveryDocumentsMapper;

    @Autowired
    private DeliveryAssignmentMapper deliveryAssignmentMapper;

    @Autowired
    private DeliveryPatientCommunicationsRepo deliveryPatientCommunicationsRepository;

    public ServiceOutcome<DeliveryDocumentResponse> fillDMECertificationDeliveryDocuments(DeliveryTicket deliveryTicket, DeliveryDocuments deliveryDocuments, File source, File destination, CommonDeliveryInputData commonDeliveryInputData){
        Boolean status = false;
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = null;
        try {
            PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
            System.out.println("=====>JSON=====>"+deliveryDocuments.getResponseJsonData());
            ObjectMapper mapper = new ObjectMapper();
            DMECertificationFormInput dmeCertificationFormInput = mapper.readValue(deliveryDocuments.getResponseJsonData(), DMECertificationFormInput.class);
            stamper.getAcroFields().setField("companyName", dmeCertificationFormInput.getCompanyName());
            stamper.getAcroFields().setField("name", CaseUtils.toCamelCase(dmeCertificationFormInput.getName(), true, ' '));
            stamper.getAcroFields().setField("patientId", dmeCertificationFormInput.getPatientId());
            stamper.getAcroFields().setField("streetAddress", dmeCertificationFormInput.getStreetAddress());
            stamper.getAcroFields().setField("city", CaseUtils.toCamelCase(dmeCertificationFormInput.getCity(), true, ' '));
            stamper.getAcroFields().setField("telephoneNo", dmeCertificationFormInput.getTelephoneNo());

            stamper.getAcroFields().setField("ProviderName", dmeCertificationFormInput.getProviderName().toUpperCase());
            stamper.getAcroFields().setField("pan", "");
            stamper.getAcroFields().setField("NPIAPI", dmeCertificationFormInput.getNPIAPI());
            stamper.getAcroFields().setField("tpi", "");

            stamper.getAcroFields().setField("monthyear", formatter.format(new Date()));
            stamper.getAcroFields().setField("monthyear_1", formatter.format(new Date()));

            stamper.getAcroFields().setField("patientSignature", "");
            stamper.getAcroFields().setField("patientRepresentativeName", dmeCertificationFormInput.getPatientRepresentativeName());
            stamper.getAcroFields().setField("patientRelationship", dmeCertificationFormInput.getPatientRelationship());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", formatter.format(new Date()));

            stamper.getAcroFields().setField("signatureCompRepresentative", "");
            stamper.getAcroFields().setField("companyRepresentativeName", dmeCertificationFormInput.getNameCompRepresentative());


            stamper.getAcroFields().setField("dateOfService", "");
            var count = new Object(){int ctx = 1;};
            dmeCertificationFormInput.getDeliveryItemDetailsList().stream().forEach(item -> {
                try {
                    stamper.getAcroFields().setField("proccode"+count.ctx, item.getProccode());
                    stamper.getAcroFields().setField("description"+count.ctx, item.getDescription());
                    stamper.getAcroFields().setField("serial"+count.ctx, item.getSerial());
                    count.ctx++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }
            });
            stamper.close();
            pdfReader.close();
            status = true;
            serviceOutcome = addSignaturesToDeliveryDocuments(deliveryTicket, destination, 0, commonDeliveryInputData);
            serviceOutcome.setOutcome(status);
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    public Boolean fillPatientRightsResponsibilityDeliveryDocuments(DeliveryTicket deliveryTicket, DeliveryAssignment deliveryAssignment, File source, File destination){
        Boolean status = false;
        try {
            PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());
            stamper.getAcroFields().setField("signaturePatientRepresentative", "");
            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", new Date().toString());

            stamper.getAcroFields().setField("companyRepresentativeSign", "");
            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());

            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    public Boolean fillHIPAANoticeDeliveryDocuments(DeliveryTicket deliveryTicket, DeliveryAssignment deliveryAssignment, File source, File destination){
        Boolean status = false;
        try {
            PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());
            stamper.getAcroFields().setField("namePatientRepresentative", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonToNotSign", "");
            stamper.getAcroFields().setField("date", new Date().toString());
            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());

            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    public Boolean fillHomeAssessmentDeliveryDocuments(DeliveryTicket deliveryTicket, DeliveryAssignment deliveryAssignment, File source, File destination){
        Boolean status = false;
        try {
            PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());
            stamper.getAcroFields().setField("streetAddress", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phoneNo", deliveryTicket.getPhone1());
            stamper.getAcroFields().setField("fax", deliveryTicket.getBranchFax());

            stamper.getAcroFields().setField("patientName", CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' '));
            stamper.getAcroFields().setField("patientId", deliveryTicket.getPatientIdNo());
            stamper.getAcroFields().setField("address", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("account", "");
            stamper.getAcroFields().setField("p_cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phone", deliveryTicket.getPhone1());

            stamper.getAcroFields().setField("equipment-trials", "");

            stamper.getAcroFields().setField("dateOfHomeAssessment", formatter.format(new Date()));

            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", new Date().toString());

            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());

            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    public Boolean fillPatientAssessmentDeliveryDocuments(DeliveryTicket deliveryTicket, DeliveryAssignment deliveryAssignment, File source, File destination){
        Boolean status = false;
        try {
            PdfReader pdfReader = new PdfReader(source.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination.getAbsolutePath()));
            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());

            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", new Date().toString());

            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());
            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    public DeliveryDocumentsDTO oprationOnDeliveryDocuments(DeliveryTicket deliveryTicket, HcpcsDocType hcpcs, String formJsonData){
        DeliveryDocumentsDTO deliveryDocumentsDTO = new DeliveryDocumentsDTO();
        DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.getDeliveryTicketId(), hcpcs.getHcpcsDoctypeId());
        if(deliveryDocuments != null){
            deliveryDocuments.setStatus("Initiated");
            deliveryDocuments.setUpdatedById(1L);
            deliveryDocuments.setUpdatedByName("Debabrata");
            deliveryDocuments.setUpdatedDate(LocalDate.now());
            deliveryDocuments.setResponseJsonData(formJsonData);
            deliveryDocuments = deliveryDocumentsRepository.save(deliveryDocuments);
            BeanUtils.copyProperties(deliveryDocuments, deliveryDocumentsDTO);
        }
        else{
            DeliveryDocuments saveDeliveryDocuments = new DeliveryDocuments();
            saveDeliveryDocuments.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
            saveDeliveryDocuments.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
            saveDeliveryDocuments.setSoId(deliveryTicket.getSoId());
            saveDeliveryDocuments.setSoNo(deliveryTicket.getSoNo());
            saveDeliveryDocuments.setHcpcsDoctypeId(hcpcs.getHcpcsDoctypeId());
            saveDeliveryDocuments.docPatientName(deliveryTicket.getPatientFirstName()+" "+deliveryTicket.getPatientLastName());
            saveDeliveryDocuments.setStatus("Initiated");
            saveDeliveryDocuments.setCreatedById(1L);
            saveDeliveryDocuments.setCreatedByName(deliveryTicket.getCreatedByName());
            saveDeliveryDocuments.setCreatedDate(LocalDate.now());
            saveDeliveryDocuments.setResponseJsonData(formJsonData);
            saveDeliveryDocuments.setDeliveryDocumentsUuid(UUID.randomUUID());
            saveDeliveryDocuments = deliveryDocumentsRepository.save(saveDeliveryDocuments);
            BeanUtils.copyProperties(saveDeliveryDocuments, deliveryDocumentsDTO);
        }
        return deliveryDocumentsDTO;
    }

    public DeliveryDocumentResponse operationOnDeliveryDocumentResponse(DeliveryDocumentsDTO deliveryDocumentsDTO, File destination){
        DeliveryDocumentResponse documentResponse = new DeliveryDocumentResponse();
        documentResponse.setDeliveryDocId(deliveryDocumentsDTO.getDeliveryDocId());
        documentResponse.setDeliveryTicketId(deliveryDocumentsDTO.getDeliveryTicketId());
        documentResponse.setDeliveryTicketNo(deliveryDocumentsDTO.getDeliveryTicketNo());
        documentResponse.setSoId(deliveryDocumentsDTO.getSoId());
        documentResponse.setSoNo(deliveryDocumentsDTO.getSoNo());
        documentResponse.setHcpcsDoctypeId(deliveryDocumentsDTO.getHcpcsDoctypeId());
        documentResponse.setDocPatientName(deliveryDocumentsDTO.getDocPatientName());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadSignatureFiles/")
            .path(destination.getName())
            .toUriString();

        documentResponse.setDocumentPatientFilePath(fileDownloadUri);
        documentResponse.setDocumentPatientFile(deliveryDocumentsDTO.getDocumentPatientFilePath());
        return documentResponse;
    }

    @Override
    @Transactional
    public ServiceOutcome<DeliveryOutputResponse> uploadSignatureFiles(UploadSignatureJsonReqAndRep uploadSignatureJsonReqAndRep){
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(uploadSignatureJsonReqAndRep.getDeliveryTicketId());
        if(deliveryTicket.isPresent() && deliveryTicket.get().getDeliveryStatus().equalsIgnoreCase("Delivered")) {
            return new ServiceOutcome<>(null, false, "Already been delivered!!!");
        }
        DeliveryOutputResponse deliveryOutputResponse = new DeliveryOutputResponse();
        List<DeliveryDocumentResponse> deliveryDocumentResponses = new ArrayList<>();
        DeliveryDocumentsSignature documentsSignature;
        AtomicReference<String> message = new AtomicReference<>("");
        AtomicReference<Boolean> status = new AtomicReference<>(false);
        try {
            CommonDeliveryInputData commonDeliveryInputData = new CommonDeliveryInputData();
            commonDeliveryInputData.setDeliveryTicketId(uploadSignatureJsonReqAndRep.getDeliveryTicketId());
            commonDeliveryInputData.setDate(uploadSignatureJsonReqAndRep.getDate());
            commonDeliveryInputData.setSignatureType(uploadSignatureJsonReqAndRep.getSignatureType());
            commonDeliveryInputData.setCompanyRepresentativeName(uploadSignatureJsonReqAndRep.getCompanyRepresentativeName());

            String caregiverSignatureUploadFile = null, patientSignatureUploadFile = null, driverAgentSignatureUploadFile = null;
            CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
            String commonSignatureFilePath = fileUploadConfigProperties.getSignatureProperties().getLocation();

            int begin = uploadSignatureJsonReqAndRep.getPatientSignature().indexOf(",");
            int last = uploadSignatureJsonReqAndRep.getPatientSignature().length();
            String b_64 = uploadSignatureJsonReqAndRep.getPatientSignature().substring(begin + 1, last);
            int begin1 = uploadSignatureJsonReqAndRep.getDriverAgentSignature().indexOf(",");
            int last1 = uploadSignatureJsonReqAndRep.getDriverAgentSignature().length();
            String b_64_1 = uploadSignatureJsonReqAndRep.getDriverAgentSignature().substring(begin1 + 1, last1);

            if(uploadSignatureJsonReqAndRep.getSignatureType().equals("caregiver")){
                if(uploadSignatureJsonReqAndRep.getCaregiverSignature() != null && !uploadSignatureJsonReqAndRep.getCaregiverSignature().isEmpty()){
                    caregiverSignatureUploadFile = uploadSignatureJsonReqAndRep.getDeliveryTicketId()+"_Caregiver_Signature.png";
                    String caregiverSignFilePath = commonSignatureFilePath+"/"+caregiverSignatureUploadFile;
                    commonPDFStubs.jsonToSignatureConverter(b_64, caregiverSignFilePath);
                }else{
                    log.info("==============Beneficiary/Patient Signature does not Exist==================");
                }
            }else if(uploadSignatureJsonReqAndRep.getSignatureType().equals("patient")){
                if(uploadSignatureJsonReqAndRep.getPatientSignature() != null && !uploadSignatureJsonReqAndRep.getPatientSignature().isEmpty()){
                    patientSignatureUploadFile = uploadSignatureJsonReqAndRep.getDeliveryTicketId()+"_Patient_Signature.png";
                    String patientSignFilePath = commonSignatureFilePath+"/"+patientSignatureUploadFile;
                    commonPDFStubs.jsonToSignatureConverter(b_64, patientSignFilePath);
                }else{
                    log.info("==============Beneficiary/Patient Signature does not Exist==================");
                }
            }
            if(uploadSignatureJsonReqAndRep.getDriverAgentSignature() != null && !uploadSignatureJsonReqAndRep.getDriverAgentSignature().isEmpty()){
                driverAgentSignatureUploadFile = uploadSignatureJsonReqAndRep.getDeliveryTicketId()+"_Driver_Agent_Signature.png";
                String driverAgentSignFilePath = commonSignatureFilePath+"/"+driverAgentSignatureUploadFile;
                commonPDFStubs.jsonToSignatureConverter(b_64_1, driverAgentSignFilePath);
            }else{
                log.info("==============Beneficiary/Patient Signature does not Exist==================");
            }

            DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.getDeliveryDocumentSignatureOnDeliveryTicketId(uploadSignatureJsonReqAndRep.getDeliveryTicketId());

            /*****Set values received from Requestbody Starts*****/
            String patientRepresentativeName = uploadSignatureJsonReqAndRep.getPatientRepresentativeName()!=null ? uploadSignatureJsonReqAndRep.getPatientRepresentativeName() : "";
            String patientRelationship = uploadSignatureJsonReqAndRep.getPatientRelationship()!=null ? uploadSignatureJsonReqAndRep.getPatientRelationship(): "";
            String reasonNotToSign = uploadSignatureJsonReqAndRep.getReasonNotToSign()!=null? uploadSignatureJsonReqAndRep.getReasonNotToSign(): "";

            commonDeliveryInputData.setPatientRepresentativeName(patientRepresentativeName);
            commonDeliveryInputData.setPatientRelationship(patientRelationship);
            commonDeliveryInputData.setReasonNotToSign(reasonNotToSign);
            /*****Set values received from Requestbody Ends*****/

            ObjectMapper mapper = new ObjectMapper();
            String formJsonData = null;
            try {
                formJsonData = mapper.writeValueAsString(commonDeliveryInputData);
            } catch (Exception exception) {
                log.error(String.valueOf(exception));
            }
            if (deliveryDocumentsSignature == null) {
                deliveryDocumentsSignature = new DeliveryDocumentsSignature();
                if(uploadSignatureJsonReqAndRep.getSignatureType().equals("caregiver"))
                    deliveryDocumentsSignature.setCaregiverSignatureFile(caregiverSignatureUploadFile);
                if(uploadSignatureJsonReqAndRep.getSignatureType().equals("patient"))
                    deliveryDocumentsSignature.setPatientSignatureFile(patientSignatureUploadFile);
                deliveryDocumentsSignature.setDriverAgentSignatureFile(driverAgentSignatureUploadFile);
                deliveryDocumentsSignature.setStatus("active");
                deliveryDocumentsSignature.setDateTime(LocalDate.now());
                deliveryDocumentsSignature.setDeliveryTicketId(deliveryTicket.get().getDeliveryTicketId());
                deliveryDocumentsSignature.setDeliveryTicketNo(deliveryTicket.get().getDeliveryTicketNo());
                deliveryDocumentsSignature.setSignatureFileResponseJsonData(formJsonData);
                deliveryDocumentsSignature.setCaregiverName(patientRepresentativeName);
                deliveryDocumentsSignature.setCaregiverRelationship(patientRelationship);
                deliveryDocumentsSignature.setPatientReasonnotsigned(reasonNotToSign);
                documentsSignature = deliveryDocumentsSignatureRepository.save(deliveryDocumentsSignature);
                if (documentsSignature != null) {
                    log.info("=======SAVED========>");
                } else {
                    log.info("=======NOT SAVED========>");
                }
            }
            else{
                if(uploadSignatureJsonReqAndRep.getSignatureType().equals("caregiver"))
                    deliveryDocumentsSignature.setCaregiverSignatureFile(caregiverSignatureUploadFile);
                if(uploadSignatureJsonReqAndRep.getSignatureType().equals("patient"))
                    deliveryDocumentsSignature.setPatientSignatureFile(patientSignatureUploadFile);
                deliveryDocumentsSignature.setDriverAgentSignatureFile(driverAgentSignatureUploadFile);
                deliveryDocumentsSignature.setSignatureFileResponseJsonData(formJsonData);
                deliveryDocumentsSignature.setCaregiverName(patientRepresentativeName);
                deliveryDocumentsSignature.setCaregiverRelationship(patientRelationship);
                deliveryDocumentsSignature.setPatientReasonnotsigned(reasonNotToSign);
                deliveryDocumentsSignatureRepository.save(deliveryDocumentsSignature);
            }

            String hcpcsCode = "0";
            List<HcpcsDocType> hcpcsDocTypeList = hcpcsDocTypeRepository.getHcpcsDocumentsOnHcpcsCode(hcpcsCode);
            log.info("=======================SIZE=========doctypelist============="+hcpcsDocTypeList.size());
            if(hcpcsDocTypeList != null && hcpcsDocTypeList.size() > 0){
                hcpcsDocTypeList.stream().forEach(hcpcs -> {
                    try {
                        if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("dme.certification"))){
                            log.info("=======================IF Inside delivery documents==============documentid = 1========================");
                            Long documentId = hcpcs.getDocumentId();
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);
                            DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.get().getDeliveryTicketId(), hcpcsDocType.getHcpcsDoctypeId());
                            if(deliveryDocuments != null) {
                                ServiceOutcome<DeliveryDocumentResponse> outcome = prepareDMECertificationReport(deliveryTicket.get(), commonDeliveryInputData, deliveryDocuments);
                                if(outcome.getOutcome()) {
                                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                    DeliveryDocuments updateDeliveryDocuments = updateDeliveryDocuments(deliveryDocuments, source, outcome);
                                    DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsMapper.toDto(updateDeliveryDocuments), source, "deliveryDocumentFiles");
                                    deliveryDocumentResponses.add(documentResponse);
                                }
                            }
                        }
                        else if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("patient.rights.responsibility"))){
                            Long documentId = hcpcs.getDocumentId();
                            DeliveryDocumentsDTO deliveryDocumentsDTO;
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);
                            if(hcpcsDocType.getFileType().equalsIgnoreCase("Doc")) {
                                DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.get().getDeliveryTicketId(), hcpcsDocType.getHcpcsDoctypeId());
                                log.info("=======================IF Inside delivery documents==============documentid = 2========================");
                                ServiceOutcome<DeliveryDocumentResponse> outcome = preparePatientRightsResponsibilityDocumentReport(deliveryTicket.get(), commonDeliveryInputData);
                                if (outcome.getOutcome()){
                                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                    String jsonData = "";
                                    deliveryDocumentsDTO = oprationOnDeliveryDocuments(deliveryTicket.get(), hcpcs, jsonData);
                                    deliveryDocuments = deliveryDocumentsMapper.toEntity(deliveryDocumentsDTO);

                                    DeliveryDocuments updateDeliveryDocuments = updateDeliveryDocuments(deliveryDocuments, source, outcome);
                                    DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsMapper.toDto(updateDeliveryDocuments), source, "deliveryDocumentFiles");
                                    deliveryDocumentResponses.add(documentResponse);
                                }
                            }
                        }
                        else if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("hipaa.notice"))){
                            Long documentId = hcpcs.getDocumentId();
                            DeliveryDocumentsDTO deliveryDocumentsDTO;
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);

                            if(hcpcsDocType.getFileType().equalsIgnoreCase("Doc")) {
                                DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.get().getDeliveryTicketId(), hcpcsDocType.getHcpcsDoctypeId());
                                log.info("=======================IF Inside delivery documents==============documentid = 2========================");
                                ServiceOutcome<DeliveryDocumentResponse> outcome = prepareHIPAANoticeReport(deliveryTicket.get(), commonDeliveryInputData);
                                File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());

                                String jsonData = "";
                                deliveryDocumentsDTO = oprationOnDeliveryDocuments(deliveryTicket.get(), hcpcs, jsonData);
                                deliveryDocuments = deliveryDocumentsMapper.toEntity(deliveryDocumentsDTO);

                                DeliveryDocuments updateDeliveryDocuments = updateDeliveryDocuments(deliveryDocuments, source, outcome);
                                DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsMapper.toDto(updateDeliveryDocuments), source, "deliveryDocumentFiles");
                                deliveryDocumentResponses.add(documentResponse);
                            }
                        }
                        else if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("home.assessment"))){
                            Long documentId = hcpcs.getDocumentId();
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);
                            DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.get().getDeliveryTicketId(), hcpcsDocType.getHcpcsDoctypeId());
                            if(deliveryDocuments != null) {
                                ServiceOutcome<DeliveryDocumentResponse> outcome = prepareHomeAssessmentDocumentReport(deliveryTicket.get(), commonDeliveryInputData, deliveryDocuments);
                                if(outcome.getOutcome()) {
                                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                    DeliveryDocuments updateDeliveryDocuments = updateDeliveryDocuments(deliveryDocuments, source, outcome);
                                    DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsMapper.toDto(updateDeliveryDocuments), source, "deliveryDocumentFiles");
                                    deliveryDocumentResponses.add(documentResponse);
                                }
                            }
                        }
                        else if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("patient.assessment"))){
                            Long documentId = hcpcs.getDocumentId();
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);
                            DeliveryDocuments deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(deliveryTicket.get().getDeliveryTicketId(), hcpcsDocType.getHcpcsDoctypeId());
                            if(deliveryDocuments != null) {
                                ServiceOutcome<DeliveryDocumentResponse> outcome = preparePatientAssessmentDocumentReport(deliveryTicket.get(), commonDeliveryInputData, deliveryDocuments);
                                if(outcome.getOutcome()) {
                                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                    DeliveryDocuments updateDeliveryDocuments = updateDeliveryDocuments(deliveryDocuments, source, outcome);
                                    DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsMapper.toDto(updateDeliveryDocuments), source, "deliveryDocumentFiles");
                                    deliveryDocumentResponses.add(documentResponse);
                                }
                            }
                        }
                        else if (hcpcs.getDocumentName().equals(fileConfigUtility.getProperty("delivery-ticket.receipt"))){
                            DeliveryDocumentsDTO deliveryDocumentsDTO;
                            Long documentId = hcpcs.getDocumentId();
                            HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeAndDocumentId(hcpcsCode, documentId);
                            if(hcpcsDocType.getFileType().equalsIgnoreCase("Doc") && deliveryTicket.get().getDeliveryType().equalsIgnoreCase("Agent Deliver")) {
                                ServiceOutcome<DeliveryDocumentResponse> outcome = prepareDeliveryDocumentReport(deliveryTicket.get(), commonDeliveryInputData);
                                log.info("====Prepare Delivery Document Report Outcome=====>"+outcome.getData().getDocumentPatientFilePath());
                                deliveryTicket.get().setDeliveryTicketDocumentNo(deliveryTicket.get().getDeliveryTicketNo());
                                deliveryTicket.get().setDeliveryTicketDocumentName(outcome.getData().getDocumentPatientFilePath());
                                DeliveryTicket updateDeliveryTicket = deliveryTicketRepository.save(deliveryTicket.get());
                                log.info("===============Update Delivery Ticket=============>>>>>"+updateDeliveryTicket.getDeliveryTicketId());
                                File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                deliveryDocumentsDTO = builtDeliveryDocumentDTO(null, deliveryTicket.get(), hcpcsDocType, source);
                                DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsDTO, source, "deliveryDocumentFiles");
                                deliveryDocumentResponses.add(documentResponse);
                            }
                            else if(hcpcsDocType.getFileType().equalsIgnoreCase("Doc") && (deliveryTicket.get().getDeliveryType().equalsIgnoreCase("Dropship") || deliveryTicket.get().getDeliveryType().equalsIgnoreCase("Courier"))) {
                                List<DeliveryItems> deliveryItems = getDeliveryItemsOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
                                ServiceOutcome<DeliveryDocumentResponse> outcome = prepareProofOfDeliveryReportOnDropship(deliveryTicket.get(), deliveryItems, commonDeliveryInputData);
                                log.info("====Prepare Delivery Document Report Outcome=====>"+outcome.getData().getDocumentPatientFilePath());
                                deliveryTicket.get().setDeliveryTicketDocumentNo(deliveryTicket.get().getDeliveryTicketNo());
                                deliveryTicket.get().setDeliveryTicketDocumentName(outcome.getData().getDocumentPatientFilePath());
                                DeliveryTicket updateDeliveryTicket = deliveryTicketRepository.save(deliveryTicket.get());
                                log.info("===============Update Delivery Ticket=============>>>>>"+updateDeliveryTicket.getDeliveryTicketId());
                                File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + outcome.getData().getDocumentPatientFilePath());
                                deliveryDocumentsDTO = builtDeliveryDocumentDTO(null, deliveryTicket.get(), hcpcsDocType, source);
                                DeliveryDocumentResponse documentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocumentsDTO, source, "deliveryDocumentFiles");
                                documentResponse = builtDeliveryTicketDocumentDetails(documentResponse, uploadSignatureJsonReqAndRep);
                                deliveryDocumentResponses.add(documentResponse);
                            }
                        }
                        deliveryOutputResponse.setDeliveryTicketDTO(deliveryTicketMapper.toDto(deliveryTicket.get()));
                        deliveryOutputResponse.setDeliveryDocumentResponseList(deliveryDocumentResponses);
                    } catch (Exception e) {
                        message.set("Failed to upload the files!!!");
                        throw new RuntimeException(e);
                    }
                });
                status.set(true);
                message.set("Successfully uploaded the files!");
            }
            return new ServiceOutcome<>(deliveryOutputResponse, status.get(), message.get());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public DeliveryDocumentResponse builtDeliveryTicketDocumentDetails(DeliveryDocumentResponse documentResponse, UploadSignatureJsonReqAndRep uploadSignatureJsonReqAndRep){
        if(uploadSignatureJsonReqAndRep.getSignatureType().equals("caregiver"))
            documentResponse.setIsCaregiverSigned("Yes");
        if(uploadSignatureJsonReqAndRep.getSignatureType().equals("patient"))
            documentResponse.setIsPatientSigned("Yes");
        documentResponse.setIsDeliveryAgentSigned("Yes");
        documentResponse.setSignedDate(LocalDate.now());
        return documentResponse;
    }

    @Override
    public ItemInventoryStatusInputRequest prepareItemInventoryStatusInputDetails(Long deliveryTicketId){
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(deliveryTicketId);
        List<ItemInventoryStatusInputParams> itemInventoryStatusInputParamsList = new ArrayList<>();
        ItemInventoryStatusInputRequest itemInventoryStatusInputRequest = new ItemInventoryStatusInputRequest();
        if (deliveryTicket.isPresent()) {
            log.info("==========================>"+deliveryTicket.get().getDeliveryTicketNo());
            log.info("==========================>"+deliveryTicket.get().getDeliveryTicketNo());
            List<DeliveryItems> deliveryItemsList;
            ItemInventoryStatusInputParams itemInventoryStatusInputParams = new ItemInventoryStatusInputParams();
            itemInventoryStatusInputParams.setDeliveryTicketId(deliveryTicket.get().getDeliveryTicketId());
            itemInventoryStatusInputParams.setDeliveryTicketNo(deliveryTicket.get().getDeliveryTicketNo());
            itemInventoryStatusInputParams.setItemLocationId(deliveryTicket.get().getInventoryLocationId());
            itemInventoryStatusInputParams.setSoId(deliveryTicket.get().getSoId());
            itemInventoryStatusInputParams.setSoNumber(deliveryTicket.get().getSoNo());
            itemInventoryStatusInputParams.setDeliveryType(deliveryTicket.get().getDeliveryType());
            itemInventoryStatusInputParams.setServiceType("DELIVERY");
            itemInventoryStatusInputParams.setDeliveryTicketUuid(deliveryTicket.get().getDeliveryTicketUuid().toString());
            deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
            List<DeliveryItemData> deliveryItemDataList = new ArrayList<>();
            if(deliveryItemsList != null && deliveryItemsList.size() > 0){
                deliveryItemsList.stream().forEach(item -> {
                    DeliveryItemData deliveryItemData = new DeliveryItemData();
                    deliveryItemData.setDeliveryItemId(item.getDeliveryItemId());
                    deliveryItemData.setItemId(item.getItemId());
                    deliveryItemData.setItemNumber(item.getItemNo());
                    deliveryItemData.setItemName(item.getItemName());
                    deliveryItemData.setHcpcsCode(item.getHcpcsCode());
                    deliveryItemData.setItemQuantity(item.getItemQuantity());
                    if(item.getItemSerial() != null)
                        deliveryItemData.setItemSerialNumber(item.getItemSerial());
                    else
                        deliveryItemData.setItemSerialNumber("");
                    deliveryItemData.setItemSaleType(item.getSoSaleType());
                    if(item.getIsDropship() != null)
                        deliveryItemData.setIsDropship(item.getIsDropship());
                    else
                        deliveryItemData.setIsDropship("");
                    if(item.getPoNumber() != null)
                        deliveryItemData.setPoNumber(item.getPoNumber());
                    else
                        deliveryItemData.setPoNumber("");
                    deliveryItemData.setDeliveryItemsUuid(item.getDeliveryItemsUuid().toString());
                    deliveryItemDataList.add(deliveryItemData);
                });
                itemInventoryStatusInputParams.setDeliveryItemData(deliveryItemDataList);
                itemInventoryStatusInputParamsList.add(itemInventoryStatusInputParams);
            }
            itemInventoryStatusInputRequest.setItemInventoryStatusInputParamsList(itemInventoryStatusInputParamsList);

            if(itemInventoryStatusInputRequest != null){
                log.info("================>ItemInventoryStatusResponse===================="+itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().get(0).getDeliveryTicketNo());
                ObjectMapper mapper = new ObjectMapper();
                String formJsonData = null;
                try {
                    formJsonData = mapper.writeValueAsString(itemInventoryStatusInputRequest);
                    System.out.println("====JSON FORMATTED DATA=====>"+formJsonData);
                    String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
                    log.info("============><============="+token);
                    MultiValueMap<String, ItemInventoryStatusInputRequest> formData = new LinkedMultiValueMap<>();
                    formData.add("itemInventoryStatusInputRequest", itemInventoryStatusInputRequest);

                    String url = fileConfigUtility.getProperty("api-gateway.ip")+"/items/api/inventory/updateItemInventoryStatusByItemAndLocation";
                    log.info("=================url=====================================>"+url);
                    ServiceOutcome<ItemInventoryStatusResponse> externalServiceOutcome = webClientBuilder.build().post()
                        .uri(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer "+token)
                        .body(Mono.just(itemInventoryStatusInputRequest), ItemInventoryStatusInputRequest.class)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<ItemInventoryStatusResponse>>() {}).toFuture().get();
                    log.info("========================>Service Outcome==============>"+externalServiceOutcome.getMessage());
                    log.info("========================>Service Outcome==============>"+externalServiceOutcome.getData().getItemInventoryStatusInputParamsList().get(0).getDeliveryTicketNo());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return itemInventoryStatusInputRequest;
    }

    public DeliveryDocumentResponse operationOnDeliveryDocumentSignedFileResponse(DeliveryDocumentsDTO deliveryDocumentsDTO, File destination, String documentType) {
        DeliveryDocumentResponse documentResponse = new DeliveryDocumentResponse();
        documentResponse.setDeliveryDocId(deliveryDocumentsDTO.getDeliveryDocId());
        documentResponse.setDeliveryTicketId(deliveryDocumentsDTO.getDeliveryTicketId());
        documentResponse.setDeliveryTicketNo(deliveryDocumentsDTO.getDeliveryTicketNo());
        documentResponse.setSoId(deliveryDocumentsDTO.getSoId());
        documentResponse.setSoNo(deliveryDocumentsDTO.getSoNo());
        documentResponse.setHcpcsDoctypeId(deliveryDocumentsDTO.getHcpcsDoctypeId());
        documentResponse.setDocPatientName(deliveryDocumentsDTO.getDocPatientName());

        String fileDownloadUri = "";
        if (documentType.equals("deliveryDocumentFiles")){
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadDeliveryDocumentFiles/")
                .path(destination.getName())
                .toUriString();
        }
        if (documentType.equals("signatureFiles")){
            fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadSignatureFiles/")
                .path(destination.getName())
                .toUriString();
        }
        documentResponse.setDocumentPatientFilePath(fileDownloadUri);
        documentResponse.setDocumentPatientFile(deliveryDocumentsDTO.getDocumentPatientFilePath());
        documentResponse.setIsPatientSigned(deliveryDocumentsDTO.getIsPatientSigned());
        documentResponse.setIsDeliveryAgentSigned(deliveryDocumentsDTO.getIsDeliveryAgentSigned());
        documentResponse.setSignedDate(deliveryDocumentsDTO.getSignedDate());
        documentResponse.setIsCaregiverSigned(deliveryDocumentsDTO.getIsCaregiverSigned());
        return documentResponse;
    }

    public String saveFile(MultipartFile file, Long deliveryTicketId) {
        String fileName = null;
        try {
            fileName = deliveryTicketId+"_"+file.getOriginalFilename();
            System.out.println("==========================="+fileUploadConfigProperties.getSignatureProperties().getLocation());
            Path dfile = Paths.get(fileUploadConfigProperties.getSignatureProperties().getLocation()+"/"+fileName);
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }
        catch (Exception e) {
            throw new FileStorageException("Could not upload file");
        }
    }

    @Override
    public Resource loadFileAsSignatureResource(String fileName) {
        try {
            Path filePath = Paths.get(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            else{
                throw new MyFileNotFoundException("File not found "+fileName);
            }
        }
        catch(MalformedURLException me){
            throw new MyFileNotFoundException("File not found "+fileName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resource loadFileAsDeliveryDocumentResource(String fileName) throws MalformedURLException{
        try {
            Path filePath = Paths.get(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            else{
                throw new MyFileNotFoundException("File not found "+fileName);
            }
        }
        catch(MalformedURLException me){
            throw new MyFileNotFoundException("File not found "+fileName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServiceOutcome<DMECertificationFormInput> receiveDMECertificationFileUserData(DMECertificationFormInput dmeCertificationFormInput) throws IOException {
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(Long.valueOf(dmeCertificationFormInput.getDeliveryTicketId()));
        ServiceOutcome<DMECertificationFormInput> serviceOutcome = operationOnDMECertificationDeliveryDocuments(dmeCertificationFormInput, deliveryTicket.get());
        return serviceOutcome;
    }

    public ServiceOutcome<DMECertificationFormInput> operationOnDMECertificationDeliveryDocuments(DMECertificationFormInput dmeCertificationFormInput, DeliveryTicket deliveryTicket) throws IOException {
        ServiceOutcome<DMECertificationFormInput> serviceOutcome = new ServiceOutcome<DMECertificationFormInput>();
        HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnFormName(dmeCertificationFormInput.getFormName());
        if(hcpcsDocType != null){
            ObjectMapper mapper = new ObjectMapper();
            String formJsonData = null;
            try {
                if(deliveryTicket != null && dmeCertificationFormInput.getFormName() != null){
                    List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.getDeliveryTicketId());
                    dmeCertificationFormInput = generateDMECertificateFormData(deliveryTicket, dmeCertificationFormInput, deliveryItemsList);
                }
                formJsonData = mapper.writeValueAsString(dmeCertificationFormInput);
            } catch (Exception exception) {
                log.error(String.valueOf(exception));
            }
            System.out.println("====JSON FORMATTED DATA=====>"+formJsonData);
            DeliveryDocumentsDTO deliveryDocumentsDTO = oprationOnDeliveryDocuments(deliveryTicket, hcpcsDocType, formJsonData);
            if(deliveryDocumentsDTO != null){
                if(deliveryDocumentsDTO != null)
                    System.out.println("==============Saved==============="+formJsonData);
                else
                    System.out.println("==============Not Saved==============="+formJsonData);
            }
        }
        serviceOutcome.setData(dmeCertificationFormInput);
        serviceOutcome.setMessage("Success");
        serviceOutcome.setOutcome(true);
        return serviceOutcome;
    }

    public DMECertificationFormInput generateDMECertificateFormData(DeliveryTicket deliveryTicket, DMECertificationFormInput dmeCertificationFormInput, List<DeliveryItems> deliveryItemsList) {
        DMECertificationFormInput dmeCertificationFormOutput;
        try{
            dmeCertificationFormOutput = new DMECertificationFormInput();
            dmeCertificationFormOutput.setFormName(dmeCertificationFormInput.getFormName());
            dmeCertificationFormOutput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
            dmeCertificationFormOutput.setCompanyName(deliveryTicket.getBranchName());
            dmeCertificationFormOutput.setName(deliveryTicket.getPatientFirstName()+" "+deliveryTicket.getPatientLastName());
            dmeCertificationFormOutput.setPatientId(deliveryTicket.getPatientIdNo());
            dmeCertificationFormOutput.setStreetAddress(deliveryTicket.getDeliveryAddress1() + " "+deliveryTicket.getDeliveryAddress2());
            dmeCertificationFormOutput.setCity(deliveryTicket.getDeliveryCity());
            dmeCertificationFormOutput.setTelephoneNo(deliveryTicket.getPhone1());
            dmeCertificationFormOutput.setProviderName(deliveryTicket.getOrderingProviderLastName());
            dmeCertificationFormOutput.setPan(deliveryTicket.getPatientIdNo());
            dmeCertificationFormOutput.setNPIAPI(deliveryTicket.getBranchNpi());;
            dmeCertificationFormOutput.setTpi("");
            dmeCertificationFormOutput.setCurrentdate(formatter.format(new Date()));
            dmeCertificationFormOutput.setCurrentdate_1(formatter.format(new Date()));
            dmeCertificationFormOutput.setPatientSignature("");
            dmeCertificationFormOutput.setPatientRepresentativeName("");
            dmeCertificationFormOutput.setPatientRelationship("");
            dmeCertificationFormOutput.setReasonNotToSign("");
            dmeCertificationFormOutput.setDate(formatter.format(new Date()));
            dmeCertificationFormOutput.setSignatureCompRepresentative("");
            dmeCertificationFormOutput.setNameCompRepresentative("");
            dmeCertificationFormOutput.setDateOfService("");
            List<DeliveryItemDetails> deliveryItemDetailsList = new ArrayList<>();
            DeliveryItemDetails itemDetails;
            for (DeliveryItemDetails inputItem : dmeCertificationFormInput.getDeliveryItemDetailsList()) {
                for(DeliveryItems item : deliveryItemsList) {
                    if (item.getHcpcsCode().equals(inputItem.getProccode())) {
                        item.setItemSerial(inputItem.getSerial());
                        deliveryItemsRepository.save(item);
                        itemDetails = new DeliveryItemDetails(item.getHcpcsCode(), item.getItemDescription(), inputItem.getSerial());
                        deliveryItemDetailsList.add(itemDetails);
                    }
                }
            }
            dmeCertificationFormInput.setDeliveryItemDetailsList(deliveryItemDetailsList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dmeCertificationFormInput;
    }

    public Boolean fillUserDataDMECertificationDeliveryDocuments(File source, File destination, Optional<DeliveryTicket> deliveryTicket, DMECertificationFormInput dmeCertificationFormInput){
        Boolean status = false;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(destination.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(source.getAbsolutePath()));

            stamper.getAcroFields().setField("companyName", deliveryTicket.get().getBranchName());
            stamper.getAcroFields().setField("name", CaseUtils.toCamelCase(deliveryTicket.get().getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.get().getPatientLastName(), true, ' '));
            stamper.getAcroFields().setField("patientId", deliveryTicket.get().getPatientIdNo());
            stamper.getAcroFields().setField("streetAddress", deliveryTicket.get().getDeliveryAddress1()+", "+deliveryTicket.get().getDeliveryAddress2());
            stamper.getAcroFields().setField("city", CaseUtils.toCamelCase(deliveryTicket.get().getDeliveryCity(), true, ' ')+", "+deliveryTicket.get().getDeliveryState()+" "+deliveryTicket.get().getDeliveryZip());
            stamper.getAcroFields().setField("telephoneNo", deliveryTicket.get().getPhone1());

            stamper.getAcroFields().setField("ProviderName", deliveryTicket.get().getBranchName().toUpperCase());
            stamper.getAcroFields().setField("pan", "");
            stamper.getAcroFields().setField("NPIAPI", deliveryTicket.get().getBranchNpi());
            stamper.getAcroFields().setField("tpi", "");

            stamper.getAcroFields().setField("monthyear", formatter.format(new Date()));
            stamper.getAcroFields().setField("monthyear_1", formatter.format(new Date()));

            stamper.getAcroFields().setField("patientSignature", "");
            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.get().getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.get().getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", formatter.format(new Date()));

            stamper.getAcroFields().setField("signatureCompRepresentative", "");
            stamper.getAcroFields().setField("companyRepresentativeName", deliveryTicket.get().getBranchName().toUpperCase());


            stamper.getAcroFields().setField("dateOfService", "");
            var count = new Object(){int ctx = 1;};
            dmeCertificationFormInput.getDeliveryItemDetailsList().stream().forEach(item -> {
                try {
                    stamper.getAcroFields().setField("proccode"+count.ctx, item.getProccode());
                    stamper.getAcroFields().setField("description"+count.ctx, item.getDescription());
                    stamper.getAcroFields().setField("serial"+count.ctx, item.getSerial());
                    DeliveryItems deliveryItems = deliveryItemsRepository.getDeliveryItemOnDeliveryTicketAndHcpcsCode(deliveryTicket.get().getDeliveryTicketId(), item.getProccode());
                    if(deliveryItems != null) {
                        deliveryItems.setItemSerial(item.getSerial());
                        deliveryItemsRepository.save(deliveryItems);
                    }
                    count.ctx++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }
            });
            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            pdfReader.close();
            destination.delete();
            e.printStackTrace();
        }
        return status;
    }

    public ServiceOutcome<DeliveryDocumentResponse> addSignaturesToDeliveryDocuments(DeliveryTicket deliveryTicket, File source, int pageNo, CommonDeliveryInputData commonDeliveryInputData) throws IOException {
        File patientSignatureFile = null;
        File companyRepresentativeSignatureFile = null;
        DeliveryDocumentResponse deliveryDocumentResponse = new DeliveryDocumentResponse();
        DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.getDeliveryDocumentSignatureOnDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        if(deliveryDocumentsSignature != null) {
            if(commonDeliveryInputData.getSignatureType().equals("caregiver")) {
                patientSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getCaregiverSignatureFile());
                deliveryDocumentResponse.setIsCaregiverSigned("Yes");
            }
            if(commonDeliveryInputData.getSignatureType().equals("patient")) {
                patientSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getPatientSignatureFile());
                deliveryDocumentResponse.setIsPatientSigned("Yes");
            }
            companyRepresentativeSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getDriverAgentSignatureFile());
            deliveryDocumentResponse.setIsDeliveryAgentSigned("Yes");
        }

        PDDocument document = PDDocument.load(source);
        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
        PDSignatureField pdSignatureField = (PDSignatureField) acroForm.getField("signatureCompRepresentative");
        PDSignatureField pdSignatureField1 = (PDSignatureField) acroForm.getField("signaturePatientRepresentative");
        PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(pageNo), PDPageContentStream.AppendMode.APPEND, true, true);
        PDImageXObject image1 = PDImageXObject.createFromFile(patientSignatureFile.getAbsolutePath(), document);
        PDImageXObject image2 = PDImageXObject.createFromFile(companyRepresentativeSignatureFile.getAbsolutePath(), document);

        float x = pdSignatureField.getWidget().getRectangle().getLowerLeftX();
        float y = pdSignatureField.getWidget().getRectangle().getLowerLeftY();
        float width = pdSignatureField.getWidget().getRectangle().getWidth();
        float height = pdSignatureField.getWidget().getRectangle().getHeight();

        contentStream.drawImage(image1, x, y, width, height);

        float x1 = pdSignatureField1.getWidget().getRectangle().getLowerLeftX();
        float y1 = pdSignatureField1.getWidget().getRectangle().getLowerLeftY();
        float width1 = pdSignatureField1.getWidget().getRectangle().getWidth();
        float height1 = pdSignatureField1.getWidget().getRectangle().getHeight();

        contentStream.drawImage(image2, x1, y1, width1, height1);
        contentStream.close();
        document.save(source);
        acroForm.flatten();
        document.close();
        deliveryDocumentResponse.setSignedDate(LocalDate.now());
        return new ServiceOutcome<>(deliveryDocumentResponse, true, "Signature is added to document successfully!");
    }

    @Override
    public ServiceOutcome<PatientHomeAssessmentFormInput> receiveHomeAssessmentFileUserData(PatientHomeAssessmentFormInput patientHomeAssessmentFormInput) throws IOException {
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(Long.valueOf(patientHomeAssessmentFormInput.getDeliveryTicketId()));
        ServiceOutcome<PatientHomeAssessmentFormInput> serviceOutcome = operationOnHomeAssessmentDeliveryDocuments(patientHomeAssessmentFormInput, deliveryTicket.get());
        return serviceOutcome;
    }

    public ServiceOutcome<PatientHomeAssessmentFormInput> operationOnHomeAssessmentDeliveryDocuments(PatientHomeAssessmentFormInput patientHomeAssessmentFormInput, DeliveryTicket deliveryTicket) throws IOException {
        ServiceOutcome<PatientHomeAssessmentFormInput> serviceOutcome = new ServiceOutcome<PatientHomeAssessmentFormInput>();
        HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnFormName(patientHomeAssessmentFormInput.getFormName());
        if(hcpcsDocType != null){
            ObjectMapper mapper = new ObjectMapper();
            String formJsonData = null;
            try {
                if(deliveryTicket.getDeliveryTicketId() != null && patientHomeAssessmentFormInput.getFormName()!= null){
                    String formName = patientHomeAssessmentFormInput.getFormName();
                    patientHomeAssessmentFormInput = generateHomeAssessmentFormCommonData(deliveryTicket, patientHomeAssessmentFormInput);
                }
                formJsonData = mapper.writeValueAsString(patientHomeAssessmentFormInput);
            } catch (Exception exception) {
                log.error(String.valueOf(exception));
            }
            System.out.println("====JSON FORMATTED DATA=====>"+formJsonData);
            DeliveryDocumentsDTO deliveryDocumentsDTO = oprationOnDeliveryDocuments(deliveryTicket, hcpcsDocType, formJsonData);
            if(deliveryDocumentsDTO != null){
                if(deliveryDocumentsDTO != null)
                    System.out.println("==============Saved==============="+formJsonData);
                else
                    System.out.println("==============Not Saved==============="+formJsonData);
            }
        }
        serviceOutcome.setData(patientHomeAssessmentFormInput);
        serviceOutcome.setMessage("Success");
        serviceOutcome.setOutcome(true);

        return serviceOutcome;
    }

    public PatientHomeAssessmentFormInput generateHomeAssessmentFormCommonData(DeliveryTicket deliveryTicket, PatientHomeAssessmentFormInput patientHomeAssessmentFormInput) {
        try{
            patientHomeAssessmentFormInput.setFormName(patientHomeAssessmentFormInput.getFormName());
            patientHomeAssessmentFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
            patientHomeAssessmentFormInput.setCompanyName(deliveryTicket.getBranchName());

            String branchAddr1 = deliveryTicket.getBranchAddressLine1()!= null ? deliveryTicket.getBranchAddressLine1() : "";
            String branchAddr2 = deliveryTicket.getBranchAddressLine2()!= null ? deliveryTicket.getBranchAddressLine2() : "";
            String branchCity = deliveryTicket.getBranchCity()!= null ? deliveryTicket.getBranchCity() : "";
            String branchState = deliveryTicket.getBranchState()!= null ? deliveryTicket.getBranchState() : "";
            String branchZip = deliveryTicket.getBranchZipCode()!= null ? deliveryTicket.getBranchZipCode() : "";
            patientHomeAssessmentFormInput.setStreetAddress(branchAddr1+", "+branchAddr2);
            patientHomeAssessmentFormInput.setCityStateZip(branchCity+", "+branchState+", "+branchZip);

            String patientFName = deliveryTicket.getPatientFirstName()!= null ? deliveryTicket.getPatientFirstName() : "";
            String patientMName = deliveryTicket.getPatientMiddleName()!= null ? deliveryTicket.getPatientMiddleName() : "";
            String patientLName = deliveryTicket.getPatientLastName()!= null ? deliveryTicket.getPatientLastName() : "";
            String patientFullName = patientFName+" "+patientMName+" "+patientLName;
            patientHomeAssessmentFormInput.setPatientName(patientFullName);
            String deliveryAddress1 = deliveryTicket.getDeliveryAddress1()!= null ? deliveryTicket.getDeliveryAddress1() : "";
            String deliveryAddress2 = deliveryTicket.getDeliveryAddress2()!= null ? deliveryTicket.getDeliveryAddress2() : "";
            String deliveryCity = deliveryTicket.getDeliveryCity()!= null ? deliveryTicket.getDeliveryCity() : "";
            String deliveryState = deliveryTicket.getDeliveryState()!= null ? deliveryTicket.getDeliveryState() : "";
            String deliveryZip = deliveryTicket.getDeliveryZip()!= null ? deliveryTicket.getDeliveryZip() : "";
            patientHomeAssessmentFormInput.setAddress(deliveryAddress1+" "+deliveryAddress2);
            patientHomeAssessmentFormInput.setpCityStateZip(deliveryCity+" "+deliveryState+" "+deliveryZip);
            patientHomeAssessmentFormInput.setPhone(deliveryTicket.getPhone1());
            patientHomeAssessmentFormInput.setPatientId(deliveryTicket.getPatientIdNo());
            patientHomeAssessmentFormInput.setAccount(patientHomeAssessmentFormInput.getAccount());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return patientHomeAssessmentFormInput;
    }

    public PatientAssessmentFormInput patientHomeAssessmentFormCommonData(DeliveryTicket deliveryTicket, PatientAssessmentFormInput patientAssessmentFormInput) {
        try{
            patientAssessmentFormInput.setFormName(patientAssessmentFormInput.getFormName());
            patientAssessmentFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
            patientAssessmentFormInput.setCompanyName(deliveryTicket.getBranchName());

            String branchAddr1 = deliveryTicket.getBranchAddressLine1()!= null ? deliveryTicket.getBranchAddressLine1() : "";
            String branchAddr2 = deliveryTicket.getBranchAddressLine2()!= null ? deliveryTicket.getBranchAddressLine2() : "";
            String branchCity = deliveryTicket.getBranchCity()!= null ? deliveryTicket.getBranchCity() : "";
            String branchState = deliveryTicket.getBranchState()!= null ? deliveryTicket.getBranchState() : "";
            String branchZip = deliveryTicket.getBranchZipCode()!= null ? deliveryTicket.getBranchZipCode() : "";
            patientAssessmentFormInput.setStreetAddress(branchAddr1+", "+branchAddr2);
            patientAssessmentFormInput.setCityStateZip(branchCity+", "+branchState+", "+branchZip);

            String patientFName = deliveryTicket.getPatientFirstName()!= null ? deliveryTicket.getPatientFirstName() : "";
            String patientMName = deliveryTicket.getPatientMiddleName()!= null ? deliveryTicket.getPatientMiddleName() : "";
            String patientLName = deliveryTicket.getPatientLastName()!= null ? deliveryTicket.getPatientLastName() : "";
            String patientFullName = patientFName+" "+patientMName+" "+patientLName;
            patientAssessmentFormInput.setPatientName(patientFullName);
            String deliveryAddress1 = deliveryTicket.getDeliveryAddress1()!= null ? deliveryTicket.getDeliveryAddress1() : "";
            String deliveryAddress2 = deliveryTicket.getDeliveryAddress2()!= null ? deliveryTicket.getDeliveryAddress2() : "";
            String deliveryCity = deliveryTicket.getDeliveryCity()!= null ? deliveryTicket.getDeliveryCity() : "";
            String deliveryState = deliveryTicket.getDeliveryState()!= null ? deliveryTicket.getDeliveryState() : "";
            String deliveryZip = deliveryTicket.getDeliveryZip()!= null ? deliveryTicket.getDeliveryZip() : "";
            patientAssessmentFormInput.setAddress(deliveryAddress1+", "+deliveryAddress2);
            patientAssessmentFormInput.setpCityStateZip(deliveryCity+", "+deliveryState+", "+deliveryZip);
            patientAssessmentFormInput.setPhone(deliveryTicket.getPhone1());
            patientAssessmentFormInput.setPatientId(deliveryTicket.getPatientIdNo());
            patientAssessmentFormInput.setAccount(patientAssessmentFormInput.getAccount());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return patientAssessmentFormInput;
    }

    public Boolean fillUserDataHomeAssessmentDeliveryDocuments(File source, File destination, DeliveryTicket deliveryTicket, PatientHomeAssessmentFormInput patientHomeAssessmentFormInput, DeliveryAssignment deliveryAssignment){
        Boolean status = false;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(destination.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(source.getAbsolutePath()));

            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());
            stamper.getAcroFields().setField("streetAddress", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phoneNo", deliveryTicket.getPhone1());
            stamper.getAcroFields().setField("fax", deliveryTicket.getBranchFax());

            stamper.getAcroFields().setField("patientName", CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' '));
            stamper.getAcroFields().setField("patientId", deliveryTicket.getPatientIdNo());
            stamper.getAcroFields().setField("address", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("account", "");
            stamper.getAcroFields().setField("p_cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phone", deliveryTicket.getPhone1());

            String param = "";
            param = "";
            if(patientHomeAssessmentFormInput.getAssessmentPerformedVerballyCheck())
                param = "Yes";
            stamper.getAcroFields().setField("assessmentPerformedVerballyCheck", param);
            param = "";
            if(patientHomeAssessmentFormInput.getCompletedPreliminaryAssessmentCheck())
                param = "Yes";
            stamper.getAcroFields().setField("completedPreliminaryAssessmentCheck", param);


            param = "";
            if(patientHomeAssessmentFormInput.getApplyCane())
                param = "Yes";
            stamper.getAcroFields().setField("applyCane", param);
            param = "";
            if(patientHomeAssessmentFormInput.getApplyCrutches())
                param = "Yes";
            stamper.getAcroFields().setField("applyCrutches", param);
            param = "";
            if(patientHomeAssessmentFormInput.getApplyWalker())
                param = "Yes";
            stamper.getAcroFields().setField("applyWalker", param);
            param = "";
            if(patientHomeAssessmentFormInput.getApplyManualWheelchair())
                param = "Yes";
            stamper.getAcroFields().setField("applyManualWheelchair", param);
            param = "";
            if(patientHomeAssessmentFormInput.getApplyPovScooter())
                param = "Yes";
            stamper.getAcroFields().setField("applyPovScooter", param);
            param = "";
            if(patientHomeAssessmentFormInput.getApplyPowerWheelchair())
                param = "Yes";
            stamper.getAcroFields().setField("applyPowerWheelchair", param);

            stamper.getAcroFields().setField("dateOfHomeAssessment", formatter.format(new Date()));

            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", formatter.format(new Date()));

            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());


            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            pdfReader.close();
            destination.delete();
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ServiceOutcome<PatientAssessmentFormInput> receivePatientAssessmentFileUserData(PatientAssessmentFormInput patientAssessmentFormInput) throws IOException{
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(Long.valueOf(patientAssessmentFormInput.getDeliveryTicketId()));
        return operationOnPatientAssessmentDeliveryDocuments(patientAssessmentFormInput, deliveryTicket.get());
    }

    public ServiceOutcome<PatientAssessmentFormInput> operationOnPatientAssessmentDeliveryDocuments(PatientAssessmentFormInput patientAssessmentFormInput, DeliveryTicket deliveryTicket) {
        ServiceOutcome<PatientAssessmentFormInput> serviceOutcome = new ServiceOutcome<>();
        HcpcsDocType hcpcsDocType = hcpcsDocTypeRepository.getHcpcsDocTypeOnFormName(patientAssessmentFormInput.getFormName());
        if(hcpcsDocType != null){
            ObjectMapper mapper = new ObjectMapper();
            String formJsonData = null;
            try {
                if(deliveryTicket.getDeliveryTicketId() != null && patientAssessmentFormInput.getFormName()!= null){
                    patientAssessmentFormInput = patientHomeAssessmentFormCommonData(deliveryTicket, patientAssessmentFormInput);
                }
                formJsonData = mapper.writeValueAsString(patientAssessmentFormInput);
            } catch (Exception exception) {
                log.error(String.valueOf(exception));
            }
            System.out.println("====JSON FORMATTED DATA=====>"+formJsonData);
            DeliveryDocumentsDTO deliveryDocumentsDTO = oprationOnDeliveryDocuments(deliveryTicket, hcpcsDocType, formJsonData);
            if(deliveryDocumentsDTO != null){
                if(deliveryDocumentsDTO != null)
                    System.out.println("==============Saved==============="+formJsonData);
                else
                    System.out.println("==============Not Saved==============="+formJsonData);
            }
        }
        serviceOutcome.setData(patientAssessmentFormInput);
        serviceOutcome.setMessage("PDF created successfully.");
        serviceOutcome.setOutcome(true);

        return serviceOutcome;
    }

    public Boolean fillUserDataPatientAssessmentDeliveryDocuments(File source, File destination, DeliveryTicket deliveryTicket, PatientAssessmentFormInput patientAssessmentFormInput, DeliveryAssignment deliveryAssignment){
        Boolean status = false;
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(destination.getAbsolutePath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(source.getAbsolutePath()));

            stamper.getAcroFields().setField("companyName", deliveryTicket.getBranchName());
            stamper.getAcroFields().setField("streetAddress", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phoneNo", deliveryTicket.getPhone1());
            stamper.getAcroFields().setField("fax", deliveryTicket.getBranchFax());

            stamper.getAcroFields().setField("patientName", CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' '));
            stamper.getAcroFields().setField("patientId", deliveryTicket.getPatientIdNo());
            stamper.getAcroFields().setField("address", deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
            stamper.getAcroFields().setField("account", "");
            stamper.getAcroFields().setField("p_cityStateZip", CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
            stamper.getAcroFields().setField("phone", deliveryTicket.getPhone1());

            String param = "";
            stamper.getAcroFields().setField("name1", patientAssessmentFormInput.getAlternateContacts().getName1());
            stamper.getAcroFields().setField("phone1", patientAssessmentFormInput.getAlternateContacts().getPhone1());
            stamper.getAcroFields().setField("relationship1", patientAssessmentFormInput.getAlternateContacts().getRelationship1());
            stamper.getAcroFields().setField("name2", patientAssessmentFormInput.getAlternateContacts().getName2());
            stamper.getAcroFields().setField("phone2", patientAssessmentFormInput.getAlternateContacts().getPhone2());
            stamper.getAcroFields().setField("relationship2", patientAssessmentFormInput.getAlternateContacts().getRelationship2());

            stamper.getAcroFields().setField("caregiverMaintainNa", param);

            stamper.getAcroFields().setField("comments", patientAssessmentFormInput.getComments());

            stamper.getAcroFields().setField("patientRepresentativeName", deliveryTicket.getDeliveryAcceptedBy());
            stamper.getAcroFields().setField("patientRelationship", deliveryTicket.getRelationshipWithPatient());
            stamper.getAcroFields().setField("reasonNotToSign", "");
            stamper.getAcroFields().setField("date", formatter.format(new Date()));
            stamper.getAcroFields().setField("companyRepresentativeName", deliveryAssignment.getAgentFirstName()+" "+deliveryAssignment.getAgentLastName());

            stamper.close();
            status = true;
        }
        catch (Exception e){
            status = false;
            pdfReader.close();
            destination.delete();
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public ServiceOutcome<List<DeliveryTicketDTO>> getDeliveryTicketsOnSalesOrder(Long salesOrderId){
        List<DeliveryTicketDTO> deliveryTicketDTO = new ArrayList<DeliveryTicketDTO>();
        try {
            List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.getDeliveryTicketsOnSalesOrder(salesOrderId);
            if (deliveryTicketList.size() > 0) {
                deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicketList);
                return new ServiceOutcome<>(deliveryTicketDTO, true, "Delivery ticket data fetched successfully!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(deliveryTicketDTO, false, "Failure to fetch delivery ticket data!");
    }

    @Override
    public ServiceOutcome<List<DeliveryDocumentResponse>> getDeliveryPODDocuments(Long deliveryTicketId){
        List<DeliveryDocumentsDTO> deliveryDocumentDTO = new ArrayList<>();
        List<DeliveryDocumentResponse> deliveryDocumentResponses = new ArrayList<>();
        try{
            List<DeliveryDocuments> deliveryDocuments = deliveryDocumentsRepository.getDeliveryPODDocumentsOnDeliveryTicket(deliveryTicketId);
            if(deliveryDocuments.size() > 0){
                deliveryDocumentDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);
                deliveryDocumentDTO.stream().forEach(deliveryDocument -> {
                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/"+deliveryDocument.getDocumentPatientFilePath());
                    DeliveryDocumentResponse deliveryDocumentResponse = operationOnDeliveryDocumentSignedFileResponse(deliveryDocument, source, "deliveryDocumentFiles");
                    deliveryDocumentResponses.add(deliveryDocumentResponse);
                });
                return new ServiceOutcome<>(deliveryDocumentResponses, true, "Delivery POD data fetched successfully!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(deliveryDocumentResponses, false, "Failure to get POD delivery documents!");
    }

    @Override
    public ServiceOutcome<List<DeliveryDocumentsForm>> getDeliveryDocuments(Long deliveryTicketId){
        List<DeliveryDocumentsDTO> deliveryDocumentDTO;
        List<DeliveryDocumentsForm> deliveryDocumentForms = new ArrayList<>();
        try{
            List<DeliveryDocuments> deliveryDocuments = deliveryDocumentsRepository.getDeliveryPODDocumentsOnDeliveryTicket(deliveryTicketId);
            if(deliveryDocuments.size() > 0){
                deliveryDocumentDTO = deliveryDocumentsMapper.toDto(deliveryDocuments);
                deliveryDocumentDTO.stream().forEach(deliveryDocument -> {
                    File source = new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/"+deliveryDocument.getDocumentPatientFilePath());
                    DeliveryDocumentsForm deliveryDocumentsForm = operationOnDeliveryDocumentsForm(deliveryDocument, source);
                    deliveryDocumentForms.add(deliveryDocumentsForm);
                });
                return new ServiceOutcome<>(deliveryDocumentForms, true, "Delivery documents fetched successfully!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(deliveryDocumentForms, false, "Failure to fetch delivery documents!");
    }

    public DeliveryDocumentsForm operationOnDeliveryDocumentsForm(DeliveryDocumentsDTO deliveryDocumentsDTO, File destination){
        DeliveryDocumentsForm documentsForm = new DeliveryDocumentsForm();
        documentsForm.setDeliveryTicketId(deliveryDocumentsDTO.getDeliveryTicketId());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadDeliveryDocumentFiles/")
            .path(destination.getName())
            .toUriString();

        documentsForm.setDocumentPatientFilePath(fileDownloadUri);
        documentsForm.setDocumentPatientFile(deliveryDocumentsDTO.getDocumentPatientFilePath());
        documentsForm.setDeliveryDocumentsUuid(deliveryDocumentsDTO.getDeliveryDocumentsUuid());
        return documentsForm;
    }

    @Override
    public ServiceOutcome<String> getDeliveryDocumentFormData(Long deliveryTicketId, String formName){
        List<DeliveryItems> deliveryItemsList;
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(deliveryTicketId);
        if(deliveryTicket.isPresent()) {
            Optional<HcpcsDocType> hcpcsDocType = Optional.ofNullable(hcpcsDocTypeRepository.getHcpcsDocTypeOnFormName(formName));
            if (hcpcsDocType.isPresent()) {
                deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
                if(hcpcsDocType.get().getFormName().equals("DMECertificateForm") && deliveryItemsList.size() > 0) {
                    DMECertificationFormInput dmeCertificationFormInput = generateDMECertificateForm(deliveryTicket.get(), deliveryItemsList);
                    if(dmeCertificationFormInput != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String response = null;
                        try {
                            response = mapper.writeValueAsString(dmeCertificationFormInput);
                        } catch (Exception exception) {
                            log.error(String.valueOf(exception));
                        }
                        System.out.println("====JSON FORMATTED DATA1=====>"+response);
                        return new ServiceOutcome<>(response, true, "Successfully generate delivery document form json!");
                    }
                }
                else if(hcpcsDocType.get().getFormName().equals("PatientHomeAssessmentForm") && deliveryItemsList.size() > 0) {
                    PatientHomeAssessmentFormInput patientHomeAssessmentFormInput = generatePatientHomeAssessmentFormData(deliveryTicket.get(), deliveryItemsList);
                    if(patientHomeAssessmentFormInput != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String response = null;
                        try {
                            response = mapper.writeValueAsString(patientHomeAssessmentFormInput);
                        } catch (Exception exception) {
                            log.error(String.valueOf(exception));
                        }
                        System.out.println("====JSON FORMATTED DATA2=====>"+response);
                        return new ServiceOutcome<>(response, true, "Successfully generate delivery document form json!");
                    }
                }
                else if(hcpcsDocType.get().getFormName().equals("PatientAssessmentForm")) {
                    PatientAssessmentFormInput patientAssessmentFormInput = generatePatientAssessmentFormData(deliveryTicket.get());
                    if(patientAssessmentFormInput != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String response = null;
                        try {
                            response = mapper.writeValueAsString(patientAssessmentFormInput);
                        } catch (Exception exception) {
                            log.error(String.valueOf(exception));
                        }
                        System.out.println("====JSON FORMATTED DATA3=====>"+response);
                        return new ServiceOutcome<>(response, true, "Successfully generate delivery document form json!");
                    }
                }
                else if(hcpcsDocType.get().getFormName().equals("DeliveryReceipt") && deliveryItemsList.size() > 0) {
                    DeliveryReceiptFormInput deliveryReceiptFormInput = generatePatientDeliveryReceiptFormData(deliveryTicket.get(), deliveryItemsList);
                    if(deliveryReceiptFormInput != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        String response = null;
                        try {
                            response = mapper.writeValueAsString(deliveryReceiptFormInput);
                        } catch (Exception exception) {
                            log.error(String.valueOf(exception));
                        }
                        System.out.println("====JSON FORMATTED DATA4=====>"+response);
                        return new ServiceOutcome<>(response, true, "Successfully generate delivery document form json!");
                    }
                }
            }
            else
                return new ServiceOutcome<>(null, false, "Form name does not exists!");
        }
        else
            return new ServiceOutcome<>(null, false, "Delivery ticket should not be empty!");
        return new ServiceOutcome<>(null, false, "Failed to generate delivery document form data!");
    }

    public DMECertificationFormInput generateDMECertificateForm(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItemsList){
        DMECertificationFormInput dmeCertificationFormInput = null;
        try{
            dmeCertificationFormInput = new DMECertificationFormInput();
            dmeCertificationFormInput.setFormName("DMECertificateForm");
            dmeCertificationFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
            dmeCertificationFormInput.setCompanyName(deliveryTicket.getBranchName());
            String patientFName = deliveryTicket.getPatientFirstName()!= null ? deliveryTicket.getPatientFirstName() : "";
            String patientMName = deliveryTicket.getPatientMiddleName()!= null ? deliveryTicket.getPatientMiddleName() : "";
            String patientLName = deliveryTicket.getPatientLastName()!= null ? deliveryTicket.getPatientLastName() : "";
            String patientFullName = patientFName+" "+patientMName+" "+patientLName;
            dmeCertificationFormInput.setName(patientFullName);
            dmeCertificationFormInput.setPatientId(deliveryTicket.getPatientIdNo());
            String deliveryAddress1 = deliveryTicket.getDeliveryAddress1()!= null ? deliveryTicket.getDeliveryAddress1() : "";
            String deliveryAddress2 = deliveryTicket.getDeliveryAddress2()!= null ? deliveryTicket.getDeliveryAddress2() : "";
            dmeCertificationFormInput.setStreetAddress(deliveryAddress1+" "+deliveryAddress2);
            dmeCertificationFormInput.setCity(deliveryTicket.getDeliveryCity());
            dmeCertificationFormInput.setTelephoneNo(deliveryTicket.getPhone1());
            dmeCertificationFormInput.setProviderName(deliveryTicket.getBranchName());
            dmeCertificationFormInput.setPan("");
            dmeCertificationFormInput.setNPIAPI(deliveryTicket.getBranchNpi());
            dmeCertificationFormInput.setTpi("");
            dmeCertificationFormInput.setCurrentdate(formatter.format(new Date()));
            dmeCertificationFormInput.setCurrentdate_1(formatter.format(new Date()));
            dmeCertificationFormInput.setPatientSignature("");
            dmeCertificationFormInput.setPatientRepresentativeName("");
            dmeCertificationFormInput.setPatientRelationship("");
            dmeCertificationFormInput.setReasonNotToSign("");
            dmeCertificationFormInput.setDate(formatter.format(new Date()));
            dmeCertificationFormInput.setSignatureCompRepresentative("");
            dmeCertificationFormInput.setNameCompRepresentative("");
            dmeCertificationFormInput.setDateOfService(formatter.format(new Date()));

            List<DeliveryItemDetails> deliveryItemDetailsList = new ArrayList<>();
            var count = new Object(){int ctx = 0;};
            deliveryItemsList.stream().forEach(item -> {
                DeliveryItemDetails deliveryItemDetails = new DeliveryItemDetails();
                try {
                    deliveryItemDetails.setProccode(item.getHcpcsCode());
                    deliveryItemDetails.setDescription(item.getItemDescription());
                    deliveryItemDetails.setSerial("");
                    count.ctx++;
                    deliveryItemDetailsList.add(deliveryItemDetails);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
            dmeCertificationFormInput.setDeliveryItemDetailsList(deliveryItemDetailsList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dmeCertificationFormInput;
    }

    public PatientHomeAssessmentFormInput generatePatientHomeAssessmentFormData(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItemsList){
        PatientHomeAssessmentFormInput patientHomeAssessmentFormInput = new PatientHomeAssessmentFormInput();
        patientHomeAssessmentFormInput.setFormName("PatientHomeAssessmentForm");
        patientHomeAssessmentFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
        patientHomeAssessmentFormInput.setCompanyName(deliveryTicket.getBranchName());
        String branchAddr1 = deliveryTicket.getBranchAddressLine1()!= null ? deliveryTicket.getBranchAddressLine1() : "";
        String branchAddr2 = deliveryTicket.getBranchAddressLine2()!= null ? deliveryTicket.getBranchAddressLine2() : "";
        String branchCity = deliveryTicket.getBranchCity()!= null ? deliveryTicket.getBranchCity() : "";
        String branchState = deliveryTicket.getBranchState()!= null ? deliveryTicket.getBranchState() : "";
        String branchZip = deliveryTicket.getBranchZipCode()!= null ? deliveryTicket.getBranchZipCode() : "";
        patientHomeAssessmentFormInput.setStreetAddress(branchAddr1+", "+branchAddr2);
        patientHomeAssessmentFormInput.setCityStateZip(branchCity+", "+branchState+", "+branchZip);
        patientHomeAssessmentFormInput.setPhoneNo(deliveryTicket.getBranchContactNo1());
        patientHomeAssessmentFormInput.setFax(deliveryTicket.getBranchFax());
        patientHomeAssessmentFormInput.setPatientName(CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' '));
        patientHomeAssessmentFormInput.setAddress(deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
        patientHomeAssessmentFormInput.setpCityStateZip(CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
        patientHomeAssessmentFormInput.setPhone(deliveryTicket.getPhone1());
        patientHomeAssessmentFormInput.setPatientId(deliveryTicket.getPatientIdNo());
        patientHomeAssessmentFormInput.setAccount("");
        patientHomeAssessmentFormInput.setTypeOfMobilityCane(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityCrutches(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityWalker(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityManualwheelchair(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityPovscooter(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityPowerwheelchair(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityOtherchk(false);
        patientHomeAssessmentFormInput.setTypeOfMobilityOther("");
        patientHomeAssessmentFormInput.setOthermedicalequipment(false);
        patientHomeAssessmentFormInput.setOthermedicalequipmentValue("AAAA");
        patientHomeAssessmentFormInput.setProviderby("");
        patientHomeAssessmentFormInput.setTypeOfHome("");
        patientHomeAssessmentFormInput.setHandicapAccessible(false);
        patientHomeAssessmentFormInput.setHandicapAccessibleValue("");
        patientHomeAssessmentFormInput.setRampsElevators(false);
        patientHomeAssessmentFormInput.setRampsElevatorsValue("");
        patientHomeAssessmentFormInput.setOthers2(false);
        patientHomeAssessmentFormInput.setOther2value("");
        patientHomeAssessmentFormInput.setEquipmentTrials("");
        patientHomeAssessmentFormInput.setCarpetThrowRugs("");
        patientHomeAssessmentFormInput.setLooseUnevenFloors("");
        patientHomeAssessmentFormInput.setStairsSteps("");
        patientHomeAssessmentFormInput.setWcRampsInsideOrOutsideTheHome("");
        patientHomeAssessmentFormInput.setExplainAccessOptions("");
        patientHomeAssessmentFormInput.setExplainAccessOptionsValue("");
        patientHomeAssessmentFormInput.setOthersSection("");
        patientHomeAssessmentFormInput.setOthersSectionValue("");
        patientHomeAssessmentFormInput.setCaneCrutchesWalkerManualWheelchair("");
        patientHomeAssessmentFormInput.setPovScooter("");
        patientHomeAssessmentFormInput.setPowerWheelchair("");
        patientHomeAssessmentFormInput.setBathroomFacilities("");
        patientHomeAssessmentFormInput.setRoomAccess("");
        patientHomeAssessmentFormInput.setRoomAccessValue("");
        patientHomeAssessmentFormInput.setEntryDoors("");
        patientHomeAssessmentFormInput.setEntryDoorsSl("");
        patientHomeAssessmentFormInput.setEntryDoorsValue("");
        patientHomeAssessmentFormInput.setBedRoom("");
        patientHomeAssessmentFormInput.setBedRoomSl("");
        patientHomeAssessmentFormInput.setBedRoomValue("");
        patientHomeAssessmentFormInput.setKitchen("");
        patientHomeAssessmentFormInput.setKitchenSl("");
        patientHomeAssessmentFormInput.setKitchenValue("");
        patientHomeAssessmentFormInput.setBathroom("");
        patientHomeAssessmentFormInput.setBathroomSl("");
        patientHomeAssessmentFormInput.setBathroomValue("");
        patientHomeAssessmentFormInput.setHallways("");
        patientHomeAssessmentFormInput.setHallwaysSl("");
        patientHomeAssessmentFormInput.setHallwaysValue("");
        patientHomeAssessmentFormInput.setOtherRooms("");
        patientHomeAssessmentFormInput.setOtherRoomsSl("");
        patientHomeAssessmentFormInput.setOtherRoomsValue("");
        patientHomeAssessmentFormInput.setPatientRepresentativeName("");

        patientHomeAssessmentFormInput.setAssessmentPerformedVerballyCheck(false);
        patientHomeAssessmentFormInput.setCompletedPreliminaryAssessmentCheck(false);
        patientHomeAssessmentFormInput.setApplyCane(false);
        patientHomeAssessmentFormInput.setApplyCrutches(false);
        patientHomeAssessmentFormInput.setApplyWalker(false);
        patientHomeAssessmentFormInput.setApplyManualWheelchair(false);
        patientHomeAssessmentFormInput.setApplyPovScooter(false);
        patientHomeAssessmentFormInput.setApplyPowerWheelchair(false);
        patientHomeAssessmentFormInput.setDateOfHomeAssessment(formatter.format(new Date()));
        patientHomeAssessmentFormInput.setSignaturePatientRepresentative("");
        patientHomeAssessmentFormInput.setPatientRelationship("");
        patientHomeAssessmentFormInput.setReasonNotToSign("");
        patientHomeAssessmentFormInput.setDate(formatter.format(new Date()));
        patientHomeAssessmentFormInput.setCompanyRepresentativeSign("");
        patientHomeAssessmentFormInput.setCompanyRepresentativeName("");
        return patientHomeAssessmentFormInput;
    }

    public PatientAssessmentFormInput generatePatientAssessmentFormData(DeliveryTicket deliveryTicket){
        PatientAssessmentFormInput patientAssessmentFormInput = new PatientAssessmentFormInput();
        patientAssessmentFormInput.setFormName("PatientAssessmentForm");
        patientAssessmentFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
        patientAssessmentFormInput.setCompanyName(deliveryTicket.getBranchName());
        String branchAddr1 = deliveryTicket.getBranchAddressLine1()!= null ? deliveryTicket.getBranchAddressLine1() : "";
        String branchAddr2 = deliveryTicket.getBranchAddressLine2()!= null ? deliveryTicket.getBranchAddressLine2() : "";
        String branchCity = deliveryTicket.getBranchCity()!= null ? deliveryTicket.getBranchCity() : "";
        String branchState = deliveryTicket.getBranchState()!= null ? deliveryTicket.getBranchState() : "";
        String branchZip = deliveryTicket.getBranchZipCode()!= null ? deliveryTicket.getBranchZipCode() : "";
        patientAssessmentFormInput.setStreetAddress(branchAddr1+", "+branchAddr2);
        patientAssessmentFormInput.setCityStateZip(branchCity+", "+branchState+", "+branchZip);
        patientAssessmentFormInput.setPhoneNo(deliveryTicket.getBranchContactNo1());
        patientAssessmentFormInput.setFax(deliveryTicket.getBranchFax());
        patientAssessmentFormInput.setPatientName(CaseUtils.toCamelCase(deliveryTicket.getPatientFirstName(), true, ' ')+" "+CaseUtils.toCamelCase(deliveryTicket.getPatientLastName(), true, ' '));
        patientAssessmentFormInput.setAddress(deliveryTicket.getDeliveryAddress1()+", "+deliveryTicket.getDeliveryAddress2());
        patientAssessmentFormInput.setpCityStateZip(CaseUtils.toCamelCase(deliveryTicket.getDeliveryCity(), true, ' ')+", "+deliveryTicket.getDeliveryState()+" "+deliveryTicket.getDeliveryZip());
        patientAssessmentFormInput.setPhone(deliveryTicket.getPhone1());
        patientAssessmentFormInput.setPatientId(deliveryTicket.getPatientIdNo());
        patientAssessmentFormInput.setAccount("");

        AlternateContacts alternateContacts = new AlternateContacts();
        alternateContacts.setName1(deliveryTicket.getDeliveryAcceptedBy());
        alternateContacts.setName2("");
        alternateContacts.setPhone1(deliveryTicket.getDeliveryAcceptedByContactNo());
        alternateContacts.setPhone2("");
        alternateContacts.setRelationship1(deliveryTicket.getRelationshipWithPatient());
        alternateContacts.setRelationship2("");
        patientAssessmentFormInput.setAlternateContacts(alternateContacts);

        patientAssessmentFormInput.setPatientVision("");
        patientAssessmentFormInput.setPatientHearing("");
        patientAssessmentFormInput.setPatientSpeech("");
        patientAssessmentFormInput.setPatientAmbulatory("");
        patientAssessmentFormInput.setAlertUnderstand("");
        patientAssessmentFormInput.setConfused("");
        patientAssessmentFormInput.setDementia("");
        patientAssessmentFormInput.setPatientMobile("");
        patientAssessmentFormInput.setPatientBedRidden("");
        patientAssessmentFormInput.setPatientFallRisk("");
        patientAssessmentFormInput.setPatientNutritional("");
        patientAssessmentFormInput.setCaregiverWilling("");
        patientAssessmentFormInput.setCaregiverAble("");
        patientAssessmentFormInput.setCaregiverUnderstands("");
        patientAssessmentFormInput.setCaregiverMaintain("");

        patientAssessmentFormInput.setComments("");
        patientAssessmentFormInput.setSignaturePatientRepresentative("");
        patientAssessmentFormInput.setPatientRelationship("");
        patientAssessmentFormInput.setReasonNotToSign("");
        patientAssessmentFormInput.setDate(formatter.format(new Date()));
        patientAssessmentFormInput.setSignaturePatientRepresentative("");
        patientAssessmentFormInput.setCompanyRepresentativeName("");
        return patientAssessmentFormInput;
    }

    public DeliveryReceiptFormInput generatePatientDeliveryReceiptFormData(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItemsList){
        DeliveryReceiptFormInput deliveryReceiptFormInput = new DeliveryReceiptFormInput();
        deliveryReceiptFormInput.setFormName("DeliveryReceipt");
        deliveryReceiptFormInput.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId().toString());
        deliveryReceiptFormInput.setBranchName(deliveryTicket.getBranchName());
        deliveryReceiptFormInput.setInvLocation(deliveryTicket.getInventoryLocationName());
        if(deliveryTicket.getDeliveryDate() != null)
            deliveryReceiptFormInput.setDate(DateUtilities.dateStringConverter(deliveryTicket.getDeliveryDate().toString()));
        else
            deliveryReceiptFormInput.setDate("NA");
        deliveryReceiptFormInput.setCsr("");
        deliveryReceiptFormInput.setOrder(deliveryTicket.getDeliveryTicketDocumentNo());
        deliveryReceiptFormInput.setPatientId(deliveryTicket.getPatientIdNo());
        deliveryReceiptFormInput.setCustomer("");
        deliveryReceiptFormInput.setAccount("");
        deliveryReceiptFormInput.setDob(deliveryTicket.getAgeAsOnDate().toString());
        if(deliveryTicket.getGender().equalsIgnoreCase("M"))
            deliveryReceiptFormInput.setGender("Male");
        else if(deliveryTicket.getGender().equalsIgnoreCase("F"))
            deliveryReceiptFormInput.setGender("Female");
        else
            deliveryReceiptFormInput.setGender(deliveryTicket.getGender());

        BillToAddress billToAddress = new BillToAddress();
        billToAddress.setStreetAddress1(deliveryTicket.getBillingAddressLine1());
        billToAddress.setStreetAddress2(deliveryTicket.getBillingAddressLine2());
        String billingCity = deliveryTicket.getBillingCity()!= null ? deliveryTicket.getBillingCity() : "";
        String billingState = deliveryTicket.getBillingState()!= null ? deliveryTicket.getBillingState() : "";
        String billingZip = deliveryTicket.getBillingZip()!= null ? deliveryTicket.getBillingZip() : "";
        billToAddress.setCityStateZip(billingCity+", "+billingState+", "+billingZip);
        billToAddress.setPhoneNo(deliveryTicket.getPhone1());
        deliveryReceiptFormInput.setBillToAddress(billToAddress);

        String deliveryAddress1 = deliveryTicket.getDeliveryAddress1()!= null ? deliveryTicket.getDeliveryAddress1() : "";
        String deliveryAddress2 = deliveryTicket.getDeliveryAddress2()!= null ? deliveryTicket.getDeliveryAddress2() : "";
        String deliveryCity = deliveryTicket.getDeliveryCity()!= null ? deliveryTicket.getDeliveryCity() : "";
        String deliveryState = deliveryTicket.getDeliveryState()!= null ? deliveryTicket.getDeliveryState() : "";
        String deliveryZip = deliveryTicket.getDeliveryZip()!= null ? deliveryTicket.getDeliveryZip() : "";

        DeliveredToAddress deliveredToAddress = new DeliveredToAddress();
        deliveredToAddress.setStreetAddress1(deliveryAddress1);
        deliveredToAddress.setStreetAddress2(deliveryAddress2);
        deliveredToAddress.setCityStateZip(deliveryCity+", "+deliveryState+", "+deliveryZip);
        deliveredToAddress.setPhoneNo(deliveryTicket.getPhone1());
        deliveryReceiptFormInput.setDeliveredToAddress(deliveredToAddress);

        deliveryReceiptFormInput.setInsurance(deliveryTicket.getPrimaryInsurerName());
        deliveryReceiptFormInput.setHippaSignatureOnFile("No");

        List<ItemOrderedDetailed> itemOrderedDetailedList = new ArrayList<>();
        deliveryItemsList.stream().forEach(order -> {
            ItemOrderedDetailed itemOrderedDetailed = new ItemOrderedDetailed();
            itemOrderedDetailed.setOrderQty(order.getItemQuantity().toString());
            itemOrderedDetailed.setDeleteQty(order.getItemQuantity().toString());
            itemOrderedDetailed.setType(order.getSoSaleType().toUpperCase());
            itemOrderedDetailed.setItemName(order.getItemDescription());
            if(order.getChargedAmount() != null)
                itemOrderedDetailed.setChargedAmount(order.getChargedAmount());
            else itemOrderedDetailed.setChargedAmount(0.00);
            if(order.getAllowedAmount() != null)
                itemOrderedDetailed.setAllowedAmount(order.getAllowedAmount());
            else itemOrderedDetailed.setAllowedAmount(0.00);
            itemOrderedDetailed.setTax(0.0);
            itemOrderedDetailed.setCoPay(0.0);
            itemOrderedDetailedList.add(itemOrderedDetailed);
        });
        deliveryReceiptFormInput.setItemOrderedDetailedList(itemOrderedDetailedList);
        return deliveryReceiptFormInput;
    }

    public CommonDocumentResponse processCommonStubsWithDocument(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData) throws IOException, WriterException, com.google.zxing.WriterException {
        File patientSignatureFile = null, techSignatureFile = null;
        DeliveryDocumentResponse deliveryDocumentResponse = new DeliveryDocumentResponse();
        CommonDocumentResponse commonDocumentResponse = new CommonDocumentResponse();
        new CommonPDFStubs().generateQRCode(deliveryTicket.getDeliveryTicketId().toString(), fileUploadConfigProperties.getTempDocumentProperties().getLocation());

        DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.getDeliveryDocumentSignatureOnDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        if(deliveryDocumentsSignature != null) {
            if(commonDeliveryInputData.getSignatureType().equals("caregiver")) {
                patientSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getCaregiverSignatureFile());
                deliveryDocumentResponse.setIsCaregiverSigned("Yes");
            }
            if(commonDeliveryInputData.getSignatureType().equals("patient")) {
                patientSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getPatientSignatureFile());
                deliveryDocumentResponse.setIsPatientSigned("Yes");
            }
            techSignatureFile = new File(fileUploadConfigProperties.getSignatureProperties().getLocation() + "/" + deliveryDocumentsSignature.getDriverAgentSignatureFile());
            deliveryDocumentResponse.setIsDeliveryAgentSigned("Yes");
        }
        commonDocumentResponse.setDeliveryDocumentResponse(deliveryDocumentResponse);
        commonDocumentResponse.setTechSignatureFile(techSignatureFile);
        commonDocumentResponse.setPatientSignatureFile(patientSignatureFile);
        commonDocumentResponse.setCommonDeliveryInputData(commonDeliveryInputData);
        return commonDocumentResponse;
    }

    @Override
    public ServiceOutcome<DeliveryDocumentResponse> prepareDeliveryDocumentReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData) throws Exception {
        String fileName = "Delivery_Ticket_Receipt_"+deliveryTicket.getDeliveryTicketId()+".pdf";
        CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);
        Document document = new Document(PageSize.A4, 35, 35, 50, 65);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));
        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitle());
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyAddressDetails(deliveryTicket));
        document.add(new Paragraph("\n"));
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentBillToDeliverToAddress(deliveryTicket));
        document.add(new Paragraph("\n\n"));
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentInsuranceHIPAAFileInfo(deliveryTicket));
        document.add(new Paragraph("\n\n"));

        List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.getDeliveryTicketId());
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentProductDetailInfo(deliveryItemsList));
        document.add(new Paragraph("\n"));

        DeliveryDocumentTableBuilder.populateDataInDeliveryDocumentDocs(document);

        document.add(new Paragraph("\n\n\n"));
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentSignatureDate(commonDocumentResponse.getPatientSignatureFile(), commonDocumentResponse.getTechSignatureFile(), formatter.format(new Date())));
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentSignatureDateHeader());
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentSignatureDateDetails(deliveryTicket));
        document.add(new Paragraph("\n\n"));
        document.add(DeliveryDocumentTableBuilder.createDeliveryDocumentPatientInformation(commonDeliveryInputData));

        document.close();

        commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
        commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
        System.out.println("PDF created successfully.");
        return new ServiceOutcome<>(commonDocumentResponse.getDeliveryDocumentResponse(), true, "PDF created successfully.");
    }

    public ServiceOutcome<DeliveryDocumentResponse> preparePatientRightsResponsibilityDocumentReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData) throws Exception {
        String fileName = "Patient_Rights_Responsibility_"+deliveryTicket.getDeliveryTicketId()+"_"+deliveryTicket.getSoNo()+".pdf";
        CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

        Document document = new Document(PageSize.A4, 35, 35, 50, 65);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));
        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        DeliveryDocumentTableBuilder.populateDataInPatientRightsResponsibilityDocs(document, deliveryTicket);
        prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);

        document.close();
        commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
        commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
        log.info("PDF created successfully.");
        return new ServiceOutcome<>(commonDocumentResponse.getDeliveryDocumentResponse(), true, "PDF created successfully.");
    }

    void prepareCommonDeliveryDocumentContents(Document document, CommonDocumentResponse commonDocumentResponse) throws Exception {
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityPatientHeading("Patient/Caregiver"));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityPatientBody(commonDocumentResponse));

        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityCompanyHeading("Company Representative"));
        document.add(DeliveryDocumentTableBuilder.createPatientRightsResponsibilityCompanyBody(commonDocumentResponse));

        document.add(new Paragraph("\n\n"));
    }

    @Override
    public Optional<DeliveryTicket> getDeliveryTicketOnDeliveryTicket(Long deliveryTicketId){
        return deliveryTicketRepository.findById(deliveryTicketId);
    }

    public ServiceOutcome<DeliveryDocumentResponse> prepareDMECertificationReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData, DeliveryDocuments deliveryDocuments) throws Exception {
        log.info("===Itext Pdf DME Certification Coding Implementation Started===");
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = new ServiceOutcome<>();
        Boolean status = false;
        String message = "";
        String responseJsonData = deliveryDocuments.getResponseJsonData();
        if(responseJsonData != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            DMECertificationFormInput responseObject = objectMapper.readValue(responseJsonData, DMECertificationFormInput.class);

            log.info("====DME responseObject====" + responseObject);

            String fileName = "DME_Certification_" + deliveryTicket.getDeliveryTicketId() + "_" + deliveryTicket.getSoNo() + ".pdf";
            CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

            Document document = new Document(PageSize.A4, 35, 35, 25, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));

            DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation() + "/" + deliveryTicket.getDeliveryTicketId() + ".png");
            writer.setPageEvent(event);

            document.open();
            DmeCertificationDocumentTableBuilder.populateDataInDMEDocument(document, responseObject);
            prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);
            document.close();

            commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
            commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);

            status = true;
            message = "PDF created successfully.";
            serviceOutcome.setData(commonDocumentResponse.getDeliveryDocumentResponse());
            log.info("PDF created successfully.");
        }
        else{
            status = false;
            message = "Failed to generate the document.";
        }
        return new ServiceOutcome<>(serviceOutcome.getData(), status, message);
    }

    public ServiceOutcome<DeliveryDocumentResponse> prepareHomeAssessmentDocumentReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData, DeliveryDocuments deliveryDocuments) throws Exception {
        log.info("===Itext Pdf Coding Implementation Started===");
        CommonDocumentResponse commonDocumentResponse = null;
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = new ServiceOutcome<>();
        Boolean status = false;
        String message = "";
        String responseJsonData = deliveryDocuments.getResponseJsonData();
        if(responseJsonData != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            PatientHomeAssessmentFormInputs responseObject = objectMapper.readValue(responseJsonData, PatientHomeAssessmentFormInputs.class);

            String fileName = "Home_Assessment_" + deliveryTicket.getDeliveryTicketId() + "_" + deliveryTicket.getSoNo() + ".pdf";
            commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

            Document document = new Document(PageSize.A4, 35, 35, 50, 65);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));
            DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation() + "/" + deliveryTicket.getDeliveryTicketId() + ".png");
            writer.setPageEvent(event);

            PdfFormField radioGroup = PdfFormField.createRadioButton(writer, true);
            radioGroup.setFieldName("patientAssessment");

            //Initialize Radio Button
            PdfFormField radioGroup1 = PdfFormField.createRadioButton(writer, true);
            radioGroup1.setFieldName("patientHomeAssessment");

            //Initialize Check Box
            PdfFormField checkboxGroup = PdfFormField.createCheckBox(writer);
            checkboxGroup.setFieldName("checkboxForPdf");

            document.open();

            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentMainBodyTitle(responseObject));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentMainBodyContactTitle(responseObject));
            document.add(new Paragraph("\n"));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentBodyHeaderTitle("Patient Home Assessment for Mobility Assistive Equipment"));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentBodyTableHeader(responseObject));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Type of Mobility Assistive Equipment:", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxInTable(radioGroup, "", "checkbox", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxAndInputBoxRowInTable(radioGroup, "", "checkbox", responseObject));
            document.add(new Paragraph("\n"));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxAndTextBoxInTable2(radioGroup, "", "checkbox", responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Type of Home", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createMultipleRadioButtonInTable(radioGroup, "", "", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createRadioAndCheckBoxInTable(radioGroup, "", "both", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxAndInputboxInTable(radioGroup, responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Equipment Trials:", Font.BOLD));
            document.add(new CommonPdfTableStubs().createTextboxInTable(responseObject.getEquipmentTrials()));
            document.add(new Paragraph("\n"));
            document.add(HomePatientAssessmentTableBuilder.createParagraphTitleTableBuilderInBody("Section 1"));
            document.add(new Paragraph("\n"));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButton3(radioGroup, "", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBox6(radioGroup, "", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBox7(radioGroup, "", "radioButton", responseObject));

            writer.addAnnotation(radioGroup);
            document.add(new Paragraph("\n"));
            // Move to the next page
            document.newPage();
            document.add(HomePatientAssessmentTableBuilder.createParagraphTitleTableBuilderInBody2("Section 2"));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButton4(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBox4(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Measurements: (Required for Power Mobility Devices)", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBox5(radioGroup1, "notNeeded", "radioButton"));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced1(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced2(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced3(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced4(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced5(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButtonAndInputBoxSpaced6(radioGroup1, "notNeeded", "radioButton", responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Supplier Attestation:", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxInTable2(radioGroup, "", "checkbox", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createCheckBoxInTable3(radioGroup, "", "checkbox", responseObject));
            document.add(HomePatientAssessmentTableBuilder.createParagraphTitleTableBuilderNormal("Date of Home Assessment: " + responseObject.getDateOfHomeAssessment()));

            writer.addAnnotation(radioGroup1);

            prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);

            document.close();
            commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
            commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
            status = true;
            message = "PDF created successfully.";
            serviceOutcome.setData(commonDocumentResponse.getDeliveryDocumentResponse());
            log.info("PDF created successfully.");
        }
        else{
            status = false;
            message = "Failed to generate the document.";
        }
        return new ServiceOutcome<>(serviceOutcome.getData(), status, message);
    }

    public ServiceOutcome<DeliveryDocumentResponse> preparePatientAssessmentDocumentReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData, DeliveryDocuments deliveryDocuments) throws Exception {
        System.out.println("===Patient Assessment Itext Pdf Coding Implementation Started===");
        String responseJsonData = deliveryDocuments.getResponseJsonData();
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = new ServiceOutcome<>();
        Boolean status;
        String message;
        ObjectMapper objectMapper = new ObjectMapper();
        if(responseJsonData != null) {
            PatientAssessmentFormInput responseObject = objectMapper.readValue(responseJsonData, PatientAssessmentFormInput.class);

            String fileName = "Patient_Assessment_" + deliveryTicket.getDeliveryTicketId() + "_" + deliveryTicket.getSoNo() + ".pdf";
            CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

            Document document = new Document(PageSize.A4, 35, 35, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));
            DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation() + "/" + deliveryTicket.getDeliveryTicketId() + ".png");
            writer.setPageEvent(event);

            //Initialize Radio Button
            PdfFormField radioGroup3 = PdfFormField.createRadioButton(writer, true);
            radioGroup3.setFieldName("patientAssessmentPdf");

            document.open();
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentPatientMainBodyTitle(responseObject));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentPatientMainBodyContactTitle(responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Patient\\Caregiver Assessment", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentPatientBodyTableHeader(responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Alternate Contact(s)", Font.BOLD));
            document.add(HomePatientAssessmentTableBuilder.createDeliveryDocumentTableContactDetails(responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Patient Assessment", Font.BOLD));

            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButton(radioGroup3, responseObject));
            document.add(new Paragraph("\n"));
            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Caregiver Assessment", Font.BOLD));  //createTitleTableHeaderBoldText
            document.add(HomePatientAssessmentTableBuilder.createTableForRadioButton2(radioGroup3, responseObject));
            writer.addAnnotation(radioGroup3);

            document.add(new Paragraph("\n"));

            // Move to the next page
            document.newPage();

            document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Comments:", Font.NORMAL));
            document.add(new CommonPdfTableStubs().createTextboxInTable(responseObject.getComments()));

            prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);

            document.close();
            commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
            commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
            status = true;
            message = "PDF created successfully.";
            serviceOutcome.setData(commonDocumentResponse.getDeliveryDocumentResponse());
            log.info("PDF created successfully.");
        }
        else{
            status = false;
            message = "Failed to generate the document.";
        }
        return new ServiceOutcome<>(serviceOutcome.getData(), status, message);
    }

    public DeliveryDocuments updateDeliveryDocuments(DeliveryDocuments deliveryDocuments, File source, ServiceOutcome<DeliveryDocumentResponse> outcome){
//        DeliveryDocumentsSignature deliveryDocumentsSignature = deliveryDocumentsSignatureRepository.getDeliveryDocumentSignatureOnDeliveryTicketId(deliveryDocuments.getDeliveryTicketId());
//        if(deliveryDocumentsSignature != null) {
//            if(commonDeliveryInputData.getSignatureType().equals("caregiver")) {
//                deliveryDocumentResponse.setIsCaregiverSigned("Yes");
//            }
//            if(commonDeliveryInputData.getSignatureType().equals("patient")) {
//                deliveryDocumentResponse.setIsPatientSigned("Yes");
//            }
//            deliveryDocumentResponse.setIsDeliveryAgentSigned("Yes");
//        }
        log.info("================Update Delivery Documents=================="+outcome.getData().getDeliveryTicketNo());
        log.info("================Update Delivery Documents=================="+source.getName());
        log.info("================Update Delivery Documents=================="+outcome.getData().getSignedDate());
        deliveryDocuments.setDocumentPatientFilePath(source.getName());
        deliveryDocuments.setIsPatientSigned(outcome.getData().getIsPatientSigned());
        deliveryDocuments.setIsCaregiverSigned(outcome.getData().getIsCaregiverSigned());
        deliveryDocuments.setSignedDate(outcome.getData().getSignedDate());
        deliveryDocuments.setIsDeliveryAgentSigned(outcome.getData().getIsDeliveryAgentSigned());
        deliveryDocuments = deliveryDocumentsRepository.save(deliveryDocuments);
        return deliveryDocuments;
    }

    public DeliveryDocumentsDTO builtDeliveryDocumentDTO(DeliveryDocuments updateDeliveryDocuments, DeliveryTicket deliveryTicket, HcpcsDocType hcpcsDocType, File source){
        DeliveryDocumentsDTO deliveryDocumentsDTO = new DeliveryDocumentsDTO();
        if(updateDeliveryDocuments != null)
            deliveryDocumentsDTO.setDeliveryDocId(updateDeliveryDocuments.getDeliveryDocId());
        else
            deliveryDocumentsDTO.setDeliveryDocId(null);
        deliveryDocumentsDTO.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        deliveryDocumentsDTO.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
        deliveryDocumentsDTO.setSoId(deliveryTicket.getSoId());
        deliveryDocumentsDTO.setSoNo(deliveryTicket.getSoNo());
        deliveryDocumentsDTO.setHcpcsDoctypeId(hcpcsDocType.getHcpcsDoctypeId());
        deliveryDocumentsDTO.setDocPatientName(deliveryTicket.getPatientFirstName() + " " + deliveryTicket.getPatientLastName());
        deliveryDocumentsDTO.setDocumentPatientFilePath(source.getName());
        deliveryDocumentsDTO.setStatus("Active");
//        deliveryDocumentsDTO.setIsDeliveryAgentSigned(updateDeliveryDocuments.getIsDeliveryAgentSigned());
//        deliveryDocumentsDTO.setIsCaregiverSigned(updateDeliveryDocuments.getIsCaregiverSigned());
//        deliveryDocumentsDTO.setIsPatientSigned(updateDeliveryDocuments.getIsPatientSigned());
//        deliveryDocumentsDTO.setSignedDate(updateDeliveryDocuments.getSignedDate());
//        deliveryDocumentsDTO.setCreatedById(updateDeliveryDocuments.getCreatedById());
//        deliveryDocumentsDTO.setCreatedByName(updateDeliveryDocuments.getCreatedByName());
//        deliveryDocumentsDTO.setCreatedDate(updateDeliveryDocuments.getCreatedDate());
//        deliveryDocumentsDTO.setUpdatedById(updateDeliveryDocuments.getUpdatedById());
//        deliveryDocumentsDTO.setUpdatedByName(updateDeliveryDocuments.getUpdatedByName());
//        deliveryDocumentsDTO.setUpdatedDate(updateDeliveryDocuments.getUpdatedDate());
        return deliveryDocumentsDTO;
    }

    @Override
    @Transactional
    public ServiceOutcome<DeliveryAssignmentDTO> assignDeliveryAgent(DeliveryAssignInput deliveryAssignInput) {
        DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.getDeliveryAssignmentOnDeliveryTicket(deliveryAssignInput.getDeliveryTicketId());
        DeliveryAssignmentDTO deliveryAssignmentDTO = null;
        if (deliveryAssignment == null){
            Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(deliveryAssignInput.getDeliveryTicketId());
            if(deliveryTicket.get() != null){
                deliveryAssignment = new DeliveryAssignment();
                deliveryAssignment.setDeliveryTicketId(deliveryTicket.get().getDeliveryTicketId());
                deliveryAssignment.setDeliveryNo(deliveryTicket.get().getDeliveryTicketNo());
                deliveryAssignment.setSoNo(deliveryTicket.get().getSoNo());
                deliveryAssignment.soId(deliveryTicket.get().getSoId());
                deliveryAssignment.setAgentFirstName(deliveryAssignInput.getAgentFirstName());
                deliveryAssignment.setAgentLastName(deliveryAssignInput.getAgentLastName());
                deliveryAssignment.setAgentIdNo(deliveryAssignInput.getAgentIdNo());
                deliveryAssignment.setAgentAgency(deliveryAssignInput.getAgentAgency());
                deliveryAssignment.setAgentContact1(deliveryAssignInput.getAgentContact1());
                deliveryAssignment.setAgentContact2(deliveryAssignInput.getAgentContact2());
                deliveryAssignment.setAgentVehicleNo(deliveryAssignInput.getAgentVehicleNo());
                deliveryAssignment.setAssignmentStatus("Initiated");
                deliveryAssignment.setAssgnmentDate(LocalDate.now());
                deliveryAssignment.setPriority("High");
                deliveryAssignment.setStatus("Active");
                deliveryAssignment.setCreatedById(1L);
                deliveryAssignment.setCreatedByName("Bimal");
                deliveryAssignment.setCreatedDate(LocalDate.now());
                deliveryAssignment = deliveryAssignmentRepository.save(deliveryAssignment);
                deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);
                return new ServiceOutcome<>(deliveryAssignmentDTO, true, "Delivery is assigned to delivery agent successfully!");
            }
            else{
                return new ServiceOutcome<>(deliveryAssignmentDTO, false, "Unable to assign the delivery to delivery agent!");
            }
        }
        else{
            deliveryAssignment.setUpdatedById(1L);
            deliveryAssignment.setUpdatedByName("Bimal");
            deliveryAssignment.setUpdatedDate(LocalDate.now());
            deliveryAssignment = deliveryAssignmentRepository.save(deliveryAssignment);
            deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment);
            return new ServiceOutcome<>(deliveryAssignmentDTO, false, "Delivery already assigned to delivery agent!");
        }
    }

    @Override
    public ServiceOutcome<DeliveryTicketDTO> operationOnCourierTypeDelivery(CourierTypeDeliveryInput courierTypeDeliveryInput){
        DeliveryTicket updateDeliveryTicket = null;
        try {
            if (courierTypeDeliveryInput.getDeliverType().equals("Courier")) {
                Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(courierTypeDeliveryInput.getDeliveryTicketId());
                if (deliveryTicket.isPresent()) {
                    DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket.get());
                    deliveryTicketDTO.setCarrierName(courierTypeDeliveryInput.getCarrierName());
                    LocalDate date = LocalDate.parse(courierTypeDeliveryInput.getShippingDate(), dateTimeFormatter);
                    deliveryTicketDTO.setShippingDate(LocalDate.parse(courierTypeDeliveryInput.getShippingDate(), dateTimeFormatter));
                    deliveryTicketDTO.setTrackingNo(courierTypeDeliveryInput.getTrackingNo());
                    deliveryTicketDTO.setReferenceNo(courierTypeDeliveryInput.getReferenceNo());
                    deliveryTicketDTO.setPackageWeight(courierTypeDeliveryInput.getPackageWeight());
                    updateDeliveryTicket = deliveryTicketMapper.toEntity(deliveryTicketDTO);
                    updateDeliveryTicket = deliveryTicketRepository.save(updateDeliveryTicket);
                }
                return new ServiceOutcome<>(deliveryTicketMapper.toDto(updateDeliveryTicket), true, "Operation on courier type delivery is updated successfully!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(null, false, "Failed to update on courier type delivery!");
    }

    @Override
    public ServiceOutcome<DeliveryTicketDTO> prepareSetupMethodForDelivery(SetupTechnicianInput setupTechnicianInput){
        DeliveryTicket updateDeliveryTicket;
        try {
            if (setupTechnicianInput.getSetupMethod().equals("Technician") || setupTechnicianInput.getSetupMethod().equals("Remote")) {
                Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(setupTechnicianInput.getDeliveryTicketId());
                if (deliveryTicket.isPresent() && deliveryTicket.get().getDeliveryStatus().equalsIgnoreCase("Approved") && deliveryTicket.get().getDeliveryDate() != null) {
                    if(deliveryTicket.get().getDeliveryDate().isBefore(LocalDate.parse(setupTechnicianInput.getSetupDateTime(), dateTimeFormatter))) {
                        DeliveryTicketDTO deliveryTicketDTO = deliveryTicketMapper.toDto(deliveryTicket.get());
                        deliveryTicketDTO.setSetupTechnicianNo(setupTechnicianInput.getSetupTechnicianNo());
                        deliveryTicketDTO.setSetupTechnicianContactNo(setupTechnicianInput.getSetupTechnicianContactNo());
                        deliveryTicketDTO.setSetupTechnicianFirstName(setupTechnicianInput.getSetupTechnicianFirstName());
                        deliveryTicketDTO.setSetupTechnicianMiddleName(setupTechnicianInput.getSetupTechnicianMiddleName());
                        deliveryTicketDTO.setSetupTechnicianLastName(setupTechnicianInput.getSetupTechnicianLastName());
                        deliveryTicketDTO.setSetupDateTime(LocalDate.parse(setupTechnicianInput.getSetupDateTime(), dateTimeFormatter));

                        deliveryTicketDTO.setScheduleSetupDateTime(LocalDate.parse(setupTechnicianInput.getScheduleSetupDateTime(), dateTimeFormatter));
                        deliveryTicketDTO.setSetupStatus(setupTechnicianInput.getSetupStatus());
                        deliveryTicketDTO.setCourierPackageAcceptedBy(setupTechnicianInput.getCourierPackageAcceptedBy());
                        deliveryTicketDTO.setTherapistFirstName(setupTechnicianInput.getTherapistFirstName());
                        deliveryTicketDTO.setTherapistLastName(setupTechnicianInput.getTherapistLastName());
                        deliveryTicketDTO.setTherapistLicenseNo(setupTechnicianInput.getTherapistLicenseNo());
                        deliveryTicketDTO.setTherapistNpi(setupTechnicianInput.getTherapistNpi());
                        deliveryTicketDTO.setTherapistTaxonomyCode(setupTechnicianInput.getTherapistTaxonomyCode());
                        deliveryTicketDTO.setScheduleTherapyDate(LocalDate.parse(setupTechnicianInput.getScheduleTherapyDate(), dateTimeFormatter));
                        deliveryTicketDTO.setActualTherapyDate(LocalDate.parse(setupTechnicianInput.getActualTherapyDate(), dateTimeFormatter));
                        deliveryTicketDTO.setTherapyMode(setupTechnicianInput.getTherapyMode());
                        deliveryTicketDTO.setTherapyStatus(setupTechnicianInput.getTherapyStatus());
                        deliveryTicketDTO.setTherapyNotes(setupTechnicianInput.getTherapyNotes());

                        updateDeliveryTicket = deliveryTicketMapper.toEntity(deliveryTicketDTO);
                        updateDeliveryTicket = deliveryTicketRepository.save(updateDeliveryTicket);
                        return new ServiceOutcome<>(deliveryTicketMapper.toDto(updateDeliveryTicket), true, "Operation on courier type delivery is updated successfully!");
                    }
                    else{
                        return new ServiceOutcome<>(null, false, "Setup date should be after the delivery date!");
                    }
                }
                else
                    return new ServiceOutcome<>(null, false, "Failed to update operation on courier type delivery due to delivery is not approved!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(null, false, "Failed to update on courier type delivery!");
    }

    @Override
    public List<DeliveryItems> getDeliveryItemsOnDeliveryTicket(Long deliveryTicketId){
        return deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicketId);
    }

    @Override
    public ServiceOutcome<DeliveryDocumentResponse> prepareProofOfDeliveryReport(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItems) throws Exception {
        String fileName = "POD_"+deliveryTicket.getDeliveryTicketId()+".pdf";

        System.out.println("=================List of deliveryItems================"+deliveryItems);

        Document document = new Document(PageSize.A4, 35, 35, 50, 50);
        System.out.println("=====deliveryTicket==>"+deliveryTicket);

        //Get Dynamic data one bt one
        String documentID = ""; //deliveryTicket.getDeliveryTicketDocumentNo() != null ? " "+deliveryTicket.getDeliveryTicketDocumentNo() : "";
        String carrierName = deliveryTicket.getCarrierName() != null ? " "+deliveryTicket.getCarrierName() : "";
        String trackingNo = deliveryTicket.getTrackingNo() != null ? " "+deliveryTicket.getTrackingNo() : "";
        String referenceNo = deliveryTicket.getReferenceNo() != null ? " "+deliveryTicket.getReferenceNo() : "";
        //purchaseOrderNo = deliveryTicket.getDeliveryTicketDocumentNo() != null ? deliveryTicket.getDeliveryTicketDocumentNo() : "";
        String packageWeight = deliveryTicket.getPackageWeight() != null ? " "+deliveryTicket.getPackageWeight() : "";

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String shippingDate = deliveryTicket.getShippingDate() != null ? " "+deliveryTicket.getShippingDate().format(formatters) : "";
        String deliveryDate = deliveryTicket.getDeliveryDate() != null ? " "+deliveryTicket.getDeliveryDate().format(formatters) : "";

        String deliveryAddress1 = deliveryTicket.getDeliveryAddress1() != null ? " "+deliveryTicket.getDeliveryAddress1() : "";
        String deliveryAddress2 = deliveryTicket.getDeliveryAddress2() != null ? deliveryTicket.getDeliveryAddress2() : "";
        String deliveryCity = deliveryTicket.getDeliveryCity() != null ? deliveryTicket.getDeliveryCity() : "";
        String deliveryState = deliveryTicket.getDeliveryState() != null ? deliveryTicket.getDeliveryState() : "";
        String deliveryZip = deliveryTicket.getDeliveryZip() != null ? deliveryTicket.getDeliveryZip() : "";
        String fullDeliveryAddress = deliveryAddress1+"\n "+ deliveryAddress2+"\n "+deliveryCity+"\n "+deliveryState+" ,"+deliveryZip;

        //String informationUpdated = deliveryTicket.getDeliveryTicketDocumentNo() != null ? deliveryTicket.getDeliveryTicketDocumentNo() : "";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));

        //Generate QR Code
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String qrPath = "POD_"+deliveryTicket.getDeliveryTicketId();
        commonPDFStubs.generateQRCode(qrPath, fileUploadConfigProperties.getTempDocumentProperties().getLocation());

        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+"POD_"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD("Proof Of Delivery"));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Document ID: ", "RIGHT",1, documentID));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Carrier: ", "RIGHT", 1, carrierName));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Tracking Number: ", "RIGHT", 1, trackingNo));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Reference Number (s): ", "RIGHT", 1, referenceNo));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Purchase Order Number: ", "RIGHT", 0, " "));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Package Weight: ", "RIGHT", 0, packageWeight));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Ship Date: ", "RIGHT", 0, shippingDate));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Delivery Date: ", "RIGHT", 0, deliveryDate));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Delivery Location: ", "RIGHT", 0, fullDeliveryAddress));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Information Updated: ", "RIGHT", 3, ""));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD(""));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD1("Order Details"));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentDynamicRowTableHeaderPOD(deliveryItems));
        //document.add(DeliveryPdfDocumentTableBuilder.createDeliveryDocumentDynamicRowWithTableBodyPOD());
        document.add(new Paragraph("\n"));

        document.close();

        System.out.println("PDF created successfully.");
        return new ServiceOutcome<>();
    }

    public ServiceOutcome<DeliveryDocumentResponse> prepareProofOfDeliveryReportOnDropship(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItems, CommonDeliveryInputData commonDeliveryInputData) throws Exception {
        String fileName = "POD_"+deliveryTicket.getDeliveryTicketNo()+".pdf";
        CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);
        Document document = new Document(PageSize.A4, 35, 35, 50, 50);
        System.out.println("=====deliveryTicket==>"+deliveryTicket);
        //Get Dynamic data one bt one
        String documentID = ""; //deliveryTicket.getDeliveryTicketDocumentNo() != null ? " "+deliveryTicket.getDeliveryTicketDocumentNo() : "";
        String carrierName = deliveryTicket.getCarrierName() != null ? " "+deliveryTicket.getCarrierName() : "";
        String trackingNo = deliveryTicket.getTrackingNo() != null ? " "+deliveryTicket.getTrackingNo() : "";
        String referenceNo = deliveryTicket.getReferenceNo() != null ? " "+deliveryTicket.getReferenceNo() : "";
        //purchaseOrderNo = deliveryTicket.getDeliveryTicketDocumentNo() != null ? deliveryTicket.getDeliveryTicketDocumentNo() : "";
        String packageWeight = deliveryTicket.getPackageWeight() != null ? " "+deliveryTicket.getPackageWeight() : "";

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String shippingDate = deliveryTicket.getShippingDate() != null ? " "+deliveryTicket.getShippingDate().format(formatters) : "";
        String deliveryDate = deliveryTicket.getDeliveryDate() != null ? " "+deliveryTicket.getDeliveryDate().format(formatters) : "";

        String deliveryAddress1 = deliveryTicket.getDeliveryAddress1() != null ? " "+deliveryTicket.getDeliveryAddress1() : "";
        String deliveryAddress2 = deliveryTicket.getDeliveryAddress2() != null ? deliveryTicket.getDeliveryAddress2() : "";
        String deliveryCity = deliveryTicket.getDeliveryCity() != null ? deliveryTicket.getDeliveryCity() : "";
        String deliveryState = deliveryTicket.getDeliveryState() != null ? deliveryTicket.getDeliveryState() : "";
        String deliveryZip = deliveryTicket.getDeliveryZip() != null ? deliveryTicket.getDeliveryZip() : "";
        String fullDeliveryAddress = deliveryAddress1+"\n "+ deliveryAddress2+"\n "+deliveryCity+"\n "+deliveryState+" ,"+deliveryZip;

        //String informationUpdated = deliveryTicket.getDeliveryTicketDocumentNo() != null ? deliveryTicket.getDeliveryTicketDocumentNo() : "";

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));

        //Generate QR Code
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String qrPath = "POD_"+deliveryTicket.getDeliveryTicketId();
        commonPDFStubs.generateQRCode(qrPath, fileUploadConfigProperties.getTempDocumentProperties().getLocation());

        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+"POD_"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD("Proof Of Delivery"));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Document ID: ", "RIGHT",1, documentID));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Carrier: ", "RIGHT", 1, carrierName));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Tracking Number: ", "RIGHT", 1, trackingNo));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Reference Number (s): ", "RIGHT", 1, referenceNo));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Purchase Order Number: ", "RIGHT", 0, " "));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Package Weight: ", "RIGHT", 0, packageWeight));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Ship Date: ", "RIGHT", 0, shippingDate));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Delivery Date: ", "RIGHT", 0, deliveryDate));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Delivery Location: ", "RIGHT", 0, fullDeliveryAddress));
        document.add(ProofOfDeliveryDocumentTableBuilder.createTableForTextAndInputBoxSpacedPOD("Information Updated: ", "RIGHT", 3, ""));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD(""));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentMainBodyTitlePOD1("Order Details"));
        document.add(ProofOfDeliveryDocumentTableBuilder.createDeliveryDocumentDynamicRowTableHeaderPOD(deliveryItems));
        //document.add(DeliveryPdfDocumentTableBuilder.createDeliveryDocumentDynamicRowWithTableBodyPOD());
        document.add(new Paragraph("\n"));

        document.close();

        commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
        commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
        System.out.println("POD document is created successfully.");
        return new ServiceOutcome<>(commonDocumentResponse.getDeliveryDocumentResponse(), true, "POD document is created successfully.");
    }

    public ServiceOutcome<DeliveryDocumentResponse> prepareHIPAANoticeReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData) throws Exception {
        log.info("===Itext Pdf HIPAA Notice of Privacy Practices (Notice) Coding Implementation Started===");

        String fileName = "HIPAA_Notice_"+deliveryTicket.getDeliveryTicketId()+"_"+deliveryTicket.getSoNo()+".pdf";
        CommonDocumentResponse commonDocumentResponse = processCommonStubsWithDocument(deliveryTicket, commonDeliveryInputData);

        log.info("======commonDocumentResponse======="+ commonDocumentResponse);
        Document document = new Document(PageSize.A4, 35, 35, 25, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));

        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        HipaaNoticeDocsTableBuilder.populateDataInHipaaNoticeDocs(document, deliveryTicket);
        prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);
        document.close();

        commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
        commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
        log.info("PDF created successfully.");
        return new ServiceOutcome<>(commonDocumentResponse.getDeliveryDocumentResponse(), true, "PDF created successfully.");
    }

    @Override
    public ServiceOutcome<List<DeliveryAssignmentDTO>> getDeliveryAssignedListByAgentDate(String searchedAgentName, String startDate, String endDate) throws ParseException {
        log.info("============================getDeliveryAssignedListByAgentDate====================================");
        List<DeliveryAssignmentDTO> deliveryAssignmentDTOS = new ArrayList<>();
        Date sDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        List<DeliveryAssignment> deliveryAssignments = deliveryAssignmentRepository.getDeliveryAssignedListByAgentDate(searchedAgentName, sDate, eDate);
        if(deliveryAssignments != null && deliveryAssignments.size() > 0){
            deliveryAssignments.stream().forEach(assignment -> {
                deliveryAssignmentDTOS.add(deliveryAssignmentMapper.toDto(assignment));
            });
        }
        return new ServiceOutcome<>(deliveryAssignmentDTOS, true, "Successfully fetched the delivery assignment details!!!");
    }

    @Override
    public ServiceOutcome<DeliveryAssignmentDTO> getDeliveryAssignedToAgentByDeliveryAssigned(Long deliveryAssignmentId){
        Optional<DeliveryAssignment> deliveryAssignment = deliveryAssignmentRepository.findById(deliveryAssignmentId);
        DeliveryAssignmentDTO deliveryAssignmentDTO;
        if(deliveryAssignment.isPresent()) {
            deliveryAssignmentDTO = deliveryAssignmentMapper.toDto(deliveryAssignment.get());
            return new ServiceOutcome<>(deliveryAssignmentDTO, true, "Successfully fetched the delivery assignment detail!!!");
        }
        else
            return new ServiceOutcome<>(null, false, "Data Not Found the delivery assignment detail!!!");
    }

    @Override
    public ServiceOutcome<HCPCSDocumentData> getHCPCSDocumentListOnDeliveryTicket(Long deliveryTicketId){
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(deliveryTicketId);
        HCPCSDocumentData hcpcsDocumenData = new HCPCSDocumentData();
        List<DocumentData> documentDataList = new ArrayList<>();
        if(deliveryTicket.isPresent()){
            List<DeliveryItems> deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
            AtomicReference<List<HcpcsDocType>> hcpcsDocTypeList = new AtomicReference<List<HcpcsDocType>>();
            deliveryItemsList.stream().forEach(items -> {
                System.out.println("=========>"+items.getHcpcsCode());
                hcpcsDocTypeList.set(hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCode(items.getHcpcsCode()));
            });
            if(hcpcsDocTypeList.get() != null){
                hcpcsDocTypeList.get().stream().forEach(hcpcs -> {
                    log.info("========1======>"+hcpcs.getHcpcsCode());
                });
            }
            String hcpcsCode = "0";
            List<HcpcsDocType> hcpcsDocTypeLists = hcpcsDocTypeRepository.getHcpcsDocTypeOnHcpcsCodeNFileType(hcpcsCode);
            hcpcsDocTypeLists.stream().forEach(hcpcs -> {
                log.info("======0========>"+hcpcs.getFormName());
                DocumentData documentData = new DocumentData();
                documentData.setDocumentId(hcpcs.getDocumentId());
                documentData.setDocumentName(hcpcs.getDocumentName());
                documentData.setHcpcsCode(hcpcs.getHcpcsCode());
                documentData.setHcpcsDoctypeId(hcpcs.getHcpcsDoctypeId());
                documentData.setFileType(hcpcs.getFileType());
                documentData.setFormName(hcpcs.getFormName());
                documentData.setHcpcsDocTypeUuid(hcpcs.getHcpcsDocTypeUuid().toString());
                documentDataList.add(documentData);
            });
            hcpcsDocumenData.setDeliveryTicketId(deliveryTicketId);
            hcpcsDocumenData.setPatientName(deliveryTicket.get().getPatientFirstName()+" "+deliveryTicket.get().getPatientLastName());
            hcpcsDocumenData.setDocumentData(documentDataList);
        }
        return new ServiceOutcome<>(hcpcsDocumenData, true, "Hcpcs code documents gets fetched successfully!!!");
    }

    @Override
    public ServiceOutcome<HCPCSDocumentData> selectedHCPCSDocumentListOnDeliveryTicket(String inputDocuments) throws JsonProcessingException {
        log.info("================>"+inputDocuments);
        HCPCSDocumentData hcpcsDocuments = new ObjectMapper().readValue(inputDocuments, HCPCSDocumentData.class);
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(hcpcsDocuments.getDeliveryTicketId());
        List<DeliveryDocumentResponse> deliveryDocumentResponses = new ArrayList<>();

        hcpcsDocuments.getDocumentData().stream().forEach(document -> {
            DeliveryDocuments deliveryDocument = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicketNHcpcsDocType(hcpcsDocuments.getDeliveryTicketId(),
                document.getHcpcsDoctypeId());
            if(deliveryDocument != null) {
                log.info("=============Update=================");
                deliveryDocument.setDocumentPatientFilePath(document.getDocumentName());
                deliveryDocument.setUpdatedDate(LocalDate.now());
                deliveryDocument.setUpdatedByName("Bimal-Update");
                deliveryDocument.setUpdatedById(1L);
                DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocumentsRepository.save(deliveryDocument));
                DeliveryDocumentResponse deliveryDocumentResponse = createDeliveryDocumentResponse(deliveryDocumentsDTO);
                deliveryDocumentResponses.add(deliveryDocumentResponse);
            }
            else{
                log.info("=============Save=================");
                DeliveryDocuments deliveryDocuments = new DeliveryDocuments();
                deliveryDocuments.setDeliveryTicketId(hcpcsDocuments.getDeliveryTicketId());
                deliveryDocuments.setDeliveryTicketNo(deliveryTicket.get().getDeliveryTicketNo());
                deliveryDocuments.setSoId(deliveryTicket.get().getSoId());
                deliveryDocuments.setSoNo(deliveryTicket.get().getSoNo());
                deliveryDocuments.setHcpcsDoctypeId(document.getHcpcsDoctypeId());
                deliveryDocuments.setDocPatientName(deliveryTicket.get().getPatientFirstName() + " " + deliveryTicket.get().getPatientLastName());
                deliveryDocuments.setDocumentPatientFilePath(document.getDocumentName());
                deliveryDocuments.setStatus("initiated");
                deliveryDocuments.setCreatedDate(LocalDate.now());
                deliveryDocuments.setCreatedByName("Bimal");
                deliveryDocuments.setCreatedById(0L);
                deliveryDocuments.setDeliveryDocumentsUuid(UUID.randomUUID());
                DeliveryDocumentsDTO deliveryDocumentsDTO = deliveryDocumentsMapper.toDto(deliveryDocumentsRepository.save(deliveryDocuments));
                DeliveryDocumentResponse deliveryDocumentResponse = createDeliveryDocumentResponse(deliveryDocumentsDTO);
                deliveryDocumentResponses.add(deliveryDocumentResponse);
            }
        });
        hcpcsDocuments.setDeliveryDocumentResponse(deliveryDocumentResponses);
        hcpcsDocuments.setDocumentData(null);
        return new ServiceOutcome<>(hcpcsDocuments, true, "Hcpcs selected documents saved successfully!!!");
    }

    public DeliveryDocumentResponse createDeliveryDocumentResponse(DeliveryDocumentsDTO deliveryDocumentsDTO){
        DeliveryDocumentResponse deliveryDocumentResponse = new DeliveryDocumentResponse();
        deliveryDocumentResponse.setDeliveryDocId(deliveryDocumentsDTO.getDeliveryDocId());
        deliveryDocumentResponse.setDeliveryTicketId(deliveryDocumentsDTO.getDeliveryTicketId());
        deliveryDocumentResponse.setDeliveryTicketNo(deliveryDocumentsDTO.getDeliveryTicketNo());
        deliveryDocumentResponse.setSoId(deliveryDocumentsDTO.getSoId());
        deliveryDocumentResponse.setSoNo(deliveryDocumentsDTO.getSoNo());
        deliveryDocumentResponse.setHcpcsDoctypeId(deliveryDocumentsDTO.getHcpcsDoctypeId());
        deliveryDocumentResponse.setDocPatientName(deliveryDocumentsDTO.getDocPatientName());
        deliveryDocumentResponse.setDocumentPatientFile(deliveryDocumentsDTO.getDocumentPatientFilePath());
        deliveryDocumentResponse.setDeliveryDocumentsUuid(deliveryDocumentsDTO.getDeliveryDocumentsUuid());
        return deliveryDocumentResponse;
    }

    @Override
    public Boolean updateDeliveryStatus(ItemInventoryStatusInputRequest itemInventoryStatusInputRequest){
        Boolean status = true;
        itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().stream().forEach(ticket -> {
            Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(ticket.getDeliveryTicketId());
            if(deliveryTicket.isPresent()){
                deliveryTicket.get().setDeliveryStatus("Delivered");
                deliveryTicket.get().setDeliveryDate(LocalDate.now());
                deliveryTicketRepository.save(deliveryTicket.get());

                List<DeliveryDocuments> deliveryDocuments = deliveryDocumentsRepository.getDeliveryDocumentOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
                if(deliveryDocuments != null && deliveryDocuments.size() > 0){
                    deliveryDocuments.stream().forEach(document -> {
                        document.setStatus("Active");
                        deliveryDocumentsRepository.save(document);
                    });
                }

                DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.getDeliveryAssignmentOnDeliveryTicket(deliveryTicket.get().getDeliveryTicketId());
                if(deliveryAssignment != null){
                    deliveryAssignment.setDeliveryStatus("Delivered");
                    deliveryAssignment.setActualDeliveryDateTime(LocalDate.now());
                    deliveryAssignment.setUpdatedById(0L);
                    deliveryAssignment.setUpdatedDate(LocalDate.now());
                    deliveryAssignment.setUpdatedByName("Bimal");
                    deliveryAssignmentRepository.save(deliveryAssignment);
                }
            }
        });
        return status;
    }

    /*@Override
    @Transactional
    public void postPatientCommunicationDetails(PatientCommunicationInputParams patientCommunicationInputParams){
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(patientCommunicationInputParams.getDeliveryTicketId());
        if(deliveryTicket.isPresent()){
            DeliveryPatientCommunications deliveryPatientCommunications = deliveryPatientCommunicationsRepository
                .getDeliveryPatientCommunicationOnDeliveryAndCommunicationReason(deliveryTicket.get().getDeliveryTicketId(), patientCommunicationInputParams.getReasonForCommunication());
            if(deliveryPatientCommunications != null) {
                deliveryPatientCommunications.setDeliveryTicketId(deliveryTicket.get().getDeliveryTicketId());
                deliveryPatientCommunications.setDeliveryTicketNo(deliveryTicket.get().getDeliveryTicketNo());
                deliveryPatientCommunications.setPatientPhoneNo(patientCommunicationInputParams.getPatientPhoneNo());
                deliveryPatientCommunications.setPersonSpokenToName(patientCommunicationInputParams.getPersonSpokenToName());

                deliveryPatientCommunications.setReasonForCommunication(patientCommunicationInputParams.getReasonForCommunication());
                deliveryPatientCommunications.setCommunicationSummery(deliveryPatientCommunications.getCommunicationSummery().concat("/"+patientCommunicationInputParams.getCommunicationSummery()));

                deliveryPatientCommunications.setPersonSpokenToRelationWithPatient(patientCommunicationInputParams.getPersonSpokenToRelationWithPatient());
                deliveryPatientCommunications.setCsrName(patientCommunicationInputParams.getCsrName());
                deliveryPatientCommunications.setDeliveryPatientCommunicationsUuid(UUID.randomUUID());
                deliveryPatientCommunications.setStatus("Active");
                deliveryPatientCommunications.updatedDate(LocalDate.now());
                deliveryPatientCommunications.setUpdatedByName("Bimal");
                deliveryPatientCommunications.setUpdatedById(0L);
                deliveryPatientCommunicationsRepository.save(deliveryPatientCommunications);
            }
            else{
                DeliveryPatientCommunications deliveryPatientCommunication = new DeliveryPatientCommunications();
                deliveryPatientCommunication.setDeliveryTicketId(deliveryTicket.get().getDeliveryTicketId());
                deliveryPatientCommunication.setDeliveryTicketNo(deliveryTicket.get().getDeliveryTicketNo());
                deliveryPatientCommunication.setPatientPhoneNo(patientCommunicationInputParams.getPatientPhoneNo());
                deliveryPatientCommunication.setPersonSpokenToName(patientCommunicationInputParams.getPersonSpokenToName());

                deliveryPatientCommunication.setReasonForCommunication(patientCommunicationInputParams.getReasonForCommunication());
                deliveryPatientCommunication.setCommunicationSummery(patientCommunicationInputParams.getCommunicationSummery());

                deliveryPatientCommunication.setPersonSpokenToRelationWithPatient(patientCommunicationInputParams.getPersonSpokenToRelationWithPatient());
                deliveryPatientCommunication.setCsrName(patientCommunicationInputParams.getCsrName());
                deliveryPatientCommunication.setDeliveryPatientCommunicationsUuid(UUID.randomUUID());
                deliveryPatientCommunication.setStatus("Active");
                deliveryPatientCommunications.setCreatedDate(LocalDate.now());
                deliveryPatientCommunications.setCreatedByName("Bimal");
                deliveryPatientCommunications.setCreatedById(0L);
                deliveryPatientCommunicationsRepository.save(deliveryPatientCommunication);
            }
        }
    }*/

    /*public void postPatientCommunicationDetails1(PatientCommunicationInputParams patientCommunicationInputParams){
        Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(patientCommunicationInputParams.getDeliveryTicketId());
        CompletableFuture.supplyAsync(() -> deliveryPatientCommunicationsRepository
                .getDeliveryPatientCommunicationOnDeliveryAndCommunicationReason(deliveryTicket.get().getDeliveryTicketId(), patientCommunicationInputParams.getReasonForCommunication()))
            .thenApplyAsync(comm -> updateDeliveryPatientCommunications(comm, deliveryTicket.get(), patientCommunicationInputParams))
            .thenAcceptAsync(comm -> deliveryPatientCommunicationsRepository.save(comm));
        CompletionStage
    }*/

    @Override
    @Transactional
    public void postPatientCommunicationDetails(PatientCommunicationInputParams patientCommunicationInputParams){
        CompletableFuture.supplyAsync(() -> {
            Optional<DeliveryTicket> deliveryTicket = deliveryTicketRepository.findById(patientCommunicationInputParams.getDeliveryTicketId());
            if (deliveryTicket.isPresent()) {
                DeliveryPatientCommunications deliveryPatientCommunications = deliveryPatientCommunicationsRepository
                    .getDeliveryPatientCommunicationOnDeliveryAndCommunicationReason(deliveryTicket.get().getDeliveryTicketId(), patientCommunicationInputParams.getReasonForCommunication());
                if(deliveryPatientCommunications != null) {
                    updateDeliveryPatientCommunications(deliveryPatientCommunications, deliveryTicket.get(), patientCommunicationInputParams);
                }
                else{
                    saveDeliveryPatientCommunications(deliveryTicket.get(), patientCommunicationInputParams);
                }
            }
            return null;
        });
    }

    public DeliveryPatientCommunications updateDeliveryPatientCommunications(DeliveryPatientCommunications deliveryPatientCommunications, DeliveryTicket deliveryTicket, PatientCommunicationInputParams patientCommunicationInputParams){
        log.info("=============================UPDATE=============================");
        deliveryPatientCommunications.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        deliveryPatientCommunications.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
        deliveryPatientCommunications.setPatientPhoneNo(patientCommunicationInputParams.getPatientPhoneNo());
        deliveryPatientCommunications.setPersonSpokenToName(patientCommunicationInputParams.getPersonSpokenToName());

        deliveryPatientCommunications.setReasonForCommunication(patientCommunicationInputParams.getReasonForCommunication());
        deliveryPatientCommunications.setCommunicationSummery(deliveryPatientCommunications.getCommunicationSummery().concat("/"+patientCommunicationInputParams.getCommunicationSummery()));

        deliveryPatientCommunications.setPersonSpokenToRelationWithPatient(patientCommunicationInputParams.getPersonSpokenToRelationWithPatient());
        deliveryPatientCommunications.setCsrName(patientCommunicationInputParams.getCsrName());
        deliveryPatientCommunications.setDeliveryPatientCommunicationsUuid(UUID.randomUUID());
        deliveryPatientCommunications.setStatus("Active");
        deliveryPatientCommunications.updatedDate(LocalDate.now());
        deliveryPatientCommunications.setUpdatedByName("Bimal");
        deliveryPatientCommunications.setUpdatedById(0L);
        return deliveryPatientCommunications;
    }

    public DeliveryPatientCommunications saveDeliveryPatientCommunications(DeliveryTicket deliveryTicket, PatientCommunicationInputParams patientCommunicationInputParams){
        log.info("=============================SAVE=============================");
        DeliveryPatientCommunications deliveryPatientCommunication = new DeliveryPatientCommunications();
        deliveryPatientCommunication.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        deliveryPatientCommunication.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
        deliveryPatientCommunication.setPatientPhoneNo(patientCommunicationInputParams.getPatientPhoneNo());
        deliveryPatientCommunication.setPersonSpokenToName(patientCommunicationInputParams.getPersonSpokenToName());

        deliveryPatientCommunication.setReasonForCommunication(patientCommunicationInputParams.getReasonForCommunication());
        deliveryPatientCommunication.setCommunicationSummery(patientCommunicationInputParams.getCommunicationSummery());

        deliveryPatientCommunication.setPersonSpokenToRelationWithPatient(patientCommunicationInputParams.getPersonSpokenToRelationWithPatient());
        deliveryPatientCommunication.setCsrName(patientCommunicationInputParams.getCsrName());
        deliveryPatientCommunication.setDeliveryPatientCommunicationsUuid(UUID.randomUUID());
        deliveryPatientCommunication.setStatus("Active");
        deliveryPatientCommunication.setCreatedDate(LocalDate.now());
        deliveryPatientCommunication.setCreatedByName("Bimal");
        deliveryPatientCommunication.setCreatedById(0L);
        return deliveryPatientCommunication;
    }

    @Override
    public ServiceOutcome<DeliveryDocumentResponse> preparePAPSetupDocumentReport(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItems) throws Exception {
        log.info("==============deliveryTicket============="+deliveryTicket);
        log.info("==============deliveryItems============="+deliveryItems);
        String fileName = "PAP_SetUp_Form_"+deliveryTicket.getDeliveryTicketId()+".pdf";
        Document document = new Document(PageSize.A4, 35, 35, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation() + "/" + fileName)));

        PdfFormField radioGroup = PdfFormField.createRadioButton(writer, true);
        radioGroup.setFieldName("patientAssessment");

        //Generate QR Code
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String qrPath = "PAP_"+deliveryTicket.getDeliveryTicketId();
        commonPDFStubs.generateQRCode(qrPath, fileUploadConfigProperties.getTempDocumentProperties().getLocation());

        DeliveryHeaderFooterPageEvent event = new DeliveryHeaderFooterPageEvent(deliveryTicket, fileUploadConfigProperties.getTempDocumentProperties().getLocation()+"/"+"PAP_"+deliveryTicket.getDeliveryTicketId()+".png");
        writer.setPageEvent(event);

        document.open();
        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentMainBodyTitle(deliveryTicket));
        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentBodyTableHeader(deliveryTicket));
        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentBodyTableHeader1(deliveryTicket));

        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentMainFormSubTitlePAP("Equipment Ordered"));
        document.add(PAPSetupFormTableBuilder.createTableForTextAndInputBoxMultipleSpacedPAP());
        document.add(PAPSetupFormTableBuilder.createTableForTextAndInputBoxMultipleSpacedPAP1());

        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentMainFormSubTitlePAP("General Information"));
        document.add(PAPSetupFormTableBuilder.createCheckBoxInTable2(radioGroup, "", "checkbox"));

        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentMainFormSubTitlePAP("Safety Information"));
        document.add(PAPSetupFormTableBuilder.createCheckBoxInTable3(radioGroup, "", "checkbox"));

        document.add(PAPSetupFormTableBuilder.createDeliveryDocumentMainFormSubTitlePAP("Operational Information"));
        document.add(PAPSetupFormTableBuilder.createCheckBoxInTable4(radioGroup, "", "checkbox"));
        document.add(PAPSetupFormTableBuilder.createCheckBoxInTable5(radioGroup, "", "checkbox"));
        document.add(PAPSetupFormTableBuilder.createCheckBoxInTable6(radioGroup, "", "checkbox"));

        document.add(new CommonPdfTableStubs().createParagraphTitleTableBuilder("Comments:", Font.BOLD));
        document.add(new CommonPdfTableStubs().createTextboxInTable(" "));
        document.add(new Paragraph("\n"));
        writer.addAnnotation(radioGroup);
        //prepareCommonDeliveryDocumentContents(document, commonDocumentResponse);  //Footer Code will be added later

        document.close();

        CommonDocumentResponse commonDocumentResponse = new CommonDocumentResponse();  //CommonDocumentResponse code will be changed later
        commonDocumentResponse.getDeliveryDocumentResponse().setSignedDate(LocalDate.now());
        commonDocumentResponse.getDeliveryDocumentResponse().setDocumentPatientFilePath(fileName);
        System.out.println("PDF created successfully.");
        return new ServiceOutcome<>(commonDocumentResponse.getDeliveryDocumentResponse(), true, "PAP SetUp Form is created successfully.");
    }
}
