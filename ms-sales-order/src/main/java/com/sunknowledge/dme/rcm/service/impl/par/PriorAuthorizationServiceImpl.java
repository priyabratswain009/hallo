package com.sunknowledge.dme.rcm.service.impl.par;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.amazon.AmazonS3Service;
import com.sunknowledge.dme.rcm.amazon.BucketName;
import com.sunknowledge.dme.rcm.amazon.FileStore;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.application.properties.FileUploadProperties;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.documentutil.*;
import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.dto.RenwalOrExpiringPAR.RenwalOrExpiringPARDTO;
import com.sunknowledge.dme.rcm.dto.cmn.CmnResponseDetails;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ItemDetail;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.mshealthcheck.service.MicroserviceHealthCheck;
import com.sunknowledge.dme.rcm.repository.PatientDocumentSoMapRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.SalesOrderItemDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.cmn.CmnDocumentMasterRepo;
import com.sunknowledge.dme.rcm.repository.cmn.CmnRepo;
import com.sunknowledge.dme.rcm.repository.par.*;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderInsuranceDetailsRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderItemDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.*;
import com.sunknowledge.dme.rcm.service.dto.par.EfaxResponseDTOExtended;
import com.sunknowledge.dme.rcm.service.dto.par.SalesOrderInsurancePriceDetails;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.*;
import com.sunknowledge.dme.rcm.service.mapper.EfaxResponseMapper;
import com.sunknowledge.dme.rcm.service.mapper.ParMasterMapper;
import com.sunknowledge.dme.rcm.service.mapper.ParRequestDetailsMapper;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentSoMapMapper;
import com.sunknowledge.dme.rcm.service.par.ParRequestDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.PatientDocumentSoMapServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;


