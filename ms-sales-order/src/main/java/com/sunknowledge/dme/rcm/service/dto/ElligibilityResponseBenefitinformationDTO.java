package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation} entity.
 */
public class ElligibilityResponseBenefitinformationDTO implements Serializable {

    private Long elligibilityResponseBenefitinformationId;

    private Long elligibilityResponseStatusId;

    private String benefitsinformationCode;

    private String benefitsinformationName;

    private String coverageLevelCode;

    private String serviceTypeCodes;

    private String insuranceTypeCode;

    private String planCoverage;

    private String benefitsDateInformation;

    private UUID elligibilityResponseBenefitInformationUuid;

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

    public String getCoverageLevelCode() {
        return coverageLevelCode;
    }

    public void setCoverageLevelCode(String coverageLevelCode) {
        this.coverageLevelCode = coverageLevelCode;
    }

    public String getServiceTypeCodes() {
        return serviceTypeCodes;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public String getInsuranceTypeCode() {
        return insuranceTypeCode;
    }

    public void setInsuranceTypeCode(String insuranceTypeCode) {
        this.insuranceTypeCode = insuranceTypeCode;
    }

    public String getPlanCoverage() {
        return planCoverage;
    }

    public void setPlanCoverage(String planCoverage) {
        this.planCoverage = planCoverage;
    }

    public String getBenefitsDateInformation() {
        return benefitsDateInformation;
    }

    public void setBenefitsDateInformation(String benefitsDateInformation) {
        this.benefitsDateInformation = benefitsDateInformation;
    }

    public UUID getElligibilityResponseBenefitInformationUuid() {
        return elligibilityResponseBenefitInformationUuid;
    }

    public void setElligibilityResponseBenefitInformationUuid(UUID elligibilityResponseBenefitInformationUuid) {
        this.elligibilityResponseBenefitInformationUuid = elligibilityResponseBenefitInformationUuid;
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
            ", coverageLevelCode='" + getCoverageLevelCode() + "'" +
            ", serviceTypeCodes='" + getServiceTypeCodes() + "'" +
            ", insuranceTypeCode='" + getInsuranceTypeCode() + "'" +
            ", planCoverage='" + getPlanCoverage() + "'" +
            ", benefitsDateInformation='" + getBenefitsDateInformation() + "'" +
            ", elligibilityResponseBenefitInformationUuid='" + getElligibilityResponseBenefitInformationUuid() + "'" +
            "}";
    }
}
