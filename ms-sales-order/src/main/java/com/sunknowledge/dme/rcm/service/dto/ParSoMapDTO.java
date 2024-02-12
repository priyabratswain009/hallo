package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ParSoMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParSoMapDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long parSoId;

    private Long parId;

    private String parNo;

    private Long soId;

    private String soNo;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID parSoMapUuid;

    public Long getParSoId() {
        return parSoId;
    }

    public void setParSoId(Long parSoId) {
        this.parSoId = parSoId;
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

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
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

    public UUID getParSoMapUuid() {
        return parSoMapUuid;
    }

    public void setParSoMapUuid(UUID parSoMapUuid) {
        this.parSoMapUuid = parSoMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParSoMapDTO)) {
            return false;
        }

        ParSoMapDTO parSoMapDTO = (ParSoMapDTO) o;
        if (this.parSoId == null) {
            return false;
        }
        return Objects.equals(this.parSoId, parSoMapDTO.parSoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parSoId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParSoMapDTO{" +
            "parSoId=" + getParSoId() +
            ", parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", parSoMapUuid='" + getParSoMapUuid() + "'" +
            "}";
    }
}
