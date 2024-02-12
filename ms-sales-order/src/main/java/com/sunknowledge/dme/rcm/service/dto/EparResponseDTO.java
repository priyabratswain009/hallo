package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.EparResponse} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EparResponseDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long eparResponseId;

    private Long eparRequestId;

    private Long parId;

    private String parNo;

    private String payerIdNo;

    private String payerName;

    private String patientFirstName;

    private String patientLastName;

    private String subscriberRelationship;

    private LocalDate certificationEffectiveDate;

    private LocalDate certificationExpirationDate;

    private String requestType;

    private String placeOfService;

    private String serviceLevel;

    private String quantity;

    private String quantityType;

    private LocalDate responseCreateDate;

    private LocalDate responseResponseDate;

    private String jsonData;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID eParResponseUuid;

    public Long getEparResponseId() {
        return eparResponseId;
    }

    public void setEparResponseId(Long eparResponseId) {
        this.eparResponseId = eparResponseId;
    }

    public Long getEparRequestId() {
        return eparRequestId;
    }

    public void setEparRequestId(Long eparRequestId) {
        this.eparRequestId = eparRequestId;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return parNo;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getPayerIdNo() {
        return payerIdNo;
    }

    public void setPayerIdNo(String payerIdNo) {
        this.payerIdNo = payerIdNo;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getSubscriberRelationship() {
        return subscriberRelationship;
    }

    public void setSubscriberRelationship(String subscriberRelationship) {
        this.subscriberRelationship = subscriberRelationship;
    }

    public LocalDate getCertificationEffectiveDate() {
        return certificationEffectiveDate;
    }

    public void setCertificationEffectiveDate(LocalDate certificationEffectiveDate) {
        this.certificationEffectiveDate = certificationEffectiveDate;
    }

    public LocalDate getCertificationExpirationDate() {
        return certificationExpirationDate;
    }

    public void setCertificationExpirationDate(LocalDate certificationExpirationDate) {
        this.certificationExpirationDate = certificationExpirationDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPlaceOfService() {
        return placeOfService;
    }

    public void setPlaceOfService(String placeOfService) {
        this.placeOfService = placeOfService;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public LocalDate getResponseCreateDate() {
        return responseCreateDate;
    }

    public void setResponseCreateDate(LocalDate responseCreateDate) {
        this.responseCreateDate = responseCreateDate;
    }

    public LocalDate getResponseResponseDate() {
        return responseResponseDate;
    }

    public void setResponseResponseDate(LocalDate responseResponseDate) {
        this.responseResponseDate = responseResponseDate;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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

    public UUID geteParResponseUuid() {
        return eParResponseUuid;
    }

    public void seteParResponseUuid(UUID eParResponseUuid) {
        this.eParResponseUuid = eParResponseUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EparResponseDTO)) {
            return false;
        }

        EparResponseDTO eparResponseDTO = (EparResponseDTO) o;
        if (this.eparResponseId == null) {
            return false;
        }
        return Objects.equals(this.eparResponseId, eparResponseDTO.eparResponseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.eparResponseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EparResponseDTO{" +
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
