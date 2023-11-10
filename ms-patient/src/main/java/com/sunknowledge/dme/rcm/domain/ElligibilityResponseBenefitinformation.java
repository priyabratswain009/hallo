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
@SuppressWarnings("common-java:DuplicatedBlocks")
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

    @Column("coveragelevelcode")
    private String coveragelevelcode;

    @Column("coveragelevel")
    private String coveragelevel;

    @Column("servicetypecodes")
    private String servicetypecodes;

    @Column("servicetypes")
    private String servicetypes;

    @Column("insurancetypecode")
    private String insurancetypecode;

    @Column("insurancetype")
    private String insurancetype;

    @Column("plancoverage")
    private Long plancoverage;

    @Column("benefits_local_dateinformation")
    private String benefitsLocalDateinformation;

    @Column("elligibility_response_benefitinformation_uuid")
    private UUID elligibilityResponseBenefitinformationUuid;

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

    public String getCoveragelevelcode() {
        return this.coveragelevelcode;
    }

    public ElligibilityResponseBenefitinformation coveragelevelcode(String coveragelevelcode) {
        this.setCoveragelevelcode(coveragelevelcode);
        return this;
    }

    public void setCoveragelevelcode(String coveragelevelcode) {
        this.coveragelevelcode = coveragelevelcode;
    }

    public String getCoveragelevel() {
        return this.coveragelevel;
    }

    public ElligibilityResponseBenefitinformation coveragelevel(String coveragelevel) {
        this.setCoveragelevel(coveragelevel);
        return this;
    }

    public void setCoveragelevel(String coveragelevel) {
        this.coveragelevel = coveragelevel;
    }

    public String getServicetypecodes() {
        return this.servicetypecodes;
    }

    public ElligibilityResponseBenefitinformation servicetypecodes(String servicetypecodes) {
        this.setServicetypecodes(servicetypecodes);
        return this;
    }

    public void setServicetypecodes(String servicetypecodes) {
        this.servicetypecodes = servicetypecodes;
    }

    public String getServicetypes() {
        return this.servicetypes;
    }

    public ElligibilityResponseBenefitinformation servicetypes(String servicetypes) {
        this.setServicetypes(servicetypes);
        return this;
    }

    public void setServicetypes(String servicetypes) {
        this.servicetypes = servicetypes;
    }

    public String getInsurancetypecode() {
        return this.insurancetypecode;
    }

    public ElligibilityResponseBenefitinformation insurancetypecode(String insurancetypecode) {
        this.setInsurancetypecode(insurancetypecode);
        return this;
    }

    public void setInsurancetypecode(String insurancetypecode) {
        this.insurancetypecode = insurancetypecode;
    }

    public String getInsurancetype() {
        return this.insurancetype;
    }

    public ElligibilityResponseBenefitinformation insurancetype(String insurancetype) {
        this.setInsurancetype(insurancetype);
        return this;
    }

    public void setInsurancetype(String insurancetype) {
        this.insurancetype = insurancetype;
    }

    public Long getPlancoverage() {
        return this.plancoverage;
    }

    public ElligibilityResponseBenefitinformation plancoverage(Long plancoverage) {
        this.setPlancoverage(plancoverage);
        return this;
    }

    public void setPlancoverage(Long plancoverage) {
        this.plancoverage = plancoverage;
    }

    public String getBenefitsLocalDateinformation() {
        return this.benefitsLocalDateinformation;
    }

    public ElligibilityResponseBenefitinformation benefitsLocalDateinformation(String benefitsLocalDateinformation) {
        this.setBenefitsLocalDateinformation(benefitsLocalDateinformation);
        return this;
    }

    public void setBenefitsLocalDateinformation(String benefitsLocalDateinformation) {
        this.benefitsLocalDateinformation = benefitsLocalDateinformation;
    }

    public UUID getElligibilityResponseBenefitinformationUuid() {
        return this.elligibilityResponseBenefitinformationUuid;
    }

    public ElligibilityResponseBenefitinformation elligibilityResponseBenefitinformationUuid(
        UUID elligibilityResponseBenefitinformationUuid
    ) {
        this.setElligibilityResponseBenefitinformationUuid(elligibilityResponseBenefitinformationUuid);
        return this;
    }

    public void setElligibilityResponseBenefitinformationUuid(UUID elligibilityResponseBenefitinformationUuid) {
        this.elligibilityResponseBenefitinformationUuid = elligibilityResponseBenefitinformationUuid;
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
