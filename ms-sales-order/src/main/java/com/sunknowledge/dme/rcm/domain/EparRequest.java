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
 * A EparRequest.
 */
@Table("t_epar_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EparRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("epar_request_id")
    private Long eparRequestId;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("par_id")
    private Long parId;

    @Column("par_no")
    private String parNo;

    @Column("request_datetime")
    private LocalDate requestDatetime;

    @Column("response_status")
    private String responseStatus;

    @Column("response_url")
    private String responseUrl;

    @Column("request_json")
    private String requestJson;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("epa_request_uuid")
    private UUID epaRequestUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEparRequestId() {
        return this.eparRequestId;
    }

    public EparRequest eparRequestId(Long eparRequestId) {
        this.setEparRequestId(eparRequestId);
        return this;
    }

    public void setEparRequestId(Long eparRequestId) {
        this.eparRequestId = eparRequestId;
    }

    public Long getSoId() {
        return this.soId;
    }

    public EparRequest soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public EparRequest soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Long getParId() {
        return this.parId;
    }

    public EparRequest parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return this.parNo;
    }

    public EparRequest parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public LocalDate getRequestDatetime() {
        return this.requestDatetime;
    }

    public EparRequest requestDatetime(LocalDate requestDatetime) {
        this.setRequestDatetime(requestDatetime);
        return this;
    }

    public void setRequestDatetime(LocalDate requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public EparRequest responseStatus(String responseStatus) {
        this.setResponseStatus(responseStatus);
        return this;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseUrl() {
        return this.responseUrl;
    }

    public EparRequest responseUrl(String responseUrl) {
        this.setResponseUrl(responseUrl);
        return this;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getRequestJson() {
        return this.requestJson;
    }

    public EparRequest requestJson(String requestJson) {
        this.setRequestJson(requestJson);
        return this;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getStatus() {
        return this.status;
    }

    public EparRequest status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public EparRequest createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public EparRequest createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public EparRequest createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public UUID getEpaRequestUuid() {
        return this.epaRequestUuid;
    }

    public EparRequest epaRequestUuid(UUID epaRequestUuid) {
        this.setEpaRequestUuid(epaRequestUuid);
        return this;
    }

    public void setEpaRequestUuid(UUID epaRequestUuid) {
        this.epaRequestUuid = epaRequestUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EparRequest)) {
            return false;
        }
        return eparRequestId != null && eparRequestId.equals(((EparRequest) o).eparRequestId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EparRequest{" +
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
