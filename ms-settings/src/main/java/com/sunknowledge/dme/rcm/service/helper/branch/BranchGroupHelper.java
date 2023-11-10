package com.sunknowledge.dme.rcm.service.helper.branch;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.branch.BranchGroupRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupRejectedDTO;
import com.sunknowledge.dme.rcm.service.impl.branch.BranchGroupServiceExtendedImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BranchGroupHelper {

    private final Logger log = LoggerFactory.getLogger(BranchGroupHelper.class);
    @Autowired
    BranchGroupRepositoryExtended branchGroupRepositoryExtended;

    public synchronized Map<String, Object> csvToBranchGroupDTOMapper(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<BranchGroupDTO> branchGroupDTOS = new ArrayList<BranchGroupDTO>();
            Map<String, Object> branchGroupDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueBranchGroupNoSet = branchGroupRepositoryExtended.findAll().stream().map(x -> x.getBranchGroupName()).collect(Collectors.toSet());
            Map<String, BranchGroupRejectedDTO> SkippedBranchGroupDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("branch_group_name") == null) {

                    throw new InvalidAttributeValueException("Invalid Attribute (branch_group_name)");
                } else if (csvRecord.get("branch_group_name").trim() == "") {
                    BranchGroupRejectedDTO branchGroupRejectedDTO = generateBranchGroupRejectedDTO(csvRecord);
                    branchGroupRejectedDTO.setMessage("Invalid Data");
                    SkippedBranchGroupDTO.put(csvRecord.get("branch_group_id"), branchGroupRejectedDTO);
                } else if (!uniqueBranchGroupNoSet.contains(csvRecord.get("branch_group_name"))) {
                    branchGroupDTOS.add(convertCSVToBranchGroupDTO(csvRecord));
                    uniqueBranchGroupNoSet.add(csvRecord.get("branch_group_name"));
                } else if (uniqueBranchGroupNoSet.contains(csvRecord.get("branch_group_name"))) {
                    BranchGroupRejectedDTO branchGroupRejectedDTO = generateBranchGroupRejectedDTO(csvRecord);
                    branchGroupRejectedDTO.setMessage("Duplicate Data");
                    SkippedBranchGroupDTO.put(csvRecord.get("branch_group_id"), branchGroupRejectedDTO);
                }
            }
            branchGroupDTOMap.put("branchGroupDTOS", branchGroupDTOS);
            branchGroupDTOMap.put("SkippedBranchGroupDTO", SkippedBranchGroupDTO);
            return branchGroupDTOMap;
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

    private BranchGroupDTO convertCSVToBranchGroupDTO(CSVRecord csvRecord) {
        BranchGroupDTO branchGroupDTO = new BranchGroupDTO();
        branchGroupDTO.setBranchGroupName(csvRecord.get("branch_group_name"));
        //branchGroupDTO.setBranchGroupId();
        branchGroupDTO.setStatus(csvRecord.get("status"));
        branchGroupDTO.setCompanyId(Long.valueOf(csvRecord.get("company_id")));
        branchGroupDTO.setCreatedById(3L);
        branchGroupDTO.setCreatedByName("Ritam");
        branchGroupDTO.setUpdatedById(null);
        branchGroupDTO.setUpdatedByName(null);

        CommonUtilities.dtoTrimmer(branchGroupDTO);
        return branchGroupDTO;
    }

    private BranchGroupRejectedDTO generateBranchGroupRejectedDTO(CSVRecord csvRecord) {
        BranchGroupRejectedDTO branchGroupRejectedDTO = new BranchGroupRejectedDTO();
        branchGroupRejectedDTO.setBranchGroupName(csvRecord.get("branch_group_name"));
        branchGroupRejectedDTO.setCompanyId(Long.valueOf(csvRecord.get("company_id")));

        return branchGroupRejectedDTO;
    }
}
