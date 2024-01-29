package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.EparRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EparRequestDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long eparRequestId;

    private Long soId;

    private String soNo;

    private Long parId;

    private String parNo;

    private LocalDate requestDatetime;

    private String responseStatus;

    private String responseUrl;

    private String requestJson;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private UUID epaRequestUuid;

    public Long getEparRequestId() {
        return eparRequestId;
    }

    public void setEparRequestId(Long eparRequestId) {
        this.eparRequestId = eparRequestId;
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

    public LocalDate getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(LocalDate requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
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

    public UUID getEpaRequestUuid() {
        return epaRequestUuid;
    }

    public void setEpaRequestUuid(UUID epaRequestUuid) {
        this.epaRequestUuid = epaRequestUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EparRequestDTO)) {
            return false;
        }

        EparRequestDTO eparRequestDTO = (EparRequestDTO) o;
        if (this.eparRequestId == null) {
            return false;
        }
        return Objects.equals(this.eparRequestId, eparRequestDTO.eparRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.eparRequestId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EparRequestDTO{" +
            "eparRequestId=" + getEparRequestId() +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", requestDatetime='" + getRequestDatetime() + "'" +
            ", responseStatus='" + getResponseStatus() + "'" +
            ", responseUrl='" + getResponseUrl() + "'" +
            ", requestJson='" + getRequestJson() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", epaRequestUuid='" + getEpaRequestUuid() + "'" +
            "}";
    }
}
