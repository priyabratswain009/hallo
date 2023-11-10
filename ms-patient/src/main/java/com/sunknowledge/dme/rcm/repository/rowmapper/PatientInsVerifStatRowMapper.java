package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStat;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PatientInsVerifStat}, with proper type conversions.
 */
@Service
public class PatientInsVerifStatRowMapper implements BiFunction<Row, String, PatientInsVerifStat> {

    private final ColumnConverter converter;

    public PatientInsVerifStatRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PatientInsVerifStat} stored in the database.
     */
    @Override
    public PatientInsVerifStat apply(Row row, String prefix) {
        PatientInsVerifStat entity = new PatientInsVerifStat();
        entity.setInsuranceVerifId(converter.fromRow(row, prefix + "_insurance_verif_id", Long.class));
        entity.setPatientInsuranceId(converter.fromRow(row, prefix + "_patient_insurance_id", Long.class));
        entity.setElligibilityCheckDate(converter.fromRow(row, prefix + "_elligibility_check_date", LocalDate.class));
        entity.setElligibilityStatus(converter.fromRow(row, prefix + "_elligibility_status", String.class));
        entity.setElligibilityCheckType(converter.fromRow(row, prefix + "_elligibility_check_type", String.class));
        entity.setPeriodYear(converter.fromRow(row, prefix + "_period_year", String.class));
        entity.setCoverageInfo(converter.fromRow(row, prefix + "_coverage_info", String.class));
        entity.setNetworkInfo(converter.fromRow(row, prefix + "_network_info", String.class));
        entity.setDeductableAmt(converter.fromRow(row, prefix + "_deductable_amt", Double.class));
        entity.setRemainingAmt(converter.fromRow(row, prefix + "_remaining_amt", Double.class));
        entity.setCoinsuranceOrCopay(converter.fromRow(row, prefix + "_coinsurance_or_copay", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", LocalDate.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", LocalDate.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setPatientInsVerifStatUuid(converter.fromRow(row, prefix + "_patient_ins_verif_stat_uuid", UUID.class));
        return entity;
    }
}
