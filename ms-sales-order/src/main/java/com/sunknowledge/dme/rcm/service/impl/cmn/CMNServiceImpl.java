package com.sunknowledge.dme.rcm.service.impl.cmn;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.amazon.BucketName;
import com.sunknowledge.dme.rcm.amazon.FileStore;
import com.sunknowledge.dme.rcm.application.applicationstatus.DefineStatus;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.documentutil.CloseCondition;
import com.sunknowledge.dme.rcm.documentutil.HeaderFooterPageEvent;
import com.sunknowledge.dme.rcm.documentutil.HeaderFooterPageEventForAWS;
import com.sunknowledge.dme.rcm.documentutil.OrderConfirmationTableBuilder;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.dto.cmn.CMNWrittenOrderOutputDTO;
import com.sunknowledge.dme.rcm.dto.cmn.CmnDataDocumentDetails;
import com.sunknowledge.dme.rcm.dto.cmn.CmnFaxDetails;
import com.sunknowledge.dme.rcm.dto.cmn.CmnResponseDetails;
import com.sunknowledge.dme.rcm.dto.cmn.CmnResponseDocumentDetails;
import com.sunknowledge.dme.rcm.dto.cmn.CmnSearchResponse;
import com.sunknowledge.dme.rcm.dto.cmn.Diagnosis;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsForCMNDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SearchCMNInputParameters;
import com.sunknowledge.dme.rcm.repository.cmn.CmnDocumentMasterRepo;
import com.sunknowledge.dme.rcm.repository.cmn.CmnRepo;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderItemDetailsRepo;
import com.sunknowledge.dme.rcm.service.cmn.CMNService;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnDocumentMasterMapper;
import com.sunknowledge.dme.rcm.service.mapper.CmnMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class CMNServiceImpl implements CMNService {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.getDefault());
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;
    @Autowired
    private CmnRepo cmnRepository;
    @Autowired
    private CmnMapper cmnMapper;
    @Autowired
    private CmnDocumentMasterMapper cmnDocumentMasterMapper;
    @Autowired
    private CmnDocumentMasterRepo cmnDocumentMasterRepository;
    @Autowired
    private SalesOrderMasterRepo salesOrderMasterRepository;
    @Autowired
    private SalesOrderItemDetailsRepo salesOrderItemDetailsRepository;
    @Autowired
    FileStore fileStore;

    @Override
    public Mono<ServiceOutcome<CmnResponseDetails>> prepareAndPrintCMNReportOnCmn(Long cmnId) {
        ServiceOutcome<CmnResponseDetails> serviceOutcome = new ServiceOutcome<>();
        try {
            CmnDTO cmnDTO = cmnMapper.toDto(cmnRepository.findById(cmnId).toFuture().get());
            log.info("==========1. cmnDTO========="+cmnDTO);
            SWODataDTO swoDataDTO = getSWODataOnSalesOrder(cmnDTO.getSalesOrderId());
            log.info("==========2. swoDataDTO==========="+swoDataDTO);
            String fileName = cmnDTO.getCmnNumber() + ".pdf";
            new CommonPDFStubs().generateQRCode(cmnDTO.getCmnNumber(), fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation());

            Document document = new Document(PageSize.A4, 35, 35, 50, 65);
            CmnDocumentMasterDTO cmnDocumentMasterDTO = saveCmnDocument(cmnDTO, fileName, "First");
            CmnResponseDetails updateCMNDetails = updateCMNDetails(cmnDTO, "PRINT", fileName);
            log.info("==========updateCMNDetails========="+updateCMNDetails);
            updateCMNDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation() + "/" + fileName));
            Rectangle rect = new Rectangle(70, 20, 480, 810);
            writer.setBoxSize("cmn", rect);
            HeaderFooterPageEvent event = new HeaderFooterPageEvent(fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation() + "/" + cmnDTO.getCmnNumber() + ".png");
            writer.setPageEvent(event);
            document.open();

            if(swoDataDTO != null) {
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyTitle());
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyProviderPatientDetails(swoDataDTO, cmnDocumentMasterDTO));

                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianOrderHeader());
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianDetailsFirst(swoDataDTO, cmnDTO));
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianDetailsSecond(swoDataDTO, cmnDTO));

                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosis());
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosisHead());
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosisHeadData(swoDataDTO));

                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipment());
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipmentTitle());

                List<EquipmentDetailsDTO> equipmentDetailsDTO = getEquipmentDetailsOnSalesOrder(cmnDTO.getSalesOrderId()); // Problem Is Here
                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipmentData(equipmentDetailsDTO, writer, 1));

                String docInfo = "Dear Physician,\n" +
                    "This information was provided to our office as part of the order intake process. Please review, correct, " +
                    "and provide additional. information If required. Please sign, and mail/fax the reviewed form back to our office.\n" +
                    "Thank you\n";
                Chunk chunk = new Chunk(docInfo);
                Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                Paragraph paragraph = new Paragraph(String.valueOf(chunk), font);
                paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);

                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainWofHeader());
                document.add(paragraph);

                document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainAdditionalNoteHeader());
                document.add(new Paragraph("\n\n\n\n"));
                OrderConfirmationTableBuilder.createOrderConfirmationMainTitleSignature(document, writer, swoDataDTO);
            }

            serviceOutcome.setData(updateCMNDetails);
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("Success");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.justOrEmpty(serviceOutcome);
    }

    @Override
    public SWODataDTO getSWODataOnSalesOrder(Long salesOrderId) throws Exception {
        Mono<SWODataDTO> swoDataDTO = cmnRepository.getSWODataOnSalesOrder(salesOrderId);
        return swoDataDTO.toFuture().get();
    }

    @Override
    public List<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(Long salesOrderId) throws Exception {
        Flux<EquipmentDetailsDTO> equipmentDetailsDTO = cmnRepository.getEquipmentDetailsOnSalesOrder(salesOrderId);
        return equipmentDetailsDTO.collectList().toFuture().get();
    }

    @Override
    public CmnDTO generateCmnOnSalesOrder(SWODataDTO swoDataDTO) throws Exception {
        CmnDTO cmnDTO;
        Cmn cmn = cmnRepository.getCmnDetailsOnSalesOrder(swoDataDTO.getSales_order_id()).toFuture().get();
        if (cmn != null) {
            cmn.setUpdatedById(0L);
            cmn.setUpdatedByName("System");
            cmn.setUpdatedDate(LocalDate.now());
        } else {
            cmn = new Cmn();
            cmn.setCmnNumber(cmnRepository.getCmnNumberSequence().toFuture().get());
            cmn.setCmnType("Generic/Generated");
            cmn.setCmnFormName("Generic/Initial");
            cmn.setPatientId(swoDataDTO.getPatient_id());
            cmn.setSalesOrderId(swoDataDTO.getSales_order_id());
            cmn.salesOrderNo(swoDataDTO.getSales_order_no());
            cmn.setCreatedById(0L);
            cmn.setCreatedByName("System");
            cmn.setCreatedDate(LocalDate.now());
            cmn.setStatus(DefineStatus.Active.name());
        }
        cmn = cmnRepository.save(cmn).toFuture().get();
        cmnDTO = cmnMapper.toDto(cmn);
        return cmnDTO;
    }

    @Override
    public CmnDocumentMasterDTO saveCmnDocument(CmnDTO cmnDTO, String fileName, String updateType) throws Exception {
        CmnDocumentMasterDTO cmnDocumentMasterDTO;
        CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnDTO.getCmnId()).toFuture().get();
        log.info("========File Name>>>>>>><<<<<<<<<<============>>>" + fileName);
        if (cmnDocumentMaster != null) {
            cmnDocumentMaster.setUpdatedById(1L);
            cmnDocumentMaster.setUpdatedByName("Bimal");
            cmnDocumentMaster.setUpdatedDate(LocalDate.now());
            if (updateType.equals("External/Uploaded"))
                cmnDocumentMaster.setInitialDocumentName(fileName);
            else
                cmnDocumentMaster.setInitialDocumentName(null);
            if (updateType.equals("Generic/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + "_in.pdf");
            }
            if (updateType.equals("External/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + ".pdf");
            }
        } else {
            cmnDocumentMaster = new CmnDocumentMaster();
            cmnDocumentMaster.setCmnId(cmnDTO.getCmnId());
            cmnDocumentMaster.setCmnNo(cmnDTO.getCmnNumber());
            cmnDocumentMaster.setGenerationDate(LocalDate.now());
            cmnDocumentMaster.setInitialDocumentName(fileName);
            cmnDocumentMaster.setCreatedById(0L);
            cmnDocumentMaster.setCreatedByName("System");
            cmnDocumentMaster.setCreatedDate(LocalDate.now());
            cmnDocumentMaster.setStatus(DefineStatus.Active.name());
            if (updateType.equals("External/Uploaded") || updateType.equals("Generic/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + "_in.pdf");
            }
        }
        cmnDocumentMaster = cmnDocumentMasterRepository.save(cmnDocumentMaster).toFuture().get();
        cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);
        return cmnDocumentMasterDTO;
    }

    @Override
    public ServiceOutcome<CmnResponseDetails> uploadAndSaveCMNDocumentFile(Long salesOrderId, String fileName) throws Exception {
        Cmn cmn = cmnRepository.getCmnDetailsOnSalesOrder(salesOrderId).toFuture().get();
        CmnDocumentMaster saveCmnDocumentMaster = new CmnDocumentMaster();
        CmnResponseDetails cmnResponseDetails = null;
        ServiceOutcome<CmnResponseDetails> serviceOutcome = new ServiceOutcome<>();
        if (cmn != null) {
            cmn.setUpdatedById(0L);
            cmn.setUpdatedByName("System");
            cmn.setUpdatedDate(LocalDate.now());
            Cmn cmnData = cmnRepository.save(cmn).toFuture().get();
            if (cmnData != null) {
                CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnData.getCmnId()).toFuture().get();
                if (cmnDocumentMaster != null) {
                    saveCmnDocumentMaster = cmnDocumentMaster;
                } else {
                    saveCmnDocumentMaster.setCmnNo(cmn.getCmnNumber());
                    saveCmnDocumentMaster.setGenerationDate(LocalDate.now());
                    saveCmnDocumentMaster = cmnDocumentMasterRepository.save(saveCmnDocumentMaster).toFuture().get();
                }
            }
        } else {
            System.out.println("============CREATE NEW CMN AND DOC=======================");
            SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrder(salesOrderId).toFuture().get();
            Cmn saveCmn = new Cmn();
            saveCmn.setCmnNumber(cmnRepository.getCmnNumberSequence().toFuture().get().toString());
            saveCmn.setCmnType("Generic/Uploaded");
            saveCmn.setCmnFormName("Generic/Initial");
            saveCmn.setPatientId(salesOrderMaster.getPatientId());
            saveCmn.setSalesOrderId(salesOrderId);
            saveCmn.salesOrderNo(salesOrderMaster.getSalesOrderNo());
            saveCmn.setCreatedById(0L);
            saveCmn.setCreatedByName("System");
            saveCmn.setCreatedDate(LocalDate.now());
            saveCmn.setStatus(DefineStatus.Active.name());
            saveCmn = cmnRepository.save(saveCmn).toFuture().get();
            if (saveCmn != null) {
                log.info("<============To Save The Document=======================>");
                saveCmnDocumentMaster.setCmnId(saveCmn.getCmnId());
                saveCmnDocumentMaster.setCmnNo(saveCmn.getCmnNumber());
                saveCmnDocumentMaster.setGenerationDate(LocalDate.now());
                saveCmnDocumentMaster.setInitialDocumentName(fileName);
                saveCmnDocumentMaster = cmnDocumentMasterRepository.save(saveCmnDocumentMaster).toFuture().get();
            }
        }
        CmnDTO cmnDTO = cmnMapper.toDto(cmn);
        CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(saveCmnDocumentMaster);
        cmnResponseDetails = new CmnResponseDetails();
        cmnResponseDetails.setCmnDTO(cmnDTO);
        cmnResponseDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);

        serviceOutcome.setData(cmnResponseDetails);
        serviceOutcome.setOutcome(true);
        serviceOutcome.setMessage("Success");
        return serviceOutcome;
    }

    @Override
    public Mono<Void> saveUploadCmnDocument1(Mono<FilePart> filePartMono, Long salesOrderId) {
        System.out.println("===========>ID====>" + salesOrderId);
        final Path tempPath = Paths.get(fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation());
        return filePartMono
            .doOnNext(fp -> System.out.println(fp.filename()))
            .flatMap(fp -> fp.transferTo(tempPath.resolve(fp.filename())))
            .then();
    }

    @Override
    public Mono<ServiceOutcome<String>> saveUploadedCmnDocument(FilePart filePart, Mono<CmnResponseDocumentDetails> cmnResponseDocumentDetails) throws ExecutionException, InterruptedException {
        final Path returnDocumentPath = Paths.get(fileUploadConfigProperties.getCmnReturnDocumentProperties().getLocation());
        final Path generatedDocumentPath = Paths.get(fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation());
        File filedir;
        Cmn cmn = cmnRepository.findById(cmnResponseDocumentDetails.toFuture().get().getCmnId()).toFuture().get();
        if (cmnResponseDocumentDetails.toFuture().get().getCmnDocumentType().equals("External/Uploaded"))
            filedir = new File(returnDocumentPath + "\\" + cmn.getCmnNumber() + "_in.pdf");
        else if (cmnResponseDocumentDetails.toFuture().get().getCmnDocumentType().equals("Generic/Uploaded"))
            filedir = new File(returnDocumentPath + "\\" + cmn.getCmnNumber() + "_in.pdf");
        else
            filedir = new File(generatedDocumentPath + "\\" + cmn.getCmnNumber() + ".pdf");

        // if a file with the same name already exists in a repository, delete and recreate it
        if (!filedir.exists()) {
            filedir.getParentFile().mkdir();
        }
        if (filedir.exists())
            filedir.delete();
        try {
            log.info("=======Create New File=========" + filedir.getAbsolutePath());
            filedir.createNewFile();
        } catch (IOException e) {
            return Mono.error(e); // if creating a new file fails return an error
        }
        try {
            // create an async file channel to store the file on disk
            final AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(filedir.toPath(), StandardOpenOption.WRITE);
            final CloseCondition closeCondition = new CloseCondition();

            // pointer to the end of file offset
            AtomicInteger fileWriteOffset = new AtomicInteger(0);
            // error signal
            AtomicBoolean errorFlag = new AtomicBoolean(false);

            ServiceOutcome<String> serviceOutcome = new ServiceOutcome<>();
            // FilePart.content produces a flux of data buffers, each need to be written to the file
            File finalFiledir = filedir;
            return filePart.content().doOnEach(dataBufferSignal -> {
                if (dataBufferSignal.hasValue() && !errorFlag.get()) {
                    // read data from the incoming data buffer into a file array
                    DataBuffer dataBuffer = dataBufferSignal.get();
                    int count = dataBuffer.readableByteCount();
                    byte[] bytes = new byte[count];
                    dataBuffer.read(bytes);

                    // create a file channel compatible byte buffer
                    final ByteBuffer byteBuffer = ByteBuffer.allocate(count);
                    byteBuffer.put(bytes);
                    byteBuffer.flip();

                    // get the current write offset and increment by the buffer size
                    final int filePartOffset = fileWriteOffset.getAndAdd(count);
                    // write the buffer to disk
                    closeCondition.onTaskSubmitted();
                    fileChannel.write(byteBuffer, filePartOffset, null, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            // file part successfuly written to disk, clean up
                            byteBuffer.clear();
                            if (closeCondition.onTaskCompleted())
                                try {
                                    System.out.println("closing after last part");
                                    fileChannel.close();
                                } catch (IOException ignored) {
                                    ignored.printStackTrace();
                                }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            // there as an error while writing to disk, set an error flag
                            errorFlag.set(true);
                        }
                    });
                }
            }).doOnComplete(() -> {
                // all done, close the file channel
                serviceOutcome.setOutcome((errorFlag.get() ? false : true));
                serviceOutcome.setMessage((errorFlag.get() ? "error" : "uploaded successfully..."));
                serviceOutcome.setData(finalFiledir.getName());
                if (closeCondition.canCloseOnComplete())
                    try {
                        fileChannel.close();
                    } catch (IOException ignored) {
                    }
            }).doOnError(t -> {
                serviceOutcome.setOutcome((errorFlag.get() ? false : true));
                serviceOutcome.setMessage((errorFlag.get() ? "error" : "Failed to uploaded the document..."));
                serviceOutcome.setData(null);
                // ooops there was an error
                try {
                    fileChannel.close();
                } catch (IOException ignored) {
                }
                // take last, map to a status string
            }).last().map(dataBuffer -> serviceOutcome);
        } catch (IOException e) {
            // unable to open the file channel, return an error
            return Mono.error(e);
        }
    }

    private Mono<String> saveCmnFile(FilePart filePart) {
        final Path tempPath = Paths.get(fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation());
        String filename = DigestUtils.sha256Hex(filePart.filename() + new Date());
        Path target = tempPath.resolve(filename);
        try {
            Files.deleteIfExists(target);
            File file = Files.createFile(target).toFile();
            return filePart
                .transferTo(file)
                .doOnSuccess(data -> log.info("do something..."))
                .thenReturn(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<ServiceOutcome<CmnResponseDetails>> documentFileOperation(ServiceOutcome<String> uploadOutcome, Mono<CmnResponseDocumentDetails> cmnResponseDocumentDetails) throws Exception {
        ServiceOutcome<CmnResponseDetails> serviceOutcome = null;
        final Path tempPath = Paths.get(fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation());
        final Path baseGeneratedPath = Paths.get(fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation());
        final Path baseReturnPath = Paths.get(fileUploadConfigProperties.getCmnReturnDocumentProperties().getLocation());
        if (uploadOutcome.getOutcome()) {
            File file;
            if (cmnResponseDocumentDetails.toFuture().get().getCmnDocumentType().equals("External/Uploaded") || cmnResponseDocumentDetails.toFuture().get().getCmnDocumentType().equals("Generic/Uploaded"))
                file = new File(baseReturnPath + "\\" + uploadOutcome.getData());
            else
                file = new File(baseGeneratedPath + "\\" + uploadOutcome.getData());

            String path = new CommonPDFStubs().generateQRCodeOnNewPage(cmnResponseDocumentDetails.toFuture().get().getCmnId().toString(), fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation(), 100, 100);
            log.info("=======Writing QR File To Uploaded File=========" + fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation());

            PDDocument document = PDDocument.load(file);
            String documentType;
            ServiceOutcome<String> outcome = new CommonPDFStubs().checkOwnOrOutsideDocument(document, tempPath);
            if (!outcome.getOutcome()) {
                log.info("====================>Outside Document====================>>>");
                new CommonPDFStubs().addQrcodePageToOutsideDocuments(document, path, file.getAbsolutePath());
                documentType = "External";
            } else {
                documentType = "Internal";
            }
            serviceOutcome = updateCMNResponseDetails(cmnResponseDocumentDetails.toFuture().get(), file.getName(), documentType);
        }
        return Mono.justOrEmpty(serviceOutcome);
    }

    public ServiceOutcome<CmnResponseDetails> updateCMNResponseDetails(CmnResponseDocumentDetails cmnResponseDocumentDetails, String fileName, String documentType) throws ExecutionException, InterruptedException {
        CmnResponseDetails cmnResponseDetails = null;
        Cmn cmn = cmnRepository.findById(cmnResponseDocumentDetails.getCmnId()).toFuture().get();
        if (cmn != null && cmnResponseDocumentDetails.getCmnDocumentType().equals("Generic/Uploaded")) {
            CmnDTO cmnDTO = cmnMapper.toDto(cmn);
            cmnDTO.setLengthOfNeed(cmnResponseDocumentDetails.getLengthOfNeed());
            cmnDTO.setRefillAuthorised(cmnResponseDocumentDetails.getRefillAuthorized());
            cmnResponseDetails = updateCMNDetails(cmnDTO, cmnResponseDocumentDetails.getCmnDocumentType(), fileName);
        }
        if (cmn != null && cmnResponseDocumentDetails.getCmnDocumentType().equals("External/Uploaded")) {
            CmnDTO cmnDTO = cmnMapper.toDto(cmn);
            cmnDTO.setLengthOfNeed(cmnResponseDocumentDetails.getLengthOfNeed());
            cmnDTO.setRefillAuthorised(cmnResponseDocumentDetails.getRefillAuthorized());
            cmnResponseDetails = updateCMNDetails(cmnDTO, cmnResponseDocumentDetails.getCmnDocumentType(), fileName);
        }
        return new ServiceOutcome<>(cmnResponseDetails, true, "Success");
    }

    public CmnResponseDetails updateCMNDetails(CmnDTO cmnDTO, String updateType, String fileName) {
        log.info("===================UPDATE CMN DETAILS===========================");
        Cmn cmn;
        CmnDocumentMasterDTO cmnDocumentMasterDTO = null;
        CmnResponseDetails cmnResponseDetails = new CmnResponseDetails();
        try {
            if (updateType.equals("PRINT")) {
                cmnDTO.setPrintCmnOption("printed");
                cmnDTO.setCmnPrintedBy(1L);
                cmnDTO.setCmnPrintedDate(LocalDate.now());
            }
            if (updateType.equals("External/Uploaded")) {
                cmnDTO.setCmnType("External/Uploaded");
                cmnDocumentMasterDTO = saveCmnDocument(cmnDTO, fileName, updateType);
            }
            if (updateType.equals("Generic/Uploaded")) {
                cmnDocumentMasterDTO = saveCmnDocument(cmnDTO, fileName, updateType);
            }
            cmn = cmnRepository.save(cmnMapper.toEntity(cmnDTO)).toFuture().get();
            cmnDTO = cmnMapper.toDto(cmn);
            cmnResponseDetails.setCmnDTO(cmnDTO);
            cmnResponseDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);
            log.info("=======>>>CMN ID========>" + cmn.getCmnId() + "<====>" + cmn.getCmnNumber());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cmnResponseDetails;
    }

    @Override
    public Mono<CmnResponseDocumentDetails> convertParameters(Long cmnId, String lengthOfNeed, String refillAuthorized, String frequencyCount, String frequencyInterval, String cmnDocumentType) {
        CmnResponseDocumentDetails cmnResponseDocumentDetails = new CmnResponseDocumentDetails();
        cmnResponseDocumentDetails.setCmnId(cmnId);
        cmnResponseDocumentDetails.setFrequencyCount(frequencyCount);
        cmnResponseDocumentDetails.setFrequencyInterval(frequencyInterval);
        cmnResponseDocumentDetails.setRefillAuthorized(refillAuthorized);
        cmnResponseDocumentDetails.setLengthOfNeed(lengthOfNeed);
        cmnResponseDocumentDetails.setCmnDocumentType(cmnDocumentType);
        return Mono.justOrEmpty(cmnResponseDocumentDetails);
    }

    @Override
    public Mono<ServiceOutcome<CmnSearchResponse>> searchActiveCMNForSalesOrder(SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        Mono<ServiceOutcome<CmnSearchResponse>> outcomeMono;
        CmnSearchResponse cmnSearchResponse = cmnRepository.searchActiveCMNForSalesOrder(searchCMNInputParameters.getPatientIdNo(), searchCMNInputParameters.getItemNo(), searchCMNInputParameters.getDos(), searchCMNInputParameters.getOrderingProviderNpi()).toFuture().get();
        if (cmnSearchResponse != null) {
            log.info("===============<<<Active IF>>>>>==========" + cmnSearchResponse.getCmnformname());
            log.info("===============<<<Active IF>>>>>==========" + cmnSearchResponse.getCmnnumber());
            return Mono.justOrEmpty(new ServiceOutcome<>(cmnSearchResponse, true, "Active CMN exists and fetched successfully!"));
        } else {
            log.info("===============<<<Active ELSE>>>>>==========");
            outcomeMono = searchInitiatedCMNForSalesOrder(searchCMNInputParameters);
        }
        return outcomeMono;
    }

    @Override
    public Mono<ServiceOutcome<CmnSearchResponse>> searchInitiatedCMNForSalesOrder(SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        String cmnNumber = cmnRepository.searchInitiatedCMNForSalesOrder(searchCMNInputParameters.getSalesOrderNo()).toFuture().get();
        String message;
        CmnSearchResponse cmnSearchResponse;
        if (cmnNumber != null) {
            log.info("================>>>IF<<<<======Go with Existing CMN========" + cmnNumber);
            cmnSearchResponse = useExistingCMN(cmnNumber, searchCMNInputParameters);
            message = "Initiated CMN is fetched successfully!";
        } else {
            log.info("================>>>ELSE<<<<=====Create New CMN=========");
            CmnDTO cmnDTO = createNewCMN(searchCMNInputParameters);
            cmnSearchResponse = setupCmnSearchResponse(cmnDTO);
            message = "CMN is created successfully!";
        }
        return Mono.justOrEmpty(new ServiceOutcome<>(cmnSearchResponse, true, message));
    }

    public CmnSearchResponse useExistingCMN(String cmnNumber, SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        Cmn cmn = cmnRepository.getCmnDetailsOnCmnNumber(cmnNumber).toFuture().get();
        updateSalesOrderItemDetailsReference(cmn, searchCMNInputParameters.getItemNo());
        CmnSearchResponse cmnSearchResponse = setupCmnSearchResponse(cmnMapper.toDto(cmn));
        return cmnSearchResponse;
    }

    public CmnSearchResponse setupCmnSearchResponse(CmnDTO cmn) throws ExecutionException, InterruptedException {
        CmnSearchResponse cmnSearchResponse = new CmnSearchResponse();
        cmnSearchResponse.setSalesorderno(cmn.getSalesOrderNo());
        cmnSearchResponse.setCmnid(cmn.getCmnId());
        cmnSearchResponse.setCmnnumber(cmn.getCmnNumber());
        cmnSearchResponse.setCmntype(cmn.getCmnType());
        cmnSearchResponse.setCmnformname(cmn.getCmnFormName());
        cmnSearchResponse.setPatientid(cmn.getPatientId());
        cmnSearchResponse.setSalesorderid(cmn.getSalesOrderId());
        cmnSearchResponse.setSalesorderno(cmn.getSalesOrderNo());
        cmnSearchResponse.setCmncreatedate(cmn.getCmnCreateDate());
        cmnSearchResponse.setCmninitialdate(cmn.getCmnInitialDate());
        cmnSearchResponse.setCmnexpirationdate(cmn.getCmnExpirationDate());
        cmnSearchResponse.setCmnlogged_date(cmn.getCmnLoggedDate());
        if (cmn.getCmnApprovedBy() != null)
            cmnSearchResponse.setCmnapproved_by(cmn.getCmnApprovedBy().toString());
        else
            cmnSearchResponse.setCmnapproved_by(null);
        cmnSearchResponse.setCmnapproved_date(cmn.getCmnApprovedDate());
        cmnSearchResponse.setLengthofneed(cmn.getLengthOfNeed());
        cmnSearchResponse.setCmniduuid(cmn.getCmnIdUuid());
        cmnSearchResponse.setCmnstatus(cmn.getCmnStatus());
        CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmn.getCmnId()).toFuture().get();
        if (cmnDocumentMaster != null) {
            cmnSearchResponse.setInitialdocumentname(cmnDocumentMaster.getInitialDocumentName());
            cmnSearchResponse.setReturndocumentname(cmnDocumentMaster.getReturnDocumentName());
        } else {
            cmnSearchResponse.setInitialdocumentname("NA");
            cmnSearchResponse.setReturndocumentname("NA");
        }
        SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrder(cmn.getSalesOrderId()).toFuture().get();
        cmnSearchResponse.setPatientfirstname(salesOrderMaster.getPatientFirstName());
        cmnSearchResponse.setPatientmiddlename(salesOrderMaster.getPatientMiddleName());
        cmnSearchResponse.setPatientlastname(salesOrderMaster.getPatientLastName());
        cmnSearchResponse.setDeliveryscheduledatetime(salesOrderMaster.getDeliveryScheduleDatetime());
        cmnSearchResponse.setDeliveryactualdatetime(salesOrderMaster.getDeliveryActualDatetime());
        cmnSearchResponse.setOrderstatus(salesOrderMaster.getOrderStatus());
        cmnSearchResponse.setSalesorderitems("");
        return cmnSearchResponse;
    }

    public void updateSalesOrderItemDetailsReference(Cmn cmn, String itemNumber) throws ExecutionException, InterruptedException {
        SalesOrderItemDetails salesOrderItemDetails =
            salesOrderItemDetailsRepository.getsalesOrderItemDetailsOnSalesOrderNItemNumber(cmn.getSalesOrderId(), itemNumber).toFuture().get();
        if (salesOrderItemDetails != null) {
            log.info("============Item Found==============");
            salesOrderItemDetails.setSalesOrderDetailsCmnparCmnKey(cmn.getCmnNumber());
            salesOrderItemDetailsRepository.save(salesOrderItemDetails).toFuture().get();
        } else {
            log.info("============No Item Found==============");
        }
    }

    public CmnDTO createNewCMN(SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.getSalesOrderDetailsOnSalesOrderNo(searchCMNInputParameters.getSalesOrderNo()).toFuture().get();
        CmnDTO cmnDTO = null;
        if (salesOrderMaster != null) {
            System.out.println("============CREATE NEW CMN=======================");
            Cmn saveCmn = new Cmn();
            saveCmn.setCmnNumber(cmnRepository.getCmnNumberSequence().toFuture().get());
            saveCmn.setCmnType("Generic/Generated");
            saveCmn.setCmnFormName("Generic/Initial");
            saveCmn.setPatientId(salesOrderMaster.getPatientId());
            saveCmn.setSalesOrderId(salesOrderMaster.getSalesOrderId());
            saveCmn.salesOrderNo(salesOrderMaster.getSalesOrderNo());
            saveCmn.setCreatedById(0L);
            saveCmn.setCreatedByName("System");
            saveCmn.setCreatedDate(LocalDate.now());
            saveCmn.setStatus(DefineStatus.Active.name());
            saveCmn.setCmnStatus("initiated");
            saveCmn = cmnRepository.save(saveCmn).toFuture().get();
            cmnDTO = cmnMapper.toDto(saveCmn);
            if (saveCmn != null) {
                cmnDTO = cmnMapper.toDto(saveCmn);
                updateSalesOrderItemDetailsReference(saveCmn, searchCMNInputParameters.getItemNo());
            }
        }
        return cmnDTO;
    }

    @Override
    public ServiceOutcome<CmnDataDocumentDetails> fetchToVerifyCMNData(Long cmnId) throws ExecutionException, InterruptedException {
        Cmn cmn = cmnRepository.findById(cmnId).toFuture().get();
        CmnDataDocumentDetails cmnDataDocumentDetails = null;
        if (cmn != null) {
            cmnDataDocumentDetails = setupCmnDataToCmnDataDocumentDetails(cmn);
        }
        return new ServiceOutcome<>(cmnDataDocumentDetails, true, "Cmn data fetched successfully!!!");
    }

    public CmnDataDocumentDetails setupCmnDataToCmnDataDocumentDetails(Cmn cmn) throws ExecutionException, InterruptedException {
        CmnDataDocumentDetails cmnDataDocumentDetails = new CmnDataDocumentDetails();
        cmnDataDocumentDetails.setCmnId(cmn.getCmnId());
        cmnDataDocumentDetails.setCmnNumber(cmn.getCmnNumber());
        cmnDataDocumentDetails.setCmnType(cmn.getCmnType());
        cmnDataDocumentDetails.setCmnFormName(cmn.getCmnFormName());
        cmnDataDocumentDetails.setPatientId(cmn.getPatientId());
        cmnDataDocumentDetails.setSalesOrderId(cmn.getSalesOrderId());
        cmnDataDocumentDetails.setSalesOrderNo(cmn.getSalesOrderNo());
        cmnDataDocumentDetails.setCmnCreateDate(cmn.getCmnCreateDate());
        cmnDataDocumentDetails.setCmnInitialDate(cmn.getCmnInitialDate());
        cmnDataDocumentDetails.setCmnRevisionDate(cmn.getCmnRevisionDate());
        cmnDataDocumentDetails.setCmnRecertificationDate(cmn.getCmnRecertificationDate());
        cmnDataDocumentDetails.setCmnExpirationDate(cmn.getCmnExpirationDate());
        cmnDataDocumentDetails.setCmnLoggedBy(cmn.getCmnLoggedBy());
        cmnDataDocumentDetails.setCmnLoggedDate(cmn.getCmnLoggedDate());
        cmnDataDocumentDetails.setCmnApprovedBy(cmn.getCmnApprovedBy());
        cmnDataDocumentDetails.setCmnApprovedDate(cmn.getCmnApprovedDate());
        cmnDataDocumentDetails.setCmnPrintedBy(cmn.getCmnPrintedBy());
        cmnDataDocumentDetails.setCmnPrintedDate(cmn.getCmnPrintedDate());
        cmnDataDocumentDetails.setLengthOfNeed(cmn.getLengthOfNeed());
        cmnDataDocumentDetails.setFaxCmnOption(cmn.getFaxCmnOption());
        cmnDataDocumentDetails.setCmnCoverLetterInclusionOption(cmn.getCmnCoverLetterInclusionOption());
        cmnDataDocumentDetails.setCmnFaxedBy(cmn.getCmnFaxedBy());
        cmnDataDocumentDetails.setCmnFaxedDate(cmn.getCmnFaxedDate());
        cmnDataDocumentDetails.setFaxStatus(cmn.getFaxStatus());
        cmnDataDocumentDetails.setFaxStatusReason(cmn.getFaxStatusReason());
        cmnDataDocumentDetails.setPrintCmnOption(cmn.getPrintCmnOption());
        cmnDataDocumentDetails.setCmnIdUuid(cmn.getCmnIdUuid());
        cmnDataDocumentDetails.setPatientPrognosis(cmn.getPatientPrognosis());
        cmnDataDocumentDetails.setCmnStatus(cmn.getCmnStatus());
        cmnDataDocumentDetails.setRefillAuthorised(cmn.getRefillAuthorised());
        CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmn.getCmnId()).toFuture().get();
        if (cmnDocumentMaster != null) {
            cmnDataDocumentDetails.setCmnDocumentId(cmnDocumentMaster.getCmnDocumentId());
            cmnDataDocumentDetails.setGenerationDate(cmnDocumentMaster.getGenerationDate());
            cmnDataDocumentDetails.setInitialDocumentName(cmnDocumentMaster.getInitialDocumentName());
            cmnDataDocumentDetails.setOutBoundFaxStatus(cmnDocumentMaster.getOutBoundFaxStatus());
            cmnDataDocumentDetails.setOutBoundFaxNo(cmnDocumentMaster.getOutBoundFaxNo());
            cmnDataDocumentDetails.setOutBoundFaxDateTime(cmnDocumentMaster.getOutBoundFaxDateTime());
            cmnDataDocumentDetails.setInBoundFaxStatus(cmnDocumentMaster.getInBoundFaxStatus());
            cmnDataDocumentDetails.setInBoundFaxNo(cmnDocumentMaster.getInBoundFaxNo());
            cmnDataDocumentDetails.setInBoundFaxDateTime(cmnDocumentMaster.getInBoundFaxDateTime());
            cmnDataDocumentDetails.setCmnDocumentMasterUuid(cmnDocumentMaster.getCmnDocumentMasterUuid());
            cmnDataDocumentDetails.setReturnDocumentName(cmnDocumentMaster.getReturnDocumentName());
            cmnDataDocumentDetails.setCmnComments(cmnDocumentMaster.getCmnComments());
        }
        return cmnDataDocumentDetails;
    }

    @Override
    public ServiceOutcome<CmnDataDocumentDetails> loggingCMN(Long cmnId) throws ExecutionException, InterruptedException {
        Cmn cmn = cmnRepository.findById(cmnId).toFuture().get();
        CmnDataDocumentDetails cmnDataDocumentDetails = null;
        if (cmn != null) {
            cmn.setCmnStatus(DefineStatus.Active.name());
            cmn.setCmnLoggedBy(0L);
            cmn.setCmnLoggedDate(LocalDate.now());
            cmnRepository.save(cmn).toFuture().get();
            cmnDataDocumentDetails = setupCmnDataToCmnDataDocumentDetails(cmn);
        }
        return new ServiceOutcome<>(cmnDataDocumentDetails, true, "Cmn is logged successfully!!!");
    }

    @Override
    public ServiceOutcome<CmnFaxDetails> sendOrFaxCMNReport(CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException {
        Cmn cmn = cmnRepository.findById(cmnFaxDetails.getCmnId()).toFuture().get();
        if (cmn != null) {
            cmn.setCmnFaxedBy(0L);
            cmn.setCmnFaxedDate(LocalDate.now());
            cmn.setFaxCmnOption("");
            cmn = cmnRepository.save(cmn).toFuture().get();
            CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmn.getCmnId()).toFuture().get();
            if (cmnDocumentMaster != null) {
                cmnDocumentMaster.setOutBoundFaxNo(cmnFaxDetails.getOutBoundFaxNo());
                cmnDocumentMaster.setOutBoundFaxStatus(cmnFaxDetails.getOutBoundFaxStatus());
                cmnDocumentMaster.setOutBoundFaxDateTime(LocalDate.parse(cmnFaxDetails.getOutBoundFaxDateTime(), dateTimeFormatter));
                cmnDocumentMasterRepository.save(cmnDocumentMaster).toFuture().get();
            }
        }
        return new ServiceOutcome<>(cmnFaxDetails, true, "Cmn fax is send successfully!!!");
    }

    @Override
    public Mono<ServiceOutcome<CmnFaxDetails>> receiveFaxCmnResponseReport(CmnFaxDetails cmnFaxDetails) {
        return cmnRepository.findById(cmnFaxDetails.getCmnId()).map(Optional::of).defaultIfEmpty(Optional.empty())
            .flatMap(cmn -> {
                log.info("============================1.IS Present=========================>" + cmn.get().getCmnNumber());
                if (cmn.isPresent()) {
                    log.info("============================2.IS Present=========================>" + cmn.get().getCmnId());
                    return cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmn.get().getCmnId()).map(Optional::of).defaultIfEmpty(Optional.empty())
                        .flatMap(cmnDocumentMaster -> {
                            if (cmnDocumentMaster.isPresent()) {
                                log.info("==============>" + cmnFaxDetails.getInBoundFaxNo());
                                cmnDocumentMaster.get().setInBoundFaxNo(cmnFaxDetails.getInBoundFaxNo());
                                log.info("==============>" + cmnDocumentMaster.get().getInBoundFaxNo());
                                cmnDocumentMaster.get().setInBoundFaxStatus(cmnFaxDetails.getInBoundFaxStatus());
                                cmnDocumentMaster.get().setInBoundFaxDateTime(LocalDate.parse(cmnFaxDetails.getInBoundFaxDateTime(), dateTimeFormatter));
                                cmnDocumentMaster.get().setCmnComments(cmnFaxDetails.getCmnComments());
                                cmnDocumentMasterRepository.save(cmnDocumentMaster.get()).log().subscribe();
                            }
                            return Mono.justOrEmpty(new ServiceOutcome<>(cmnFaxDetails, false, "Cmn is already been logged..s!!!"));
                        });
                }
                return Mono.justOrEmpty(new ServiceOutcome<>(cmnFaxDetails, false, "Cmn is already been logged!!!"));
            });
    }

    @Override
    public Flux<DataBuffer> loadFileAsCmnDocumentResource(String filename, String filetype) {
        try {
            String path = null;
            if (filetype.equalsIgnoreCase("out"))
                path = fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation();
            if (filetype.equalsIgnoreCase("in"))
                path = fileUploadConfigProperties.getCmnReturnDocumentProperties().getLocation();
            Path filePath = Paths.get(path + "/" + filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096);
            } else {
                throw new MyFileNotFoundException("Could not read the file! " + filename);
            }
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Could not read the file! " + filename);
        }
    }

    @Override
    public Mono<String> deleteTmpCreatedFiles(Long salesOrderId) {
        final Path tempPath = Paths.get(fileUploadConfigProperties.getCmnTempQrcodeProperties().getLocation() + "\\" + salesOrderId);
        try {
            CommonPDFStubs.deleteDirectory(new File(String.valueOf(tempPath.toAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just("DELETED");
    }

    @Override
    public Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataOut(Long cmnId) {
        ServiceOutcome<CMNWrittenOrderOutputDTO> serviceOutcome = new ServiceOutcome<>();
        try {
            CmnDTO cmnDTO = cmnMapper.toDto(cmnRepository.findById(cmnId).toFuture().get());
            SWODataDTO swoDataDTO = getSWODataOnSalesOrder(cmnDTO.getSalesOrderId());

            CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnDTO.getCmnId()).toFuture().get();
            System.out.println("===cmnDocumentMaster===" + cmnDocumentMaster);

            Long salesOrderId = cmnDTO.getSalesOrderId();

            CMNWrittenOrderOutputDTO getCMNDetails = getCMNDetails(swoDataDTO, cmnDocumentMaster, cmnId, salesOrderId);
            //updateCMNDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);
            System.out.println("===cmnDTO===" + cmnDTO);
            System.out.println("===swoDataDTO===" + swoDataDTO);
            //System.out.println("===cmnDocumentMasterDTO==="+cmnDocumentMasterDTO);
            //System.out.println("===updateCMNDetails==="+updateCMNDetails);
            System.out.println("===getCMNDetails===" + getCMNDetails);
            serviceOutcome.setData(getCMNDetails);
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.justOrEmpty(serviceOutcome);
    }

    public CMNWrittenOrderOutputDTO getCMNDetails(SWODataDTO swoDataDTO, CmnDocumentMaster cmnDocumentMaster, Long cmnId, Long salesOrderId) {
        log.info("===================Get CMN DETAILS===========================");
        CMNWrittenOrderOutputDTO cmnWrittenOrderOutputDTO = new CMNWrittenOrderOutputDTO();
        //CmnResponseDetails cmnResponseDetails = new CmnResponseDetails();
        //BeanUtils.copyProperties(swoDataDTO, cmnWrittenOrderOutputDTO);
        try {
            String branchFax = swoDataDTO.getBranch_fax() != null ? swoDataDTO.getBranch_fax() : "";
            String branchAddress1 = swoDataDTO.getBranch_address_line_1() != null ? swoDataDTO.getBranch_address_line_1() : "";
            String branchAddress2 = swoDataDTO.getBranch_address_line_2() != null ? swoDataDTO.getBranch_address_line_2() : "";
            String providerAddress = "";
            if (!branchAddress1.equals("")) {
                providerAddress = branchAddress1;
            }
            if (!branchAddress2.equals("")) {
                providerAddress = providerAddress + ", " + branchAddress2;
            }
            String branchCityStateZip = swoDataDTO.getBranch_city() + " " + swoDataDTO.getBranch_state() + " " + swoDataDTO.getBranch_zip_code();

            if (!branchCityStateZip.equals("")) {
                providerAddress = providerAddress + ", " + branchCityStateZip;
            }

            String patientAddress1 = swoDataDTO.getPatient_address_line_1() != null ? swoDataDTO.getPatient_address_line_1() : "";
            String patientAddress2 = swoDataDTO.getPatient_address_line_2() != null ? swoDataDTO.getPatient_address_line_2() : "";
            String patientAddress = "";
            if (!branchAddress1.equals("")) {
                patientAddress = patientAddress1;
            }
            if (!patientAddress2.equals("")) {
                patientAddress = patientAddress + ", " + patientAddress2;
            }
            String patientCityStateZip = swoDataDTO.getCity_name() + " " + swoDataDTO.getState_name() + " " + swoDataDTO.getZip_code();

            if (!patientCityStateZip.equals("")) {
                patientAddress = patientAddress + ", " + patientCityStateZip;
            }

            String physicianAddress1 = swoDataDTO.getOrdering_provider_address_line_1() != null ? swoDataDTO.getOrdering_provider_address_line_1() : "";
            String physicianAddress2 = swoDataDTO.getOrdering_provider_address_line_2() != null ? swoDataDTO.getOrdering_provider_address_line_2() : "";
            String physicianAddress = "";
            if (!physicianAddress1.equals("")) {
                physicianAddress = physicianAddress1;
            }
            if (!physicianAddress2.equals("")) {
                physicianAddress = physicianAddress + ", " + physicianAddress2;
            }
            String physicianCityStateZip = swoDataDTO.getOrdering_provider_city() + "," + swoDataDTO.getOrdering_provider_state() + " " + swoDataDTO.getOrdering_provider_zip();

            if (!physicianCityStateZip.equals("")) {
                physicianAddress = physicianAddress + ", " + physicianCityStateZip;
            }
            String physicianFax = swoDataDTO.getOrdering_provider_fax() != null ? swoDataDTO.getOrdering_provider_fax() : "";

            List<Diagnosis> diagnosisObj = new ArrayList<>();
            Diagnosis diagnosis = new Diagnosis();

            String icd10Code1 = null;
            String icd10Description1 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_1() != null && !swoDataDTO.getIcd_10_diagnosis_code_1().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_1().contains("||")) {
                String[] icd10CodeDescriptionSplitArr1 = swoDataDTO.getIcd_10_diagnosis_code_1().split("\\|\\|");
                icd10Code1 = icd10CodeDescriptionSplitArr1[0];
                icd10Description1 = icd10CodeDescriptionSplitArr1[1];
                diagnosis.setIcd10Code(icd10Code1);
                diagnosis.setDescription(icd10Description1);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code2 = null;
            String icd10Description2 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_2() != null && !swoDataDTO.getIcd_10_diagnosis_code_2().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_2().contains("||")) {
                String[] icd10CodeDescriptionSplitArr2 = swoDataDTO.getIcd_10_diagnosis_code_2().split("\\|\\|");
                icd10Code2 = icd10CodeDescriptionSplitArr2[0];
                icd10Description2 = icd10CodeDescriptionSplitArr2[1];

                diagnosis.setIcd10Code(icd10Code2);
                diagnosis.setDescription(icd10Description2);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code3 = null;
            String icd10Description3 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_3() != null && !swoDataDTO.getIcd_10_diagnosis_code_3().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_3().contains("||")) {
                String[] icd10CodeDescriptionSplitArr3 = swoDataDTO.getIcd_10_diagnosis_code_3().split("\\|\\|");
                icd10Code3 = icd10CodeDescriptionSplitArr3[0];
                icd10Description3 = icd10CodeDescriptionSplitArr3[1];

                diagnosis.setIcd10Code(icd10Code3);
                diagnosis.setDescription(icd10Description3);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code4 = null;
            String icd10Description4 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_4() != null && !swoDataDTO.getIcd_10_diagnosis_code_4().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_4().contains("||")) {
                String[] icd10CodeDescriptionSplitArr4 = swoDataDTO.getIcd_10_diagnosis_code_4().split("\\|\\|");
                icd10Code4 = icd10CodeDescriptionSplitArr4[0];
                icd10Description4 = icd10CodeDescriptionSplitArr4[1];

                diagnosis.setIcd10Code(icd10Code4);
                diagnosis.setDescription(icd10Description4);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code5 = null;
            String icd10Description5 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_5() != null && !swoDataDTO.getIcd_10_diagnosis_code_5().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_5().contains("||")) {
                String[] icd10CodeDescriptionSplitArr5 = swoDataDTO.getIcd_10_diagnosis_code_5().split("\\|\\|");
                icd10Code5 = icd10CodeDescriptionSplitArr5[0];
                icd10Description5 = icd10CodeDescriptionSplitArr5[1];

                diagnosis.setIcd10Code(icd10Code5);
                diagnosis.setDescription(icd10Description5);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code6 = null;
            String icd10Description6 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_6() != null && !swoDataDTO.getIcd_10_diagnosis_code_6().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_6().contains("||")) {
                String[] icd10CodeDescriptionSplitArr6 = swoDataDTO.getIcd_10_diagnosis_code_6().split("\\|\\|");
                icd10Code6 = icd10CodeDescriptionSplitArr6[0];
                icd10Description6 = icd10CodeDescriptionSplitArr6[1];

                diagnosis.setIcd10Code(icd10Code6);
                diagnosis.setDescription(icd10Description6);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code7 = null;
            String icd10Description7 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_7() != null && !swoDataDTO.getIcd_10_diagnosis_code_7().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_7().contains("||")) {
                String[] icd10CodeDescriptionSplitArr7 = swoDataDTO.getIcd_10_diagnosis_code_7().split("\\|\\|");
                icd10Code7 = icd10CodeDescriptionSplitArr7[0];
                icd10Description7 = icd10CodeDescriptionSplitArr7[1];

                diagnosis.setIcd10Code(icd10Code7);
                diagnosis.setDescription(icd10Description7);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code8 = null;
            String icd10Description8 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_8() != null && !swoDataDTO.getIcd_10_diagnosis_code_8().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_8().contains("||")) {
                String[] icd10CodeDescriptionSplitArr8 = swoDataDTO.getIcd_10_diagnosis_code_8().split("\\|\\|");
                icd10Code8 = icd10CodeDescriptionSplitArr8[0];
                icd10Description8 = icd10CodeDescriptionSplitArr8[1];

                diagnosis.setIcd10Code(icd10Code8);
                diagnosis.setDescription(icd10Description8);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code9 = null;
            String icd10Description9 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_9() != null && !swoDataDTO.getIcd_10_diagnosis_code_9().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_9().contains("||")) {
                String[] icd10CodeDescriptionSplitArr9 = swoDataDTO.getIcd_10_diagnosis_code_9().split("\\|\\|");
                icd10Code9 = icd10CodeDescriptionSplitArr9[0];
                icd10Description9 = icd10CodeDescriptionSplitArr9[1];

                diagnosis.setIcd10Code(icd10Code9);
                diagnosis.setDescription(icd10Description9);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code10 = null;
            String icd10Description10 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_10() != null && !swoDataDTO.getIcd_10_diagnosis_code_10().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_10().contains("||")) {
                String[] icd10CodeDescriptionSplitArr10 = swoDataDTO.getIcd_10_diagnosis_code_10().split("\\|\\|");
                icd10Code10 = icd10CodeDescriptionSplitArr10[0];
                icd10Description10 = icd10CodeDescriptionSplitArr10[1];

                diagnosis.setIcd10Code(icd10Code10);
                diagnosis.setDescription(icd10Description10);
                diagnosisObj.add(diagnosis);
            }

            String icd10Code11 = null;
            String icd10Description11 = null;
            if (swoDataDTO.getIcd_10_diagnosis_code_11() != null && !swoDataDTO.getIcd_10_diagnosis_code_11().trim().equals("") && swoDataDTO.getIcd_10_diagnosis_code_11().contains("||")) {
                String[] icd10CodeDescriptionSplitArr11 = swoDataDTO.getIcd_10_diagnosis_code_11().split("\\|\\|");
                icd10Code11 = icd10CodeDescriptionSplitArr11[0];
                icd10Description11 = icd10CodeDescriptionSplitArr11[1];

                diagnosis.setIcd10Code(icd10Code11);
                diagnosis.setDescription(icd10Description11);
                diagnosisObj.add(diagnosis);
            }

            List<EquipmentDetailsDTO> equipmentDetailsObj = getEquipmentDetailsOnSalesOrder(salesOrderId);
            EquipmentDetailsForCMNDTO equipmentDetailsForCMNDTO = new EquipmentDetailsForCMNDTO();
            log.info("======equipmentDetailsObj========" + equipmentDetailsObj);
            List<EquipmentDetailsForCMNDTO> modifiedEquipmentDetailsObj = new ArrayList<>();
            for (int i = 0; i < equipmentDetailsObj.size(); i++) {
                equipmentDetailsForCMNDTO.setItemId(String.valueOf(equipmentDetailsObj.get(i).getSales_order_item_details_id()));
                equipmentDetailsForCMNDTO.setQty(String.valueOf(equipmentDetailsObj.get(i).getSales_order_details_qty()));
                equipmentDetailsForCMNDTO.setProcCode(equipmentDetailsObj.get(i).getSales_order_details_proc_code());
                equipmentDetailsForCMNDTO.setItemName(equipmentDetailsObj.get(i).getSales_order_details_item_name());
                modifiedEquipmentDetailsObj.add(equipmentDetailsForCMNDTO);
            }
            cmnWrittenOrderOutputDTO.setCmnId(cmnId);
            cmnWrittenOrderOutputDTO.setProviderName(swoDataDTO.getBilling_branch_name().toUpperCase());
            cmnWrittenOrderOutputDTO.setProviderAddress(providerAddress);
            cmnWrittenOrderOutputDTO.setProviderPhone(swoDataDTO.getBranch_contact_no_1());
            cmnWrittenOrderOutputDTO.setProviderFax(branchFax);
            cmnWrittenOrderOutputDTO.setProviderDocId(cmnDocumentMaster.getCmnNo());
            cmnWrittenOrderOutputDTO.setPatientName(swoDataDTO.getPatient_first_name().toUpperCase() + " " + swoDataDTO.getPatient_middle_name() + " " + swoDataDTO.getPatient_last_name().toUpperCase());
            cmnWrittenOrderOutputDTO.setPatientAddress(patientCityStateZip);
            cmnWrittenOrderOutputDTO.setPatientPhone(swoDataDTO.getPatient_contact_no_1());
            cmnWrittenOrderOutputDTO.setPatientDob(swoDataDTO.getPatient_dob().toString());
            cmnWrittenOrderOutputDTO.setPatientPolicy(swoDataDTO.getPrimary_insurer_policy_no());
            cmnWrittenOrderOutputDTO.setPhysicianName(swoDataDTO.getOrdering_provider_first_name() + " " + swoDataDTO.getOrdering_provider_middle_name() + " " + swoDataDTO.getOrdering_provider_last_name());
            cmnWrittenOrderOutputDTO.setPhysicianAddress(physicianAddress);
            cmnWrittenOrderOutputDTO.setPhysicianLicense(swoDataDTO.getOrdering_provider_dea());
            cmnWrittenOrderOutputDTO.setPhysicianPhone(swoDataDTO.getOrdering_provider_contact_no_1());
            cmnWrittenOrderOutputDTO.setPhysicianNpi(swoDataDTO.getOrdering_provider_npi());
            cmnWrittenOrderOutputDTO.setPhysicianFax(physicianFax);
            cmnWrittenOrderOutputDTO.setOrderInitialDate("");
            cmnWrittenOrderOutputDTO.setOrderRevisedDate("");
            cmnWrittenOrderOutputDTO.setOrderRecertificationDate("");
            cmnWrittenOrderOutputDTO.setDiagnosis(diagnosisObj);
            cmnWrittenOrderOutputDTO.setEquipmentDetails(modifiedEquipmentDetailsObj);

            log.info("===cmnWrittenOrderOutputDTO===" + cmnWrittenOrderOutputDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cmnWrittenOrderOutputDTO;
    }

    @Override
    public Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataIn(CMNWrittenOrderOutputDTO cmnWrittenOrderOutputDTO) {
        ServiceOutcome<CMNWrittenOrderOutputDTO> serviceOutcome = new ServiceOutcome<>();
        try {
            Long cmnId = null;
            if (cmnWrittenOrderOutputDTO.getCmnId() != null && !cmnWrittenOrderOutputDTO.getCmnId().equals("")) {
                cmnId = cmnWrittenOrderOutputDTO.getCmnId();
            }
            CmnDTO cmnDTO = cmnMapper.toDto(cmnRepository.findById(cmnId).toFuture().get());
            Long salesOrderId = cmnDTO.getSalesOrderId();
            /*
            SWODataDTO swoDataDTO = getSWODataOnSalesOrder(cmnDTO.getSalesOrderId());

            CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnDTO.getCmnId()).toFuture().get();
            System.out.println("===cmnDocumentMaster==="+cmnDocumentMaster);
            */
            CMNWrittenOrderOutputDTO getInsertedCMNDetails = insertCMNDetails(cmnWrittenOrderOutputDTO, salesOrderId);
            System.out.println("===getInsertedCMNDetails===" + getInsertedCMNDetails);
            serviceOutcome.setData(getInsertedCMNDetails);
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.justOrEmpty(serviceOutcome);
    }

    public CMNWrittenOrderOutputDTO insertCMNDetails(CMNWrittenOrderOutputDTO cmnWrittenOrderOutputDTO, Long salesOrderId) {
        log.info("===================Inserted CMN DETAILS===========================");
        try {
            //Write Business Logic Here
            //orderLengthOfNeed, orderPrognosis, refillAuthorized, [count, interval (wrt itemDetailsId)] value which will come as input that should be updated
            log.info("===cmnWrittenOrderOutputDTO===" + cmnWrittenOrderOutputDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cmnWrittenOrderOutputDTO;
    }


    //============================= CMN Sub-routines For Sales Order Item Details Integration ==========
    // ====== By @ Abhijit Chowdhury
    //----- Search_CMN_For_Sales_Order -------------------
    public Mono<Cmn> searchCMNForSalesOrder(Long patientId, Long salesOrderId, String dos, String itemNo, String orderingProviderNpi) {
        LocalDate date = (dos == null || dos.trim().equals("")) ? LocalDate.now() : LocalDate.parse(dos);
        return cmnRepository.findCmnBySalesOrderIdAndPatientId(salesOrderId, patientId, date, itemNo, orderingProviderNpi);
    }

    //----- Create_CMN_For_Sales_Order -------------------
    @Override
    public Mono<Cmn> createCMNForSalesOrder(Cmn cmn) {
        return cmnRepository.getCmnNumberSequence().map(cmnNumber -> {
            cmn.setCmnNumber(cmnNumber);
            return cmn;
        }).flatMap(cmnRepository::save);
    }
    //============================= CMN Sub-routines For Sales Order Item Details Integration ==========

    @Override
    public Mono<ServiceOutcome<CmnResponseDetails>> prepareAndPrintCMNReportOnCmnForAwsS3(CmnDTO cmnDTO, SWODataDTO swoDataDTO, List<EquipmentDetailsDTO> equipmentDetailsDTO,
                                                                                          CmnDocumentMaster cmnDocumentMaster, CmnDocumentMasterDTO cmnDocumentMasterDTO,
                                                                                          String updateType) {
        ServiceOutcome<CmnResponseDetails> serviceOutcome = new ServiceOutcome<>();
        String eTag = "";
        try {
            log.info("==========1. cmnDTO========="+cmnDTO);
            log.info("==========2. swoDataDTO==========="+swoDataDTO);
            String fileName = cmnDTO.getCmnNumber() + ".pdf";
            String bucketName = BucketName.BUCKET_NAME.getSoServiceBucket(); // Replace with your S3 bucket name
            String s3Key = "cmnDocuments/" + fileName; // Specify the path in your S3 bucket

            CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
            byte[] qrCodeBytes = commonPDFStubs.generateQRCodeInAmazon(cmnDTO.getCmnNumber());

            Document document = new Document(PageSize.A4, 35, 35, 50, 65);
            //Mono<CmnDocumentMasterDTO> cmnDocumentMasterDTO = saveCmnDocumentInReactive(cmnDTO, fileName, "First", cmnDocumentMaster); //Need To be Reactive (Pending)

            //CmnResponseDetails updateCMNDetails = updateCMNDetails(cmnDTO, "PRINT", fileName);  // Need To Be Reactive (Pending)
            //log.info("==========updateCMNDetails========="+updateCMNDetails);
            //updateCMNDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);

            //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation() + "/" + fileName));
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                PdfWriter writer = PdfWriter.getInstance(document, outputStream);

                Rectangle rect = new Rectangle(70, 20, 480, 810);
                writer.setBoxSize("cmn", rect);
                HeaderFooterPageEventForAWS event = new HeaderFooterPageEventForAWS(qrCodeBytes);
                writer.setPageEvent(event);
                document.open();

                if(swoDataDTO != null) {
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyTitle());
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyProviderPatientDetails(swoDataDTO, cmnDocumentMasterDTO));

                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianOrderHeader());
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianDetailsFirst(swoDataDTO, cmnDTO));
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyPhysicianDetailsSecond(swoDataDTO, cmnDTO));

                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosis());
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosisHead());
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainBodyDiagnosisHeadData(swoDataDTO));

                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipment());
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipmentTitle());

                    //List<EquipmentDetailsDTO> equipmentDetailsDTO = getEquipmentDetailsOnSalesOrder(cmnDTO.getSalesOrderId()); // Need to be in Reactive Way
                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainTitleEquipmentData(equipmentDetailsDTO, writer, 1));

                    String docInfo = "Dear Physician,\n" +
                        "This information was provided to our office as part of the order intake process. Please review, correct, " +
                        "and provide additional. information If required. Please sign, and mail/fax the reviewed form back to our office.\n" +
                        "Thank you\n";
                    Chunk chunk = new Chunk(docInfo);
                    Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
                    Paragraph paragraph = new Paragraph(String.valueOf(chunk), font);
                    paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);

                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainWofHeader());
                    document.add(paragraph);

                    document.add(OrderConfirmationTableBuilder.createOrderConfirmationMainAdditionalNoteHeader());
                    document.add(new Paragraph("\n\n\n\n"));
                    OrderConfirmationTableBuilder.createOrderConfirmationMainTitleSignature(document, writer, swoDataDTO);

                    //PriorAuthReportBuilder.addQrCodeOnFooterInAwsBucket(document, qrCodeBytes, qrCodeImage);
                    //document.add(qrCodeImage);
                }

                CmnResponseDetails cmnResponseDetails = new CmnResponseDetails();
                if (updateType.equals("PRINT")) {
                    cmnDTO.setPrintCmnOption("printed");
                    cmnDTO.setCmnPrintedBy(1L);
                    cmnDTO.setCmnPrintedDate(LocalDate.now());
                }
                if (updateType.equals("External/Uploaded")) {
                    cmnDTO.setCmnType("External/Uploaded");
                    //cmnDocumentMasterDTO = saveCmnDocument(cmnDTO, fileName, updateType);
                }
                if (updateType.equals("Generic/Uploaded")) {
                    //cmnDocumentMasterDTO = saveCmnDocument(cmnDTO, fileName, updateType);
                }

                cmnRepository.save(cmnMapper.toEntity(cmnDTO)).map(cmnMapper::toDto); //Update t_cmn
                cmnResponseDetails.setCmnDTO(cmnDTO);
                cmnResponseDetails.setCmnDocumentMasterDTO(cmnDocumentMasterDTO);
                log.info("=======>>>CMN ID========>" + cmnDTO.getCmnId() + "<====>" + cmnDTO.getCmnNumber());

                serviceOutcome.setData(cmnResponseDetails);
                serviceOutcome.setOutcome(true);
                serviceOutcome.setMessage("Success");
                document.close();

                // Get the document bytes
                byte[] documentBytes = outputStream.toByteArray();
                // Upload the document to S3
                eTag = fileStore.uploadToS3(bucketName, s3Key, documentBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            // Handle IOException
            e.printStackTrace();
            return Mono.just(new ServiceOutcome<>(null, false, "Error generating Fax Cover Document."));
        }
        if(eTag!=null && !eTag.equals("")) {
            return Mono.justOrEmpty(serviceOutcome);
        }else{
            return Mono.just(new ServiceOutcome<>(null, false, "Error In generating CMN Document."));
        }
    }

    @Override
    public Mono<CmnDTO> getCMNMasterData(Long soId) {
        return cmnRepository.getCMNMasterData(soId).map(cmnMapper::toDto);
    }

    @Override
    public Mono<SWODataDTO> getSWODataOnSalesOrderReactive(Long salesOrderId) throws Exception {
        return cmnRepository.getSWODataOnSalesOrder(salesOrderId);
    }

    @Override
    public Mono<CmnDTO> getCMNMasterDataByCmnId(Long cmnId) {
        return cmnRepository.findById(cmnId).map(cmnMapper::toDto);
    }

    @Override
    public Flux<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrderReactive(Long salesOrderId) throws Exception {
        return cmnRepository.getEquipmentDetailsOnSalesOrder(salesOrderId);
    }

    @Override
    public Mono<CmnDocumentMaster> getCmnDocumentByCmnId(Long cmnId) throws Exception {
        return cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnId);
    }

    @Override
    public Mono<CmnDocumentMasterDTO> saveCmnDocumentInReactive(CmnDTO cmnDTO, String fileName, String updateType, CmnDocumentMaster cmnDocumentMaster) throws Exception {
        //CmnDocumentMasterDTO cmnDocumentMasterDTO;
        //CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentOnCmn(cmnDTO.getCmnId()).toFuture().get();
        log.info("========File Name>>>>>>><<<<<<<<<<============>>>" + fileName);
        if (cmnDocumentMaster != null) {
            cmnDocumentMaster.setUpdatedById(1L);
            cmnDocumentMaster.setUpdatedByName("Bimal");
            cmnDocumentMaster.setUpdatedDate(LocalDate.now());
            if (updateType.equals("External/Uploaded"))
                cmnDocumentMaster.setInitialDocumentName(fileName);
            else
                cmnDocumentMaster.setInitialDocumentName(null);
            if (updateType.equals("Generic/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + "_in.pdf");
            }
            if (updateType.equals("External/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + ".pdf");
            }
        } else {
            cmnDocumentMaster = new CmnDocumentMaster();
            cmnDocumentMaster.setCmnId(cmnDTO.getCmnId());
            cmnDocumentMaster.setCmnNo(cmnDTO.getCmnNumber());
            cmnDocumentMaster.setGenerationDate(LocalDate.now());
            cmnDocumentMaster.setInitialDocumentName(fileName);
            cmnDocumentMaster.setCreatedById(0L);
            cmnDocumentMaster.setCreatedByName("System");
            cmnDocumentMaster.setCreatedDate(LocalDate.now());
            cmnDocumentMaster.setStatus(DefineStatus.Active.name());
            if (updateType.equals("External/Uploaded") || updateType.equals("Generic/Uploaded")) {
                cmnDocumentMaster.setReturnDocumentName(fileName.split(".pdf")[0] + "_in.pdf");
            }
        }
        return cmnDocumentMasterRepository.save(cmnDocumentMaster).map(obj->{
            return cmnDocumentMasterMapper.toDto(obj);
        });
        //cmnDocumentMasterDTO = cmnDocumentMasterMapper.toDto(cmnDocumentMaster);
        //return cmnDocumentMasterDTO;
    }
}
