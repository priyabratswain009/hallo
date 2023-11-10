package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemType;
import com.sunknowledge.dme.rcm.repository.ItemTypeRepository;

import java.util.List;

public interface ItemTypeRepositoryExtended extends ItemTypeRepository {
    List<ItemType> findByItemTypeId(Long itemTypeId);
}
