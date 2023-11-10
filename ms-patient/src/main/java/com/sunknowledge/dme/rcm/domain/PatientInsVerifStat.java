package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientInsVerifStat.
 */
@Table("t_patient_ins_verif_stat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientInsVerifStat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("insurance_verif_id")
    private Long insuranceVerifId;

    @Column("patient_insurance_id")
    private Long patientInsuranceId;

    @Column("elligibility_check_date")
    private LocalDate elligibilityCheckDate;

    @Column("elligibility_status")
    private String elligibilityStatus;

    @Column("elligibility_check_type")
    private String elligibilityCheckType;

    @Column("period_year")
    private String periodYear;

    @Column("coverage_info")
    private String coverageInfo;

    @Column("network_info")
    private String networkInfo;

    @Column("deductable_amt")
    private Double deductableAmt;

    @Column("remaining_amt")
    private Double remainingAmt;

    @Column("coinsurance_or_copay")
    private String coinsuranceOrCopay;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("patient_ins_verif_stat_uuid")
    private UUID patientInsVerifStatUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInsuranceVerifId() {
        return this.insuranceVerifId;
    }

    public PatientInsVerifStat insuranceVerifId(Long insuranceVerifId) {
        this.setInsuranceVerifId(insuranceVerifId);
        return this;
    }

    public void setInsuranceVerifId(Long insuranceVerifId) {
        this.insuranceVerifId = insuranceVerifId;
    }

    public Long getPatientInsuranceId() {
        return this.patientInsuranceId;
    }

    public PatientInsVerifStat patientInsuranceId(Long patientInsuranceId) {
        this.setPatientInsuranceId(patientInsuranceId);
        return this;
    }

    public void setPatientInsuranceId(Long patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public LocalDate getElligibilityCheckDate() {
        return this.elligibilityCheckDate;
    }

    public PatientInsVerifStat elligibilityCheckDate(LocalDate elligibilityCheckDate) {
        this.setElligibilityCheckDate(elligibilityCheckDate);
        return this;
    }

    public void setElligibilityCheckDate(LocalDate elligibilityCheckDate) {
        this.elligibilityCheckDate = elligibilityCheckDate;
    }

    public String getElligibilityStatus() {
        return this.elligibilityStatus;
    }

    public PatientInsVerifStat elligibilityStatus(String elligibilityStatus) {
        this.setElligibilityStatus(elligibilityStatus);
        return this;
    }

    public void setElligibilityStatus(String elligibilityStatus) {
        this.elligibilityStatus = elligibilityStatus;
    }

    public String getElligibilityCheckType() {
        return this.elligibilityCheckType;
    }

    public PatientInsVerifStat elligibilityCheckType(String elligibilityCheckType) {
        this.setElligibilityCheckType(elligibilityCheckType);
        return this;
    }

    public void setElligibilityCheckType(String elligibilityCheckType) {
        this.elligibilityCheckType = elligibilityCheckType;
    }

    public String getPeriodYear() {
        return this.periodYear;
    }

    public PatientInsVerifStat periodYear(String periodYear) {
        this.setPeriodYear(periodYear);
        return this;
    }

    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    public String getCoverageInfo() {
        return this.coverageInfo;
    }

    public PatientInsVerifStat coverageInfo(String coverageInfo) {
        this.setCoverageInfo(coverageInfo);
        return this;
    }

    public void setCoverageInfo(String coverageInfo) {
        this.coverageInfo = coverageInfo;
    }

    public String getNetworkInfo() {
        return this.networkInfo;
    }

    public PatientInsVerifStat networkInfo(String networkInfo) {
        this.setNetworkInfo(networkInfo);
        return this;
    }

    public void setNetworkInfo(String networkInfo) {
        this.networkInfo = networkInfo;
    }

    public Double getDeductableAmt() {
        return this.deductableAmt;
    }

    public PatientInsVerifStat deductableAmt(Double deductableAmt) {
        this.setDeductableAmt(deductableAmt);
        return this;
    }

    public void setDeductableAmt(Double deductableAmt) {
        this.deductableAmt = deductableAmt;
    }

    public Double getRemainingAmt() {
        return this.remainingAmt;
    }

    public PatientInsVerifStat remainingAmt(Double remainingAmt) {
        this.setRemainingAmt(remainingAmt);
        return this;
    }

    public void setRemainingAmt(Double remainingAmt) {
        this.remainingAmt = remainingAmt;
    }

    public String getCoinsuranceOrCopay() {
        return this.coinsuranceOrCopay;
    }

    public PatientInsVerifStat coinsuranceOrCopay(String coinsuranceOrCopay) {
        this.setCoinsuranceOrCopay(coinsuranceOrCopay);
        return this;
    }

    public void setCoinsuranceOrCopay(String coinsuranceOrCopay) {
        this.coinsuranceOrCopay = coinsuranceOrCopay;
    }

    public String getStatus() {
        return this.status;
    }

    public PatientInsVerifStat status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PatientInsVerifStat createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PatientInsVerifStat createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PatientInsVerifStat updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PatientInsVerifStat createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PatientInsVerifStat updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PatientInsVerifStat updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getPatientInsVerifStatUuid() {
        return this.patientInsVerifStatUuid;
    }

    public PatientInsVerifStat patientInsVerifStatUuid(UUID patientInsVerifStatUuid) {
        this.setPatientInsVerifStatUuid(patientInsVerifStatUuid);
        return this;
    }

    public void setPatientInsVerifStatUuid(UUID patientInsVerifStatUuid) {
        this.patientInsVerifStatUuid = patientInsVerifStatUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientInsVerifStat)) {
            return false;
        }
        return insuranceVerifId != null && insuranceVerifId.equals(((PatientInsVerifStat) o).insuranceVerifId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientInsVerifStat{" +
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
