package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadPatientDocumentByPatientIdCommand {
    private Long patientDocumentId;
    private String basePath;
    private Path rootPath;
    private String fileName;
}
