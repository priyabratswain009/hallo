package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderFinancialDetails}, with proper type conversions.
 */
@Service
public class SalesOrderFinancialDetailsRowMapper implements BiFunction<Row, String, SalesOrderFinancialDetails> {

    private final ColumnConverter converter;

    public SalesOrderFinancialDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderFinancialDetails} stored in the database.
     */
    @Override
    public SalesOrderFinancialDetails apply(Row row, String prefix) {
        SalesOrderFinancialDetails entity = new SalesOrderFinancialDetails();
        entity.setSalesOrderFinancialId(converter.fromRow(row, prefix + "_sales_order_financial_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setItemId(converter.fromRow(row, prefix + "_item_id", Long.class));
        entity.setItemName(converter.fromRow(row, prefix + "_item_name", String.class));
        entity.setItemProcCode(converter.fromRow(row, prefix + "_item_proc_code", String.class));
        entity.setChargedAmount(converter.fromRow(row, prefix + "_charged_amount", Double.class));
        entity.setAllowedAmount(converter.fromRow(row, prefix + "_allowed_amount", Double.class));
        entity.setPrimaryInsurerId(converter.fromRow(row, prefix + "_primary_insurer_id", Long.class));
        entity.setPrimaryInsurerName(converter.fromRow(row, prefix + "_primary_insurer_name", String.class));
        entity.setPrimaryInsurerStatus(converter.fromRow(row, prefix + "_primary_insurer_status", String.class));
        entity.setPrimaryInsurerCoveragePercentage(converter.fromRow(row, prefix + "_primary_insurer_coverage_percentage", Long.class));
        entity.setPrimaryInsurerCoverageAmount(converter.fromRow(row, prefix + "_primary_insurer_coverage_amount", Double.class));
        entity.setPrimaryInsurerDeductibleAmount(converter.fromRow(row, prefix + "_primary_insurer_deductible_amount", Double.class));
        entity.setPrimaryInsurerPayment(converter.fromRow(row, prefix + "_primary_insurer_payment", Double.class));
        entity.setPrimaryInsurerBalanceAmount(converter.fromRow(row, prefix + "_primary_insurer_balance_amount", Double.class));
        entity.setSecondaryInsurerId(converter.fromRow(row, prefix + "_secondary_insurer_id", Long.class));
        entity.setSecondaryInsurerName(converter.fromRow(row, prefix + "_secondary_insurer_name", String.class));
        entity.setSecondaryInsurerStatus(converter.fromRow(row, prefix + "_secondary_insurer_status", String.class));
        entity.setSecondaryInsurerCoveragerPercentage(
            converter.fromRow(row, prefix + "_secondary_insurer_coverager_percentage", Long.class)
        );
        entity.setSecondaryInsurerCoverageAmount(converter.fromRow(row, prefix + "_secondary_insurer_coverage_amount", Double.class));
        entity.setSecondaryInsurerPayment(converter.fromRow(row, prefix + "_secondary_insurer_payment", Double.class));
        entity.setSecondaryInsurerBalanceAmount(converter.fromRow(row, prefix + "_secondary_insurer_balance_amount", Double.class));
        entity.setTertiaryInsurerId(converter.fromRow(row, prefix + "_tertiary_insurer_id", Long.class));
        entity.setTertiaryInsurerName(converter.fromRow(row, prefix + "_tertiary_insurer_name", String.class));
        entity.setTertiaryInsurerStatus(converter.fromRow(row, prefix + "_tertiary_insurer_status", String.class));
        entity.setTertiaryInsurerCoveragePercentage(converter.fromRow(row, prefix + "_tertiary_insurer_coverage_percentage", Long.class));
        entity.setTertiaryInsurerCoverageAmount(converter.fromRow(row, prefix + "_tertiary_insurer_coverage_amount", Double.class));
        entity.setTertiaryInsurerPayment(converter.fromRow(row, prefix + "_tertiary_insurer_payment", Double.class));
        entity.setTertiaryInsurerBalanceAmount(converter.fromRow(row, prefix + "_tertiary_insurer_balance_amount", Double.class));
        entity.setPatientCoinsurancePercentage(converter.fromRow(row, prefix + "_patient_coinsurance_percentage", Long.class));
        entity.setPatientCoinsuranceAmount(converter.fromRow(row, prefix + "_patient_coinsurance_amount", Double.class));
        entity.setTotalPatientResponsibilityAmount(converter.fromRow(row, prefix + "_total_patient_responsibility_amount", Double.class));
        entity.setPatientPayAmount(converter.fromRow(row, prefix + "_patient_pay_amount", Double.class));
        entity.setNarration(converter.fromRow(row, prefix + "_narration", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setPrimaryInvoiceNo(converter.fromRow(row, prefix + "_primary_invoice_no", String.class));
        entity.setPrimaryInvoiceDate(converter.fromRow(row, prefix + "_primary_invoice_date", LocalDate.class));
        entity.setPrimaryInvoiceStatus(converter.fromRow(row, prefix + "_primary_invoice_status", String.class));
        entity.setDos(converter.fromRow(row, prefix + "_dos", String.class));
        entity.setSecondaryInvoiceNo(converter.fromRow(row, prefix + "_secondary_invoice_no", String.class));
        entity.setTertiaryInvoiceNo(converter.fromRow(row, prefix + "_tertiary_invoice_no", String.class));
        entity.setSecondaryInvoiceDate(converter.fromRow(row, prefix + "_secondary_invoice_date", LocalDate.class));
        entity.setTertiaryInvoiceDate(converter.fromRow(row, prefix + "_tertiary_invoice_date", LocalDate.class));
        entity.setSecondaryInvoiceStatus(converter.fromRow(row, prefix + "_secondary_invoice_status", String.class));
        entity.setTertiaryInvoiceStatus(converter.fromRow(row, prefix + "_tertiary_invoice_status", String.class));
        entity.setSalesOrderFinancialDetailsUuid(converter.fromRow(row, prefix + "_sales_order_financial_details_uuid", UUID.class));
        return entity;
    }
}
