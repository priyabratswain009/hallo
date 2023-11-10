package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PriceDetails}, with proper type conversions.
 */
@Service
public class PriceDetailsRowMapper implements BiFunction<Row, String, PriceDetails> {

    private final ColumnConverter converter;

    public PriceDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PriceDetails} stored in the database.
     */
    @Override
    public PriceDetails apply(Row row, String prefix) {
        PriceDetails entity = new PriceDetails();
        entity.setPriceDetailsId(converter.fromRow(row, prefix + "_price_details_id", Long.class));
        entity.setPriceTableId(converter.fromRow(row, prefix + "_price_table_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setHcpcs(converter.fromRow(row, prefix + "_hcpcs", String.class));
        entity.setBillingCodeWhenSecondary(converter.fromRow(row, prefix + "_billing_code_when_secondary", String.class));
        entity.setPriceType(converter.fromRow(row, prefix + "_price_type", String.class));
        entity.setEffectiveStartDate(converter.fromRow(row, prefix + "_effective_start_date", LocalDate.class));
        entity.setEffectiveEndDate(converter.fromRow(row, prefix + "_effective_end_date", LocalDate.class));
        entity.setCmnReqdToBillStatus(converter.fromRow(row, prefix + "_cmn_reqd_to_bill_status", String.class));
        entity.setCmnFormName(converter.fromRow(row, prefix + "_cmn_form_name", String.class));
        entity.setPriorAuthReqStatus(converter.fromRow(row, prefix + "_prior_auth_req_status", String.class));
        entity.setFunctionalAbilityReqStatus(converter.fromRow(row, prefix + "_functional_ability_req_status", String.class));
        entity.setOptionNumber(converter.fromRow(row, prefix + "_option_number", String.class));
        entity.setOptionName(converter.fromRow(row, prefix + "_option_name", String.class));
        entity.setDefaultOptionStatus(converter.fromRow(row, prefix + "_default_option_status", String.class));
        entity.setBillingCyclePeriod(converter.fromRow(row, prefix + "_billing_cycle_period", String.class));
        entity.setBillingCycleInterval(converter.fromRow(row, prefix + "_billing_cycle_interval", String.class));
        entity.setBillingInArrearsStatus(converter.fromRow(row, prefix + "_billing_in_arrears_status", String.class));
        entity.setProRateBillingStatus(converter.fromRow(row, prefix + "_pro_rate_billing_status", String.class));
        entity.setDailyBillingInvoiceFreq(converter.fromRow(row, prefix + "_daily_billing_invoice_freq", String.class));
        entity.setDailyBillingInvoiceInterval(converter.fromRow(row, prefix + "_daily_billing_invoice_interval", String.class));
        entity.setChargeAmt(converter.fromRow(row, prefix + "_charge_amt", Double.class));
        entity.setAllowedAmt(converter.fromRow(row, prefix + "_allowed_amt", Double.class));
        entity.setAllowedModifier1(converter.fromRow(row, prefix + "_allowed_modifier_1", String.class));
        entity.setAllowedModifier2(converter.fromRow(row, prefix + "_allowed_modifier_2", String.class));
        entity.setAllowedModifier3(converter.fromRow(row, prefix + "_allowed_modifier_3", String.class));
        entity.setAllowedModifier4(converter.fromRow(row, prefix + "_allowed_modifier_4", String.class));
        entity.setAcceptAssignmentStatus(converter.fromRow(row, prefix + "_accept_assignment_status", String.class));
        entity.setTaxableStatus(converter.fromRow(row, prefix + "_taxable_status", String.class));
        entity.setNontaxTypeName(converter.fromRow(row, prefix + "_nontax_type_name", String.class));
        entity.setConvertToPurchaseLastStatus(converter.fromRow(row, prefix + "_convert_to_purchase_last_status", String.class));
        entity.setConvertToPurchaseChargeAmt(converter.fromRow(row, prefix + "_convert_to_purchase_charge_amt", Double.class));
        entity.setConvertToPurchaseAllowAmt(converter.fromRow(row, prefix + "_convert_to_purchase_allow_amt", Double.class));
        entity.setConvertToPurchaseModifier1(converter.fromRow(row, prefix + "_convert_to_purchase_modifier_1", String.class));
        entity.setConvertToPurchaseModifier2(converter.fromRow(row, prefix + "_convert_to_purchase_modifier_2", String.class));
        entity.setConvertToPurchaseModifier3(converter.fromRow(row, prefix + "_convert_to_purchase_modifier_3", String.class));
        entity.setConvertToPurchaseModifier4(converter.fromRow(row, prefix + "_convert_to_purchase_modifier_4", String.class));
        entity.setBillingMultiplierUnit(converter.fromRow(row, prefix + "_billing_multiplier_unit", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", String.class));
        entity.setPriceDetailsUuid(converter.fromRow(row, prefix + "_price_details_uuid", UUID.class));
        entity.setPriceTableName(converter.fromRow(row, prefix + "_price_table_name", String.class));
        entity.setItemNo(converter.fromRow(row, prefix + "_item_no", String.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setItemUom(converter.fromRow(row, prefix + "_item_uom", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPriceOptionBillingPeriodStart(converter.fromRow(row, prefix + "_price_option_billing_period_start", Long.class));
        entity.setPriceOptionBillingPeriodEnd(converter.fromRow(row, prefix + "_price_option_billing_period_end", Long.class));
        return entity;
    }
}
