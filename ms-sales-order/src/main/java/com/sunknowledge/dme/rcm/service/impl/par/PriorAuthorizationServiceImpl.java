package com.sunknowledge.dme.rcm.service.impl.par;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.documentutil.HeaderFooterPageEvent;
import com.sunknowledge.dme.rcm.documentutil.OrderConfirmationTableBuilder;
import com.sunknowledge.dme.rcm.documentutil.PriorAuthReportBuilder;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.domain.ParDetails;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.dto.RenwalOrExpiringPAR.RenwalOrExpiringPARDTO;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ItemDetail;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.cmn.CmnDocumentMasterRepo;
import com.sunknowledge.dme.rcm.repository.cmn.CmnRepo;
import com.sunknowledge.dme.rcm.repository.par.ParDetailsRepo;
import com.sunknowledge.dme.rcm.repository.par.ParMasterRepo;
import com.sunknowledge.dme.rcm.repository.par.ParRequestDetailsRepo;
import com.sunknowledge.dme.rcm.repository.par.ParSoMapRepo;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderInsuranceDetailsRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderItemDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.par.SalesOrderInsurancePriceDetails;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PriorAuthorizationServiceImpl implements PriorAuthorizationService {
    @Autowired
    SalesOrderItemDetailsRepositoryExtended salesOrderItemDetailsRepositoryExtended;
    @Autowired
    private SalesOrderMasterRepo salesOrderMasterRepository;
    @Autowired
    private ParMasterRepo parMasterRepository;
    @Autowired
    private ParDetailsRepo parDetailsRepository;
    @Autowired
    private ParSoMapRepo parSOMapRepository;
    @Autowired
    private SalesOrderItemDetailsRepo salesOrderItemDetailsRepository;
    @Autowired
    private SalesOrderInsuranceDetailsRepo salesOrderInsuranceDetailsRepository;
    @Autowired
    private CmnRepo cmnRepository;
    @Autowired
    private CmnDocumentMasterRepo cmnDocumentMasterRepository;
    @Autowired
    private ParRequestDetailsRepo parRequestDetailsRepository;

    @Override
    public Mono<ServiceOutcome<ParMasterDTO>> generatePriorAuthorizationOnSalesOrderItem(Long salesOrderId,
                                                                                         String hcpcsCode) throws Exception {
        log.info("==============SERVICE-generatePriorAuthorizationOnSalesOrderItem=======================");
        ParMaster parMaster = null;
        ParMasterDTO parMasterDTO = new ParMasterDTO();
        ServiceOutcome<ParMasterDTO> outcome = new ServiceOutcome<>();
        try {
            SalesOrderMaster salesOrderMaster = salesOrderMasterRepository
                .getSalesOrderDetailsOnSalesOrder(salesOrderId).toFuture().get();
            if (salesOrderMaster != null) {
                SalesOrderInsurancePriceDetails salesOrderInsurancePriceDetails = salesOrderInsuranceDetailsRepository
                    .getSalesOrderInsurancePriceDetailsOnSalesOrderhcpcsCode(salesOrderMaster.getSalesOrderId(),
                        hcpcsCode)
                    .toFuture().get();
                if (salesOrderInsurancePriceDetails != null
                    && salesOrderInsurancePriceDetails.getPriorAuthReqStatus().equals("Y")) {
                    ParMaster parSalesOrderStatus = parMasterRepository
                        .getParMasterOnSalesOrderNStatus(salesOrderMaster.getSalesOrderId(), "Initiated").toFuture()
                        .get();
//                    List<ParMaster> parMasterList = parMasterRepository.getParMasterOnSalesOrderPatientNstatus(salesOrderMaster.getSalesOrderId(), "Active").collectList().toFuture().get();
                    List<Long> parIds = parMasterRepository
                        .getParIdsOnPatientNparStatus(salesOrderMaster.getPatientId()).collectList().toFuture()
                        .get();
                    log.info("==============Par Ids==========" + parIds);
                    ParMaster parMst = null;
                    if (parIds.size() > 0)
                        parMst = parMasterRepository.getParMasterOnParIdsNhcpcsCode(hcpcsCode, parIds).toFuture().get();
                    if (parSalesOrderStatus == null && parMst == null) {
                        log.info("====================Criterial-1 Create New PAR=======================>");
                        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails = salesOrderInsuranceDetailsRepository
                            .getSalesOrderInsurancePriceDetailsOnSalesOrder(salesOrderMaster.getSalesOrderId())
                            .toFuture().get();
                        if (salesOrderInsuranceDetails != null) {
                            parMaster = generatePar(hcpcsCode, salesOrderMaster, salesOrderInsuranceDetails);
                            BeanUtils.copyProperties(parMaster, parMasterDTO);
                            outcome.setOutcome(true);
                            outcome.setData(parMasterDTO);
                            outcome.setMessage("PAR is created Successfully!");
                        }
                    } else if (parSalesOrderStatus != null && parMst == null) {
                        log.info(
                            "====================Criterial-2 Adding Item to Existing PAR in Initial State=======================>");
                        ParDetails parDetails = parDetailsRepository
                            .getParDetailsOnParNitem(parSalesOrderStatus.getParId(), hcpcsCode).toFuture().get();
                        if (parDetails == null) {
                            SalesOrderItemDetails salesOrderItemDetails = salesOrderItemDetailsRepository
                                .getsalesOrderItemDetailsOnSalesOrderNitem(salesOrderMaster.getSalesOrderId(),
                                    hcpcsCode)
                                .toFuture().get();
                            if (salesOrderItemDetails != null)
                                createParDetails(parSalesOrderStatus, salesOrderItemDetails);
                        }
                        BeanUtils.copyProperties(parSalesOrderStatus, parMasterDTO);
                        outcome.setOutcome(true);
                        outcome.setData(parMasterDTO);
                        outcome.setMessage("Item is added to PAR Successfully!");
                    } else if (parSalesOrderStatus != null && parMst != null) {
                        log.info("====================Criterial-3 Using Existing PAR ID=======================>");
                        createParSOMap(parMst, salesOrderMaster);
                        BeanUtils.copyProperties(parMst, parMasterDTO);
                        outcome.setOutcome(true);
                        outcome.setData(parMasterDTO);
                        outcome.setMessage("Existing PAR is linked to SO Map Successfully!");
                    }
                } else {
                    outcome.setOutcome(false);
                    outcome.setData(null);
                    outcome.setMessage("PAR is not required for this item!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.justOrEmpty(outcome);
    }

    ParMaster generatePar(String hcpcsCode, SalesOrderMaster salesOrderMaster,
                          SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails) {
        ParMaster saveParMaster = null;
        try {
            ParMaster parMaster = new ParMaster();
            parMaster.setParNo("");
            parMaster.setPatientId(salesOrderMaster.getPatientId());
            parMaster.setPatientIdNumber(salesOrderMaster.getPatientIdNo());
            parMaster.setPatientFirstName(salesOrderMaster.getPatientFirstName());
            parMaster.setPatientLastName(salesOrderMaster.getPatientLastName());
            parMaster.setPatientDob(salesOrderMaster.getPatientDob());
            parMaster.setPatientGender(salesOrderMaster.getPatientGender());
            parMaster.setInsuranceId(salesOrderInsuranceDetails.getPrimaryInsurerId());
            parMaster.setInsuranceName(salesOrderInsuranceDetails.getPrimaryInsurerName());
            parMaster.setPayerIdNo(salesOrderInsuranceDetails.getPrimaryInsurancePayerId());
            parMaster.setPayerLevel("Primary");
            parMaster.setPolicyNumber(salesOrderInsuranceDetails.getPrimaryInsurerPolicyNo());
            parMaster.setParIdNo(parDetailsRepository.getParNo().toFuture().get());
            //parMaster.setPolicyStartDate(salesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate());
            parMaster.setPolicyEndDate(salesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate());
            parMaster.setPayerContactNumber(salesOrderMaster.getInsuredContactNo());
            parMaster.setDateOfContact(LocalDate.now());
            parMaster.setPayerContactName(salesOrderInsuranceDetails.getPrimaryInsurerContactName());
            parMaster.setDescription("PAR Initiated");
            //parMaster.setInitialDate(LocalDate.now());
            parMaster.setParStatus("initiated");
            parMaster.setLogInStatus(null);
            parMaster.status("active");
            parMaster.setCreatedById(4L);
            parMaster.setCreatedByName("Debabrata");
            parMaster.setCreatedDate(LocalDate.now());
            saveParMaster = parMasterRepository.save(parMaster).toFuture().get();
            if (saveParMaster != null) {
                SalesOrderItemDetails salesOrderItemDetails = salesOrderItemDetailsRepository
                    .getsalesOrderItemDetailsOnSalesOrderNitem(salesOrderMaster.getSalesOrderId(), hcpcsCode)
                    .toFuture().get();
                ParDetails saveParDetails = createParDetails(saveParMaster, salesOrderItemDetails);
                if (saveParDetails != null) {
                    ParSoMap parSOMap = new ParSoMap();
                    parSOMap.setParId(saveParMaster.getParId());
                    parSOMap.setParNo(saveParMaster.getParNo());
                    parSOMap.setSoId(salesOrderMaster.getSalesOrderId());
                    parSOMap.setSoNo(salesOrderMaster.getSalesOrderNo());
                    parSOMap.setStatus("active");
                    parSOMap.setCreatedById(4L);
                    parSOMap.setCreatedDate(LocalDate.now());
                    parSOMap.setCreatedByName("Debabrata");
                    parSOMapRepository.save(parSOMap).toFuture().get();
                }
                ParRequestDetails parRequestDetails = new ParRequestDetails();
                parRequestDetails.setParRequestType("initial");
                parRequestDetails.setParId(saveParMaster.getParId());
                parRequestDetails.setParInitiationDate(LocalDate.now());
                parRequestDetails.setStatus("active");
                parRequestDetails.setCreatedById(4L);
                parRequestDetails.setCreatedDate(LocalDate.now());
                parRequestDetails.setCreatedByName("Debabrata");
                parRequestDetailsRepository.save(parRequestDetails).toFuture().get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveParMaster;
    }

    ParDetails createParDetails(ParMaster parMaster, SalesOrderItemDetails salesOrderItemDetails) {
        ParDetails parDetail = new ParDetails();
        try {
            parDetail.parId(parMaster.getParId());
            parDetail.setItemId(salesOrderItemDetails.getSalesOrderDetailsItemId());
            parDetail.setItemNo(salesOrderItemDetails.getSalesOrderItemNumber());
//			parDetail.setItemUom(salesOrderItemDetails.getItemUom());
            parDetail.setItemQuantity(salesOrderItemDetails.getSalesOrderDetailsQty().doubleValue());
            parDetail.setHcpcsCode(salesOrderItemDetails.getSalesOrderDetailsProcCode());
            parDetail.setDescription("Authoriasation Required");
            parDetail.setItemName(salesOrderItemDetails.getSalesOrderDetailsItemName());
            parDetail.setStatus("active");
            parDetail.setCreatedById(4L);
            //parDetail.setCreatedDate(LocalDate.now());
            parDetail.setCreatedByName("Debabrata");
            parDetail = parDetailsRepository.save(parDetail).toFuture().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parDetail;
    }

    ParSoMap createParSOMap(ParMaster parMaster, SalesOrderMaster salesOrderMaster) {
        ParSoMap parSOMap = new ParSoMap();
        try {
            parSOMap.setParId(parMaster.getParId());
            parSOMap.setParNo(parMaster.getParNo());
            parSOMap.setSoId(salesOrderMaster.getSalesOrderId());
            parSOMap.setSoNo(salesOrderMaster.getSalesOrderNo());
            parSOMap.setStatus("active");
            parSOMap.setCreatedById(4L);
            parSOMap.setCreatedDate(LocalDate.now());
            parSOMap.setCreatedByName("Debabrata");
            parSOMap = parSOMapRepository.save(parSOMap).toFuture().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parSOMap;
    }

    @Override
    public Mono<ServiceOutcome<ParMasterDTO>> createManualPriorAuthorizationOnSalesOrderNItem(ParInputParameters parInputParameters) {
        log.info("==============SERVICE-createManualPriorAuthorizationOnSalesorderNitem=======================");
        ServiceOutcome<ParMasterDTO> outcome = new ServiceOutcome<>();
        ParMaster parMaster = null;
        ParMasterDTO parMasterDTO = new ParMasterDTO();
        try {
            SalesOrderItemDetails salesOrderItemDetails = salesOrderItemDetailsRepository.getsalesOrderItemDetailsOnSalesOrderNitem(parInputParameters.getSalesorderId(), parInputParameters.getItemId())
                .toFuture().get();
            if (salesOrderItemDetails != null) {
                ParDetails parDetails = parDetailsRepository.getParDetailsOnSalesorderNitem(parInputParameters.getSalesorderId(), parInputParameters.getItemId(), parInputParameters.getParId())
                    .toFuture().get();
                if (parDetails != null) {
                    parDetails.setStatus("inactive");
                    parDetailsRepository.save(parDetails).toFuture().get();
                    SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.findById(salesOrderItemDetails.getSalesOrderId()).toFuture().get();
                    SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails = salesOrderInsuranceDetailsRepository.getSalesOrderInsurancePriceDetailsOnSalesOrder(salesOrderItemDetails.getSalesOrderId())
                        .toFuture().get();
                    if (salesOrderInsuranceDetails != null) {
                        parMaster = generatePar(salesOrderItemDetails.getSalesOrderDetailsProcCode(), salesOrderMaster, salesOrderInsuranceDetails);
                        BeanUtils.copyProperties(parMaster, parMasterDTO);
                        outcome.setOutcome(true);
                        outcome.setData(parMasterDTO);
                        outcome.setMessage("Manual PAR is created Successfully!");
                    }
                } else {
                    outcome.setOutcome(false);
                    outcome.setData(null);
                    outcome.setMessage("PAR is not exists for this item!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.justOrEmpty(outcome);
    }

    @Override
    public Mono<ServiceOutcome<ParRequestDetails>> updatePriorAuthorization(UUID parUUID, String parNo, String dateOfContact, String description,
                                                                            String initialDate, String expirationDate, String authorizedBy, String docName, String docQrCode,
                                                                            String addlInformation) throws Exception {
        // TODO Auto-generated method stub
        ServiceOutcome<ParRequestDetails> objoutCome = new ServiceOutcome<ParRequestDetails>();
        String response = "";
        Mono<ServiceOutcome<ParRequestDetails>> abc = null;
        try {
            ParMaster parMaster = parMasterRepository.getParMasterOnParUUID(parUUID).toFuture().get();
            ParRequestDetails objParRequestDetails = getParRequestDetailsWithDocQrCode(parMaster.getParId(), docQrCode);
            List<ParSoMap> parSOMapList = parSOMapRepository.getParSOMapOnpar(parMaster.getParId()).collectList().toFuture().get();

            parMaster.setParNo(parNo);
            parMaster.setDateOfContact(CommonUtilities.stringtodateConverter(dateOfContact));
            parMaster.setDescription(description);
            //parMaster.setInitialDate(CommonUtilities.stringtodateConverter(initialDate));
            parMaster.setEffectiveStartDate(CommonUtilities.stringtodateConverter(initialDate));
            parMaster.setExpirationDate(CommonUtilities.stringtodateConverter(expirationDate));
            parMaster.setAuthorizedBy(authorizedBy);
            parMaster.setAddlInformation(addlInformation);
            parMaster.setParStatus("Initiated");

            objParRequestDetails.setParNo(parNo);
            objParRequestDetails.setParNoEffetiveStartDate(CommonUtilities.stringtodateConverter(initialDate));
            objParRequestDetails.setParNoEffetiveEndDate(CommonUtilities.stringtodateConverter(expirationDate));
            objParRequestDetails.setParAuthorisedBy(authorizedBy);
            objParRequestDetails.setParRequestResponseDocName(docName);
            objParRequestDetails.setUpdatedById(Long.valueOf("007"));
            objParRequestDetails.setUpdatedByName("AJ");
            objParRequestDetails.setUpdatedDate(LocalDate.now());

            for (ParSoMap parSoMap : parSOMapList) {
                if (!CommonUtilities.isStringNullOrBlank(parSoMap.getParNo())) {
                    parSoMap.setParNo(parNo);
                    parSOMapRepository.save(parSoMap).toFuture().get();
                }
            }

            parRequestDetailsRepository.save(objParRequestDetails).toFuture().get();
            parMasterRepository.save(parMaster).toFuture().get();

            objoutCome.setData(objParRequestDetails);
            objoutCome.setOutcome(true);

            response = "Par is Updated";

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        objoutCome.setMessage(response);

        return Mono.just(objoutCome);
    }

    @Override
    public ServiceOutcome<ParMaster> loggingPriorAuthorization(Long parId, String logInDate) {
        // TODO Auto-generated method stub
        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();
        String response = "";

        try {
            ParMaster parMaster = parMasterRepository.getParMasterOnParId(parId).toFuture().get();

            if (!CommonUtilities.isStringNullOrBlank(parMaster.getLogInStatus())) {

                if (!CommonUtilities.isStringNullOrBlank(parMaster.getParStatus()) || parMaster.getParStatus().equalsIgnoreCase("Initiated")) {
                    response = "Par Needs to be Updated before Login";
                } else {
                    parMaster.setLogInDate(CommonUtilities.stringtodateConverter(logInDate));
                    parMaster.setLogInStatus("Logged");
                    parMaster.setParStatus("Active");
                    //par logged by
                    parMaster.setUpdatedById(Long.valueOf("7"));
                    parMaster.setUpdatedByName("ANDROKTASIAI");
                    parMaster.setUpdatedDate(LocalDate.now());
                    parMasterRepository.save(parMaster).toFuture().get();
                    outCome.setData(parMaster);
                    response = "Par is Logged In";
                }

            } else {
                response = "Logged data is already Available";
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        outCome.setMessage(response);

        return outCome;
    }

    @Override
    public String getRenwalOrExpiringPARList() throws DocumentException, IOException, WriterException {

        String exp = "";

        try {

            List<RenwalOrExpiringPARDTO> parList = parMasterRepository.getRenwalOrExpiringPARList().collectList()
                .toFuture().get();
            // ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            // exp = ow.writeValueAsString(parList);
            exp = JSONArray.toJSONString(parList);

            return exp;

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exp;

    }

    public void validateinitOrRenewalReport(long parId, Long soId) throws Exception {

        String reqType = "";
        ParMaster parMaster = parMasterRepository.getParMasterOnParId(parId).toFuture().get();
        InsuranceMasterDTO objInsuranceMasterDTO = salesOrderInsuranceDetailsRepository
            .getsalesOrderInsuranceBySOIDForReport(soId).toFuture().get();
        if ((!CommonUtilities.isStringNullOrBlank(parMaster.getParNo())) && parMaster.getExpirationDate() == null
            && parMaster.getParStatus().equalsIgnoreCase("Initiated")) {
            reqType = "Initial";
        } else {
            reqType = "Renewal";
        }
        getPriorAuthorizationReport(parId, soId, objInsuranceMasterDTO, reqType);
    }

    @Override
    public void getPriorAuthorizationReport(long parId, Long soId, InsuranceMasterDTO objInsuranceMasterDTO, String reqType)
        throws Exception {
        // TODO Auto-generated method stub

        SWODataDTO swoDataDTO = getSWODataOnSalesOrder(soId);
        ParRequestDetails parRequestDetails = new ParRequestDetails();
        ParMaster pMdto = new ParMaster();
        int newreqdtlId = 0;

        if (reqType.equalsIgnoreCase("Initial")) {
            parRequestDetails = getParRequestDetailsForInit(parId, "Initial");
            newreqdtlId = Integer.parseInt(String.valueOf(parRequestDetails.getParRequestDetailsId()));
        } else {
            parRequestDetails = getLatestParRequestDetailIdForRenew();
            pMdto = parMasterRepository.getParMasterOnParId(parId).toFuture().get();
            newreqdtlId = Integer.parseInt(parRequestDetails.getParRequestDetailsId().toString()) + 1;
        }

        // generating QR Code
        new CommonPDFStubs().generateQRCode(parRequestDetails.getParRequestDetailsId().toString(), ApplicationConstants.TEMP_PAR_REQ_PATH);

        Document document = new Document(PageSize.A4);
        String fileName = "PAR_" + parId + "_" + String.valueOf(newreqdtlId) + ".pdf";

        PdfWriter writer = PdfWriter.getInstance(document,
            new FileOutputStream(ApplicationConstants.TEMP_PAR_REQ_PATH + fileName));
        Rectangle rect = new Rectangle(70, 20, 480, 810);
        writer.setBoxSize("par", rect);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(ApplicationConstants.TEMP_PAR_REQ_PATH);
        writer.setPageEvent(event);

        // set Data in PAR Request Details
        if (reqType.equalsIgnoreCase("Initial")) {
            updateInitialParRequestDetails(parRequestDetails, fileName, "TEMP_PAR_REQ_PATH",
                String.valueOf("PAR_" + parId + "_" + newreqdtlId));
        } else {
            setRenewalParRequestDetails(objInsuranceMasterDTO, fileName, "TEMP_PAR_REQ_PATH", parId, "PAR_" + parId + "_" + newreqdtlId, pMdto);
        }

        // Creating Document
        document.open();
        if (reqType.equalsIgnoreCase("Initial")) {
            document.add(PriorAuthReportBuilder.PriorAuthInitReporMainTitle(1));
        } else {
            document.add(PriorAuthReportBuilder.PriorAuthRenewalReporMainTitle(1));
        }
        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyTitle("PROVIDER", "PATIENT"));
        document.add(PriorAuthReportBuilder.createPriorAuthReportProviderPatientDetails(swoDataDTO));

        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyTitle("INSURANCE", "PHYSICIAN"));
        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyInsurancePhysicianDetails(swoDataDTO,
            objInsuranceMasterDTO));

        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyPARHeader());
        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyPARHeader2());
        if (!reqType.equalsIgnoreCase("Initial")) {
            document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyParDetailsData(pMdto));
        }

        document.add(new Paragraph("\n"));

        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyDiagnosisHeader());
        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyDiagnosisHeader2());
        document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosisHeadData(swoDataDTO));

        document.add(new Paragraph("\n"));

        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyItemDetailsHeader());
        document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyItemDetailsHeader2());

        List<EquipmentDetailsDTO> equipmentDetailsDTO = getEquipmentDetailsOnSalesOrder(soId);
        document.add(
            PriorAuthReportBuilder.createOrderConfirmationMainTitleEquipmentData(equipmentDetailsDTO, writer, 1));

        if (equipmentDetailsDTO.size() >= 5) {
            document.newPage();
            document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyTitle("PROVIDER", "PATIENT"));
            document.add(PriorAuthReportBuilder.createPriorAuthReportProviderPatientDetails(swoDataDTO));

            document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyTitle("INSURANCE", "PHYSICIAN"));
            document.add(PriorAuthReportBuilder.createPriorAuthReportMainBodyInsurancePhysicianDetails(swoDataDTO,
                objInsuranceMasterDTO));
        }
        if (reqType.equalsIgnoreCase("Initial")) {
            document.add(new Paragraph("\n\n"));
            document.add(PriorAuthReportBuilder.createPriorAuthInitReportMainBodyAuthNo());
            document.add(PriorAuthReportBuilder.createPriorAuthInitReportMainBodyCoverageFrom());
            document.add(PriorAuthReportBuilder.createPriorAuthInitReportMainBodyCoverageTo());
            document.add(PriorAuthReportBuilder.createPriorAuthInitReportMainBodyAuthorization());
            document.add(PriorAuthReportBuilder.createPriorAuthInitReportMainBodyComments());
        } else {
            document.add(PriorAuthReportBuilder.createPriorAuthRenewalReportMainBodyAuthNo(pMdto.getParNo()));
            document.add(PriorAuthReportBuilder
                .createPriorAuthRenewalReportMainBodyCoverageFrom(pMdto.getEffectiveStartDate().toString()));
            document.add(PriorAuthReportBuilder
                .createPriorAuthRenewalReportMainBodyCoverageTo(pMdto.getExpirationDate().toString()));
            document.add(PriorAuthReportBuilder
                .createPriorAuthRenewalReportMainBodyAuthorization(pMdto.getRenewalAuthorizedBy()));
            document.add(PriorAuthReportBuilder.createPriorAuthRenewalReportMainBodyComments(pMdto.getComments()));
        }

        PriorAuthReportBuilder.addQrCodeOnFooter(parId, writer);

        document.close();

    }

    @Override
    public SWODataDTO getSWODataOnSalesOrder(Long salesOrderId) throws Exception {
        Mono<SWODataDTO> swoDataDTO = cmnRepository.getSWODataOnSalesOrder(salesOrderId);
        return swoDataDTO.toFuture().get();
    }

    @Override
    public ParRequestDetails getParRequestDetailsForInit(Long parId, String reqType) throws Exception {
        Mono<ParRequestDetails> ParRequestDetailsDTO = parRequestDetailsRepository.getParRequestDetailsForInit(parId,
            reqType);
        return ParRequestDetailsDTO.toFuture().get();
    }

    @Override
    public ParRequestDetails getLatestParRequestDetailIdForRenew() throws Exception {
        Mono<ParRequestDetails> ParRequestDetailsDTO = parRequestDetailsRepository.getLatestParRequestDetailIdForRenew();
        return ParRequestDetailsDTO.toFuture().get();
    }

    @Override
    public ParRequestDetails getParRequestDetailsForRenewal(Long parId, String reqType) throws Exception {
        Mono<ParRequestDetails> ParRequestDetailsDTO = parRequestDetailsRepository.getParRequestDetailsForRenewal(parId,
            reqType);
        return ParRequestDetailsDTO.toFuture().get();
    }

    @Override
    public ParRequestDetails getParRequestDetailsWithDocQrCode(Long parId, String docQrCode) throws Exception {
        Mono<ParRequestDetails> ParRequestDetailsDTO = parRequestDetailsRepository
            .getParRequestDetailsWithDocQrCode(parId, docQrCode);
        return ParRequestDetailsDTO.toFuture().get();
    }

    public void updateInitialParRequestDetails(ParRequestDetails obj, String docName, String docLocation, String docQrCode)
        throws InterruptedException, ExecutionException {

        obj.setParInitiationDate(LocalDate.now());
        obj.setParRequestDocName(docName);
        obj.setParRequestDocLocation(docLocation);
        obj.setDocQrCode(docQrCode);

        parRequestDetailsRepository.save(obj).toFuture().get();
    }

    public void setRenewalParRequestDetails(InsuranceMasterDTO objInsuranceMasterDTO, String docName, String docLocation, Long parId, String docQrCode, ParMaster pMdto) throws InterruptedException, ExecutionException {

        ParRequestDetails objParRequestDetails = new ParRequestDetails();
        objParRequestDetails.setParRequestType("Renewal");
        objParRequestDetails.setParId(parId);
        objParRequestDetails.setParInitiationDate(LocalDate.now());
        objParRequestDetails.setParRequestDocName(docName);
        objParRequestDetails.setParRequestDocLocation(docLocation);
        objParRequestDetails.setParRequestFaxNumber(objInsuranceMasterDTO.getPrimary_insurer_fax());
        objParRequestDetails.setDocQrCode(docQrCode);
        objParRequestDetails.setStatus("Active");
        objParRequestDetails.setCreatedById(Long.valueOf("5501"));
        objParRequestDetails.setCreatedByName("Androktasiai");
        objParRequestDetails.setCreatedDate(LocalDate.now());
        pMdto.setRenewalReqSentStatus("Send");
        parMasterRepository.save(pMdto);
        parRequestDetailsRepository.save(objParRequestDetails).toFuture().get();
    }

    @Override
    public Cmn generateCmnOnSalesOrder(SWODataDTO swoDataDTO) throws Exception {
        Cmn cmnData = null;
        Cmn cmn = cmnRepository.getCmnDetailsOnSalesOrder(swoDataDTO.getSales_order_id()).toFuture().get();
        if (cmn != null) {
            //cmn.setUpdatedById(0L);
            cmn.setUpdatedByName("System");
            cmn.setUpdatedDate(LocalDate.now());
            cmnData = cmn;
        } else {
            Cmn saveCmn = new Cmn();
            saveCmn.setCmnNumber("111111");
            saveCmn.setCmnType("aa");
            saveCmn.setCmnFormName("00000");
            saveCmn.setPatientId(swoDataDTO.getPatient_id());
            saveCmn.setSalesOrderId(swoDataDTO.getSales_order_id());
            saveCmn.salesOrderNo(swoDataDTO.getSales_order_no());
            //saveCmn.setCreatedById(0L);
            saveCmn.setCreatedByName("System");
            saveCmn.setCreatedDate(LocalDate.now());
            saveCmn.setStatus("CREATED");
            saveCmn = cmnRepository.save(saveCmn).toFuture().get();
            cmnData = saveCmn;
        }
        return cmnData;
    }

    @Override
    public CmnDocumentMaster saveCmnDocument(Cmn cmn, String fileName) throws Exception {
        CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmn.getCmnId()).toFuture()
            .get();
        CmnDocumentMaster cmnDocumentMasterData = null;
        if (cmnDocumentMaster != null) {
            cmnDocumentMasterData = cmnDocumentMaster;
        } else {
            CmnDocumentMaster saveCmnDocumentMaster = new CmnDocumentMaster();
            saveCmnDocumentMaster.setCmnId(cmn.getCmnId());
            saveCmnDocumentMaster.setCmnNo(cmn.getCmnNumber());
            saveCmnDocumentMaster.setGenerationDate(LocalDate.now());
            saveCmnDocumentMaster.setInitialDocumentName(fileName);
            saveCmnDocumentMaster = cmnDocumentMasterRepository.save(saveCmnDocumentMaster).toFuture().get();
            cmnDocumentMasterData = saveCmnDocumentMaster;
        }
        return cmnDocumentMasterData;
    }

    @Override
    public List<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(Long salesOrderId) throws Exception {
        Flux<EquipmentDetailsDTO> equipmentDetailsDTO = cmnRepository.getEquipmentDetailsOnSalesOrder(salesOrderId);
        return equipmentDetailsDTO.collectList().toFuture().get();
    }

    @Override
    public String getParNo() throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        return parMasterRepository.getParNo().toFuture().get();
    }

    @Override
    public ServiceOutcome<ParSearchForCreate> validateParSearchProc(String patientidno, String hcpcsno, String itemno, Long soId, String dos)
        throws Exception {
        // TODO Auto-generated method stub
        ServiceOutcome<ParSearchForCreate> outCome = new ServiceOutcome<ParSearchForCreate>();
        ParSearchForCreate parSearchForCreate = parMasterRepository.validateParSearchProc(patientidno, hcpcsno, itemno, soId, dos).toFuture().get();
        if (parSearchForCreate == null) {
            outCome.setData(null);
            outCome.setMessage("Active PAR is not available");
            outCome.setOutcome(false);
        } else {
            outCome.setData(parSearchForCreate);
            outCome.setMessage("Active PAR is available");
            outCome.setOutcome(true);
        }
        return outCome;
    }

    @Override
    public void userChoosetoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception {
        // TODO Auto-generated method stub
        // Add data in PAR SO MAP
        ParMaster parMaster = parMasterRepository.getParMasterOnParUUID(parSearchForCreate.getParuuid()).toFuture().get();
        SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrder(Long.valueOf(parSearchForCreate.getSalesorderid())).toFuture().get();

        ParSoMap parSoMap = createParSOMap(parMaster, salesOrderMaster);
    }

    @Override
    public ServiceOutcome userChooseNottoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception {
        // TODO Auto-generated method stub
        SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrder(Long.valueOf(parSearchForCreate.getSalesorderid())).toFuture().get();
        ParMaster parMaster = parMasterRepository.getParMasterOnParUUID(parSearchForCreate.getParuuid()).toFuture().get();
        String parNo = parMasterRepository.initiatedparsearchbyso(salesOrderMaster.getSalesOrderNo()).toFuture().get();
        SalesOrderItemDetails salesOrderItemDetails = salesOrderItemDetailsRepository
            .getsalesOrderItemDetailsOnSalesOrderNitem(Long.valueOf(parSearchForCreate.getSalesorderid()), parSearchForCreate.getHcpcscode())
            .toFuture().get();
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails = salesOrderInsuranceDetailsRepository.getSalesOrderInsurancePriceDetailsOnSalesOrder(salesOrderItemDetails.getSalesOrderId())
            .toFuture().get();
        ServiceOutcome serviceOutcome = new ServiceOutcome();
        if (CommonUtilities.isStringNullOrBlank(parNo)) {
            //Add Data in Par Details Table
            ParDetails parDetails = createParDetails(parMaster, salesOrderItemDetails);
            serviceOutcome.setData(parMaster);
        } else {
            //Create new PAR
            ParMaster createdparMaster = generatePar(salesOrderItemDetails.getSalesOrderDetailsProcCode(), salesOrderMaster, salesOrderInsuranceDetails);
            serviceOutcome.setData(createdparMaster);
        }
        return serviceOutcome;
    }

    @Override
    public ServiceOutcome createOrAttachPar(String YesOrNo, ParSearchForCreate parSearchForCreate) throws Exception {
        // TODO Auto-generated method stub
        String message = "";
        ServiceOutcome serviceOutcome = new ServiceOutcome<>();
        if (YesOrNo.equalsIgnoreCase("Yes")) {
            userChoosetoUseExistingPAR(parSearchForCreate);
            message = "Existing PAR utilized";
        } else {
            serviceOutcome.setData(userChooseNottoUseExistingPAR(parSearchForCreate).getData());
            message = "New PAR created";
            serviceOutcome.setMessage(message);
        }
        return serviceOutcome;
    }

    @Override
    public ServiceOutcome<ParMaster> createNewPar(String patientidno, String hcpcsno) throws Exception {
        // TODO Auto-generated method stub

        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();
        Long salesOrderId = salesOrderMasterRepository.getSalesOrderIdFromPatientIdno(patientidno).toFuture().get();
        SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrder(salesOrderId).toFuture().get();
        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails = salesOrderInsuranceDetailsRepository
            .getSalesOrderInsurancePriceDetailsOnSalesOrder(salesOrderMaster.getSalesOrderId()).toFuture().get();

        ParMaster parMaster = generatePar(hcpcsno, salesOrderMaster, salesOrderInsuranceDetails);

        outCome.setData(parMaster);
        outCome.setMessage("New PAR created successfully");
        outCome.setOutcome(true);

        return outCome;
    }

    @Override
    public ServiceOutcome<ParMaster> parextension(UUID parUuid, String expirationDate, String authorizedBy,
                                                  String extensionApprovalDate, String comments) throws Exception {
        // TODO Auto-generated method stub
        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();
        ParMaster parMaster = parMasterRepository.getParMasterOnParUUID(parUuid).toFuture().get();

        parMaster.setExpirationDate(CommonUtilities.stringtodateConverter(expirationDate));
        parMaster.setAuthorizedBy(authorizedBy);
        parMaster.setExtensionApprovalDate(CommonUtilities.stringtodateConverter(extensionApprovalDate));
        parMaster.setComments(comments);
        parMaster.setParStatus("Inactive");
        parMaster.setLogInStatus(null);
        parMaster.setLogInDate(null);
        parMaster.setExtensionStatus("Extended");
        parMaster.setStatus("Active");
        parMaster.setUpdatedById(Long.valueOf("5501"));
        parMaster.setUpdatedByName("ANDROKTASIAI");
        parMaster.setUpdatedDate(LocalDate.now());

        parMasterRepository.save(parMaster);

        outCome.setData(parMaster);
        outCome.setMessage("PAR data Extended");
        outCome.setOutcome(true);

        return outCome;
    }

    @Override
    public ServiceOutcome<ParMaster> parrenewal(UUID parUuid, String renewedPARNo, String effectiveStartDate,
                                                String expirationDate, String renewalAuthorizedBy, String comments) throws Exception {

        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();
        ParMaster parMaster = parMasterRepository.getParMasterOnParUUID(parUuid).toFuture().get();

        if (!CommonUtilities.isStringNullOrBlank(parMaster.getOriginalParNo())) {
            parMaster.setOriginalParNo(parMaster.getParNo());
        }
        parMaster.setParNo(renewedPARNo);
        parMaster.setEffectiveStartDate(CommonUtilities.stringtodateConverter(effectiveStartDate));
        parMaster.setExpirationDate(CommonUtilities.stringtodateConverter(expirationDate));
        parMaster.setParStatus("Inactive");
        parMaster.setComments(comments);
        parMaster.setLogInStatus(null);
        parMaster.setLogInDate(null);
        parMaster.setRenewalStatus("Renewed");
        parMaster.setRenewalDate(LocalDate.now());
        parMaster.setRenewalAuthorizedBy(renewalAuthorizedBy);
        parMaster.setRenewalReqReplyStatus("Recieved");
        parMaster.setStatus("Active");
        parMaster.setUpdatedById(Long.valueOf("5501"));
        parMaster.setUpdatedByName("ANDROKTASIAI");
        parMaster.setUpdatedDate(LocalDate.now());

        parMasterRepository.save(parMaster);

        outCome.setData(parMaster);
        outCome.setMessage("Renewal Data Updated");
        outCome.setOutcome(true);

        return outCome;
    }

    @Override
    public Mono<String> isPARRequired(String hcpcsNo, String itemNo, LocalDate dos, Long priceTableId) {
        System.out.println("In :: isPARRequired :: priceTableId=" + priceTableId);
        System.out.println("In :: isPARRequired :: itemNo=" + itemNo);
        System.out.println("In :: isPARRequired :: dos=" + dos);
        System.out.println("In :: isPARRequired :: hcpcsNo=" + hcpcsNo);
        return parMasterRepository.isPARRequired(hcpcsNo, itemNo, dos, priceTableId);
        //System.out.println("Test Required "+parMasterRepository.isPARRequired(itemNo,hcpcsNo).collectList().toFuture().get());
    }

    @Override
    public ServiceOutcome<ParMaster> delinkItem(Long parId, Long soId, String hcpcsCode, Long itemId)
        throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub

        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();

        List<ParSoMap> parSoMapList = parSOMapRepository.getParSOMapOnpar(parId).collectList().toFuture().get();
        ParMaster parMaster = parMasterRepository.getParMasterOnParId(parId).toFuture().get();

        if (parMaster.getParStatus().equalsIgnoreCase("Initiated")) {
            if (parSoMapList.size() != 1) {
                outCome.setData(null);
                outCome.setMessage("Multiple SO is linked to this PAR, cannot delink");
                outCome.setOutcome(false);
            } else {

                ParDetails parDetails = parDetailsRepository.getDelinkItem(parId, hcpcsCode, itemId).toFuture().get();

                parDetails.setStatus("Inactive");
                parDetails.setUpdatedById(Long.valueOf("5501"));
                parDetails.setUpdatedByName("ANDROKTASIAI");
                parDetails.setUpdatedDate(LocalDate.now());

                parDetailsRepository.save(parDetails);

                outCome.setData(parMaster);
                outCome.setMessage("Item is successfully delinked");
                outCome.setOutcome(true);
            }
        } else if (parMaster.getParStatus().equalsIgnoreCase("Active")) {
            ParSoMap ParSoMap = parSOMapRepository.getParSOMapOnSalesOrderNpar(soId, parId).toFuture().get();

            ParSoMap.setStatus("Inactive");
            ParSoMap.setUpdatedById(Long.valueOf("5501"));
            ParSoMap.setUpdatedByName("ANDROKTASIAI");
            ParSoMap.setUpdatedDate(LocalDate.now());

            parSOMapRepository.save(ParSoMap);

            outCome.setData(parMaster);
            outCome.setMessage("Item is successfully delinked");
            outCome.setOutcome(true);
        }

        return outCome;
    }

    @Override
    public ServiceOutcome<ParMaster> delinkAllItems(DelinkItemsinPAR delinkItemsinPAR) throws ExecutionException, InterruptedException {
        // TODO Auto-generated method stub

        ServiceOutcome<ParMaster> outCome = new ServiceOutcome<ParMaster>();

        for (ItemDetail itemDetail : delinkItemsinPAR.getItemDetails()) {

            List<ParSoMap> parSoMapList = parSOMapRepository.getParSOMapOnpar(itemDetail.getParId()).collectList().toFuture().get();
            ParMaster parMaster = parMasterRepository.getParMasterOnParId(itemDetail.getParId()).toFuture().get();

            if (parMaster.getParStatus().equalsIgnoreCase("Initiated")) {
                if (parSoMapList.size() != 1) {
                    outCome.setData(null);
                    outCome.setMessage("Multiple SO is linked to this PAR, cannot delink");
                    outCome.setOutcome(false);
                } else {

                    ParDetails parDetails = parDetailsRepository.getDelinkItem(itemDetail.getParId(), itemDetail.getHcpcsCode(), itemDetail.getItemId()).toFuture()
                        .get();

                    parDetails.setStatus("Inactive");
                    parDetails.setUpdatedById(Long.valueOf("5501"));
                    parDetails.setUpdatedByName("ANDROKTASIAI");
                    parDetails.setUpdatedDate(LocalDate.now());

                    parDetailsRepository.save(parDetails);

                    outCome.setData(parMaster);
                    outCome.setMessage("Item is successfully delinked");
                    outCome.setOutcome(true);
                }
            } else if (parMaster.getParStatus().equalsIgnoreCase("Active")) {
                ParSoMap ParSoMap = parSOMapRepository.getParSOMapOnSalesOrderNpar(delinkItemsinPAR.getSoId(), itemDetail.getParId()).toFuture().get();

                ParSoMap.setStatus("Inactive");
                ParSoMap.setUpdatedById(Long.valueOf("5501"));
                ParSoMap.setUpdatedByName("ANDROKTASIAI");
                ParSoMap.setUpdatedDate(LocalDate.now());

                parSOMapRepository.save(ParSoMap);

                outCome.setData(parMaster);
                outCome.setMessage("Item is successfully delinked");
                outCome.setOutcome(true);
            }
        }
        return outCome;
    }

    @Override
    public Mono<String> isPARRequiredReactive(String hcpcsNo, String itemNo, LocalDate dos, Long priceTableId) {
        return parMasterRepository.isPARRequired(hcpcsNo, itemNo, dos, priceTableId);
    }

    @Override
    public Mono<ServiceOutcome> dLinkParForItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {
        //Todo updatedById, updatedByName hard code value will change later.
        Long updatedById = 79L;
        String updatedByName = "Pritam Panja";
        return salesOrderItemDetailsRepositoryExtended.getDLinkParForItems(parId, salesOrderId, salesOrderDetailsItemId, updatedById, updatedByName)
            .map(result -> {
                if (result) {
                    return new ServiceOutcome(null, true, "Successfully D-linked.");
                } else return new ServiceOutcome(null, false, "Failed to D-linked.");

            });
    }

    @Override
    public Mono<ServiceOutcome> linkForParItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {
        //Todo updatedById, updatedByName hard code value will change later.
        Long updatedById = 79L;
        String updatedByName = "Pritam Panja";
        return salesOrderItemDetailsRepositoryExtended.getLinkForParItems(parId, salesOrderId, salesOrderDetailsItemId, updatedById, updatedByName)
            .map(result -> {
                if (result) {
                    return new ServiceOutcome(null, true, "Successfully Linked.");
                } else return new ServiceOutcome(null, false, "Failed to Linked.");

            });
    }
}
