package com.sunknowledge.dme.rcm.service.impl.items;

import com.netflix.discovery.converters.Auto;
import com.sunknowledge.dme.rcm.repository.items.ItemTypeRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import com.sunknowledge.dme.rcm.service.items.ItemTypeServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ItemTypeServiceExtendedImpl implements ItemTypeServiceExtended {
    @Override
    public ItemTypeDTO save(ItemTypeDTO itemTypeDTO) {
        return null;
    }

    @Override
    public ItemTypeDTO update(ItemTypeDTO itemTypeDTO) {
        return null;
    }

    @Override
    public Optional<ItemTypeDTO> partialUpdate(ItemTypeDTO itemTypeDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemTypeDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemTypeDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemTypeRepositoryExtended itemTypeRepositoryExtended;
    @Autowired
    ItemTypeMapper itemTypeMapper;
    @Override
    public List<ItemTypeDTO> getItemTypeByItemTypeId(Long itemTypeId) {
        return itemTypeMapper.toDto(itemTypeRepositoryExtended.findByItemTypeId(itemTypeId));
    }
}
