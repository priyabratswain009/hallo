package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ElligibilityResponseBenefitinformation.
 */
@Table("t_elligibility_response_benefitinformation")
public class ElligibilityResponseBenefitinformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("elligibility_response_benefitinformation_id")
    private Long elligibilityResponseBenefitinformationId;

    @Column("elligibility_response_status_id")
    private Long elligibilityResponseStatusId;

    @Column("benefitsinformation_code")
    private String benefitsinformationCode;

    @Column("benefitsinformation_name")
    private String benefitsinformationName;

    @Column("coverage_level_code")
    private String coverageLevelCode;

    @Column("service_type_codes")
    private String serviceTypeCodes;

    @Column("insurance_type_code")
    private String insuranceTypeCode;

    @Column("plan_coverage")
    private String planCoverage;

    @Column("benefits_date_information")
    private String benefitsDateInformation;

    @Column("elligibility_response_benefit_information_uuid")
    private UUID elligibilityResponseBenefitInformationUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getElligibilityResponseBenefitinformationId() {
        return this.elligibilityResponseBenefitinformationId;
    }

    public ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformationId(Long elligibilityResponseBenefitinformationId) {
        this.setElligibilityResponseBenefitinformationId(elligibilityResponseBenefitinformationId);
        return this;
    }

    public void setElligibilityResponseBenefitinformationId(Long elligibilityResponseBenefitinformationId) {
        this.elligibilityResponseBenefitinformationId = elligibilityResponseBenefitinformationId;
    }

    public Long getElligibilityResponseStatusId() {
        return this.elligibilityResponseStatusId;
    }

    public ElligibilityResponseBenefitinformation elligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.setElligibilityResponseStatusId(elligibilityResponseStatusId);
        return this;
    }

    public void setElligibilityResponseStatusId(Long elligibilityResponseStatusId) {
        this.elligibilityResponseStatusId = elligibilityResponseStatusId;
    }

    public String getBenefitsinformationCode() {
        return this.benefitsinformationCode;
    }

    public ElligibilityResponseBenefitinformation benefitsinformationCode(String benefitsinformationCode) {
        this.setBenefitsinformationCode(benefitsinformationCode);
        return this;
    }

    public void setBenefitsinformationCode(String benefitsinformationCode) {
        this.benefitsinformationCode = benefitsinformationCode;
    }

    public String getBenefitsinformationName() {
        return this.benefitsinformationName;
    }

    public ElligibilityResponseBenefitinformation benefitsinformationName(String benefitsinformationName) {
        this.setBenefitsinformationName(benefitsinformationName);
        return this;
    }

    public void setBenefitsinformationName(String benefitsinformationName) {
        this.benefitsinformationName = benefitsinformationName;
    }

    public String getCoverageLevelCode() {
        return this.coverageLevelCode;
    }

    public ElligibilityResponseBenefitinformation coverageLevelCode(String coverageLevelCode) {
        this.setCoverageLevelCode(coverageLevelCode);
        return this;
    }

    public void setCoverageLevelCode(String coverageLevelCode) {
        this.coverageLevelCode = coverageLevelCode;
    }

    public String getServiceTypeCodes() {
        return this.serviceTypeCodes;
    }

    public ElligibilityResponseBenefitinformation serviceTypeCodes(String serviceTypeCodes) {
        this.setServiceTypeCodes(serviceTypeCodes);
        return this;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public String getInsuranceTypeCode() {
        return this.insuranceTypeCode;
    }

    public ElligibilityResponseBenefitinformation insuranceTypeCode(String insuranceTypeCode) {
        this.setInsuranceTypeCode(insuranceTypeCode);
        return this;
    }

    public void setInsuranceTypeCode(String insuranceTypeCode) {
        this.insuranceTypeCode = insuranceTypeCode;
    }

    public String getPlanCoverage() {
        return this.planCoverage;
    }

    public ElligibilityResponseBenefitinformation planCoverage(String planCoverage) {
        this.setPlanCoverage(planCoverage);
        return this;
    }

    public void setPlanCoverage(String planCoverage) {
        this.planCoverage = planCoverage;
    }

    public String getBenefitsDateInformation() {
        return this.benefitsDateInformation;
    }

    public ElligibilityResponseBenefitinformation benefitsDateInformation(String benefitsDateInformation) {
        this.setBenefitsDateInformation(benefitsDateInformation);
        return this;
    }

    public void setBenefitsDateInformation(String benefitsDateInformation) {
        this.benefitsDateInformation = benefitsDateInformation;
    }

    public UUID getElligibilityResponseBenefitInformationUuid() {
        return this.elligibilityResponseBenefitInformationUuid;
    }

    public ElligibilityResponseBenefitinformation elligibilityResponseBenefitInformationUuid(
        UUID elligibilityResponseBenefitInformationUuid
    ) {
        this.setElligibilityResponseBenefitInformationUuid(elligibilityResponseBenefitInformationUuid);
        return this;
    }

    public void setElligibilityResponseBenefitInformationUuid(UUID elligibilityResponseBenefitInformationUuid) {
        this.elligibilityResponseBenefitInformationUuid = elligibilityResponseBenefitInformationUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElligibilityResponseBenefitinformation)) {
            return false;
        }
        return (
            elligibilityResponseBenefitinformationId != null &&
            elligibilityResponseBenefitinformationId.equals(
                ((ElligibilityResponseBenefitinformation) o).elligibilityResponseBenefitinformationId
            )
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElligibilityResponseBenefitinformation{" +
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
