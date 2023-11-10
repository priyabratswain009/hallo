package com.sunknowledge.dme.rcm.web.rest.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import com.sunknowledge.dme.rcm.application.exceptions.ResourceNotFoundException;
import com.sunknowledge.dme.rcm.application.properties.FileDownloadUtilityService;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.pojo.dmecertification.DMECertificationFormInput;
import com.sunknowledge.dme.rcm.pojo.patientassessment.PatientAssessmentFormInput;
import com.sunknowledge.dme.rcm.pojo.patienthomeassement.PatientHomeAssessmentFormInput;
import com.sunknowledge.dme.rcm.service.delivery.DeliveryService;
import com.sunknowledge.dme.rcm.service.dto.DeliveryAssignmentDTO;
import com.sunknowledge.dme.rcm.service.dto.DeliveryTicketDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.*;
import com.sunknowledge.dme.rcm.service.dto.delivery.inventory.ItemInventoryStatusInputRequest;
import com.sunknowledge.dme.rcm.validation.ValidationUtility;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * @author Bimal K Sahoo
 * @since 27/04/2023
 */
@RestController
@RequestMapping("/api/delivery")
@Slf4j
public class DeliveryResource {
    @Autowired
    private FileDownloadUtilityService fileDownloadUtilityService;
    @Autowired
    private DeliveryService deliveryService;

