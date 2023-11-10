package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.application.applicationstatus.DefineStatus;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.dto.cmn.SalesOrderItemCmnSubroutineDTO;
import com.sunknowledge.dme.rcm.dto.par.SearchPARInputParameters;
import com.sunknowledge.dme.rcm.dto.soItemDetails.ItemDefaultVendorResponseDTO;
import com.sunknowledge.dme.rcm.dto.soItemDetailsAndClicnicalAndInsurance.SoItemDetailsAndClinicalAndInsuranceResponseData;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.par.ParMasterRepo;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.abn.AbnDataDeliveryService;
import com.sunknowledge.dme.rcm.service.cmn.CMNService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.DropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.RemoveDropshipParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.SOItemInventoryTransactionDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderItemDetailsEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderItemDetailsMapper;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Primary
@Service
//@Transactional
public class SalesOrderItemDetailsServiceExtendedImpl implements SalesOrderItemDetailsServiceExtended {

    @Autowired
    SalesOrderItemDetailsRepositoryExtended salesOrderItemDetailsRepositoryExtended;

    @Autowired
    SalesOrderItemDetailsMapper salesOrderItemDetailsMapper;

    @Autowired
    CMNService cmnService;

    @Autowired
    AbnDataDeliveryService abnDataDeliveryService;

    @Autowired
    PriorAuthorizationService priorAuthorizationService;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Autowired
    private ParMasterRepo parMasterRepository;

    @Autowired
    SalesOrderMasterRepo salesOrderMasterRepository;

