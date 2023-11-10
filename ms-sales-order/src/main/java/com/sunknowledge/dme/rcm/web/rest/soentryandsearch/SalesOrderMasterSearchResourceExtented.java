package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCombinedSearchInternalDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCombinedSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.web.rest.SalesOrderMasterResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


/**
 * REST controller for SalesOrderMasterSearchResource.
 */
@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderMasterSearchResourceExtented {
    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchService;

//    /**
//     * Get SO Details with Created By Id
//     */
//    @GetMapping("/getSOByCreatedById")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreatedById(
//        @Min(value = 1, message = "Created_By_Id must be greater than or equal to 1")
//        @RequestParam("createdById") Integer createdById) {
//        return salesOrderMasterSearchService.getSODetailsByCreatedById(createdById);
//    }
//
//    /**
//     * Get SO Details By Status
//     */
//    @GetMapping(value = "/getSOByStatus")
//    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByStatus(
//        @NotBlank(message = "Status must be provided")
//        @RequestParam("status") String status,
//        @NotBlank(message = "From_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "From_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("fromDate") String fromDate,
//        @NotBlank(message = "To_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "To_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("toDate") String toDate
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByStatus(status, fromDate, toDate);
//    }
//
//    /**
//     * Get SO Details By Branch and Created Date
//     */
//    @GetMapping("/getSOByBranchAndCreatedDate")
//    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByBranchAndCreatedDate(
//        @NotBlank(message = "Branch_Name must be provided")
//        @RequestParam("branchName") String branchName,
//        @NotBlank(message = "Created_From_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_From_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdFromDate") String createdFromDate,
//        @NotBlank(message = "Created_To_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_To_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdToDate") String createdToDate
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByBranchandCreatedDate(branchName, createdFromDate, createdToDate);
//    }
//
//    /**
//     * Get SO Details By Facility Name
//     */
//    @GetMapping("/getSOByFacilityName")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByFacilityName(
//        @NotBlank(message = "Facility_Name must be provided")
//        @RequestParam("facilityName") String facilityName
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByFacilityName(facilityName);
//    }
//
//    /**
//     * Get SO Details By Sales Order Number
//     */
//    @GetMapping("/getSOBySONumber")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySalesOrderNumber(
//        @NotBlank(message = "Sales_Order_Number must be provided")
//        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Provide only alphanumeric characters for SO_Number")
//        @RequestParam("salesOrderNumber") String salesOrderNumber
//    ) {
//        return salesOrderMasterSearchService.getSODetailsBySalesOrderNumber(salesOrderNumber);
//    }
//
//    /**
//     * Get SO Details By Branch Name
//     */
//    @GetMapping("/getSOByBranchName")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientBranchName(
//        @NotBlank(message = "Branch_Name must be provided")
//        @RequestParam("branchname") String branchname
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByPatientBranchName(branchname);
//    }
//
//    /**
//     * Get SO Details By Patient Name
//     */
//    @GetMapping("/getSOByPatientName")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientName(
//        @NotBlank(message = "Patient_Name must be provided")
//        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Patient_Name")
//        @RequestParam("patientName") String patientName
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByPatientName(patientName);
//    }
//
//    /**
//     * Get SO Details By Patient Id
//     */
//    @GetMapping("/getSOByPatientId")
//    public Flux<SalesOrderMaster> getSODetailsByPatientId(
//        @Min(value = 1, message = "Patient_Id must be greater than or equal to 1")
//        @RequestParam("patientId") Long patientId
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByPatientId(patientId);
//    }
//
//    /**
//     * Get SO Details By Confirmation Id
//     */
//    @GetMapping("/getSOByConfirmationId")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmedById(
//        @Min(value = 1, message = "Confirmation_By_Id must be greater than or equal to 1")
//        @RequestParam("confirmationbyid") Integer confirmationbyid
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByConfirmedById(confirmationbyid);
//    }
//
//    /**
//     * Get SO Details By Confirmation Details
//     */
//    @GetMapping("/getSOByConfirmationDetails")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmationDetails(
//        @NotBlank(message = "Confirmation_by_Name must be provided")
//        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Confirmation_By_Name")
//        @RequestParam("confirmationbyName") String confirmationbyName,
//        @NotBlank(message = "Status must be provided")
//        @RequestParam("status") String status,
//        @NotBlank(message = "Created_From_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_From_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdFromDate") String createdFromDate,
//        @NotBlank(message = "Created_To_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_To_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdToDate") String createdToDate
//    ) {
//        return salesOrderMasterSearchService.getSODetailsByConfirmationDetails(confirmationbyName, status, createdFromDate,
//            createdToDate);
//    }
//
//    /**
//     * Get SO Details By Creation Details
//     */
//    @GetMapping("/getSOByCreationDetails")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreationDetails(
//        @NotBlank(message = "Created_by_Name must be provided")
//        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Created_By_Name")
//        @RequestParam("createdbyName") String createdbyName,
//        @NotBlank(message = "Status must be provided")
//        @RequestParam("status") String status,
//        @NotBlank(message = "Created_From_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_From_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdFromDate") String createdFromDate,
//        @NotBlank(message = "Created_To_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_To_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdToDate") String createdToDate) {
//        return salesOrderMasterSearchService.getSODetailsByCreationDetails(createdbyName, status, createdFromDate,
//            createdToDate);
//    }
//
//    /**
//     * Get SO Details By SO Info
//     */
//    @GetMapping("/getSOBySOInfo")
//    public Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySOInfo(
//        @NotBlank(message = "Sales_Order_Number must be provided")
//        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Provide only alphanumeric characters for SO_Number")
//        @RequestParam("salesOderNo") String salesOrderNo,
//        @NotBlank(message = "Delivery_Schedule_Datetime must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Delivery_Schedule_Date_Time accepts only 'dd-mm-yyyy' format")
//        @RequestParam("deliveryScheduleDatetime") String deliveryScheduleDatetime,
//        @NotBlank(message = "Delivery_Actual_Datetime must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Delivery_Actual_Date_Time accepts only 'dd-mm-yyyy' format")
//        @RequestParam("deliveryActualDatetime") String deliveryActualDatetime,
//        @NotBlank(message = "Created_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Created_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("createdDate") String createdDate) {
//        Flux<SalesOrderMasterSearchOutputDTO> salesOrderMaster = salesOrderMasterSearchService.getSODetailsBySOInfo(salesOrderNo,
//            deliveryScheduleDatetime, deliveryActualDatetime, createdDate);
//        return salesOrderMaster;
//    }
//
//    /**
//     * Get SO Details With Combined Information
//     */
//    @GetMapping("/getSOByCombinedInformation")
//    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsWithCombinedInformation(
//        @NotBlank(message = "Patient_Name must be provided")
//        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Provide only appropriate Patient_Name")
//        @RequestParam("patientName") String patientName,
//        @NotBlank(message = "Sales_Order_Number must be provided")
//        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Provide only alphanumeric characters for SO_Number")
//        @RequestParam("salesOderNo") String salesOrderNo,
//        @NotBlank(message = "Patient_DOB must be a date in the past")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Patient_DOB accepts only 'dd-mm-yyyy' format")
//        @RequestParam("patientDOB") String patientDOB,
//        @NotBlank(message = "Start_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "Start_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("startDate") String startDate,
//        @NotBlank(message = "End_Date must be provided")
//        @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\-)(((0)[0-9])|((1)[0-2]))(\\-)\\d{4}$", message = "End_Date accepts only 'dd-mm-yyyy' format")
//        @RequestParam("endDate") String endDate,
//        @NotBlank(message = "Status must be provided")
//        @RequestParam("status") String status,
//        @NotBlank(message = "Branch_Name must be provided")
//        @RequestParam("branchName") String branchName,
//        @NotBlank(message = "Primary_Insurer_Name must be provided")
//        @RequestParam("primaryInsurerName") String primaryInsurerName,
//        @NotBlank(message = "Patient_Address1 must be provided")
//        @RequestParam("patientAddress1") String patientAddress1,
//        @NotBlank(message = "Facility_Name must be provided")
//        @RequestParam("facilityName") String facilityName) {
//
//        return salesOrderMasterSearchService.getSODetailsWithCombinedInformation(patientName, salesOrderNo, patientDOB,
//            startDate, endDate, status, branchName, primaryInsurerName, patientAddress1, facilityName);
//    }
//
//    /**
//     * Sales Order Common Search (Dynamic Way - Combined or Single)
//     */
//    @GetMapping("/getSalesOrderDetailsBySearchParameters")
//    public Flux<HashMap<String, Object>> getSalesOrderDetailsBySearchParameters(
//        @Valid @ModelAttribute SalesOrderCommonSearchParameterDTO SalesOrderCommonSearchParameterDTO
//    ) {
//        SalesOrderCommonSearchInternalDTO salesOrderCommonSearchInternalDTO =
//            new SalesOrderCommonSearchInternalDTO();
//        BeanUtils.copyProperties(SalesOrderCommonSearchParameterDTO, salesOrderCommonSearchInternalDTO);
//        salesOrderCommonSearchInternalDTO.setBranchID(13L); //==== Should be taken from Login ====
//
//        return salesOrderMasterSearchService.getSalesOrderDetailsBySearchParameters(salesOrderCommonSearchInternalDTO);
//    }

    /**
     * Sales Order Combined Search (Combined or Single) --- By Database Function
     */
    @GetMapping(value = "/getSalesOrderDetailsByCombinedParameters")
    public Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsByCombinedParameters(SalesOrderCombinedSearchParameterDTO salesOrderCombinedSearchParameterDTO) {
        SalesOrderCombinedSearchInternalDTO salesOrderCombinedSearchInternalDTO =
            new SalesOrderCombinedSearchInternalDTO();
        CommonUtilities.dtoTrimmer(salesOrderCombinedSearchInternalDTO);
        BeanUtils.copyProperties(salesOrderCombinedSearchParameterDTO, salesOrderCombinedSearchInternalDTO);
        //==== Should be taken from Login ====
        salesOrderCombinedSearchInternalDTO.setBranchID(salesOrderCombinedSearchParameterDTO.getBranchID());

        return salesOrderMasterSearchService.getSalesOrderDetailsByCombinedParameters(salesOrderCombinedSearchInternalDTO);
    }

}
