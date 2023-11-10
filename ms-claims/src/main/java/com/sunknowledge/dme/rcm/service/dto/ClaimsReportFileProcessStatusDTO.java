package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus} entity.
 */
public class ClaimsReportFileProcessStatusDTO implements Serializable {

    @NotNull
    private Long fileStatusId;

    private String fileName;

    private LocalDate runDate;

    private LocalDate processDate;

    private String processStatus;

    private UUID claimsReportFileProcessStatusUuid;

    public Long getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public LocalDate getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public UUID getClaimsReportFileProcessStatusUuid() {
        return claimsReportFileProcessStatusUuid;
    }

    public void setClaimsReportFileProcessStatusUuid(UUID claimsReportFileProcessStatusUuid) {
        this.claimsReportFileProcessStatusUuid = claimsReportFileProcessStatusUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsReportFileProcessStatusDTO)) {
            return false;
        }

        ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO = (ClaimsReportFileProcessStatusDTO) o;
        if (this.fileStatusId == null) {
            return false;
        }
        return Objects.equals(this.fileStatusId, claimsReportFileProcessStatusDTO.fileStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fileStatusId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsReportFileProcessStatusDTO{" +
            "fileStatusId=" + getFileStatusId() +
            ", fileName='" + getFileName() + "'" +
            ", runDate='" + getRunDate() + "'" +
            ", processDate='" + getProcessDate() + "'" +
            ", processStatus='" + getProcessStatus() + "'" +
            ", claimsReportFileProcessStatusUuid='" + getClaimsReportFileProcessStatusUuid() + "'" +
            "}";
    }
}
