package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import com.sunknowledge.dme.rcm.repository.ItemAssetNumberMapRepository;

import java.util.List;
import java.util.UUID;

public interface ItemAssetNumberMapRepositoryExtended extends ItemAssetNumberMapRepository {

    List<ItemAssetNumberMap> findByStatusIgnoreCase(String active);
    ItemAssetNumberMap findByItemAssetNumberUuid(UUID insuranceGroupMasterUuid);
    List<ItemAssetNumberMap> findByItemIdAndAssetNumberAndStatusIgnoreCase(Long itemId, String assetNumber, String status);
    List<ItemAssetNumberMap> findByItemIdAndStatusIgnoreCase(Long itemId, String status);
}
