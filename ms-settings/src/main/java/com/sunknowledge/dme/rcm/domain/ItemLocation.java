package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ItemLocation.
 */
@Entity
@Table(name = "t_item_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ItemLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_location_id")
    private Long itemLocationId;

    @Column(name = "item_location_name")
    private String itemLocationName;

    @Column(name = "description")
    private String description;

    @Column(name = "contact_first_name")
    private String contactFirstName;

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

    @Column(name = "item_location_uuid")
    private UUID itemLocationUuid;

    @Column(name = "billing_address_line_1")
    private String billingAddressLine1;

    @Column(name = "billing_address_line_2")
    private String billingAddressLine2;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_state")
    private String billingState;

    @Column(name = "billing_country")
    private String billingCountry;

    @Column(name = "billing_zip")
    private String billingZip;

    @Column(name = "contact_middle_name")
    private String contactMiddleName;

    @Column(name = "contact_last_name")
    private String contactLastName;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "billling_address_company_name")
    private String billlingAddressCompanyName;

    @Column(name = "delivery_address_line_1")
    private String deliveryAddressLine1;

    @Column(name = "delivery_address_line_2")
    private String deliveryAddressLine2;

    @Column(name = "delivery_city")
    private String deliveryCity;

    @Column(name = "delivery_state")
    private String deliveryState;

    @Column(name = "delivery_zip")
    private String deliveryZip;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public ItemLocation itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return this.itemLocationName;
    }

    public ItemLocation itemLocationName(String itemLocationName) {
        this.setItemLocationName(itemLocationName);
        return this;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public String getDescription() {
        return this.description;
    }

    public ItemLocation description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactFirstName() {
        return this.contactFirstName;
    }

    public ItemLocation contactFirstName(String contactFirstName) {
        this.setContactFirstName(contactFirstName);
        return this;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemLocation status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemLocation createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemLocation createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemLocation updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemLocation createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemLocation updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemLocation updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemLocationUuid() {
        return this.itemLocationUuid;
    }

    public ItemLocation itemLocationUuid(UUID itemLocationUuid) {
        this.setItemLocationUuid(itemLocationUuid);
        return this;
    }

    public void setItemLocationUuid(UUID itemLocationUuid) {
        this.itemLocationUuid = itemLocationUuid;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public ItemLocation billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public ItemLocation billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public ItemLocation billingCity(String billingCity) {
        this.setBillingCity(billingCity);
        return this;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return this.billingState;
    }

    public ItemLocation billingState(String billingState) {
        this.setBillingState(billingState);
        return this;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return this.billingCountry;
    }

    public ItemLocation billingCountry(String billingCountry) {
        this.setBillingCountry(billingCountry);
        return this;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingZip() {
        return this.billingZip;
    }

    public ItemLocation billingZip(String billingZip) {
        this.setBillingZip(billingZip);
        return this;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public String getContactMiddleName() {
        return this.contactMiddleName;
    }

    public ItemLocation contactMiddleName(String contactMiddleName) {
        this.setContactMiddleName(contactMiddleName);
        return this;
    }

    public void setContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
    }

    public String getContactLastName() {
        return this.contactLastName;
    }

    public ItemLocation contactLastName(String contactLastName) {
        this.setContactLastName(contactLastName);
        return this;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public ItemLocation contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public ItemLocation contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    public String getFax() {
        return this.fax;
    }

    public ItemLocation fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public ItemLocation email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBilllingAddressCompanyName() {
        return this.billlingAddressCompanyName;
    }

    public ItemLocation billlingAddressCompanyName(String billlingAddressCompanyName) {
        this.setBilllingAddressCompanyName(billlingAddressCompanyName);
        return this;
    }

    public void setBilllingAddressCompanyName(String billlingAddressCompanyName) {
        this.billlingAddressCompanyName = billlingAddressCompanyName;
    }

    public String getDeliveryAddressLine1() {
        return this.deliveryAddressLine1;
    }

    public ItemLocation deliveryAddressLine1(String deliveryAddressLine1) {
        this.setDeliveryAddressLine1(deliveryAddressLine1);
        return this;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return this.deliveryAddressLine2;
    }

    public ItemLocation deliveryAddressLine2(String deliveryAddressLine2) {
        this.setDeliveryAddressLine2(deliveryAddressLine2);
        return this;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public ItemLocation deliveryCity(String deliveryCity) {
        this.setDeliveryCity(deliveryCity);
        return this;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return this.deliveryState;
    }

    public ItemLocation deliveryState(String deliveryState) {
        this.setDeliveryState(deliveryState);
        return this;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return this.deliveryZip;
    }

    public ItemLocation deliveryZip(String deliveryZip) {
        this.setDeliveryZip(deliveryZip);
        return this;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemLocation)) {
            return false;
        }
        return itemLocationId != null && itemLocationId.equals(((ItemLocation) o).itemLocationId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemLocation{" +
            "itemLocationId=" + getItemLocationId() +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", description='" + getDescription() + "'" +
            ", contactFirstName='" + getContactFirstName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemLocationUuid='" + getItemLocationUuid() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingState='" + getBillingState() + "'" +
            ", billingCountry='" + getBillingCountry() + "'" +
            ", billingZip='" + getBillingZip() + "'" +
            ", contactMiddleName='" + getContactMiddleName() + "'" +
            ", contactLastName='" + getContactLastName() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", billlingAddressCompanyName='" + getBilllingAddressCompanyName() + "'" +
            ", deliveryAddressLine1='" + getDeliveryAddressLine1() + "'" +
            ", deliveryAddressLine2='" + getDeliveryAddressLine2() + "'" +
            ", deliveryCity='" + getDeliveryCity() + "'" +
            ", deliveryState='" + getDeliveryState() + "'" +
            ", deliveryZip='" + getDeliveryZip() + "'" +
            "}";
    }
}
