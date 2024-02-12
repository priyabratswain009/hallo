package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
@AllArgsConstructor
public class AmazonS3ServiceImpl implements AmazonS3Service{
    @Autowired
    private AmazonConfig amazonConfig;
    private final FileStore fileStore;
    private AmazonS3Client amazonS3Client;

    public List<Bucket> listBuckets(){
        return amazonConfig.amazonS3().listBuckets();
    }

    public void downloadFileFromS3BucketOld(String fileName) {
        String bucketName = "dme-s3-bucket";
        S3Object s3object = amazonS3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File("." + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUploadedFileToS3BucketFileOld(String title, String description, MultipartFile file){
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
//        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
//            IMAGE_BMP.getMimeType(),
//            IMAGE_GIF.getMimeType(),
//            IMAGE_JPEG.getMimeType(), ).contains(file.getContentType())) {
//            throw new IllegalStateException("FIle uploaded is not an image");
//        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getDeliveryServiceBucket(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload("dme-s3-bucket", fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }

    @Override
    public String saveAndUploadedFileToS3Bucket(MultipartFile file){
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        String savedFileName;
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
//        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getDeliveryServiceBucket(), UUID.randomUUID());
        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getDeliveryServiceBucket(), "deliveryDocuments");
        log.info("======PATH---AMAZON======================"+path);
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            savedFileName = fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
            log.info("=========savedFileName============"+savedFileName);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return savedFileName;
    }

    public void uploadDocumentToS3Bucket(String bucketName, String s3Key, ByteArrayOutputStream outputStream){
        log.info("=================uploadDocumentToS3Bucket=========================");
        // Upload the PDF to S3
        byte[] pdfBytes = outputStream.toByteArray();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(pdfBytes.length);
        // Specify the S3 key (object key) under which the file will be stored in the bucket
        log.info("===========bucketName===========" + bucketName);
        log.info("===========s3Key===========" + s3Key);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key, new ByteArrayInputStream(pdfBytes), metadata);
        amazonConfig.amazonS3().putObject(putObjectRequest);
        System.out.println("PDF uploaded to S3: " + s3Key);
    }

    public void uploadFileToS3Bucket(String bucketName, String s3Key, byte[] content){
        log.info("=================uploadFileToS3Bucket========================="+content.length);
        // Upload the PDF to S3
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.length);
        // Specify the S3 key (object key) under which the file will be stored in the bucket
        log.info("===========bucketName===========" + bucketName);
        log.info("===========s3Key===========" + s3Key);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Key, new ByteArrayInputStream(content), metadata);
        amazonConfig.amazonS3().putObject(putObjectRequest);
        System.out.println("PDF uploaded to S3: " + s3Key);
    }

    public void deleteFileFromS3(String bucketName, String fileKey){
        amazonConfig.amazonS3().deleteObject(bucketName, fileKey);
    }

    public void copyDocumentToS3(String bucketName, String s3Key, File localTempFile){
        log.info("================copyDocumentToS3=========================");
        try {
            TransferManager transferManager = new TransferManager(amazonConfig.amazonS3());
            // Upload the local temporary file to S3
            Upload upload = transferManager.upload(bucketName, s3Key, localTempFile);
            upload.waitForCompletion();
            System.out.println("File uploaded to S3 successfully.");
        } catch (Exception e) {
            System.err.println("Error uploading file to S3: " + e.getMessage());
        }
    }

    @Override
    public URL getS3ObjectURL(String bucketName, String s3Key){
        log.info("=================getS3ObjectURL=========================");
        // Generate a pre-signed URL for the S3 object
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, s3Key);
        URL url = amazonConfig.amazonS3().generatePresignedUrl(urlRequest);
        System.out.println("S3 Object URL: " + url);
        return url;
    }

    public void saveFileToS3Bucket(String bucketName, String s3Key, MultipartFile multipartFile) throws SdkClientException, IOException {
        log.info("=================Inside Cloud saveFile==================");
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(multipartFile.getContentType());
        data.setContentLength(multipartFile.getSize());
        PutObjectResult objectResult = amazonConfig.amazonS3().putObject(bucketName, s3Key, multipartFile.getInputStream(), data);
        System.out.println(objectResult.getContentMd5()); //you can verify MD5
        System.out.println(objectResult.getMetadata());
    }

    public CopyObjectResult copyS3BucketPdfFile(String bucketName, String sourceKey, String destinationKey) {
        CopyObjectResult response = null;
        try {
            log.info("=============copyS3BucketPdfFile=======================");
            response = amazonConfig.amazonS3().copyObject(bucketName, sourceKey, bucketName, destinationKey);
            System.out.println("PDF file copied successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String extractFilename(String key) {
        // Extract filename from the key (assuming key is in the format "path/to/filename.pdf")
        return key.substring(key.lastIndexOf("/") + 1);
    }

    public PdfReader createPdfReaderFromS3(String bucketName, String pdfKey) throws IOException {
        log.info("===============createPdfReaderFromS3=========================");
        // Download the PDF file from S3
        S3Object s3Object = amazonConfig.amazonS3().getObject(bucketName, pdfKey);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        // Create PdfReader from the input stream
        return new PdfReader(inputStream);
    }

    public InputStream getInputStreamFromS3(String bucketName, String pdfKey) {
        // Download the PDF file from S3
        S3Object s3Object = amazonConfig.amazonS3().getObject(bucketName, pdfKey);
        return s3Object.getObjectContent();
    }

    public PdfStamper getPdfStamperFromS3(PdfReader pdfReader, String outputFilePathKey) throws IOException {
        try {
            // Create PdfStamper from the PdfReader
            FileOutputStream outputStream = new FileOutputStream(outputFilePathKey);
            return new PdfStamper(pdfReader, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error getting PDF from S3: " + e.getMessage());
        }
    }

    public PdfStamper getPdfStamper(PdfReader pdfReader, File tempFile) throws IOException {
        try {
            log.info("=======================getPdfStamper=================="+tempFile.getAbsolutePath());
            // Create temporary FileOutputStream from a temporary file
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            // Create PdfStamper from the PdfReader
            return new PdfStamper(pdfReader, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error creating PdfStamper: " + e.getMessage());
        }
    }

    public void uploadTempFileToS3(String bucketName, String pdfKey, File tempFile) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/pdf");
            // Create PutObjectRequest
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, pdfKey, tempFile).withMetadata(metadata);
            // Upload the temporary file to S3
            amazonConfig.amazonS3().putObject(putObjectRequest);
            System.out.println("File uploaded to S3 successfully.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public byte[] mergePdfFiles(String bucketName, String file1Key, String file2Key) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, outputStream);
        document.open();

        // Read and add content of file1.pdf
        S3Object file1Object = amazonConfig.amazonS3().getObject(bucketName, file1Key);
        PdfReader file1Reader = new PdfReader(file1Object.getObjectContent());
        copy.addDocument(file1Reader);
        file1Reader.close();

        // Read and add content of file2.pdf
        S3Object file2Object = amazonConfig.amazonS3().getObject(bucketName, file2Key);
        PdfReader file2Reader = new PdfReader(file2Object.getObjectContent());
        copy.addDocument(file2Reader);
        file2Reader.close();

        document.close();
        return outputStream.toByteArray();
    }

    @Override
    public Mono<ResponseEntity<ByteArrayResource>> downloadFileFromS3Bucket(List<String> fileNames) {

        if(fileNames.size()==1) {
            return Mono.fromSupplier(() -> {
                String bucketName = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "patientDocuments");
                S3Object s3object = amazonS3Client.getObject(bucketName, fileNames.get(0));
                S3ObjectInputStream inputStream = s3object.getObjectContent();
                ByteArrayResource resource = null;
                try {
                    resource = new ByteArrayResource(inputStream.readAllBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (resource.exists() || resource.isReadable()) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileNames.get(0));

                    return ResponseEntity.ok()
                            .headers(headers)
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(resource);
                    //return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096);
                } else {
                    throw new MyFileNotFoundException("Could not read the file! " + fileNames.get(0));
                }
            });
        }
        else {
            return Mono.fromSupplier(() -> {
                try {
                    // Create a ZIP output stream
                    ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
                    ZipOutputStream zipOutputStream = new ZipOutputStream(zipStream);

                    for (String pdfFile : fileNames) {
                        String bucketName = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "patientDocuments");
                        S3Object s3object = amazonS3Client.getObject(bucketName, pdfFile);
                        S3ObjectInputStream inputStream = s3object.getObjectContent();
                        // Add each PDF to the ZIP archive with a unique entry name

                        zipOutputStream.putNextEntry(new ZipEntry(pdfFile.toString()));
                        inputStream.transferTo(zipOutputStream);
                        zipOutputStream.closeEntry();
                    }

                    // Close the ZIP output stream
                    zipOutputStream.close();

                    // Create a ByteArrayResource from the ZIP data
                    byte[] zipData = zipStream.toByteArray();
                    ByteArrayResource resource = new ByteArrayResource(zipData);

                    // Create the HTTP response
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PatientDocuments.zip");

                    return ResponseEntity.ok()
                            .headers(headers)
                            .contentType(MediaType.APPLICATION_OCTET_STREAM)
                            .body(resource);
                } catch (IOException e) {
                    // Handle the exception here
                    e.printStackTrace();
                    return ResponseEntity.badRequest().build();
                }
            });
        }
    }

    @Override
    public Flux<Object> saveUploadedFileToS3BucketFile(String title, String description, Flux<FilePart> files) {
        //check if the file is empty

        //Check if the file is an image
//        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
//            IMAGE_BMP.getMimeType(),
//            IMAGE_GIF.getMimeType(),
//            IMAGE_JPEG.getMimeType(), ).contains(file.getContentType())) {
//            throw new IllegalStateException("FIle uploaded is not an image");
//        }
        //get file metadata
        Map<String,InputStream> stringInputStreamMap = new HashMap<>();
        List<Map<String, String>> metadataList = new ArrayList<>();
        //FilePart file = files.get(0);
        List<String> strings = new ArrayList<>();
        return files.flatMapSequential(file-> {
            return DataBufferUtils.join(file.content())
                    .map(dataBuffer -> {
                        InputStream targetStream = new ByteArrayInputStream(dataBuffer.asByteBuffer().array());
                        String fileName = String.format("%s", file.filename());
                        Map<String, String> metadata = new HashMap<>();

                        metadata.put("Content-Type", file.headers().getContentType().toString());
                        metadata.put("Content-Length", String.valueOf(file.headers().getContentLength()));

                        //stringInputStreamMap.put(fileName,targetStream);
                        //metadataList.add(metadata);
                        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getPatientServiceBucket(), "patientDocuments");
                        System.out.println("===path=== "+path);
                        return fileStore.uploadMono(path,fileName, Optional.of(metadata), targetStream);
                    });
        });
    }

    @Override
    public Mono<String> uploadInputStreamDocsS3Bucket(String title, String description, InputStream inputStream, String folderName, String bucketName) {
        String path = String.format("%s/%s", bucketName, folderName);
        System.out.println("===path=== "+path);
        Map<String, String> metadata = new HashMap<>();

        metadata.put("Content-Type", MediaType.APPLICATION_PDF.toString());
        //metadata.put("Content-Length", String.valueOf(file.headers().getContentLength()));

        return fileStore.uploadMono(path,title, Optional.of(metadata), inputStream);
    }


    @Override
    public Mono<byte[]> getFileAsByteCode(String attachmentName) throws IOException {
        String bucketName = String.format("%s/%s", BucketName.BUCKET_NAME.getSoServiceBucket(), "unIdentifiedEfaxDocuments");
        S3Object s3object = amazonS3Client.getObject(bucketName, attachmentName);
        System.out.println("URL   "+amazonS3Client.getUrl(bucketName,s3object.getKey()));
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        return Mono.just(inputStream.readAllBytes());
    }
}
