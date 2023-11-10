package com.sunknowledge.dme.rcm.service.itemothers;

import com.sunknowledge.dme.rcm.service.ManufacturerService;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerParameterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManufacturerServiceExtended extends ManufacturerService {
    ResponseDTO saveManufacturer(ManufacturerParameterDTO manufacturerDTO);

    ResponseDTO bulkUploadForManufacturer(MultipartFile documentFile);

    List<ManufacturerDTO> getManufacturerById(Long manufactureId);

    List<ManufacturerDTO> getManufacturerByManufacturerName(String manufactureName);

    List<ManufacturerDTO> getManufacturerByManufacturerNo(String manufacturerNo);

    List<ManufacturerDTO> getAllManufacturerData();

    List<ManufacturerDTO> getManufactureByStatus(String status);

    ResponseDTO setManufacturerStatusById(Long manufactureById, String status);
}
