package com.sunknowledge.dme.rcm.service.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DMECertificationFormInput;
import com.sunknowledge.dme.rcm.pojo.patientassessment.PatientAssessmentFormInput;
import com.sunknowledge.dme.rcm.pojo.patienthomeassement.PatientHomeAssessmentFormInput;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.*;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.ItemInventoryStatusInputRequest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    ServiceOutcome<DeliveryOutputResponse> uploadSignatureFiles(UploadSignatureJsonReqAndRep uploadSignatureJsonReqAndRep);
    Resource loadFileAsSignatureResource(String fileName) throws MalformedURLException;
    Resource loadFileAsDeliveryDocumentResource(String fileName) throws MalformedURLException;
    ServiceOutcome<DMECertificationFormInput> receiveDMECertificationFileUserData(DMECertificationFormInput dmeCertificationFormInput) throws IOException;
    ServiceOutcome<PatientHomeAssessmentFormInput> receiveHomeAssessmentFileUserData(PatientHomeAssessmentFormInput patientHomeAssessmentFormInput) throws IOException;
    ServiceOutcome<PatientAssessmentFormInput> receivePatientAssessmentFileUserData(PatientAssessmentFormInput patientAssessmentFormInput) throws IOException;
    ServiceOutcome<List<DeliveryTicketDTO>> getDeliveryTicketsOnSalesOrder(Long salesOrderId);
    ServiceOutcome<List<DeliveryDocumentResponse>> getDeliveryPODDocuments(Long deliveryTicketId);
    ServiceOutcome<List<DeliveryDocumentsForm>> getDeliveryDocuments(Long deliveryTicketId);
    ServiceOutcome<String> getDeliveryDocumentFormData(Long deliveryTicketId, String formName);
    ServiceOutcome<DeliveryDocumentResponse> prepareDeliveryDocumentReport(DeliveryTicket deliveryTicket, CommonDeliveryInputData commonDeliveryInputData) throws Exception;
    Optional<DeliveryTicket> getDeliveryTicketOnDeliveryTicket(Long deliveryTicketId);
    ServiceOutcome<DeliveryAssignmentDTO> assignDeliveryAgent(DeliveryAssignInput deliveryAssignInput);
    ServiceOutcome<DeliveryTicketDTO> operationOnCourierTypeDelivery(CourierTypeDeliveryInput courierTypeDeliveryInput);
    ServiceOutcome<DeliveryTicketDTO> prepareSetupMethodForDelivery(SetupTechnicianInput setupTechnicianInput);
    ServiceOutcome<DeliveryDocumentResponse> prepareProofOfDeliveryReport(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItems) throws Exception;
    List<DeliveryItems> getDeliveryItemsOnDeliveryTicket(Long deliveryTicketId);
    ServiceOutcome<List<DeliveryAssignmentDTO>> getDeliveryAssignedListByAgentDate(String searchedAgentName, String startDate, String endDate) throws ParseException;
    ServiceOutcome<DeliveryAssignmentDTO> getDeliveryAssignedToAgentByDeliveryAssigned(Long deliveryAssignmentId);
    ServiceOutcome<HCPCSDocumentData> getHCPCSDocumentListOnDeliveryTicket(Long deliveryTicketId);
    ServiceOutcome<HCPCSDocumentData> selectedHCPCSDocumentListOnDeliveryTicket(String inputDocuments) throws JsonProcessingException;
    ItemInventoryStatusInputRequest prepareItemInventoryStatusInputDetails(Long deliveryTicketId);
    Boolean updateDeliveryStatus(ItemInventoryStatusInputRequest itemInventoryStatusInputRequest);
    void postPatientCommunicationDetails(PatientCommunicationInputParams patientCommunicationInputParams);
    ServiceOutcome<DeliveryDocumentResponse> preparePAPSetupDocumentReport(DeliveryTicket deliveryTicket, List<DeliveryItems> deliveryItems) throws Exception;
}
