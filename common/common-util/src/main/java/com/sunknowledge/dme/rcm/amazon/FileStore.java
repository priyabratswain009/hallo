package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FileStore {
    private AmazonS3 amazonS3;
    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

    public String upload(String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        PutObjectResult putObjectResult;
        try {
            System.out.println("=============>Path===========>"+path);
            System.out.println("=============>fileName===========>"+fileName);
            putObjectResult = amazonS3.putObject(path, fileName, inputStream, objectMetadata);
            log.info("======================>PutObjectResult===================="+putObjectResult.getMetadata());
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
        return fileName;
    }

    public Mono<String> uploadMono(String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            System.out.println("=============>Path===========>"+path);
            System.out.println("=============>fileName===========>"+fileName);
            return Mono.just(amazonS3.putObject(path, fileName, inputStream, objectMetadata).getETag());
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public String uploadToS3(String bucketName, String key, byte[] documentBytes) {
        // Create metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(documentBytes.length);
        metadata.setContentType("application/pdf");

        // Upload the document to S3
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new ByteArrayInputStream(documentBytes), metadata);
        PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);

        // You can check the ETag (entity tag) of the uploaded object
        String eTag = putObjectResult.getETag();
        return eTag;
    }

    public byte[] downloadFileFromS3(String bucketName, String key) throws IOException {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, key));
        try (S3ObjectInputStream objectInputStream = s3Object.getObjectContent()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = objectInputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
}
