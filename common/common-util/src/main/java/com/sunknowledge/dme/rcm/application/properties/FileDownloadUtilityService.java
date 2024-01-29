package com.sunknowledge.dme.rcm.application.properties;

import com.sunknowledge.dme.rcm.application.exceptions.MyFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * @author Bimal K Sahoo
 * @since 11/07/2023
 */
@Service
@Slf4j
public class FileDownloadUtilityService {
    private FileUploadConfigProperties fileUploadConfigProperties;

    public FileDownloadUtilityService(FileUploadConfigProperties fileUploadConfigProperties) {
        this.fileUploadConfigProperties = fileUploadConfigProperties;
    }

    public Flux<DataBuffer> loadFileAsReactiveDocumentResource(String moduleName, String filename, String filetype) {
        try {
            String path = null;
            /*#######################################START - DOWNLOAD CMN DOCUMENTS####################################################*/
            if(moduleName.equalsIgnoreCase("CMN") && filetype.equalsIgnoreCase("out"))
                path = fileUploadConfigProperties.getCmnGeneratedDocumentProperties().getLocation();
            if(moduleName.equalsIgnoreCase("CMN") && filetype.equalsIgnoreCase("in"))
                path = fileUploadConfigProperties.getCmnReturnDocumentProperties().getLocation();
            /*#######################################END - DOWNLOAD CMN DOCUMENTS######################################################*/

            /*#######################################START - DOWNLOAD PATIENT DOCUMENTS IN SO ####################################################*/
            if(moduleName.equalsIgnoreCase("PATIENT"))
                path = fileUploadConfigProperties.getPatientDocumentsPDDorPIDorPMRProperties().getLocation();
            /*#######################################END - DOWNLOAD PATIENT DOCUMENTS IN SO######################################################*/

            Path filePath = Paths.get(path + "/" + filename);
            System.out.println("======filePath======"+filePath);

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() || resource.isReadable()){
                return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 4096);
            }
            else {
                throw new MyFileNotFoundException("Could not read the file! "+filename);
            }
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Could not read the file! "+filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource loadFileAsNonReactiveDocumentResource(String moduleName, String filename, String filetype) {
        try {
            String path = null;
            /*#######################################START - DOWNLOAD DELIVERY DOCUMENTS####################################################*/
            if(moduleName.equalsIgnoreCase("DELIVERY") && filetype.equalsIgnoreCase("delivery"))
                path = fileUploadConfigProperties.getDeliveryDocumentProperties().getLocation();
            if(moduleName.equalsIgnoreCase("DELIVERY") && filetype.equalsIgnoreCase("signature"))
                path = fileUploadConfigProperties.getSignatureProperties().getLocation();
            if(moduleName.equalsIgnoreCase("DELIVERY") && filetype.equalsIgnoreCase("carrier"))
                path = fileUploadConfigProperties.getDeliveryCarrierProperties().getLocation();
            /*#######################################END - DOWNLOAD DELIVERY DOCUMENTS######################################################*/

            Path filePath = Paths.get(path + "/" + filename);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            else{
                throw new MyFileNotFoundException("File not found "+filename);
            }
        }
        catch(MalformedURLException me){
            throw new MyFileNotFoundException("File not found "+filename);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
