package com.sunknowledge.dme.rcm.service.patientdocuments;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface PatientDocumentsManagementService {
    void processPatientDocuments() throws IOException, DocumentException, WriterException, ExecutionException, InterruptedException;
}
