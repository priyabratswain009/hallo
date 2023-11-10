package com.sunknowledge.dme.rcm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ItemProcedureCodeMap.
 */
@Entity
@Table(name = "t_item_procedure_code_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemProcedureCodeMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_procedure_code_map_id")
    private Long itemProcedureCodeMapId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "procedure_code")
    private String procedureCode;

    @Column(name = "modifier_1")
    private String modifier1;

    @Column(name = "modifier_2")
    private String modifier2;

    @Column(name = "modifier_3")
    private String modifier3;

    @Column(name = "modifier_4")
    private String modifier4;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_name")
    private String updatedName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "item_procedure_code_map_uuid")
    private UUID itemProcedureCodeMapUuid;

    @ManyToOne
    @JsonIgnoreProperties(value = { "procedureDetails" }, allowSetters = true)
    private ItemMaster itemMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemProcedureCodeMapId() {
        return this.itemProcedureCodeMapId;
    }

    public ItemProcedureCodeMap itemProcedureCodeMapId(Long itemProcedureCodeMapId) {
        this.setItemProcedureCodeMapId(itemProcedureCodeMapId);
        return this;
    }

    public void setItemProcedureCodeMapId(Long itemProcedureCodeMapId) {
        this.itemProcedureCodeMapId = itemProcedureCodeMapId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemProcedureCodeMap itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ItemProcedureCodeMap itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public ItemProcedureCodeMap itemDescription(String itemDescription) {
        this.setItemDescription(itemDescription);
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public ItemProcedureCodeMap itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getProcedureCode() {
        return this.procedureCode;
    }

    public ItemProcedureCodeMap procedureCode(String procedureCode) {
        this.setProcedureCode(procedureCode);
        return this;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public ItemProcedureCodeMap modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public ItemProcedureCodeMap modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public ItemProcedureCodeMap modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public ItemProcedureCodeMap modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemProcedureCodeMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemProcedureCodeMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemProcedureCodeMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemProcedureCodeMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemProcedureCodeMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedName() {
        return this.updatedName;
    }

    public ItemProcedureCodeMap updatedName(String updatedName) {
        this.setUpdatedName(updatedName);
        return this;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemProcedureCodeMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemProcedureCodeMapUuid() {
        return this.itemProcedureCodeMapUuid;
    }

    public ItemProcedureCodeMap itemProcedureCodeMapUuid(UUID itemProcedureCodeMapUuid) {
        this.setItemProcedureCodeMapUuid(itemProcedureCodeMapUuid);
        return this;
    }

    public void setItemProcedureCodeMapUuid(UUID itemProcedureCodeMapUuid) {
        this.itemProcedureCodeMapUuid = itemProcedureCodeMapUuid;
    }

    public ItemMaster getItemMaster() {
        return this.itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public ItemProcedureCodeMap itemMaster(ItemMaster itemMaster) {
        this.setItemMaster(itemMaster);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemProcedureCodeMap)) {
            return false;
        }
        return itemProcedureCodeMapId != null && itemProcedureCodeMapId.equals(((ItemProcedureCodeMap) o).itemProcedureCodeMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemProcedureCodeMap{" +
            "itemProcedureCodeMapId=" + getItemProcedureCodeMapId() +
            ", itemName='" + getItemName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", procedureCode='" + getProcedureCode() + "'" +
            ", modifier1='" + getModifier1() + "'" +
            ", modifier2='" + getModifier2() + "'" +
            ", modifier3='" + getModifier3() + "'" +
            ", modifier4='" + getModifier4() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedName='" + getUpdatedName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", itemProcedureCodeMapUuid='" + getItemProcedureCodeMapUuid() + "'" +
            "}";
    }
}
