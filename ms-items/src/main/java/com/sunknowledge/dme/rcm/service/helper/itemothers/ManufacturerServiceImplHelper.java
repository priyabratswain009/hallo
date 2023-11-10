package com.sunknowledge.dme.rcm.service.helper.itemothers;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.itemothers.ManufacturerRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerRejectedDTO;
import com.sunknowledge.dme.rcm.service.impl.itemothers.ManufacturerServiceExtendedImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
public class ManufacturerServiceImplHelper {
    private final Logger log = LoggerFactory.getLogger(ManufacturerServiceImplHelper.class);
    public static String TYPE = "text/csv";

    @Autowired
    ManufacturerRepositoryExtended manufacturerRepositoryExtended;
    public synchronized Map<String, Object> csvToManufacturerDTOMapper(InputStream is){

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<ManufacturerDTO> manufacturerDTOS = new ArrayList<ManufacturerDTO>();
            Map<String, Object> manufacturerDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueManufacturerNoSet = manufacturerRepositoryExtended.findAll().stream().map(x -> x.getManufacturerNo()).collect(Collectors.toSet());
            Map<String, ManufacturerRejectedDTO> SkippedManufacturerDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("manufacturer_no") == null || csvRecord.get("manufacturer_no").trim() == "") {
                    throw new InvalidAttributeValueException("Invalid Attribute (manufacturer_no)");
                } else if (csvRecord.get("manufacturer_name") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (manufacturer_name)");
                } else if (csvRecord.get("manufacturer_no").trim() == "") {
                    throw new InputMismatchException("Manufacturer_no must be provided");
                } else if (csvRecord.get("manufacturer_name").trim() == "") {
                    ManufacturerRejectedDTO manufacturerRejectedDTO = generateManufacturerRejectedDTO(csvRecord);
                    manufacturerRejectedDTO.setMessage("Invalid Data");
                    SkippedManufacturerDTO.put(csvRecord.get("manufacturer_no"), manufacturerRejectedDTO);
                } else if (!uniqueManufacturerNoSet.contains(csvRecord.get("manufacturer_no"))) {
                    manufacturerDTOS.add(convertCSVToManufacturerDTO(csvRecord));
                    uniqueManufacturerNoSet.add(csvRecord.get("manufacturer_no"));
                } else if (uniqueManufacturerNoSet.contains(csvRecord.get("manufacturer_no"))) {
                    ManufacturerRejectedDTO manufacturerRejectedDTO = generateManufacturerRejectedDTO(csvRecord);
                    manufacturerRejectedDTO.setMessage("Duplicate Data");
                    SkippedManufacturerDTO.put(csvRecord.get("manufacturer_no"), manufacturerRejectedDTO);
                }
            }

            manufacturerDTOMap.put("manufacturerDTOS", manufacturerDTOS);
            manufacturerDTOMap.put("SkippedManufacturerDTO", SkippedManufacturerDTO);
            return manufacturerDTOMap;
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

    public ManufacturerRejectedDTO generateManufacturerRejectedDTO(CSVRecord csvRecord){
        ManufacturerRejectedDTO manufacturerRejectedDTO = new ManufacturerRejectedDTO();
        manufacturerRejectedDTO.setManufacturerName(csvRecord.get("manufacturer_name"));
        manufacturerRejectedDTO.setStatus(csvRecord.get("status"));
        manufacturerRejectedDTO.setWebUrl(csvRecord.get("web_url"));
        manufacturerRejectedDTO.setManufacturerNo(csvRecord.get("manufacturer_no"));
        manufacturerRejectedDTO.setAddressLine1(csvRecord.get("address_line_1"));
        manufacturerRejectedDTO.setAddressLine2(csvRecord.get("address_line_2"));
        manufacturerRejectedDTO.setCity(csvRecord.get("city"));
        manufacturerRejectedDTO.setState(csvRecord.get("state"));
        manufacturerRejectedDTO.setZip(csvRecord.get("zip"));
        manufacturerRejectedDTO.setEmail(csvRecord.get("email"));
        manufacturerRejectedDTO.setFax(csvRecord.get("fax"));
        manufacturerRejectedDTO.setEfax(csvRecord.get("efax"));
        manufacturerRejectedDTO.setContactPersonName(csvRecord.get("contact_person_name"));
        manufacturerRejectedDTO.setContactNo1(csvRecord.get("contact_no_1"));
        manufacturerRejectedDTO.setContactNo2(csvRecord.get("contact_no_2"));

        return manufacturerRejectedDTO;
    }
    public ManufacturerDTO convertCSVToManufacturerDTO(CSVRecord csvRecord){
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setManufacturerId(csvRecord.get("manufacturer_id")==""?null:Long.valueOf(csvRecord.get("manufacturer_id")));
        manufacturerDTO.setManufacturerName(csvRecord.get("manufacturer_name"));
        manufacturerDTO.setStatus(csvRecord.get("status"));
        manufacturerDTO.setContactPersonName(csvRecord.get("contact_person_name"));
        manufacturerDTO.setWebUrl(csvRecord.get("web_url"));
        manufacturerDTO.setAddressLine1(csvRecord.get("address_line_1"));
        manufacturerDTO.setAddressLine2(csvRecord.get("address_line_2"));
        manufacturerDTO.setCity(csvRecord.get("city"));
        manufacturerDTO.setState(csvRecord.get("state"));
        manufacturerDTO.setZip(csvRecord.get("zip"));
        manufacturerDTO.setEmail(csvRecord.get("email"));
        manufacturerDTO.setFax(csvRecord.get("fax"));
        manufacturerDTO.setContactNo1(csvRecord.get("contact_no_1"));
        manufacturerDTO.setContactNo2(csvRecord.get("contact_no_2"));
        manufacturerDTO.setEfax(csvRecord.get("efax"));
        manufacturerDTO.setManufacturerNo(csvRecord.get("manufacturer_no"));
        manufacturerDTO.setCreatedById(3L); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        manufacturerDTO.setCreatedDate(LocalDateTime.now().toLocalDate());
        manufacturerDTO.setCreatedByName("Ritam"); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        manufacturerDTO.setUpdatedById(null); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        manufacturerDTO.setUpdatedByName(null); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)

        CommonUtilities.dtoTrimmer(manufacturerDTO);
        return manufacturerDTO;
    }
}
