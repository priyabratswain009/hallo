package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.UszipMaster} entity.
 */
public class UszipMasterDTO implements Serializable {

    private Long uszipMasterId;

    private Long zipCode;

    private String cityName;

    private String stateCode;

    private String stateName;

    private String status;

    private UUID uszipMasterUuid;

    public Long getUszipMasterId() {
        return uszipMasterId;
    }

    public void setUszipMasterId(Long uszipMasterId) {
        this.uszipMasterId = uszipMasterId;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUszipMasterUuid() {
        return uszipMasterUuid;
    }

    public void setUszipMasterUuid(UUID uszipMasterUuid) {
        this.uszipMasterUuid = uszipMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UszipMasterDTO)) {
            return false;
        }

        UszipMasterDTO uszipMasterDTO = (UszipMasterDTO) o;
        if (this.uszipMasterId == null) {
            return false;
        }
        return Objects.equals(this.uszipMasterId, uszipMasterDTO.uszipMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uszipMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UszipMasterDTO{" +
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