    @Override
    public Mono<SalesOrderItemDetailsDTO> save(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> update(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> partialUpdate(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderItemDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderItemDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    //@Transactional(readOnly = true)
    public Flux<SalesOrderItemDetails> findBySalesOrderId(Long SOID) {
        return salesOrderItemDetailsRepositoryExtended.findBySalesOrderId(SOID);
    }

    @Override
    //@Transactional(readOnly = true)
    public Flux<SalesOrderItemDetails> getSOItemDetailsBySOItemDetailsUUID(UUID sOItemDetailsUUID) {
        return salesOrderItemDetailsRepositoryExtended.getSOItemDetailsBySOItemDetailsUUID(sOItemDetailsUUID);
    }

    @Override
    //@Transactional(readOnly = true)
    public Mono<Long> getIDByUUID(UUID sOItemDetailsUUID) {
        return salesOrderItemDetailsRepositoryExtended.getIDByUUID(sOItemDetailsUUID);
    }

    @Override
    //@Transactional(readOnly = true)
    public Mono<SalesOrderItemDetails> findById(Long id) {
        return salesOrderItemDetailsRepositoryExtended.findById(id);
    }

    //    @Override
    public Mono<SalesOrderItemDetails> getCMNSubroutineDataForSalesOrderItemDetails(SalesOrderItemCmnSubroutineDTO salesOrderItemCmnSubroutineDTO) {
        System.out.println("=====> Before Calling searchCMNForSalesOrder");
        return cmnService.searchCMNForSalesOrder(salesOrderItemCmnSubroutineDTO.getPatientId(),
                salesOrderItemCmnSubroutineDTO.getSalesOrderId(),
                salesOrderItemCmnSubroutineDTO.getDos(),
                salesOrderItemCmnSubroutineDTO.getItemNo(),
                salesOrderItemCmnSubroutineDTO.getOrderingProviderNpi()
            ).flatMap(cmn -> {
                System.out.println("=====> After Calling searchCMNForSalesOrder");
                return salesOrderItemDetailsRepositoryExtended.findById(salesOrderItemCmnSubroutineDTO.getSoItemDetailsId())
                    .map(existingObj -> {
                        System.out.println("=====> searchCMNForSalesOrder :: After CMN Update: Sales Order Item Details =>" + existingObj);
//                        CmnSearchResponse cmnSearchResponseObj = serviceOutcome.getData() != null ? serviceOutcome.getData() : new CmnSearchResponse();
                        Long cmnId = cmn.getCmnId() != null ? cmn.getCmnId() : 0;
                        String cmnNumber = cmn.getCmnNumber() != null ? cmn.getCmnNumber() : "";
                        System.out.println("=====> searchCMNForSalesOrder :: After CMN Update: cmnNumber =>" + cmnNumber);

                        existingObj.setCmnId(cmnId);
                        existingObj.setSalesOrderDetailsCmnparCmnKey(cmnNumber);
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedById(31L);         // --------------- From User Details
                        existingObj.setUpdatedByName("Falguni");  // --------------- From User Details

                        System.out.println("=====> searchCMNForSalesOrder :: After CMN Update: updated DTO =>" + cmnNumber);
                        return existingObj;
                    })
                    .flatMap(salesOrderItemDetailsRepositoryExtended::save);
            })
            .switchIfEmpty(createCMNForSalesOrder(salesOrderItemCmnSubroutineDTO));
    }

    private Mono<SalesOrderItemDetails> createCMNForSalesOrder(SalesOrderItemCmnSubroutineDTO salesOrderItemCmnSubroutineDTO) {
//        CmnDTO cmnDto = new CmnDTO();
        Cmn saveCmn = new Cmn();
//        saveCmn.setCmnNumber(cmnRepository.getCmnNumberSequence().toFuture().get());
        saveCmn.setCmnType("Generic/Generated");
        saveCmn.setCmnFormName("Generic/Initial");
        saveCmn.setPatientId(salesOrderItemCmnSubroutineDTO.getPatientId());
        saveCmn.setSalesOrderId(salesOrderItemCmnSubroutineDTO.getSalesOrderId());
        saveCmn.salesOrderNo(salesOrderItemCmnSubroutineDTO.getSalesOrderNo());
        saveCmn.setCreatedById(0L);
        saveCmn.setCreatedByName("System");
        saveCmn.setCreatedDate(LocalDate.now());
        saveCmn.setStatus(DefineStatus.Active.name());
        saveCmn.setCmnStatus("initiated");
//        cmnDto.setSalesOrderId(salesOrderItemCmnSubroutineDTO.getSoItemDetailsId());
        return cmnService.createCMNForSalesOrder(saveCmn).flatMap(cmn -> {
            System.out.println("=====> After Calling createCMNForSalesOrder");
            return salesOrderItemDetailsRepositoryExtended.findById(salesOrderItemCmnSubroutineDTO.getSoItemDetailsId())
                .map(existingObj -> {
                    System.out.println("=====> createCMNForSalesOrder :: After CMN Update: Sales Order Item Details =>" + existingObj);
                    Long cmnId = cmn.getCmnId() != null ? cmn.getCmnId() : 0;
                    String cmnNumber = cmn.getCmnNumber() != null ? cmn.getCmnNumber() : "";
                    System.out.println("=====> createCMNForSalesOrder :: After CMN Update: cmnNumber =>" + cmnNumber);

                    existingObj.setCmnId(cmnId);
                    existingObj.setSalesOrderDetailsCmnparCmnKey(cmnNumber);
                    existingObj.setUpdatedDate(LocalDate.now());
                    existingObj.setUpdatedById(31L);         // --------------- From User Details
                    existingObj.setUpdatedByName("Falguni");  // --------------- From User Details

                    System.out.println("=====> createCMNForSalesOrder :: After CMN Update: updated DTO =>" + cmnNumber);
                    return existingObj;
                })
                .flatMap(salesOrderItemDetailsRepositoryExtended::save);
        });
    }

    //@Transactional(readOnly = true)
    public ServiceOutcome<List<DeliveryAbnData>> getabnData(Long soid, String item_proc) throws ExecutionException, InterruptedException {
        ServiceOutcome<List<DeliveryAbnData>> serviceOutcome;
        serviceOutcome = abnDataDeliveryService.getabnData(soid, item_proc);
        return serviceOutcome;
    }

    public Mono<ServiceOutcome> abnCreationAndTagging(Long soid, String item_proc, String abn_reason, String abn_modifier_1,
                                                      String abn_modifier_2, Long uid,
                                                      String uname, Long soItemDetailsId) throws ExecutionException, InterruptedException {
        System.out.println("=======In ABN Creation Service=======");
        //Mono<DeliveryAbnData> abndata = abnDataDeliveryService.abnCreation(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid, uname);
        Mono<Mono<ServiceOutcome>> monoObj = abnDataDeliveryService.abnCreationUsingDbFunction(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid, uname)
            .map(abnObj -> {
                return salesOrderItemDetailsRepositoryExtended.findById(soItemDetailsId)
                    .map(existingObj -> {
                        existingObj.setSalesOrderDetailsAbn(abnObj.getAbnNo());
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedById(31L);
                        existingObj.setUpdatedByName("Falguni");
                        return existingObj;
                    })
                    .flatMap(salesOrderItemDetailsRepositoryExtended::save)
                    .map(updatedObj -> new ServiceOutcome(List.of(updatedObj), true, "ABN successfully created and tagged."));
            });
        return monoObj.toFuture().get();
    }

    public Mono<ServiceOutcome> abnTagging(Long soItemDetailsId, String abnNumber) {
        return salesOrderItemDetailsRepositoryExtended.findById(soItemDetailsId)
            .map(existingObj -> {
                existingObj.setSalesOrderDetailsAbn(abnNumber);
                existingObj.setUpdatedDate(LocalDate.now());
                existingObj.setUpdatedById(31L);
                existingObj.setUpdatedByName("Falguni");
                return existingObj;
            })
            .flatMap(salesOrderItemDetailsRepositoryExtended::save)
            .map(updatedObj -> new ServiceOutcome(List.of(updatedObj), true, "ABN successfully tagged."));
    }

    //@Transactional(readOnly = true)
    public Mono<ServiceOutcome<SoItemDetailsAndClinicalAndInsuranceResponseData>> checkDataAvailabilityOfSOMasterAndClinicalAndInsurance(Long soId) throws ExecutionException, InterruptedException {
        if (soId != null) {
            return salesOrderItemDetailsRepositoryExtended.findSoItemDetailsAndClinicalAndInsuranceDataBySoId(soId, "active").map(responseObj -> {
                if (responseObj != null) {
                    if (Objects.isNull(responseObj.getSales_order_id())) {
                        return new ServiceOutcome<>(null, false, "Sales Order Id should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getSales_order_no()) || responseObj.getSales_order_no().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Sales Order No should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getPatient_id())) {
                        return new ServiceOutcome<>(null, false, "Patient Id should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getPatient_first_name()) || responseObj.getPatient_first_name().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Patient First Name should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getPatient_last_name()) || responseObj.getPatient_last_name().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Patient Last Name should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getPatient_id_no()) || responseObj.getPatient_id_no().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Patient Id No should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getOrdering_provider_id())) {
                        return new ServiceOutcome<>(null, false, "Ordering Provider Id should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getOrdering_provider_first_name()) || responseObj.getOrdering_provider_first_name().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Ordering Provider First Name should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getOrdering_provider_last_name()) || responseObj.getOrdering_provider_last_name().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Ordering Provider Last Name should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getOrdering_provider_npi()) || responseObj.getOrdering_provider_npi().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Ordering Provider Npi should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getPrimary_diagnosis()) || responseObj.getPrimary_diagnosis().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Primary Diagnosis should not be null/blank/empty");
                    } else if (Objects.isNull(responseObj.getDiagnosis_code_type()) || responseObj.getDiagnosis_code_type().trim().isEmpty()) {
                        return new ServiceOutcome<>(null, false, "Diagnosis Code Type should not be null/blank/empty");
                    } else if (Objects.nonNull(responseObj.getPrimary_insurer_id()) && (Objects.nonNull(responseObj.getPrimary_insurer_name()) && !responseObj.getPrimary_insurer_name().trim().isEmpty())) {
                        if (Objects.isNull(responseObj.getPrice_table_id()) && (Objects.isNull(responseObj.getPrice_table_name()) || responseObj.getPrice_table_name().trim().isEmpty()))
                            return new ServiceOutcome<>(null, false, "Price Table Id and Price Table Name should not be null/blank/empty");
                    }

                    return new ServiceOutcome<>(responseObj, true, "Allow for process.");

                } else {
                    return new ServiceOutcome<>(null, false, "Sales Order Not Found.");
                }
            });
        } else {
            return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "So Id must be provided."));
        }
    }

    @Override
    public Mono<ParMaster> getPARDataForSalesOrderItemDetails(SearchPARInputParameters searchPARInputParameters) {
        System.out.println("In :: getPARDataForSalesOrderItemDetails");

        LocalDate localDateDos = (searchPARInputParameters.getDos() != null) ? searchPARInputParameters.getDos()
            : LocalDate.now();

        System.out.println("getPARDataForSalesOrderItemDetails :: localDateDos=" + localDateDos);
        return salesOrderItemDetailsRepositoryExtended.linkParForSO(
                searchPARInputParameters.getInsuranceid(),
                searchPARInputParameters.getInsurancename(),
                searchPARInputParameters.getPayercontactname(),
                searchPARInputParameters.getPayercontactnumber(),
                searchPARInputParameters.getPayeridno(),
                searchPARInputParameters.getPolicynumber(),
                searchPARInputParameters.getPolicystartdate(),
                searchPARInputParameters.getPolicyenddate(),
                searchPARInputParameters.getEffectivestartdate(),
                searchPARInputParameters.getPatientidno(),
                searchPARInputParameters.getPatientid(),
                searchPARInputParameters.getPatientdob(),
                searchPARInputParameters.getPatientfirstname(),
                searchPARInputParameters.getPatientlastname(),
                searchPARInputParameters.getPatientgender(),
                searchPARInputParameters.getItemid(),
                searchPARInputParameters.getItemno(),
                searchPARInputParameters.getItemname(),
                searchPARInputParameters.getItemquantity(),
                searchPARInputParameters.getItemuom(),
                searchPARInputParameters.getDescription(),
                searchPARInputParameters.getCreatedbyid(),
                searchPARInputParameters.getCreatedbyname(),
//                            searchPARInputParameters.getDos(),
                localDateDos,
                searchPARInputParameters.getHcpcsno(),
                searchPARInputParameters.getPayerlevel(),
                searchPARInputParameters.getUseexistingpar(),
                searchPARInputParameters.getSoid(),
                searchPARInputParameters.getSono(),
                searchPARInputParameters.getPricetableid()
            )
            .map(parMaster -> {
                System.out.println("After PAR Link calling ...... par id: " + parMaster.getParId());
                System.out.println("After PAR Link calling ...... par no: " + parMaster.getParIdNo());
                return parMaster;
            })
            .switchIfEmpty(Mono.just(new ParMaster()));

    }

    @Override
    public Mono<ResponseDTO> saveSOItemDetails(SalesOrderItemDetailsDTO obj, SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO,
                                               SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns) {
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();

        try {

            if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderDetailsBqty()
                <= salesOrderItemDetailsEntryParameterDTO.getSalesOrderDetailsQty() &&
                salesOrderItemDetailsEntryParameterDTO.getSalesOrderDetailsQty()
                    <= (salesOrderItemDetailsEntryParameterDTO.getOnhandQty() -
                    (salesOrderItemDetailsEntryParameterDTO.getInorderQty() +
                        salesOrderItemDetailsEntryParameterDTO.getCommittedQty()))) {
                if (fetchMasterClinicalIns != null) {
                    //----------- Hardcoded Values ----------------
                    obj.setSalesOrderDetailsIcdPointer("1");
                    obj.setSalesOrderDetailsIcdPointer("H");
                    //----------- Hardcoded Values ----------------

                    obj.setSalesOrderDetailsLineQty(obj.getSalesOrderDetailsQty());
                    obj.setPatientId(fetchMasterClinicalIns.getPatient_id());

                    //------------Saving Data-----------------------------------------------------
                    if (obj.getSalesOrderItemDetailsId() == null || obj.getSalesOrderItemDetailsId() == 0) {
                        obj.setCreatedDate(LocalDate.now());
                        obj.setCreatedById(1L);   //----- Data taken from login user service
                        obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                        obj.setSalesOrderItemDetailsId(null);
                        obj.setStatus("active");
                        obj.setSalesOrderItemDetailsUuid(UUID.randomUUID());
//                        Mono<SalesOrderItemDetails> orderItemDetails = salesOrderItemDetailsRepositoryExtended
//                            .save(salesOrderItemDetailsMapper.toEntity(obj));
                        System.out.println("====> Before Save SalesOrderItems 1");
                        return salesOrderItemDetailsRepositoryExtended
                            .save(salesOrderItemDetailsMapper.toEntity(obj)).flatMap(
                                i -> {
                                    System.out.println("=====> After Save SalesOrderItems :: " + i);
                                    System.out.println("Inside responseDTOMono If");
                                    //ResponseDTO responseDTO = responseDTOMono.toFuture().get();

                                    System.out.println("Inside responseDTO getData If");
                                    SalesOrderItemDetailsDTO savedSOItemDetails = salesOrderItemDetailsMapper.toDto(i);
                                    SOItemInventoryTransactionDTO soItemInventoryTransactionDTO = new SOItemInventoryTransactionDTO();
                                    soItemInventoryTransactionDTO.setSalesOrderNo(fetchMasterClinicalIns.getSales_order_no());
                                    soItemInventoryTransactionDTO.setSaleType(savedSOItemDetails.getSalesOrderDetailsSaleType());
                                    soItemInventoryTransactionDTO.setItemId(savedSOItemDetails.getSalesOrderDetailsItemId());
                                    soItemInventoryTransactionDTO.setItemNo(savedSOItemDetails.getSalesOrderItemNumber());
                                    soItemInventoryTransactionDTO.setItemName(savedSOItemDetails.getSalesOrderDetailsItemName());
                                    soItemInventoryTransactionDTO.setItemUom(savedSOItemDetails.getSalesOrderDetailsStockingUom());
                                    soItemInventoryTransactionDTO.setItemQty(savedSOItemDetails.getSalesOrderDetailsQty());
                                    soItemInventoryTransactionDTO.setWheatherSerialized(savedSOItemDetails.getWhetherSerialised());
                                    soItemInventoryTransactionDTO.setSerialNo(savedSOItemDetails.getItemSerialNo() != null ? savedSOItemDetails.getItemSerialNo().toString() : "");
                                    soItemInventoryTransactionDTO.setWheatherAssetTagged(salesOrderItemDetailsEntryParameterDTO.getIsAssetTagged());
                                    soItemInventoryTransactionDTO.setOriginalDos(savedSOItemDetails.getSalesOrderDetailsOriginalDos());
                                    soItemInventoryTransactionDTO.setBranchId(fetchMasterClinicalIns.getBranch_id());
                                    soItemInventoryTransactionDTO.setLocationId(savedSOItemDetails.getItemLocationId());
                                    soItemInventoryTransactionDTO.setLocationName(salesOrderItemDetailsEntryParameterDTO.getLocationName());

                                    System.out.println("soItemInventoryTransactionDTO => " + soItemInventoryTransactionDTO);

                                    return soItemInventoryTransactionUpdate(soItemInventoryTransactionDTO)
                                        .flatMap(x -> {
                                            System.out.println("DB Function Output:: " + x);
                                            if (x.equalsIgnoreCase("success")) {

                                                //============= CMN Integration ==============================
                                                SalesOrderItemCmnSubroutineDTO salesOrderItemCmnSubroutineDTO = new SalesOrderItemCmnSubroutineDTO();
                                                salesOrderItemCmnSubroutineDTO.setItemNo(obj.getSalesOrderItemNumber());
                                                salesOrderItemCmnSubroutineDTO.setSalesOrderNo(fetchMasterClinicalIns.getSales_order_no());
                                                salesOrderItemCmnSubroutineDTO.setSoItemDetailsId(savedSOItemDetails.getSalesOrderItemDetailsId());
                                                salesOrderItemCmnSubroutineDTO.setDos(obj.getSalesOrderDetailsOriginalDos().toString());
                                                salesOrderItemCmnSubroutineDTO.setOrderingProviderNpi(fetchMasterClinicalIns.getOrdering_provider_npi());
                                                salesOrderItemCmnSubroutineDTO.setPatientId(fetchMasterClinicalIns.getPatient_id());
                                                salesOrderItemCmnSubroutineDTO.setSalesOrderId(fetchMasterClinicalIns.getSales_order_id());
                                                System.out.println("Before CMN Calling.");
                                                return getCMNSubroutineDataForSalesOrderItemDetails(salesOrderItemCmnSubroutineDTO)
                                                    .map(j -> {

                                                        //============= PAR Integration ==============================
                                                        String useExistingPAR = (salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR() == null ||
                                                            salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR().trim().equals(""))
                                                            ? "No" : salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR();

                                                        SearchPARInputParameters searchPARInputParameters = new SearchPARInputParameters();
                                                        searchPARInputParameters.setInsuranceid(fetchMasterClinicalIns.getPrimary_insurer_id());
                                                        searchPARInputParameters.setInsurancename(fetchMasterClinicalIns.getPrimary_insurer_name());
                                                        searchPARInputParameters.setPayercontactname(fetchMasterClinicalIns.getPrimary_insurer_contact_name());
                                                        searchPARInputParameters.setPayercontactnumber(fetchMasterClinicalIns.getPrimary_insurer_contact_1());
                                                        searchPARInputParameters.setPayeridno(fetchMasterClinicalIns.getPrimary_insurance_payer_id());
                                                        searchPARInputParameters.setPolicynumber(fetchMasterClinicalIns.getPrimary_insurer_policy_no());
                                                        searchPARInputParameters.setPolicystartdate(fetchMasterClinicalIns.getPrimary_insurer_effective_date());
                                                        searchPARInputParameters.setPolicyenddate(null);
                                                        searchPARInputParameters.setEffectivestartdate(fetchMasterClinicalIns.getPrimary_insurer_effective_date());
                                                        searchPARInputParameters.setPatientidno(fetchMasterClinicalIns.getPatient_id_no());
                                                        searchPARInputParameters.setPatientid(fetchMasterClinicalIns.getPatient_id());
                                                        searchPARInputParameters.setPatientdob(fetchMasterClinicalIns.getPatient_dob());
                                                        searchPARInputParameters.setPatientfirstname(fetchMasterClinicalIns.getPatient_first_name() + " " + fetchMasterClinicalIns.getPatient_middle_name());
                                                        searchPARInputParameters.setPatientlastname(fetchMasterClinicalIns.getPatient_last_name());
                                                        searchPARInputParameters.setPatientgender(fetchMasterClinicalIns.getPatient_gender());
                                                        searchPARInputParameters.setItemid(i.getSalesOrderDetailsItemId());
                                                        searchPARInputParameters.setItemno(i.getSalesOrderItemNumber());
                                                        searchPARInputParameters.setItemname(i.getSalesOrderDetailsItemName());
                                                        searchPARInputParameters.setItemquantity(i.getSalesOrderDetailsQty());
                                                        searchPARInputParameters.setItemuom(i.getSalesOrderDetailsStockingUom());
                                                        searchPARInputParameters.setDescription(i.getSalesOrderDetailsItemDescription());
                                                        searchPARInputParameters.setCreatedbyid(1L);
                                                        searchPARInputParameters.setCreatedbyname("Abhijit Chowdhury");
                                                        searchPARInputParameters.setDos(i.getSalesOrderDetailsOriginalDos());
                                                        searchPARInputParameters.setHcpcsno(i.getSalesOrderDetailsProcCode());
                                                        searchPARInputParameters.setPayerlevel("primary");
                                                        searchPARInputParameters.setUseexistingpar(useExistingPAR);
                                                        searchPARInputParameters.setSoid(i.getSalesOrderId());
                                                        searchPARInputParameters.setSono(fetchMasterClinicalIns.getSales_order_no());
                                                        searchPARInputParameters.setPricetableid(fetchMasterClinicalIns.getPrice_table_id());

                                                        System.out.println("Before getPARDataForSalesOrderItemDetails ");
                                                        Mono<ParMaster> parResponse = getPARDataForSalesOrderItemDetails(searchPARInputParameters);
                                                        return parResponse.map(k -> {
                                                            System.out.println("After Success: getPARDataForSalesOrderItemDetails: " + k);
                                                            if (k.getParIdNo() != null) {
                                                                j.setParNo(k.getParIdNo());
                                                                j.setSalesOrderDetailsCmnparParId(String.valueOf(k.getParId()));
                                                                return new ResponseDTO(true, "Successfully Saved with CMN and PAR Linked.", List.of(j));
                                                            } else {
                                                                return new ResponseDTO(true, "Successfully Saved with CMN Linked. But PAR is not associated with this item.", List.of(j));
                                                            }
                                                        }).switchIfEmpty(Mono.just(new ResponseDTO(true, "Successfully Saved with CMN Linked. But PAR is not associated with this item.", List.of(j))));
                                                        //============= PAR Integration ==============================
                                                    })
                                                    .flatMap(obj1 -> obj1)
                                                    .switchIfEmpty(Mono.just(new ResponseDTO(true, "Successfully Saved. But CMN is not associated with this item.", List.of(i))));
                                                //============= CMN Integration ==============================
                                            } else {
                                                return Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>()));
                                            }
                                        })
                                        .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>())));
                                })
                            .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>())));

                    } else {
                        System.out.println("====== Inside the Update Section (Sales Order Item Details) =====");
                        List<SalesOrderItemDetails> salesOrderItemDetailsForUpdate = salesOrderItemDetailsRepositoryExtended.
                            getSOItemDetailsBySOItemDetailsUUID(obj.getSalesOrderItemDetailsUuid()).collectList().toFuture().get();
                        if (salesOrderItemDetailsForUpdate.size() > 0) {
                            SalesOrderItemDetails updateObj = salesOrderItemDetailsForUpdate.get(0);
                            BeanUtils.copyProperties(salesOrderItemDetailsEntryParameterDTO, updateObj);
                            updateObj.setUpdatedDate(LocalDate.now());
                            updateObj.setUpdatedById(1L);      //----- Data taken from login user service
                            updateObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                            return salesOrderItemDetailsRepositoryExtended
                                .save(salesOrderItemDetailsMapper.toEntity(obj))
                                .map(salesOrderItemDetailsMapper::toDto).map(
                                    i -> new ResponseDTO(true, "Successfully Saved", List.of(i)))
                                .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>())));
                        } else {
                            message = "Update failed! Sales Order Item does not exist.";
                            return Mono.just(new ResponseDTO(status, message, responseList));
                        }
                    }
                    //-----------------------------Saving Data----------------------------------------------
                } else {
                    message = "Sales Order Not Found.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            } else {
                message = "Insufficient Quantity.";
                return Mono.just(new ResponseDTO(status, message, responseList));
            }

        } catch (Exception e) {
            // Client-side errors (4xx)
            throw new RuntimeException(e);
        }
