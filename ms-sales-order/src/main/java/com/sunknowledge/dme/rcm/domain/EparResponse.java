package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EparResponse.
 */
@Table("t_epar_response")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EparResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("epar_response_id")
    private Long eparResponseId;

    @Column("epar_request_id")
    private Long eparRequestId;

    @Column("par_id")
    private Long parId;

    @Column("par_no")
    private String parNo;

    @Column("payer_id_no")
    private String payerIdNo;

    @Column("payer_name")
    private String payerName;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("subscriber_relationship")
    private String subscriberRelationship;

    @Column("certification_effective_date")
    private LocalDate certificationEffectiveDate;

    @Column("certification_expiration_date")
    private LocalDate certificationExpirationDate;

    @Column("request_type")
    private String requestType;

    @Column("place_of_service")
    private String placeOfService;

    @Column("service_level")
    private String serviceLevel;

    @Column("quantity")
    private String quantity;

    @Column("quantity_type")
    private String quantityType;

    @Column("response_create_date")
    private LocalDate responseCreateDate;

    @Column("response_response_date")
    private LocalDate responseResponseDate;

    @Column("json_data")
    private String jsonData;

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

    @Column("e_par_response_uuid")
    private UUID eParResponseUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEparResponseId() {
        return this.eparResponseId;
    }

    public EparResponse eparResponseId(Long eparResponseId) {
        this.setEparResponseId(eparResponseId);
        return this;
    }

    public void setEparResponseId(Long eparResponseId) {
        this.eparResponseId = eparResponseId;
    }

    public Long getEparRequestId() {
        return this.eparRequestId;
    }

    public EparResponse eparRequestId(Long eparRequestId) {
        this.setEparRequestId(eparRequestId);
        return this;
    }

    public void setEparRequestId(Long eparRequestId) {
        this.eparRequestId = eparRequestId;
    }

    public Long getParId() {
        return this.parId;
    }

    public EparResponse parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return this.parNo;
    }

    public EparResponse parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getPayerIdNo() {
        return this.payerIdNo;
    }

    public EparResponse payerIdNo(String payerIdNo) {
        this.setPayerIdNo(payerIdNo);
        return this;
    }

    public void setPayerIdNo(String payerIdNo) {
        this.payerIdNo = payerIdNo;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public EparResponse payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public EparResponse patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public EparResponse patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getSubscriberRelationship() {
        return this.subscriberRelationship;
    }

    public EparResponse subscriberRelationship(String subscriberRelationship) {
        this.setSubscriberRelationship(subscriberRelationship);
        return this;
    }

    public void setSubscriberRelationship(String subscriberRelationship) {
        this.subscriberRelationship = subscriberRelationship;
    }

    public LocalDate getCertificationEffectiveDate() {
        return this.certificationEffectiveDate;
    }

    public EparResponse certificationEffectiveDate(LocalDate certificationEffectiveDate) {
        this.setCertificationEffectiveDate(certificationEffectiveDate);
        return this;
    }

    public void setCertificationEffectiveDate(LocalDate certificationEffectiveDate) {
        this.certificationEffectiveDate = certificationEffectiveDate;
    }

    public LocalDate getCertificationExpirationDate() {
        return this.certificationExpirationDate;
    }

    public EparResponse certificationExpirationDate(LocalDate certificationExpirationDate) {
        this.setCertificationExpirationDate(certificationExpirationDate);
        return this;
    }

    public void setCertificationExpirationDate(LocalDate certificationExpirationDate) {
        this.certificationExpirationDate = certificationExpirationDate;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public EparResponse requestType(String requestType) {
        this.setRequestType(requestType);
        return this;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPlaceOfService() {
        return this.placeOfService;
    }

    public EparResponse placeOfService(String placeOfService) {
        this.setPlaceOfService(placeOfService);
        return this;
    }

    public void setPlaceOfService(String placeOfService) {
        this.placeOfService = placeOfService;
    }

    public String getServiceLevel() {
        return this.serviceLevel;
    }

    public EparResponse serviceLevel(String serviceLevel) {
        this.setServiceLevel(serviceLevel);
        return this;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public EparResponse quantity(String quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return this.quantityType;
    }

    public EparResponse quantityType(String quantityType) {
        this.setQuantityType(quantityType);
        return this;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public LocalDate getResponseCreateDate() {
        return this.responseCreateDate;
    }

    public EparResponse responseCreateDate(LocalDate responseCreateDate) {
        this.setResponseCreateDate(responseCreateDate);
        return this;
    }

    public void setResponseCreateDate(LocalDate responseCreateDate) {
        this.responseCreateDate = responseCreateDate;
    }

    public LocalDate getResponseResponseDate() {
        return this.responseResponseDate;
    }

    public EparResponse responseResponseDate(LocalDate responseResponseDate) {
        this.setResponseResponseDate(responseResponseDate);
        return this;
    }

    public void setResponseResponseDate(LocalDate responseResponseDate) {
        this.responseResponseDate = responseResponseDate;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public EparResponse jsonData(String jsonData) {
        this.setJsonData(jsonData);
        return this;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getStatus() {
        return this.status;
    }

    public EparResponse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public EparResponse createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public EparResponse createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public EparResponse createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public EparResponse updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public EparResponse updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public EparResponse updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID geteParResponseUuid() {
        return this.eParResponseUuid;
    }

    public EparResponse eParResponseUuid(UUID eParResponseUuid) {
        this.seteParResponseUuid(eParResponseUuid);
        return this;
    }

    public void seteParResponseUuid(UUID eParResponseUuid) {
        this.eParResponseUuid = eParResponseUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EparResponse)) {
            return false;
        }
        return eparResponseId != null && eparResponseId.equals(((EparResponse) o).eparResponseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EparResponse{" +
            "eparResponseId=" + getEparResponseId() +
            ", eparRequestId=" + getEparRequestId() +
            ", parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", payerIdNo='" + getPayerIdNo() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", subscriberRelationship='" + getSubscriberRelationship() + "'" +
            ", certificationEffectiveDate='" + getCertificationEffectiveDate() + "'" +
            ", certificationExpirationDate='" + getCertificationExpirationDate() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", placeOfService='" + getPlaceOfService() + "'" +
            ", serviceLevel='" + getServiceLevel() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", quantityType='" + getQuantityType() + "'" +
            ", responseCreateDate='" + getResponseCreateDate() + "'" +
            ", responseResponseDate='" + getResponseResponseDate() + "'" +
            ", jsonData='" + getJsonData() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", eParResponseUuid='" + geteParResponseUuid() + "'" +
            "}";
    }
}
