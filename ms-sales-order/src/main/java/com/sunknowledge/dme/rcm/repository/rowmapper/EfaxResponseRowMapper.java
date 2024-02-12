package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EfaxResponse}, with proper type conversions.
 */
@Service
public class EfaxResponseRowMapper implements BiFunction<Row, String, EfaxResponse> {

    private final ColumnConverter converter;

    public EfaxResponseRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EfaxResponse} stored in the database.
     */
    @Override
    public EfaxResponse apply(Row row, String prefix) {
        EfaxResponse entity = new EfaxResponse();
        entity.setEfaxResponseId(converter.fromRow(row, prefix + "_efax_response_id", Long.class));
        entity.setEfaxReceivedDate(converter.fromRow(row, prefix + "_efax_received_date", LocalDate.class));
        entity.setSenderEmail(converter.fromRow(row, prefix + "_sender_email", String.class));
        entity.setEmailSubjectLine(converter.fromRow(row, prefix + "_email_subject_line", String.class));
        entity.setAttachmentName(converter.fromRow(row, prefix + "_attachment_name", String.class));
        entity.setIsQrDecoded(converter.fromRow(row, prefix + "_is_qr_decoded", Boolean.class));
        entity.setQrValue(converter.fromRow(row, prefix + "_qr_value", String.class));
        entity.setIsCmn(converter.fromRow(row, prefix + "_is_cmn", Boolean.class));
        entity.setIsPar(converter.fromRow(row, prefix + "_is_par", Boolean.class));
        entity.setPatientIdNo(converter.fromRow(row, prefix + "_patient_id_no", String.class));
        entity.setPatientFirstName(converter.fromRow(row, prefix + "_patient_first_name", String.class));
        entity.setPatientLastName(converter.fromRow(row, prefix + "_patient_last_name", String.class));
        entity.setCmnIdNo(converter.fromRow(row, prefix + "_cmn_id_no", String.class));
        entity.setParIdNo(converter.fromRow(row, prefix + "_par_id_no", String.class));
        entity.setSoId(converter.fromRow(row, prefix + "_so_id", Long.class));
        entity.setSoNo(converter.fromRow(row, prefix + "_so_no", String.class));
        entity.setIsManuallyTagged(converter.fromRow(row, prefix + "_is_manually_tagged", Boolean.class));
        entity.setIsPatientRecord(converter.fromRow(row, prefix + "_is_patient_record", Boolean.class));
        entity.setEfaxNumber(converter.fromRow(row, prefix + "_efax_number", String.class));
        entity.setIsPoAck(converter.fromRow(row, prefix + "_is_po_ack", Boolean.class));
        entity.setDocumentRenameTo(converter.fromRow(row, prefix + "_document_rename_to", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setEfaxResponseUuid(converter.fromRow(row, prefix + "_efax_response_uuid", UUID.class));
        return entity;
    }
}
