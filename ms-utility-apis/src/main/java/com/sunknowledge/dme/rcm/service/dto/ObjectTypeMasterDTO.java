package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ObjectTypeMaster} entity.
 */
public class ObjectTypeMasterDTO implements Serializable {

    @NotNull
    private Long objectId;

    private String objectName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID objectTypeMasterUuid;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
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

    public UUID getObjectTypeMasterUuid() {
        return objectTypeMasterUuid;
    }

    public void setObjectTypeMasterUuid(UUID objectTypeMasterUuid) {
        this.objectTypeMasterUuid = objectTypeMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectTypeMasterDTO)) {
            return false;
        }

        ObjectTypeMasterDTO objectTypeMasterDTO = (ObjectTypeMasterDTO) o;
        if (this.objectId == null) {
            return false;
        }
        return Objects.equals(this.objectId, objectTypeMasterDTO.objectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.objectId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectTypeMasterDTO{" +
            "objectId=" + getObjectId() +
            ", objectName='" + getObjectName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", objectTypeMasterUuid='" + getObjectTypeMasterUuid() + "'" +
            "}";
    }
}
