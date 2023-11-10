package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NoteReasonMaster.
 */
@Entity
@Table(name = "t_note_reason_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoteReasonMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "note_reason_id")
    private Long noteReasonId;

    @Column(name = "note_reason_name")
    private String noteReasonName;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "note_reason_master_uuid")
    private UUID noteReasonMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getNoteReasonId() {
        return this.noteReasonId;
    }

    public NoteReasonMaster noteReasonId(Long noteReasonId) {
        this.setNoteReasonId(noteReasonId);
        return this;
    }

    public void setNoteReasonId(Long noteReasonId) {
        this.noteReasonId = noteReasonId;
    }

    public String getNoteReasonName() {
        return this.noteReasonName;
    }

    public NoteReasonMaster noteReasonName(String noteReasonName) {
        this.setNoteReasonName(noteReasonName);
        return this;
    }

    public void setNoteReasonName(String noteReasonName) {
        this.noteReasonName = noteReasonName;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public NoteReasonMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public NoteReasonMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public NoteReasonMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public NoteReasonMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public NoteReasonMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public NoteReasonMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public NoteReasonMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getNoteReasonMasterUuid() {
        return this.noteReasonMasterUuid;
    }

    public NoteReasonMaster noteReasonMasterUuid(UUID noteReasonMasterUuid) {
        this.setNoteReasonMasterUuid(noteReasonMasterUuid);
        return this;
    }

    public void setNoteReasonMasterUuid(UUID noteReasonMasterUuid) {
        this.noteReasonMasterUuid = noteReasonMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteReasonMaster)) {
            return false;
        }
        return noteReasonId != null && noteReasonId.equals(((NoteReasonMaster) o).noteReasonId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteReasonMaster{" +
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
