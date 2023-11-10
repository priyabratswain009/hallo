package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Claims835CrossoverProcessed.
 */
@Entity
@Table(name = "t_claims_835_crossover_processed")
public class Claims835CrossoverProcessed implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claims_835_crossover_processed_id", nullable = false)
    private Long claims835CrossoverProcessedId;

    @Column(name = "claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column(name = "patient_control_number")
    private String patientControlNumber;

    @Column(name = "processed_date")
    private LocalDate processedDate;

    @Column(name = "claims_835_crossover_processed")
    private UUID claims835CrossoverProcessed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaims835CrossoverProcessedId() {
        return this.claims835CrossoverProcessedId;
    }

    public Claims835CrossoverProcessed claims835CrossoverProcessedId(Long claims835CrossoverProcessedId) {
        this.setClaims835CrossoverProcessedId(claims835CrossoverProcessedId);
        return this;
    }

    public void setClaims835CrossoverProcessedId(Long claims835CrossoverProcessedId) {
        this.claims835CrossoverProcessedId = claims835CrossoverProcessedId;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public Claims835CrossoverProcessed claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getPatientControlNumber() {
        return this.patientControlNumber;
    }

    public Claims835CrossoverProcessed patientControlNumber(String patientControlNumber) {
        this.setPatientControlNumber(patientControlNumber);
        return this;
    }

    public void setPatientControlNumber(String patientControlNumber) {
        this.patientControlNumber = patientControlNumber;
    }

    public LocalDate getProcessedDate() {
        return this.processedDate;
    }

    public Claims835CrossoverProcessed processedDate(LocalDate processedDate) {
        this.setProcessedDate(processedDate);
        return this;
    }

    public void setProcessedDate(LocalDate processedDate) {
        this.processedDate = processedDate;
    }

    public UUID getClaims835CrossoverProcessed() {
        return this.claims835CrossoverProcessed;
    }

    public Claims835CrossoverProcessed claims835CrossoverProcessed(UUID claims835CrossoverProcessed) {
        this.setClaims835CrossoverProcessed(claims835CrossoverProcessed);
        return this;
    }

    public void setClaims835CrossoverProcessed(UUID claims835CrossoverProcessed) {
        this.claims835CrossoverProcessed = claims835CrossoverProcessed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claims835CrossoverProcessed)) {
            return false;
        }
        return (
            claims835CrossoverProcessedId != null &&
            claims835CrossoverProcessedId.equals(((Claims835CrossoverProcessed) o).claims835CrossoverProcessedId)
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
        return "Claims835CrossoverProcessed{" +
            "claims835CrossoverProcessedId=" + getClaims835CrossoverProcessedId() +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", processedDate='" + getProcessedDate() + "'" +
            ", claims835CrossoverProcessed='" + getClaims835CrossoverProcessed() + "'" +
            "}";
    }
}