//        return null;
    }

    //    public ServiceOutcome<ItemDefaultVendorResponseDTO> getItemDefaultVendorByItemId(Long itemId) throws ExecutionException, InterruptedException {
    @Override
    public Mono<ResponseDTO> saveSOItemDetailsWithDropship(SalesOrderItemDetailsDTO obj, SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO, SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns) {
        try {

            if (salesOrderItemDetailsEntryParameterDTO.getOnhandQty() == 0) {
                DropshipPurchaseOrderParameterDTO dropshipPurchaseOrderParameterDTO = new DropshipPurchaseOrderParameterDTO();
                dropshipPurchaseOrderParameterDTO.setSoId(fetchMasterClinicalIns.getSales_order_id());
                dropshipPurchaseOrderParameterDTO.setSoNo(fetchMasterClinicalIns.getSales_order_no());

                dropshipPurchaseOrderParameterDTO.setBranchId(fetchMasterClinicalIns.getBranch_id());
                dropshipPurchaseOrderParameterDTO.setBranchName(fetchMasterClinicalIns.getBranch_name());
                dropshipPurchaseOrderParameterDTO.setBillingAddressLine1(fetchMasterClinicalIns.getBranch_address_line_1());
                dropshipPurchaseOrderParameterDTO.setBillingAddressLine2(fetchMasterClinicalIns.getBranch_address_line_2());
                dropshipPurchaseOrderParameterDTO.setBillingAddressCity(fetchMasterClinicalIns.getBranch_city());
                dropshipPurchaseOrderParameterDTO.setBillingAddressZip(fetchMasterClinicalIns.getBranch_zip_code());
                dropshipPurchaseOrderParameterDTO.setBillingAddressState(fetchMasterClinicalIns.getBranch_state());
                dropshipPurchaseOrderParameterDTO.setBillingContactNo(fetchMasterClinicalIns.getBranch_contact_no_1());
                dropshipPurchaseOrderParameterDTO.setBillingContactName(fetchMasterClinicalIns.getBranch_contact_person_name());
                dropshipPurchaseOrderParameterDTO.setBillingContactEmail("");

                dropshipPurchaseOrderParameterDTO.setDeliveryAddressLine1(fetchMasterClinicalIns.getPatient_address_line_1());
                dropshipPurchaseOrderParameterDTO.setDeliveryAddressLine2(fetchMasterClinicalIns.getPatient_address_line_2());
                dropshipPurchaseOrderParameterDTO.setDeliveryAddressCity(fetchMasterClinicalIns.getCity_name());
                dropshipPurchaseOrderParameterDTO.setDeliveryAddressState(fetchMasterClinicalIns.getState_name());
                dropshipPurchaseOrderParameterDTO.setDeliveryAddressZip(fetchMasterClinicalIns.getZip_code());
                dropshipPurchaseOrderParameterDTO.setDeliveryContactNo(fetchMasterClinicalIns.getPatient_contact_no_1());
                dropshipPurchaseOrderParameterDTO.setDeliveryContactName(fetchMasterClinicalIns.getContact_person_name());
                dropshipPurchaseOrderParameterDTO.setDeliveryContactEmail(fetchMasterClinicalIns.getPatient_email());

                dropshipPurchaseOrderParameterDTO.setItemId(obj.getSalesOrderDetailsItemId());
                dropshipPurchaseOrderParameterDTO.setVendorId(salesOrderItemDetailsEntryParameterDTO.getVendorId());
                dropshipPurchaseOrderParameterDTO.setItemQty(obj.getSalesOrderDetailsQty());
                dropshipPurchaseOrderParameterDTO.setItemprices(obj.getSalesOrderDetailsChargeAmt().toString());
                dropshipPurchaseOrderParameterDTO.setWhetherSerialised(obj.getWhetherSerialised());
                dropshipPurchaseOrderParameterDTO.setLocationId(obj.getItemLocationId());

                Map dropshipPurchaseOrderParameterDTOMap = new HashMap();
                Field[] fields = dropshipPurchaseOrderParameterDTO.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    dropshipPurchaseOrderParameterDTOMap.put(field.getName(), field.get(dropshipPurchaseOrderParameterDTO));
                }

                ResponseDTO responseBody = callDropshipPOinItem(dropshipPurchaseOrderParameterDTOMap);
                if (responseBody.getStatus()) {
                    return salesOrderItemDetailsRepositoryExtended.getSavedPurchaseOrder(fetchMasterClinicalIns.getSales_order_id(),
                            salesOrderItemDetailsEntryParameterDTO.getVendorId(),
                            obj.getSalesOrderDetailsItemId(),
                            obj.getSalesOrderDetailsQty())
                        .flatMap(dropShipPO -> {

                            //======================= Adding Drop-ship Item ======================================
                            String message = "";
                            Boolean status = false;
                            List<Object> responseList = new ArrayList<>();

                            if (fetchMasterClinicalIns != null) {
                                //----------- Hardcoded Values ----------------
                                obj.setSalesOrderDetailsIcdPointer("1");
                                obj.setSalesOrderDetailsIcdPointer("H");
                                //----------- Hardcoded Values ----------------

                                obj.setSalesOrderDetailsLineQty(obj.getSalesOrderDetailsQty());
                                obj.setPatientId(fetchMasterClinicalIns.getPatient_id());

                                // ============== Drop-ship Section ========================
                                obj.setIsDropshipAllowed("Y");
                                obj.setPoNumber(dropShipPO.getPo_number());
                                obj.setPurchaseOrderUuid(dropShipPO.getPurchase_order_uuid());
                                // ============== Drop-ship Section ========================

                                //------------Saving Data-----------------------------------------------------
                                if (obj.getSalesOrderItemDetailsId() == null || obj.getSalesOrderItemDetailsId() == 0) {
                                    obj.setCreatedDate(LocalDate.now());
                                    obj.setCreatedById(1L);   //----- Data taken from login user service
                                    obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                                    obj.setSalesOrderItemDetailsId(null);
                                    obj.setStatus("active");
                                    obj.setSalesOrderItemDetailsUuid(UUID.randomUUID());
//                        Mono<SalesOrderItemDetails> orderItemDetails = salesOrderItemDetailsRepositoryExtended
//                            .save(salesOrderItemDetailsMapper.toEntity(obj));
                                    System.out.println("====> Before Save SalesOrderItems 1");
                                    return salesOrderItemDetailsRepositoryExtended
                                        .save(salesOrderItemDetailsMapper.toEntity(obj)).flatMap(
                                            i -> {
                                                System.out.println("=====> After Save SalesOrderItems :: " + i);
                                                System.out.println("Inside responseDTOMono If");
                                                //ResponseDTO responseDTO = responseDTOMono.toFuture().get();

                                                System.out.println("Inside responseDTO getData If");
                                                SalesOrderItemDetailsDTO savedSOItemDetails = salesOrderItemDetailsMapper.toDto(i);
                                                SOItemInventoryTransactionDTO soItemInventoryTransactionDTO = new SOItemInventoryTransactionDTO();
                                                soItemInventoryTransactionDTO.setSalesOrderNo(fetchMasterClinicalIns.getSales_order_no());
                                                soItemInventoryTransactionDTO.setSaleType(savedSOItemDetails.getSalesOrderDetailsSaleType());
                                                soItemInventoryTransactionDTO.setItemId(savedSOItemDetails.getSalesOrderDetailsItemId());
                                                soItemInventoryTransactionDTO.setItemNo(savedSOItemDetails.getSalesOrderItemNumber());
                                                soItemInventoryTransactionDTO.setItemName(savedSOItemDetails.getSalesOrderDetailsItemName());
                                                soItemInventoryTransactionDTO.setItemUom(savedSOItemDetails.getSalesOrderDetailsStockingUom());
                                                soItemInventoryTransactionDTO.setItemQty(savedSOItemDetails.getSalesOrderDetailsQty());
                                                soItemInventoryTransactionDTO.setWheatherSerialized(savedSOItemDetails.getWhetherSerialised());
                                                soItemInventoryTransactionDTO.setSerialNo(savedSOItemDetails.getItemSerialNo() != null ? savedSOItemDetails.getItemSerialNo().toString() : "");
                                                soItemInventoryTransactionDTO.setWheatherAssetTagged(salesOrderItemDetailsEntryParameterDTO.getIsAssetTagged());
                                                soItemInventoryTransactionDTO.setOriginalDos(savedSOItemDetails.getSalesOrderDetailsOriginalDos());
                                                soItemInventoryTransactionDTO.setBranchId(fetchMasterClinicalIns.getBranch_id());
                                                soItemInventoryTransactionDTO.setLocationId(savedSOItemDetails.getItemLocationId());
                                                soItemInventoryTransactionDTO.setLocationName(salesOrderItemDetailsEntryParameterDTO.getLocationName());
                                                soItemInventoryTransactionDTO.setIsDropship("Y");
                                                soItemInventoryTransactionDTO.setPoNo(dropShipPO.getPo_number());

                                                System.out.println("soItemInventoryTransactionDTO => " + soItemInventoryTransactionDTO);

                                                return soItemInventoryTransactionUpdate(soItemInventoryTransactionDTO)
                                                    .flatMap(x -> {
                                                        System.out.println("DB Function Output:: " + x);
                                                        if (x.equalsIgnoreCase("success")) {

                                                            //============= CMN Integration ==============================
                                                            SalesOrderItemCmnSubroutineDTO salesOrderItemCmnSubroutineDTO = new SalesOrderItemCmnSubroutineDTO();
                                                            salesOrderItemCmnSubroutineDTO.setItemNo(obj.getSalesOrderItemNumber());
                                                            salesOrderItemCmnSubroutineDTO.setSalesOrderNo(fetchMasterClinicalIns.getSales_order_no());
                                                            salesOrderItemCmnSubroutineDTO.setSoItemDetailsId(savedSOItemDetails.getSalesOrderItemDetailsId());
                                                            salesOrderItemCmnSubroutineDTO.setDos(obj.getSalesOrderDetailsOriginalDos().toString());
                                                            salesOrderItemCmnSubroutineDTO.setOrderingProviderNpi(fetchMasterClinicalIns.getOrdering_provider_npi());
                                                            salesOrderItemCmnSubroutineDTO.setPatientId(fetchMasterClinicalIns.getPatient_id());
                                                            salesOrderItemCmnSubroutineDTO.setSalesOrderId(fetchMasterClinicalIns.getSales_order_id());
                                                            System.out.println("Before CMN Calling.");
                                                            return getCMNSubroutineDataForSalesOrderItemDetails(salesOrderItemCmnSubroutineDTO)
                                                                .map(j -> {

                                                                    //============= PAR Integration ==============================
                                                                    String useExistingPAR = (salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR() == null ||
                                                                        salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR().trim().equals(""))
                                                                        ? "No" : salesOrderItemDetailsEntryParameterDTO.getUseExistingPAR();

                                                                    SearchPARInputParameters searchPARInputParameters = new SearchPARInputParameters();
                                                                    searchPARInputParameters.setInsuranceid(fetchMasterClinicalIns.getPrimary_insurer_id());
                                                                    searchPARInputParameters.setInsurancename(fetchMasterClinicalIns.getPrimary_insurer_name());
                                                                    searchPARInputParameters.setPayercontactname(fetchMasterClinicalIns.getPrimary_insurer_contact_name());
                                                                    searchPARInputParameters.setPayercontactnumber(fetchMasterClinicalIns.getPrimary_insurer_contact_1());
                                                                    searchPARInputParameters.setPayeridno(fetchMasterClinicalIns.getPrimary_insurance_payer_id());
                                                                    searchPARInputParameters.setPolicynumber(fetchMasterClinicalIns.getPrimary_insurer_policy_no());
                                                                    searchPARInputParameters.setPolicystartdate(fetchMasterClinicalIns.getPrimary_insurer_effective_date());
                                                                    searchPARInputParameters.setPolicyenddate(null);
                                                                    searchPARInputParameters.setEffectivestartdate(fetchMasterClinicalIns.getPrimary_insurer_effective_date());
                                                                    searchPARInputParameters.setPatientidno(fetchMasterClinicalIns.getPatient_id_no());
                                                                    searchPARInputParameters.setPatientid(fetchMasterClinicalIns.getPatient_id());
                                                                    searchPARInputParameters.setPatientdob(fetchMasterClinicalIns.getPatient_dob());
                                                                    searchPARInputParameters.setPatientfirstname(fetchMasterClinicalIns.getPatient_first_name() + " " + fetchMasterClinicalIns.getPatient_middle_name());
                                                                    searchPARInputParameters.setPatientlastname(fetchMasterClinicalIns.getPatient_last_name());
                                                                    searchPARInputParameters.setPatientgender(fetchMasterClinicalIns.getPatient_gender());
                                                                    searchPARInputParameters.setItemid(i.getSalesOrderDetailsItemId());
                                                                    searchPARInputParameters.setItemno(i.getSalesOrderItemNumber());
                                                                    searchPARInputParameters.setItemname(i.getSalesOrderDetailsItemName());
                                                                    searchPARInputParameters.setItemquantity(i.getSalesOrderDetailsQty());
                                                                    searchPARInputParameters.setItemuom(i.getSalesOrderDetailsStockingUom());
                                                                    searchPARInputParameters.setDescription(i.getSalesOrderDetailsItemDescription());
                                                                    searchPARInputParameters.setCreatedbyid(1L);
                                                                    searchPARInputParameters.setCreatedbyname("Abhijit Chowdhury");
                                                                    searchPARInputParameters.setDos(i.getSalesOrderDetailsOriginalDos());
                                                                    searchPARInputParameters.setHcpcsno(i.getSalesOrderDetailsProcCode());
                                                                    searchPARInputParameters.setPayerlevel("primary");
                                                                    searchPARInputParameters.setUseexistingpar(useExistingPAR);
                                                                    searchPARInputParameters.setSoid(i.getSalesOrderId());
                                                                    searchPARInputParameters.setSono(fetchMasterClinicalIns.getSales_order_no());
                                                                    searchPARInputParameters.setPricetableid(fetchMasterClinicalIns.getPrice_table_id());

                                                                    System.out.println("Before getPARDataForSalesOrderItemDetails ");
                                                                    Mono<ParMaster> parResponse = getPARDataForSalesOrderItemDetails(searchPARInputParameters);
                                                                    return parResponse.map(k -> {
                                                                        System.out.println("After Success: getPARDataForSalesOrderItemDetails: " + k);
                                                                        if (k.getParIdNo() != null) {
                                                                            j.setParNo(k.getParIdNo());
                                                                            j.setSalesOrderDetailsCmnparParId(String.valueOf(k.getParId()));
                                                                            return new ResponseDTO(true, "Successfully Saved with CMN and PAR Linked.", List.of(j));
                                                                        } else {
                                                                            return new ResponseDTO(true, "Successfully Saved with CMN Linked. But PAR is not associated with this item.", List.of(j));
                                                                        }
                                                                    }).switchIfEmpty(Mono.just(new ResponseDTO(true, "Successfully Saved with CMN Linked. But PAR is not associated with this item.", List.of(j))));
                                                                    //============= PAR Integration ==============================
                                                                })
                                                                .flatMap(obj1 -> obj1)
                                                                .switchIfEmpty(Mono.just(new ResponseDTO(true, "Successfully Saved. But CMN is not associated with this item.", List.of(i))));
                                                            //============= CMN Integration ==============================
                                                        } else {
                                                            return Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>()));
                                                        }
                                                    })
                                                    .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>())));
                                            })
                                        .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Save. Data Error.", new ArrayList<>())));

                                } else {
                                    System.out.println("====== Inside the Update Section (Sales Order Item Details) =====");
                                    return salesOrderItemDetailsRepositoryExtended.findById(obj.getSalesOrderItemDetailsId())
                                        .map(soItemObj -> {
                                            BeanUtils.copyProperties(salesOrderItemDetailsEntryParameterDTO, soItemObj);
                                            soItemObj.setUpdatedDate(LocalDate.now());
                                            soItemObj.setUpdatedById(1L);      //----- Data taken from login user service
                                            soItemObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                                            return soItemObj;
//                                        return salesOrderItemDetailsRepositoryExtended
//                                            .save(salesOrderItemDetailsMapper.toEntity(obj))
//                                            .map(salesOrderItemDetailsMapper::toDto).map(
//                                                i -> new ResponseDTO(true, "Successfully Saved", List.of(i)))
//                                            .switchIfEmpty(Mono.just(new ResponseDTO(false, "Failed to Saved: Data Error.", new ArrayList<>())));
                                        })
                                        .flatMap(salesOrderItemDetailsRepositoryExtended::save)
                                        .map(data -> new ResponseDTO(true, "Successfully Saved.", List.of(data)))
                                        .switchIfEmpty(Mono.just(new ResponseDTO(false, "Sales Order Item not Found.", new ArrayList<>())));
                                }
                                //-----------------------------Saving Data----------------------------------------------
                            } else {
                                message = "Sales Order Not Found.";
                                return Mono.just(new ResponseDTO(status, message, responseList));
                            }

                            //======================= Adding Drop-ship Item =====================================

                        })
