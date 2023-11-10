package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SalesOrderDocuments}, with proper type conversions.
 */
@Service
public class SalesOrderDocumentsRowMapper implements BiFunction<Row, String, SalesOrderDocuments> {

    private final ColumnConverter converter;

    public SalesOrderDocumentsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SalesOrderDocuments} stored in the database.
     */
    @Override
    public SalesOrderDocuments apply(Row row, String prefix) {
        SalesOrderDocuments entity = new SalesOrderDocuments();
        entity.setPatientId(converter.fromRow(row, prefix + "_patient_id", Long.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientDob(converter.fromRow(row, prefix + "_patient_dob", LocalDate.class));
        entity.setPatientDod(converter.fromRow(row, prefix + "_patient_dod", LocalDate.class));
        entity.setPatientSsn(converter.fromRow(row, prefix + "_patient_ssn", String.class));
        entity.setQmbStatus(converter.fromRow(row, prefix + "_qmb_status", String.class));
        entity.setPatientGender(converter.fromRow(row, prefix + "_patient_gender", String.class));
        entity.setPatientHeight(converter.fromRow(row, prefix + "_patient_height", Double.class));
        entity.setPatientWeight(converter.fromRow(row, prefix + "_patient_weight", Double.class));
        entity.setPatientContact1(converter.fromRow(row, prefix + "_patient_contact_1", String.class));
        entity.setPatientContact2(converter.fromRow(row, prefix + "_patient_contact_2", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setHipaaOnFileStatus(converter.fromRow(row, prefix + "_hipaa_on_file_status", String.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", Long.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setDocumentTypeId(converter.fromRow(row, prefix + "_document_type_id", Long.class));
        entity.setDocumentTypeName(converter.fromRow(row, prefix + "_document_type_name", String.class));
        entity.setDocumentDate(converter.fromRow(row, prefix + "_document_date", LocalDate.class));
        entity.setDocumentNote(converter.fromRow(row, prefix + "_document_note", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setSalesOrderDocumentId(converter.fromRow(row, prefix + "_sales_order_document_id", Long.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setFax(converter.fromRow(row, prefix + "_fax", String.class));
        entity.setDocumentTitle(converter.fromRow(row, prefix + "_document_title", String.class));
        entity.setDocumentName(converter.fromRow(row, prefix + "_document_name", String.class));
        entity.setScanBy(converter.fromRow(row, prefix + "_scan_by", Long.class));
        entity.setFileUploadPath(converter.fromRow(row, prefix + "_file_upload_path", String.class));
        entity.setUploadDate(converter.fromRow(row, prefix + "_upload_date", LocalDate.class));
        entity.setUploadBy(converter.fromRow(row, prefix + "_upload_by", Long.class));
        entity.setScanDate(converter.fromRow(row, prefix + "_scan_date", LocalDate.class));
        entity.setReviewStatus(converter.fromRow(row, prefix + "_review_status", String.class));
        entity.setReviewReason(converter.fromRow(row, prefix + "_review_reason", String.class));
        entity.setReviewDate(converter.fromRow(row, prefix + "_review_date", LocalDate.class));
        entity.setReviewBy(converter.fromRow(row, prefix + "_review_by", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setSalesOrderNo(converter.fromRow(row, prefix + "_sales_order_no", String.class));
        entity.setSalesOrderCreationDate(converter.fromRow(row, prefix + "_sales_order_creation_date", LocalDate.class));
        entity.setSalesOrderDocumentsUuid(converter.fromRow(row, prefix + "_sales_order_documents_uuid", UUID.class));
        entity.setPatientMiddleName(converter.fromRow(row, prefix + "_patient_middle_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        return entity;
    }
}
