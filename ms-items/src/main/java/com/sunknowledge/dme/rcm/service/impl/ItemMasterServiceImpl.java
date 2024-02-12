package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.repository.ItemMasterRepository;
import com.sunknowledge.dme.rcm.service.ItemMasterService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemMaster}.
 */
@Service
@Transactional
public class ItemMasterServiceImpl implements ItemMasterService {

    private final Logger log = LoggerFactory.getLogger(ItemMasterServiceImpl.class);

    private final ItemMasterRepository itemMasterRepository;

    private final ItemMasterMapper itemMasterMapper;

    public ItemMasterServiceImpl(ItemMasterRepository itemMasterRepository, ItemMasterMapper itemMasterMapper) {
        this.itemMasterRepository = itemMasterRepository;
        this.itemMasterMapper = itemMasterMapper;
    }

    @Override
    public ItemMasterDTO save(ItemMasterDTO itemMasterDTO) {
        log.debug("Request to save ItemMaster : {}", itemMasterDTO);
        ItemMaster itemMaster = itemMasterMapper.toEntity(itemMasterDTO);
        itemMaster = itemMasterRepository.save(itemMaster);
        return itemMasterMapper.toDto(itemMaster);
    }

    @Override
    public ItemMasterDTO update(ItemMasterDTO itemMasterDTO) {
        log.debug("Request to save ItemMaster : {}", itemMasterDTO);
        ItemMaster itemMaster = itemMasterMapper.toEntity(itemMasterDTO);
        itemMaster = itemMasterRepository.save(itemMaster);
        return itemMasterMapper.toDto(itemMaster);
    }

    @Override
    public Optional<ItemMasterDTO> partialUpdate(ItemMasterDTO itemMasterDTO) {
        log.debug("Request to partially update ItemMaster : {}", itemMasterDTO);

        return itemMasterRepository
            .findById(itemMasterDTO.getItemId())
            .map(existingItemMaster -> {
                itemMasterMapper.partialUpdate(existingItemMaster, itemMasterDTO);

                return existingItemMaster;
            })
            .map(itemMasterRepository::save)
            .map(itemMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemMasters");
        return itemMasterRepository.findAll(pageable).map(itemMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemMasterDTO> findOne(Long id) {
        log.debug("Request to get ItemMaster : {}", id);
        return itemMasterRepository.findById(id).map(itemMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemMaster : {}", id);
        itemMasterRepository.deleteById(id);
    }
}
