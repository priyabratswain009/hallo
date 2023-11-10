package com.sunknowledge.dme.rcm.service.impl.itemothers;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;
import com.sunknowledge.dme.rcm.repository.itemothers.SoItemTransactionDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.itemothers.SoItemTransactionDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.SoItemTransactionDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Primary
@Service
public class SoItemTransactionDetailsServiceExtendedImpl implements SoItemTransactionDetailsServiceExtended {

    @Autowired
    SoItemTransactionDetailsRepositoryExtended soItemTransactionDetailsRepositoryExtended;
    @Autowired
    SoItemTransactionDetailsMapper soItemTransactionDetailsMapper;
    @Override
    public SoItemTransactionDetailsDTO save(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        return null;
    }

    @Override
    public SoItemTransactionDetailsDTO update(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        return null;
    }

    @Override
    public Optional<SoItemTransactionDetailsDTO> partialUpdate(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO) {
        return Optional.empty();
    }

    @Override
    public Page<SoItemTransactionDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<SoItemTransactionDetailsDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SoItemTransactionDetailsDTO getSOItemTransactionDetailsBysONoAndItemId(String soNo, int itemId) {
        Optional<SoItemTransactionDetails> soItemTransactionDetails = soItemTransactionDetailsRepositoryExtended.findBySalesOrderNoAndItemIdAndStatusIgnoreCase(soNo,(long) itemId,"active");
        return soItemTransactionDetails.isPresent()?soItemTransactionDetailsMapper.toDto(soItemTransactionDetails.get()):null;
    }

    @Override
    public List<SoItemTransactionDetailsDTO> saveAll(List<SoItemTransactionDetailsDTO> soItemTransactionDetailsDTOS) {
        return soItemTransactionDetailsMapper.toDto(soItemTransactionDetailsRepositoryExtended
            .saveAll(soItemTransactionDetailsMapper.toEntity(soItemTransactionDetailsDTOS)));
    }
}
