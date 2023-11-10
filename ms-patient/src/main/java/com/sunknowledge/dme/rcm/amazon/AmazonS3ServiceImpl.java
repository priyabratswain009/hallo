package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.sunknowledge.dme.rcm.application.config.AmazonConfig;
import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.compress.utils.IOUtils;
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
import java.nio.file.Files;
import java.nio.file.Path;
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
                    return fileStore.upload(path,fileName, Optional.of(metadata), targetStream);
                });
        });
    }
}
