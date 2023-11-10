package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PatientInsVerifStat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientInsVerifStatDTO implements Serializable {

    private Long insuranceVerifId;

    private Long patientInsuranceId;

    private LocalDate elligibilityCheckDate;

    private String elligibilityStatus;

    private String elligibilityCheckType;

    private String periodYear;

    private String coverageInfo;

    private String networkInfo;

    private Double deductableAmt;

    private Double remainingAmt;

    private String coinsuranceOrCopay;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID patientInsVerifStatUuid;

    public Long getInsuranceVerifId() {
        return insuranceVerifId;
    }

    public void setInsuranceVerifId(Long insuranceVerifId) {
        this.insuranceVerifId = insuranceVerifId;
    }

    public Long getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(Long patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public LocalDate getElligibilityCheckDate() {
        return elligibilityCheckDate;
    }

    public void setElligibilityCheckDate(LocalDate elligibilityCheckDate) {
        this.elligibilityCheckDate = elligibilityCheckDate;
    }

    public String getElligibilityStatus() {
        return elligibilityStatus;
    }

    public void setElligibilityStatus(String elligibilityStatus) {
        this.elligibilityStatus = elligibilityStatus;
    }

    public String getElligibilityCheckType() {
        return elligibilityCheckType;
    }

    public void setElligibilityCheckType(String elligibilityCheckType) {
        this.elligibilityCheckType = elligibilityCheckType;
    }

    public String getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    public String getCoverageInfo() {
        return coverageInfo;
    }

    public void setCoverageInfo(String coverageInfo) {
        this.coverageInfo = coverageInfo;
    }

    public String getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(String networkInfo) {
        this.networkInfo = networkInfo;
    }

    public Double getDeductableAmt() {
        return deductableAmt;
    }

    public void setDeductableAmt(Double deductableAmt) {
        this.deductableAmt = deductableAmt;
    }

    public Double getRemainingAmt() {
        return remainingAmt;
    }

    public void setRemainingAmt(Double remainingAmt) {
        this.remainingAmt = remainingAmt;
    }

    public String getCoinsuranceOrCopay() {
        return coinsuranceOrCopay;
    }

    public void setCoinsuranceOrCopay(String coinsuranceOrCopay) {
        this.coinsuranceOrCopay = coinsuranceOrCopay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getPatientInsVerifStatUuid() {
        return patientInsVerifStatUuid;
    }

    public void setPatientInsVerifStatUuid(UUID patientInsVerifStatUuid) {
        this.patientInsVerifStatUuid = patientInsVerifStatUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientInsVerifStatDTO)) {
            return false;
        }

        PatientInsVerifStatDTO patientInsVerifStatDTO = (PatientInsVerifStatDTO) o;
        if (this.insuranceVerifId == null) {
            return false;
        }
        return Objects.equals(this.insuranceVerifId, patientInsVerifStatDTO.insuranceVerifId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.insuranceVerifId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientInsVerifStatDTO{" +
            "insuranceVerifId=" + getInsuranceVerifId() +
            ", patientInsuranceId=" + getPatientInsuranceId() +
            ", elligibilityCheckDate='" + getElligibilityCheckDate() + "'" +
            ", elligibilityStatus='" + getElligibilityStatus() + "'" +
            ", elligibilityCheckType='" + getElligibilityCheckType() + "'" +
            ", periodYear='" + getPeriodYear() + "'" +
            ", coverageInfo='" + getCoverageInfo() + "'" +
            ", networkInfo='" + getNetworkInfo() + "'" +
            ", deductableAmt=" + getDeductableAmt() +
            ", remainingAmt=" + getRemainingAmt() +
            ", coinsuranceOrCopay='" + getCoinsuranceOrCopay() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", patientInsVerifStatUuid='" + getPatientInsVerifStatUuid() + "'" +
            "}";
    }
}
