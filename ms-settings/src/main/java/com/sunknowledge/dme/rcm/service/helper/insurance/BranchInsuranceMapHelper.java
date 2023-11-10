package com.sunknowledge.dme.rcm.service.helper.insurance;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.insurance.BranchInsuranceMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapRejectedDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BranchInsuranceMapHelper {

    @Autowired
    BranchInsuranceMapRepositoryExtended branchInsuranceMapRepositoryExtended;
    public synchronized Map<String, Object> csvToBranchInsuranceMapDTOMapper(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<BranchInsuranceMapDTO> branchInsuranceMapDTOS = new ArrayList<BranchInsuranceMapDTO>();
            Map<String, Object> branchInsuranceMapDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueBranchId_InsuranceIdSet = branchInsuranceMapRepositoryExtended.findAll().stream().map(x -> x.getInsuranceId()+"_"+x.getBranchId()).collect(Collectors.toSet());
            Map<String, BranchInsuranceMapRejectedDTO> SkippedBranchInsuranceMapDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("insurance_id") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (insurance_id)");
                } else if (csvRecord.get("branch_id") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (branch_id)");
                } else if (csvRecord.get("insurance_id").equals("NULL") || csvRecord.get("insurance_id").trim() == "") {
                    throw new InvalidAttributeValueException("insurance_id must be provided");
                } else if (csvRecord.get("branch_id").equals("NULL") || csvRecord.get("branch_id").trim() == "") {
                    throw new InvalidAttributeValueException("branch_id must be provided");
                } else if (csvRecord.get("insurance_name").equals("NULL") || csvRecord.get("insurance_name").trim() == "") {
                    BranchInsuranceMapRejectedDTO branchInsuranceMapRejectedDTO = generateBranchInsuranceMapRejectedDTO(csvRecord);
                    branchInsuranceMapRejectedDTO.setMessage("Invalid Data");
                    SkippedBranchInsuranceMapDTO.put("I"+csvRecord.get("insurance_id")+"_B"+csvRecord.get("branch_id"), branchInsuranceMapRejectedDTO);
                } else if (!uniqueBranchId_InsuranceIdSet.contains(csvRecord.get("insurance_id")+"_"+csvRecord.get("branch_id"))) {
                    branchInsuranceMapDTOS.add(convertCSVToBranchInsuranceMapDTO(csvRecord));
                    uniqueBranchId_InsuranceIdSet.add(csvRecord.get("insurance_id")+"_"+csvRecord.get("branch_id"));
                } else if (uniqueBranchId_InsuranceIdSet.contains(csvRecord.get("insurance_id")+"_"+csvRecord.get("branch_id"))) {
                    BranchInsuranceMapRejectedDTO branchInsuranceMapRejectedDTO = generateBranchInsuranceMapRejectedDTO(csvRecord);
                    branchInsuranceMapRejectedDTO.setMessage("Duplicate Data");
                    SkippedBranchInsuranceMapDTO.put("I"+csvRecord.get("insurance_id")+"_B"+csvRecord.get("branch_id"), branchInsuranceMapRejectedDTO);
                }
            }

            branchInsuranceMapDTOMap.put("branchInsuranceMapDTOS", branchInsuranceMapDTOS);
            branchInsuranceMapDTOMap.put("SkippedBranchInsuranceMapDTO", SkippedBranchInsuranceMapDTO);
            System.out.println("branchInsuranceMapDTOMap  =>> " + branchInsuranceMapDTOMap);
            return branchInsuranceMapDTOMap;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    private BranchInsuranceMapDTO convertCSVToBranchInsuranceMapDTO(CSVRecord csvRecord) {
        BranchInsuranceMapDTO branchInsuranceMapDTO = new BranchInsuranceMapDTO();
        branchInsuranceMapDTO.setBranchId(csvRecord.get("branch_id")==null?null:Long.valueOf(csvRecord.get("branch_id")));
        branchInsuranceMapDTO.setBranchName(csvRecord.get("branch_name"));
        branchInsuranceMapDTO.setInsuranceId(csvRecord.get("insurance_id")==null?null:Long.valueOf(csvRecord.get("insurance_id")));
        branchInsuranceMapDTO.setInsuranceName(csvRecord.get("insurance_name"));
        branchInsuranceMapDTO.setInsuranceIdNo(csvRecord.get("insurance_id_no"));
        branchInsuranceMapDTO.setInsuranceStateName(csvRecord.get("insurance_state_name"));
        branchInsuranceMapDTO.setPriceTableId(csvRecord.get("price_table_id")==null?null:Long.valueOf(csvRecord.get("price_table_id")));
        branchInsuranceMapDTO.setPriceTableName(csvRecord.get("price_table_name"));
        branchInsuranceMapDTO.setBranchNpi(csvRecord.get("branch_npi"));
        branchInsuranceMapDTO.setBranchPtan(csvRecord.get("branch_ptan"));
        branchInsuranceMapDTO.setEsubmitterPayorId(csvRecord.get("esubmitter_payor_id"));
        branchInsuranceMapDTO.setBranchTaxonomyCode(csvRecord.get("branch_taxonomy_code"));
        branchInsuranceMapDTO.setBillingProviderOverrideCompanyName(csvRecord.get("billing_provider_override_company_name"));
        branchInsuranceMapDTO.setBillingProviderOverrideTaxIdEin(csvRecord.get("billing_provider_override_tax_id_ein"));
        branchInsuranceMapDTO.setBillingProviderOverrideAddressLine1(csvRecord.get("billing_provider_override_address_line_1"));
        branchInsuranceMapDTO.setBillingProviderOverrideAddressLine2(csvRecord.get("billing_provider_override_address_line_2"));
        branchInsuranceMapDTO.setBillingProviderOverrideCity(csvRecord.get("billing_provider_override_city"));
        branchInsuranceMapDTO.setBillingProviderOverrideState(csvRecord.get("billing_provider_override_state"));
        branchInsuranceMapDTO.setBillingProviderOverrideZip(csvRecord.get("billing_provider_override_zip"));
        branchInsuranceMapDTO.setBillingProviderOverrideContact1(csvRecord.get("billing_provider_override_contact_1"));
        branchInsuranceMapDTO.setBillingProviderOverrideContact2(csvRecord.get("billing_provider_override_contact_2"));
        branchInsuranceMapDTO.setBillingProviderOverrideFax(csvRecord.get("billing_provider_override_fax"));
        branchInsuranceMapDTO.setBillingProviderOverrideEmail(csvRecord.get("billing_provider_override_email"));
        branchInsuranceMapDTO.setPayToProviderCompanyName(csvRecord.get("pay_to_provider_company_name"));
        branchInsuranceMapDTO.setPayToProviderTaxIdEin(csvRecord.get("pay_to_provider_tax_id_ein"));
        branchInsuranceMapDTO.setPayToProviderAddressLine1(csvRecord.get("pay_to_provider_address_line_1"));
        branchInsuranceMapDTO.setPayToProviderAddressLine2(csvRecord.get("pay_to_provider_address_line_2"));
        branchInsuranceMapDTO.setPayToProviderCity(csvRecord.get("pay_to_provider_city"));
        branchInsuranceMapDTO.setPayToProviderState(csvRecord.get("pay_to_provider_state"));
        branchInsuranceMapDTO.setPayToProviderZip(csvRecord.get("pay_to_provider_zip"));
        branchInsuranceMapDTO.setPayToProviderContact1(csvRecord.get("pay_to_provider_contact_1"));
        branchInsuranceMapDTO.setPayToProviderContact2(csvRecord.get("pay_to_provider_contact_2"));
        branchInsuranceMapDTO.setPayToProviderFax(csvRecord.get("pay_to_provider_fax"));
        branchInsuranceMapDTO.setPayToProviderEmail(csvRecord.get("pay_to_provider_email"));
        branchInsuranceMapDTO.setSubmitterName(csvRecord.get("submitter_name"));
        branchInsuranceMapDTO.setSubmitterContact1(csvRecord.get("submitter_contact_1"));
        branchInsuranceMapDTO.setSubmitterContact2(csvRecord.get("submitter_contact_2"));
        branchInsuranceMapDTO.setStatus(csvRecord.get("status"));
        branchInsuranceMapDTO.setCreatedById(3L);
        branchInsuranceMapDTO.setCreatedByName(csvRecord.get("created_by_name"));
        branchInsuranceMapDTO.setCreatedDate(LocalDate.now());
        branchInsuranceMapDTO.setUpdatedById(null);
        branchInsuranceMapDTO.setUpdatedByName(null);
        branchInsuranceMapDTO.setUpdatedDate(null);
        branchInsuranceMapDTO.setBranchInsuranceMapUuid(UUID.randomUUID());

        CommonUtilities.dtoTrimmer(branchInsuranceMapDTO);
        return branchInsuranceMapDTO;
    }

    private BranchInsuranceMapRejectedDTO generateBranchInsuranceMapRejectedDTO(CSVRecord csvRecord) {
        BranchInsuranceMapRejectedDTO branchInsuranceMapRejectedDTO = new BranchInsuranceMapRejectedDTO();
        branchInsuranceMapRejectedDTO.setBranchId(csvRecord.get("branch_id")==null?null:Long.valueOf(csvRecord.get("branch_id")));
        branchInsuranceMapRejectedDTO.setBranchName(csvRecord.get("branch_name"));
        branchInsuranceMapRejectedDTO.setInsuranceId(csvRecord.get("insurance_id")==null?null:Long.valueOf(csvRecord.get("insurance_id")));
        branchInsuranceMapRejectedDTO.setInsuranceName(csvRecord.get("insurance_name"));
        branchInsuranceMapRejectedDTO.setInsuranceIdNo(csvRecord.get("insurance_id_no"));
        branchInsuranceMapRejectedDTO.setInsuranceStateName(csvRecord.get("insurance_state_name"));
        branchInsuranceMapRejectedDTO.setPriceTableId(Long.valueOf(csvRecord.get("price_table_id")));
        branchInsuranceMapRejectedDTO.setPriceTableName(csvRecord.get("price_table_name"));
        branchInsuranceMapRejectedDTO.setBranchNpi(csvRecord.get("branch_npi"));
        branchInsuranceMapRejectedDTO.setBranchPtan(csvRecord.get("branch_ptan"));
        branchInsuranceMapRejectedDTO.setEsubmitterPayorId(csvRecord.get("esubmitter_payor_id"));
        branchInsuranceMapRejectedDTO.setBranchTaxonomyCode(csvRecord.get("branch_taxonomy_code"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideCompanyName(csvRecord.get("billing_provider_override_company_name"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideTaxIdEin(csvRecord.get("billing_provider_override_tax_id_ein"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideAddressLine1(csvRecord.get("billing_provider_override_address_line_1"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideAddressLine2(csvRecord.get("billing_provider_override_address_line_2"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideCity(csvRecord.get("billing_provider_override_city"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideState(csvRecord.get("billing_provider_override_state"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideZip(csvRecord.get("billing_provider_override_zip"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideContact1(csvRecord.get("billing_provider_override_contact_1"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideContact2(csvRecord.get("billing_provider_override_contact_2"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideFax(csvRecord.get("billing_provider_override_fax"));
        branchInsuranceMapRejectedDTO.setBillingProviderOverrideEmail(csvRecord.get("billing_provider_override_email"));
        branchInsuranceMapRejectedDTO.setPayToProviderCompanyName(csvRecord.get("pay_to_provider_company_name"));
        branchInsuranceMapRejectedDTO.setPayToProviderTaxIdEin(csvRecord.get("pay_to_provider_tax_id_ein"));
        branchInsuranceMapRejectedDTO.setPayToProviderAddressLine1(csvRecord.get("pay_to_provider_address_line_1"));
        branchInsuranceMapRejectedDTO.setPayToProviderAddressLine2(csvRecord.get("pay_to_provider_address_line_2"));
        branchInsuranceMapRejectedDTO.setPayToProviderCity(csvRecord.get("pay_to_provider_city"));
        branchInsuranceMapRejectedDTO.setPayToProviderState(csvRecord.get("pay_to_provider_state"));
        branchInsuranceMapRejectedDTO.setPayToProviderZip(csvRecord.get("pay_to_provider_zip"));
        branchInsuranceMapRejectedDTO.setPayToProviderContact1(csvRecord.get("pay_to_provider_contact_1"));
        branchInsuranceMapRejectedDTO.setPayToProviderContact2(csvRecord.get("pay_to_provider_contact_2"));
        branchInsuranceMapRejectedDTO.setPayToProviderFax(csvRecord.get("pay_to_provider_fax"));
        branchInsuranceMapRejectedDTO.setPayToProviderEmail(csvRecord.get("pay_to_provider_email"));
        branchInsuranceMapRejectedDTO.setSubmitterName(csvRecord.get("submitter_name"));
        branchInsuranceMapRejectedDTO.setSubmitterContact1(csvRecord.get("submitter_contact_1"));
        branchInsuranceMapRejectedDTO.setSubmitterContact2(csvRecord.get("submitter_contact_2"));
        branchInsuranceMapRejectedDTO.setStatus(csvRecord.get("status"));
        branchInsuranceMapRejectedDTO.setCreatedById(3L);
        branchInsuranceMapRejectedDTO.setCreatedByName(csvRecord.get("created_by_name"));
        branchInsuranceMapRejectedDTO.setCreatedDate(LocalDate.now());
        branchInsuranceMapRejectedDTO.setUpdatedById(null);
        branchInsuranceMapRejectedDTO.setUpdatedByName(null);
        branchInsuranceMapRejectedDTO.setUpdatedDate(null);
        branchInsuranceMapRejectedDTO.setBranchInsuranceMapUuid(UUID.fromString(csvRecord.get("branch_insurance_map_uuid")));

        return branchInsuranceMapRejectedDTO;
    }
}
