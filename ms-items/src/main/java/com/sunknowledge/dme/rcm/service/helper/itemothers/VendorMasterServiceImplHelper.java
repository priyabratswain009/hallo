package com.sunknowledge.dme.rcm.service.helper.itemothers;


import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.itemothers.VendorMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterRejectedDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class VendorMasterServiceImplHelper {
    public static String TYPE = "text/csv";
    @Autowired
    VendorMasterRepositoryExtended vendorMasterRepositoryExtended;

    public synchronized Map<String,Object> csvToVendorMasterDTOMapper(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<VendorMasterDTO> vendorMasterDTOS = new ArrayList<VendorMasterDTO>();
            Map<String,Object> VendorMasterDTOMap = new HashMap<>();
            List<String> headers = csvParser.getHeaderNames();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            Set uniqueVendorNoSet = vendorMasterRepositoryExtended.findAll().stream().map(x -> x.getVendorNo()).collect(Collectors.toSet());
            Map<String, VendorMasterRejectedDTO> SkippedVendorMasterDTO = new HashMap<>();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get("vendor_no") == null || csvRecord.get("vendor_no").trim() == "") {
                    throw new InputMismatchException("Vendor_No must be provided");
                } else if (csvRecord.get("vendor_name") == null || csvRecord.get("vendor_name").trim() == "") {
                    VendorMasterRejectedDTO vendorMasterRejectedDTO = generateVendorMasterRejectedDTO(csvRecord);
                    vendorMasterRejectedDTO.setMessage("Invalid Data");
                    SkippedVendorMasterDTO.put(csvRecord.get("vendor_no"), vendorMasterRejectedDTO);
                } else if (!uniqueVendorNoSet.contains(csvRecord.get("vendor_no"))) {
                    vendorMasterDTOS.add(convertCSVToVendorMasterDTO(csvRecord));
                    uniqueVendorNoSet.add(csvRecord.get("vendor_no"));
                } else if (uniqueVendorNoSet.contains(csvRecord.get("vendor_no"))) {
                    VendorMasterRejectedDTO vendorMasterRejectedDTO = generateVendorMasterRejectedDTO(csvRecord);
                    vendorMasterRejectedDTO.setMessage("Duplicate Data");
                    SkippedVendorMasterDTO.put(csvRecord.get("vendor_no"), vendorMasterRejectedDTO);
                }
            }
            VendorMasterDTOMap.put("vendorMasterDTOS",vendorMasterDTOS);
            VendorMasterDTOMap.put("SkippedVendorMasterDTO",SkippedVendorMasterDTO);
            return VendorMasterDTOMap;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

    public VendorMasterRejectedDTO generateVendorMasterRejectedDTO(CSVRecord csvRecord){
        VendorMasterRejectedDTO vendorMasterRejectedDTO = new VendorMasterRejectedDTO();
        vendorMasterRejectedDTO.setVendorName(csvRecord.get("vendor_name"));
        vendorMasterRejectedDTO.setStatus(csvRecord.get("status"));
        vendorMasterRejectedDTO.setVendorDescription(csvRecord.get("vendor_description"));
        vendorMasterRejectedDTO.setVendorNo(csvRecord.get("vendor_no"));
        vendorMasterRejectedDTO.setVendorAccountNo(csvRecord.get("vendor_account_no"));
        vendorMasterRejectedDTO.setDefaultPoType(csvRecord.get("default_po_type"));
        vendorMasterRejectedDTO.setDefaultShopType(csvRecord.get("default_shop_type"));
        vendorMasterRejectedDTO.setAddressLine1(csvRecord.get("address_line_1"));
        vendorMasterRejectedDTO.setAddressLine2(csvRecord.get("address_line_2"));
        vendorMasterRejectedDTO.setCity(csvRecord.get("city"));
        vendorMasterRejectedDTO.setState(csvRecord.get("state"));
        vendorMasterRejectedDTO.setZip(csvRecord.get("zip"));
        vendorMasterRejectedDTO.setEmail(csvRecord.get("email"));
        vendorMasterRejectedDTO.setFax(csvRecord.get("fax"));
        vendorMasterRejectedDTO.setEfax(csvRecord.get("efax"));
        vendorMasterRejectedDTO.setContactPersonName(csvRecord.get("contact_person_name"));
        vendorMasterRejectedDTO.setContactNo1(csvRecord.get("contact_no_1"));
        vendorMasterRejectedDTO.setContactNo2(csvRecord.get("contact_no_2"));

        return vendorMasterRejectedDTO;
    }
    public VendorMasterDTO convertCSVToVendorMasterDTO(CSVRecord csvRecord){
        VendorMasterDTO vendorMasterDTO = new VendorMasterDTO();
        vendorMasterDTO.setVendorId(csvRecord.get("vendor_id") == "" ? null : Long.parseLong(csvRecord.get("vendor_id")));
        vendorMasterDTO.setVendorName(csvRecord.get("vendor_name"));
        vendorMasterDTO.setStatus(csvRecord.get("status"));
        vendorMasterDTO.setCreatedDate(LocalDateTime.now().toLocalDate());
        vendorMasterDTO.setVendorDescription(csvRecord.get("vendor_description"));
        vendorMasterDTO.setVendorNo(csvRecord.get("vendor_no"));
        vendorMasterDTO.setCreatedById(3L); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        vendorMasterDTO.setCreatedByName("Ritam"); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        vendorMasterDTO.setUpdatedByName(null); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        vendorMasterDTO.setUpdatedById(null); // SHOULD BE RETRIVE FROM LOGIN USER DETAILS (USER MASTER)
        vendorMasterDTO.setVendorAccountNo(csvRecord.get("vendor_account_no"));
        vendorMasterDTO.setDefaultPoType(csvRecord.get("default_po_type"));
        vendorMasterDTO.setDefaultShopType(csvRecord.get("default_shop_type"));
        vendorMasterDTO.setAddressLine1(csvRecord.get("address_line_1"));
        vendorMasterDTO.setAddressLine2(csvRecord.get("address_line_2"));
        vendorMasterDTO.setCity(csvRecord.get("city"));
        vendorMasterDTO.setState(csvRecord.get("state"));
        vendorMasterDTO.setZip(csvRecord.get("zip"));
        vendorMasterDTO.setEmail(csvRecord.get("email"));
        vendorMasterDTO.setFax(csvRecord.get("fax"));
        vendorMasterDTO.setEfax(csvRecord.get("efax"));
        vendorMasterDTO.setContactPersonName(csvRecord.get("contact_person_name"));
        vendorMasterDTO.setContactNo1(csvRecord.get("contact_no_1"));
        vendorMasterDTO.setContactNo2(csvRecord.get("contact_no_2"));
        CommonUtilities.dtoTrimmer(vendorMasterDTO);
        return vendorMasterDTO;
    }
}
