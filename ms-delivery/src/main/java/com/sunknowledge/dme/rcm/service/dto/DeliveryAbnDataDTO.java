package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryAbnData} entity.
 */
public class DeliveryAbnDataDTO implements Serializable {

    private Long deliveryAbnDataId;

    private Long salesOrderId;

    private Long patientId;

    private String primaryInsuranceName;

    private String primaryInsurancePolicyNumber;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate salesOrderDetailsAbnPrintDate;

    private String salesOrderDetailsAbnItemName;

    private String salesOrderDetailsAbnItemProcCode;

    private String salesOrderDetailsAbnItemChargeAmount;

    private String salesOrderDetailsPatientAbnResponse;

    private String salesOrderDetailsAbnDeliveryStatus;

    private String salesOrderDetailsAbnPatientSignatureStatus;

    private String salesOrderDetailsAbnStatus;

    private String salesOrderDetailsAbnReason;

    private String salesOrderDetailsAbnModifier1;

    private String salesOrderDetailsAbnModifier2;

    private String branchName;

    private String branchId;

    private String qrCode;

    private String patientIdNo;

    private String abnNumber;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryAbnDataUuid;

    public Long getDeliveryAbnDataId() {
        return deliveryAbnDataId;
    }

    public void setDeliveryAbnDataId(Long deliveryAbnDataId) {
        this.deliveryAbnDataId = deliveryAbnDataId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPrimaryInsuranceName() {
        return primaryInsuranceName;
    }

    public void setPrimaryInsuranceName(String primaryInsuranceName) {
        this.primaryInsuranceName = primaryInsuranceName;
    }

    public String getPrimaryInsurancePolicyNumber() {
        return primaryInsurancePolicyNumber;
    }

    public void setPrimaryInsurancePolicyNumber(String primaryInsurancePolicyNumber) {
        this.primaryInsurancePolicyNumber = primaryInsurancePolicyNumber;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getSalesOrderDetailsAbnPrintDate() {
        return salesOrderDetailsAbnPrintDate;
    }

    public void setSalesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.salesOrderDetailsAbnPrintDate = salesOrderDetailsAbnPrintDate;
    }

    public String getSalesOrderDetailsAbnItemName() {
        return salesOrderDetailsAbnItemName;
    }

    public void setSalesOrderDetailsAbnItemName(String salesOrderDetailsAbnItemName) {
        this.salesOrderDetailsAbnItemName = salesOrderDetailsAbnItemName;
    }

    public String getSalesOrderDetailsAbnItemProcCode() {
        return salesOrderDetailsAbnItemProcCode;
    }

    public void setSalesOrderDetailsAbnItemProcCode(String salesOrderDetailsAbnItemProcCode) {
        this.salesOrderDetailsAbnItemProcCode = salesOrderDetailsAbnItemProcCode;
    }

    public String getSalesOrderDetailsAbnItemChargeAmount() {
        return salesOrderDetailsAbnItemChargeAmount;
    }

    public void setSalesOrderDetailsAbnItemChargeAmount(String salesOrderDetailsAbnItemChargeAmount) {
        this.salesOrderDetailsAbnItemChargeAmount = salesOrderDetailsAbnItemChargeAmount;
    }

    public String getSalesOrderDetailsPatientAbnResponse() {
        return salesOrderDetailsPatientAbnResponse;
    }

    public void setSalesOrderDetailsPatientAbnResponse(String salesOrderDetailsPatientAbnResponse) {
        this.salesOrderDetailsPatientAbnResponse = salesOrderDetailsPatientAbnResponse;
    }

    public String getSalesOrderDetailsAbnDeliveryStatus() {
        return salesOrderDetailsAbnDeliveryStatus;
    }

    public void setSalesOrderDetailsAbnDeliveryStatus(String salesOrderDetailsAbnDeliveryStatus) {
        this.salesOrderDetailsAbnDeliveryStatus = salesOrderDetailsAbnDeliveryStatus;
    }

    public String getSalesOrderDetailsAbnPatientSignatureStatus() {
        return salesOrderDetailsAbnPatientSignatureStatus;
    }

    public void setSalesOrderDetailsAbnPatientSignatureStatus(String salesOrderDetailsAbnPatientSignatureStatus) {
        this.salesOrderDetailsAbnPatientSignatureStatus = salesOrderDetailsAbnPatientSignatureStatus;
    }

    public String getSalesOrderDetailsAbnStatus() {
        return salesOrderDetailsAbnStatus;
    }

    public void setSalesOrderDetailsAbnStatus(String salesOrderDetailsAbnStatus) {
        this.salesOrderDetailsAbnStatus = salesOrderDetailsAbnStatus;
    }

    public String getSalesOrderDetailsAbnReason() {
        return salesOrderDetailsAbnReason;
    }

    public void setSalesOrderDetailsAbnReason(String salesOrderDetailsAbnReason) {
        this.salesOrderDetailsAbnReason = salesOrderDetailsAbnReason;
    }

    public String getSalesOrderDetailsAbnModifier1() {
        return salesOrderDetailsAbnModifier1;
    }

    public void setSalesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.salesOrderDetailsAbnModifier1 = salesOrderDetailsAbnModifier1;
    }

