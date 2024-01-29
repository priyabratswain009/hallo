package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A MemberElligibilityMaster.
 */
@Table("t_member_elligibility_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MemberElligibilityMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("claim_elligibility_master_id")
    private Long claimElligibilityMasterId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("elligibility_control_number")
    private String elligibilityControlNumber;

    @Column("trading_partner_service_id")
    private String tradingPartnerServiceId;

    @Column("trading_partner_name")
    private String tradingPartnerName;

    @Column("provider_organization_name")
    private String providerOrganizationName;

    @Column("provider_npi")
    private String providerNpi;

    @Column("provider_type")
    private String providerType;

    @Column("subscriber_first_name")
    private String subscriberFirstName;

    @Column("subscriber_last_name")
    private String subscriberLastName;

    @Column("subscriber_member_id")
    private String subscriberMemberId;

    @Column("subscriber_idcard")
    private String subscriberIdcard;

    @Column("subscriber_dob")
    private LocalDate subscriberDob;

    @Column("subscriber_gender")
    private String subscriberGender;

    @Column("subscriber_plan_issue_date")
    private LocalDate subscriberPlanIssueDate;

    @Column("insured_first_name")
    private String insuredFirstName;

    @Column("insured_last_name")
    private String insuredLastName;

    @Column("insured_gender")
    private String insuredGender;

    @Column("insured_dob")
    private LocalDate insuredDob;

    @Column("insured_relationshipwith_subscriber")
    private String insuredRelationshipwithSubscriber;

    @Column("date_of_service")
    private LocalDate dateOfService;

    @Column("service_type_codes")
    private String serviceTypeCodes;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("member_elligibility_master_uuid")
    private UUID memberElligibilityMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimElligibilityMasterId() {
        return this.claimElligibilityMasterId;
    }

    public MemberElligibilityMaster claimElligibilityMasterId(Long claimElligibilityMasterId) {
        this.setClaimElligibilityMasterId(claimElligibilityMasterId);
        return this;
    }

    public void setClaimElligibilityMasterId(Long claimElligibilityMasterId) {
        this.claimElligibilityMasterId = claimElligibilityMasterId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public MemberElligibilityMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getElligibilityControlNumber() {
        return this.elligibilityControlNumber;
    }

    public MemberElligibilityMaster elligibilityControlNumber(String elligibilityControlNumber) {
        this.setElligibilityControlNumber(elligibilityControlNumber);
        return this;
    }

    public void setElligibilityControlNumber(String elligibilityControlNumber) {
        this.elligibilityControlNumber = elligibilityControlNumber;
    }

    public String getTradingPartnerServiceId() {
        return this.tradingPartnerServiceId;
    }

    public MemberElligibilityMaster tradingPartnerServiceId(String tradingPartnerServiceId) {
        this.setTradingPartnerServiceId(tradingPartnerServiceId);
        return this;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }

    public MemberElligibilityMaster tradingPartnerName(String tradingPartnerName) {
        this.setTradingPartnerName(tradingPartnerName);
        return this;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getProviderOrganizationName() {
        return this.providerOrganizationName;
    }

    public MemberElligibilityMaster providerOrganizationName(String providerOrganizationName) {
        this.setProviderOrganizationName(providerOrganizationName);
        return this;
    }

    public void setProviderOrganizationName(String providerOrganizationName) {
        this.providerOrganizationName = providerOrganizationName;
    }

    public String getProviderNpi() {
        return this.providerNpi;
    }

    public MemberElligibilityMaster providerNpi(String providerNpi) {
        this.setProviderNpi(providerNpi);
        return this;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getProviderType() {
        return this.providerType;
    }

    public MemberElligibilityMaster providerType(String providerType) {
        this.setProviderType(providerType);
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public MemberElligibilityMaster subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public MemberElligibilityMaster subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberMemberId() {
        return this.subscriberMemberId;
    }

    public MemberElligibilityMaster subscriberMemberId(String subscriberMemberId) {
        this.setSubscriberMemberId(subscriberMemberId);
        return this;
    }

    public void setSubscriberMemberId(String subscriberMemberId) {
        this.subscriberMemberId = subscriberMemberId;
    }

    public String getSubscriberIdcard() {
        return this.subscriberIdcard;
    }

    public MemberElligibilityMaster subscriberIdcard(String subscriberIdcard) {
        this.setSubscriberIdcard(subscriberIdcard);
        return this;
    }

    public void setSubscriberIdcard(String subscriberIdcard) {
        this.subscriberIdcard = subscriberIdcard;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public MemberElligibilityMaster subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public MemberElligibilityMaster subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberPlanIssueDate() {
        return this.subscriberPlanIssueDate;
    }

    public MemberElligibilityMaster subscriberPlanIssueDate(LocalDate subscriberPlanIssueDate) {
        this.setSubscriberPlanIssueDate(subscriberPlanIssueDate);
        return this;
    }

    public void setSubscriberPlanIssueDate(LocalDate subscriberPlanIssueDate) {
        this.subscriberPlanIssueDate = subscriberPlanIssueDate;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public MemberElligibilityMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public MemberElligibilityMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public MemberElligibilityMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public MemberElligibilityMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredRelationshipwithSubscriber() {
        return this.insuredRelationshipwithSubscriber;
    }

    public MemberElligibilityMaster insuredRelationshipwithSubscriber(String insuredRelationshipwithSubscriber) {
        this.setInsuredRelationshipwithSubscriber(insuredRelationshipwithSubscriber);
        return this;
    }

    public void setInsuredRelationshipwithSubscriber(String insuredRelationshipwithSubscriber) {
        this.insuredRelationshipwithSubscriber = insuredRelationshipwithSubscriber;
    }

    public LocalDate getDateOfService() {
        return this.dateOfService;
    }

    public MemberElligibilityMaster dateOfService(LocalDate dateOfService) {
        this.setDateOfService(dateOfService);
        return this;
    }

    public void setDateOfService(LocalDate dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getServiceTypeCodes() {
        return this.serviceTypeCodes;
    }

    public MemberElligibilityMaster serviceTypeCodes(String serviceTypeCodes) {
        this.setServiceTypeCodes(serviceTypeCodes);
        return this;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public String getStatus() {
        return this.status;
    }

    public MemberElligibilityMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public MemberElligibilityMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public MemberElligibilityMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public MemberElligibilityMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public MemberElligibilityMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public MemberElligibilityMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public MemberElligibilityMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getMemberElligibilityMasterUuid() {
        return this.memberElligibilityMasterUuid;
    }

    public MemberElligibilityMaster memberElligibilityMasterUuid(UUID memberElligibilityMasterUuid) {
        this.setMemberElligibilityMasterUuid(memberElligibilityMasterUuid);
        return this;
    }

    public void setMemberElligibilityMasterUuid(UUID memberElligibilityMasterUuid) {
        this.memberElligibilityMasterUuid = memberElligibilityMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberElligibilityMaster)) {
            return false;
        }
        return (
            claimElligibilityMasterId != null && claimElligibilityMasterId.equals(((MemberElligibilityMaster) o).claimElligibilityMasterId)
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
        return "MemberElligibilityMaster{" +
            "claimElligibilityMasterId=" + getClaimElligibilityMasterId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", elligibilityControlNumber='" + getElligibilityControlNumber() + "'" +
            ", tradingPartnerServiceId='" + getTradingPartnerServiceId() + "'" +
            ", tradingPartnerName='" + getTradingPartnerName() + "'" +
            ", providerOrganizationName='" + getProviderOrganizationName() + "'" +
            ", providerNpi='" + getProviderNpi() + "'" +
            ", providerType='" + getProviderType() + "'" +
            ", subscriberFirstName='" + getSubscriberFirstName() + "'" +
            ", subscriberLastName='" + getSubscriberLastName() + "'" +
            ", subscriberMemberId='" + getSubscriberMemberId() + "'" +
            ", subscriberIdcard='" + getSubscriberIdcard() + "'" +
            ", subscriberDob='" + getSubscriberDob() + "'" +
            ", subscriberGender='" + getSubscriberGender() + "'" +
            ", subscriberPlanIssueDate='" + getSubscriberPlanIssueDate() + "'" +
            ", insuredFirstName='" + getInsuredFirstName() + "'" +
            ", insuredLastName='" + getInsuredLastName() + "'" +
            ", insuredGender='" + getInsuredGender() + "'" +
            ", insuredDob='" + getInsuredDob() + "'" +
            ", insuredRelationshipwithSubscriber='" + getInsuredRelationshipwithSubscriber() + "'" +
            ", dateOfService='" + getDateOfService() + "'" +
            ", serviceTypeCodes='" + getServiceTypeCodes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", memberElligibilityMasterUuid='" + getMemberElligibilityMasterUuid() + "'" +
            "}";
    }
}
