package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.repository.ItemGroupRepository;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;

import java.util.List;
import java.util.Optional;

public interface ItemGroupRepositoryExtended extends ItemGroupRepository {

    Optional<List<ItemGroup>> findByItemGroupNameLikeAndStatusIgnoreCase(String itemGroupName, String active);

    Optional<List<ItemGroup>> findByItemGroupNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);
}
