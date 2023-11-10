package com.sunknowledge.dme.rcm.service.helper.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.others.ItemLocationRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationRejectedDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemLocationHelper {
    @Autowired
    ItemLocationRepositoryExtended itemLocationRepositoryExtended;

    public synchronized Map<String, Object> csvToItemLocationDTOMapper(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<ItemLocationDTO> itemLocationDTOS = new ArrayList<ItemLocationDTO>();
            Map<String, Object> itemLocationDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueItemLocationNameSet = itemLocationRepositoryExtended.findAll().stream().map(x -> x.getItemLocationName()).collect(Collectors.toSet());
            Map<String, ItemLocationRejectedDTO> SkippedItemLocationDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                System.out.println("csvRecords => " + csvRecords);
                if (csvRecord.get("item_location_name") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (item_location_name)");
                } else if (csvRecord.get("item_location_name").trim() == "") {
                    ItemLocationRejectedDTO itemLocationRejectedDTO = generateItemLocationRejectedDTO(csvRecord);
                    itemLocationRejectedDTO.setMessage("Invalid Data");
                    SkippedItemLocationDTO.put(csvRecord.get("item_location_name"), itemLocationRejectedDTO);
                } else if (!uniqueItemLocationNameSet.contains(csvRecord.get("item_location_name"))) {
                    itemLocationDTOS.add(convertCSVToItemLocationDTO(csvRecord));
                    uniqueItemLocationNameSet.add(csvRecord.get("item_location_name"));
                } else if (uniqueItemLocationNameSet.contains(csvRecord.get("item_location_name"))) {
                    ItemLocationRejectedDTO itemLocationRejectedDTO = generateItemLocationRejectedDTO(csvRecord);
                    itemLocationRejectedDTO.setMessage("Duplicate Data");
                    SkippedItemLocationDTO.put(csvRecord.get("item_location_name"), itemLocationRejectedDTO);
                }
            }

            itemLocationDTOMap.put("itemLocationDTOS", itemLocationDTOS);
            itemLocationDTOMap.put("SkippedItemLocationDTO", SkippedItemLocationDTO);
            System.out.println("branchGroupDTOMap  =>> " + itemLocationDTOMap);
            return itemLocationDTOMap;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    private ItemLocationDTO convertCSVToItemLocationDTO(CSVRecord csvRecord) {
        ItemLocationDTO itemLocationDTO = new ItemLocationDTO();
        itemLocationDTO.setItemLocationName(csvRecord.get("item_location_name"));
        itemLocationDTO.setDescription(csvRecord.get("description"));
        itemLocationDTO.setStatus(csvRecord.get("status"));
        itemLocationDTO.setBillingCity(csvRecord.get("billing_city"));
        itemLocationDTO.setBillingState(csvRecord.get("billing_state"));
        itemLocationDTO.setBillingAddressLine1(csvRecord.get("billing_address_line_1"));
        itemLocationDTO.setBillingAddressLine2(csvRecord.get("billing_address_line_2"));
        itemLocationDTO.setBillingCountry(csvRecord.get("billing_country"));
        itemLocationDTO.setBillingZip(csvRecord.get("billing_zip"));
        itemLocationDTO.setBilllingAddressCompanyName(csvRecord.get("billling_address_company_name"));
        itemLocationDTO.setContactFirstName(csvRecord.get("contact_first_name"));
        itemLocationDTO.setContactMiddleName(csvRecord.get("contact_middle_name"));
        itemLocationDTO.setContactLastName(csvRecord.get("contact_last_name"));
        itemLocationDTO.setContactNo1(csvRecord.get("contact_no_1"));
        itemLocationDTO.setContactNo2(csvRecord.get("contact_no_2"));
        itemLocationDTO.setFax(csvRecord.get("fax"));
        itemLocationDTO.setEmail(csvRecord.get("email"));
        itemLocationDTO.setDeliveryAddressLine1(csvRecord.get("delivery_address_line_1"));
        itemLocationDTO.setDeliveryAddressLine2(csvRecord.get("delivery_address_line_2"));
        itemLocationDTO.setDeliveryCity(csvRecord.get("delivery_city"));
        itemLocationDTO.setDeliveryState(csvRecord.get("delivery_state"));
        itemLocationDTO.setDeliveryZip(csvRecord.get("delivery_zip"));
        itemLocationDTO.setCreatedById(3L);
        itemLocationDTO.setCreatedByName("Ritam");
        itemLocationDTO.setUpdatedById(null);
        itemLocationDTO.setUpdatedByName(null);

        CommonUtilities.dtoTrimmer(itemLocationDTO);
        return itemLocationDTO;
    }

    private ItemLocationRejectedDTO generateItemLocationRejectedDTO(CSVRecord csvRecord) {
        ItemLocationRejectedDTO itemLocationRejectedDTO = new ItemLocationRejectedDTO();
        itemLocationRejectedDTO.setItemLocationName(csvRecord.get("item_location_name"));
        itemLocationRejectedDTO.setDescription(csvRecord.get("description"));
        itemLocationRejectedDTO.setStatus(csvRecord.get("status"));

        return itemLocationRejectedDTO;
    }
}
