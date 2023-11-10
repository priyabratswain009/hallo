package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A StateMaster.
 */
@Table("state_master")
public class StateMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("state_id")
    private Long stateId;

    @Column("state_code")
    private String stateCode;

    @Column("state_name")
    private String stateName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getStateId() {
        return this.stateId;
    }

    public StateMaster stateId(Long stateId) {
        this.setStateId(stateId);
        return this;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public StateMaster stateCode(String stateCode) {
        this.setStateCode(stateCode);
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return this.stateName;
    }

    public StateMaster stateName(String stateName) {
        this.setStateName(stateName);
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StateMaster)) {
            return false;
        }
        return stateId != null && stateId.equals(((StateMaster) o).stateId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StateMaster{" +
            "stateId=" + getStateId() +
            ", stateCode='" + getStateCode() + "'" +
            ", stateName='" + getStateName() + "'" +
            "}";
    }
}
