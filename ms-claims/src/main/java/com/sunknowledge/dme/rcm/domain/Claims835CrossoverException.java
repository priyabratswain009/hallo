package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Claims835CrossoverException.
 */
@Entity
@Table(name = "t_claims_835_crossover_exception")
public class Claims835CrossoverException implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claims_835_crossover_exception_id", nullable = false)
    private Long claims835CrossoverExceptionId;

    @Column(name = "claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column(name = "patient_control_number")
    private String patientControlNumber;

    @Column(name = "payer_claim_control_number")
    private String payerClaimControlNumber;

    @Column(name = "crossover_carrier_name")
    private String crossoverCarrierName;

    @Column(name = "crossover_carrier_payer_id")
    private String crossoverCarrierPayerId;

    @Column(name = "crossover_carrier_payer_name")
    private String crossoverCarrierPayerName;

    @Column(name = "exception_type")
    private String exceptionType;

    @Column(name = "claims_835_crossover_exception_uuid")
    private UUID claims835CrossoverExceptionUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaims835CrossoverExceptionId() {
        return this.claims835CrossoverExceptionId;
    }

    public Claims835CrossoverException claims835CrossoverExceptionId(Long claims835CrossoverExceptionId) {
        this.setClaims835CrossoverExceptionId(claims835CrossoverExceptionId);
        return this;
    }

    public void setClaims835CrossoverExceptionId(Long claims835CrossoverExceptionId) {
        this.claims835CrossoverExceptionId = claims835CrossoverExceptionId;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public Claims835CrossoverException claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientControlNumber() {
        return this.patientControlNumber;
    }

    public Claims835CrossoverException patientControlNumber(String patientControlNumber) {
        this.setPatientControlNumber(patientControlNumber);
        return this;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public String getPayerClaimControlNumber() {
        return this.payerClaimControlNumber;
    }

    public Claims835CrossoverException payerClaimControlNumber(String payerClaimControlNumber) {
        this.setPayerClaimControlNumber(payerClaimControlNumber);
        return this;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
    }

    public String getCrossoverCarrierName() {
        return this.crossoverCarrierName;
    }

    public Claims835CrossoverException crossoverCarrierName(String crossoverCarrierName) {
        this.setCrossoverCarrierName(crossoverCarrierName);
        return this;
    }

    public void setCrossoverCarrierName(String crossoverCarrierName) {
        this.crossoverCarrierName = crossoverCarrierName;
    }

    public String getCrossoverCarrierPayerId() {
        return this.crossoverCarrierPayerId;
    }

    public Claims835CrossoverException crossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.setCrossoverCarrierPayerId(crossoverCarrierPayerId);
        return this;
    }

    public void setCrossoverCarrierPayerId(String crossoverCarrierPayerId) {
        this.crossoverCarrierPayerId = crossoverCarrierPayerId;
    }

    public String getCrossoverCarrierPayerName() {
        return this.crossoverCarrierPayerName;
    }

    public Claims835CrossoverException crossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.setCrossoverCarrierPayerName(crossoverCarrierPayerName);
        return this;
    }

    public void setCrossoverCarrierPayerName(String crossoverCarrierPayerName) {
        this.crossoverCarrierPayerName = crossoverCarrierPayerName;
    }

    public String getExceptionType() {
        return this.exceptionType;
    }

    public Claims835CrossoverException exceptionType(String exceptionType) {
        this.setExceptionType(exceptionType);
        return this;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public UUID getClaims835CrossoverExceptionUuid() {
        return this.claims835CrossoverExceptionUuid;
    }

    public Claims835CrossoverException claims835CrossoverExceptionUuid(UUID claims835CrossoverExceptionUuid) {
        this.setClaims835CrossoverExceptionUuid(claims835CrossoverExceptionUuid);
        return this;
    }

    public void setClaims835CrossoverExceptionUuid(UUID claims835CrossoverExceptionUuid) {
        this.claims835CrossoverExceptionUuid = claims835CrossoverExceptionUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claims835CrossoverException)) {
            return false;
        }
        return (
            claims835CrossoverExceptionId != null &&
            claims835CrossoverExceptionId.equals(((Claims835CrossoverException) o).claims835CrossoverExceptionId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Claims835CrossoverException{" +
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
