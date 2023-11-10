package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import com.sunknowledge.dme.rcm.repository.ItemItemlocationMapRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;

import java.util.List;

public interface ItemItemlocationMapRepositoryExtended extends ItemItemlocationMapRepository {
    List<ItemItemlocationMap> findByItemLocationIdAndItemIdInAndStatusIgnoreCase(Long itemLocationId, List<Long> itemIdList, String active);

    List<ItemItemlocationMap> findByItemIdAndStatusIgnoreCase(Long itemId,String status);

    List<ItemItemlocationMap> findByItemLocationIdAndStatusIgnoreCase(Long itemLocationId,String status);

    List<ItemItemlocationMap> findByItemNameLikeAndStatusIgnoreCase(String s,String status);

    List<ItemItemlocationMap> findByItemLocationNameLikeAndStatusIgnoreCase(String s,String status);

    List<ItemItemlocationMap> findByStatusIgnoreCase(String status);

    List<ItemItemlocationMap> findByItemLocationNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<ItemItemlocationMap> findByItemNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);
}
