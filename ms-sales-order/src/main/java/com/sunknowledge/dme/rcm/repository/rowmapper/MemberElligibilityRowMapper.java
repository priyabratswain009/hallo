package com.sunknowledge.dme.rcm.repository.rowmapper;

import com.sunknowledge.dme.rcm.domain.MemberElligibility;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link MemberElligibility}, with proper type conversions.
 */
@Service
public class MemberElligibilityRowMapper implements BiFunction<Row, String, MemberElligibility> {

    private final ColumnConverter converter;

    public MemberElligibilityRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link MemberElligibility} stored in the database.
     */
    @Override
    public MemberElligibility apply(Row row, String prefix) {
        MemberElligibility entity = new MemberElligibility();
        entity.setClaimElligibilityMasterId(converter.fromRow(row, prefix + "_claim_elligibility_master_id", Long.class));
        entity.setSalesOrderId(converter.fromRow(row, prefix + "_sales_order_id", Long.class));
        entity.setElligibilityControlNumber(converter.fromRow(row, prefix + "_elligibility_control_number", String.class));
        entity.setTradingPartnerServiceId(converter.fromRow(row, prefix + "_trading_partner_service_id", String.class));
        entity.setTradingPartnerName(converter.fromRow(row, prefix + "_trading_partner_name", String.class));
        entity.setProviderOrganizationName(converter.fromRow(row, prefix + "_provider_organization_name", String.class));
        entity.setProviderNpi(converter.fromRow(row, prefix + "_provider_npi", String.class));
        entity.setProviderType(converter.fromRow(row, prefix + "_provider_type", String.class));
        entity.setSubscriberFirstName(converter.fromRow(row, prefix + "_subscriber_first_name", String.class));
        entity.setSubscriberLastName(converter.fromRow(row, prefix + "_subscriber_last_name", String.class));
        entity.setSubscriberMemberId(converter.fromRow(row, prefix + "_subscriber_member_id", String.class));
        entity.setSubscriberIdcard(converter.fromRow(row, prefix + "_subscriber_idcard", String.class));
        entity.setSubscriberDob(converter.fromRow(row, prefix + "_subscriber_dob", LocalDate.class));
        entity.setSubscriberGender(converter.fromRow(row, prefix + "_subscriber_gender", String.class));
        entity.setSubscriberPlanIssueDate(converter.fromRow(row, prefix + "_subscriber_plan_issue_date", ZonedDateTime.class));
        entity.setInsuredFirstName(converter.fromRow(row, prefix + "_insured_first_name", String.class));
        entity.setInsuredLastName(converter.fromRow(row, prefix + "_insured_last_name", String.class));
        entity.setInsuredGender(converter.fromRow(row, prefix + "_insured_gender", String.class));
        entity.setInsuredDob(converter.fromRow(row, prefix + "_insured_dob", LocalDate.class));
        entity.setInsuredRelationshipwithSubscriber(converter.fromRow(row, prefix + "_insured_relationshipwith_subscriber", String.class));
        entity.setDateOfService(converter.fromRow(row, prefix + "_date_of_service", ZonedDateTime.class));
        entity.setServiceTypeCodes(converter.fromRow(row, prefix + "_service_type_codes", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", LocalDate.class));
        entity.setCreatedById(converter.fromRow(row, prefix + "_created_by_id", Long.class));
        entity.setCreatedByName(converter.fromRow(row, prefix + "_created_by_name", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", ZonedDateTime.class));
        entity.setUpdatedById(converter.fromRow(row, prefix + "_updated_by_id", Long.class));
        entity.setUpdatedByName(converter.fromRow(row, prefix + "_updated_by_name", String.class));
        entity.setUpdatedDate(converter.fromRow(row, prefix + "_updated_date", ZonedDateTime.class));
        entity.setMemberElligibilityMasterUuid(converter.fromRow(row, prefix + "_member_elligibility_master_uuid", UUID.class));
        return entity;
    }
}