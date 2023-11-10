package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FunctionalAbility.
 */
@Table("t_functional_ability")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FunctionalAbility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("functional_ability_id")
    private Long functionalAbilityId;

    @Column("functional_ability_code")
    private String functionalAbilityCode;

    @Column("functional_ability_name")
    private String functionalAbilityName;

    @Column("description")
    private String description;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("functional_ability_uuid")
    private UUID functionalAbilityUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getFunctionalAbilityId() {
        return this.functionalAbilityId;
    }

    public FunctionalAbility functionalAbilityId(Long functionalAbilityId) {
        this.setFunctionalAbilityId(functionalAbilityId);
        return this;
    }

    public void setFunctionalAbilityId(Long functionalAbilityId) {
        this.functionalAbilityId = functionalAbilityId;
    }

    public String getFunctionalAbilityCode() {
        return this.functionalAbilityCode;
    }

    public FunctionalAbility functionalAbilityCode(String functionalAbilityCode) {
        this.setFunctionalAbilityCode(functionalAbilityCode);
        return this;
    }

    public void setFunctionalAbilityCode(String functionalAbilityCode) {
        this.functionalAbilityCode = functionalAbilityCode;
    }

    public String getFunctionalAbilityName() {
        return this.functionalAbilityName;
    }

    public FunctionalAbility functionalAbilityName(String functionalAbilityName) {
        this.setFunctionalAbilityName(functionalAbilityName);
        return this;
    }

    public void setFunctionalAbilityName(String functionalAbilityName) {
        this.functionalAbilityName = functionalAbilityName;
    }

    public String getDescription() {
        return this.description;
    }

    public FunctionalAbility description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public FunctionalAbility status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public FunctionalAbility createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public FunctionalAbility createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public FunctionalAbility createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public FunctionalAbility updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public FunctionalAbility updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public FunctionalAbility updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getFunctionalAbilityUuid() {
        return this.functionalAbilityUuid;
    }

    public FunctionalAbility functionalAbilityUuid(UUID functionalAbilityUuid) {
        this.setFunctionalAbilityUuid(functionalAbilityUuid);
        return this;
    }

    public void setFunctionalAbilityUuid(UUID functionalAbilityUuid) {
        this.functionalAbilityUuid = functionalAbilityUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalAbility)) {
            return false;
        }
        return functionalAbilityId != null && functionalAbilityId.equals(((FunctionalAbility) o).functionalAbilityId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalAbility{" +
            "functionalAbilityId=" + getFunctionalAbilityId() +
            ", functionalAbilityCode='" + getFunctionalAbilityCode() + "'" +
            ", functionalAbilityName='" + getFunctionalAbilityName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", functionalAbilityUuid='" + getFunctionalAbilityUuid() + "'" +
            "}";
    }
}
