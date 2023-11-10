package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NoteTypeMaster.
 */
@Entity
@Table(name = "t_note_type_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoteTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "note_type_id")
    private Long noteTypeId;

    @Column(name = "note_type_name")
    private String noteTypeName;

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

    @Column(name = "note_type_master_uuid")
    private UUID noteTypeMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getNoteTypeId() {
        return this.noteTypeId;
    }

    public NoteTypeMaster noteTypeId(Long noteTypeId) {
        this.setNoteTypeId(noteTypeId);
        return this;
    }

    public void setNoteTypeId(Long noteTypeId) {
        this.noteTypeId = noteTypeId;
    }

    public String getNoteTypeName() {
        return this.noteTypeName;
    }

    public NoteTypeMaster noteTypeName(String noteTypeName) {
        this.setNoteTypeName(noteTypeName);
        return this;
    }

    public void setNoteTypeName(String noteTypeName) {
        this.noteTypeName = noteTypeName;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public NoteTypeMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public NoteTypeMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public NoteTypeMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public NoteTypeMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public NoteTypeMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public NoteTypeMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public NoteTypeMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getNoteTypeMasterUuid() {
        return this.noteTypeMasterUuid;
    }

    public NoteTypeMaster noteTypeMasterUuid(UUID noteTypeMasterUuid) {
        this.setNoteTypeMasterUuid(noteTypeMasterUuid);
        return this;
    }

    public void setNoteTypeMasterUuid(UUID noteTypeMasterUuid) {
        this.noteTypeMasterUuid = noteTypeMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteTypeMaster)) {
            return false;
        }
        return noteTypeId != null && noteTypeId.equals(((NoteTypeMaster) o).noteTypeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteTypeMaster{" +
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
