package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.services.s3.model.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmazonS3Service {
    List<Bucket> listBuckets();
    void downloadFileFromS3Bucket(String fileName);
    void saveUploadedFileToS3BucketFile(String title, String description, MultipartFile file);
}
