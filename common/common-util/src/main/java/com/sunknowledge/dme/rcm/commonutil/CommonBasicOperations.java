package com.sunknowledge.dme.rcm.commonutil;


import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonBasicOperations {
    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // Get the bytes from MultipartFile
        byte[] fileBytes = multipartFile.getBytes();
        // Create a File in memory
        File file = File.createTempFile("in-memory-file", null);
        // Write the bytes to the File
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileBytes);
        }
        return file;
    }

    private static File convertMultipartFileToFileUsingTempFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", null);
        multipartFile.transferTo(file);
        return file;
    }

    public static void convertTempFileToOriginalFile(String tempFilePath, String originalFilePath) {
        File tempFile = new File(tempFilePath);
        File originalFile = new File(originalFilePath);
        if (tempFile.exists()) {
            if (tempFile.renameTo(originalFile)) {
                System.out.println("File renamed successfully.");
            } else {
                System.out.println("Failed to rename the file.");
            }
        } else {
            System.out.println("Temporary file does not exist.");
        }
    }

    public byte[] readFileToByteArray(File file) throws IOException {
        System.out.println("==========readFileToByteArray============Path========="+file.toPath());
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large to read into a byte array");
            }
            byte[] bytes = new byte[(int) length];
            int offset = 0;
            int bytesRead;
            while ((offset < bytes.length) && ((bytesRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0)) {
                offset += bytesRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            return bytes;
        }
    }

    public ByteArrayOutputStream createByteArrayOutputStream(byte[] fileContent) throws IOException {
        // Create a ByteArrayOutputStream and write the byte array to it
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(fileContent);
        System.out.println("File content converted to ByteArrayOutputStream successfully.");
        return byteArrayOutputStream;
    }

    public static byte[] fileToByteArray(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your requirements
            return null;
        }
    }

    public File createTempFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("stamped-pdf", ".pdf");
        System.out.println("Temporary file location: " + tempFile.getAbsolutePath());
        return tempFile;
    }

    public MultipartFile renameFile(MultipartFile originalFile, String newFileName) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return originalFile.getName();
            }
            @Override
            public String getOriginalFilename() {
                return newFileName;
            }
            @Override
            public String getContentType() {
                return originalFile.getContentType();
            }
            @Override
            public boolean isEmpty() {
                return originalFile.isEmpty();
            }
            @Override
            public long getSize() {
                return originalFile.getSize();
            }
            @Override
            public byte[] getBytes() throws IOException {
                return originalFile.getBytes();
            }
            @Override
            public InputStream getInputStream() throws IOException {
                return originalFile.getInputStream();
            }
            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                originalFile.transferTo(dest);
            }
        };
    }

    private String getFileExtension(String fileName) {
        System.out.println("==============getFileExtension=========== " + fileName);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
}
