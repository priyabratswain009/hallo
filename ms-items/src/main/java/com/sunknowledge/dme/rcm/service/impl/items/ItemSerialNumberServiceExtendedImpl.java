package com.sunknowledge.dme.rcm.service.impl.items;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.repository.items.ItemSerialNumberRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.items.ItemSerialNumberServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemSerialNumberMapper;

@Primary
@Service
public class ItemSerialNumberServiceExtendedImpl implements ItemSerialNumberServiceExtended {

    @Autowired
    ItemSerialNumberRepositoryExtended itemSerialNumberRepositoryExtended;
    @Autowired
    ItemSerialNumberMapper itemSerialNumberMapper;

    @Override
    public ItemSerialNumberDTO save(ItemSerialNumberDTO itemSerialNumberDTO) {
        return null;
    }

    @Override
    public ItemSerialNumberDTO update(ItemSerialNumberDTO itemSerialNumberDTO) {
        return null;
    }

    @Override
    public Optional<ItemSerialNumberDTO> partialUpdate(ItemSerialNumberDTO itemSerialNumberDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemSerialNumberDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemSerialNumberDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ItemSerialNumberDTO> getItemSerialNumberByItemId(Long itemId) {
        return itemSerialNumberMapper.toDto(itemSerialNumberRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }

	@Override
	public ItemSerialNumberDTO getItemSerialNumberByserialNo(String serialNumber, Long itemId) {
		// TODO Auto-generated method stub
		return itemSerialNumberMapper.toDto(itemSerialNumberRepositoryExtended.getItemSerialNumberByserialNo(serialNumber, itemId));
	}
}
