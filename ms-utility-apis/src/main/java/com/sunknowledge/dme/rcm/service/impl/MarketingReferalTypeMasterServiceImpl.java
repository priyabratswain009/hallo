package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import com.sunknowledge.dme.rcm.repository.MarketingReferalTypeMasterRepository;
import com.sunknowledge.dme.rcm.service.MarketingReferalTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.MarketingReferalTypeMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MarketingReferalTypeMaster}.
 */
@Service
@Transactional
public class MarketingReferalTypeMasterServiceImpl implements MarketingReferalTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(MarketingReferalTypeMasterServiceImpl.class);

    private final MarketingReferalTypeMasterRepository marketingReferalTypeMasterRepository;

    private final MarketingReferalTypeMasterMapper marketingReferalTypeMasterMapper;

    public MarketingReferalTypeMasterServiceImpl(
        MarketingReferalTypeMasterRepository marketingReferalTypeMasterRepository,
        MarketingReferalTypeMasterMapper marketingReferalTypeMasterMapper
    ) {
        this.marketingReferalTypeMasterRepository = marketingReferalTypeMasterRepository;
        this.marketingReferalTypeMasterMapper = marketingReferalTypeMasterMapper;
    }

    @Override
    public MarketingReferalTypeMasterDTO save(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        log.debug("Request to save MarketingReferalTypeMaster : {}", marketingReferalTypeMasterDTO);
        MarketingReferalTypeMaster marketingReferalTypeMaster = marketingReferalTypeMasterMapper.toEntity(marketingReferalTypeMasterDTO);
        marketingReferalTypeMaster = marketingReferalTypeMasterRepository.save(marketingReferalTypeMaster);
        return marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);
    }

    @Override
    public MarketingReferalTypeMasterDTO update(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        log.debug("Request to save MarketingReferalTypeMaster : {}", marketingReferalTypeMasterDTO);
        MarketingReferalTypeMaster marketingReferalTypeMaster = marketingReferalTypeMasterMapper.toEntity(marketingReferalTypeMasterDTO);
        marketingReferalTypeMaster = marketingReferalTypeMasterRepository.save(marketingReferalTypeMaster);
        return marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMaster);
    }

    @Override
    public Optional<MarketingReferalTypeMasterDTO> partialUpdate(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        log.debug("Request to partially update MarketingReferalTypeMaster : {}", marketingReferalTypeMasterDTO);

        return marketingReferalTypeMasterRepository
            .findById(marketingReferalTypeMasterDTO.getMarketingReferralTypeId())
            .map(existingMarketingReferalTypeMaster -> {
                marketingReferalTypeMasterMapper.partialUpdate(existingMarketingReferalTypeMaster, marketingReferalTypeMasterDTO);

                return existingMarketingReferalTypeMaster;
            })
            .map(marketingReferalTypeMasterRepository::save)
            .map(marketingReferalTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MarketingReferalTypeMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MarketingReferalTypeMasters");
        return marketingReferalTypeMasterRepository.findAll(pageable).map(marketingReferalTypeMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarketingReferalTypeMasterDTO> findOne(Long id) {
        log.debug("Request to get MarketingReferalTypeMaster : {}", id);
        return marketingReferalTypeMasterRepository.findById(id).map(marketingReferalTypeMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MarketingReferalTypeMaster : {}", id);
        marketingReferalTypeMasterRepository.deleteById(id);
    }
}
