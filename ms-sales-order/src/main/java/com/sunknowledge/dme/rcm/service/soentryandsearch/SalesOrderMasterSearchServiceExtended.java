package com.sunknowledge.dme.rcm.service.soentryandsearch;


import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterService;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCombinedSearchInternalDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchInternalDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public interface SalesOrderMasterSearchServiceExtended extends SalesOrderMasterService {

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreatedById(Integer createdById);

    Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByStatus(String status, String fromDate, String toDate);

    Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByBranchandCreatedDate(String branchName, String createdFromDate,
                                                                              String createdToDate);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByFacilityName(String facilityName);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySalesOrderNumber(String salesOrderNumber);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientBranchName(String branchname);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByPatientName(String patientName);

    Flux<SalesOrderMaster> getSODetailsByPatientId(Long patientId);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmedById(Integer confirmationbyid);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByConfirmationDetails(String confirmationByName, String status,
                                                                            String confirmationFromDate, String confirmationToDate);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsByCreationDetails(String confirmationByName, String status,
                                                                        String confirmationFromDate, String confirmationToDate);

    Flux<SalesOrderMasterSearchOutputDTO> getSODetailsBySOInfo(String salesOderNo, String deliveryScheduleDatetime,
                                                               String deliveryActualDatetime, String createdDate);

    Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsWithCombinedInformation(String patientName, String salesOderNo,
                                                                               String patientDOBDt, String startDateDt, String endDateDt, String status, String branchName,
                                                                               String primaryInsurerName, String patientAddress1, String facilityName);

    Mono<String> getsalesOrderNo();

    Flux<HashMap<String, Object>> getSalesOrderDetailsBySearchParameters(SalesOrderCommonSearchInternalDTO salesOrderCommonSearchInternalDTO);

    Flux<SalesOrderCommonSearchOutputDTO> getSalesOrderDetailsByCombinedParameters(SalesOrderCombinedSearchInternalDTO salesOrderCombinedSearchInternalDTO);
}
