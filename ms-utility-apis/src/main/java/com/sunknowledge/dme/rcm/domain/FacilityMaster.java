package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A FacilityMaster.
 */
@Entity
@Table(name = "t_facility_master")
public class FacilityMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "npi")
    private String npi;

    @Column(name = "sales_rep_id")
    private Long salesRepId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "facility_type_id")
    private Long facilityTypeId;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "facility_master_uuid")
    private UUID facilityMasterUuid;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    @Column(name = "fax")
    private String fax;

    @Column(name = "efax")
    private String efax;

    @Column(name = "email")
    private String email;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "mode_of_contact")
    private String modeOfContact;

    @Column(name = "facility_no")
    private String facilityNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getFacilityId() {
        return this.facilityId;
    }

    public FacilityMaster facilityId(Long facilityId) {
        this.setFacilityId(facilityId);
        return this;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return this.facilityName;
    }

    public FacilityMaster facilityName(String facilityName) {
        this.setFacilityName(facilityName);
        return this;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getNpi() {
        return this.npi;
    }

    public FacilityMaster npi(String npi) {
        this.setNpi(npi);
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public Long getSalesRepId() {
        return this.salesRepId;
    }

    public FacilityMaster salesRepId(Long salesRepId) {
        this.setSalesRepId(salesRepId);
        return this;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getStatus() {
        return this.status;
    }

    public FacilityMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public FacilityMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public FacilityMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public FacilityMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getFacilityTypeId() {
        return this.facilityTypeId;
    }

    public FacilityMaster facilityTypeId(Long facilityTypeId) {
        this.setFacilityTypeId(facilityTypeId);
        return this;
    }

    public void setFacilityTypeId(Long facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public FacilityMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public FacilityMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public FacilityMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getFacilityMasterUuid() {
        return this.facilityMasterUuid;
    }

    public FacilityMaster facilityMasterUuid(UUID facilityMasterUuid) {
        this.setFacilityMasterUuid(facilityMasterUuid);
        return this;
    }

    public void setFacilityMasterUuid(UUID facilityMasterUuid) {
        this.facilityMasterUuid = facilityMasterUuid;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public FacilityMaster addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public FacilityMaster addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public FacilityMaster city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public FacilityMaster state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public FacilityMaster country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public FacilityMaster zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public FacilityMaster contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public FacilityMaster contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return this.fax;
    }

    public FacilityMaster fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return this.efax;
    }

    public FacilityMaster efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getEmail() {
        return this.email;
    }

    public FacilityMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public FacilityMaster relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public FacilityMaster modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getFacilityNo() {
        return this.facilityNo;
    }

    public FacilityMaster facilityNo(String facilityNo) {
        this.setFacilityNo(facilityNo);
        return this;
    }

    public void setFacilityNo(String facilityNo) {
        this.facilityNo = facilityNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FacilityMaster)) {
            return false;
        }
        return facilityId != null && facilityId.equals(((FacilityMaster) o).facilityId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FacilityMaster{" +
            "facilityId=" + getFacilityId() +
            ", facilityName='" + getFacilityName() + "'" +
            ", npi='" + getNpi() + "'" +
            ", salesRepId=" + getSalesRepId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", facilityTypeId=" + getFacilityTypeId() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", facilityMasterUuid='" + getFacilityMasterUuid() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", zip='" + getZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", fax='" + getFax() + "'" +
            ", efax='" + getEfax() + "'" +
            ", email='" + getEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", facilityNo='" + getFacilityNo() + "'" +
            "}";
    }
}
