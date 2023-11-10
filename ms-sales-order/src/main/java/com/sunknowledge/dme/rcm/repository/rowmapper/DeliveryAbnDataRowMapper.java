package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DeliveryAbnData}, with proper type conversions.
 */
@Service
public class DeliveryAbnDataRowMapper implements BiFunction<Row, String, DeliveryAbnData> {

    private final ColumnConverter converter;

    public DeliveryAbnDataRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DeliveryAbnData} stored in the database.
     */
    @Override
    public DeliveryAbnData apply(Row row, String prefix) {
        DeliveryAbnData entity = new DeliveryAbnData();
        entity.setDeliveryAbnDataId(converter.fromRow(row, prefix + "_delivery_abn_data_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPrimaryInsuranceName(converter.fromRow(row, prefix + "_primary_insurance_name", String.class));
        entity.setPrimaryInsurancePolicyNumber(converter.fromRow(row, prefix + "_primary_insurance_policy_number", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setSalesOrderDetailsAbnPrintDate(converter.fromRow(row, prefix + "_sales_order_details_abn_print_date", LocalDate.class));
        entity.setSalesOrderDetailsAbnItemName(converter.fromRow(row, prefix + "_sales_order_details_abn_item_name", String.class));
        entity.setSalesOrderDetailsAbnItemProcCode(
            converter.fromRow(row, prefix + "_sales_order_details_abn_item_proc_code", String.class)
        );
        entity.setSalesOrderDetailsAbnItemChargeAmount(
            converter.fromRow(row, prefix + "_sales_order_details_abn_item_charge_amount", String.class)
        );
        entity.setSalesOrderDetailsPatientAbnResponse(
            converter.fromRow(row, prefix + "_sales_order_details_patient_abn_response", String.class)
        );
        entity.setSalesOrderDetailsAbnDeliveryStatus(
            converter.fromRow(row, prefix + "_sales_order_details_abn_delivery_status", String.class)
        );
        entity.setSalesOrderDetailsAbnPatientSignatureStatus(
            converter.fromRow(row, prefix + "_sales_order_details_abn_patient_signature_status", String.class)
        );
        entity.setSalesOrderDetailsAbnStatus(converter.fromRow(row, prefix + "_sales_order_details_abn_status", String.class));
        entity.setSalesOrderDetailsAbnReason(converter.fromRow(row, prefix + "_sales_order_details_abn_reason", String.class));
        entity.setSalesOrderDetailsAbnModifier1(converter.fromRow(row, prefix + "_sales_order_details_abn_modifier_1", String.class));
        entity.setSalesOrderDetailsAbnModifier2(converter.fromRow(row, prefix + "_sales_order_details_abn_modifier_2", String.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", String.class));
        entity.setQrCode(converter.fromRow(row, prefix + "_qr_code", String.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setAbnNumber(converter.fromRow(row, prefix + "_abn_number", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setDeliveryAbnDataUuid(converter.fromRow(row, prefix + "_delivery_abn_data_uuid", UUID.class));
        return entity;
    }
}
