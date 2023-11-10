package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.services.s3.model.Bucket;
import com.sunknowledge.dme.rcm.application.config.AmazonConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/amazon")
public class AmazonController {
    @Autowired
    private AmazonConfig amazonConfig;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @ApiOperation(value = "Create Amazon S3 Bucket")
    @GetMapping("/createAmazonBucket")
    public void createS3Bucket(String bucketName) {
        if(amazonConfig.amazonS3().doesBucketExist(bucketName)) {
            System.out.println("Bucket name already in use. Try another name.");
            return;
        }
        amazonConfig.amazonS3().createBucket(bucketName);
    }

    @ApiOperation(value = "List Amazon S3 Bucket")
    @GetMapping("/listBuckets")
    public List<String> listBuckets(){
        var buckets = amazonS3Service.listBuckets();
        var names = buckets.stream().map(Bucket::getName).collect(Collectors.toList());
        return names;
    }

    @PostMapping(path = "uploadDocument", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveUploadedFileToS3BucketFile(@RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam("file") MultipartFile file) {
        amazonS3Service.saveUploadedFileToS3BucketFile(title, description, file);
    }

    @GetMapping(value = "/download")
    public void downloadFileFromS3Bucket(@RequestParam("fileName") String fileName) {
        amazonS3Service.downloadFileFromS3Bucket(fileName);
    }
}