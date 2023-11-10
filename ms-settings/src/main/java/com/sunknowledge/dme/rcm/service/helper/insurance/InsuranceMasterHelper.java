package com.sunknowledge.dme.rcm.service.helper.insurance;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.insurance.InsuranceMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.InsuranceMasterRejectedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationRejectedDTO;
import com.sunknowledge.dme.rcm.service.impl.insurance.InsuranceMasterServiceExtendedImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.log4j.spi.Configurator.NULL;

@Service
public class InsuranceMasterHelper {
    private final Logger log = LoggerFactory.getLogger(InsuranceMasterHelper.class);
    @Autowired
    InsuranceMasterRepositoryExtended insuranceMasterRepositoryExtended;
    public synchronized Map<String, Object> csvToInsuranceMasterDTOMapper(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<InsuranceMasterDTO> insuranceMasterDTOS = new ArrayList<InsuranceMasterDTO>();
            Map<String, Object> insuranceMasterDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueInsuranceIdNoSet = insuranceMasterRepositoryExtended.findAll().stream().map(x -> x.getInsuranceIdNo()).collect(Collectors.toSet());
            Map<String, InsuranceMasterRejectedDTO> SkippedInsuranceMasterDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("insurance_id_no") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (insurance_id_no)");
                } else if (csvRecord.get("insurance_id_no").equals("NULL") || csvRecord.get("insurance_id_no").trim() == "") {
                    throw new InvalidAttributeValueException("Insurance_id_no must be provided");
                } else if (csvRecord.get("insurance_name").equals("NULL") || csvRecord.get("insurance_name").trim() == "") {
                    InsuranceMasterRejectedDTO insuranceMasterRejectedDTO = generateInsuranceMasterRejectedDTO(csvRecord);
                    insuranceMasterRejectedDTO.setMessage("Invalid Data");
                    SkippedInsuranceMasterDTO.put(csvRecord.get("insurance_id_no"), insuranceMasterRejectedDTO);
                } else if (!uniqueInsuranceIdNoSet.contains(csvRecord.get("insurance_id_no"))) {
                    insuranceMasterDTOS.add(convertCSVToInsuranceMasterDTO(csvRecord));
                    uniqueInsuranceIdNoSet.add(csvRecord.get("insurance_id_no"));
                } else if (uniqueInsuranceIdNoSet.contains(csvRecord.get("insurance_id_no"))) {
                    InsuranceMasterRejectedDTO insuranceMasterRejectedDTO = generateInsuranceMasterRejectedDTO(csvRecord);
                    insuranceMasterRejectedDTO.setMessage("Duplicate Data");
                    SkippedInsuranceMasterDTO.put(csvRecord.get("insurance_id_no"), insuranceMasterRejectedDTO);
                }
            }

            insuranceMasterDTOMap.put("insuranceMasterDTOS", insuranceMasterDTOS);
            insuranceMasterDTOMap.put("SkippedInsuranceMasterDTO", SkippedInsuranceMasterDTO);

            return insuranceMasterDTOMap;
        } catch (UnsupportedEncodingException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        } catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    private InsuranceMasterDTO convertCSVToInsuranceMasterDTO(CSVRecord csvRecord) {
        InsuranceMasterDTO insuranceMasterDTO = new InsuranceMasterDTO();

        insuranceMasterDTO.setInsuranceName(csvRecord.get("insurance_name"));
        insuranceMasterDTO.setInsurancePlanType(csvRecord.get("insurance_plan_type"));
        insuranceMasterDTO.setTaxType(csvRecord.get("tax_type"));
        insuranceMasterDTO.setTaxIncludedAllowableStatus(csvRecord.get("tax_included_allowable_status"));
        insuranceMasterDTO.setInsuranceGroupId(csvRecord.get("insurance_group_id") == null ? null : Long.valueOf(csvRecord.get("insurance_group_id")));
        insuranceMasterDTO.setPriceTableId(csvRecord.get("price_table_id") == null ? null : Long.valueOf(csvRecord.get("price_table_id")));
        insuranceMasterDTO.setClaimTypeDmeStatus(csvRecord.get("claim_type_dme_status"));
        insuranceMasterDTO.setClaimTypeMajorMadicalStatus(csvRecord.get("claim_type_major_madical_status"));
        insuranceMasterDTO.setClaimTypePharmacyStatus(csvRecord.get("claim_type_pharmacy_status"));
        insuranceMasterDTO.setPayPercentage(Double.valueOf(csvRecord.get("pay_percentage")));
        insuranceMasterDTO.setEnableSecondaryBillingStatus(csvRecord.get("enable_secondary_billing_status"));
        insuranceMasterDTO.setAmtPrintOnDeliveryTicketStatus(csvRecord.get("amt_print_on_delivery_ticket_status"));
        insuranceMasterDTO.setMedigapStatus(csvRecord.get("medigap_status"));
        insuranceMasterDTO.setMedigapNum(csvRecord.get("medigap_num") == "" || csvRecord.get("medigap_num") == null ? null : Long.valueOf(csvRecord.get("medigap_num")));
        insuranceMasterDTO.setNotes(csvRecord.get("notes"));
        insuranceMasterDTO.setStatus(csvRecord.get("status"));
        insuranceMasterDTO.setCreatedById(3L);
        insuranceMasterDTO.setCreatedDate(LocalDate.now());
        insuranceMasterDTO.setCreatedByName("Ritam");
        insuranceMasterDTO.setUpdatedByName(null);
        insuranceMasterDTO.setInsurancePayerIdNo(csvRecord.get("insurance_payer_id_no"));
        insuranceMasterDTO.setAddressLine1(csvRecord.get("address_line_1"));
        insuranceMasterDTO.setAddressLine2(csvRecord.get("address_line_2"));
        insuranceMasterDTO.setCity(csvRecord.get("city"));
        insuranceMasterDTO.setState(csvRecord.get("state"));
        insuranceMasterDTO.setCountry(csvRecord.get("country"));
        insuranceMasterDTO.setZip(csvRecord.get("zip"));
        insuranceMasterDTO.setContactNo1(csvRecord.get("contact_no_1"));
        insuranceMasterDTO.setContactNo2(csvRecord.get("contact_no_2"));
        insuranceMasterDTO.setFax(csvRecord.get("fax"));
        insuranceMasterDTO.setEfax(csvRecord.get("efax"));
        insuranceMasterDTO.setEmail(csvRecord.get("email"));
        insuranceMasterDTO.setRelationship(csvRecord.get("relationship"));
        insuranceMasterDTO.setModeOfContact(csvRecord.get("mode_of_contact"));
        insuranceMasterDTO.setClaimProgramCode(csvRecord.get("claim_program_code"));
        insuranceMasterDTO.setClaimProgramName(csvRecord.get("claim_program_name"));
        insuranceMasterDTO.setInsuranceIdNo(csvRecord.get("insurance_id_no"));
        insuranceMasterDTO.setClaimProgramId(csvRecord.get("claim_program_id"));
        insuranceMasterDTO.setPriceTableName(csvRecord.get("price_table_name"));
        insuranceMasterDTO.setClaimFormName(csvRecord.get("claim_form_name"));
        insuranceMasterDTO.setInsuranceGroupName(csvRecord.get("insurance_group_name"));
        insuranceMasterDTO.setContactPersonFirstName(csvRecord.get("contact_person_first_name"));
        insuranceMasterDTO.setContactPersonMiddleName(csvRecord.get("contact_person_middle_name"));
        insuranceMasterDTO.setContactPersonLastName(csvRecord.get("contact_person_last_name"));
        insuranceMasterDTO.setInsuranceMasterUuid(UUID.randomUUID());
        CommonUtilities.dtoTrimmer(insuranceMasterDTO);
        return insuranceMasterDTO;
    }

    private InsuranceMasterRejectedDTO generateInsuranceMasterRejectedDTO(CSVRecord csvRecord) {
        InsuranceMasterRejectedDTO insuranceMasterRejectedDTO = new InsuranceMasterRejectedDTO();
        insuranceMasterRejectedDTO.setInsuranceGroupId(csvRecord.get("insurance_group_id") == null ? null : Long.valueOf(csvRecord.get("insurance_group_id")));
        insuranceMasterRejectedDTO.setInsuranceName(csvRecord.get("insurance_name"));
        insuranceMasterRejectedDTO.setInsurancePayerIdNo(csvRecord.get("insurance_payer_id_no"));
        insuranceMasterRejectedDTO.setInsurancePlanType(csvRecord.get("insurance_plan_type"));
        insuranceMasterRejectedDTO.setStatus(csvRecord.get("status"));
        insuranceMasterRejectedDTO.setInsuranceIdNo(csvRecord.get("insurance_id_no"));

        return insuranceMasterRejectedDTO;
    }
}
