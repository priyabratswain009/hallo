package com.sunknowledge.dme.rcm.service.helper.branch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.branch.BranchOfficeRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeRejectedDTO;
import com.sunknowledge.dme.rcm.service.impl.branch.BranchOfficeServiceExtendedImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.gaul.modernizer_maven_annotations.SuppressModernizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BranchOfficeHelper {
    private final Logger log = LoggerFactory.getLogger(BranchOfficeHelper.class);
    @Autowired
    BranchOfficeRepositoryExtended branchOfficeRepositoryExtended;

    @SuppressModernizer
    public synchronized Map<String, Object> csvToBranchOfficeDTOMapper(InputStream is){

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<BranchOfficeDTO> branchOfficeDTOS = new ArrayList<BranchOfficeDTO>();
            Map<String, Object> branchOfficeDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueBranchGroupNoSet = branchOfficeRepositoryExtended.findAll().stream().map(x -> x.getNpi()).collect(Collectors.toSet());
            Map<String, BranchOfficeRejectedDTO> SkippedBranchOfficeDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("npi") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (npi)");
                } else if (csvRecord.get("branch_name") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (branch_name)");
                } else if (csvRecord.get("npi").trim() == "") {
                    throw new InputMismatchException("Npi must be provided");
                } else if (csvRecord.get("branch_name").trim() == "") {
                    BranchOfficeRejectedDTO branchGroupRejectedDTO = generateBranchOfficeRejectedDTO(csvRecord);
                    branchGroupRejectedDTO.setMessage("Invalid Data");
                    SkippedBranchOfficeDTO.put(csvRecord.get("npi"), branchGroupRejectedDTO);
                } else if (!uniqueBranchGroupNoSet.contains(csvRecord.get("npi"))) {
                    branchOfficeDTOS.add(convertCSVToBranchOfficeDTO(csvRecord));
                    uniqueBranchGroupNoSet.add(csvRecord.get("npi"));
                } else if (uniqueBranchGroupNoSet.contains(csvRecord.get("npi"))) {
                    BranchOfficeRejectedDTO branchGroupRejectedDTO = generateBranchOfficeRejectedDTO(csvRecord);
                    branchGroupRejectedDTO.setMessage("Duplicate Data");
                    SkippedBranchOfficeDTO.put(csvRecord.get("npi"), branchGroupRejectedDTO);
                }
            }

            branchOfficeDTOMap.put("branchOfficeDTOS", branchOfficeDTOS);
            branchOfficeDTOMap.put("SkippedBranchOfficeDTO", SkippedBranchOfficeDTO);
            return branchOfficeDTOMap;
        } catch (UnsupportedEncodingException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        } catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

    private BranchOfficeDTO convertCSVToBranchOfficeDTO(CSVRecord csvRecord) {
        BranchOfficeDTO branchOfficeDTO = new BranchOfficeDTO();
        branchOfficeDTO.setBranchId(csvRecord.get("branch_id")==null?null:Long.valueOf(csvRecord.get("branch_id")));
        branchOfficeDTO.setBranchName(csvRecord.get("branch_name"));
        branchOfficeDTO.setBranchGroupId(csvRecord.get("branch_group_id")==null?null:Long.valueOf(csvRecord.get("branch_group_id")));
        branchOfficeDTO.setNpi(csvRecord.get("npi"));
        branchOfficeDTO.setPtan(csvRecord.get("ptan"));
        branchOfficeDTO.setTaxonomyCode(csvRecord.get("taxonomy_code"));
        branchOfficeDTO.setTaxonomyCodeDescription(csvRecord.get("taxonomy_code_description"));
        branchOfficeDTO.setStatus(csvRecord.get("status"));
        branchOfficeDTO.setTaxIdType(csvRecord.get("tax_id_type"));
        branchOfficeDTO.setTaxIdNo(csvRecord.get("tax_id_no"));
        branchOfficeDTO.setBranchNo(csvRecord.get("branch_no"));
        branchOfficeDTO.setBillingAddressLine1(csvRecord.get("billing_address_line_1"));
        branchOfficeDTO.setBillingAddressLine2(csvRecord.get("billing_address_line_2"));
        branchOfficeDTO.setBillingCity(csvRecord.get("billing_city"));
        branchOfficeDTO.setBillingState(csvRecord.get("billing_state"));
        branchOfficeDTO.setBillingZipCode(csvRecord.get("billing_zip_code"));
        branchOfficeDTO.setSubmitterContactPersonName(csvRecord.get("submitter_contact_person_name"));
        branchOfficeDTO.setSubmitterContactPhoneNo1(csvRecord.get("submitter_contact_phone_no_1"));
        branchOfficeDTO.setSubmitterContactPhoneNo2(csvRecord.get("submitter_contact_phone_no_2"));
        branchOfficeDTO.setBillingFax(csvRecord.get("billing_fax"));
        branchOfficeDTO.setContactEmail(csvRecord.get("contact_email"));
        branchOfficeDTO.setZip(csvRecord.get("zip"));
        branchOfficeDTO.setBillingContactNo1(csvRecord.get("billing_contact_no_1"));
        branchOfficeDTO.setBillingContactNo2(csvRecord.get("billing_contact_no_2"));
        branchOfficeDTO.setBillingEmail(csvRecord.get("billing_email"));
        branchOfficeDTO.setBranchCompany(csvRecord.get("branch_company"));
        branchOfficeDTO.setBranchGroupName(csvRecord.get("branch_group_name"));
        branchOfficeDTO.setAddressLine1(csvRecord.get("address_line_1"));
        branchOfficeDTO.setAddressLine2(csvRecord.get("address_line_2"));
        branchOfficeDTO.setCity(csvRecord.get("city"));
        branchOfficeDTO.setState(csvRecord.get("state"));
        branchOfficeDTO.setSignatureName(csvRecord.get("signature_name"));
        branchOfficeDTO.setItemLocationId(csvRecord.get("item_location_id"));
        branchOfficeDTO.setItemLocationName(csvRecord.get("item_location_name"));
        branchOfficeDTO.setBranchCompanyId(csvRecord.get("branch_company_id")==null?null:Long.valueOf(csvRecord.get("branch_company_id")));
        branchOfficeDTO.setIsDropshipAllowed(csvRecord.get("is_dropship_allowed"));
        branchOfficeDTO.setCreatedById(3L);
        branchOfficeDTO.setCreatedByName("Ritam");
        branchOfficeDTO.setCreatedDate(LocalDateTime.now().toLocalDate());
        branchOfficeDTO.setBillingTaxonomyCode(csvRecord.get("billing_taxonomy_code"));
        branchOfficeDTO.setBillingNpi(csvRecord.get("billing_npi"));
        branchOfficeDTO.setBillingOrganisationName(csvRecord.get("billing_organisation_name"));
        branchOfficeDTO.setBillingTaxIdNo(csvRecord.get("billing_tax_id_no"));
        branchOfficeDTO.setBillingBranchName(csvRecord.get("billing_branch_name"));
        branchOfficeDTO.setFax(csvRecord.get("fax"));
        CommonUtilities.dtoTrimmer(branchOfficeDTO);
        return branchOfficeDTO;
    }

    private BranchOfficeRejectedDTO generateBranchOfficeRejectedDTO(CSVRecord csvRecord) {
        BranchOfficeRejectedDTO branchOfficeRejectedDTO = new BranchOfficeRejectedDTO();
        branchOfficeRejectedDTO.setBranchName(csvRecord.get("branch_name"));
        branchOfficeRejectedDTO.setBranchGroupId(Long.valueOf(csvRecord.get("branch_group_id")));
        branchOfficeRejectedDTO.setNpi(csvRecord.get("npi"));
        branchOfficeRejectedDTO.setPtan(csvRecord.get("ptan"));
        branchOfficeRejectedDTO.setTaxonomyCode(csvRecord.get("taxonomy_code"));
        branchOfficeRejectedDTO.setTaxonomyCodeDescription(csvRecord.get("taxonomy_code_description"));
        branchOfficeRejectedDTO.setStatus(csvRecord.get("status"));
        branchOfficeRejectedDTO.setTaxIdType(csvRecord.get("tax_id_type"));
        branchOfficeRejectedDTO.setTaxIdNo(csvRecord.get("tax_id_no"));
        branchOfficeRejectedDTO.setBranchNo(csvRecord.get("branch_no"));
        branchOfficeRejectedDTO.setBillingAddressLine1(csvRecord.get("billing_address_line_1"));
        branchOfficeRejectedDTO.setBillingAddressLine2(csvRecord.get("billing_address_line_2"));
        branchOfficeRejectedDTO.setBillingCity(csvRecord.get("billing_city"));
        branchOfficeRejectedDTO.setBillingState(csvRecord.get("billing_state"));
        branchOfficeRejectedDTO.setBillingZipCode(csvRecord.get("billing_zip_code"));
        branchOfficeRejectedDTO.setSubmitterContactPersonName(csvRecord.get("submitter_contact_person_name"));
        branchOfficeRejectedDTO.setSubmitterContactPhoneNo1(csvRecord.get("submitter_contact_phone_no_1"));
        branchOfficeRejectedDTO.setSubmitterContactPhoneNo2(csvRecord.get("submitter_contact_phone_no_2"));
        branchOfficeRejectedDTO.setBillingFax(csvRecord.get("billing_fax"));
        branchOfficeRejectedDTO.setContactEmail(csvRecord.get("contact_email"));
        branchOfficeRejectedDTO.setPayToProviderZip(csvRecord.get("pay_to_provider_zip"));
        branchOfficeRejectedDTO.setContactNo1(csvRecord.get("contact_no_1"));
        branchOfficeRejectedDTO.setContactNo2(csvRecord.get("contact_no_2"));
        branchOfficeRejectedDTO.setBillingEmail(csvRecord.get("billing_email"));
        branchOfficeRejectedDTO.setBranchCompany(csvRecord.get("branch_company"));
        branchOfficeRejectedDTO.setBranchGroupName(csvRecord.get("branch_group_name"));
        branchOfficeRejectedDTO.setPayToProviderAddressLine1(csvRecord.get("pay_to_provider_address_line_1"));
        branchOfficeRejectedDTO.setPayToProviderAddressLine2(csvRecord.get("pay_to_provider_address_line_2"));
        branchOfficeRejectedDTO.setPayToProviderCity(csvRecord.get("pay_to_provider_city"));
        branchOfficeRejectedDTO.setPayToProviderState(csvRecord.get("pay_to_provider_state"));
        branchOfficeRejectedDTO.setSignatureName(csvRecord.get("signature_name"));
        branchOfficeRejectedDTO.setItemLocationId(csvRecord.get("item_location_id"));
        branchOfficeRejectedDTO.setItemLocationName(csvRecord.get("item_location_name"));
        branchOfficeRejectedDTO.setBranchCompanyId(csvRecord.get("branch_company_id")==null?null:Long.valueOf(csvRecord.get("branch_company_id")));
        branchOfficeRejectedDTO.setIsDropshipAllowed(csvRecord.get("is_dropship_allowed"));
        return branchOfficeRejectedDTO;
    }
}
