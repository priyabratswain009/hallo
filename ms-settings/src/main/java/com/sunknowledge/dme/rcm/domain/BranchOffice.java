package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchOffice.
 */
@Entity
@Table(name = "t_branch_office")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_group_id")
    private Long branchGroupId;

    @Column(name = "npi")
    private String npi;

    @Column(name = "ptan")
    private String ptan;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "taxonomy_code_description")
    private String taxonomyCodeDescription;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "tax_id_type")
    private String taxIdType;

    @Column(name = "tax_id_no")
    private String taxIdNo;

    @Column(name = "branch_no")
    private String branchNo;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "branch_office_uuid")
    private UUID branchOfficeUuid;

    @Column(name = "billing_address_line_1")
    private String billingAddressLine1;

    @Column(name = "billing_address_line_2")
    private String billingAddressLine2;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_state")
    private String billingState;

    @Column(name = "billing_zip_code")
    private String billingZipCode;

    @Column(name = "submitter_contact_person_name")
    private String submitterContactPersonName;

    @Column(name = "submitter_contact_phone_no_1")
    private String submitterContactPhoneNo1;

    @Column(name = "submitter_contact_phone_no_2")
    private String submitterContactPhoneNo2;

    @Column(name = "billing_fax")
    private String billingFax;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "zip")
    private String zip;

    @Column(name = "billing_contact_no_1")
    private String billingContactNo1;

    @Column(name = "billing_contact_no_2")
    private String billingContactNo2;

    @Column(name = "billing_email")
    private String billingEmail;

    @Column(name = "branch_company")
    private String branchCompany;

    @Column(name = "branch_group_name")
    private String branchGroupName;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "signature_name")
    private String signatureName;

    @Column(name = "item_location_id")
    private String itemLocationId;

    @Column(name = "item_location_name")
    private String itemLocationName;

    @Column(name = "branch_company_id")
    private Long branchCompanyId;

    @Column(name = "is_dropship_allowed")
    private String isDropshipAllowed;

    @Column(name = "billing_taxonomy_code")
    private String billingTaxonomyCode;

    @Column(name = "billing_npi")
    private String billingNpi;

    @Column(name = "billing_organisation_name")
    private String billingOrganisationName;

    @Column(name = "billing_tax_id_no")
    private String billingTaxIdNo;

    @Column(name = "billing_branch_name")
    private String billingBranchName;

    @Column(name = "fax")
    private String fax;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBranchId() {
        return this.branchId;
    }

    public BranchOffice branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public BranchOffice branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getBranchGroupId() {
        return this.branchGroupId;
    }

    public BranchOffice branchGroupId(Long branchGroupId) {
        this.setBranchGroupId(branchGroupId);
        return this;
    }

    public void setBranchGroupId(Long branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getNpi() {
        return this.npi;
    }

    public BranchOffice npi(String npi) {
        this.setNpi(npi);
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getPtan() {
        return this.ptan;
    }

    public BranchOffice ptan(String ptan) {
        this.setPtan(ptan);
        return this;
    }

    public void setPtan(String ptan) {
        this.ptan = ptan;
    }

    public String getTaxonomyCode() {
        return this.taxonomyCode;
    }

    public BranchOffice taxonomyCode(String taxonomyCode) {
        this.setTaxonomyCode(taxonomyCode);
        return this;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getTaxonomyCodeDescription() {
        return this.taxonomyCodeDescription;
    }

    public BranchOffice taxonomyCodeDescription(String taxonomyCodeDescription) {
        this.setTaxonomyCodeDescription(taxonomyCodeDescription);
        return this;
    }

    public void setTaxonomyCodeDescription(String taxonomyCodeDescription) {
        this.taxonomyCodeDescription = taxonomyCodeDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public BranchOffice status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BranchOffice createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BranchOffice createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BranchOffice updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTaxIdType() {
        return this.taxIdType;
    }

    public BranchOffice taxIdType(String taxIdType) {
        this.setTaxIdType(taxIdType);
        return this;
    }

    public void setTaxIdType(String taxIdType) {
        this.taxIdType = taxIdType;
    }

    public String getTaxIdNo() {
        return this.taxIdNo;
    }

    public BranchOffice taxIdNo(String taxIdNo) {
        this.setTaxIdNo(taxIdNo);
        return this;
    }

    public void setTaxIdNo(String taxIdNo) {
        this.taxIdNo = taxIdNo;
    }

    public String getBranchNo() {
        return this.branchNo;
    }

    public BranchOffice branchNo(String branchNo) {
        this.setBranchNo(branchNo);
        return this;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BranchOffice createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BranchOffice updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BranchOffice updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getBranchOfficeUuid() {
        return this.branchOfficeUuid;
    }

    public BranchOffice branchOfficeUuid(UUID branchOfficeUuid) {
        this.setBranchOfficeUuid(branchOfficeUuid);
        return this;
    }

    public void setBranchOfficeUuid(UUID branchOfficeUuid) {
        this.branchOfficeUuid = branchOfficeUuid;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public BranchOffice billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public BranchOffice billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public BranchOffice billingCity(String billingCity) {
        this.setBillingCity(billingCity);
        return this;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return this.billingState;
    }

    public BranchOffice billingState(String billingState) {
        this.setBillingState(billingState);
        return this;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZipCode() {
        return this.billingZipCode;
    }

    public BranchOffice billingZipCode(String billingZipCode) {
        this.setBillingZipCode(billingZipCode);
        return this;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getSubmitterContactPersonName() {
        return this.submitterContactPersonName;
    }

    public BranchOffice submitterContactPersonName(String submitterContactPersonName) {
        this.setSubmitterContactPersonName(submitterContactPersonName);
        return this;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactPhoneNo1() {
        return this.submitterContactPhoneNo1;
    }

    public BranchOffice submitterContactPhoneNo1(String submitterContactPhoneNo1) {
        this.setSubmitterContactPhoneNo1(submitterContactPhoneNo1);
        return this;
    }

    public void setSubmitterContactPhoneNo1(String submitterContactPhoneNo1) {
        this.submitterContactPhoneNo1 = submitterContactPhoneNo1;
    }

    public String getSubmitterContactPhoneNo2() {
        return this.submitterContactPhoneNo2;
    }

    public BranchOffice submitterContactPhoneNo2(String submitterContactPhoneNo2) {
        this.setSubmitterContactPhoneNo2(submitterContactPhoneNo2);
        return this;
    }

    public void setSubmitterContactPhoneNo2(String submitterContactPhoneNo2) {
        this.submitterContactPhoneNo2 = submitterContactPhoneNo2;
    }

    public String getBillingFax() {
        return this.billingFax;
    }

    public BranchOffice billingFax(String billingFax) {
        this.setBillingFax(billingFax);
        return this;
    }

    public void setBillingFax(String billingFax) {
        this.billingFax = billingFax;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public BranchOffice contactEmail(String contactEmail) {
        this.setContactEmail(contactEmail);
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getZip() {
        return this.zip;
    }

    public BranchOffice zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getBillingContactNo1() {
        return this.billingContactNo1;
    }

    public BranchOffice billingContactNo1(String billingContactNo1) {
        this.setBillingContactNo1(billingContactNo1);
        return this;
    }

    public void setBillingContactNo1(String billingContactNo1) {
        this.billingContactNo1 = billingContactNo1;
    }

    public String getBillingContactNo2() {
        return this.billingContactNo2;
    }

    public BranchOffice billingContactNo2(String billingContactNo2) {
        this.setBillingContactNo2(billingContactNo2);
        return this;
    }

    public void setBillingContactNo2(String billingContactNo2) {
        this.billingContactNo2 = billingContactNo2;
    }

    public String getBillingEmail() {
        return this.billingEmail;
    }

    public BranchOffice billingEmail(String billingEmail) {
        this.setBillingEmail(billingEmail);
        return this;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBranchCompany() {
        return this.branchCompany;
    }

    public BranchOffice branchCompany(String branchCompany) {
        this.setBranchCompany(branchCompany);
        return this;
    }

    public void setBranchCompany(String branchCompany) {
        this.branchCompany = branchCompany;
    }

    public String getBranchGroupName() {
        return this.branchGroupName;
    }

    public BranchOffice branchGroupName(String branchGroupName) {
        this.setBranchGroupName(branchGroupName);
        return this;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public BranchOffice addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public BranchOffice addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public BranchOffice city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public BranchOffice state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSignatureName() {
        return this.signatureName;
    }

    public BranchOffice signatureName(String signatureName) {
        this.setSignatureName(signatureName);
        return this;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public String getItemLocationId() {
        return this.itemLocationId;
    }

    public BranchOffice itemLocationId(String itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(String itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return this.itemLocationName;
    }

    public BranchOffice itemLocationName(String itemLocationName) {
        this.setItemLocationName(itemLocationName);
        return this;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public Long getBranchCompanyId() {
        return this.branchCompanyId;
    }

    public BranchOffice branchCompanyId(Long branchCompanyId) {
        this.setBranchCompanyId(branchCompanyId);
        return this;
    }

    public void setBranchCompanyId(Long branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    public String getIsDropshipAllowed() {
        return this.isDropshipAllowed;
    }

    public BranchOffice isDropshipAllowed(String isDropshipAllowed) {
        this.setIsDropshipAllowed(isDropshipAllowed);
        return this;
    }

    public void setIsDropshipAllowed(String isDropshipAllowed) {
        this.isDropshipAllowed = isDropshipAllowed;
    }

    public String getBillingTaxonomyCode() {
        return this.billingTaxonomyCode;
    }

    public BranchOffice billingTaxonomyCode(String billingTaxonomyCode) {
        this.setBillingTaxonomyCode(billingTaxonomyCode);
        return this;
    }

    public void setBillingTaxonomyCode(String billingTaxonomyCode) {
        this.billingTaxonomyCode = billingTaxonomyCode;
    }

    public String getBillingNpi() {
        return this.billingNpi;
    }

    public BranchOffice billingNpi(String billingNpi) {
        this.setBillingNpi(billingNpi);
        return this;
    }

    public void setBillingNpi(String billingNpi) {
        this.billingNpi = billingNpi;
    }

    public String getBillingOrganisationName() {
        return this.billingOrganisationName;
    }

    public BranchOffice billingOrganisationName(String billingOrganisationName) {
        this.setBillingOrganisationName(billingOrganisationName);
        return this;
    }

    public void setBillingOrganisationName(String billingOrganisationName) {
        this.billingOrganisationName = billingOrganisationName;
    }

    public String getBillingTaxIdNo() {
        return this.billingTaxIdNo;
    }

    public BranchOffice billingTaxIdNo(String billingTaxIdNo) {
        this.setBillingTaxIdNo(billingTaxIdNo);
        return this;
    }

    public void setBillingTaxIdNo(String billingTaxIdNo) {
        this.billingTaxIdNo = billingTaxIdNo;
    }

    public String getBillingBranchName() {
        return this.billingBranchName;
    }

    public BranchOffice billingBranchName(String billingBranchName) {
        this.setBillingBranchName(billingBranchName);
        return this;
    }

    public void setBillingBranchName(String billingBranchName) {
        this.billingBranchName = billingBranchName;
    }

    public String getFax() {
        return this.fax;
    }

    public BranchOffice fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchOffice)) {
            return false;
        }
        return branchId != null && branchId.equals(((BranchOffice) o).branchId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchOffice{" +
            "branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", branchGroupId=" + getBranchGroupId() +
            ", npi='" + getNpi() + "'" +
            ", ptan='" + getPtan() + "'" +
            ", taxonomyCode='" + getTaxonomyCode() + "'" +
            ", taxonomyCodeDescription='" + getTaxonomyCodeDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taxIdType='" + getTaxIdType() + "'" +
            ", taxIdNo='" + getTaxIdNo() + "'" +
            ", branchNo='" + getBranchNo() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", branchOfficeUuid='" + getBranchOfficeUuid() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingState='" + getBillingState() + "'" +
            ", billingZipCode='" + getBillingZipCode() + "'" +
            ", submitterContactPersonName='" + getSubmitterContactPersonName() + "'" +
            ", submitterContactPhoneNo1='" + getSubmitterContactPhoneNo1() + "'" +
            ", submitterContactPhoneNo2='" + getSubmitterContactPhoneNo2() + "'" +
            ", billingFax='" + getBillingFax() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", zip='" + getZip() + "'" +
            ", billingContactNo1='" + getBillingContactNo1() + "'" +
            ", billingContactNo2='" + getBillingContactNo2() + "'" +
            ", billingEmail='" + getBillingEmail() + "'" +
            ", branchCompany='" + getBranchCompany() + "'" +
            ", branchGroupName='" + getBranchGroupName() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", signatureName='" + getSignatureName() + "'" +
            ", itemLocationId='" + getItemLocationId() + "'" +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", branchCompanyId=" + getBranchCompanyId() +
            ", isDropshipAllowed='" + getIsDropshipAllowed() + "'" +
            ", billingTaxonomyCode='" + getBillingTaxonomyCode() + "'" +
            ", billingNpi='" + getBillingNpi() + "'" +
            ", billingOrganisationName='" + getBillingOrganisationName() + "'" +
            ", billingTaxIdNo='" + getBillingTaxIdNo() + "'" +
            ", billingBranchName='" + getBillingBranchName() + "'" +
            ", fax='" + getFax() + "'" +
            "}";
    }
}
