package com.sunknowledge.dme.rcm.service.itemothers;

import com.sunknowledge.dme.rcm.service.VendorMasterService;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterParameterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VendorMasterServiceExtended extends VendorMasterService {
    ResponseDTO bulkUploadForVendor(MultipartFile file);

    ResponseDTO saveVendor(VendorMasterParameterDTO vendorMasterDTO);

    List<VendorMasterDTO> getVendorByVendorNo(String vendorNo);

    List<VendorMasterDTO> getVendorByVendorName(String vendorName);

    List<VendorMasterDTO> getVendorById(Long vendorId);

    List<VendorMasterDTO> getVendorByVendorDescription(String vendorDescription);

    List<VendorMasterDTO> getAllVendorMasterData();

    List<VendorMasterDTO> getVendorByStatus(String status);

    Optional<VendorMasterDTO> findByVendorId(Long valueOf);

    ResponseDTO setVendorMasterStatusById(Long id, String status);
}
