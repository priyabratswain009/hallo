package com.sunknowledge.dme.rcm.service.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.applicationstatus.DefineStatus;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.pojo.invoice.*;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCombinedSearchParameterDTO;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.repository.invoice.InvoiceLineItemDetailsRepo;
import com.sunknowledge.dme.rcm.repository.invoice.InvoiceMasterDetailsRepo;
import com.sunknowledge.dme.rcm.repository.invoice.InvoicePostingDetailsRepo;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.*;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.DepositMasterDetailsMapper;
import com.sunknowledge.dme.rcm.service.mapper.InvoicePostingDetailsMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvoicePostingServiceImpl implements InvoicePostingService {
    @Autowired
    private ClaimSubmissionStatusRepo claimSubmissionStatusRepository;
    @Autowired
    private InvoiceMasterDetailsRepo invoiceMasterDetailsRepository;
    @Autowired
    private InvoiceLineItemDetailsRepo invoiceLineItemDetailsRepository;
    @Autowired
    private InvoicePostingDetailsRepo invoicePostingDetailsRepository;
    @Autowired
    private ReceiptMasterDetailsRepo receiptMasterDetailsRepository;
    @Autowired
    private ClaimsCOB835DetailsRepo claimsCOB835DetailsRepository;
    @Autowired
    private ClaimsCOB835MasterRepo claimsCOB835MasterRepository;
    @Autowired
    private DepositMasterDetailsRepo depositMasterDetailsRepository;
    @Autowired
    private InvoicePostingDetailsMapper invoicePostingDetailsMapper;
    @Autowired
    private DepositMasterDetailsMapper depositMasterDetailsMapper;

    private final DecimalFormat df = new DecimalFormat("0.00");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.getDefault());

    private final WebClient webClient;

    @Autowired
    public InvoicePostingServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/services").build();
    }

    @Override
    @Transactional
    public void processInvoicePosting(){
        System.out.println("=====================processInvoicePosting========================");
        ModelMapper mapper = new ModelMapper();
        List<ClaimsCOB835Details> claimsCOB835Details = claimsCOB835DetailsRepository.getAllActiveClaimCOB835DetailsWithNotPosted(DefineStatus.Active.name());
        AtomicReference<String> invoicePrefix = new AtomicReference<>("");
        if(claimsCOB835Details.size() > 0){
            claimsCOB835Details.stream().forEach(claimsCOB835Detail -> {
                System.out.println("===###########===claimsCOB835Details Not NULL===>>>>>>>" + claimsCOB835Detail.getAllowedAmount());
                Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(claimsCOB835Detail.getClaimCob835MasterId());
                if(claimsCOB835Master.isPresent()) {
                    System.out.println("===###########===claimsCOB835Master.get().getPatientControlNumber()===>>>>>>>" + claimsCOB835Master.get().getPatientControlNumber());
                    ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository
                        .getClaimSubmissionStatusByInternalClaimControlNumber(claimsCOB835Master.get().getPatientControlNumber());
                    if(claimSubmissionStatus != null){
                        System.out.println("===###########===claimSubmissionStatus.getIntClaimNo()===>>>>>>>" + claimSubmissionStatus.getIntClaimNo());
                        List<PostingAutoAdjustmentSettingsProjection> postingAutoAdjustmentSettings = invoicePostingDetailsRepository.getPostingAutoAdjustmentSettingsByBranch(claimSubmissionStatus.getBranchId())
                            .stream().map(autoAdjustment -> mapper.map(autoAdjustment, PostingAutoAdjustmentSettingsProjection.class)).collect(Collectors.toList());
                        System.out.println("===###########===postingAutoAdjustmentSettings===>>>>>>>" + postingAutoAdjustmentSettings.get(0).getAdjustmentAmount());
                        double thresholdValue = postingAutoAdjustmentSettings.get(0).getAdjustmentAmount();

                        String prefixInternalClaimNo = claimSubmissionStatus.getIntClaimNo().substring(0, 3);
                        System.out.println("===###########===prefixInternalClaimNo===>>>>>>>" + prefixInternalClaimNo);
                        if (prefixInternalClaimNo.equalsIgnoreCase("CLP") || prefixInternalClaimNo.equalsIgnoreCase("CLR")) {//START PRIMARY PAYMENT
                            System.out.println("=##########################################START PRIMARY PAYMENT#############################################");
                            String prefix = "";
                            List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.getInvoiceMasterDetailsOnSalesOrder(claimSubmissionStatus.getSalesOrderId());
                            if(invoiceMasterDetailsList != null){
                                String nextLevel = lookForNextLevel(invoiceMasterDetailsList, "Secondary");
                                System.out.println("===========>"+nextLevel);
                                if(nextLevel.equalsIgnoreCase("Nothing"))
                                    prefix = "IVM";
                                else
                                    prefix = "IVS";
                            }
                            invoicePrefix.set("IVP");
                            System.out.println("===###########===claimSubmissionStatus.getPayorLevel===>>>>>>>" + claimSubmissionStatus.getPayorLevel());
                            System.out.println("===###########===claimSubmissionStatus.getSalesOrderId()===>>>>>>>" + claimSubmissionStatus.getSalesOrderId());
                            System.out.println("===###########===transaction.getItemProcCode()===>>>>>>>" + claimsCOB835Detail.getAdjudicatedProcedureCode());

                            List<Double> prAmountList = Arrays.asList(claimsCOB835Detail.getAdjustmentPrCode1Amount(), claimsCOB835Detail.getAdjustmentPrCode2Amount(),
                                claimsCOB835Detail.getAdjustmentPrCode3Amount(), claimsCOB835Detail.getAdjustmentPrCode4Amount());
//                            HashMap<String, Double> prMapList = new HashMap<>();
//                            prMapList.put(claimsCOB835Detail.getAdjustmentPrCode1(), claimsCOB835Detail.getAdjustmentPrCode1Amount());
//                            prMapList.put(claimsCOB835Detail.getAdjustmentPrCode2(), claimsCOB835Detail.getAdjustmentPrCode2Amount());
//                            prMapList.put(claimsCOB835Detail.getAdjustmentPrCode3(), claimsCOB835Detail.getAdjustmentPrCode3Amount());
//                            prMapList.put(claimsCOB835Detail.getAdjustmentPrCode4(), claimsCOB835Detail.getAdjustmentPrCode4Amount());
//                            for(Map.Entry entry : prMapList.entrySet()){
//
//                            }

                            AtomicReference<Double> totalPRAmount = new AtomicReference<>(0.00);
                            AtomicInteger prCount = new AtomicInteger();
                            prAmountList.stream().forEach(pr -> {
                                if(pr != null && pr > 0){
                                    prCount.getAndIncrement();
                                    totalPRAmount.set(totalPRAmount.get() + pr);
                                }
                            });
                            System.out.println("===###########===totalPRAmount===>>>>>>>" + totalPRAmount+"===>prCount===>"+prCount);

                            List<Double> coAmountList = Arrays.asList(claimsCOB835Detail.getAdjustmentCoCode1Amount(), claimsCOB835Detail.getAdjustmentCoCode2Amount(),
                                claimsCOB835Detail.getAdjustmentCoCode3Amount(), claimsCOB835Detail.getAdjustmentCoCode4Amount());
                            AtomicReference<Double> totalCOAmount = new AtomicReference<>(0.00);
                            AtomicInteger coCount = new AtomicInteger();
                            coAmountList.stream().forEach(co -> {
                                if(co != null && co > 0){
                                    coCount.getAndIncrement();
                                    totalCOAmount.set(totalCOAmount.get() + co);
                                }
                            });
                            System.out.println("===###########===totalCOAmount===>>>>>>>" + totalCOAmount+"===>coCount===>"+coCount);
                            double totalAmount = totalPRAmount.get() + totalCOAmount.get() + claimsCOB835Detail.getProviderPaymentAmount();
                            System.out.println("===###########===totalAmount===>>>>>>>" + totalAmount);

                            InvoiceMasterDetails invoiceMasterDetails = invoiceMasterDetailsRepository
                                .getInvoiceMasterDetailsOnSalesOrderAndInvoiceNo(claimSubmissionStatus.getSalesOrderId(), claimSubmissionStatus.getInvoiceNo());
                            System.out.println("===###########===invoiceMasterDetails.getInvoiceNo()===>>>>>>>" + invoiceMasterDetails.getInvoiceNo());

                            AtomicReference<InvoiceLineItemDetails> invoiceLineItemDetails = new AtomicReference<>();
                            invoiceLineItemDetails.set(invoiceLineItemDetailsRepository
                                .getPrimaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(invoiceMasterDetails.getInvoiceNo(), claimsCOB835Detail.getAdjudicatedProcedureCode()));
                            if(invoiceLineItemDetails.get() != null){
                                double autoAdjustmentAmount = invoiceLineItemDetails.get().getChargedAmount() - totalAmount;
                                System.out.println("===###########===autoAdjustmentAmount===>>>>>>>" + autoAdjustmentAmount);

                                ClaimPostedAmounts claimPostedAmounts = getClaimPostedAmount(claimSubmissionStatus, claimsCOB835Detail);
                                System.out.println("===###########===writeOffAmount===>>>>>>>" + claimPostedAmounts.getWRITEOFFAmount());
                                System.out.println("===###########===BTAmount===>>>>>>>" + claimPostedAmounts.getBTAmount());

                                invoicePosting(invoiceLineItemDetails.get(), "Payment", claimsCOB835Detail.getProviderPaymentAmount(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                //CO CODE
                                if(claimPostedAmounts.getWRITEOFFAmount() == totalCOAmount.get()){
                                    System.out.println("===###########===EQUAL WRITEOFFAmount===>>>>>>>");
                                }
                                else{
                                    System.out.println("===###########===NOT EQUAL WRITEOFFAmount===>>>>>>>");
                                    AtomicInteger check = new AtomicInteger(0);
                                    if(coCount.get() == 0)
                                        invoicePosting(invoiceLineItemDetails.get(), "WriteOff(CO-45)", totalCOAmount.get(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                    else{
                                        coAmountList.stream().forEach(inv -> {
                                            if(inv != null && inv > 0) {
                                                System.out.println("===###########===inv===>>>>>>>"+inv);
                                                System.out.println("===###########===claimPostedAmounts.getWRITEOFFAmount()===>>>>>>>"+claimPostedAmounts.getWRITEOFFAmount());
                                                if(inv == claimPostedAmounts.getWRITEOFFAmount()){
                                                    check.set(1);
                                                }
                                                System.out.println("===###########===check.get()===>>>>>>>"+check.get());
                                                if(check.get() == 0)
                                                    invoicePosting(invoiceLineItemDetails.get(), "WriteOff(CO-45)", inv, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                                check.set(0);
                                            }
                                        });
                                    }
                                    if(check.get() == 1)
                                        invoicePosting(invoiceLineItemDetails.get(), "WriteOff Adjustment", (-1) * claimPostedAmounts.getWRITEOFFAmount(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                }
                                //PR CODE
                                if(claimPostedAmounts.getBTAmount() == totalPRAmount.get()){
                                    System.out.println("===###########===EQUAL BTAmount===>>>>>>>");
                                }
                                else {
                                    System.out.println("===###########===NOT EQUAL BTAmount===>>>>>>>");
                                    if (prCount.get() == 0)
                                        invoicePosting(invoiceLineItemDetails.get(), "Balance Transfer", totalPRAmount.get(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                    else {
                                        prAmountList.stream().forEach(inv -> {
                                            if(inv != null && inv > 0)
                                                invoicePosting(invoiceLineItemDetails.get(), "Balance Transfer", inv, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                        });
                                    }
                                    invoicePosting(invoiceLineItemDetails.get(), "Balance Transfer", (-1) * claimPostedAmounts.getBTAmount(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                    invoicePrefix.set(prefix);
                                    contraEntryNextLevelPrimaryBalanceTransfer(claimPostedAmounts.getBTAmount(), prCount, prAmountList, totalPRAmount.get(), invoiceLineItemDetails.get(), invoiceMasterDetails, invoicePrefix.get(), claimsCOB835Master.get());
                                }
                                claimsCOB835Detail.setPostStatus("POSTED");
                                claimsCOB835DetailsRepository.save(claimsCOB835Detail);
                                //CHECK FOR AUTO-ADJUSTMENT
                                if(autoAdjustmentAmount <= thresholdValue && autoAdjustmentAmount < 0 && prefix.equalsIgnoreCase("IVM")){
                                    System.out.println("===###########===autoAdjustmentAmount <= thresholdValue===>>>>>>>");
                                    invoicePrefix.set("IVP");
                                    invoicePosting(invoiceLineItemDetails.get(), "Balance Transfer", autoAdjustmentAmount, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                }
                            }
                            System.out.println("=============================END=====================================");
                        }//END PRIMARY

                        if (prefixInternalClaimNo.equalsIgnoreCase("CLS")) {//START SECONDARY PAYMENT
                            System.out.println("=######################################START SECONDARY PAYMENT##########################################");
                            String prefix = "";
                            List<InvoiceMasterDetails> invoiceMasterDetailsList = invoiceMasterDetailsRepository.getInvoiceMasterDetailsOnSalesOrder(claimSubmissionStatus.getSalesOrderId());
                            if(invoiceMasterDetailsList != null){
                                String nextLevel = lookForNextLevel(invoiceMasterDetailsList, "Tertiary");
                                System.out.println("===========>"+nextLevel);
                                if(nextLevel.equalsIgnoreCase("Nothing"))
                                    prefix = "IVM";
                                else
                                    prefix = "IVT";
                            }
                            invoicePrefix.set("IVS");
                            System.out.println("===###########===claimSubmissionStatus.getPayorLevel===>>>>>>>" + claimSubmissionStatus.getPayorLevel());
                            System.out.println("===###########===claimSubmissionStatus.getSalesOrderId()===>>>>>>>" + claimSubmissionStatus.getSalesOrderId());

                            List<Double> prAmountList = Arrays.asList(claimsCOB835Detail.getAdjustmentPrCode1Amount(), claimsCOB835Detail.getAdjustmentPrCode2Amount(),
                                claimsCOB835Detail.getAdjustmentPrCode3Amount(), claimsCOB835Detail.getAdjustmentPrCode4Amount());
                            AtomicReference<Double> totalPRAmount = new AtomicReference<>(0.00);
                            AtomicInteger prCount = new AtomicInteger();
                            prAmountList.stream().forEach(pr -> {
                                if(pr != null && pr > 0){
                                    prCount.getAndIncrement();
                                    totalPRAmount.set(totalPRAmount.get() + pr);
                                }
                            });
                            System.out.println("===###########===totalPRAmount===>>>>>>>" + totalPRAmount+"===>prCount===>"+prCount);

                            List<Double> coAmountList = Arrays.asList(claimsCOB835Detail.getAdjustmentCoCode1Amount(), claimsCOB835Detail.getAdjustmentCoCode2Amount(),
                                claimsCOB835Detail.getAdjustmentCoCode3Amount(), claimsCOB835Detail.getAdjustmentCoCode4Amount());
                            AtomicReference<Double> totalCOAmount = new AtomicReference<>(0.00);
                            AtomicInteger coCount = new AtomicInteger();
                            coAmountList.stream().forEach(co -> {
                                if(co != null && co > 0){
                                    coCount.getAndIncrement();
                                    totalCOAmount.set(totalCOAmount.get() + co);
                                }
                            });
                            System.out.println("===###########===totalCOAmount===>>>>>>>" + totalCOAmount+"===>coCount===>"+coCount);
                            double totalAmount = totalPRAmount.get() + totalCOAmount.get() + claimsCOB835Detail.getProviderPaymentAmount();
                            System.out.println("===###########===totalAmount===>>>>>>>" + totalAmount);

                            System.out.println("===###########===claimSubmissionStatus.getSalesOrderId()===>>>>>>>" + claimSubmissionStatus.getSalesOrderId());
                            System.out.println("===###########===invoicePrefix.get()===>>>>>>>" + invoicePrefix.get());
                            System.out.println("===###########===claimSubmissionStatus.getIntClaimNo()===>>>>>>>" + claimSubmissionStatus.getIntClaimNo());
                            System.out.println("===###########===claimSubmissionStatus.getInvoiceNo()===>>>>>>>" + claimSubmissionStatus.getInvoiceNo());
                            InvoiceMasterDetails invoiceMasterDetails = invoiceMasterDetailsRepository
                                .getInvoiceMasterDetailsOnSalesOrderAndInvoiceNo(claimSubmissionStatus.getSalesOrderId(), claimSubmissionStatus.getInvoiceNo());

                            System.out.println("===###########===invoiceMasterDetails.getInvoiceNo()===>>>>>>>" + invoiceMasterDetails.getInvoiceNo());
                            System.out.println("===###########===transaction.getItemProcCode()===>>>>>>>" + claimsCOB835Detail.getAdjudicatedProcedureCode());

                            AtomicReference<InvoiceLineItemDetails> invoiceLineItemDetails = new AtomicReference<>();
                            invoiceLineItemDetails.set(invoiceLineItemDetailsRepository
                                .getSecondaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(invoiceMasterDetails.getInvoiceNo(), claimsCOB835Detail.getAdjudicatedProcedureCode()));
                            if(invoiceLineItemDetails != null){
                                ClaimPostedAmounts claimPostedAmounts = getClaimPostedAmount(claimSubmissionStatus, claimsCOB835Detail);
                                System.out.println("===###########===writeOffAmount===>>>>>>>" + claimPostedAmounts.getWRITEOFFAmount());
                                System.out.println("===###########===BTAmount===>>>>>>>" + claimPostedAmounts.getBTAmount());

                                double autoAdjustmentAmount = Double.valueOf(df.format(claimPostedAmounts.getBTAmount() + totalAmount));
                                System.out.println("===###########===autoAdjustmentAmount===>>>>>>>" + autoAdjustmentAmount);

                                invoicePosting(invoiceLineItemDetails.get(), "Payment", claimsCOB835Detail.getProviderPaymentAmount(), invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                //CO CODE
                                coAmountList.stream().forEach(inv -> {
                                    if(inv != null && inv > 0)
                                        invoicePosting(invoiceLineItemDetails.get(), "WriteOff(CO-45)", inv, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                });

                                //PR CODE
                                prAmountList.stream().forEach(inv -> {
                                    if(inv != null && inv > 0)
                                        invoicePosting(invoiceLineItemDetails.get(), "Balance Transfer", inv, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                });
                                invoicePrefix.set(prefix);
                                contraEntryNextLevelSecondaryBalanceTransfer(prAmountList, totalPRAmount.get(), invoiceLineItemDetails.get(), invoiceMasterDetails, invoicePrefix.get(), claimsCOB835Master.get());
                                claimsCOB835Detail.setPostStatus("POSTED");
                                claimsCOB835DetailsRepository.save(claimsCOB835Detail);

                                //CHECK FOR AUTO-ADJUSTMENT
                                System.out.println("===###########===autoAdjustmentAmount <= thresholdValue===>>>>>>>"+autoAdjustmentAmount);
                                System.out.println("===###########===autoAdjustmentAmount <= thresholdValue===>>>>>>>"+thresholdValue);
                                System.out.println("===###########===autoAdjustmentAmount <= thresholdValue===>>>>>>>"+prefix);
                                if(autoAdjustmentAmount <= thresholdValue && autoAdjustmentAmount < 0 && prefix.equalsIgnoreCase("IVM")){
                                    System.out.println("===###########===autoAdjustmentAmount <= thresholdValue===>>>>>>>");
                                    invoicePrefix.set("IVS");
                                    invoicePosting(invoiceLineItemDetails.get(), "Auto Adjustment", (-1) * autoAdjustmentAmount, invoicePrefix.get(), invoiceMasterDetails, claimsCOB835Master.get(), 0, null, "S");
                                }
                            }
                            System.out.println("=============================END=====================================");
                        }
                    }
                }
            });
        }
    }

    private ClaimPostedAmounts getClaimPostedAmount(ClaimSubmissionStatus claimSubmissionStatus, ClaimsCOB835Details claimsCOB835Detail){
        ClaimPostedAmounts claimPostedAmounts = new ClaimPostedAmounts();
        List<InvoicePostingDetails> invoicePostingDetails = invoicePostingDetailsRepository
            .getInvoiceClaimPostingOnItemInvoiceNoAndPostStatus(claimsCOB835Detail.getAdjudicatedProcedureCode(),
                claimSubmissionStatus.getInvoiceNo(), DefineStatus.Open.name(), DefineStatus.Active.name());
        if(invoicePostingDetails.size() > 0){
            for(InvoicePostingDetails invoicePosting : invoicePostingDetails){
                if(invoicePosting.getPostType().equalsIgnoreCase("Adjustment")){
                    claimPostedAmounts.setWRITEOFFAmount(invoicePosting.getPostAmount());
                }
                if(invoicePosting.getPostType().equalsIgnoreCase("Balance Transfer")){
                    claimPostedAmounts.setBTAmount(invoicePosting.getPostAmount());
                }
            }
        }
        return claimPostedAmounts;
    }

    private void contraEntryNextLevelPrimaryBalanceTransfer(double BTAmount, AtomicInteger prCount, List<Double> prAmountList, Double totalPRAmount,
                                                            InvoiceLineItemDetails invoiceLineItemDetails, InvoiceMasterDetails invoiceMasterDetails,
                                                            String invoicePrefix, ClaimsCOB835Master claimsCOB835Master) {
        System.out.println("====================contraEntryForBalanceTransfer=================>"+BTAmount+"===>"+totalPRAmount);
        if (prCount.get() == 1)
            invoicePosting(invoiceLineItemDetails, "Balance Transfer", (-1) * totalPRAmount, invoicePrefix, invoiceMasterDetails, claimsCOB835Master, 0, null, "S");
        else {
            prAmountList.stream().forEach(inv -> {
                if(inv != null && inv > 0)
                    invoicePosting(invoiceLineItemDetails, "Balance Transfer", (-1) * inv, invoicePrefix, invoiceMasterDetails, claimsCOB835Master, 0, null, "S");
            });
        }
        invoicePosting(invoiceLineItemDetails, "Balance Transfer", BTAmount, invoicePrefix, invoiceMasterDetails, claimsCOB835Master, 0, null, "S");
    }

    private void contraEntryNextLevelSecondaryBalanceTransfer(List<Double> prAmountList, Double totalPRAmount, InvoiceLineItemDetails invoiceLineItemDetails,
                                                              InvoiceMasterDetails invoiceMasterDetails, String invoicePrefix, ClaimsCOB835Master claimsCOB835Master) {
        System.out.println("====================contraEntryForBalanceTransfer=================>"+"===>"+totalPRAmount);
        prAmountList.stream().forEach(inv -> {
            if(inv != null && inv > 0)
                invoicePosting(invoiceLineItemDetails, "Balance Transfer", (-1) * inv, invoicePrefix, invoiceMasterDetails, claimsCOB835Master, 0, null, "S");
        });
    }

    public InvoicePostingDetails invoicePosting(InvoiceLineItemDetails invoiceLineItemDetails, String narration,
                               Double postingAmount, String nextLevel, InvoiceMasterDetails invoiceMasterDetails, ClaimsCOB835Master claimsCOB835Master, int type,
                                                ManualInvoiceEntryInputParameters manualInvoiceEntryInputParameters, String postingType) {
        System.out.println("====================START=================>");
        InvoicePostingDetails invoicePostingDetails = new InvoicePostingDetails();
        Optional<DepositMasterDetails> depositMasterDetails = Optional.ofNullable(depositMasterDetailsRepository.getDepositeDetailsOnClaimCOB835Master(claimsCOB835Master.getClaimCob835MasterId()));
        Optional<ReceiptMasterDetails> receiptMasterDetails = null;
        if (depositMasterDetails.isPresent()) {
            receiptMasterDetails = Optional.ofNullable(receiptMasterDetailsRepository.getReceiptDetailsOnDepositMaster(depositMasterDetails.get().getDepositId()));
        }
        invoicePostingDetails.setPostingComment(narration);
        invoicePostingDetails.postType(narration);

        invoicePostingDetails.setInvoiceNo(invoiceLineItemDetails.getPrimaryInvoiceNo());
        invoicePostingDetails.setPostedById(0L);
        invoicePostingDetails.setInvoiceDate(invoiceLineItemDetails.getInvoiceDate());
        invoicePostingDetails.setCreatedById(0L);
        invoicePostingDetails.setCreatedByName("System");
        invoicePostingDetails.setCreatedDate(LocalDate.now());
        invoicePostingDetails.setDepositId(depositMasterDetails.get().getDepositId());
        invoicePostingDetails.setReceiptId(receiptMasterDetails.get().getReceiptId());
        invoicePostingDetails.setInvoiceLineItemDetailsId(invoiceLineItemDetails.getInvoiceLineItemDetailsId());
        invoicePostingDetails.setItemId(invoiceLineItemDetails.getItemId());
        invoicePostingDetails.setPostAmount(postingAmount);
        invoicePostingDetails.setInvoiceNo(nextLevel + invoiceMasterDetails.getInvoiceNo().substring(3));
        invoicePostingDetails.setStatus(DefineStatus.Active.name());

        invoicePostingDetails.setUpdatedById(null);
        invoicePostingDetails.setUpdatedByName(null);
        invoicePostingDetails.setUpdatedDate(null);
        invoicePostingDetails.setPostStatus(DefineStatus.POSTED.name());
        if (type == 0){
            invoicePostingDetails.setPostedByName("System");
            invoicePostingDetails.setPostingDate(LocalDate.now());
        }
        else{
            invoicePostingDetails.setPostedByName("Bimal");
            log.info("==============Entry Date====================>"+manualInvoiceEntryInputParameters.getEntryDate());
            invoicePostingDetails.setPostingDate(LocalDate.parse(manualInvoiceEntryInputParameters.getEntryDate()));
        }

        if(postingType.equalsIgnoreCase("M"))
            invoicePostingDetails.setIsManualPosting(true);
        else
            invoicePostingDetails.setIsManualPosting(false);
        invoicePostingDetails.setHcpcsCode(invoiceLineItemDetails.getHcpcsCode());
        invoicePostingDetails.setPostingNo(invoicePostingDetailsRepository.getPostingNumber());
        invoicePostingDetails.setInvoicePostingDetailsUuid(UUID.randomUUID());
        InvoicePostingDetails saveInvoicePostingDetails = invoicePostingDetailsRepository.save(invoicePostingDetails);
        return saveInvoicePostingDetails;
    }

    public static String lookForNextLevel(List<InvoiceMasterDetails> invoiceMasterDetailsList, String type) {
        Optional<String> insType = Optional.of(invoiceMasterDetailsList.stream()
            .filter(a -> a.getInvoiceToEntity().equalsIgnoreCase(type))
            .map(p -> p.toString())
            .findAny()
            .orElse("Nothing"));
        return insType.get();
    }

    @Override
    public ServiceOutcome<List<SalesOrderCommonSearchOutputDTO>> searchSalesOrderListOnSearchCriteria(SalesOrderCombinedSearchParameterDTO salesOrderCombinedSearchParameterDTO) throws ExecutionException, InterruptedException, JsonProcessingException {
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
//        String token = "abbbb";
        String url = "/salesorder/api/getSalesOrderDetailsByCombinedParameters";
        log.info("=================url=====================================>"+url);
        List<SalesOrderCommonSearchOutputDTO> salesOrderListOutcome = null;
        String outcomeMessage = null;
        boolean outcome = false;
        try {
            salesOrderListOutcome = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                    .queryParam("branchID", salesOrderCombinedSearchParameterDTO.getBranchID())
                    .queryParam("branchName", salesOrderCombinedSearchParameterDTO.getBranchName())
                    .queryParam("salesOrderUUID", salesOrderCombinedSearchParameterDTO.getSalesOrderUUID())
                    .queryParam("patientIDNo", salesOrderCombinedSearchParameterDTO.getPatientIDNo())
                    .queryParam("patientFirstName", salesOrderCombinedSearchParameterDTO.getPatientFirstName())
                    .queryParam("patientMiddleName", salesOrderCombinedSearchParameterDTO.getPatientMiddleName())
                    .queryParam("patientLastName", salesOrderCombinedSearchParameterDTO.getPatientLastName())
                    .queryParam("salesOrderNo", salesOrderCombinedSearchParameterDTO.getSalesOrderNo())
                    .queryParam("scheduleDeliveryFromDate", salesOrderCombinedSearchParameterDTO.getScheduleDeliveryFromDate())
                    .queryParam("scheduleDeliveryToDate", salesOrderCombinedSearchParameterDTO.getScheduleDeliveryToDate())
                    .queryParam("deliveryActualDateStart", salesOrderCombinedSearchParameterDTO.getDeliveryActualDateStart())
                    .queryParam("deliveryActualDateEnd", salesOrderCombinedSearchParameterDTO.getDeliveryActualDateEnd())
                    .queryParam("createdByID", salesOrderCombinedSearchParameterDTO.getCreatedByID())
                    .queryParam("createdByName", salesOrderCombinedSearchParameterDTO.getCreatedByName())
                    .queryParam("createdDateFromDate", salesOrderCombinedSearchParameterDTO.getCreatedDateFromDate())

                    .queryParam("createdDateToDate", salesOrderCombinedSearchParameterDTO.getCreatedDateToDate())
                    .queryParam("itemNo", salesOrderCombinedSearchParameterDTO.getItemNo())
                    .queryParam("itemName", salesOrderCombinedSearchParameterDTO.getItemName())
                    .queryParam("hCPCSCode", salesOrderCombinedSearchParameterDTO.getHCPCSCode())
                    .queryParam("primaryPayerId", salesOrderCombinedSearchParameterDTO.getPrimaryPayerId())
                    .queryParam("primaryPayerName", salesOrderCombinedSearchParameterDTO.getPrimaryPayerName())
                    .queryParam("dropshipStatus", salesOrderCombinedSearchParameterDTO.getDropshipStatus())
                    .queryParam("deliveryAddressLine1", salesOrderCombinedSearchParameterDTO.getDeliveryAddressLine1())
                    .queryParam("deliveryAddressLine2", salesOrderCombinedSearchParameterDTO.getDeliveryAddressLine2())
                    .queryParam("deliveryCity", salesOrderCombinedSearchParameterDTO.getDeliveryCity())
                    .queryParam("deliveryState", salesOrderCombinedSearchParameterDTO.getDeliveryState())
                    .queryParam("deliveryZip", salesOrderCombinedSearchParameterDTO.getDeliveryZip())
                    .queryParam("salesOrderStatus", salesOrderCombinedSearchParameterDTO.getSalesOrderStatus())
                    .build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error((Supplier<? extends Throwable>) handleError("A client error occurred!")))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error((Supplier<? extends Throwable>) handleError("A server error occurred!")))
                .bodyToFlux(SalesOrderCommonSearchOutputDTO.class).collectList().toFuture().get();
            log.info("==============Response Result Next================" + salesOrderListOutcome.size());
            if(salesOrderListOutcome.size() > 0){
                outcome = true;
                outcomeMessage = "Sales order details fetched successfully!!";
            }
            else {
                outcome = true;
                outcomeMessage = "There are no sales order details in this search criteria!!";
            }
        }
        catch(Exception e) {
            log.error("=================Exception=======================");
            outcome = false;
            outcomeMessage = "Unable to process due to internal server error!!";
        }
        return new ServiceOutcome<>(salesOrderListOutcome, outcome, outcomeMessage);
    }

    private Mono<? extends Throwable> handleError(String message){
        log.error(message);
        return Mono.error(Exception::new);
    }

    @Override
    public ServiceOutcome<List<InvoiceDetailsProjection>> getInvoiceListOnSalesOrder(String salesOrderNo){
        ModelMapper mapper = new ModelMapper();
        return new ServiceOutcome<>(claimSubmissionStatusRepository.getInvoiceListOnSalesOrder(salesOrderNo)
            .stream()
            .map(invoiceDetailsProjection -> mapper.map(invoiceDetailsProjection, InvoiceDetailsProjection.class))
            .collect(Collectors.toList()), true, "Invoice list fetched successfully!!");
    }

    @Override
    public ServiceOutcome<List<ClaimedInvoiceDetailsProjection>> getInvoiceDetailsOnInvoice(Long invoiceId){
        ModelMapper mapper = new ModelMapper();
        return new ServiceOutcome<>(invoiceMasterDetailsRepository.getInvoiceDetailsOnInvoice(invoiceId)
            .stream()
            .map(claimedInvoiceDetailsProjection -> mapper.map(claimedInvoiceDetailsProjection, ClaimedInvoiceDetailsProjection.class))
            .collect(Collectors.toList()), true, "Invoice Details fetched successfully!!");
    }

    @Override
    public ServiceOutcome<InvoiceItemsDetailPostingMain> getInvoicePostingDetailsOnInvoice(String invoiceNo){
        List<InvoiceItemsDetailPosting> invoiceItemsDetailPostingList = new ArrayList<>();
        InvoiceItemsDetailPostingMain invoiceItemsDetailPostingMain = new InvoiceItemsDetailPostingMain();
        List<String> hcpcsCodes = invoicePostingDetailsRepository.getItemListOnInvoiceNo(invoiceNo);
        final double[] totalChargeAmount = {0.00};
        final double[] totalAllowedAmount = {0.00};
        final double[] totalPaymentAmount = {0.00};
        final double[] totalAdjustmentAmount = {0.00};
        AtomicReference<Double> totalBalanceAmount = new AtomicReference<>(0.00);
        if(hcpcsCodes.size() > 0){
            hcpcsCodes.stream().forEach(hcpcs -> {
                log.info("========================================START================================================");
                log.info("====>"+hcpcs);
                InvoiceItemsDetailPosting invoiceItemsDetailPosting = new InvoiceItemsDetailPosting();
                String prefixInvoiceNo = invoiceNo.substring(0, 3);
                InvoiceLineItemDetails invoiceLineItemDetails = null;
                if(prefixInvoiceNo.equalsIgnoreCase("IVP"))
                    invoiceLineItemDetails = invoiceLineItemDetailsRepository.getInvoiceLineItemDetailsOnPrimaryInvoiceNoAndProcCode(invoiceNo, hcpcs);
                if(prefixInvoiceNo.equalsIgnoreCase("IVS"))
                    invoiceLineItemDetails = invoiceLineItemDetailsRepository.getInvoiceLineItemDetailsOnSecondaryInvoiceNoAndProcCode(invoiceNo, hcpcs);
                if(invoiceLineItemDetails != null){
                    InvoiceItemHeaderValue invoiceItemHeaderValue = new InvoiceItemHeaderValue();
                    invoiceItemHeaderValue.setQuantity(invoiceLineItemDetails.getItemQty().toString());
                    invoiceItemHeaderValue.setProcedureCode(invoiceLineItemDetails.getHcpcsCode());
                    invoiceItemHeaderValue.setItemName(invoiceLineItemDetails.getItemName());
                    invoiceItemHeaderValue.setSaleType("");
                    invoiceItemsDetailPosting.setInvoiceItemHeaderValue(invoiceItemHeaderValue);

                    List<InvoicePostingDetails> invoicePostingDetails = invoicePostingDetailsRepository.getInvoiceClaimPostingOnItemInvoiceNo(hcpcs, invoiceNo, DefineStatus.Active.name());
                    List<InvoiceItemPostingDetails> invoiceItemPostingDetailsList = new ArrayList<>();
                    final double[] paymentAmount = {0.00};
                    final double[] adjustmentBTAmounts = {0.00};
                    final double[] totalBalance = {0.00};
                    invoicePostingDetails.stream().forEach(postings -> {
                        InvoiceItemPostingDetails invoiceItemPostingDetails = new InvoiceItemPostingDetails();
                        invoiceItemPostingDetails.setDate(dtf.format(postings.getInvoiceDate()));
                        invoiceItemPostingDetails.setCreatedBy(postings.getCreatedByName());
                        if(postings.getDepositId() != null) {
                            Optional<DepositMasterDetails> depositMasterDetails = depositMasterDetailsRepository.findById(postings.getDepositId());
                            invoiceItemPostingDetails.setDepositId(depositMasterDetails.get().getDepositNo());
                        }
                        else
                            invoiceItemPostingDetails.setDepositId("");
                        if(postings.getReceiptId() != null){
                            Optional<ReceiptMasterDetails> receiptMasterDetails = receiptMasterDetailsRepository.findById(postings.getReceiptId());
                            invoiceItemPostingDetails.setReceiptId(receiptMasterDetails.get().getReceiptNo());
                        }
                        else
                            invoiceItemPostingDetails.setReceiptId("");
                        invoiceItemPostingDetails.setPaymentType(postings.getPostingComment());
                        if(postings.getPostType().equalsIgnoreCase("Payment")) {
                            invoiceItemPostingDetails.setPayments(postings.getPostAmount().toString());
                            paymentAmount[0] = paymentAmount[0] + postings.getPostAmount();
                        }
                        invoiceItemPostingDetails.setTax("");
                        if(!postings.getPostType().equalsIgnoreCase("Payment")) {
                            invoiceItemPostingDetails.setAdjustment(postings.getPostAmount().toString());
                            adjustmentBTAmounts[0] = adjustmentBTAmounts[0] + postings.getPostAmount();
                        }
                        totalBalance[0] = totalBalance[0] + postings.getPostAmount();
                        invoiceItemPostingDetailsList.add(invoiceItemPostingDetails);
                    });
                    invoiceItemsDetailPosting.setInvoiceItemPostingDetailsList(invoiceItemPostingDetailsList);

                    InvoiceItemTotals invoiceItemTotals = new InvoiceItemTotals();
                    invoiceItemTotals.setCharge(String.valueOf(Double.valueOf(df.format(invoiceLineItemDetails.getChargedAmount()))));
                    invoiceItemTotals.setAllowed(String.valueOf(Double.valueOf(df.format(invoiceLineItemDetails.getAllowAmount()))));
                    invoiceItemTotals.setPayments(String.valueOf(Double.valueOf(df.format(Arrays.stream(paymentAmount).sum()))));
                    log.info("===============paymentAmount=============="+Arrays.stream(paymentAmount).sum());
                    invoiceItemTotals.setTax("");
                    invoiceItemTotals.setAdjustment(String.valueOf(Double.valueOf(df.format(Arrays.stream(adjustmentBTAmounts).sum()))));
                    log.info("===============adjustmentBTAmounts=============="+Arrays.stream(adjustmentBTAmounts).sum());

                    double balance = 0.00;
                    if(prefixInvoiceNo.equalsIgnoreCase("IVP"))
                        balance = Double.valueOf(df.format(invoiceLineItemDetails.getChargedAmount() - Arrays.stream(paymentAmount).sum() - Arrays.stream(adjustmentBTAmounts).sum()));
                    if(prefixInvoiceNo.equalsIgnoreCase("IVS"))
                        balance = totalBalance[0];
                    log.info("===============balance=============="+balance);
                    invoiceItemTotals.setBalance(String.valueOf(Double.valueOf(df.format(balance))));
                    invoiceItemsDetailPosting.setInvoiceItemTotals(invoiceItemTotals);

                    totalChargeAmount[0] = totalChargeAmount[0] + invoiceLineItemDetails.getChargedAmount();
                    totalAllowedAmount[0] = totalAllowedAmount[0] + invoiceLineItemDetails.getAllowAmount();
                    totalAdjustmentAmount[0] = totalAdjustmentAmount[0] + Arrays.stream(adjustmentBTAmounts).sum();
                    totalPaymentAmount[0] = totalPaymentAmount[0] + Arrays.stream(paymentAmount).sum();
                    totalBalanceAmount.set(totalBalanceAmount.get() + balance);

                    invoiceItemsDetailPostingList.add(invoiceItemsDetailPosting);
                    log.info("========================================END================================================");
                }
            });
            log.info("=============totalBalanceAmount================"+totalBalanceAmount.get());
            InvoiceItemDetailsTotal invoiceItemDetailsTotal = new InvoiceItemDetailsTotal();
            invoiceItemDetailsTotal.setCharge(String.valueOf(Double.valueOf(df.format(Arrays.stream(totalChargeAmount).sum()))));
            invoiceItemDetailsTotal.setAllowed(String.valueOf(Double.valueOf(df.format(Arrays.stream(totalAllowedAmount).sum()))));
            invoiceItemDetailsTotal.setAdjustment(String.valueOf(Double.valueOf(df.format(Arrays.stream(totalAdjustmentAmount).sum()))));
            invoiceItemDetailsTotal.setTax(String.valueOf(0.00));
            invoiceItemDetailsTotal.setPayments(String.valueOf(Double.valueOf(df.format(Arrays.stream(totalPaymentAmount).sum()))));
            invoiceItemDetailsTotal.setBalance(String.valueOf(Double.valueOf(df.format(totalBalanceAmount.get()))));
            invoiceItemsDetailPostingMain.setInvoiceItemsDetailPostingList(invoiceItemsDetailPostingList);
            invoiceItemsDetailPostingMain.setInvoiceItemDetailsTotal(invoiceItemDetailsTotal);
        }
        return new ServiceOutcome<>(invoiceItemsDetailPostingMain, true, "Successfully prepare the invoice posting!!");
    }

    @Override
    public ServiceOutcome<ManualInvoiceEntryOutputParameters> manualInvoiceEntry(ManualInvoiceEntryInputParameters manualInvoiceEntryInputParameters){
        log.info("=====================Manual Invoice Entry==========================");
        Long depositId = invoicePostingDetailsRepository.getDepositeOnInvoiceNoAndHcpcsCode(manualInvoiceEntryInputParameters.getHcpcsCode(),
            manualInvoiceEntryInputParameters.getInvoiceNo(), DefineStatus.POSTED.name(), DefineStatus.Active.name());
        if(depositId != null){
            log.info("=====================depositId========================"+depositId);
            Optional<DepositMasterDetails> depositMasterDetails = depositMasterDetailsRepository.findById(depositId);
            if(depositMasterDetails.isPresent() && depositMasterDetails.get().getDepositStatus() == null){
                Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(depositMasterDetails.get().getClaimCob835MasterId());
                log.info("=====================claimsCOB835Master========================"+claimsCOB835Master.get().getClaimCob835MasterId());
                ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository
                    .getClaimSubmissionStatusByInternalClaimControlNumber(claimsCOB835Master.get().getPatientControlNumber());
                log.info("=====================claimSubmissionStatus========================"+claimSubmissionStatus.getInvoiceNo());
                log.info("=====================claimSubmissionStatus========================"+claimSubmissionStatus.getSalesOrderId());

                String prefixInvoiceNo = claimSubmissionStatus.getInvoiceNo().substring(0, 3);
                log.info("=====================prefixInvoiceNo========================"+prefixInvoiceNo);

                InvoiceMasterDetails invoiceMasterDetails = invoiceMasterDetailsRepository
                    .getInvoiceMasterDetailsOnSalesOrderAndInvoiceNo(claimSubmissionStatus.getSalesOrderId(), claimSubmissionStatus.getInvoiceNo());

                AtomicReference<InvoiceLineItemDetails> invoiceLineItemDetails = new AtomicReference<>();
                invoiceLineItemDetails.set(invoiceLineItemDetailsRepository
                    .getPrimaryInvoiceLineItemDetailsOnInvoiceNoAndProcCode(invoiceMasterDetails.getInvoiceNo(), manualInvoiceEntryInputParameters.getHcpcsCode()));

                InvoicePostingDetailsDTO invoicePostingDetailsDTO = invoicePostingDetailsMapper.toDto(invoicePosting(invoiceLineItemDetails.get(), manualInvoiceEntryInputParameters.getPostType(), manualInvoiceEntryInputParameters.getEntryAmount(),
                    prefixInvoiceNo, invoiceMasterDetails, claimsCOB835Master.get(), 1, manualInvoiceEntryInputParameters, "M"));
                ManualInvoiceEntryOutputParameters outputParameters = convertingToManualInvoiceEntryOutput(invoicePostingDetailsDTO);
                return new ServiceOutcome<>(outputParameters, true, "Manual invoice entry is done successfully!");
            }
            return new ServiceOutcome<>(null, false, "Unable to do the manual entry due to deposit has already been posted!");
        }
        else{
            return new ServiceOutcome<>(null, false, "There is no deposit exists in these input search data!");
        }
    }

    public ManualInvoiceEntryOutputParameters convertingToManualInvoiceEntryOutput(InvoicePostingDetailsDTO invoicePostingDetailsDTO){
        ManualInvoiceEntryOutputParameters outputParameters = new ManualInvoiceEntryOutputParameters();
        outputParameters.setInvoiceLineItemPostingId(invoicePostingDetailsDTO.getInvoiceLineItemPostingId());
        outputParameters.setItemId(invoicePostingDetailsDTO.getItemId());
        outputParameters.setPostingDate(dateTimeFormatter.format(invoicePostingDetailsDTO.getPostingDate()));
        outputParameters.setPostedById(invoicePostingDetailsDTO.getPostedById());
        outputParameters.setPostedByName(invoicePostingDetailsDTO.getPostedByName());
        outputParameters.setPostingComment(invoicePostingDetailsDTO.getPostingComment());
        outputParameters.setPostType(invoicePostingDetailsDTO.getPostType());
        outputParameters.setDepositId(invoicePostingDetailsDTO.getDepositId());
        outputParameters.setReceiptId(invoicePostingDetailsDTO.getReceiptId());
        outputParameters.setPostStatus(invoicePostingDetailsDTO.getPostStatus());
        outputParameters.setInvoicePostingDetailsUuid(invoicePostingDetailsDTO.getInvoicePostingDetailsUuid().toString());
        outputParameters.setInvoiceNo(invoicePostingDetailsDTO.getInvoiceNo());
        outputParameters.setInvoiceDate(dateTimeFormatter.format(invoicePostingDetailsDTO.getInvoiceDate()));
        outputParameters.setHcpcsCode(invoicePostingDetailsDTO.getHcpcsCode());
        outputParameters.setPostingNo(invoicePostingDetailsDTO.getPostingNo());
        return outputParameters;
    }

    @Override
    public ServiceOutcome<DepositMasterDetailsDTO> manualInvoiceEntryPosting(Long depositId){
        Optional<DepositMasterDetails> depositMasterDetails = depositMasterDetailsRepository.findById(depositId);
        if(depositMasterDetails.isPresent()){
            Double totalPostedAmount = invoicePostingDetailsRepository.getTotalPostedPaymentAmountOnDeposit(depositMasterDetails.get().getDepositId());
            log.info("===============totalPostedAmount==================="+totalPostedAmount);
            if(totalPostedAmount == depositMasterDetails.get().getDepositAmount()){
                log.info("=============================IF=====================================");
                depositMasterDetails.get().setDepositStatus(DefineStatus.POSTED.name());
                DepositMasterDetails depositMasterDetail = depositMasterDetailsRepository.save(depositMasterDetails.get());
                return new ServiceOutcome<>(depositMasterDetailsMapper.toDto(depositMasterDetail), true, "Deposit gets posted successfully!");
            }
            else{
                log.info("=============================ELSE===================================");
                return new ServiceOutcome<>(null, false, "Unable to post the deposit!");
            }
        }
        return new ServiceOutcome<>(null, false, "Deposit is not exists!");


//        log.info("=============================depositId==================================="+depositId);
//        Optional<DepositMasterDetails> depositMasterDetails = depositMasterDetailsRepository.findById(depositId);
//        Stream.of(depositMasterDetailsRepository.findById(depositId))
//            .filter(b -> b.isPresent())
//            .map(a -> invoicePostingDetailsRepository.getTotalPostedPaymentAmountOnDeposit(a.get().getDepositId()))
//            .filter(c -> {
//                log.info("=============================Filter==================================="+c);
//                if(c == depositMasterDetails.get().getDepositAmount()) {
//                    log.info("=============================SAME==================================="+depositMasterDetails.get().getDepositAmount());
//                    return false;
//                }
//                else {
//                    log.info("=============================NT SAME==================================="+depositMasterDetails.get().getDepositAmount());
//                    return true;
//                }
//            })
//            .map(x -> {
//                log.info("================ZZZZZZZZZZZZZ===================="+x);
//                depositMasterDetails.get().setDepositStatus(DefineStatus.POSTED.name());
//                depositMasterDetailsRepository.save(depositMasterDetails.get());
//                return x;
//            })
//            .forEach(System.out::println);
//        return null;
    }

    @Override
    public List<UnprocessedCOBClaimFiles> prepareUnprocessedCOBClaimsList(){
        List<String> fileNameList = claimsCOB835MasterRepository.getActiveUnprocessedFileNameListOnException();
        List<UnprocessedCOBClaimFiles> unprocessedCOBClaimsFilesList = new ArrayList<>();
        fileNameList.stream().forEach(file -> {
            System.out.println("============>"+file);
            ModelMapper mapper = new ModelMapper();
            List<UnprocessedCOBClaimsDetailProjection> unprocessedCOBClaimsDetailProjections = claimsCOB835MasterRepository.getActiveUnprocessedFileNameListOnExceptionFileName(file)
                .stream().map(unprocessedFileProjection -> mapper.map(unprocessedFileProjection, UnprocessedCOBClaimsDetailProjection.class)).collect(Collectors.toList());
            AtomicReference<UnprocessedCOBClaimFiles> unprocessedCOBClaimFiles = new AtomicReference<>();
            List<UnprocessedCOBClaimsDetail> unprocessedCOBClaimsDetailList = new ArrayList<>();
            final Double[] totalChargeAmount = {0.00};
            final Double[] totalPaymentAmount = {0.00};
            final Double[] totalResponsibilityAmount = {0.00};
            unprocessedCOBClaimsDetailProjections.stream().forEach(projection -> {
                UnprocessedCOBClaimsDetail unprocessedCOBClaimsDetail = new UnprocessedCOBClaimsDetail();
                unprocessedCOBClaimsDetail.setClaimCob835MasterId(Long.valueOf(projection.getClaimCob835MasterId()));
                unprocessedCOBClaimsDetail.setPayerClaimControlNumber(projection.getPayerClaimControlNumber());
                unprocessedCOBClaimsDetail.setPatientName(projection.getPatientName());
                unprocessedCOBClaimsDetail.setPatientControlNumber(projection.getPatientControlNumber());
                unprocessedCOBClaimsDetailList.add(unprocessedCOBClaimsDetail);

                unprocessedCOBClaimFiles.set(new UnprocessedCOBClaimFiles());
                unprocessedCOBClaimFiles.get().setFileName(projection.getFileName());
                unprocessedCOBClaimFiles.get().setPayerName(projection.getPayerName());
                unprocessedCOBClaimFiles.get().setCheckIssueOrEFTEffectiveDate(projection.getCheckIssueOrEftEffectiveDate());
                unprocessedCOBClaimFiles.get().setCheckOrEFTTraceNumber(projection.getCheckOrEftTraceNumber());
                totalChargeAmount[0] = totalChargeAmount[0] + Double.valueOf(projection.getTotalClaimChargeAmount());
                totalPaymentAmount[0] = totalPaymentAmount[0] + Double.valueOf(projection.getTotalClaimPaymentAmount());
                totalResponsibilityAmount[0] = totalResponsibilityAmount[0] + Double.valueOf(projection.getTotalPatientResponsibilityAmount());

                unprocessedCOBClaimFiles.get().setTotalClaimChargeAmount(totalChargeAmount[0]);
                unprocessedCOBClaimFiles.get().setTotalClaimPaymentAmount(totalPaymentAmount[0]);
                unprocessedCOBClaimFiles.get().setTotalPatientResponsibilityAmount(totalResponsibilityAmount[0]);
            });
            unprocessedCOBClaimFiles.get().setUnprocessedCOBClaimsDetailList(unprocessedCOBClaimsDetailList);
            unprocessedCOBClaimsFilesList.add(unprocessedCOBClaimFiles.get());
        });
        return unprocessedCOBClaimsFilesList;
    }

    @Override
    public ServiceOutcome<String> cob835DepositAmountAdjustment(DepositAmountAdjustmentInputParameters depositAmountAdjustmentInputParameters){
        String data = "";
        Boolean outcome = false;
        String message = "";
        try {
            List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.getClaimTransactionMasterOnEFTTraceNumberAndDate(depositAmountAdjustmentInputParameters.getCheckOrEFTTraceNumber(),
                LocalDate.parse(depositAmountAdjustmentInputParameters.getCheckIssueOrEFTEffectiveDate(), dtf));
            log.info("===============>" + claimsCOB835MasterList.size());
            AtomicReference<Double> unprocessedPaymentAmount = new AtomicReference<>(0.00);
            if (claimsCOB835MasterList.size() > 0) {
                claimsCOB835MasterList.stream().forEach(claim -> {
                    ClaimsCOB835Master claimsCOB835Master = claimsCOB835MasterRepository.getClaimTransactionMasterOnClaimCOBAndFileName(claim.getClaimCob835MasterId(), claim.getFileName());
                    if (claimsCOB835Master != null) {
                        log.info("=======IF========>" + claimsCOB835Master.getCheckOrEFTTraceNumber());
                        unprocessedPaymentAmount.set(unprocessedPaymentAmount.get() + claim.getTotalClaimPaymentAmount());
                    } else {
                        log.info("=======ELSE========>");
                    }
                });
            }
            log.info("=====================UnProcessed Amount======================" + unprocessedPaymentAmount);
            Optional<DepositMasterDetails> depositMasterDetails = Optional.ofNullable(depositMasterDetailsRepository.getDepositMasterDetailsOnReferenceNumber(depositAmountAdjustmentInputParameters.getCheckOrEFTTraceNumber()));
            if (depositMasterDetails.isPresent()) {
                log.info("=============depositAmountAdjustmentInputParameters.getAdjustmentAmount()===============>"+depositAmountAdjustmentInputParameters.getAdjustmentAmount());
                if (depositAmountAdjustmentInputParameters.getAdjustmentAmount().equals(unprocessedPaymentAmount.get())) {
                    log.info("=============getAdjustmentAmount == unprocessedPaymentAmount.get()===============>");
                    depositMasterDetails.get().setDepositAmount(depositMasterDetails.get().getDepositAmount() - unprocessedPaymentAmount.get());
                    depositMasterDetails.get().setDepositStatus(DefineStatus.POSTED.name());
                    DepositMasterDetails depositDetails = depositMasterDetailsRepository.save(depositMasterDetails.get());
                    ReceiptMasterDetails receiptMasterDetails = receiptMasterDetailsRepository.getReceiptDetailsOnDepositMaster(depositMasterDetails.get().getDepositId());
                    receiptMasterDetails.setReceiptAmount(depositDetails.getDepositAmount());
                    receiptMasterDetails.setReceiptStatus(DefineStatus.POSTED.name());
                    receiptMasterDetailsRepository.save(receiptMasterDetails);
                    data = "Deposit amount is adjusted successfully!!";
                    outcome = true;
                    message = "Deposit amount is adjusted successfully!!";
                } else {
                    log.info("=============getAdjustmentAmount != unprocessedPaymentAmount.get()===============>");
                    data = "Unable to adjust the deposit amount!!";
                    outcome = true;
                    message = "Unable to post the deposit!!";
                }
            }
            else{
                data = "It has already been posted!!";
                outcome = true;
                message = "It has already been posted!!";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ServiceOutcome<>(data, outcome, message);
    }

//    @Override
//    public ServiceOutcome<String> cob835DepositAmountAdjustment(DepositAmountAdjustmentInputParameters depositAmountAdjustmentInputParameters){
//        AtomicReference<String> data = new AtomicReference<>("");
//        AtomicReference<Boolean> outcome = new AtomicReference<>(false);
//        AtomicReference<String> message = new AtomicReference<>("");
//        AtomicReference<Double> unprocessedPaymentAmount = new AtomicReference<>(0.00);
//
//        List<ClaimsCOB835Master> claimsCOB835MasterList = claimsCOB835MasterRepository.getClaimTransactionMasterOnEFTTraceNumberAndDate(depositAmountAdjustmentInputParameters.getCheckOrEFTTraceNumber(),
//            LocalDate.parse(depositAmountAdjustmentInputParameters.getCheckIssueOrEFTEffectiveDate(), dtf));
//
//
//        return null;
//    }

//    @Override
//    public ServiceOutcome<InvoiceItemsDetailPostingMain> getInvoicePostingDetailsOnInvoice(String invoiceNo){
//        List<InvoiceItemsDetailPosting> invoiceItemsDetailPostingList = new ArrayList<>();
//        InvoiceItemsDetailPostingMain invoiceItemsDetailPostingMain = new InvoiceItemsDetailPostingMain();
//        List<String> hcpcsCodes = invoicePostingDetailsRepository.getItemListOnInvoiceNo(invoiceNo);
//        final double[] totalChargeAmount = {0.00};
//        final double[] totalAllowedAmount = {0.00};
//        final double[] totalPaymentAmount = {0.00};
//        final double[] totalAdjustmentAmount = {0.00};
//        AtomicReference<Double> totalBalanceAmount = new AtomicReference<>(0.00);
//        if(hcpcsCodes.size() > 0){
//            hcpcsCodes.stream()
//                .map(hcpcs -> {
//                    log.info("========================================START================================================");
//                    log.info("====>"+hcpcs);
//
//                    InvoiceItemsDetailPosting invoiceItemsDetailPosting = new InvoiceItemsDetailPosting();
//                    InvoiceLineItemDetails invoiceLineItemDetails = invoiceLineItemDetailsRepository.getInvoiceLineItemDetailsOnInvoiceNoAndProcCode(invoiceNo, hcpcs);
//                    if(invoiceLineItemDetails != null){
//                        InvoiceItemHeaderValue invoiceItemHeaderValue = new InvoiceItemHeaderValue();
//                        invoiceItemHeaderValue.setQuantity(invoiceLineItemDetails.getItemQty().toString());
//                        invoiceItemHeaderValue.setProcedureCode(invoiceLineItemDetails.getHcpcsCode());
//                        invoiceItemHeaderValue.setItemName(invoiceLineItemDetails.getItemName());
//                        invoiceItemHeaderValue.setSaleType("");
//                        invoiceItemsDetailPosting.setInvoiceItemHeaderValue(invoiceItemHeaderValue);
//                    }
//                    return hcpcs;
//                });
//        }
//        return new ServiceOutcome<>(invoiceItemsDetailPostingMain, true, "Successfully prepare the invoice posting!!");
//    }
}
