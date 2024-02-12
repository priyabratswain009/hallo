package com.sunknowledge.dme.rcm.amazon;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public interface AmazonS3Service {
    List<Bucket> listBuckets();
    Mono<ResponseEntity<ByteArrayResource>> downloadFileFromS3Bucket(List<String> fileNames);
    void saveUploadedFileToS3BucketFileOld(String title, String description, MultipartFile file);
    String saveAndUploadedFileToS3Bucket(MultipartFile file);
    void uploadDocumentToS3Bucket(String bucketName, String s3Key, ByteArrayOutputStream outputStream);
    URL getS3ObjectURL(String bucketName, String s3Key);
    void saveFileToS3Bucket(String bucketName, String s3Key, MultipartFile multipartFile) throws SdkClientException, IOException;
    CopyObjectResult copyS3BucketPdfFile(String sourceBucket, String sourceKey, String destinationKey);
    String extractFilename(String key);
    PdfReader createPdfReaderFromS3(String bucketName, String pdfKey) throws IOException;
    InputStream getInputStreamFromS3(String bucketName, String pdfKey);
    PdfStamper getPdfStamperFromS3(PdfReader pdfReader, String outputFilePathKey) throws IOException;
    PdfStamper getPdfStamper(PdfReader pdfReader, File tempFile) throws IOException;
//    void putObjectToS3(String bucketName, String outputFilePathKey, File tempFile);
    Flux<Object> saveUploadedFileToS3BucketFile(String title, String description, Flux<FilePart> file);

    Mono<String> uploadInputStreamDocsS3Bucket(String title, String description, InputStream inputStream, String folderName, String bucketName);

    Mono<byte[]> getFileAsByteCode(String attachmentName) throws IOException;
    void uploadTempFileToS3(String bucketName, String pdfKey, File tempFile);
    void copyDocumentToS3(String bucketName, String s3Key, File localTempFile);
    byte[] mergePdfFiles(String bucketName, String file1Key, String file2Key) throws Exception;
    void uploadFileToS3Bucket(String bucketName, String s3Key, byte[] content);
    void deleteFileFromS3(String bucketName, String fileKey);
}
