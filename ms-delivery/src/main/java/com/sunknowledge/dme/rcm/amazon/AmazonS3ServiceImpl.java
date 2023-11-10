package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.sunknowledge.dme.rcm.application.config.AmazonConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public void downloadFileFromS3Bucket(String fileName) {
//        return fileStore.download("dme-s3-bucket", fileName);
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
    public void saveUploadedFileToS3BucketFile(String title, String description, MultipartFile file){
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
        String path = String.format("%s/%s", BucketName.TODO_IMAGE.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload("dme-s3-bucket", fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }
}
