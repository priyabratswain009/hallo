package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A Manufacturer.
 */
@Entity
@Table(name = "t_manufacturer")
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "manufacturer_uuid")
    private UUID manufacturerUuid;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "web_url")
    private String webUrl;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "email")
    private String email;

    @Column(name = "fax")
    private String fax;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    @Column(name = "efax")
    private String efax;

    @Column(name = "manufacturer_no")
    private String manufacturerNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getManufacturerId() {
        return this.manufacturerId;
    }

    public Manufacturer manufacturerId(Long manufacturerId) {
        this.setManufacturerId(manufacturerId);
        return this;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public Manufacturer manufacturerName(String manufacturerName) {
        this.setManufacturerName(manufacturerName);
        return this;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getStatus() {
        return this.status;
    }

    public Manufacturer status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public Manufacturer createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Manufacturer createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public Manufacturer updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public Manufacturer createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public Manufacturer updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public Manufacturer updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getManufacturerUuid() {
        return this.manufacturerUuid;
    }

    public Manufacturer manufacturerUuid(UUID manufacturerUuid) {
        this.setManufacturerUuid(manufacturerUuid);
        return this;
    }

    public void setManufacturerUuid(UUID manufacturerUuid) {
        this.manufacturerUuid = manufacturerUuid;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public Manufacturer contactPersonName(String contactPersonName) {
        this.setContactPersonName(contactPersonName);
        return this;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public Manufacturer webUrl(String webUrl) {
        this.setWebUrl(webUrl);
        return this;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public Manufacturer addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public Manufacturer addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public Manufacturer city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public Manufacturer state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public Manufacturer zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return this.email;
    }

    public Manufacturer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }

    public Manufacturer fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public Manufacturer contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public Manufacturer contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getEfax() {
        return this.efax;
    }

    public Manufacturer efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getManufacturerNo() {
        return this.manufacturerNo;
    }

    public Manufacturer manufacturerNo(String manufacturerNo) {
        this.setManufacturerNo(manufacturerNo);
        return this;
    }

    public void setManufacturerNo(String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Manufacturer)) {
            return false;
        }
        return manufacturerId != null && manufacturerId.equals(((Manufacturer) o).manufacturerId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Manufacturer{" +
            "manufacturerId=" + getManufacturerId() +
            ", manufacturerName='" + getManufacturerName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", manufacturerUuid='" + getManufacturerUuid() + "'" +
            ", contactPersonName='" + getContactPersonName() + "'" +
            ", webUrl='" + getWebUrl() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", efax='" + getEfax() + "'" +
            ", manufacturerNo='" + getManufacturerNo() + "'" +
            "}";
    }
}