@Service
@Slf4j
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class PriorAuthorizationServiceImpl implements PriorAuthorizationService {
    private final WebClient webClient;
    @Autowired
    PatientDocumentSoMapServiceExtended patientDocumentSoMapServiceExtended;
    @Autowired
    private SalesOrderItemDetailsRepositoryExtended salesOrderItemDetailsRepositoryExtended;
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
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;
    @Autowired
    private ParRequestDetailsRepo parRequestDetailsRepo;
    @Autowired
    private FileStore fileStore;
    @Autowired
    private ParMasterMapper parMasterMapper;
    @Autowired
    private ParRequestDetailsMapper parRequestDetailsMapper;
    @Autowired
    private AmazonS3Client amazonS3Client;
    @Autowired
    private AmazonS3Service amazonS3Service;
    @Autowired
    private EfaxResponseRepositoryExtended efaxResponseRepositoryExtended;
    @Autowired
    private EfaxResponseMapper efaxResponseMapper;
    @Autowired
    private ParSoMapRepo parSoMapRepo;
    @Autowired
    private ParRequestDetailsServiceExtended parRequestDetailsServiceExtended;
    @Autowired
    private MicroserviceHealthCheck microserviceHealthCheck;
    @Autowired
    private PatientDocumentSoMapRepositoryExtended patientDocumentSoMapRepositoryExtended;
    @Autowired
    private PatientDocumentSoMapMapper patientDocumentSoMapMapper;

    public PriorAuthorizationServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/services").build();
    }

    private static Comparator customComparator() {
        return Comparator.comparing((String s) -> {
            if (s.startsWith("POR")) {
                return 1;
            } else if (s.startsWith("PAR")) {
                return 2;
            } else if (s.startsWith("CMN")) {
                return 3;
            } else if (s.startsWith("PID") || s.startsWith("PDD") || s.startsWith("PMR")) {
                return 4;
            } else {
                return 5; // For other cases, maintain the original order
            }
        }).thenComparing(Comparator.naturalOrder());
    }

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

    public Mono<ServiceOutcome> validateinitOrRenewalReport(long parId, Long soId, byte[] qrCodeBytes) throws Exception {

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
        return getPriorAuthorizationReport(parId, soId, objInsuranceMasterDTO, reqType, qrCodeBytes);
    }

    @Override
    public Mono<ServiceOutcome> getPriorAuthorizationReport(long parId, Long soId, InsuranceMasterDTO objInsuranceMasterDTO, String reqType, byte[] qrCodeBytes)
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

        String fileName = "PAR_" + parId + "_" + String.valueOf(newreqdtlId) + ".pdf";

        //// generating QR Code
        //new CommonPDFStubs().generateQRCode(parRequestDetails.getParRequestDetailsId().toString(), ApplicationConstants.TEMP_PAR_REQ_PATH);
        //CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        //byte[] qrCodeBytes = commonPDFStubs.generateQRCodeInAmazon(fileName);

        Document document = new Document(PageSize.A4, 35, 35, 50, 65);
        String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket(); // Replace with your S3 bucket name
        String s3Key = "parDocuments/" + fileName; // Specify the path in your S3 bucket

        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(ApplicationConstants.TEMP_PAR_REQ_PATH + fileName));
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            Rectangle rect = new Rectangle(70, 20, 480, 810);
            writer.setBoxSize("par", rect);
            HeaderFooterPageEventForAWS event = new HeaderFooterPageEventForAWS(qrCodeBytes);
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

            //PriorAuthReportBuilder.addQrCodeOnFooter(parId, writer);
            /*Image qrCodeImage = Image.getInstance(qrCodeBytes);
            PriorAuthReportBuilder.addQrCodeOnFooterInAwsBucket(document, qrCodeBytes, qrCodeImage);
            document.add(qrCodeImage);*/

            document.close();

            // Get the document bytes
            byte[] documentBytes = outputStream.toByteArray();

            // Upload the document to S3
            String eTag = fileStore.uploadToS3(bucketName, s3Key, documentBytes);
            log.info("ETag of the uploaded object: " + eTag);
            HashMap<String, String> output = new HashMap<>();
            if (eTag != null && !eTag.equals("")) {
                log.info("PDF created successfully.");
                output.put("eTag", eTag);
                output.put("initialOrRenewalFileName", fileName);
                return Mono.just(new ServiceOutcome<>(output, true, "Initial/Renewal PAR Document is generated successfully."));
            } else {
                return Mono.just(new ServiceOutcome<>(null, false, "Error generating Initial/Renewal PAR Document."));
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return Mono.just(new ServiceOutcome<>(null, false, "Error generating Initial/Renewal PAR Document."));
        }
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
        log.info("In :: isPARRequired :: priceTableId=" + priceTableId);
        log.info("In :: isPARRequired :: itemNo=" + itemNo);
        log.info("In :: isPARRequired :: dos=" + dos);
        log.info("In :: isPARRequired :: hcpcsNo=" + hcpcsNo);
        return parMasterRepository.isPARRequired(hcpcsNo, itemNo, dos, priceTableId);
        //log.info("Test Required "+parMasterRepository.isPARRequired(itemNo,hcpcsNo).collectList().toFuture().get());
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

    @Override
    public Flux<PARCustomOutputDTO> getPARDetailsData(Long soId) {
        return parMasterRepository.getPARDetailsData(soId);
    }

    @Override
    public Mono<ParMasterDTO> getParMasterDataByParId(Long parId) {
        return parMasterRepository.getParMasterDataByParId(parId).map(parMasterMapper::toDto);
    }

    @Override
    public Mono<ParRequestDetailsDTO> getParRequestDetailsForInitReactive(Long parId, String reqType) throws Exception {
        return parRequestDetailsRepository.getParRequestDetailsForInit(parId,
            reqType).map(parRequestDetailsMapper::toDto);
    }

    @Override
    public Mono<ParRequestDetailsDTO> getLatestParRequestDetailIdForRenewReactive() throws Exception {
        return parRequestDetailsRepository.getLatestParRequestDetailIdForRenew().map(parRequestDetailsMapper::toDto);
    }

    @Override
    public Mono<ServiceOutcome> generateFaxCoverDocument(SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData, int countNoOfPages, byte[] qrCodeBytes, String parrequestDetailsId) throws IOException, DocumentException, com.google.zxing.WriterException {

        String fileName = "Fax_Cover_" + parrequestDetailsId + ".pdf";
        String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket(); // Replace with your S3 bucket name
        String s3Key = "parDocuments/" + fileName; // Specify the path in your S3 bucket

        FaxCoverPDFInputDTO inputDTO = new FaxCoverPDFInputDTO();
        String ordProName = soClinicalInsuranceOutputData.getOrderingProviderFullName() != null ? soClinicalInsuranceOutputData.getOrderingProviderFullName().trim() : "";
        String ordProContact1 = soClinicalInsuranceOutputData.getOrderingProviderContactNo1() != null ? soClinicalInsuranceOutputData.getOrderingProviderContactNo1().trim() : "";
        String ordProFax = soClinicalInsuranceOutputData.getOrderingProviderFax() != null ? soClinicalInsuranceOutputData.getOrderingProviderFax().trim() : "";
        String branchName = soClinicalInsuranceOutputData.getBillingBranchName() != null ? soClinicalInsuranceOutputData.getBillingBranchName().trim() : "";
        String branchContact1 = soClinicalInsuranceOutputData.getBranchContactNo1() != null ? soClinicalInsuranceOutputData.getBranchContactNo1().trim() : "";
        String branchFax = soClinicalInsuranceOutputData.getBranchFax() != null ? soClinicalInsuranceOutputData.getBranchFax().trim() : "";
        String ordProNpi = soClinicalInsuranceOutputData.getOrderingProviderNpi() != null ? soClinicalInsuranceOutputData.getOrderingProviderNpi().trim() : "";
        String branchContactPerson = soClinicalInsuranceOutputData.getBranchContactPersonName() != null ? soClinicalInsuranceOutputData.getBranchContactPersonName().trim() : "";
        String billingBranchName = soClinicalInsuranceOutputData.getBillingBranchName() != null ? soClinicalInsuranceOutputData.getBillingBranchName().trim() : "";

        inputDTO.setAttn("");
        inputDTO.setAttnTo(ordProName);
        inputDTO.setAttnPhone(ordProContact1);
        inputDTO.setAttnfax(ordProFax);
        inputDTO.setAttnRe("");
        inputDTO.setFrom(branchName);
        inputDTO.setPhone(branchContact1);
        inputDTO.setFax(branchFax);
        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        inputDTO.setDate(dateInString + "");
        inputDTO.setNoOfPages(countNoOfPages + "");
        inputDTO.setNpi(ordProNpi);
        inputDTO.setName(branchContactPerson);
        inputDTO.setCompanyName(billingBranchName);

        //qrCodeBytes = fileStore.downloadFileFromS3(bucketName, s3KeyForQRCode); //get files from s3 bucket
        //log.info("=======qrCodeBytes======"+qrCodeBytes);

        Document document = new Document(PageSize.A4, 35, 35, 50, 50);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation() + "/" + fileName)));   //Code for local directory
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            //PriorAuthDocsHeaderFooter event = new PriorAuthDocsHeaderFooter("", fileUploadConfigProperties.getTempDocumentProperties().getLocation() + "/" + deliveryTicket.getDeliveryTicketId() + ".png");
            //writer.setPageEvent((PdfPageEvent) event);

            document.open();
            document.add(FaxCoverTableBuilder.createDeliveryDocumentMainBodyTitle(inputDTO));
            document.add(FaxCoverTableBuilder.createDeliveryDocumentMainBodyTitleSecondRow(inputDTO));
            document.add(FaxCoverTableBuilder.createDeliveryDocumentMainBodyTitleThirdRow(inputDTO.getDate()));
            document.add(FaxCoverTableBuilder.createDeliveryDocumentMainBodyTitleFourthRow(inputDTO.getNoOfPages()));
            document.add(FaxCoverTableBuilder.createFaxDocumentCommentsTitle());
            document.add(FaxCoverTableBuilder.createFaxDocumentCommentsContent("We will supply the equipments to your patient.\n\nPlease answer all questions in the Physician’s Order and sign & date at the bottom. Please do not forget to include Length Of Need and the Diagnosis Codes (ICD-10).\n**If at any time you make a change or mistake please initial and date.**\n** Please complete the front page only. The back page is just guidelines.**\n\n\nPLEASE INCLUDE YOUR NPI #", " " + inputDTO.getNpi(), "\n\n\nFax the completed forms to " + inputDTO.getFax() + ".\n\n\nThank you very much for your help and time with this matter! If at any time you have any questions please call our office.\n\n\n\nBest Regards,\n" + inputDTO.getName() + "\n" + inputDTO.getCompanyName() + ".\n\n\n\n\n\n\n\n\n\nThis e-mail, facsimile, or letter and any files or attachments transmitted with it contains information that is confidential and privileged. This information is intended only for the use of the individual(s) and entity (ies) to whom it is addressed. If you are the intended recipient, further disclosures are prohibited without proper authorization. If you are not the intended recipient, any disclosure, copying, printing, or use of this information is strictly prohibited and possibly a violation of federal or state law and regulations. If you have received this information in error, please notify " + branchName + " immediately at " + branchFax + ". " + branchName + " hereby claims all applicable privileges related to this information."));
            //document.add(new Paragraph("\n"));

            // Add QR code to the document
            Image qrCodeImage = Image.getInstance(qrCodeBytes);
            document.add(qrCodeImage);

            document.close();

            // Get the document bytes
            byte[] documentBytes = outputStream.toByteArray();

            // Upload the document to S3
            String eTag = fileStore.uploadToS3(bucketName, s3Key, documentBytes);
            log.info("ETag of the uploaded object: " + eTag);
            HashMap<String, String> output = new HashMap<>();
            if (eTag != null && !eTag.equals("")) {
                log.info("PDF created successfully.");
                output.put("eTag", eTag);
                output.put("faxCoverFileName", fileName);
                return Mono.just(new ServiceOutcome<>(output, true, "Fax Cover Document is generated successfully."));
            } else {
                return Mono.just(new ServiceOutcome<>(null, false, "Error generating Fax Cover Document."));
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return Mono.just(new ServiceOutcome<>(null, false, "Error generating Fax Cover Document."));
        }
    }

    @Override
    public Mono<ServiceOutcome> getDMEAuthorizationRequestForm1HighMarkHealthOptionsDocument(SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData, ParMasterDTO parMasterDTO,
                                                                                             List<PARCustomOutputDTO> parCustomOutputList, Long itemId, String soDetailsSaleType,
                                                                                             String parNo, String parRequestDetailsId, String documentNameList, String genericDocsNotes, byte[] qrCodeBytes) throws IOException, DocumentException, com.google.zxing.WriterException {

        DmeAuthReqForm1HighmarkHealthOptionsDTO inputDTO = new DmeAuthReqForm1HighmarkHealthOptionsDTO();

        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        inputDTO.setDate(dateInString + "");
        String patientFullName = soClinicalInsuranceOutputData.getPatientFullName() != null ? soClinicalInsuranceOutputData.getPatientFullName().trim() : "";
        String primaryInsurerPolicyNo = soClinicalInsuranceOutputData.getPrimaryInsurerPolicyNo() != null ? soClinicalInsuranceOutputData.getPrimaryInsurerPolicyNo().trim() : "";
        inputDTO.setMemberName(patientFullName);
        inputDTO.setMemberId(primaryInsurerPolicyNo);

        if (soClinicalInsuranceOutputData.getPatientDob() != null) {
            LocalDate localDate = soClinicalInsuranceOutputData.getPatientDob();
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(pattern);
            String patientDob = localDate.format(outputFormatter);
            inputDTO.setDob(patientDob);
        } else {
            inputDTO.setDob("");
        }

        String primaryDiagnosis = soClinicalInsuranceOutputData.getPrimaryDiagnosis() != null ? soClinicalInsuranceOutputData.getPrimaryDiagnosis().trim() : "";
        String icd10DiagnosisCode1 = soClinicalInsuranceOutputData.getIcd10DiagnosisCode1() != null ? soClinicalInsuranceOutputData.getIcd10DiagnosisCode1().trim() : "";
        inputDTO.setDiagnosis(primaryDiagnosis);
        inputDTO.setIct10Code(icd10DiagnosisCode1);

        String ordProName = soClinicalInsuranceOutputData.getOrderingProviderFullName() != null ? soClinicalInsuranceOutputData.getOrderingProviderFullName().trim() : "";
        String ordProNpi = soClinicalInsuranceOutputData.getOrderingProviderNpi() != null ? soClinicalInsuranceOutputData.getOrderingProviderNpi().trim() : "";
        inputDTO.setProviderName(ordProName);
        inputDTO.setProviderNpiNumber(ordProNpi);
        String providerFullAddr = "";
        String providerAddr1 = soClinicalInsuranceOutputData.getOrderingProviderAddressLine1() != null && !soClinicalInsuranceOutputData.getOrderingProviderAddressLine1().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderAddressLine1().trim() : "";
        String providerAddr2 = soClinicalInsuranceOutputData.getOrderingProviderAddressLine2() != null && !soClinicalInsuranceOutputData.getOrderingProviderAddressLine2().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderAddressLine2().trim() : "";
        String providerCity = soClinicalInsuranceOutputData.getOrderingProviderCity() != null && !soClinicalInsuranceOutputData.getOrderingProviderCity().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderCity().trim() : "";
        String providerState = soClinicalInsuranceOutputData.getOrderingProviderState() != null && !soClinicalInsuranceOutputData.getOrderingProviderState().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderState().trim() : "";
        String providerZip = soClinicalInsuranceOutputData.getOrderingProviderZip() != null && !soClinicalInsuranceOutputData.getOrderingProviderZip().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderZip().trim() : "";
        if (!providerAddr1.equals("")) {
            providerFullAddr = providerAddr1;
        }

        if (!providerAddr2.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerAddr2;
            } else {
                providerFullAddr = providerAddr2;
            }
        }

        if (!providerCity.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerCity;
            } else {
                providerFullAddr = providerCity;
            }
        }

        if (!providerState.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerState;
            } else {
                providerFullAddr = providerState;
            }
        }

        if (!providerZip.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerZip;
            } else {
                providerFullAddr = providerZip;
            }
        }
        inputDTO.setPracticeNameAndAddress(providerFullAddr);

        String ordProContact1 = soClinicalInsuranceOutputData.getOrderingProviderContactNo1() != null ? soClinicalInsuranceOutputData.getOrderingProviderContactNo1().trim() : "";
        String ordProFax = soClinicalInsuranceOutputData.getOrderingProviderFax() != null ? soClinicalInsuranceOutputData.getOrderingProviderFax().trim() : "";
        String billOrdProOrgName = soClinicalInsuranceOutputData.getBillingProviderOrganisationName() != null ? soClinicalInsuranceOutputData.getBillingProviderOrganisationName().trim() : "";
        String billProNpi = soClinicalInsuranceOutputData.getBillingProviderNpi() != null ? soClinicalInsuranceOutputData.getBillingProviderNpi().trim() : "";
        inputDTO.setProviderPhone(ordProContact1);
        inputDTO.setProviderFax(ordProFax);
        inputDTO.setDmeProviderName(billOrdProOrgName);
        inputDTO.setDmeProviderNpiNumber(billProNpi);
        String billingProviderFullAddr = "";
        String billingProviderAddr1 = soClinicalInsuranceOutputData.getBillingProviderAddressLine1() != null && !soClinicalInsuranceOutputData.getBillingProviderAddressLine1().equals("") ? soClinicalInsuranceOutputData.getBillingProviderAddressLine1().trim() : "";
        String billingProviderAddr2 = soClinicalInsuranceOutputData.getBillingProviderAddressLine2() != null && !soClinicalInsuranceOutputData.getBillingProviderAddressLine2().equals("") ? soClinicalInsuranceOutputData.getBillingProviderAddressLine2().trim() : "";
        String billingProviderCity = soClinicalInsuranceOutputData.getBillingProviderCity() != null && !soClinicalInsuranceOutputData.getBillingProviderCity().equals("") ? soClinicalInsuranceOutputData.getBillingProviderCity().trim() : "";
        String billingProviderState = soClinicalInsuranceOutputData.getBillingProviderState() != null && !soClinicalInsuranceOutputData.getBillingProviderState().equals("") ? soClinicalInsuranceOutputData.getBillingProviderState().trim() : "";
        String billingProviderZip = soClinicalInsuranceOutputData.getBillingProviderZipCode() != null && !soClinicalInsuranceOutputData.getBillingProviderZipCode().equals("") ? soClinicalInsuranceOutputData.getBillingProviderZipCode().trim() : "";
        if (!billingProviderAddr1.equals("")) {
            billingProviderFullAddr = billingProviderAddr1;
        }

        if (!billingProviderAddr2.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderAddr2;
            } else {
                billingProviderFullAddr = billingProviderAddr2;
            }
        }

        if (!billingProviderCity.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderCity;
            } else {
                billingProviderFullAddr = billingProviderCity;
            }
        }

        if (!billingProviderState.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderState;
            } else {
                billingProviderFullAddr = billingProviderState;
            }
        }

        if (!billingProviderZip.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderZip;
            } else {
                billingProviderFullAddr = billingProviderZip;
            }
        }

        inputDTO.setDmeProviderAddress(billingProviderFullAddr);

        String branchContact1 = soClinicalInsuranceOutputData.getBranchContactNo1() != null ? soClinicalInsuranceOutputData.getBranchContactNo1().trim() : "";
        String branchFax = soClinicalInsuranceOutputData.getBranchFax() != null ? soClinicalInsuranceOutputData.getBranchFax().trim() : "";
        String branchContactPerson = soClinicalInsuranceOutputData.getBranchContactPersonName() != null ? soClinicalInsuranceOutputData.getBranchContactPersonName().trim() : "";
        String branchContact2 = soClinicalInsuranceOutputData.getBranchContactNo2() != null ? soClinicalInsuranceOutputData.getBranchContactNo2().trim() : "";
        inputDTO.setDmeProviderPhone(branchContact1);
        inputDTO.setDmeProviderFax(branchFax);
        inputDTO.setContactName(branchContactPerson);
        inputDTO.setContactPhone(branchContact2);

        if (soDetailsSaleType != null && soDetailsSaleType.equalsIgnoreCase("Purchase")) {
            inputDTO.setPurchaseYes(true);
            inputDTO.setPurchaseNo(false);
        } else {
            inputDTO.setPurchaseYes(false);
            inputDTO.setPurchaseNo(true);
        }

        if (soDetailsSaleType != null && soDetailsSaleType.equalsIgnoreCase("Rental")) {
            inputDTO.setRentalYes(true);
            inputDTO.setRentalNo(false);
        } else {
            inputDTO.setRentalYes(false);
            inputDTO.setRentalNo(true);
        }

        if (parNo != null && !parNo.equals("")) {
            inputDTO.setReplacementYes(true);
            inputDTO.setReplacementNo(false);
            inputDTO.setInitialRequestYes(false);
            inputDTO.setInitialRequestNo(true);
        } else {
            inputDTO.setInitialRequestYes(true);
            inputDTO.setInitialRequestNo(false);
            inputDTO.setReplacementYes(false);
            inputDTO.setReplacementNo(true);
        }

        log.info("============inputDTO::Generic Docs===========" + inputDTO);
        HashMap<String, String> procedureCodeDataMap = new HashMap<>();
        if (parCustomOutputList.size() > 0) {
            int j = 0;
            for (int i = 0; i < parCustomOutputList.size(); i++) {
                j = i + 1;
                if (parCustomOutputList.get(i).getHcpcsCode() != null && !parCustomOutputList.get(i).getHcpcsCode().equals("")) {
                    procedureCodeDataMap.put("procedureCodeRow" + j, parCustomOutputList.get(i).getHcpcsCode());
                    procedureCodeDataMap.put("codeDescriptionRow" + j, (parCustomOutputList.get(i).getDescription() != null && !parCustomOutputList.get(i).getDescription().trim().equals("")) ? parCustomOutputList.get(i).getDescription() : "");
                    procedureCodeDataMap.put("startDateRow" + j, (parCustomOutputList.get(i).getEffectiveStartDate() != null && !parCustomOutputList.get(i).getEffectiveStartDate().equals("")) ? parCustomOutputList.get(i).getEffectiveStartDate() + "" : "");
                    procedureCodeDataMap.put("endDateRow" + j, (parCustomOutputList.get(i).getExpirationDate() != null && !parCustomOutputList.get(i).getExpirationDate().equals("")) ? parCustomOutputList.get(i).getExpirationDate() + "" : "");
                    procedureCodeDataMap.put("noOfUnitsRow" + j, (parCustomOutputList.get(i).getItemQuantity() != null ? parCustomOutputList.get(i).getItemQuantity() + "" : ""));
                }
            }
        }

        inputDTO.setClinicalDocumentation("PMR Files :" + documentNameList);
        inputDTO.setNotes(genericDocsNotes);

        log.info("=========procedureCodeDataMap=========" + procedureCodeDataMap);
        String fileName = "DME_Authorization_Request_Form_" + parRequestDetailsId + ".pdf";
        String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket(); // Replace with your S3 bucket name
        String s3Key = "parDocuments/" + fileName; // Specify the path in your S3 bucket

        Document document = new Document(PageSize.A4, 35, 35, 50, 50);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation() + "/" + fileName)));
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            PdfFormField radioGroup = PdfFormField.createRadioButton(writer, true);
            radioGroup.setFieldName("dmeAuthorizationRequestForm1");

            //Generate QR Code
            //commonPDFStubs.generateQRCode(qrPath, fileUploadConfigProperties.getPriorAuthorizationQrcodeProperties().getLocation());
            DMEAuthorizationRequestFormHeaderFooterPageEvent event = new DMEAuthorizationRequestFormHeaderFooterPageEvent(qrCodeBytes);
            writer.setPageEvent(event);
            String paragraphContent = "Complete and fax all requested information below including any supporting documentation as applicable to Highmark Health Options at";
            String paragraphContent1 = " 1-855-451-6663. Authorization is based on medical necessity. Incomplete information or illegible forms will delay processing.";
            String paragraphContent2 = "Questions or concerns? ";
            String paragraphContent3 = "Call Utilization Management at 1-844-325-6251, Monday through Friday, 8 a.m. to 5 p.m.";
            document.open();
            document.add(DMEAuthorizationRequestFormTableBuilder.createDeliveryDocumentMainBodyTitle());
            document.add(new Paragraph("\n"));
            //document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderInBody(paragraphContent, paragraphContent1));
            //document.add(new Paragraph("\n"));
            //document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderInBody1(paragraphContent2, paragraphContent3));
            document.add(DMEAuthorizationRequestFormTableBuilder.createTableForDate(inputDTO, writer));

            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" Member Information", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder1(Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol3(inputDTO, Font.NORMAL));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" Diagnosis", " ICD-10 Code", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getDiagnosis(), inputDTO.getIct10Code(), Font.NORMAL, 12));
            //document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" Provider Information", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" Provider Name", " Provider NPI Number", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getProviderName(), inputDTO.getProviderNpiNumber(), Font.NORMAL, 0));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder5(" Practice Name and Address", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol1(inputDTO.getPracticeNameAndAddress(), Font.NORMAL));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" Provider Phone", " Provider Fax", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getProviderPhone(), inputDTO.getProviderFax(), Font.NORMAL, 12));
            //document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" DME Provider Information", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" DME Provider Name", " DME Provider NPI Number", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getDmeProviderName(), inputDTO.getDmeProviderNpiNumber(), Font.NORMAL, 0));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder5(" DME Provider Address", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol1(inputDTO.getDmeProviderAddress(), Font.NORMAL));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" DME Provider Phone", " DME Provider Fax", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getDmeProviderPhone(), inputDTO.getDmeProviderFax(), Font.NORMAL, 0));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder3(" Contact Name", " Contact Phone", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol2(inputDTO.getContactName(), inputDTO.getContactPhone(), Font.NORMAL, 12));
            //document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" Billing Code Information (Attach supplemental sheet if necessary)", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createCheckBoxInTableWithCol20(radioGroup, inputDTO, "checkbox", Font.NORMAL));
            //document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(" Procedure Code", " Code Description", " Start Date", " End Date", " Number of Units", Font.BOLD));
            if (procedureCodeDataMap.containsKey("procedureCodeRow1")) {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow1"), procedureCodeDataMap.get("codeDescriptionRow1"), procedureCodeDataMap.get("startDateRow1"), procedureCodeDataMap.get("endDateRow1"), procedureCodeDataMap.get("noOfUnitsRow1"), Font.NORMAL));
            } else {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
            }
            if (procedureCodeDataMap.containsKey("procedureCodeRow2")) {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow2"), procedureCodeDataMap.get("codeDescriptionRow2"), procedureCodeDataMap.get("startDateRow2"), procedureCodeDataMap.get("endDateRow2"), procedureCodeDataMap.get("noOfUnitsRow2"), Font.NORMAL));
            } else {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
            }

            if (procedureCodeDataMap.containsKey("procedureCodeRow3")) {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow3"), procedureCodeDataMap.get("codeDescriptionRow3"), procedureCodeDataMap.get("startDateRow3"), procedureCodeDataMap.get("endDateRow3"), procedureCodeDataMap.get("noOfUnitsRow3"), Font.NORMAL));
            } else {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
            }

            if (procedureCodeDataMap.containsKey("procedureCodeRow4")) {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5Bottom(procedureCodeDataMap.get("procedureCodeRow4"), procedureCodeDataMap.get("codeDescriptionRow4"), procedureCodeDataMap.get("startDateRow4"), procedureCodeDataMap.get("endDateRow4"), procedureCodeDataMap.get("noOfUnitsRow4"), Font.NORMAL));
            } else {
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5Bottom("", "", "", "", "", Font.NORMAL));
            }

            if (parCustomOutputList.size() > 4) { //
                document.newPage();
                document.add(DMEAuthorizationRequestFormTableBuilder.createDeliveryDocumentMainBodyTitle());
                document.add(new Paragraph("\n"));
                document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(" Procedure Code", " Code Description", " Start Date", " End Date", " Number of Units", Font.BOLD));
                if (procedureCodeDataMap.containsKey("procedureCodeRow5")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow5"), procedureCodeDataMap.get("codeDescriptionRow5"), procedureCodeDataMap.get("startDateRow5"), procedureCodeDataMap.get("endDateRow5"), procedureCodeDataMap.get("noOfUnitsRow5"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
                }

                if (procedureCodeDataMap.containsKey("procedureCodeRow6")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow6"), procedureCodeDataMap.get("codeDescriptionRow6"), procedureCodeDataMap.get("startDateRow6"), procedureCodeDataMap.get("endDateRow6"), procedureCodeDataMap.get("noOfUnitsRow6"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
                }

                if (procedureCodeDataMap.containsKey("procedureCodeRow7")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow7"), procedureCodeDataMap.get("codeDescriptionRow7"), procedureCodeDataMap.get("startDateRow7"), procedureCodeDataMap.get("endDateRow7"), procedureCodeDataMap.get("noOfUnitsRow7"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
                }

                if (procedureCodeDataMap.containsKey("procedureCodeRow8")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow8"), procedureCodeDataMap.get("codeDescriptionRow8"), procedureCodeDataMap.get("startDateRow8"), procedureCodeDataMap.get("endDateRow8"), procedureCodeDataMap.get("noOfUnitsRow8"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
                }

                if (procedureCodeDataMap.containsKey("procedureCodeRow9")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5(procedureCodeDataMap.get("procedureCodeRow9"), procedureCodeDataMap.get("codeDescriptionRow9"), procedureCodeDataMap.get("startDateRow9"), procedureCodeDataMap.get("endDateRow9"), procedureCodeDataMap.get("noOfUnitsRow9"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5("", "", "", "", "", Font.NORMAL));
                }

                if (procedureCodeDataMap.containsKey("procedureCodeRow10")) {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5Bottom(procedureCodeDataMap.get("procedureCodeRow10"), procedureCodeDataMap.get("codeDescriptionRow10"), procedureCodeDataMap.get("startDateRow10"), procedureCodeDataMap.get("endDateRow10"), procedureCodeDataMap.get("noOfUnitsRow10"), Font.NORMAL));
                } else {
                    document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilderWithCol5Bottom("", "", "", "", "", Font.NORMAL));
                }
            }
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            //document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphDocumentFooter("Highmark Health Options is an independent licensee of the Blue Cross Blue Shield Association, an association of independent Blue Cross Blue Shield Plans."));
            document.newPage();
            document.add(DMEAuthorizationRequestFormTableBuilder.createDeliveryDocumentMainBodyTitle());
            document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" Clinical Documentation", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createMultiLineTextBox("Include or attach any clinical/office notes, prescription, doctor’s orders, labs, imaging reports, therapy assessment, successful trial of requested item, quote sheet, documentation member has been instructed on item requested, home evaluation, compliance reported for ongoing request as applicable to support medical necessity.", Font.BOLD, 30f, 30f, 0.1f, 0.1f));
            document.add(DMEAuthorizationRequestFormTableBuilder.createMultiLineTextBox(inputDTO.getClinicalDocumentation(), Font.NORMAL, 80f, 80f, 0f, 0.1f));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphTitleTableBuilder(" Notes", Font.BOLD));
            document.add(DMEAuthorizationRequestFormTableBuilder.createMultiLineTextBox(inputDTO.getNotes(), Font.NORMAL, 480f, 480f, 0.1f, 0.1f));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            //document.add(DMEAuthorizationRequestFormTableBuilder.createParagraphDocumentFooter("Highmark Health Options is an independent licensee of the Blue Cross Blue Shield Association, an association of independent Blue Cross Blue Shield Plans."));
            writer.addAnnotation(radioGroup);
            document.close();

            // Flatten the checkboxes
            PdfReader reader = new PdfReader(outputStream.toByteArray());
            ByteArrayOutputStream flattenedOutputStream = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, flattenedOutputStream);
            AcroFields acroFields = stamper.getAcroFields();
            acroFields.setGenerateAppearances(true);
            stamper.setFormFlattening(true);
            stamper.close();

            // Get the flattened PDF as bytes
            byte[] documentBytes = flattenedOutputStream.toByteArray();

            //Load the document, Flatten Code For Local
            /*PDDocument pDDocument = PDDocument.load(new File(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation()+"/"+fileName));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();


            //Flatten the document
            pDAcroForm.flatten();

            //Save the document
            pDDocument.save(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation()+"/"+fileName);
            pDDocument.close();*/

            // Get the document bytes
            //byte[] documentBytes = outputStream.toByteArray();

            // Upload the document to S3
            String eTag = fileStore.uploadToS3(bucketName, s3Key, documentBytes);
            HashMap<String, String> output = new HashMap<>();
            log.info("ETag of the uploaded object: " + eTag);
            if (eTag != null && !eTag.equals("")) {
                log.info("PDF created successfully.");
                output.put("eTag", eTag);
                output.put("dmeAuthForm1fileName", fileName);
                return Mono.just(new ServiceOutcome<>(output, true, "DME Authorization Request Form 1 High Mark Health Options Document is created successfully."));
            } else {
                return Mono.just(new ServiceOutcome<>(null, false, "Error generating DME Authorization Request Form 1 Document."));
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return Mono.just(new ServiceOutcome<>(null, false, "Error generating DME Authorization Request Form 1 Document."));
        }
    }

    @Override
    public Mono<ServiceOutcome> getDMEAuthorizationMedicareRequestDocument(SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData, ParMasterDTO parMasterDTO,
                                                                           List<PARCustomOutputDTO> parCustomOutputList, List<SalesOrderItemDetailsDTO> soItemDetailsList, Long itemId, String soDetailsSaleType,
                                                                           String parNo, String primaryHcpcsCode, int countNoOfPages, String parRequestDetailsId, byte[] qrCodeBytes) throws IOException, DocumentException {
        DMEAuthorizationMedicareInputDTO inputDTO = new DMEAuthorizationMedicareInputDTO();
        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());
        inputDTO.setCompanyName("CGS Administrators, LLC");
        inputDTO.setFormType("DME MAC JURISDICTION C");
        inputDTO.setRequestDate(dateInString + "");
        inputDTO.setNumberOfPages(countNoOfPages + "");
        inputDTO.setHcpcsCode(primaryHcpcsCode);
        HashMap<String, String> mapAccessoryHcpcsCodes = new HashMap<String, String>();
        int j = 1;
        if (soItemDetailsList.size() > 0) {
            for (int i = 0; i < soItemDetailsList.size(); i++) { //SalesOrderItemDetailsDTO eachSoItemDetails : soItemDetailsList
                if (!soItemDetailsList.get(i).getSalesOrderDetailsProcCode().equals(primaryHcpcsCode)) {
                    mapAccessoryHcpcsCodes.put("accessoryHcpcsCode" + j, (soItemDetailsList.get(i).getSalesOrderDetailsProcCode() != null && !soItemDetailsList.get(i).getSalesOrderDetailsProcCode().equals("")) ? soItemDetailsList.get(i).getSalesOrderDetailsProcCode().trim() : "");
                    j++;
                }
            }
        }

        if (parNo != null && !parNo.equals("")) {
            inputDTO.setInitialValue(false);
            inputDTO.setResubmissionValue(true);
        } else {
            inputDTO.setInitialValue(true);
            inputDTO.setResubmissionValue(false);
        }

        String patientFullName = soClinicalInsuranceOutputData.getPatientFullName() != null && !soClinicalInsuranceOutputData.getPatientFullName().equals("") ? soClinicalInsuranceOutputData.getPatientFullName() : "";
        inputDTO.setBeneficiaryInfoName(patientFullName);
        if (soClinicalInsuranceOutputData.getPatientMemberId() != null && !soClinicalInsuranceOutputData.getPatientMemberId().equals("")) {
            inputDTO.setBeneficiaryInfoMedicareID(soClinicalInsuranceOutputData.getPatientMemberId());
        } else {
            if (soClinicalInsuranceOutputData.getPrimaryInsurerPolicyNo() != null && !soClinicalInsuranceOutputData.getPrimaryInsurerPolicyNo().equals("")) {
                inputDTO.setBeneficiaryInfoMedicareID(soClinicalInsuranceOutputData.getPrimaryInsurerPolicyNo());
            } else {
                inputDTO.setBeneficiaryInfoMedicareID("");
            }
        }

        if (soClinicalInsuranceOutputData.getPatientDob() != null) {
            LocalDate localDate = soClinicalInsuranceOutputData.getPatientDob();
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(pattern);
            String patientDob = localDate.format(outputFormatter);
            inputDTO.setBeneficiaryInfoDOB(patientDob);
        } else {
            inputDTO.setBeneficiaryInfoDOB("");
        }
        inputDTO.setBeneficiaryInfoStateOfResidence((soClinicalInsuranceOutputData.getPatientDeliveryState() != null && !soClinicalInsuranceOutputData.getPatientDeliveryState().equals("")) ? soClinicalInsuranceOutputData.getPatientDeliveryState().trim() : "");

        String supplierName = soClinicalInsuranceOutputData.getBillingProviderOrganisationName() != null && !soClinicalInsuranceOutputData.getBillingProviderOrganisationName().equals("") ? soClinicalInsuranceOutputData.getBillingProviderOrganisationName().trim() : "";
        String supplierNpi = soClinicalInsuranceOutputData.getBillingProviderNpi() != null && !soClinicalInsuranceOutputData.getBillingProviderNpi().equals("") ? soClinicalInsuranceOutputData.getBillingProviderNpi().trim() : "";
        String supplierPhone = soClinicalInsuranceOutputData.getBranchContactNo1() != null && !soClinicalInsuranceOutputData.getBranchContactNo1().equals("") ? soClinicalInsuranceOutputData.getBranchContactNo1().trim() : "";
        String supplierFax = soClinicalInsuranceOutputData.getBranchFax() != null && !soClinicalInsuranceOutputData.getBranchFax().equals("") ? soClinicalInsuranceOutputData.getBranchFax().trim() : "";
        String contactPerson = soClinicalInsuranceOutputData.getBranchContactPersonName() != null && !soClinicalInsuranceOutputData.getBranchContactPersonName().equals("") ? soClinicalInsuranceOutputData.getBranchContactPersonName().trim() : "";
        String billingProviderFullAddr = "";
        String billingProviderAddr1 = soClinicalInsuranceOutputData.getBillingProviderAddressLine1() != null && !soClinicalInsuranceOutputData.getBillingProviderAddressLine1().equals("") ? soClinicalInsuranceOutputData.getBillingProviderAddressLine1().trim() : "";
        String billingProviderAddr2 = soClinicalInsuranceOutputData.getBillingProviderAddressLine2() != null && !soClinicalInsuranceOutputData.getBillingProviderAddressLine2().equals("") ? soClinicalInsuranceOutputData.getBillingProviderAddressLine2().trim() : "";
        String billingProviderCity = soClinicalInsuranceOutputData.getBillingProviderCity() != null && !soClinicalInsuranceOutputData.getBillingProviderCity().equals("") ? soClinicalInsuranceOutputData.getBillingProviderCity().trim() : "";
        String billingProviderState = soClinicalInsuranceOutputData.getBillingProviderState() != null && !soClinicalInsuranceOutputData.getBillingProviderState().equals("") ? soClinicalInsuranceOutputData.getBillingProviderState().trim() : "";
        String billingProviderZip = soClinicalInsuranceOutputData.getBillingProviderZipCode() != null && !soClinicalInsuranceOutputData.getBillingProviderZipCode().equals("") ? soClinicalInsuranceOutputData.getBillingProviderZipCode().trim() : "";
        if (!billingProviderAddr1.equals("")) {
            billingProviderFullAddr = billingProviderAddr1;
        }

        if (!billingProviderAddr2.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderAddr2;
            } else {
                billingProviderFullAddr = billingProviderAddr2;
            }
        }

        if (!billingProviderCity.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderCity;
            } else {
                billingProviderFullAddr = billingProviderCity;
            }
        }

        if (!billingProviderState.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderState;
            } else {
                billingProviderFullAddr = billingProviderState;
            }
        }

        if (!billingProviderZip.equals("")) {
            if (!billingProviderFullAddr.equals("")) {
                billingProviderFullAddr = billingProviderFullAddr + ", " + billingProviderZip;
            } else {
                billingProviderFullAddr = billingProviderZip;
            }
        }

        inputDTO.setSupplierInfoName(supplierName);
        inputDTO.setSupplierInfoNpi(supplierNpi);
        inputDTO.setSupplierInfoPtan("");
        inputDTO.setSupplierInfoPhone(supplierPhone);
        inputDTO.setSupplierInfoAddress(billingProviderFullAddr);
        inputDTO.setSupplierInfoFax(supplierFax);
        inputDTO.setSupplierInfoPointOfContact(contactPerson);

        String ordProName = soClinicalInsuranceOutputData.getOrderingProviderFullName() != null ? soClinicalInsuranceOutputData.getOrderingProviderFullName() : "";
        String ordProNpi = soClinicalInsuranceOutputData.getOrderingProviderNpi() != null ? soClinicalInsuranceOutputData.getOrderingProviderNpi() : "";
        inputDTO.setTreatingPracInfoName(ordProName);
        inputDTO.setTreatingPracInfoNpi(ordProNpi);
        String providerFullAddr = "";
        String providerAddr1 = soClinicalInsuranceOutputData.getOrderingProviderAddressLine1() != null && !soClinicalInsuranceOutputData.getOrderingProviderAddressLine1().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderAddressLine1().trim() : "";
        String providerAddr2 = soClinicalInsuranceOutputData.getOrderingProviderAddressLine2() != null && !soClinicalInsuranceOutputData.getOrderingProviderAddressLine2().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderAddressLine2().trim() : "";
        String providerCity = soClinicalInsuranceOutputData.getOrderingProviderCity() != null && !soClinicalInsuranceOutputData.getOrderingProviderCity().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderCity().trim() : "";
        String providerState = soClinicalInsuranceOutputData.getOrderingProviderState() != null && !soClinicalInsuranceOutputData.getOrderingProviderState().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderState().trim() : "";
        String providerZip = soClinicalInsuranceOutputData.getOrderingProviderZip() != null && !soClinicalInsuranceOutputData.getOrderingProviderZip().equals("") ? soClinicalInsuranceOutputData.getOrderingProviderZip().trim() : "";
        if (!providerAddr1.equals("")) {
            providerFullAddr = providerAddr1;
        }

        if (!providerAddr2.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerAddr2;
            } else {
                providerFullAddr = providerAddr2;
            }
        }

        if (!providerCity.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerCity;
            } else {
                providerFullAddr = providerCity;
            }
        }

        if (!providerState.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerState;
            } else {
                providerFullAddr = providerState;
            }
        }

        if (!providerZip.equals("")) {
            if (!providerFullAddr.equals("")) {
                providerFullAddr = providerFullAddr + ", " + providerZip;
            } else {
                providerFullAddr = providerZip;
            }
        }
        inputDTO.setTreatingPracInfoAddress(providerFullAddr);
        String ordProContact1 = soClinicalInsuranceOutputData.getOrderingProviderContactNo1() != null ? soClinicalInsuranceOutputData.getOrderingProviderContactNo1() : "";
        String ordProFax = soClinicalInsuranceOutputData.getOrderingProviderFax() != null ? soClinicalInsuranceOutputData.getOrderingProviderFax() : "";
        inputDTO.setTreatingPracInfoPhone(ordProContact1);
        inputDTO.setTreatingPracInfoFax(ordProFax);

        inputDTO.setFooterFax("1.615.664.5960");
        inputDTO.setFooterMail("CGS - JUR C DME Medical Review - Condition of Payment Program");
        inputDTO.setFooterMailAddr("PO Box 24890");
        inputDTO.setFooterMailCity("Nashville");
        inputDTO.setFooterMailState("TN");
        inputDTO.setFooterMailZip("37202-4890");
        log.info("============inputDTO::Medicare Docs============" + inputDTO);
        String fileName = "DME_Authorization_Medicare_Request_" + parRequestDetailsId + ".pdf";
        String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket(); // Replace with your S3 bucket name
        String s3Key = "parDocuments/" + fileName; // Specify the path in your S3 bucket

        Document document = new Document(PageSize.A4, 35, 35, 50, 20);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation() + "/" + fileName)));

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            DMEAuthorizationRequestFormHeaderFooterPageEvent event = new DMEAuthorizationRequestFormHeaderFooterPageEvent(qrCodeBytes);
            writer.setPageEvent(event);
            PdfFormField radioGroup = PdfFormField.createRadioButton(writer, true);
            radioGroup.setFieldName("dmeAuthorizationMediCareForm");

            document.open();
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodyTitle(inputDTO));
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodyTitle1(inputDTO));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthMedicareTableBuilder.createFormDataTableBuilderWithCol13(radioGroup, inputDTO, "checkbox"));
            document.add(DMEAuthMedicareTableBuilder.createFormDataTableBuilderWithCol2(radioGroup, inputDTO, "checkbox"));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol8(mapAccessoryHcpcsCodes, 0));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol9(mapAccessoryHcpcsCodes, 15));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol9Next(mapAccessoryHcpcsCodes, 15));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle("SUBMISSION TYPE", 12, BaseColor.BLACK, Font.BOLD));
            document.add(DMEAuthMedicareTableBuilder.createFormDataTableBuilderWithCol8Check(radioGroup, inputDTO, "checkbox"));
            //document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle("If an expedited review is requested please provide rationale", 10, BaseColor.WHITE, Font.BOLD));
            //document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle(inputDTO.getSubmissionTypeComment(), 9, BaseColor.BLACK, Font.NORMAL));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle("BENEFICIARY INFORMATION", 12, BaseColor.BLACK, Font.BOLD));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol5(inputDTO, 0));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataSmallerTableBuilderWithCol5(inputDTO, 0));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle("SUPPLIER INFORMATION", 12, BaseColor.BLACK, Font.BOLD));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol8ForSI(inputDTO, 0));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol5ForSI("Phone", "Address", 0, 0.06f,
                0.19f, 0.06f, 0.67f, inputDTO.getSupplierInfoPhone(), inputDTO.getSupplierInfoAddress()));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol5ForSI("Fax", "Point of Contact", 0, 0.06f, 0.19f,
                0.12f, 0.55f, inputDTO.getSupplierInfoFax(), inputDTO.getSupplierInfoPointOfContact()));
            document.add(new Paragraph("\n"));
            document.add(DMEAuthMedicareTableBuilder.createDeliveryDocumentMainBodySubTitle("TREATING PRACTITIONER INFORMATION", 12, BaseColor.BLACK, Font.BOLD));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol5ForSI("Name", "NPI", 0, 0.06f, 0.67f,
                0.06f, 0.19f, inputDTO.getTreatingPracInfoName(), inputDTO.getTreatingPracInfoNpi()));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol5ForSI("Phone", "Address", 0, 0.06f, 0.19f,
                0.06f, 0.67f, inputDTO.getTreatingPracInfoPhone(), inputDTO.getTreatingPracInfoAddress()));
            document.add(DMEAuthMedicareTableBuilder.createInputBoxDataTableBuilderWithCol2ForSI("Fax", inputDTO.getTreatingPracInfoFax()));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            //Footer Design
            document.add(DMEAuthMedicareTableBuilder.createFooterTitleTableBuilderWithCol1("Please submit forms via the myCGS web portal, esMD, fax, or mail."));
            document.add(DMEAuthMedicareTableBuilder.createFooterTableBuilderWithCol2Fax("Fax", inputDTO));
            document.add(DMEAuthMedicareTableBuilder.createFooterTableBuilderWithCol2Mail("Mail", inputDTO));
            //writer.addAnnotation(radioGroup);
            document.close();

            // Flatten the checkboxes
            PdfReader reader = new PdfReader(outputStream.toByteArray());
            ByteArrayOutputStream flattenedOutputStream = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, flattenedOutputStream);
            AcroFields acroFields = stamper.getAcroFields();
            acroFields.setGenerateAppearances(true);
            stamper.setFormFlattening(true);
            stamper.close();

            // Get the flattened PDF as bytes
            byte[] documentBytes = flattenedOutputStream.toByteArray();

            //Load the document
            /*PDDocument pDDocument = PDDocument.load(new File(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation()+"/"+fileName));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();


            //Flatten the document
            pDAcroForm.flatten();

            //Save the document
            pDDocument.save(fileUploadConfigProperties.getPriorAuthorizationDocumentProperties().getLocation()+"/"+fileName);
            pDDocument.close();*/

            // Upload the document to S3
            String eTag = fileStore.uploadToS3(bucketName, s3Key, documentBytes);
            HashMap<String, String> output = new HashMap<>();
            log.info("ETag of the uploaded object: " + eTag);
            if (eTag != null && !eTag.equals("")) {
                log.info("PDF created successfully.");
                output.put("eTag", eTag);
                output.put("dmeAuthMedicareFormfileName", fileName);
                return Mono.just(new ServiceOutcome<>(output, true, "DME Authorization Medicare Request Document is created successfully."));
            } else {
                return Mono.just(new ServiceOutcome<>(null, false, "Error generating DME Authorization Medicare Request Document."));
            }
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            return Mono.just(new ServiceOutcome<>(null, false, "Error generating DME Authorization Medicare Request Document."));
        }
    }

    @Override
    public Mono<ServiceOutcome> generateCombinedParDocument(ServiceOutcome faxCoverDocsOutCome, ServiceOutcome priorAuthGenericDocsOutCome,
                                                            ServiceOutcome priorAuthMediCareDocsOutCome, ServiceOutcome initialOrRenewalDocsOutCome, ServiceOutcome swoDocsOutCome,
                                                            String combinedFileName, String documentNameList, ParRequestDetailsDTO parRequestDetailsDto, String qrCodeName) throws DocumentException, IOException {
        log.info("==========generateCombinedParDocument::Starts==========");
        String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket();
        String combinedFilePath = "parDocuments/" + combinedFileName;

        // Download files from S3
        List<byte[]> fileContents = new ArrayList<>();
        if (faxCoverDocsOutCome.getOutcome() != null && faxCoverDocsOutCome.getOutcome()) {
            HashMap<String, String> output = (HashMap<String, String>) faxCoverDocsOutCome.getData();
            fileContents.add(fileStore.downloadFileFromS3(bucketName, "parDocuments/" + output.get("faxCoverFileName")));
        }

        if (priorAuthGenericDocsOutCome.getOutcome() != null && priorAuthGenericDocsOutCome.getOutcome()) {
            HashMap<String, String> output = (HashMap<String, String>) priorAuthGenericDocsOutCome.getData();
            fileContents.add(fileStore.downloadFileFromS3(bucketName, "parDocuments/" + output.get("dmeAuthForm1fileName")));
            String filePath = "parDocuments";
            String bucketFilePathWithFile = String.format("%s/%s", bucketName, filePath);
            amazonS3Client.deleteObject(bucketFilePathWithFile, output.get("dmeAuthForm1fileName"));
        }

        if (priorAuthMediCareDocsOutCome.getOutcome() != null && priorAuthMediCareDocsOutCome.getOutcome()) {
            HashMap<String, String> output = (HashMap<String, String>) priorAuthMediCareDocsOutCome.getData();
            fileContents.add(fileStore.downloadFileFromS3(bucketName, "parDocuments/" + output.get("dmeAuthMedicareFormfileName")));
            String filePath = "parDocuments";
            String bucketFilePathWithFile = String.format("%s/%s", bucketName, filePath);
            amazonS3Client.deleteObject(bucketFilePathWithFile, output.get("dmeAuthMedicareFormfileName"));
        }

        if (initialOrRenewalDocsOutCome.getOutcome() != null && initialOrRenewalDocsOutCome.getOutcome()) {
            HashMap<String, String> output = (HashMap<String, String>) initialOrRenewalDocsOutCome.getData();
            fileContents.add(fileStore.downloadFileFromS3(bucketName, "parDocuments/" + output.get("initialOrRenewalFileName")));
        }

        if (swoDocsOutCome.getOutcome() != null && swoDocsOutCome.getOutcome()) {
            CmnResponseDetails cmnResponseDetails = (CmnResponseDetails) swoDocsOutCome.getData();
            String cmnNumber = cmnResponseDetails.getCmnDTO().getCmnNumber();
            fileContents.add(fileStore.downloadFileFromS3(bucketName, "cmnDocuments/" + cmnNumber + ".pdf"));
        }

        String patientBucketName = "dme-patient-service";

        List<String> pmrDocuments = Arrays.asList(documentNameList.split(","));
        for (String eachPmrDocs : pmrDocuments) {
            String pmrFileWithPath = "patientDocuments/" + eachPmrDocs;
            fileContents.add(fileStore.downloadFileFromS3(patientBucketName, pmrFileWithPath));
        }

        // Merge PDFs
        byte[] mergedPdfContent = mergePdfs(fileContents);

        // Upload the merged file to S3
        String eTag = fileStore.uploadToS3(bucketName, combinedFilePath, mergedPdfContent);
        log.info("ETag of the uploaded object: " + eTag);
        if (eTag != null && !eTag.equals("")) {
            log.info("PDF created successfully.");
            log.info("======Par Req Details Id::=======" + parRequestDetailsDto.getParRequestDetailsId());
            return parRequestDetailsRepo.findById(parRequestDetailsDto.getParRequestDetailsId()).map(existingSOData -> {
                    existingSOData.setParRequestDocLocation(existingSOData.getParRequestDocName());
                    existingSOData.setDocQrCode(qrCodeName);
                    existingSOData.setParRequestDocName(combinedFileName);
                    existingSOData.setUpdatedDate(LocalDate.now());
                    existingSOData.setUpdatedByName("Falguni");
                    existingSOData.setUpdatedById(31L);
                    return existingSOData;
                }).flatMap(parRequestDetailsRepo::save)
                .map(parRequestDetailsMapper::toDto)
                .map(updatedObj -> new ServiceOutcome(updatedObj, true, "Combined Fax Cover Request Document is generated successfully."))
                .switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderMasterDTO>(null, false, "Par Request Details Does Not Exist")));
        } else {
            return Mono.just(new ServiceOutcome<>(null, false, "Error to Generate Combined Document."));
        }
    }

    private byte[] mergePdfs(List<byte[]> pdfContents) throws IOException, DocumentException {
        ByteArrayOutputStream mergedPdfOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, mergedPdfOutputStream);
        document.open();

        for (byte[] pdfContent : pdfContents) {
            PdfReader reader = new PdfReader(pdfContent);
            copy.addDocument(reader);
            reader.close();
        }

        document.close();
        return mergedPdfOutputStream.toByteArray();
    }

    @Override
    public Mono<Integer> getCountOfNoOfPagesInDocs(String documentNameList, String s3bucketName, String filePath) throws IOException {
        List<String> pmrDocuments = new ArrayList<>();
        if (documentNameList.contains(",")) {
            pmrDocuments = Arrays.asList(documentNameList.split(","));
        } else {
            pmrDocuments.add(documentNameList);  //Single Document
        }

        int count = 0;
        String bucketName = String.format("%s/%s", s3bucketName, filePath);
        for (String eachPmrDocs : pmrDocuments) {
            log.info("========eachPmrDocs========" + eachPmrDocs);
            S3Object s3object = amazonS3Client.getObject(bucketName, eachPmrDocs);
            //amazonS3Client.deleteObject(bucketName, eachPmrDocs);
            InputStream targetStream = s3object.getObjectContent();

            PDDocument pdDocument = PDDocument.load(targetStream);
            count = count + pdDocument.getNumberOfPages();
        }
        return Mono.just(count);
    }

    @Override
    public Mono<ServiceOutcome<ParMasterDTO>> getParMasterbyParNo(String parNo) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub

        ServiceOutcome<ParMasterDTO> outCome = new ServiceOutcome<ParMasterDTO>();

        ParMaster parMaster = parMasterRepository.getParMasterOnParNo(parNo).toFuture().get();

        if (parMaster != null) {
            outCome.setData(parMasterMapper.toDto(parMaster));
            outCome.setMessage("Data Retrieved Successfully");
            outCome.setOutcome(true);
        } else {
            outCome.setData(null);
            outCome.setMessage("Data Not Found");
            outCome.setOutcome(false);
        }

        return Mono.just(outCome);
    }

    @Override
    public Mono<ParMaster> saveParMaster(ParMaster parMaster) {
        // TODO Auto-generated method stub
        Mono<ParMaster> parmasterData = parMasterRepository.save(parMaster);

        return parmasterData;
    }

    @Override
    public Mono<ServiceOutcome<ParMasterDTO>> getParMasterbyParIdNo(String parIdNo) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        ServiceOutcome<ParMasterDTO> outCome = new ServiceOutcome<ParMasterDTO>();

        ParMaster parMaster = parMasterRepository.getParMasterOnParIdNo(parIdNo).toFuture().get();

        if (parMaster != null) {
            outCome.setData(parMasterMapper.toDto(parMaster));
            outCome.setMessage("Data Retrieved Successfully");
            outCome.setOutcome(true);
        } else {
            outCome.setData(null);
            outCome.setMessage("Data Not Found");
            outCome.setOutcome(false);
        }

        return Mono.just(outCome);
    }

    @Override
    public Mono<ParSoMap> saveParSoMap(ParSoMap parSoMap) {
        // TODO Auto-generated method stub
        Mono<ParSoMap> parSoMapData = parSOMapRepository.save(parSoMap);

        return parSoMapData;
    }

    @Override
    public Mono<ServiceOutcome> fetchEfaxAutomatic() throws MessagingException {
        String pop3Host = "pop.gmail.com";
        String mailStoreType = "imaps";
        final String userName = "fax.inbox@sunknowledgeinc.com";
        final String password = "fmbn siwi drcn zfkq";//"U1tr@C0mp13x";

        Properties props = new Properties();
        props.put("mail.pop3.host", pop3Host);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");
        props.put("mail.store.protocol", mailStoreType);

        Session session = Session.getInstance(props);

        Store mailStore = session.getStore(mailStoreType);
        mailStore.connect(pop3Host, userName, password);

        Folder folder = mailStore.getFolder("Inbox");
        folder.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        SearchTerm senderFlagTerm = new FromTerm(new InternetAddress("faxes@redfax.com"));
        SearchTerm subjectFlagTerm = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains("New Fax Received")) {
                        return true;
                    }
                    /*if (message.getSubject().contains(keyword)
                        && message.getSentDate().after(aDate)) {
                        return true;
                    }*/
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
        SearchTerm[] searchTerms = {unseenFlagTerm, senderFlagTerm, subjectFlagTerm};
        SearchTerm searchTerm = new AndTerm(searchTerms);
        Message[] emailMessages = folder.search(searchTerm);
        //Message[] emailMessages = folder.getMessages();
        log.info("Total Message - "
            + emailMessages.length);
        Flux<Message> flux = Flux.just(emailMessages);
        return flux.flatMapSequential(message -> {
            try {
                Address[] toAddress =
                    message.getRecipients(Message.RecipientType.TO);
                log.info("Email " + (1) + "-");
                log.info("Subject - " + message.getSubject());
                log.info("From - " + message.getFrom()[0]);
                log.info("ContentType - " + message.getContentType());
                Flags seen2 = new Flags(Flags.Flag.SEEN);
                message.setFlags(seen2, true);
                List<MimeBodyPart> mimeBodyParts = new ArrayList<>();
                //message.getFrom()[0].toString().contains("RedFax Team")
                if (message.getSubject().contains("New Fax Received")) {
                    Multipart multiPart = (Multipart) message.getContent();
                    for (int i = 0; i < multiPart.getCount(); i++) {
                        MimeBodyPart part = null;
                        try {
                            part = (MimeBodyPart) multiPart.getBodyPart(i);
                            log.info("Content Type " + part.getContentType());
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                mimeBodyParts.add(part);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                return decodeQRImageUpdated(mimeBodyParts, message)
                    .map(data -> {
                        log.info("Qrcodes " + data);
                        return data;
                    });
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collectList().map(data -> {
            try {
                folder.close(false);
                mailStore.close();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            return new ServiceOutcome(data, true, "Data Saved Successfully");
        });
    }

    private Mono<ServiceOutcome> decodeQRImageUpdated(List<MimeBodyPart> mimeBodyParts, Message message) throws MessagingException {
        return Flux.fromIterable(mimeBodyParts).flatMapSequential(part -> {
            try {
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    if (part.getContentType().contains("APPLICATION/PDF")) {
                        String fileName = part.getFileName();
                        log.info("FileName " + fileName);
                        InputStream inputStream = part.getInputStream();
                        // Hints for scanning
                        //byte[] bytes = new byte[];
                        byte[] bytes = inputStream.readAllBytes();
                        InputStream targetStream = new ByteArrayInputStream(bytes);
                        log.info("targetStream " + targetStream.available());
                        Vector<BarcodeFormat> decodeFormat = new Vector<BarcodeFormat>();
                        decodeFormat.add(BarcodeFormat.QR_CODE);

                        Hashtable<DecodeHintType, Object> hintMap = new Hashtable<DecodeHintType, Object>();
                        hintMap.put(DecodeHintType.TRY_HARDER, true);
                        hintMap.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormat);

                        MultiFormatReader qrcodeReader = new MultiFormatReader();
                        qrcodeReader.setHints(hintMap);
                        ArrayList<String> qrCodes = new ArrayList<String>();
                        //Path docPath = Paths.get(filePath);

                        qrcodeReader.setHints(hintMap);
                        try {
                            log.info("Available 0 " + targetStream.available());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        PDDocument pdfDoc = PDDocument.load(targetStream);
                        PDFRenderer renderer = new PDFRenderer(pdfDoc);
                        log.info("pdfDoc.getNumberOfPages() " + pdfDoc.getNumberOfPages());
                        for (int pageIndex = 0; pageIndex < pdfDoc.getNumberOfPages(); pageIndex++) {
                            // renderImageWithDPI(page number, image size, format)
                            BufferedImage pageImage = renderer.renderImageWithDPI(pageIndex, 150, ImageType.BINARY); // entire page info
                            // BufferedImage image = renderer.renderImage(0);
                            LuminanceSource source = new BufferedImageLuminanceSource(pageImage);
                            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                            // By using decodeWithState, we keep the Hints that we set earlier.
                            Result result = null;
                            try {
                                result = qrcodeReader.decodeWithState(bitmap);
                            } catch (NotFoundException e) {
                            }
                            if (result != null) {
                                //log.info("result.getText() "+ result.getText());
                                qrCodes.add(result.getText());
                                if (result.getText().toLowerCase().contains("par") || result.getText().toLowerCase().contains("por")) {
                                    break;
                                }
                            }
                        }
                        log.info("qrCodes " + qrCodes);
                        pdfDoc.close();

                        EfaxResponseDTO efaxResponseDTO = new EfaxResponseDTO();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                        LocalDate responseReceivedDate = LocalDate.parse(message.getReceivedDate().toString(), formatter);

                        efaxResponseDTO.setEfaxReceivedDate(responseReceivedDate);
                        efaxResponseDTO.setSenderEmail(message.getFrom()[0].toString());
                        efaxResponseDTO.setEmailSubjectLine(message.getSubject());
                        efaxResponseDTO.setAttachmentName(fileName);

                        efaxResponseDTO.setStatus("active");
                        efaxResponseDTO.setCreatedById(1l);
                        efaxResponseDTO.setCreatedByName("Abhay");
                        efaxResponseDTO.setCreatedDate(LocalDate.now());
                        efaxResponseDTO.setEfaxResponseUuid(UUID.randomUUID());
                        final String faxNo = Pattern.compile(" ")
                            .splitAsStream(message.getSubject())
                            .reduce((first, second) -> second)
                            .orElse(null);
                        efaxResponseDTO.setEfaxNumber(faxNo);
                        return amazonS3Service.uploadInputStreamDocsS3Bucket(fileName, "", new ByteArrayInputStream(bytes),
                                "unIdentifiedEfaxDocuments", BucketName.BUCKET_NAME.getSoServiceBucket())
                            .flatMap(uploadedData -> {
                                boolean isPar = qrCodes.stream().anyMatch(x -> x.toLowerCase().contains("par"));
                                boolean isCMN = qrCodes.stream().anyMatch(x -> x.toLowerCase().contains("cmn"));
                                boolean isPO_ack = qrCodes.stream().anyMatch(x -> x.toLowerCase().contains("por"));
                                boolean isPatient = qrCodes.stream().anyMatch(x -> x.toLowerCase().contains("pmr") || x.toLowerCase().contains("pid") || x.toLowerCase().contains("pdd"));
                                log.info("qrCodes " + qrCodes);
                                if(qrCodes.size() > 0){
                                    if (qrCodes.get(0).toLowerCase().contains("por")) efaxResponseDTO.setIsPoAck(true);
                                    else if (qrCodes.get(0).toLowerCase().contains("par")) efaxResponseDTO.setIsPar(true);
                                    else if (qrCodes.get(0).toLowerCase().contains("cmn")) efaxResponseDTO.setIsCmn(true);
                                    else if ((qrCodes.get(0).toLowerCase().contains("pmr") ||
                                        qrCodes.get(0).toLowerCase().contains("pid") ||
                                        qrCodes.get(0).toLowerCase().contains("pdd"))) efaxResponseDTO.setIsPatientRecord(true);
                                }
                                Arrays.sort(qrCodes.toArray(), customComparator());
                                log.info("qrCodes " + qrCodes);
                                return efaxResponseRepositoryExtended.save(efaxResponseMapper.toEntity(efaxResponseDTO))
                                    .flatMap(efaxSavedData -> {
                                        if (qrCodes.size() > 0) {
                                            efaxSavedData.setIsQrDecoded(true);
                                            log.info("isPar : " + isPar + " isCMN : " + isCMN + "isPatient : " + isPatient + " isPO_ack : " + isPO_ack);
                                            for (String code : qrCodes) {
                                                log.info("code " + code);
                                                if (code.toLowerCase().contains("por")) {
                                                    try {
                                                        return updatePurchaseOrderInfoWithResponse(code, responseReceivedDate, fileName)
                                                            .map(savedPurchaseOrderData -> {
                                                                if (savedPurchaseOrderData.getOutcome()) {
                                                                    LinkedHashMap patientDocumentData = (LinkedHashMap) savedPurchaseOrderData.getData();
                                                                    efaxSavedData.setIsPoAck(true);
                                                                    efaxSavedData.setQrValue(code);
                                                                    efaxSavedData.setSoId(patientDocumentData.get("salesOrderId") != null ? Long.valueOf(patientDocumentData.get("salesOrderId").toString()) : null);
                                                                    efaxSavedData.setSoNo(patientDocumentData.get("salesOrderNo") != null ? (patientDocumentData.get("salesOrderNo").toString()) : null);
                                                                    log.info("savedPurchaseOrderData " + savedPurchaseOrderData);
                                                                    return savedPurchaseOrderData;
                                                                } else {
                                                                    return null;
                                                                }
                                                            }).flatMap(data1 -> {
                                                                log.info("Inside POR Map flatmap " + code);
                                                                return transferUnidentifiedToCorrectLocation(BucketName.BUCKET_NAME.getSoServiceBucket(),
                                                                    "unIdentifiedEfaxDocuments", BucketName.BUCKET_NAME.getItemServiceBucket(),
                                                                    "purchaseOrderAck",fileName)
                                                                    .flatMap(data2->{
                                                                        log.info("Inside POR Map flatmap flatmap");
                                                                        return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                                            .map(x -> x);
                                                                    });
                                                            });
                                                    } catch (ExecutionException e) {
                                                        throw new RuntimeException(e);
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                                else if (code.toLowerCase().contains("par")) {
                                                    log.info("Inside Par code " + code);
                                                    String parId = code.split("_")[1];
                                                    String parRequestId = code.split("_")[2];
                                                    log.info("parId " + parId);
                                                    return parSoMapRepo.getSingleParSOMapOnpar(Long.valueOf(parId),Long.valueOf(parRequestId))
                                                        .map(data -> {
                                                            log.info("Inside Par Map " + code);
                                                            efaxSavedData.setParIdNo(data.getParIdNo());
                                                            efaxSavedData.setQrValue(code);
                                                            efaxSavedData.setIsPar(true);
                                                            efaxSavedData.setPatientFirstName(data.getPatientFirstName());
                                                            efaxSavedData.setPatientLastName(data.getPatientLastName());
                                                            efaxSavedData.setPatientIdNo(data.getPatientIdNumber());
                                                            efaxSavedData.setSoId(data.getSoId());
                                                            efaxSavedData.setSoNo(data.getSoNo());
                                                            return efaxSavedData;
                                                        })
                                                        .flatMap(data1 -> {
                                                            return parRequestDetailsRepo.getParRequestDetailsById(Long.valueOf(parRequestId))
                                                                .flatMap(parRequestDetails -> {
                                                                    log.info("Inside Par Map flatmap " + code);
                                                                    log.info("Inside Par Map flatmap " + bytes.length);
                                                                    parRequestDetails.setParRequestFaxNumber(faxNo);
                                                                    parRequestDetails.setFaxResponseDate(responseReceivedDate);
                                                                    parRequestDetails.setParRequestResponseDocName(fileName);
                                                                    parRequestDetails.setUpdatedDate(LocalDate.now());
                                                                    parRequestDetails.setUpdatedById(6165l);
                                                                    parRequestDetails.setUpdatedByName("Abhay Api");
                                                                    return parRequestDetailsRepo.save(parRequestDetails)
                                                                        .flatMap(updatedParRequestData -> {
                                                                            return transferUnidentifiedToCorrectLocation(BucketName.BUCKET_NAME.getSoServiceBucket(),
                                                                                "unIdentifiedEfaxDocuments",
                                                                                BucketName.BUCKET_NAME.getSoServiceBucket(),"parDocuments",fileName)
                                                                                .flatMap(data2->{
                                                                                    log.info("Inside Par Map flatmap flatmap " + code);
                                                                                    return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                                                        .map(x->efaxResponseMapper.toDto(x));
                                                                                });
                                                                        });
                                                                });
                                                        });
                                                }
                                                else if (code.toLowerCase().contains("cmn")) {
                                                    log.info("Inside CMN code " + code);
                                                    return cmnRepository.getCMNSOIdandPatientData(code)
                                                        .map(data -> {
                                                            efaxSavedData.setIsCmn(true);
                                                            efaxSavedData.setCmnIdNo(code);
                                                            efaxSavedData.setQrValue(code);
                                                            efaxSavedData.setSoId(data.getSalesOrderId());
                                                            efaxSavedData.setSoNo(data.getSalesOrderNo());
                                                            efaxSavedData.setPatientIdNo(data.getPatientIdNo());
                                                            efaxSavedData.setPatientFirstName(data.getPatientFirstName());
                                                            efaxSavedData.setPatientLastName(data.getPatientLastName());
                                                            return data;
                                                        }).flatMap(data1 -> {
                                                            return cmnDocumentMasterRepository.getCmnDocumentDetailsOnCmnNumber(code)
                                                                .flatMap(cmnDocData -> {
                                                                    cmnDocData.setFaxStatus("Updated"); // need to ask
                                                                    cmnDocData.setReturnDocumentName(fileName);
                                                                    cmnDocData.setInBoundFaxStatus("Response Got");
                                                                    cmnDocData.setInBoundFaxNo(faxNo);
                                                                    cmnDocData.setInBoundFaxDateTime(responseReceivedDate);
                                                                    cmnDocData.setUpdatedDate(LocalDate.now());
                                                                    cmnDocData.setUpdatedById(6165l);
                                                                    cmnDocData.setUpdatedByName("Abhay Api");
                                                                    return cmnDocumentMasterRepository.save(cmnDocData)
                                                                        .flatMap(updatedCmnDocument -> {
                                                                            log.info("Inside CMN Map flatmap " + code);
                                                                            return transferUnidentifiedToCorrectLocation(BucketName.BUCKET_NAME.getSoServiceBucket(),
                                                                                "unIdentifiedEfaxDocuments",
                                                                                BucketName.BUCKET_NAME.getSoServiceBucket(),"cmnDocuments",fileName)
                                                                                .flatMap(data2->{
                                                                                    log.info("Inside CMN Map flatmap flatmap");
                                                                                    return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                                                        .map(x -> x);
                                                                                });
                                                                        });
                                                                });
                                                        });
                                                }
                                                else if ((code.toLowerCase().contains("pmr") ||
                                                    code.toLowerCase().contains("pid") ||
                                                    code.toLowerCase().contains("pdd"))) {
                                                    efaxSavedData.setIsPatientRecord(true);
                                                    log.info("efaxSavedData " + efaxSavedData);
                                                    return transferUnidentifiedToCorrectLocation(BucketName.BUCKET_NAME.getSoServiceBucket(),"unIdentifiedEfaxDocuments",
                                                        BucketName.BUCKET_NAME.getPatientServiceBucket(),"patientDocuments",fileName)
                                                        .flatMap(data2->{
                                                            log.info("Patient Records");
                                                            efaxSavedData.setIsQrDecoded(true);
                                                            return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                                .map(x -> x);
                                                        });
                                                }
                                                else {
                                                    return parRequestDetailsServiceExtended.getParRequestDetailsByFaxNo(faxNo)
                                                        .flatMap(parOrcmn -> {
                                                            if (parOrcmn.equalsIgnoreCase("par")) {
                                                                efaxSavedData.setIsPar(true);
                                                            } else if (parOrcmn.equalsIgnoreCase("cmn")) {
                                                                efaxSavedData.setIsCmn(true);
                                                            }
                                                            log.info("efaxSavedData " + efaxSavedData);
                                                    /*return amazonS3Service.uploadInputStreamDocsS3Bucket(fileName, "", new ByteArrayInputStream(bytes),
                                                            "unIdentifiedEfaxDocuments", BucketName.BUCKET_NAME.getSoServiceBucket())
                                                        .flatMap(data2 -> {
                                                            log.info("Qrcode not Identified");*/
                                                            efaxSavedData.setIsQrDecoded(false);
                                                            return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                                .map(x -> x);
                                                            //});
                                                        });//.switchIfEmpty(saveAndUploadUnidentifiedPdf(fileName, new ByteArrayInputStream(bytes), efaxResponseDTO));
                                                }
                                            }
                                            return Mono.just(new ServiceOutcome(null, true, "Data Saved Successfully"));
                                        }
                                        else {
                                            log.info("Qrcode not Identified else part");
                                            return parRequestDetailsServiceExtended.getParRequestDetailsByFaxNo(faxNo)
                                                .flatMap(parOrcmn -> {
                                                    log.info("parOrcmn " + parOrcmn);
                                                    if (parOrcmn.equalsIgnoreCase("par")) {
                                                        efaxSavedData.setIsPar(true);
                                                    } else if (parOrcmn.equalsIgnoreCase("cmn")) {
                                                        efaxSavedData.setIsCmn(true);
                                                    }
                                                    log.info("efaxSavedData " + efaxSavedData);
                                           /* return amazonS3Service.uploadInputStreamDocsS3Bucket(fileName, "", new ByteArrayInputStream(bytes), "unIdentifiedEfaxDocuments", BucketName.BUCKET_NAME.getSoServiceBucket())
                                                .flatMap(data2 -> {
                                                    log.info("Qrcode not Identified");*/
                                                    efaxSavedData.setIsQrDecoded(false);
                                                    return efaxResponseRepositoryExtended.save(efaxSavedData)
                                                        .map(x -> x);
                                                    //});
                                                });//.switchIfEmpty(saveAndUploadUnidentifiedPdf(fileName, new ByteArrayInputStream(bytes), efaxResponseDTO));
                                        }
                                    });

                            });
                    } else {
                        log.info("File is Not APPLICATION/PDF");
                        //return Mono.just(new ServiceOutcome(null,false,"File is Not APPLICATION/PDF"));
                        //return Mono.just(new ServiceOutcome());
                    }
                } else {
                    log.info("File is Not part.getDisposition()");
                    //log.info("FileName Not Found");
                    //return Mono.just(new ServiceOutcome(null,false,"File is Not part.getDisposition()"));
                }
            } catch (MessagingException | IOException e) {
                return Mono.empty();
            }
            return Mono.empty();
        }).collectList().flatMap(data -> {
            log.info("data " + data);
            return Mono.just(new ServiceOutcome(data, data.size() > 0 ? true : false, data.size() > 0 ? "" : "Data not saved."));
        });
    }

    private Mono<ServiceOutcome> updatePurchaseOrderInfoWithResponse(String code, LocalDate responseReceivedDate, String fileName) throws ExecutionException, InterruptedException {
        CommonUtilities commonUtilitiesObj = new CommonUtilities();
        Properties propData = null;
        try {
            propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        log.info("============><=============" + token);
        String url = propData.getProperty("purchase_order_Update_url");

        String finalUrl = url;
        log.info("====final URL===GET=============>" + finalUrl);

        return microserviceHealthCheck.microserviceHealthCheck("items")
            .flatMap(isUp -> {
                if (isUp) {
                    return webClient.post()
                        .uri(uriBuilder -> uriBuilder.path(finalUrl)
                            .queryParam("poNumber", code)
                            .queryParam("ackDocumentName", fileName)
                            .queryParam("ackReceivedDate", responseReceivedDate)
                            .build())
                        .header("Authorization", "Bearer " + token)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<>() {
                        });
                } else {
                    return Mono.just(new ServiceOutcome(null, false, "Items Microservice is Down."));
                }
            });
    }

    private Mono<EfaxResponseDTO> saveAndUploadUnidentifiedPdf(String fileName, ByteArrayInputStream byteArrayInputStream, EfaxResponseDTO efaxResponseDTO) {
        log.info("Qrcode not Identified fileName 1 ---> " + fileName);
        return amazonS3Service.uploadInputStreamDocsS3Bucket(fileName, "", byteArrayInputStream, "unIdentifiedEfaxDocuments", BucketName.BUCKET_NAME.getSoServiceBucket())
            .flatMap(data2 -> {
                log.info("Qrcode not Identified fileName 2 ---> " + fileName);
                efaxResponseDTO.setIsQrDecoded(false);
//                return efaxResponseRepositoryExtended.save(efaxResponseMapper.toEntity(efaxResponseDTO));
                return efaxResponseRepositoryExtended.save(efaxResponseMapper.toEntity(efaxResponseDTO)).map(x -> efaxResponseMapper.toDto(x));
            });
    }

    private Mono<Boolean> transferUnidentifiedToCorrectLocation(String transferFromBucket,
                                                                String transferFromFolder,
                                                                String transferToBucket,
                                                                String transferToFolder,
                                                                String fileName) {
        log.info("Qrcode not Identified");
        String fromPath = String.format("%s/%s", transferFromBucket, transferFromFolder);
        S3Object s3object = amazonS3Client.getObject(fromPath, fileName);
        InputStream inputStream = s3object.getObjectContent();
        return amazonS3Service.uploadInputStreamDocsS3Bucket(fileName, "", inputStream, transferToFolder, transferToBucket)
            .flatMap(e -> {
                log.info("Inside Upload " + e);
                return deleteFile(transferFromBucket, transferFromFolder, fileName)
                    .flatMap(data -> {
                        if (data.size() > 0) {
                            return Mono.just(true);
                        } else {
                            return Mono.just(false);
                        }
                    });

// for single delete with void return
// amazonS3Client.deleteObject(transferFromBucket, fileName);
// Consumer methodRef3 = StackOverflowExample::inIntegerOutVoid;
// methodRef3.accept(45);

            })
            .switchIfEmpty(Mono.just(false));
    }

    public Mono<List> deleteFile(String transferFromBucket, String transferFromFolder, String fileName) {
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(transferFromBucket);
        DeleteObjectsRequest.KeyVersion keyVersion = new DeleteObjectsRequest.KeyVersion(transferFromFolder + "/" + fileName);
        deleteObjectsRequest.setKeys(List.of(keyVersion));
        List list = amazonS3Client.deleteObjects(deleteObjectsRequest)
            .getDeletedObjects();
        log.info("Deleted Objects " + list);
        return Mono.justOrEmpty(list);
    }

    @Override
    public Mono<ServiceOutcome> getAllUnIdentifiedFaxes() {
        return efaxResponseRepositoryExtended.getAllUnIdentifiedFaxes()
            .collectList().map(eFaxData -> {
                return new ServiceOutcome<>(eFaxData, true, "");
            });
        //collectList().map(data->new ServiceOutcome(data,data.size()>0? true:false,data.size()>0?"":"Data Not Found."));
    }

    @Override
    public Mono<ServiceOutcome> updateEfaxResponse(EfaxResponseDTOExtended efaxResponseDTOExtended) {
        CommonUtilities.dtoTrimmer(efaxResponseDTOExtended);
        return efaxResponseRepositoryExtended.findById(efaxResponseDTOExtended.getEfaxResponseId())
            .map(efaxResponse -> {
                BeanUtils.copyProperties(efaxResponseDTOExtended, efaxResponse);
                efaxResponse.setStatus("active");
                if (efaxResponse.getEfaxResponseId() == null) {
                    efaxResponse.setEfaxResponseId(null);
                    efaxResponse.setEfaxResponseUuid(UUID.randomUUID());
                    efaxResponse.setCreatedDate(LocalDate.now());
                    efaxResponse.setCreatedById(1L);
                    efaxResponse.setCreatedByName("Abhijit");

                } else {
                    efaxResponse.setUpdatedDate(LocalDate.now());
                    efaxResponse.setUpdatedById(1L);
                    efaxResponse.setUpdatedByName("Abhijit");
                }
                return efaxResponse;
            }).flatMap(efaxResponseRepositoryExtended::save)
            .map(data -> new ServiceOutcome<EfaxResponse>(data, true, "Data Successfully Saved."));
    }

    @Override
    public Mono<ServiceOutcome> getFileAsByteCode(String name) throws IOException {
        return amazonS3Service.getFileAsByteCode(name)
            .map(byteArray -> {
                JSONObject jsonObject = new JSONObject();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                    gzipOutputStream.write(byteArray);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                log.info("compress " + byteArrayOutputStream.toByteArray().length);
                log.info("Not " + byteArray.length);
                jsonObject.put("fileName", name);
                jsonObject.put("contentType", "application/pdf");
                byte[] strenc = Base64.getEncoder().encode(byteArray);
                try {
                    jsonObject.put("fileContent", new String(strenc, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                return jsonObject;
            }).map(data -> new ServiceOutcome(data, true, ""))
            .switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "File Not Found.")));
    }

    @Override
    public Mono<ServiceOutcome> viewPARDetailsByPatientId(UUID patientUUID) {
        return efaxResponseRepositoryExtended.getPatientIdOnUUID(patientUUID)
            .flatMap(patientId -> {
                System.out.println("patientId "+patientId);
                return efaxResponseRepositoryExtended.viewPARDetailsByPatientId(patientId)
                    .collectList()
                    .map(data -> new ServiceOutcome<>(data, true, ""));
            });
    }

    @Override
    public Mono<ServiceOutcome> viewCMNDetailsByPatientId(UUID patientUUID) {
        return efaxResponseRepositoryExtended.getPatientIdOnUUID(patientUUID)
            .flatMap(patientId -> {
                return efaxResponseRepositoryExtended.viewCMNDetailsByPatientId(patientId)
                    .collectList()
                    .map(data -> new ServiceOutcome<>(data, true, ""));
            });
    }

    @Override
    public Mono<ServiceOutcome> viewPatientDetailsByPatientId(UUID patientUUID) {
        return efaxResponseRepositoryExtended.getPatientIdOnUUID(patientUUID)
            .flatMap(patientId -> {
                return efaxResponseRepositoryExtended.viewPatientDetailsByPatientId(patientId)
                    .collectList()
                    .map(data -> new ServiceOutcome<>(data, true, ""));
            });
    }

    @Override
    public Mono<ServiceOutcome> tagCMNEfax(UUID eFaxResponseUUID, UUID cmnUUID, UUID cmnDocumentUUID) {
        return cmnRepository.getCMNDetailsOnUUID(cmnUUID)
            .flatMap(cmnData -> {
                return efaxResponseRepositoryExtended.getEfaxDetailsOnUUID(eFaxResponseUUID)
                    .flatMap(efaxData -> {
                        return cmnDocumentMasterRepository.getCmnDocumentOnUUID(cmnDocumentUUID)
                            .flatMap(cmnDocumentMaster -> {
                                //cmnDocumentMaster.setCmnId(cmnData.getCmnId());
                                //cmnDocumentMaster.setCmnNo(cmnData.getCmnNumber());
                                cmnDocumentMaster.setGenerationDate(LocalDate.now());
                                cmnDocumentMaster.setInitialDocumentName(efaxData.getAttachmentName());
                                cmnDocumentMaster.setFaxStatus("Updated"); // need to ask
                                cmnDocumentMaster.setInBoundFaxNo(efaxData.getEfaxNumber());
                                cmnDocumentMaster.setInBoundFaxDateTime(efaxData.getEfaxReceivedDate());
                                cmnDocumentMaster.setInBoundFaxStatus("Response Got");
                                cmnDocumentMaster.setReturnDocumentName(efaxData.getAttachmentName());

                                cmnDocumentMaster.setUpdatedById(6165l);
                                cmnDocumentMaster.setUpdatedByName("Abhay");
                                cmnDocumentMaster.setUpdatedDate(LocalDate.now());
                                //cmnDocumentMaster.setCmnDocumentMasterUuid(UUID.randomUUID());
                                //cmnDocumentMaster.setStatus("active");
                                return cmnDocumentMasterRepository.save(cmnDocumentMaster)
                                    .flatMap(cmnDocData -> {
                                        efaxData.setIsCmn(true);
                                        efaxData.setCmnIdNo(cmnData.getCmnNumber());

                                        efaxData.setIsPar(false);
                                        efaxData.setIsPoAck(false);
                                        efaxData.setIsPatientRecord(false);
                                        efaxData.setIsQrDecoded(false);
                                        //efaxData.setPatientIdNo(cmnData.getPatientId());
                                        efaxData.setIsManuallyTagged(true);
                                        efaxData.setSoId(cmnData.getSalesOrderId());
                                        efaxData.setSoNo(cmnData.getSalesOrderNo());

                                        efaxData.setUpdatedDate(LocalDate.now());
                                        efaxData.setUpdatedById(6165L);
                                        efaxData.setUpdatedByName("Abhay");
                                        return efaxResponseRepositoryExtended.save(efaxData)
                                            .flatMap(data -> {
                                                return transferUnidentifiedToCorrectLocation("dme-salesorder-service", "unIdentifiedEfaxDocuments",
                                                    "dme-salesorder-service", "cmnDocuments", efaxData.getAttachmentName())
                                                    .flatMap(isTransfered -> {
                                                        log.info("isTransfered " + isTransfered);
                                                        log.info("Updated Efax Data " + data);
                                                        if (isTransfered) {
                                                            return Mono.just(new ServiceOutcome(data, true, ""));
                                                        } else {
                                                            return Mono.just(new ServiceOutcome(data, false, "File not transfered."));
                                                        }

                                                    });
                                            });
                                    });
                            });
                    });
            });
    }

    @Override
    public Mono<ServiceOutcome> tagPAREfax(UUID eFaxResponseUUID, UUID parUUID, UUID parRequestUUID) {
        log.info("Inside1");
        return parMasterRepository.getParMasterOnParUUID(parUUID)
            .flatMap(parData -> {
                log.info("Inside2");
                return efaxResponseRepositoryExtended.getEfaxDetailsOnUUID(eFaxResponseUUID)
                    .flatMap(efaxData -> {
                        log.info("Inside3");
                        return parRequestDetailsRepo.getParRequestDetailsByUUID(parRequestUUID)
                            .flatMap(parRequestDetails -> {
                                log.info("Inside4");
                                //parRequestDetails.setParId(parData.getParId());
                                //parRequestDetails.setParNo(parData.getParNo());
                                parRequestDetails.setParInitiationDate(parData.getInitialDate());
                                parRequestDetails.setParNoEffetiveStartDate(parData.getEffectiveStartDate());

                                parRequestDetails.setParRequestFaxNumber(efaxData.getEfaxNumber());
                                //parRequestDetails.setParRequestFaxStatus("");
                                parRequestDetails.setParRequestFaxDatetime(ZonedDateTime.now());
                                parRequestDetails.setFaxResponseStatus("Response Got");
                                parRequestDetails.setFaxResponseDate(efaxData.getEfaxReceivedDate());

                                parRequestDetails.setUpdatedById(6165l);
                                parRequestDetails.setUpdatedByName("Abhay");
                                parRequestDetails.setUpdatedDate(LocalDate.now());
                                parRequestDetails.setStatus("active");
                                return parRequestDetailsRepo.save(parRequestDetails)
                                    .flatMap(parRequestData -> {
                                        log.info("Inside5");
                                        return parSOMapRepository.getParSOMapOnpar(parData.getParId())
                                            .collectList()
                                            .flatMap(parSoMapData -> {
                                                log.info("Inside6");
                                                efaxData.setIsPar(true);

                                                efaxData.setIsCmn(false);
                                                efaxData.setIsPoAck(false);
                                                efaxData.setIsPatientRecord(false);
                                                efaxData.setIsQrDecoded(false);

                                                efaxData.setPatientIdNo(parData.getPatientIdNumber());
                                                efaxData.setPatientFirstName(parData.getPatientFirstName());
                                                efaxData.setPatientLastName(parData.getPatientLastName());
                                                efaxData.setIsManuallyTagged(true);
                                                efaxData.setSoId(parSoMapData.get(0).getSoId());
                                                efaxData.setSoNo(parSoMapData.get(0).getSoNo());

                                                efaxData.setUpdatedDate(LocalDate.now());
                                                efaxData.setUpdatedById(6165L);
                                                efaxData.setUpdatedByName("Abhay");
                                                return efaxResponseRepositoryExtended.save(efaxData)
                                                    .flatMap(data -> {
                                                        log.info("Inside7");
                                                        return transferUnidentifiedToCorrectLocation("dme-salesorder-service", "unIdentifiedEfaxDocuments",
                                                            "dme-salesorder-service", "parDocuments", efaxData.getAttachmentName())
                                                            .flatMap(isTransfered -> {
                                                                log.info("isTransfered " + isTransfered);
                                                                log.info("Updated Efax Data " + data);
                                                                if (isTransfered) {
                                                                    return Mono.just(new ServiceOutcome(data, true, ""));
                                                                } else {
                                                                    return Mono.just(new ServiceOutcome(data, false, "File not transfered."));
                                                                }
                                                            });
                                                    }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
                                            }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
                                    }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
                            }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
                    }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
            }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "")));
    }

    @Override
    public Mono<ServiceOutcome> tagPatientEfax(UUID eFaxResponseUUID, UUID soUUID, String documentType, UUID patientUUID) throws ExecutionException, InterruptedException {
        return efaxResponseRepositoryExtended.getEfaxDetailsOnUUID(eFaxResponseUUID)
            .flatMap(efaxData -> {
                return salesOrderMasterRepository.getSalesOrderDetailsOnUUID(soUUID)
                    .flatMap(soData -> {
                        try {
                            String[] documentName = efaxData.getAttachmentName().trim().split(".");
                            String documentName1 = efaxData.getAttachmentName().replace(".pdf", "");
                            log.info("documentName " + documentName);
                            log.info("documentName1 " + documentName1);
                            return savePatientDocumentMasterInPatient(soData, "Test Description", documentName1,
                                documentType, "Test Fax", patientUUID)
                                .flatMap(savedPatientDocumentData -> {
                                    if (savedPatientDocumentData.getOutcome()) {
                                        //LinkedHashMap patientDocumentData = (LinkedHashMap) savedPatientDocumentData.getData();
                                        //PatientDocumentCopyDTO patientDocumentData = data;
                                        //if (patientDocumentData != null) {
                                        efaxData.setIsPatientRecord(true);

                                        efaxData.setIsPar(false);
                                        efaxData.setIsCmn(false);
                                        efaxData.setIsPoAck(false);

                                        efaxData.setIsQrDecoded(false);
                                        efaxData.setPatientIdNo(soData.getPatientIdNo());
                                        efaxData.setPatientFirstName(soData.getPatientFirstName());
                                        efaxData.setPatientLastName(soData.getPatientLastName());
                                        efaxData.setIsManuallyTagged(true);
                                        efaxData.setSoId(soData.getSalesOrderId());
                                        efaxData.setSoNo(soData.getSalesOrderNo());
                                        efaxData.setDocumentRenameTo(savedPatientDocumentData.getData().getPatientDocumentNo()+".pdf");

                                        efaxData.setUpdatedDate(LocalDate.now());
                                        efaxData.setUpdatedById(6165L);
                                        efaxData.setUpdatedByName("Abhay");
                                        return efaxResponseRepositoryExtended.save(efaxData)
                                            .map(data -> {
                                                log.info("Updated Efax Data " + data);
                                                return new ServiceOutcome(data, true, "");
                                            })
                                            .switchIfEmpty(Mono.just(new ServiceOutcome()));
                                        /*} else {
                                            return Mono.just(new ServiceOutcome(null,false,"Data not saved in Patient Document Master."));
                                        }*/
                                    } else {
                                        return Mono.just(new ServiceOutcome(null, false, "Data not saved in Patient Document Master."));
                                    }
                                });
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
            });
    }

    private Mono<ServiceOutcome<PatientDocumentSoMap>> savePatientDocumentMasterInPatient(SalesOrderMaster salesOrderMaster, String description, String documentName,
                                                                    String documentType, String patientDocumentStatus, UUID patientUUID) throws ExecutionException, InterruptedException {
        return patientDocumentSoMapServiceExtended.uploadPatientDocumentFromSOAndSaveInMap(documentName, patientUUID, patientDocumentStatus,
                description, documentType, true, "unIdentifiedFile",
                salesOrderMaster.getSalesOrderId(), salesOrderMaster.getSalesOrderNo(), "unIdentifiedEfaxDocuments")
            .collectList()
            .map(data -> {
                log.info("savePatientDocumentMasterInPatient " + data);
                return new ServiceOutcome<PatientDocumentSoMap>(data.size() > 0 ? data.get(0) : null, data.size() > 0 ? true : false,
                    data.size() > 0 ? "" : "Error while uploading and saving Patient Document.", "200");
            });
    }
}
