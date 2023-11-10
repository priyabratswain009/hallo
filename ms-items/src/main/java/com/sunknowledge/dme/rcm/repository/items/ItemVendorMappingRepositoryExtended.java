package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemVendorMapping;
import com.sunknowledge.dme.rcm.repository.ItemVendorMappingRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;

import java.util.List;

public interface ItemVendorMappingRepositoryExtended extends ItemVendorMappingRepository {
    List<ItemVendorMapping> findByVendorIdAndItemIdInAndStatusIgnoreCase(Long vendorId, List<Long> itemIdList, String active);

    List<ItemVendorMapping> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<ItemVendorMapping> findByVendorIdAndStatusIgnoreCase(Long vendorId, String active);

    List<ItemVendorMapping> findByItemNameLikeAndStatusIgnoreCase(String itemName, String active);

    List<ItemVendorMapping> findByVendorNameLikeAndStatusIgnoreCase(String vendorName, String active);

    List<ItemVendorMapping> findByStatusIgnoreCase(String status);

    List<ItemVendorMapping> findByItemVendorId(Long itemVendorId);

    List<ItemVendorMapping> findByItemNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<ItemVendorMapping> findByVendorNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<ItemVendorMapping> findByItemIdAndVendorIdAndStatusIgnoreCase(Long itemId, Long vendorId, String active);
}
