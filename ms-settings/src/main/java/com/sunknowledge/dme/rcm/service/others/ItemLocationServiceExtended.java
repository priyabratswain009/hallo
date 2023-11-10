package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.ItemLocationService;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.ItemLocationParameterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ItemLocationServiceExtended extends ItemLocationService {
    ResponseDTO bulkUploadForItemLocation(MultipartFile documentFile);

    ResponseDTO saveItemLocation(ItemLocationParameterDTO itemLocationDTO);

    List<ItemLocationDTO> getItemLocationById(Long itemLocationId);

    List<ItemLocationDTO> getItemLocationByItemLocationName(String itemLocationName);

    List<ItemLocationDTO> getItemLocationByDescription(String description);

    List<ItemLocationDTO> getAllItemLocationData();

    Optional<ItemLocationDTO> findByItemLocationIdIn(Long itemLocationId);

    ResponseDTO setItemLocationStatusById(Long itemLocationId, String status);

    List<ItemLocationDTO> getItemLocationByStatus(String status);
}