    /**
     * @since 09/05/2023
     */
    @ApiOperation(value = "Prepare Delivery Documents")
    @PostMapping(path = "/prepareDeliveryDocuments")
    public ServiceOutcome<List<DeliveryDocumentResponse>> prepareDeliveryDocuments(@RequestParam("deliveryTicketId") Long deliveryTicketId) {
        log.info("=======================POST(CONTROLLER) Prepare Delivery Documents=========================="+deliveryTicketId);
        String resultOutcomeJson = "Response";
        ServiceOutcome<List<DeliveryDocumentResponse>> serviceOutcome = null;
        try {
//            serviceOutcome = deliveryService.prepareDeliveryDocuments(deliveryTicketId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Get Delivery Document Form Data")
    @GetMapping(path = "/getDeliveryDocumentFormData")
    public ServiceOutcome<String> getDeliveryDocumentFormData(Long deliveryTicketId, String formName){
        return deliveryService.getDeliveryDocumentFormData(deliveryTicketId, formName);
    }

    @ApiOperation(value = "Receive DME Certification Template File User Data")
    @PostMapping(path = "/receiveDMECertificationFileUserData")
    public ServiceOutcome<DMECertificationFormInput> receiveDMECertificationFileUserData(@RequestBody DMECertificationFormInput dmeCertificationFormInput) {
        log.info("=======================POST(CONTROLLER) Receive Template File User Data==========================");
        ServiceOutcome<DMECertificationFormInput> serviceOutcome = null;
        try {
            serviceOutcome =  deliveryService.receiveDMECertificationFileUserData(dmeCertificationFormInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Receive Home Assessment File User Data")
    @PostMapping(path = "/receiveHomeAssessmentFileUserData")
    public ServiceOutcome<PatientHomeAssessmentFormInput> receiveTemplateFileUserData(@RequestBody PatientHomeAssessmentFormInput patientHomeAssessmentFormInput) {
        log.info("=======================POST(CONTROLLER) Receive Home Assessment File User Data==========================");
        ServiceOutcome<PatientHomeAssessmentFormInput> serviceOutcome = null;
        try {
            serviceOutcome =  deliveryService.receiveHomeAssessmentFileUserData(patientHomeAssessmentFormInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Receive Patient Assessment File User Data")
    @PostMapping(path = "/receivePatientAssessmentFileUserData")
    public ServiceOutcome<PatientAssessmentFormInput>  receivePatientAssessmentFileUserData(@RequestBody PatientAssessmentFormInput patientAssessmentFormInput) {
        log.info("=======================POST(CONTROLLER) Receive Patient Assessment File User Data==========================");
        ServiceOutcome<PatientAssessmentFormInput> serviceOutcome = null;
        try {
            serviceOutcome =  deliveryService.receivePatientAssessmentFileUserData(patientAssessmentFormInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Prepare Delivery Document Report")
    @PostMapping(path = "/prepareDeliveryDocumentReport")
    public ServiceOutcome<DeliveryDocumentResponse> prepareDeliveryDocumentReport(@RequestParam("deliveryTicketId") Long deliveryTicketId) {
        log.info("=======================POST(CONTROLLER) Prepare Delivery Document Report=========================="+deliveryTicketId);
        String resultOutcomeJson = "Response";
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = null;
        CommonDeliveryInputData commonDeliveryInputData = null;
        try {
            Optional<DeliveryTicket> deliveryTicket = deliveryService.getDeliveryTicketOnDeliveryTicket(deliveryTicketId);
            serviceOutcome = deliveryService.prepareDeliveryDocumentReport(deliveryTicket.get(), commonDeliveryInputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Uploading Signature Files")
    @PostMapping(path = "/uploadSignatureFiles", produces = "application/json")
    public ServiceOutcome<DeliveryOutputResponse> uploadSignatureFiles(@RequestBody UploadSignatureJsonReqAndRep uploadSignatureJsonReqAndRep) {
        log.info("=======================POST(CONTROLLER) Uploading Signature Files==========================");
        ServiceOutcome<DeliveryOutputResponse> outcome = new ServiceOutcome<>();
        ItemInventoryStatusInputRequest itemInventoryStatusInputRequest;
        try {
            if(uploadSignatureJsonReqAndRep.getSignatureType() == null || uploadSignatureJsonReqAndRep.getSignatureType().equals("")){
                throw new ResourceNotFoundException("Signature type should not be empty!");
            }
            if(!uploadSignatureJsonReqAndRep.getSignatureType().equals("patient") && !uploadSignatureJsonReqAndRep.getSignatureType().equals("caregiver")){
                throw new ResourceNotFoundException("Signature type should be patient/caregiver!");
            }
            if(uploadSignatureJsonReqAndRep.getDriverAgentSignature() == null || uploadSignatureJsonReqAndRep.getDriverAgentSignature().equals("")) {
                throw new ResourceNotFoundException("You must select the driver signature file for uploading!");
            }
            else{
                outcome = deliveryService.uploadSignatureFiles(uploadSignatureJsonReqAndRep);
                if(outcome != null && outcome.getOutcome()){
                    itemInventoryStatusInputRequest = deliveryService.prepareItemInventoryStatusInputDetails(uploadSignatureJsonReqAndRep.getDeliveryTicketId());
                    if(itemInventoryStatusInputRequest != null){
                        Boolean status = deliveryService.updateDeliveryStatus(itemInventoryStatusInputRequest);
                        if(status)
                            outcome.setMessage("Successfully uploaded the files and updated the item inventory details!!!");
                        else
                            outcome.setMessage("Something went wrong. Try after sometime!!!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Download Signature Files")
    @GetMapping(path = "/downloadSignatureFiles/{fileName:.+}")
    public ResponseEntity<Resource> downloadSignatureFiles(@PathVariable String fileName, HttpServletRequest request) {
        log.info("=======================GET(CONTROLLER) Downloading Signature Files==========================");
        String contentType = null;
        Resource resource = null;
        try {
            resource = deliveryService.loadFileAsSignatureResource(fileName);
            try {
                if(resource != null)
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                else
                    throw new MyFileNotFoundException("File not found= "+fileName);
            } catch (IOException ex) {
                log.info("Could not determine file type.");
            }
            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    @ApiOperation(value = "Download Delivery Document Files")
    @GetMapping(path = "/downloadDeliveryDocumentFiles/{filename:.+}")
    public ResponseEntity<Resource> downloadDeliveryDocumentFiles(@PathVariable String filename, String filetype, HttpServletRequest request) {
        log.info("=======================GET(CONTROLLER) Downloading Delivery Document Files==========================");
        String moduleName = "DELIVERY";
        String contentType = null;
        Resource resource = null;
        try {
            resource = fileDownloadUtilityService.loadFileAsNonReactiveDocumentResource(moduleName, filename, filetype);
            try {
                if(resource != null)
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                else
                    throw new MyFileNotFoundException("File not found= "+filename);
            } catch (IOException ex) {
                log.info("Could not determine file type.");
            }
            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    @ApiOperation(value = "Get Delivery Tickets on Sales Order")
    @GetMapping(path = "/getDeliveryTicketsOnSalesOrder")
    public ServiceOutcome<List<DeliveryTicketDTO>> getDeliveryTicketsOnSalesOrder(@RequestParam("salesOrderId") Long salesOrderId){
        return deliveryService.getDeliveryTicketsOnSalesOrder(salesOrderId);
    }

    @ApiOperation(value = "Get Delivery POD(Proof of Delivery) Documents")
    @GetMapping(path = "/getDeliveryPODDcocuments")
    public ServiceOutcome<List<DeliveryDocumentResponse>> getDeliveryPODDocuments(Long deliveryTicketId){
        return deliveryService.getDeliveryPODDocuments(deliveryTicketId);
    }

    @ApiOperation(value = "Get Delivery Documents")
    @GetMapping(path = "/getDeliveryDocuments")
    public ServiceOutcome<List<DeliveryDocumentsForm>> getDeliveryDocuments(Long deliveryTicketId){
        return deliveryService.getDeliveryDocuments(deliveryTicketId);
    }

    @ApiOperation(value = "Assign Delivery Agent")
    @PostMapping(path = "/assignDeliveryAgent")
    public ServiceOutcome<DeliveryAssignmentDTO> assignDeliveryAgent(@RequestBody DeliveryAssignInput deliveryAssignInput){
        log.info("=======================POST(CONTROLLER) Assign Delivery Agent==========================");
        ServiceOutcome<DeliveryAssignmentDTO> serviceOutcome = ValidationUtility.validateDeliveryTicketAssignmentRequestParams(deliveryAssignInput);
        if(serviceOutcome.getOutcome())
            return deliveryService.assignDeliveryAgent(deliveryAssignInput);
        else
            return new ServiceOutcome<>(serviceOutcome.getData(), serviceOutcome.getOutcome(), serviceOutcome.getMessage());
    }

    @ApiOperation(value = "Operation On Courier Type Delivery")
    @PostMapping(path = "/operationOnCourierTypeDelivery")
    public ServiceOutcome<DeliveryTicketDTO> operationOnCourierTypeDelivery(@RequestBody CourierTypeDeliveryInput courierTypeDeliveryInput){
        log.info("=======================POST(CONTROLLER) Operation On Courier Type Delivery==========================");
        ServiceOutcome<DeliveryTicketDTO> serviceOutcome = ValidationUtility.validateCourierTypeDeliveryRequestParams(courierTypeDeliveryInput);
        if(serviceOutcome.getOutcome())
            return deliveryService.operationOnCourierTypeDelivery(courierTypeDeliveryInput);
        else
            return new ServiceOutcome<>(serviceOutcome.getData(), serviceOutcome.getOutcome(), serviceOutcome.getMessage());
    }

    @ApiOperation(value = "Prepare Setup Method For Delivery")
    @PostMapping(path = "/prepareSetupMethodForDelivery")
    public ServiceOutcome<DeliveryTicketDTO> prepareSetupMethodForDelivery(@RequestBody SetupTechnicianInput setupTechnicianInput){
        log.info("=======================POST(CONTROLLER) Prepare Setup Method For Delivery=========================="+setupTechnicianInput.getSetupDateTime());
        ServiceOutcome<DeliveryTicketDTO> serviceOutcome = ValidationUtility.validateSetupTechnicianRequestParams(setupTechnicianInput);
        if(serviceOutcome.getOutcome()) {
            return deliveryService.prepareSetupMethodForDelivery(setupTechnicianInput);
        }
        else
            return new ServiceOutcome<>(serviceOutcome.getData(), serviceOutcome.getOutcome(), serviceOutcome.getMessage());
    }

    @ApiOperation(value = "Prepare Proof of Delivery Report")
    @PostMapping(path = "/prepareProofOfDeliveryReport")
    public ServiceOutcome<DeliveryDocumentResponse> prepareDeliveryDocumentProofOfDeliveryReport(@RequestParam("deliveryTicketId") Long deliveryTicketId) {
        log.info("=======================Code Starts Here=========================="+deliveryTicketId);
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = null;
        try {
            Optional<DeliveryTicket> deliveryTicket = deliveryService.getDeliveryTicketOnDeliveryTicket(deliveryTicketId);
            List<DeliveryItems> deliveryItems = deliveryService.getDeliveryItemsOnDeliveryTicket(deliveryTicketId);
            System.out.println("===================deliveryTicket==================="+deliveryTicket);
            serviceOutcome = deliveryService.prepareProofOfDeliveryReport(deliveryTicket.get(), deliveryItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation("Get All Delivery Assigned To Agent By Date")
    @GetMapping("/getDeliveryAssignedListByAgentDate")
    public ServiceOutcome<List<DeliveryAssignmentDTO>> getDeliveryAssignedListByAgentDate(@RequestParam("searchedAgentName") String searchedAgentName,
                                                                                          @RequestParam("startDate") String startDate,
                                                                                          @RequestParam("endDate") String endDate) throws ParseException {
        return deliveryService.getDeliveryAssignedListByAgentDate(searchedAgentName, startDate, endDate);
    }

    @ApiOperation("Get Delivery Assigned To Agent By Delivery Assigned")
    @GetMapping("/getDeliveryAssignedToAgentByDeliveryAssigned")
    public ServiceOutcome<DeliveryAssignmentDTO> getDeliveryAssignedToAgentByDeliveryAssigned(@RequestParam("deliveryAssignmentId") Long deliveryAssignmentId) {
        return deliveryService.getDeliveryAssignedToAgentByDeliveryAssigned(deliveryAssignmentId);
    }

    @ApiOperation("Get HCPCS Document List for Delivery Ticket")
    @GetMapping("/getHCPCSDocumentListOnDeliveryTicket")
    public ServiceOutcome<HCPCSDocumentData> getHCPCSDocumentListOnDeliveryTicket(@RequestParam("deliveryTicketId") Long deliveryTicketId){
        return deliveryService.getHCPCSDocumentListOnDeliveryTicket(deliveryTicketId);
    }

    @ApiOperation("Selected HCPCS Document List for Delivery Ticket")
    @PostMapping("/selectedHCPCSDocumentListOnDeliveryTicket")
    public ServiceOutcome<HCPCSDocumentData> selectedHCPCSDocumentListOnDeliveryTicket(@RequestBody String inputDocuments) throws JsonProcessingException {
        return deliveryService.selectedHCPCSDocumentListOnDeliveryTicket(inputDocuments);
    }

    @ApiOperation(value = "Posting Patient Communication Details")
    @PostMapping(value = "postPatientCommunicationDetails")
    public void postPatientCommunicationDetails(@RequestBody PatientCommunicationInputParams patientCommunicationInputParams){
        deliveryService.postPatientCommunicationDetails(patientCommunicationInputParams);
    }

    @ApiOperation(value = "Prepare PAP Setup Form Document")
    @PostMapping(path = "/preparePAPSetupDocumentReport")
    public ServiceOutcome<DeliveryDocumentResponse> preparePAPSetupDocumentReport(@RequestParam("deliveryTicketId") Long deliveryTicketId) {
        log.info("=======================Code Starts Here=========================="+deliveryTicketId);
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = null;
        CommonDeliveryInputData commonDeliveryInputData = null;
        try {
            Optional<DeliveryTicket> deliveryTicket = deliveryService.getDeliveryTicketOnDeliveryTicket(deliveryTicketId);
            List<DeliveryItems> deliveryItems = deliveryService.getDeliveryItemsOnDeliveryTicket(deliveryTicketId);
            System.out.println("===================deliveryTicket==================="+deliveryTicket);
            serviceOutcome = deliveryService.preparePAPSetupDocumentReport(deliveryTicket.get(), deliveryItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }
}
