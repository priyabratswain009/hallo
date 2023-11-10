package com.sunknowledge.dme.rcm.application.utils.tasks;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.sunknowledge.dme.rcm.service.patientdocuments.PatientDocumentsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author Bimal K Sahoo
 * @since 27/07/2023
 */

@Configuration
@EnableScheduling
@Component(value = "incomingPatientDocumentsProcessor")
public class IncomingPatientDocumentsProcessor {
    @Autowired
    private PatientDocumentsManagementService patientDocumentsManagementService;
    private void incomingPatientDocumentsProcessor() throws DocumentException, IOException, WriterException, ExecutionException, InterruptedException {
        System.out.println("===========================IncomingPatientDocumentsProcessor==============================");
        patientDocumentsManagementService.processPatientDocuments();
    }
}
