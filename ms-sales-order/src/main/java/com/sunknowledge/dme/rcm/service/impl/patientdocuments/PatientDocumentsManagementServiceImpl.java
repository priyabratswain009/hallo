package com.sunknowledge.dme.rcm.service.impl.patientdocuments;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.repository.cmn.CmnDocumentMasterRepo;
import com.sunknowledge.dme.rcm.service.patientdocuments.PatientDocumentsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PatientDocumentsManagementServiceImpl implements PatientDocumentsManagementService {
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;
    @Autowired
    private CmnDocumentMasterRepo cmnDocumentMasterRepository;

    @Override
    public void processPatientDocuments() throws IOException, ExecutionException, InterruptedException {
        log.info("==================void processPatientDocuments()=======================");
        File directoryPath = new File(fileUploadConfigProperties.getDmePatientDocuments().getLocation());
        FileFilter textFileFilter = new FileFilter(){
            public boolean accept(File file) {
                boolean isFile = file.isFile();
                if (isFile) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File filesList[] = directoryPath.listFiles(textFileFilter);
        final Path tempPath = Paths.get(fileUploadConfigProperties.getDmeTempQrCode().getLocation());
        for(File file : filesList) {
            File source = new File(fileUploadConfigProperties.getDmePatientDocuments().getLocation() + "/" + file.getName());
            File destination = null;
            PDDocument document = PDDocument.load(file);
            ServiceOutcome<String> outcome = new CommonPDFStubs().checkOwnOrOutsideDocument(document, tempPath);
            if (!outcome.getOutcome()) {
                log.info("====================>External Document====================>>>");
            }
            else{
                log.info("====================>Internal Document====================>>>");
                if(outcome.getData().length() >= 3 && outcome.getData().contains(outcome.getData().substring(0, 3))) {
                    String moduleName = outcome.getData().substring(0, 3);
                    log.info("==============Module Name=================" + moduleName);
                    if (moduleName.equalsIgnoreCase("CMN")) {
                        CmnDocumentMaster cmnDocumentMaster = cmnDocumentMasterRepository.getCmnDocumentDetailsOnCmnNumber(outcome.getData()).toFuture().get();
                        if (cmnDocumentMaster != null) {
                            String fileName = outcome.getData()+"_in.pdf";
                            cmnDocumentMaster.setReturnDocumentName(fileName);
                            cmnDocumentMasterRepository.save(cmnDocumentMaster).toFuture().get();
                            destination = new File(fileUploadConfigProperties.getCmnReturnDocumentProperties().getLocation() + "/" + fileName);
                            copyFileUsingChannel(source, destination);
                            document.close();
                            Files.deleteIfExists(Paths.get(fileUploadConfigProperties.getDmePatientDocuments().getLocation() + "/" + file.getName()));
                        }
                    }
                }
                else{
                    log.info("===================ELSE====UNMATCHED QRCODE================");
                }
            }
            System.out.println("===================================END================================================");
        }
    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        log.info("===============CALL to FILE COPY===================");
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }
}
