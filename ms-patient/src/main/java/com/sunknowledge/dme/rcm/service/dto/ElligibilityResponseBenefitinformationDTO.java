package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ElligibilityResponseBenefitinformationDTO implements Serializable {

    private Long elligibilityResponseBenefitinformationId;

    private Long elligibilityResponseStatusId;

    private String benefitsinformationCode;

    private String benefitsinformationName;

    private String coveragelevelcode;

    private String coveragelevel;

    private String servicetypecodes;

    private String servicetypes;

    private String insurancetypecode;

    private String insurancetype;

    private Long plancoverage;

    private String benefitsLocalDateinformation;

    private UUID elligibilityResponseBenefitinformationUuid;

    public Long getElligibilityResponseBenefitinformationId() {
        return elligibilityResponseBenefitinformationId;
    }

    public void setElligibilityResponseBenefitinformationId(Long elligibilityResponseBenefitinformationId) {
        this.elligibilityResponseBenefitinformationId = elligibilityResponseBenefitinformationId;
    }

    public Long getElligibilityResponseStatusId() {
        return elligibilityResponseStatusId;
    }

    public void setElligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.elligibilityResponseStatusId = elligibilityResponseStatusId;
    }

    public String getBenefitsinformationCode() {
        return benefitsinformationCode;
    }

    public void setBenefitsinformationCode(String benefitsinformationCode) {
        this.benefitsinformationCode = benefitsinformationCode;
    }

    public String getBenefitsinformationName() {
        return benefitsinformationName;
    }

    public void setBenefitsinformationName(String benefitsinformationName) {
        this.benefitsinformationName = benefitsinformationName;
    }

    public String getCoveragelevelcode() {
        return coveragelevelcode;
    }

    public void setCoveragelevelcode(String coveragelevelcode) {
        this.coveragelevelcode = coveragelevelcode;
    }

    public String getCoveragelevel() {
        return coveragelevel;
    }

    public void setCoveragelevel(String coveragelevel) {
        this.coveragelevel = coveragelevel;
    }

    public String getServicetypecodes() {
        return servicetypecodes;
    }

    public void setServicetypecodes(String servicetypecodes) {
        this.servicetypecodes = servicetypecodes;
    }

    public String getServicetypes() {
        return servicetypes;
    }

    public void setServicetypes(String servicetypes) {
        this.servicetypes = servicetypes;
    }

    public String getInsurancetypecode() {
        return insurancetypecode;
    }

    public void setInsurancetypecode(String insurancetypecode) {
        this.insurancetypecode = insurancetypecode;
    }

    public String getInsurancetype() {
        return insurancetype;
    }

    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }

    public Long getPlancoverage() {
        return plancoverage;
    }

    public void setPlancoverage(Long plancoverage) {
        this.plancoverage = plancoverage;
    }

    public String getBenefitsLocalDateinformation() {
        return benefitsLocalDateinformation;
    }

    public void setBenefitsLocalDateinformation(String benefitsLocalDateinformation) {
        this.benefitsLocalDateinformation = benefitsLocalDateinformation;
    }

    public UUID getElligibilityResponseBenefitinformationUuid() {
        return elligibilityResponseBenefitinformationUuid;
    }

    public void setElligibilityResponseBenefitinformationUuid(UUID elligibilityResponseBenefitinformationUuid) {
        this.elligibilityResponseBenefitinformationUuid = elligibilityResponseBenefitinformationUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElligibilityResponseBenefitinformationDTO)) {
            return false;
        }

        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO = (ElligibilityResponseBenefitinformationDTO) o;
        if (this.elligibilityResponseBenefitinformationId == null) {
            return false;
        }
        return Objects.equals(
            this.elligibilityResponseBenefitinformationId,
            elligibilityResponseBenefitinformationDTO.elligibilityResponseBenefitinformationId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.elligibilityResponseBenefitinformationId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElligibilityResponseBenefitinformationDTO{" +
            "elligibilityResponseBenefitinformationId=" + getElligibilityResponseBenefitinformationId() +
            ", elligibilityResponseStatusId=" + getElligibilityResponseStatusId() +
            ", benefitsinformationCode='" + getBenefitsinformationCode() + "'" +
            ", benefitsinformationName='" + getBenefitsinformationName() + "'" +
            ", coveragelevelcode='" + getCoveragelevelcode() + "'" +
            ", coveragelevel='" + getCoveragelevel() + "'" +
            ", servicetypecodes='" + getServicetypecodes() + "'" +
            ", servicetypes='" + getServicetypes() + "'" +
            ", insurancetypecode='" + getInsurancetypecode() + "'" +
            ", insurancetype='" + getInsurancetype() + "'" +
            ", plancoverage=" + getPlancoverage() +
            ", benefitsLocalDateinformation='" + getBenefitsLocalDateinformation() + "'" +
            ", elligibilityResponseBenefitinformationUuid='" + getElligibilityResponseBenefitinformationUuid() + "'" +
            "}";
    }
}
