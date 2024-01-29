package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.ItemMasterService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterCombinedSearchInputDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterParameterDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Map;

public interface ItemMasterServiceExtended extends ItemMasterService {
    ResponseDTO bulkUploadForItemMaster(MultipartFile documentFile);

    ResponseDTO saveItemMaster(ItemMasterParameterDTO itemMasterParameterDTO);

    ItemMasterOutputDTO getItemMasterById(Long itemId);

    List<ItemMasterOutputDTO> getItemByItemName(String itemName);

    List<ItemMasterOutputDTO> getItemByItemNo(String itemNumber);

    List<ItemMasterOutputDTO> getItemByItemDescription(String itemDescription);

    List<ItemMasterOutputDTO> getAllItemMasterData();

    List<ItemMasterOutputDTO> getItemByStatus(String status);

    List<ItemMasterDTO> findByItemIdIn(List<Long> itemIdList);

    List<Map> getItemByItemNoOrItemNameAndLocationIdAndPricetableId(String itemNo, String itemName, Long locationId, Long pricetableId);

    List<Map> getItemByItemNoOrItemNameAndPricetableId(String itemNo, String itemName, Long pricetableId);

    ResponseDTO setItemStatusById(Long itemId, String status);

    List<Map> getItemsForSoByItemLocationIdAndItemNoAndProcCodeAndItemNameAndGroupId(String itemLocationId, String itemNo, String procCode, String itemName, String groupId) throws InvalidAttributeValueException;

    List<Map> getItemsForPricingByItemNoAndItemNameAndLidAndPtid(String itemNo, String itemName, Long lid, Long ptid) throws InvalidAttributeValueException;

    List<Map> itemPriceSearchByPriceTableId(String itemNo, String procCode, String priceTableId, String purchaseType, String dos) throws InvalidAttributeValueException;

    ServiceOutcome getItemMasterDataByCombinedSearchParameters(ItemMasterCombinedSearchInputDTO itemMasterCombinedSearchInputDTO);
}
