package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.StockTransfer;
import com.sunknowledge.dme.rcm.repository.StockTransferRepository;
import com.sunknowledge.dme.rcm.service.StockTransferService;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StockTransfer}.
 */
@Service
@Transactional
public class StockTransferServiceImpl implements StockTransferService {

    private final Logger log = LoggerFactory.getLogger(StockTransferServiceImpl.class);

    private final StockTransferRepository stockTransferRepository;

    private final StockTransferMapper stockTransferMapper;

    public StockTransferServiceImpl(StockTransferRepository stockTransferRepository, StockTransferMapper stockTransferMapper) {
        this.stockTransferRepository = stockTransferRepository;
        this.stockTransferMapper = stockTransferMapper;
    }

    @Override
    public StockTransferDTO save(StockTransferDTO stockTransferDTO) {
        log.debug("Request to save StockTransfer : {}", stockTransferDTO);
        StockTransfer stockTransfer = stockTransferMapper.toEntity(stockTransferDTO);
        stockTransfer = stockTransferRepository.save(stockTransfer);
        return stockTransferMapper.toDto(stockTransfer);
    }

    @Override
    public StockTransferDTO update(StockTransferDTO stockTransferDTO) {
        log.debug("Request to update StockTransfer : {}", stockTransferDTO);
        StockTransfer stockTransfer = stockTransferMapper.toEntity(stockTransferDTO);
        stockTransfer = stockTransferRepository.save(stockTransfer);
        return stockTransferMapper.toDto(stockTransfer);
    }

    @Override
    public Optional<StockTransferDTO> partialUpdate(StockTransferDTO stockTransferDTO) {
        log.debug("Request to partially update StockTransfer : {}", stockTransferDTO);

        return stockTransferRepository
            .findById(stockTransferDTO.getStockTransferId())
            .map(existingStockTransfer -> {
                stockTransferMapper.partialUpdate(existingStockTransfer, stockTransferDTO);

                return existingStockTransfer;
            })
            .map(stockTransferRepository::save)
            .map(stockTransferMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockTransferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockTransfers");
        return stockTransferRepository.findAll(pageable).map(stockTransferMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockTransferDTO> findOne(Long id) {
        log.debug("Request to get StockTransfer : {}", id);
        return stockTransferRepository.findById(id).map(stockTransferMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockTransfer : {}", id);
        stockTransferRepository.deleteById(id);
    }
}
