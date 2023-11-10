package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

/**
 * A UszipMaster.
 */
@Entity
@Table(name = "t_uszip_master")
public class UszipMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "uszip_master_id")
    private Long uszipMasterId;

    @Column(name = "zip_code")
    private Long zipCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "status")
    private String status;

    @Column(name = "uszip_master_uuid")
    private UUID uszipMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getUszipMasterId() {
        return this.uszipMasterId;
    }

    public UszipMaster uszipMasterId(Long uszipMasterId) {
        this.setUszipMasterId(uszipMasterId);
        return this;
    }

    public void setUszipMasterId(Long uszipMasterId) {
        this.uszipMasterId = uszipMasterId;
    }

    public Long getZipCode() {
        return this.zipCode;
    }

    public UszipMaster zipCode(Long zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public UszipMaster cityName(String cityName) {
        this.setCityName(cityName);
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public UszipMaster stateCode(String stateCode) {
        this.setStateCode(stateCode);
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return this.stateName;
    }

    public UszipMaster stateName(String stateName) {
        this.setStateName(stateName);
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStatus() {
        return this.status;
    }

    public UszipMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUszipMasterUuid() {
        return this.uszipMasterUuid;
    }

    public UszipMaster uszipMasterUuid(UUID uszipMasterUuid) {
        this.setUszipMasterUuid(uszipMasterUuid);
        return this;
    }

    public void setUszipMasterUuid(UUID uszipMasterUuid) {
        this.uszipMasterUuid = uszipMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UszipMaster)) {
            return false;
        }
        return uszipMasterId != null && uszipMasterId.equals(((UszipMaster) o).uszipMasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UszipMaster{" +
            "uszipMasterId=" + getUszipMasterId() +
            ", zipCode=" + getZipCode() +
            ", cityName='" + getCityName() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", status='" + getStatus() + "'" +
            ", uszipMasterUuid='" + getUszipMasterUuid() + "'" +
            "}";
    }
}
