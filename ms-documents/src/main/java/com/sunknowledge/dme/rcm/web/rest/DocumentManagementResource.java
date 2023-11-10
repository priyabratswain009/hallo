package com.sunknowledge.dme.rcm.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.domain.PatientDetails;
import com.sunknowledge.dme.rcm.exception.MyFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@RestController
@RequestMapping("/api/document-management")
public class DocumentManagementResource {
    /*@Autowired
    private DocumentManagementService documentManagementService;*/

    @PostMapping(value = "/uploadPatientDocument", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPatientDocument(@RequestPart MultipartFile documentFile) throws Exception {
        String resultOutcomeJson = "";
        if(documentFile == null || documentFile.getOriginalFilename().equals("")) {
            PatientDetails patientDetails = new PatientDetails();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            resultOutcomeJson = ow.writeValueAsString(patientDetails);
            throw new MyFileNotFoundException("You must select the document file for uploading");
        }
        else {
            String path = documentFile.getOriginalFilename();
            File file = new File(path);
            /*PatientDetails savePatientDetails = documentManagementService.saveDocumentFile(file, documentFile);
            if(savePatientDetails != null) {
                System.out.println("Document Stored........");
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                resultOutcomeJson = ow.writeValueAsString(savePatientDetails);
            }*/
        }
        return new ResponseEntity<String>(resultOutcomeJson, HttpStatus.OK);
    }
}
