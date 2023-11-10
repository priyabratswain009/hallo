package com.sunknowledge.dme.rcm.service.impl.price;

import com.sunknowledge.dme.rcm.repository.pricetable.PriceDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.PriceDetailsMapper;
import com.sunknowledge.dme.rcm.service.pricetable.PriceDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("priceDetailsServiceExtendedImpl")
public class PriceDetailsServiceExtendedImpl implements PriceDetailsServiceExtended {

    @Autowired
    PriceDetailsRepo priceDetailsRepositoryExtended;
    @Autowired
    PriceDetailsMapper priceDetailsMapper;

    @Override
    public PriceDetailsDTO save(PriceDetailsDTO priceDetailsDTO) {
        return null;
    }

    @Override
    public PriceDetailsDTO update(PriceDetailsDTO priceDetailsDTO) {
        return null;
    }

    @Override
    public Optional<PriceDetailsDTO> partialUpdate(PriceDetailsDTO priceDetailsDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PriceDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PriceDetailsDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PriceDetailsDTO> getPriceDetailsByItemId(Long itemId) {
        return priceDetailsMapper.toDto(priceDetailsRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }
}
