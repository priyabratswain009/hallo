package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MemberElligibilityMasterDTO implements Serializable {

    private Long claimElligibilityMasterId;

    private Long salesOrderId;

    private String elligibilityControlNumber;

    private Long tradingPartnerServiceId;

    private String tradingPartnerName;

    private String providerOrganizationName;

    private String providerNpi;

    private String providerType;

    private String subscriberFirstName;

    private String subscriberLastName;

    private String subscriberMemberId;

    private String subscriberIdcard;

    private LocalDate subscriberDob;

    private String subscriberGender;

    private LocalDate subscriberPlanIssueDate;

    private String insuredFirstName;

    private String insuredLastName;

    private String insuredGender;

    private LocalDate insuredDob;

    private String insuredRelationshipwithSubscriber;

    private LocalDate dateOfService;

    private String serviceTypeCodes;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID memberElligibilityMasterUuid;

    public Long getClaimElligibilityMasterId() {
        return claimElligibilityMasterId;
    }

    public void setClaimElligibilityMasterId(Long claimElligibilityMasterId) {
        this.claimElligibilityMasterId = claimElligibilityMasterId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getElligibilityControlNumber() {
        return elligibilityControlNumber;
    }

    public void setElligibilityControlNumber(String elligibilityControlNumber) {
        this.elligibilityControlNumber = elligibilityControlNumber;
    }

    public Long getTradingPartnerServiceId() {
        return tradingPartnerServiceId;
    }

    public void setTradingPartnerServiceId(Long tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getProviderOrganizationName() {
        return providerOrganizationName;
    }

    public void setProviderOrganizationName(String providerOrganizationName) {
        this.providerOrganizationName = providerOrganizationName;
    }

    public String getProviderNpi() {
        return providerNpi;
    }

    public void setProviderNpi(String providerNpi) {
        this.providerNpi = providerNpi;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getSubscriberFirstName() {
        return subscriberFirstName;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return subscriberLastName;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberMemberId() {
        return subscriberMemberId;
    }

    public void setSubscriberMemberId(String subscriberMemberId) {
        this.subscriberMemberId = subscriberMemberId;
    }

    public String getSubscriberIdcard() {
        return subscriberIdcard;
    }

    public void setSubscriberIdcard(String subscriberIdcard) {
        this.subscriberIdcard = subscriberIdcard;
    }

    public LocalDate getSubscriberDob() {
        return subscriberDob;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSubscriberGender() {
        return subscriberGender;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberPlanIssueDate() {
        return subscriberPlanIssueDate;
    }

    public void setSubscriberPlanIssueDate(LocalDate subscriberPlanIssueDate) {
        this.subscriberPlanIssueDate = subscriberPlanIssueDate;
    }

    public String getInsuredFirstName() {
        return insuredFirstName;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return insuredLastName;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public LocalDate getInsuredDob() {
        return insuredDob;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredRelationshipwithSubscriber() {
        return insuredRelationshipwithSubscriber;
    }

    public void setInsuredRelationshipwithSubscriber(String insuredRelationshipwithSubscriber) {
        this.insuredRelationshipwithSubscriber = insuredRelationshipwithSubscriber;
    }

    public LocalDate getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(LocalDate dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getServiceTypeCodes() {
        return serviceTypeCodes;
    }

    public void setServiceTypeCodes(String serviceTypeCodes) {
        this.serviceTypeCodes = serviceTypeCodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getMemberElligibilityMasterUuid() {
        return memberElligibilityMasterUuid;
    }

    public void setMemberElligibilityMasterUuid(UUID memberElligibilityMasterUuid) {
        this.memberElligibilityMasterUuid = memberElligibilityMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberElligibilityMasterDTO)) {
            return false;
        }

        MemberElligibilityMasterDTO memberElligibilityMasterDTO = (MemberElligibilityMasterDTO) o;
        if (this.claimElligibilityMasterId == null) {
            return false;
        }
        return Objects.equals(this.claimElligibilityMasterId, memberElligibilityMasterDTO.claimElligibilityMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.claimElligibilityMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MemberElligibilityMasterDTO{" +
            "claimElligibilityMasterId=" + getClaimElligibilityMasterId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", elligibilityControlNumber='" + getElligibilityControlNumber() + "'" +
            ", tradingPartnerServiceId=" + getTradingPartnerServiceId() +
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
