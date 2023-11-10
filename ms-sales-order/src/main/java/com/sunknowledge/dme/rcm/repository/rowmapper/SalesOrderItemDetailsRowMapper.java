package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderItemDetails}, with proper type conversions.
 */
@Service
public class SalesOrderItemDetailsRowMapper implements BiFunction<Row, String, SalesOrderItemDetails> {

    private final ColumnConverter converter;

    public SalesOrderItemDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderItemDetails} stored in the database.
     */
    @Override
    public SalesOrderItemDetails apply(Row row, String prefix) {
        SalesOrderItemDetails entity = new SalesOrderItemDetails();
        entity.setSalesOrderItemDetailsId(converter.fromRow(row, prefix + "_sales_order_item_details_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setItemLocationId(converter.fromRow(row, prefix + "_item_location_id", Long.class));
        entity.setSalesOrderDetailsItemId(converter.fromRow(row, prefix + "_sales_order_details_item_id", Long.class));
        entity.setSalesOrderDetailsItemName(converter.fromRow(row, prefix + "_sales_order_details_item_name", String.class));
        entity.setSalesOrderDetailsStockingUom(converter.fromRow(row, prefix + "_sales_order_details_stocking_uom", String.class));
        entity.setItemAssetNo(converter.fromRow(row, prefix + "_item_asset_no", String.class));
        entity.setSalesOrderDetailsItemDescription(converter.fromRow(row, prefix + "_sales_order_details_item_description", String.class));
        entity.setSalesOrderDetailsDefaultVendor(converter.fromRow(row, prefix + "_sales_order_details_default_vendor", String.class));
        entity.setSalesOrderDetailsOriginalDos(converter.fromRow(row, prefix + "_sales_order_details_original_dos", LocalDate.class));
        entity.setSalesOrderDetailsPreviousBillingDate(
            converter.fromRow(row, prefix + "_sales_order_details_previous_billing_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsNextBillingDate(
            converter.fromRow(row, prefix + "_sales_order_details_next_billing_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsDosTo(converter.fromRow(row, prefix + "_sales_order_details_dos_to", LocalDate.class));
        entity.setSalesOrderDetailsNextPeriod(converter.fromRow(row, prefix + "_sales_order_details_next_period", String.class));
        entity.setSalesOrderDetailsSpecialPricing(converter.fromRow(row, prefix + "_sales_order_details_special_pricing", String.class));
        entity.setSalesOrderDetailsPriceOverride(converter.fromRow(row, prefix + "_sales_order_details_price_override", String.class));
        entity.setSalesOrderDetailsSpecialTaxRate(converter.fromRow(row, prefix + "_sales_order_details_special_tax_rate", Long.class));
        entity.setSalesOrderDetailsQty(converter.fromRow(row, prefix + "_sales_order_details_qty", Long.class));
        entity.setSalesOrderDetailsBqty(converter.fromRow(row, prefix + "_sales_order_details_bqty", Long.class));
        entity.setSalesOrderDetailsLineQty(converter.fromRow(row, prefix + "_sales_order_details_line_qty", Long.class));
        entity.setSalesOrderDetailsProcCode(converter.fromRow(row, prefix + "_sales_order_details_proc_code", String.class));
        entity.setSalesOrderDetailsPriceOption(converter.fromRow(row, prefix + "_sales_order_details_price_option", String.class));
        entity.setSalesOrderDetailsModifier1(converter.fromRow(row, prefix + "_sales_order_details_modifier_1", String.class));
        entity.setSalesOrderDetailsModifier2(converter.fromRow(row, prefix + "_sales_order_details_modifier_2", String.class));
        entity.setSalesOrderDetailsModifier3(converter.fromRow(row, prefix + "_sales_order_details_modifier_3", String.class));
        entity.setSalesOrderDetailsModifier4(converter.fromRow(row, prefix + "_sales_order_details_modifier_4", String.class));
        entity.setSalesOrderDetailsChargeAmt(converter.fromRow(row, prefix + "_sales_order_details_charge_amt", Double.class));
        entity.setSalesOrderDetailsAllowedAmt(converter.fromRow(row, prefix + "_sales_order_details_allowed_amt", Double.class));
        entity.setSalesOrderDetailsTaxable(converter.fromRow(row, prefix + "_sales_order_details_taxable", String.class));
        entity.setSalesOrderDetailsAbn(converter.fromRow(row, prefix + "_sales_order_details_abn", String.class));
        entity.setSalesOrderDetailsAbnUpgrade(converter.fromRow(row, prefix + "_sales_order_details_abn_upgrade", String.class));
        entity.setSalesOrderDetailsAbnPrintDate(converter.fromRow(row, prefix + "_sales_order_details_abn_print_date", LocalDate.class));
        entity.setSalesOrderDetailsAbnItem(converter.fromRow(row, prefix + "_sales_order_details_abn_item", String.class));
        entity.setSalesOrderDetailsAbnProcCode(converter.fromRow(row, prefix + "_sales_order_details_abn_proc_code", String.class));
        entity.setSalesOrderDetailsAbnAllow(converter.fromRow(row, prefix + "_sales_order_details_abn_allow", String.class));
        entity.setSalesOrderDetailsAbnCharge(converter.fromRow(row, prefix + "_sales_order_details_abn_charge", Double.class));
        entity.setSalesOrderDetailsAbnModifier1(converter.fromRow(row, prefix + "_sales_order_details_abn_modifier_1", String.class));
        entity.setSalesOrderDetailsAbnModifier2(converter.fromRow(row, prefix + "_sales_order_details_abn_modifier_2", String.class));
        entity.setSalesOrderDetailsTaxRate(converter.fromRow(row, prefix + "_sales_order_details_tax_rate", Long.class));
        entity.setSalesOrderDetailsTaxZone(converter.fromRow(row, prefix + "_sales_order_details_tax_zone", String.class));
        entity.setSalesOrderDetailsNonTaxReason(converter.fromRow(row, prefix + "_sales_order_details_non_tax_reason", String.class));
        entity.setSalesOrderDetailsNote(converter.fromRow(row, prefix + "_sales_order_details_note", String.class));
        entity.setSalesOrderDetailsSaleType(converter.fromRow(row, prefix + "_sales_order_details_sale_type", String.class));
        entity.setSalesOrderDetailsItemGroup(converter.fromRow(row, prefix + "_sales_order_details_item_group", String.class));
        entity.setSalesOrderDetailsItemUser1(converter.fromRow(row, prefix + "_sales_order_details_item_user_1", String.class));
        entity.setSalesOrderDetailsItemUser2(converter.fromRow(row, prefix + "_sales_order_details_item_user_2", String.class));
        entity.setSalesOrderDetailsItemUser3(converter.fromRow(row, prefix + "_sales_order_details_item_user_3", String.class));
        entity.setSalesOrderDetailsItemUser4(converter.fromRow(row, prefix + "_sales_order_details_item_user_4", String.class));
        entity.setSalesOrderDetailsConvertedToPurchase(
            converter.fromRow(row, prefix + "_sales_order_details_converted_to_purchase", String.class)
        );
        entity.setSalesOrderDetailsManualConvertToPurchaseMctp(
            converter.fromRow(row, prefix + "_sales_order_details_manual_convert_to_purchase_mctp", String.class)
        );
        entity.setSalesOrderDetailsMctpChargeAmt(converter.fromRow(row, prefix + "_sales_order_details_mctp_charge_amt", Double.class));
        entity.setSalesOrderDetailsMctpAllowedAmt(converter.fromRow(row, prefix + "_sales_order_details_mctp_allowed_amt", Double.class));
        entity.setSalesOrderDetailsMctpModifier1(converter.fromRow(row, prefix + "_sales_order_details_mctp_modifier_1", String.class));
        entity.setSalesOrderDetailsMctpModifier2(converter.fromRow(row, prefix + "_sales_order_details_mctp_modifier_2", String.class));
        entity.setSalesOrderDetailsMctpModifier3(converter.fromRow(row, prefix + "_sales_order_details_mctp_modifier_3", String.class));
        entity.setSalesOrderDetailsMctpModifier4(converter.fromRow(row, prefix + "_sales_order_details_mctp_modifier_4", String.class));
        entity.setSalesOrderDetailsMctpPeriod(converter.fromRow(row, prefix + "_sales_order_details_mctp_period", Long.class));
        entity.setSalesOrderDetailsAddtlModifier1(converter.fromRow(row, prefix + "_sales_order_details_addtl_modifier_1", String.class));
        entity.setSalesOrderDetailsAddtlModifier2(converter.fromRow(row, prefix + "_sales_order_details_addtl_modifier_2", String.class));
        entity.setSalesOrderDetailsAddtlModifier3(converter.fromRow(row, prefix + "_sales_order_details_addtl_modifier_3", String.class));
        entity.setSalesOrderDetailsAddtlModifier4(converter.fromRow(row, prefix + "_sales_order_details_addtl_modifier_4", String.class));
        entity.setSalesOrderDetailsNextDateOfService(
            converter.fromRow(row, prefix + "_sales_order_details_next_date_of_service", LocalDate.class)
        );
        entity.setSalesOrderDetailsPriceTable(converter.fromRow(row, prefix + "_sales_order_details_price_table", String.class));
        entity.setSalesOrderDetailsPriceOptionName(converter.fromRow(row, prefix + "_sales_order_details_price_option_name", String.class));
        entity.setSalesOrderDetailsExtendedChargeAmount(
            converter.fromRow(row, prefix + "_sales_order_details_extended_charge_amount", Double.class)
        );
        entity.setSalesOrderDetailsExtendedAllowanceAmount(
            converter.fromRow(row, prefix + "_sales_order_details_extended_allowance_amount", Double.class)
        );
        entity.setSalesOrderDetailsItemNdcCode(converter.fromRow(row, prefix + "_sales_order_details_item_ndc_code", String.class));
        entity.setSalesOrderDetailsManufacturer(converter.fromRow(row, prefix + "_sales_order_details_manufacturer", String.class));
        entity.setSalesOrderDetailsCbPricing(converter.fromRow(row, prefix + "_sales_order_details_cb_pricing", String.class));
        entity.setSalesOrderDetailsCbPriceTableOverride(
            converter.fromRow(row, prefix + "_sales_order_details_cb_price_table_override", String.class)
        );
        entity.setSalesOrderDetailsCbOverride(converter.fromRow(row, prefix + "_sales_order_details_cb_override", String.class));
        entity.setSalesOrderDetailsMessages(converter.fromRow(row, prefix + "_sales_order_details_messages", String.class));
        entity.setSalesOrderDetailsLocation(converter.fromRow(row, prefix + "_sales_order_details_location", Long.class));
        entity.setSalesOrderDetailsCaloriesPerDay(converter.fromRow(row, prefix + "_sales_order_details_calories_per_day", Long.class));
        entity.setSalesOrderDetailsSecondaryBillingProcudureCode(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_procudure_code", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingPriceOption(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_price_option", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingPriceOptionName(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_price_option_name", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingModifier1(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_modifier_1", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingModifier2(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_modifier_2", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingModifier3(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_modifier_3", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingModifier4(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_modifier_4", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingAdditionalModifier1(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_additional_modifier_1", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingAdditionalModifier2(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_additional_modifier_2", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingAdditionalModifier3(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_additional_modifier_3", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingAdditionalModifier4(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_additional_modifier_4", String.class)
        );
        entity.setSalesOrderDetailsSecondaryBillingIgnore(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_billing_ignore", String.class)
        );
        entity.setSalesOrderDetailsSecondarySpecialBilling(
            converter.fromRow(row, prefix + "_sales_order_details_secondary_special_billing", String.class)
        );
        entity.setSalesOrderDetailsSpanDateSplitBilling(
            converter.fromRow(row, prefix + "_sales_order_details_span_date_split_billing", String.class)
        );
        entity.setSalesOrderDetailsCmnparCmnFormId(converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_form_id", Long.class));
        entity.setSalesOrderDetailsCmnparCmnKey(converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_key", String.class));
        entity.setSalesOrderDetailsCmnparCmnCreateDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_create_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnExpirationDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_expiration_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnInitialDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_initial_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnRenewalDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_renewal_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnRecertificationDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_recertification_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnPhysicianDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_physician_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnStatus(converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_status", String.class));
        entity.setSalesOrderDetailsCmnparParId(converter.fromRow(row, prefix + "_sales_order_details_cmnpar_par_id", String.class));
        entity.setSalesOrderDetailsCmnparParDescr(converter.fromRow(row, prefix + "_sales_order_details_cmnpar_par_descr", String.class));
        entity.setSalesOrderDetailsCmnparParInitialDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_par_initial_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparParExpirationDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_par_expiration_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnLogDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_log_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnLengthOfNeed(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_length_of_need", Long.class)
        );
        entity.setSalesOrderDetailsCmnparCmnPrintedDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_printed_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnPrintedBy(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_printed_by", String.class)
        );
        entity.setSalesOrderDetailsCmnparFaxedDate(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_faxed_date", LocalDate.class)
        );
        entity.setSalesOrderDetailsCmnparCmnPlaceholder(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_placeholder", String.class)
        );
        entity.setSalesOrderDetailsCmnparCmnFaxedBy(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_faxed_by", String.class)
        );
        entity.setSalesOrderDetailsCmnparCmnLoggedBy(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_cmn_logged_by", String.class)
        );
        entity.setSalesOrderDetailsCmnparNumberOfRefills(
            converter.fromRow(row, prefix + "_sales_order_details_cmnpar_number_of_refills", Long.class)
        );
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setSalesOrderDetailsManufacturerItemIdNumber(
            converter.fromRow(row, prefix + "_sales_order_details_manufacturer_item_id_number", String.class)
        );
        entity.setCmnId(converter.fromRow(row, prefix + "_cmn_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSalesOrderItemDetailsUuid(converter.fromRow(row, prefix + "_sales_order_item_details_uuid", UUID.class));
        entity.setSalesOrderItemNumber(converter.fromRow(row, prefix + "_sales_order_item_number", String.class));
        entity.setIsAssetTagged(converter.fromRow(row, prefix + "_is_asset_tagged", String.class));
        entity.setItemSerialNo(converter.fromRow(row, prefix + "_item_serial_no", Long.class));
        entity.setSalesOrderDetailsIcdPointer(converter.fromRow(row, prefix + "_sales_order_details_icd_pointer", String.class));
        entity.setProcedureIdentifier(converter.fromRow(row, prefix + "_procedure_identifier", String.class));
        entity.setParNo(converter.fromRow(row, prefix + "_par_no", String.class));
        entity.setWhetherSerialised(converter.fromRow(row, prefix + "_whether_serialised", String.class));
        entity.setPickupExchangeNo(converter.fromRow(row, prefix + "_pickup_exchange_no", String.class));
        entity.setSalesOrderAbnUserResponse(converter.fromRow(row, prefix + "_sales_order_abn_user_response", String.class));
        entity.setIsDropshipAllowed(converter.fromRow(row, prefix + "_is_dropship_allowed", String.class));
        entity.setPoNumber(converter.fromRow(row, prefix + "_po_number", String.class));
        entity.setPurchaseOrderUuid(converter.fromRow(row, prefix + "_purchase_order_uuid", UUID.class));
        entity.setIsResupplyType(converter.fromRow(row, prefix + "_is_resupply_type", String.class));
        entity.setFrequencyCount(converter.fromRow(row, prefix + "_frequency_count", Long.class));
        entity.setFrequencyInterval(converter.fromRow(row, prefix + "_frequency_interval", Long.class));
        entity.setItemGroupId(converter.fromRow(row, prefix + "_item_group_id", Long.class));
        return entity;
    }
}
