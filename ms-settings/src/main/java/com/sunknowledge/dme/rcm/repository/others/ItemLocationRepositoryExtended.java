package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import com.sunknowledge.dme.rcm.repository.ItemLocationRepository;

import java.util.List;
import java.util.UUID;

public interface ItemLocationRepositoryExtended extends ItemLocationRepository {
    List<ItemLocation> findByStatusIgnoreCase(String active);

    List<ItemLocation> findByDescriptionLikeAndStatusIgnoreCase(String s, String active);

    ItemLocation findByItemLocationNameAndStatusIgnoreCase(String itemLocationName, String active);

    ItemLocation findByItemLocationIdAndStatusIgnoreCase(Long itemLocationId, String active);

    ItemLocation findByItemLocationId(Long itemLocationId);

    List<ItemLocation> findByDescriptionLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    ItemLocation findByItemLocationNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    ItemLocation findByItemLocationUuid(UUID itemLocationUuid);
}
