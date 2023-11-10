package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.google.zxing.WriterException;
import com.sunknowledge.dme.rcm.amazon.BucketName;
import com.sunknowledge.dme.rcm.amazon.FileStore;
import com.sunknowledge.dme.rcm.application.config.AmazonConfig;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.application.properties.FileUploadProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.repository.PatientDocumentEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDocumentEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDocumentEntryAggregate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service("patientDocumentEntryServiceExtendedImpl")
@Transactional
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class PatientDocumentEntryServiceExtendedImpl implements PatientDocumentEntryServiceExtended {
    private final Path basePath = Paths.get("./upload");

    @Autowired
    PatientDocumentEntryAggregate patientDocumentEntryAggregate;
    @Autowired
    PatientDocumentEntryRepositoryExtended patientDocumentEntryRepositoryExtended;
    @Autowired
    FileUploadConfigProperties fileUploadConfigProperties;
    @Autowired
    FileStore fileStore;
    @Autowired
    AmazonConfig amazonConfig;
    @Autowired
    AmazonS3Client amazonS3Client;
    @Autowired
    PatientDocumentMapper patientDocumentMapper;

//    @Override
//    public Mono<ResponseDTO> savePatientDocument(SavePatientDocumentDetailsCommand obj) {
//        return patientDocumentEntryAggregate.handleSavePatientDocumentInfoCommand(obj);
//    }

    @Override
    public Flux<PatientDocument> uploadPatientDocument(Flux<FilePart> patientFilePartFlux, String patientDocumentStatus, String description,
                                                   String documentType, PatientMasterDTO patientMasterDTO,List<String> documentNoList) {
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        log.info("===================uploadPatientDocument function called =================");
        //patientMasterDTO.subscribe(System.out::println).dispose();
        CommonUtilities commonUtilitiesObj = new CommonUtilities();
        Properties propData = null;
        try {
            propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/document_management.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean isCloudStorage = Boolean.parseBoolean(propData.getProperty("is_cloud_storage"));

        if (patientMasterDTO != null) {
            Long patientId = patientMasterDTO.getPatientId();
            String patientNo = patientMasterDTO.getPatientIdNumber();

            if (patientId != null && patientNo != null) {
                AtomicInteger counter = new AtomicInteger(-1);
                List<PatientDocument> patientDocuments = new ArrayList<>();
                return patientFilePartFlux.flatMapSequential(file-> {
                    return DataBufferUtils.join(file.content())
                        .map(dataBuffer -> {
                            System.out.println("==== Inside flatMapSequential =====");
                            InputStream targetStream = new ByteArrayInputStream(dataBuffer.asByteBuffer().array());
                            String fileName = String.format("%s", file.filename());
                            System.out.println("==== Inside flatMapSequential 2 =====");

                            counter.getAndIncrement();
                            System.out.println("==== Inside flatMapSequential Map ===== ");
                            try {
                                return saveUploadedDocumentDetailsCloud(documentNoList.get(counter.get()), documentType, description,
                                    patientDocumentStatus, patientMasterDTO,targetStream,isCloudStorage)
                                    .map(patientDocument ->{
                                        return patientDocument;
                                    }).toFuture().get();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        });
                });
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Flux<PatientDocument> uploadPatientDocumentByPath(String[] documentNameList, String patientDocumentStatus, String description,
                                                             String documentType, PatientMasterDTO patientMasterDTO, List<String> documentNoList, Boolean isCloudStorage, String operationType) {

        String patientDocumentFile = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();

        Flux<String> documentNameFlux = Mono.just(documentNameList).flatMapMany(Flux::fromArray);
        AtomicInteger counter = new AtomicInteger(-1);
        return documentNameFlux.flatMapSequential(name -> {
            System.out.println("name " + name);
            counter.getAndIncrement();
            String documentNoName = documentNoList.get(counter.get());
            InputStream inputStream = null;
            if(isCloudStorage) {
                String bucketName = null;
                if(operationType.equalsIgnoreCase("upload")){
                    bucketName = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "qrCode");
                }else{
                    bucketName = String.format("%s/%s", BucketName.BUCKET_NAME.getSoServiceBucket(), "basePatientDocuments");
                }
                S3Object s3object = amazonS3Client.getObject(bucketName, name + ".pdf");
                amazonS3Client.deleteObject(bucketName, name + ".pdf");
                inputStream = s3object.getObjectContent();
                System.out.println("==== Inside flatMapSequential Map ===== ");
            }else{
                String tempPatientDocumentFileUUID = patientDocumentFile + "/" + name + ".pdf";
                String tempPatientDocumentFile = patientDocumentFile + "/" + documentNoName + ".pdf";
                System.out.println("tempPatientDocumentFile " + tempPatientDocumentFile);
                new File(tempPatientDocumentFileUUID).renameTo(new File(patientDocumentFile + "/" + documentNoName + ".pdf"));
                File file = new File(tempPatientDocumentFile);
                try {
                    inputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            return saveUploadedDocumentDetailsCloud(documentNoName, documentType, description,
                patientDocumentStatus, patientMasterDTO, inputStream, isCloudStorage)
                .map(patientDocument -> {
                    return patientDocument;
                });

        });
    }

    @Override
    public Mono<String> generateDocumentNo(String documentType) {
        switch (documentType) {
            case "PDD": {
                System.out.println("Inside generateDocumentNo");
                return patientDocumentEntryRepositoryExtended.getPDDNumber();
            }
            case "PID": {
                return patientDocumentEntryRepositoryExtended.getPIDNumber();
            }
            case "PMR": {
                return patientDocumentEntryRepositoryExtended.getPMRNumber();
            }
            default: {
                return null;
            }
        }
    }

    public Mono<PatientDocument> saveUploadedDocumentDetailsCloud(String documentNo,String documentType,String description,
                                                                  String patientDocumentStatus,PatientMasterDTO patientMasterDTO,
                                                                  InputStream targetStream, boolean isCloudStorage) {
        System.out.println("Inside saveUploadedDocumentDetailsCloud");

        if (patientMasterDTO != null) {
            CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
            try {
                Long patientId = patientMasterDTO.getPatientId();
                String patientNo = patientMasterDTO.getPatientIdNumber();
                UUID patientUUID = patientMasterDTO.getPatientMasterUUID();
                PDDocument pdDocument = PDDocument.load(targetStream);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                log.info("===============================SERVICE:create QR Code======================================");
                byte[] qrCodeBytes = commonPDFStubs.generateQRCodeInAmazon(documentNo);

                log.info("===============================SERVICE:create QR Code======================================");
                //commonPDFStubs.addQrcodePageToOutsideDocuments(pdDocument, outputStream,generatedFileName);
                log.info("===============================SERVICE:addQrcodePageToOutsideDocuments======================================");

                PDPage page = new PDPage();
                pdDocument.addPage(page);
                log.info("=======Create New Page=========");

                PDImageXObject pdImage = PDImageXObject.createFromByteArray(pdDocument,qrCodeBytes,null);
                PDPageContentStream contentStream = new PDPageContentStream(pdDocument, page);
                contentStream.drawImage(pdImage, 250, 300);
                PDFont pdfFont = PDType1Font.HELVETICA_BOLD;
                int fontSize = 12;
                contentStream.setFont(pdfFont, fontSize);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 485);
                contentStream.showText("This is system generated to make the document as more informative.");
                contentStream.endText();

                contentStream.close();
                pdDocument.save(outputStream);
                pdDocument.close();

                log.info("===============================SERVICE:addQrcodePageToOutsideDocuments======================================");

                Map<String, String> metadata = new HashMap<>();
                InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                //metadata.put("Content-Type", file.headers().getContentType().toString());
                //metadata.put("Content-Length", String.valueOf(file.headers().getContentLength()));

                //stringInputStreamMap.put(fileName,targetStream);
                //metadataList.add(metadata);
                String path = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "patientDocuments");
                System.out.println("===path=== "+path);
                System.out.println("============ Inside saveUploadedDocumentDetailsCloud ==========");

                if(!isCloudStorage){
                    String patientDocumentFile = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();
                    System.out.println("============ Inside saveUploadedDocumentDetailsCloud for Local Storage ==========");
                    String tempPatientDocumentFile = patientDocumentFile + "/" + documentNo + ".pdf";
                    CountDownLatch countDownLatch= new CountDownLatch(1);
                    countDownLatch.countDown();
                    System.out.println("============ Inside saveUploadedDocumentDetailsCloud for Local Storage ==========");
                    //Files.copy(inputStream, Path.of(tempPatientDocumentFile));
                    FileUtils.copyInputStreamToFile(inputStream, new File(tempPatientDocumentFile));
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Updated");
                    System.out.println("Task 1 Completed");
                    System.out.println("Next Task");
                    return savePatientDocumentObject(documentNo,documentType,patientNo,patientId,patientUUID,description,patientDocumentStatus,isCloudStorage);
                }else{
                    return fileStore.upload(path,documentNo+".pdf", Optional.of(metadata), inputStream)
                        .map(eTag->{
                            System.out.println("Updated");
                            System.out.println("Task 1 Completed");
                            System.out.println("Next Task");
                            return savePatientDocumentObject(documentNo,documentType,patientNo,patientId,patientUUID,description,patientDocumentStatus,isCloudStorage);
                        }).flatMap(x->x)
                        .switchIfEmpty(Mono.just(new PatientDocument()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            return null;
        }
    }

    private Mono<PatientDocument> savePatientDocumentObject(String documentNo, String documentType, String patientNo, Long patientId,
                                                            UUID patientUUID, String description, String patientDocumentStatus, boolean isCloudStorage){
        String patientDocumentFile = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();
        PatientDocument patientDocument = new PatientDocument();
        patientDocument.setPatientDocumentNo(documentNo);
        if(isCloudStorage) {
            patientDocument.setPatientDocumentRepositoryName(null);
        }else{
            patientDocument.setPatientDocumentRepositoryName(patientDocumentFile + "/" + documentNo + ".pdf");
        }
        patientDocument.setPatientDocumentName(documentNo + ".pdf");
        patientDocument.setPatientDocumentType(documentType);
        patientDocument.setPatientIdNo(patientNo);
        patientDocument.setPatientId(patientId);
        patientDocument.setPatientUuid(patientUUID);
        patientDocument.setPatientDocumentDescription(description);
        patientDocument.setPatientDocumentStatus(patientDocumentStatus);

        patientDocument.setStatus("Active");
        patientDocument.setUploadedById(1L);
        patientDocument.setUploadedByName("Abhay Api");
        patientDocument.setUploadedDate(LocalDate.now());
        Mono<PatientDocument> savedPatientDocument = patientDocumentEntryRepositoryExtended.save(patientDocument);
        System.out.println("savedPatientDocument ");
        return savedPatientDocument;
    }
    public Mono<PatientDocument> saveUploadedDocumentDetails(String documentNo,String documentType,String description,String patientDocumentStatus,PatientMasterDTO patientMasterDTO) {
        System.out.println("Inside saveUploadedDocumentDetails");
        if (patientMasterDTO != null) {
            //String documentType = patientDocumentParameterDTO.getDocumentType();
            CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
            String patientDocumentFile = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();
            try {
                //patientMasterDTO.subscribe(System.out::println);
                Long patientId = patientMasterDTO.getPatientId();
                String patientNo = patientMasterDTO.getPatientIdNumber();
                UUID patientUUID = patientMasterDTO.getPatientMasterUUID();
                String tempPatientDocumentFile = patientDocumentFile + "/" + documentNo + ".pdf";
                System.out.println("tempPatientDocumentFile " + tempPatientDocumentFile);
                PDDocument pdDocument = PDDocument.load(new File(tempPatientDocumentFile));
                String documentNoQrcodePath = fileUploadConfigProperties.getPatientDocumentsQRcodeProperties().getLocation();

                //String documentNoQrcodePath = amazonS3Client.getResourceUrl("dme-patient-service","qrCode");
                System.out.println("===Path==== "+ documentNoQrcodePath);
                String qrCodePath = commonPDFStubs.generateQRCode(documentNo, documentNoQrcodePath);
                System.out.println("qrCodePath " + qrCodePath);
                commonPDFStubs.addQrcodePageToOutsideDocuments(pdDocument, qrCodePath, tempPatientDocumentFile);
                System.out.println("Updated");

                System.out.println("Task 1 Completed");
                System.out.println("Next Task");
                PatientDocument patientDocument = new PatientDocument();
                patientDocument.setPatientDocumentNo(documentNo);
                patientDocument.setPatientDocumentRepositoryName(patientDocumentFile + "/" + documentNo + ".pdf");
                patientDocument.setPatientDocumentName(documentNo + ".pdf");
                patientDocument.setPatientDocumentType(documentType);
                patientDocument.setPatientIdNo(patientNo);
                patientDocument.setPatientId(patientId);
                patientDocument.setPatientUuid(patientUUID);
                patientDocument.setPatientDocumentDescription(description);
                patientDocument.setPatientDocumentStatus(patientDocumentStatus);

                patientDocument.setStatus("Active");
                patientDocument.setUploadedById(1L);
                patientDocument.setUploadedByName("Abhay Api");
                patientDocument.setUploadedDate(LocalDate.now());
                Mono<PatientDocument> savedPatientDocument = patientDocumentEntryRepositoryExtended.save(patientDocument);
                System.out.println("savedPatientDocument ");
                return savedPatientDocument;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            return null;
        }
    }
}
