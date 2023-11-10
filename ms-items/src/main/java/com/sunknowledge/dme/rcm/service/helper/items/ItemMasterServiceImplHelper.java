package com.sunknowledge.dme.rcm.service.helper.items;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.items.ItemMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerRejectedDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterRejectedDTO;
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
public class ItemMasterServiceImplHelper {
    @Autowired
    ItemMasterRepositoryExtended itemMasterRepositoryExtended;
    public synchronized Map<String, Object> csvToItemMasterDTOMapper(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<ItemMasterDTO> itemMasterDTOS = new ArrayList<ItemMasterDTO>();
            Map<String, Object> itemMasterDTOMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueItemNoSet = itemMasterRepositoryExtended.findAll().stream().map(x -> x.getItemNumber()).collect(Collectors.toSet());
            Map<String, ItemMasterRejectedDTO> SkippedItemMasterDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("item_number") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (item_number)");
                } else if (csvRecord.get("item_name") == null) {
                    throw new InvalidAttributeValueException("Invalid Attribute (item_name)");
                } else if (csvRecord.get("item_number").trim() == "") {
                    throw new InputMismatchException("Item_number must be provided");
                } else if (csvRecord.get("item_name").trim() == "") {
                    ItemMasterRejectedDTO itemMasterRejectedDTO = generateItemMasterRejectedDTO(csvRecord);
                    itemMasterRejectedDTO.setMessage("Invalid Data");
                    SkippedItemMasterDTO.put(csvRecord.get("item_number"), itemMasterRejectedDTO);
                } else if (!uniqueItemNoSet.contains(csvRecord.get("item_number"))) {
                    itemMasterDTOS.add(convertCSVToItemMasterDTO(csvRecord));
                    uniqueItemNoSet.add(csvRecord.get("item_number"));
                } else if (uniqueItemNoSet.contains(csvRecord.get("item_number"))) {
                    ItemMasterRejectedDTO itemMasterRejectedDTO = generateItemMasterRejectedDTO(csvRecord);
                    itemMasterRejectedDTO.setMessage("Duplicate Data");
                    SkippedItemMasterDTO.put(csvRecord.get("item_number"), itemMasterRejectedDTO);
                }
            }

            itemMasterDTOMap.put("itemMasterDTOS", itemMasterDTOS);
            itemMasterDTOMap.put("SkippedItemMasterDTO", SkippedItemMasterDTO);
            return itemMasterDTOMap;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    private ItemMasterDTO convertCSVToItemMasterDTO(CSVRecord csvRecord) {
        ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
        itemMasterDTO.setItemName(csvRecord.get("item_name"));
        itemMasterDTO.setItemDescription(csvRecord.get("item_description"));
        itemMasterDTO.setItemTypeId(csvRecord.get("item_type_id") == null ? null : Long.valueOf(csvRecord.get("item_type_id")));
        itemMasterDTO.setItemGroupId(csvRecord.get("item_group_id") == null ? null : Long.valueOf(csvRecord.get("item_group_id")));
        itemMasterDTO.setSaleType(csvRecord.get("sale_type"));
        itemMasterDTO.setCoverageType(csvRecord.get("coverage_type"));
        itemMasterDTO.setWeight(csvRecord.get("weight"));
        itemMasterDTO.setLotStatus(csvRecord.get("lot_status"));
        itemMasterDTO.setKitStatus(csvRecord.get("kit_status"));
        itemMasterDTO.setKitType(csvRecord.get("kit_type"));
        itemMasterDTO.setItemPricingDefaultRentalAmt(csvRecord.get("item_pricing_default_rental_amt") == null ? null : Double.valueOf(csvRecord.get("item_pricing_default_rental_amt")));
        itemMasterDTO.setItemPricingDefaultPurchaseAmt(csvRecord.get("item_pricing_default_purchase_amt") == null ? null : Double.valueOf(csvRecord.get("item_pricing_default_purchase_amt")));
        itemMasterDTO.setAutoReorderStatus(csvRecord.get("auto_reorder_status"));
        itemMasterDTO.setExcludePoStatus(csvRecord.get("exclude_po_status"));
        itemMasterDTO.setExcludeSoStatus(csvRecord.get("exclude_so_status"));
        itemMasterDTO.setNdc(csvRecord.get("ndc"));
        itemMasterDTO.setNdcUnitOfMeasurement(csvRecord.get("ndc_unit_of_measurement"));
        itemMasterDTO.setManufacturerName(csvRecord.get("manufacturer_name"));
        itemMasterDTO.setManufacturerId(csvRecord.get("manufacturer_id") == null ? null : Long.valueOf(csvRecord.get("manufacturer_id")));
        itemMasterDTO.setManufacturerBarcode(csvRecord.get("manufacturer_barcode"));
        itemMasterDTO.setDefaultVendorId(csvRecord.get("default_vendor_id") == null ? null : Long.valueOf(csvRecord.get("default_vendor_id")));
        itemMasterDTO.setExcludeStandardPriceingStat(csvRecord.get("exclude_standard_priceing_stat"));
        itemMasterDTO.setUserField1(csvRecord.get("user_field_1"));
        itemMasterDTO.setUserField2(csvRecord.get("user_field_2"));
        itemMasterDTO.setUserField3(csvRecord.get("user_field_3"));
        itemMasterDTO.setBillingMultiplier(csvRecord.get("billing_multiplier"));
        itemMasterDTO.setClaimNote(csvRecord.get("claim_note"));
        itemMasterDTO.setItemUom(csvRecord.get("item_uom"));
        itemMasterDTO.setStatus(csvRecord.get("status"));
        itemMasterDTO.setCreatedById(3L);
        itemMasterDTO.setCreatedDate(LocalDate.now());
        itemMasterDTO.setCreatedByName("Ritam");
        itemMasterDTO.setUpdatedByName(csvRecord.get("updated_by_name"));
        itemMasterDTO.setUpdatedById(Long.valueOf(csvRecord.get("updated_by_id")));
        itemMasterDTO.setItemMasterUuid(csvRecord.get("item_master_uuid") == null ? null : UUID.fromString(csvRecord.get("item_master_uuid")));
        itemMasterDTO.setItemNumber(csvRecord.get("item_number"));
        itemMasterDTO.setItemGroupName(csvRecord.get("item_group_name"));
        itemMasterDTO.setItemTypeName(csvRecord.get("item_type_name"));
        itemMasterDTO.setPrimaryProcedureCodeValue(csvRecord.get("primary_procedure_code_value"));
        itemMasterDTO.setDefaultVendorName(csvRecord.get("default_vendor_name"));
        itemMasterDTO.setResupplyTypeStatus(csvRecord.get("resupply_type_status") == null ? null : (csvRecord.get("resupply_type_status")));

        CommonUtilities.dtoTrimmer(itemMasterDTO);
        return itemMasterDTO;
    }

    private ItemMasterRejectedDTO generateItemMasterRejectedDTO(CSVRecord csvRecord) {
        ItemMasterRejectedDTO itemMasterRejectedDTO = new ItemMasterRejectedDTO();
        itemMasterRejectedDTO.setItemName(csvRecord.get("item_name"));
        itemMasterRejectedDTO.setItemDescription(csvRecord.get("item_description"));
        itemMasterRejectedDTO.setItemTypeId(csvRecord.get("item_type_id") == null ? null : Long.valueOf(csvRecord.get("item_type_id")));
        itemMasterRejectedDTO.setItemGroupName(csvRecord.get("item_group_name"));
        itemMasterRejectedDTO.setDefaultVendorId(csvRecord.get("default_vendor_id") == null ? null : Long.valueOf(csvRecord.get("default_vendor_id")));
        itemMasterRejectedDTO.setManufacturerBarcode(csvRecord.get("manufacturer_barcode"));
        itemMasterRejectedDTO.setManufacturerId(csvRecord.get("manufacturer_id") == null ? null : Long.valueOf(csvRecord.get("manufacturer_id")));
        itemMasterRejectedDTO.setManufacturerName(csvRecord.get("manufacturer_name"));
        itemMasterRejectedDTO.setStatus(csvRecord.get("status"));

        CommonUtilities.dtoTrimmer(itemMasterRejectedDTO);
        return itemMasterRejectedDTO;
    }
}
