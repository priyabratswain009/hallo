package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverException} entity.
 */
public class Claims835CrossoverExceptionDTO implements Serializable {

    @NotNull
    private Long claims835CrossoverExceptionId;

    private Long claimCob835MasterId;

    private String patientControlNumber;

    private String payerClaimControlNumber;

    private String crossoverCarrierName;

    private String crossoverCarrierPayerId;

    private String crossoverCarrierPayerName;

    private String exceptionType;

    private UUID claims835CrossoverExceptionUuid;

    public Long getClaims835CrossoverExceptionId() {
        return claims835CrossoverExceptionId;
    }

    public void setClaims835CrossoverExceptionId(Long claims835CrossoverExceptionId) {
        this.claims835CrossoverExceptionId = claims835CrossoverExceptionId;
    }

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientControlNumber() {
        return patientControlNumber;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getCrossoverCarrierName() {
        return crossoverCarrierName;
    }

    public void setCrossoverCarrierName(String crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getCrossoverCarrierPayerId() {
        return crossoverCarrierPayerId;
    }

    public void setCrossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.crossoverCarrierPayerId = crossoverCarrierPayerId;
    }

    public String getCrossoverCarrierPayerName() {
        return crossoverCarrierPayerName;
    }

    public void setCrossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.crossoverCarrierPayerName = crossoverCarrierPayerName;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public UUID getClaims835CrossoverExceptionUuid() {
        return claims835CrossoverExceptionUuid;
    }

    public void setClaims835CrossoverExceptionUuid(UUID claims835CrossoverExceptionUuid) {
        this.claims835CrossoverExceptionUuid = claims835CrossoverExceptionUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claims835CrossoverExceptionDTO)) {
            return false;
        }

        Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO = (Claims835CrossoverExceptionDTO) o;
        if (this.claims835CrossoverExceptionId == null) {
            return false;
        }
        return Objects.equals(this.claims835CrossoverExceptionId, claims835CrossoverExceptionDTO.claims835CrossoverExceptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claims835CrossoverExceptionId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Claims835CrossoverExceptionDTO{" +
            "claims835CrossoverExceptionId=" + getClaims835CrossoverExceptionId() +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", payerClaimControlNumber='" + getPayerClaimControlNumber() + "'" +
            ", crossoverCarrierName='" + getCrossoverCarrierName() + "'" +
            ", crossoverCarrierPayerId='" + getCrossoverCarrierPayerId() + "'" +
            ", crossoverCarrierPayerName='" + getCrossoverCarrierPayerName() + "'" +
            ", exceptionType='" + getExceptionType() + "'" +
            ", claims835CrossoverExceptionUuid='" + getClaims835CrossoverExceptionUuid() + "'" +
            "}";
    }
}
