package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.services.s3.model.Bucket;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AmazonS3Service {
    List<Bucket> listBuckets();
    Mono<ResponseEntity<ByteArrayResource>> downloadFileFromS3Bucket(List<String> fileNames);
    Flux<Object> saveUploadedFileToS3BucketFile(String title, String description, Flux<FilePart> file);
}
