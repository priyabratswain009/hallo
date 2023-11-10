package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed} entity.
 */
public class Claims835CrossoverProcessedDTO implements Serializable {

    @NotNull
    private Long claims835CrossoverProcessedId;

    private Long claimCob835MasterId;

    private String patientControlNumber;

    private LocalDate processedDate;

    private UUID claims835CrossoverProcessed;

    public Long getClaims835CrossoverProcessedId() {
        return claims835CrossoverProcessedId;
    }

    public void setClaims835CrossoverProcessedId(Long claims835CrossoverProcessedId) {
        this.claims835CrossoverProcessedId = claims835CrossoverProcessedId;
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

    public LocalDate getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(LocalDate processedDate) {
        this.processedDate = processedDate;
    }

    public UUID getClaims835CrossoverProcessed() {
        return claims835CrossoverProcessed;
    }

    public void setClaims835CrossoverProcessed(UUID claims835CrossoverProcessed) {
        this.claims835CrossoverProcessed = claims835CrossoverProcessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Claims835CrossoverProcessedDTO)) {
            return false;
        }

        Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO = (Claims835CrossoverProcessedDTO) o;
        if (this.claims835CrossoverProcessedId == null) {
            return false;
        }
        return Objects.equals(this.claims835CrossoverProcessedId, claims835CrossoverProcessedDTO.claims835CrossoverProcessedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claims835CrossoverProcessedId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Claims835CrossoverProcessedDTO{" +
            "claims835CrossoverProcessedId=" + getClaims835CrossoverProcessedId() +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", patientControlNumber='" + getPatientControlNumber() + "'" +
            ", processedDate='" + getProcessedDate() + "'" +
            ", claims835CrossoverProcessed='" + getClaims835CrossoverProcessed() + "'" +
            "}";
    }
}