    public String getSalesOrderDetailsAbnModifier2() {
        return salesOrderDetailsAbnModifier2;
    }

    public void setSalesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.salesOrderDetailsAbnModifier2 = salesOrderDetailsAbnModifier2;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getAbnNumber() {
        return abnNumber;
    }

    public void setAbnNumber(String abnNumber) {
        this.abnNumber = abnNumber;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryAbnDataUuid() {
        return deliveryAbnDataUuid;
    }

    public void setDeliveryAbnDataUuid(UUID deliveryAbnDataUuid) {
        this.deliveryAbnDataUuid = deliveryAbnDataUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAbnDataDTO)) {
            return false;
        }

        DeliveryAbnDataDTO deliveryAbnDataDTO = (DeliveryAbnDataDTO) o;
        if (this.deliveryAbnDataId == null) {
            return false;
        }
        return Objects.equals(this.deliveryAbnDataId, deliveryAbnDataDTO.deliveryAbnDataId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryAbnDataId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAbnDataDTO{" +
            "deliveryAbnDataId=" + getDeliveryAbnDataId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", patientId=" + getPatientId() +
            ", primaryInsuranceName='" + getPrimaryInsuranceName() + "'" +
            ", primaryInsurancePolicyNumber='" + getPrimaryInsurancePolicyNumber() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", salesOrderDetailsAbnPrintDate='" + getSalesOrderDetailsAbnPrintDate() + "'" +
            ", salesOrderDetailsAbnItemName='" + getSalesOrderDetailsAbnItemName() + "'" +
            ", salesOrderDetailsAbnItemProcCode='" + getSalesOrderDetailsAbnItemProcCode() + "'" +
            ", salesOrderDetailsAbnItemChargeAmount='" + getSalesOrderDetailsAbnItemChargeAmount() + "'" +
            ", salesOrderDetailsPatientAbnResponse='" + getSalesOrderDetailsPatientAbnResponse() + "'" +
            ", salesOrderDetailsAbnDeliveryStatus='" + getSalesOrderDetailsAbnDeliveryStatus() + "'" +
            ", salesOrderDetailsAbnPatientSignatureStatus='" + getSalesOrderDetailsAbnPatientSignatureStatus() + "'" +
            ", salesOrderDetailsAbnStatus='" + getSalesOrderDetailsAbnStatus() + "'" +
            ", salesOrderDetailsAbnReason='" + getSalesOrderDetailsAbnReason() + "'" +
            ", salesOrderDetailsAbnModifier1='" + getSalesOrderDetailsAbnModifier1() + "'" +
            ", salesOrderDetailsAbnModifier2='" + getSalesOrderDetailsAbnModifier2() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", branchId='" + getBranchId() + "'" +
            ", qrCode='" + getQrCode() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", abnNumber='" + getAbnNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryAbnDataUuid='" + getDeliveryAbnDataUuid() + "'" +
            "}";
    }
}
