package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.NoteReasonMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoteReasonMasterDTO implements Serializable {

    private Long noteReasonId;

    private String noteReasonName;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID noteReasonMasterUuid;

    public Long getNoteReasonId() {
        return noteReasonId;
    }

    public void setNoteReasonId(Long noteReasonId) {
        this.noteReasonId = noteReasonId;
    }

    public String getNoteReasonName() {
        return noteReasonName;
    }

    public void setNoteReasonName(String noteReasonName) {
        this.noteReasonName = noteReasonName;
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

    public UUID getNoteReasonMasterUuid() {
        return noteReasonMasterUuid;
    }

    public void setNoteReasonMasterUuid(UUID noteReasonMasterUuid) {
        this.noteReasonMasterUuid = noteReasonMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteReasonMasterDTO)) {
            return false;
        }

        NoteReasonMasterDTO noteReasonMasterDTO = (NoteReasonMasterDTO) o;
        if (this.noteReasonId == null) {
            return false;
        }
        return Objects.equals(this.noteReasonId, noteReasonMasterDTO.noteReasonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.noteReasonId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteReasonMasterDTO{" +
            "noteReasonId=" + getNoteReasonId() +
            ", noteReasonName='" + getNoteReasonName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", noteReasonMasterUuid='" + getNoteReasonMasterUuid() + "'" +
            "}";
    }
}
