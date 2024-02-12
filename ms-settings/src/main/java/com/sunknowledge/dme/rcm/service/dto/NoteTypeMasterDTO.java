package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.NoteTypeMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoteTypeMasterDTO implements Serializable {

    private Long noteTypeId;

    private String noteTypeName;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID noteTypeMasterUuid;

    public Long getNoteTypeId() {
        return noteTypeId;
    }

    public void setNoteTypeId(Long noteTypeId) {
        this.noteTypeId = noteTypeId;
    }

    public String getNoteTypeName() {
        return noteTypeName;
    }

    public void setNoteTypeName(String noteTypeName) {
        this.noteTypeName = noteTypeName;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getNoteTypeMasterUuid() {
        return noteTypeMasterUuid;
    }

    public void setNoteTypeMasterUuid(UUID noteTypeMasterUuid) {
        this.noteTypeMasterUuid = noteTypeMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteTypeMasterDTO)) {
            return false;
        }

        NoteTypeMasterDTO noteTypeMasterDTO = (NoteTypeMasterDTO) o;
        if (this.noteTypeId == null) {
            return false;
        }
        return Objects.equals(this.noteTypeId, noteTypeMasterDTO.noteTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.noteTypeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteTypeMasterDTO{" +
            "noteTypeId=" + getNoteTypeId() +
            ", noteTypeName='" + getNoteTypeName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", noteTypeMasterUuid='" + getNoteTypeMasterUuid() + "'" +
            "}";
    }
}
