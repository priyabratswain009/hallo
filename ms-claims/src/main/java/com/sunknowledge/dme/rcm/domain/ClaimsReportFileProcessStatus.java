package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ClaimsReportFileProcessStatus.
 */
@Entity
@Table(name = "t_claims_report_file_process_status")
public class ClaimsReportFileProcessStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "file_status_id", nullable = false)
    private Long fileStatusId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "run_date")
    private LocalDate runDate;

    @Column(name = "process_date")
    private LocalDate processDate;

    @Column(name = "process_status")
    private String processStatus;

    @Column(name = "claims_report_file_process_status_uuid")
    private UUID claimsReportFileProcessStatusUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getFileStatusId() {
        return this.fileStatusId;
    }

    public ClaimsReportFileProcessStatus fileStatusId(Long fileStatusId) {
        this.setFileStatusId(fileStatusId);
        return this;
    }

    public void setFileStatusId(Long fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ClaimsReportFileProcessStatus fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getRunDate() {
        return this.runDate;
    }

    public ClaimsReportFileProcessStatus runDate(LocalDate runDate) {
        this.setRunDate(runDate);
        return this;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public LocalDate getProcessDate() {
        return this.processDate;
    }

    public ClaimsReportFileProcessStatus processDate(LocalDate processDate) {
        this.setProcessDate(processDate);
        return this;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public String getProcessStatus() {
        return this.processStatus;
    }

    public ClaimsReportFileProcessStatus processStatus(String processStatus) {
        this.setProcessStatus(processStatus);
        return this;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public UUID getClaimsReportFileProcessStatusUuid() {
        return this.claimsReportFileProcessStatusUuid;
    }

    public ClaimsReportFileProcessStatus claimsReportFileProcessStatusUuid(UUID claimsReportFileProcessStatusUuid) {
        this.setClaimsReportFileProcessStatusUuid(claimsReportFileProcessStatusUuid);
        return this;
    }

    public void setClaimsReportFileProcessStatusUuid(UUID claimsReportFileProcessStatusUuid) {
        this.claimsReportFileProcessStatusUuid = claimsReportFileProcessStatusUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsReportFileProcessStatus)) {
            return false;
        }
        return fileStatusId != null && fileStatusId.equals(((ClaimsReportFileProcessStatus) o).fileStatusId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimsReportFileProcessStatus{" +
            "fileStatusId=" + getFileStatusId() +
            ", fileName='" + getFileName() + "'" +
            ", runDate='" + getRunDate() + "'" +
            ", processDate='" + getProcessDate() + "'" +
            ", processStatus='" + getProcessStatus() + "'" +
            ", claimsReportFileProcessStatusUuid='" + getClaimsReportFileProcessStatusUuid() + "'" +
            "}";
    }
}
