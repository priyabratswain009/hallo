package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DeliveryAbnData.
 */
@Table("t_delivery_abn_data")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryAbnData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("delivery_abn_data_id")
    private Long deliveryAbnDataId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("patient_id")
    private Long patientId;

    @Column("primary_insurance_name")
    private String primaryInsuranceName;

    @Column("primary_insurance_policy_number")
    private String primaryInsurancePolicyNumber;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("sales_order_details_abn_print_date")
    private LocalDate salesOrderDetailsAbnPrintDate;

    @Column("sales_order_details_abn_item_name")
    private String salesOrderDetailsAbnItemName;

    @Column("sales_order_details_abn_item_proc_code")
    private String salesOrderDetailsAbnItemProcCode;

    @Column("sales_order_details_abn_item_charge_amount")
    private String salesOrderDetailsAbnItemChargeAmount;

    @Column("sales_order_details_patient_abn_response")
    private String salesOrderDetailsPatientAbnResponse;

    @Column("sales_order_details_abn_delivery_status")
    private String salesOrderDetailsAbnDeliveryStatus;

    @Column("sales_order_details_abn_patient_signature_status")
    private String salesOrderDetailsAbnPatientSignatureStatus;

    @Column("sales_order_details_abn_status")
    private String salesOrderDetailsAbnStatus;

    @Column("sales_order_details_abn_reason")
    private String salesOrderDetailsAbnReason;

    @Column("sales_order_details_abn_modifier_1")
    private String salesOrderDetailsAbnModifier1;

    @Column("sales_order_details_abn_modifier_2")
    private String salesOrderDetailsAbnModifier2;

    @Column("branch_name")
    private String branchName;

    @Column("branch_id")
    private String branchId;

    @Column("qr_code")
    private String qrCode;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("abn_number")
    private String abnNumber;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("delivery_abn_data_uuid")
    private UUID deliveryAbnDataUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryAbnDataId() {
        return this.deliveryAbnDataId;
    }

    public DeliveryAbnData deliveryAbnDataId(Long deliveryAbnDataId) {
        this.setDeliveryAbnDataId(deliveryAbnDataId);
        return this;
    }

    public void setDeliveryAbnDataId(Long deliveryAbnDataId) {
        this.deliveryAbnDataId = deliveryAbnDataId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public DeliveryAbnData salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public DeliveryAbnData patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPrimaryInsuranceName() {
        return this.primaryInsuranceName;
    }

    public DeliveryAbnData primaryInsuranceName(String primaryInsuranceName) {
        this.setPrimaryInsuranceName(primaryInsuranceName);
        return this;
    }

    public void setPrimaryInsuranceName(String primaryInsuranceName) {
        this.primaryInsuranceName = primaryInsuranceName;
    }

    public String getPrimaryInsurancePolicyNumber() {
        return this.primaryInsurancePolicyNumber;
    }

    public DeliveryAbnData primaryInsurancePolicyNumber(String primaryInsurancePolicyNumber) {
        this.setPrimaryInsurancePolicyNumber(primaryInsurancePolicyNumber);
        return this;
    }

    public void setPrimaryInsurancePolicyNumber(String primaryInsurancePolicyNumber) {
        this.primaryInsurancePolicyNumber = primaryInsurancePolicyNumber;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public DeliveryAbnData patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public DeliveryAbnData patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public DeliveryAbnData patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getSalesOrderDetailsAbnPrintDate() {
        return this.salesOrderDetailsAbnPrintDate;
    }

    public DeliveryAbnData salesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.setSalesOrderDetailsAbnPrintDate(salesOrderDetailsAbnPrintDate);
        return this;
    }

    public void setSalesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
        this.salesOrderDetailsAbnPrintDate = salesOrderDetailsAbnPrintDate;
    }

    public String getSalesOrderDetailsAbnItemName() {
        return this.salesOrderDetailsAbnItemName;
    }

    public DeliveryAbnData salesOrderDetailsAbnItemName(String salesOrderDetailsAbnItemName) {
        this.setSalesOrderDetailsAbnItemName(salesOrderDetailsAbnItemName);
        return this;
    }

    public void setSalesOrderDetailsAbnItemName(String salesOrderDetailsAbnItemName) {
        this.salesOrderDetailsAbnItemName = salesOrderDetailsAbnItemName;
    }

    public String getSalesOrderDetailsAbnItemProcCode() {
        return this.salesOrderDetailsAbnItemProcCode;
    }

    public DeliveryAbnData salesOrderDetailsAbnItemProcCode(String salesOrderDetailsAbnItemProcCode) {
        this.setSalesOrderDetailsAbnItemProcCode(salesOrderDetailsAbnItemProcCode);
        return this;
    }

    public void setSalesOrderDetailsAbnItemProcCode(String salesOrderDetailsAbnItemProcCode) {
        this.salesOrderDetailsAbnItemProcCode = salesOrderDetailsAbnItemProcCode;
    }

    public String getSalesOrderDetailsAbnItemChargeAmount() {
        return this.salesOrderDetailsAbnItemChargeAmount;
    }

    public DeliveryAbnData salesOrderDetailsAbnItemChargeAmount(String salesOrderDetailsAbnItemChargeAmount) {
        this.setSalesOrderDetailsAbnItemChargeAmount(salesOrderDetailsAbnItemChargeAmount);
        return this;
    }

    public void setSalesOrderDetailsAbnItemChargeAmount(String salesOrderDetailsAbnItemChargeAmount) {
        this.salesOrderDetailsAbnItemChargeAmount = salesOrderDetailsAbnItemChargeAmount;
    }

    public String getSalesOrderDetailsPatientAbnResponse() {
        return this.salesOrderDetailsPatientAbnResponse;
    }

    public DeliveryAbnData salesOrderDetailsPatientAbnResponse(String salesOrderDetailsPatientAbnResponse) {
        this.setSalesOrderDetailsPatientAbnResponse(salesOrderDetailsPatientAbnResponse);
        return this;
    }

    public void setSalesOrderDetailsPatientAbnResponse(String salesOrderDetailsPatientAbnResponse) {
        this.salesOrderDetailsPatientAbnResponse = salesOrderDetailsPatientAbnResponse;
    }

    public String getSalesOrderDetailsAbnDeliveryStatus() {
        return this.salesOrderDetailsAbnDeliveryStatus;
    }

    public DeliveryAbnData salesOrderDetailsAbnDeliveryStatus(String salesOrderDetailsAbnDeliveryStatus) {
        this.setSalesOrderDetailsAbnDeliveryStatus(salesOrderDetailsAbnDeliveryStatus);
        return this;
    }

    public void setSalesOrderDetailsAbnDeliveryStatus(String salesOrderDetailsAbnDeliveryStatus) {
        this.salesOrderDetailsAbnDeliveryStatus = salesOrderDetailsAbnDeliveryStatus;
    }

    public String getSalesOrderDetailsAbnPatientSignatureStatus() {
        return this.salesOrderDetailsAbnPatientSignatureStatus;
    }

    public DeliveryAbnData salesOrderDetailsAbnPatientSignatureStatus(String salesOrderDetailsAbnPatientSignatureStatus) {
        this.setSalesOrderDetailsAbnPatientSignatureStatus(salesOrderDetailsAbnPatientSignatureStatus);
        return this;
    }

    public void setSalesOrderDetailsAbnPatientSignatureStatus(String salesOrderDetailsAbnPatientSignatureStatus) {
        this.salesOrderDetailsAbnPatientSignatureStatus = salesOrderDetailsAbnPatientSignatureStatus;
    }

    public String getSalesOrderDetailsAbnStatus() {
        return this.salesOrderDetailsAbnStatus;
    }

    public DeliveryAbnData salesOrderDetailsAbnStatus(String salesOrderDetailsAbnStatus) {
        this.setSalesOrderDetailsAbnStatus(salesOrderDetailsAbnStatus);
        return this;
    }

    public void setSalesOrderDetailsAbnStatus(String salesOrderDetailsAbnStatus) {
        this.salesOrderDetailsAbnStatus = salesOrderDetailsAbnStatus;
    }

    public String getSalesOrderDetailsAbnReason() {
        return this.salesOrderDetailsAbnReason;
    }

    public DeliveryAbnData salesOrderDetailsAbnReason(String salesOrderDetailsAbnReason) {
        this.setSalesOrderDetailsAbnReason(salesOrderDetailsAbnReason);
        return this;
    }

    public void setSalesOrderDetailsAbnReason(String salesOrderDetailsAbnReason) {
        this.salesOrderDetailsAbnReason = salesOrderDetailsAbnReason;
    }

    public String getSalesOrderDetailsAbnModifier1() {
        return this.salesOrderDetailsAbnModifier1;
    }

    public DeliveryAbnData salesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.setSalesOrderDetailsAbnModifier1(salesOrderDetailsAbnModifier1);
        return this;
    }

    public void setSalesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
        this.salesOrderDetailsAbnModifier1 = salesOrderDetailsAbnModifier1;
    }

    public String getSalesOrderDetailsAbnModifier2() {
        return this.salesOrderDetailsAbnModifier2;
    }

    public DeliveryAbnData salesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.setSalesOrderDetailsAbnModifier2(salesOrderDetailsAbnModifier2);
        return this;
    }

    public void setSalesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
        this.salesOrderDetailsAbnModifier2 = salesOrderDetailsAbnModifier2;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public DeliveryAbnData branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public DeliveryAbnData branchId(String branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getQrCode() {
        return this.qrCode;
    }

    public DeliveryAbnData qrCode(String qrCode) {
        this.setQrCode(qrCode);
        return this;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public DeliveryAbnData patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getAbnNumber() {
        return this.abnNumber;
    }

    public DeliveryAbnData abnNumber(String abnNumber) {
        this.setAbnNumber(abnNumber);
        return this;
    }

    public void setAbnNumber(String abnNumber) {
        this.abnNumber = abnNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryAbnData status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryAbnData createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryAbnData createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryAbnData createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DeliveryAbnData updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryAbnData updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryAbnData updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryAbnDataUuid() {
        return this.deliveryAbnDataUuid;
    }

    public DeliveryAbnData deliveryAbnDataUuid(UUID deliveryAbnDataUuid) {
        this.setDeliveryAbnDataUuid(deliveryAbnDataUuid);
        return this;
    }

    public void setDeliveryAbnDataUuid(UUID deliveryAbnDataUuid) {
        this.deliveryAbnDataUuid = deliveryAbnDataUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAbnData)) {
            return false;
        }
        return deliveryAbnDataId != null && deliveryAbnDataId.equals(((DeliveryAbnData) o).deliveryAbnDataId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAbnData{" +
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