//                    .flatMap(obj1 -> obj1)
                        .switchIfEmpty(Mono.just(new ResponseDTO(false, "Data Error: Failed to Create Drop-ship PO", new ArrayList())));
                } else {
                    return Mono.just(new ResponseDTO(false, "Data Error: Failed to Create Drop-ship PO", new ArrayList()));
                }
            } else {
                return Mono.just(new ResponseDTO(false, "Drop-ship PO is not allowed as On_Hand_Qty > 0.", new ArrayList()));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Mono<ServiceOutcome> removeSalesOrderItemDetails(RemoveDropshipParameterDTO removeDropshipParameterDTO) throws ExecutionException, InterruptedException {
        List<SalesOrderItemDetails> salesOrderItemDetailsList = salesOrderItemDetailsRepositoryExtended.getSOItemDetailsBySOItemDetailsUUID(removeDropshipParameterDTO.getSoItemUUID()).collectList().toFuture().get();
        Long soId = salesOrderMasterServiceExtented.getIDByUUID(removeDropshipParameterDTO.getSoUUID()).toFuture().get();
        if (salesOrderItemDetailsList.size() > 0) {
            SalesOrderItemDetails salesOrderItemDetails = salesOrderItemDetailsList.get(0);
            if (salesOrderItemDetails.getIsDropshipAllowed().equalsIgnoreCase("Y") &&
                salesOrderItemDetails.getPoNumber() != null && salesOrderItemDetails.getPoNumber() != "") {
                //TODO - Call the "/cancelDropshipPurchaseOrder" API of Item Micro-service [See the ParameterDto of It]
                cancelDropshipPurchaseOrder(salesOrderItemDetails.getPoNumber());
            }
            if (salesOrderItemDetails.getParNo() != null && salesOrderItemDetails.getParNo() != "" &&
                salesOrderItemDetails.getSalesOrderDetailsCmnparParId() != null && !salesOrderItemDetails.getSalesOrderDetailsCmnparParId().equals("0")) {
                //TODO - call priorAuthorizationService.delinkItem(parId, soId, hcpcsCode, itemId);
                ServiceOutcome<ParMaster> serviceOutcome = priorAuthorizationService.delinkItem(Long.parseLong(salesOrderItemDetails.getSalesOrderDetailsCmnparParId()),
                    soId, salesOrderItemDetails.getSalesOrderDetailsProcCode(), salesOrderItemDetails.getSalesOrderDetailsItemId());
                System.out.println("serviceOutcome " + serviceOutcome.getData());
            }
            //TODO - In-activate the <sales_order_item_details> row with "sales_order_item_details_id"
            salesOrderItemDetails.setStatus("InActive");
            SalesOrderItemDetails salesOrderItemDetails1 = salesOrderItemDetailsRepositoryExtended.save(salesOrderItemDetails).toFuture().get();
            return Mono.just(new ServiceOutcome(salesOrderItemDetails1, true, "Successfully Removed"));
        }
        return Mono.just(new ServiceOutcome(null, false, "Data Not Found"));
    }

    //    //@Transactional(readOnly = true)
    @Override
    public Mono<ServiceOutcome<ItemDefaultVendorResponseDTO>> getItemDefaultVendorByItemId(Long itemId) throws ExecutionException, InterruptedException {
        return salesOrderItemDetailsRepositoryExtended.findItemDefaultVendorByItemId(itemId).map(responseObj -> {
            System.out.println("In fetching default vendor (in method)....");
            if (responseObj.getId() != null && responseObj.getTitle() != null) {
                System.out.println("IF:: In fetching default vendor data=>" + responseObj);
                return new ServiceOutcome<ItemDefaultVendorResponseDTO>(responseObj, true, "Data Fetched Successfully.");
            } else {
                System.out.println("IF:: In fetching default vendor data=>" + responseObj);
                return new ServiceOutcome<ItemDefaultVendorResponseDTO>(null, false, "No Data Available");
            }
        });

    }

    @Override
    public Mono<String> soItemInventoryTransactionUpdate(SOItemInventoryTransactionDTO soItemInventoryTransactionDTO) {
        String salesOrderNo = soItemInventoryTransactionDTO.getSalesOrderNo();
        String saleType = soItemInventoryTransactionDTO.getSaleType();
        Long itemId = soItemInventoryTransactionDTO.getItemId();
        String itemNo = soItemInventoryTransactionDTO.getItemNo();
        String itemName = soItemInventoryTransactionDTO.getItemName();
        String itemUom = soItemInventoryTransactionDTO.getItemUom();
        Long itemQty = soItemInventoryTransactionDTO.getItemQty();
        String wheatherSerialized = soItemInventoryTransactionDTO.getWheatherSerialized();
        String serialNo = soItemInventoryTransactionDTO.getSerialNo();
        String wheatherAssetTagged = soItemInventoryTransactionDTO.getWheatherAssetTagged();
        LocalDate originalDos = soItemInventoryTransactionDTO.getOriginalDos();
        Long branchId = soItemInventoryTransactionDTO.getBranchId();
        Long locationId = soItemInventoryTransactionDTO.getLocationId();
        String locationName = soItemInventoryTransactionDTO.getLocationName();
        String poNo = soItemInventoryTransactionDTO.getPoNo();
        String isDropship = soItemInventoryTransactionDTO.getIsDropship();
        Long createdById = 1l;
        String createdByName = "Abhay Shaw";
        LocalDate createdDate = LocalDate.now();
        Long updatedById = null;
        LocalDate updatedDate = null;
        String updatedByName = null;
        if (poNo == null) {
            return salesOrderItemDetailsRepositoryExtended.soItemInventoryTransactionUpdate(salesOrderNo,
                saleType,
                itemId,
                itemNo,
                itemName,
                itemUom,
                itemQty,
                wheatherSerialized,
                serialNo,
                wheatherAssetTagged,
                originalDos,
                branchId,
                locationId,
                locationName,
                createdById,
                createdByName,
                createdDate,
                updatedById,
                updatedDate,
                updatedByName);
        } else {
            return salesOrderItemDetailsRepositoryExtended.soDropshipItemInventoryTransactionUpdate(salesOrderNo,
                saleType,
                itemId,
                itemNo,
                itemName,
                itemUom,
                itemQty,
                wheatherSerialized,
                serialNo,
                wheatherAssetTagged,
                originalDos,
                branchId,
                locationId,
                locationName,
                createdById,
                createdByName,
                createdDate,
                updatedById,
                updatedDate,
                updatedByName,
                poNo,
                isDropship);
        }
    }

    private ResponseDTO callDropshipPOinItem(Map dropshipPurchaseOrderParameterDTO) {
        ResponseDTO responseBody = new ResponseDTO();
        try {
            //==================== Get the access token ====================
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("item_url");
                //String param="";
                String completeUrl = url;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json;charset=utf-8");
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(dropshipPurchaseOrderParameterDTO, headersData);
                ResponseEntity<ResponseDTO> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.POST,
                    requestEntity,
                    ResponseDTO.class
                );
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (ResponseDTO) responseData.getBody();
                } else {
                    responseBody.setStatus(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                }
            } else {
                responseBody.setStatus(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }
        JSONParser parser = new JSONParser();
        Boolean isDropshipSaved = responseBody.getStatus();
        if (isDropshipSaved) {
            System.out.println("Dropship " + responseBody.getData());
            System.out.println("isDropshipSaved " + isDropshipSaved);
            return responseBody;
        } else {
            return responseBody;
        }
    }

    private ResponseDTO cancelDropshipPurchaseOrder(String poNumber) {
        ResponseDTO responseBody = new ResponseDTO();
        try {
            //==================== Get the access token ====================
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("item_cancelDropship_url");
                String param = "?poNumber={poNumber}";
                String completeUrl = url + param;
                System.out.println("completeUrl " + completeUrl);
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json;charset=utf-8");
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.PUT,
                    entityData,
                    ResponseDTO.class,
                    poNumber);

                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (ResponseDTO) responseData.getBody();
                } else {
                    responseBody.setStatus(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                }
            } else {
                responseBody.setStatus(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }
        JSONParser parser = new JSONParser();
        Boolean isDropshipSaved = responseBody.getStatus();
        if (isDropshipSaved) {
            System.out.println("Dropship " + responseBody.getData());
            System.out.println("isDropshipSaved " + isDropshipSaved);
            return responseBody;
        } else {
            return responseBody;
        }
    }

    @Override
    public Mono<ServiceOutcome> dLinkForCmnItems(Long cmnId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        //Todo updatedById, updatedByName hard code value will change later.
        Long updatedById = 79L;
        String updatedByName = "Pritam Panja";

        return salesOrderItemDetailsRepositoryExtended.getDLinkForCmnItems(cmnId, salesOrderId, salesOrderDetailsItemId, updatedById, updatedByName)
            .map(result -> {
                if (result) {
                    return new ServiceOutcome(null, true, "Successfully D-linked.");
                } else return new ServiceOutcome(null, false, "Failed to D-linked.");

            });
    }

    @Override
    public Mono<ServiceOutcome> linkForCmnItems(Long cmnId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        //Todo updatedById, updatedByName hard code value will change later.
        Long updatedById = 79L;
        String updatedByName = "Pritam Panja";

        return salesOrderItemDetailsRepositoryExtended.getLinkForCmnItems(cmnId, salesOrderId, salesOrderDetailsItemId, updatedById, updatedByName)
            .map(result -> {
                if (result) {
                    return new ServiceOutcome(null, true, "Successfully Linked.");
                } else return new ServiceOutcome(null, false, "Failed to Linked.");

            });
    }
}
