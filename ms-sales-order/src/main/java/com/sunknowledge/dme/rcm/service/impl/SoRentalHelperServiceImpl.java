package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.SoRentalHelper;
import com.sunknowledge.dme.rcm.repository.SoRentalHelperRepository;
import com.sunknowledge.dme.rcm.service.SoRentalHelperService;
import com.sunknowledge.dme.rcm.service.dto.SoRentalHelperDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoRentalHelperMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link SoRentalHelper}.
 */
@Service
@Transactional
public class SoRentalHelperServiceImpl implements SoRentalHelperService {

    private final Logger log = LoggerFactory.getLogger(SoRentalHelperServiceImpl.class);

    private final SoRentalHelperRepository soRentalHelperRepository;

    private final SoRentalHelperMapper soRentalHelperMapper;

    public SoRentalHelperServiceImpl(SoRentalHelperRepository soRentalHelperRepository, SoRentalHelperMapper soRentalHelperMapper) {
        this.soRentalHelperRepository = soRentalHelperRepository;
        this.soRentalHelperMapper = soRentalHelperMapper;
    }

    @Override
    public Mono<SoRentalHelperDTO> save(SoRentalHelperDTO soRentalHelperDTO) {
        log.debug("Request to save SoRentalHelper : {}", soRentalHelperDTO);
        return soRentalHelperRepository.save(soRentalHelperMapper.toEntity(soRentalHelperDTO)).map(soRentalHelperMapper::toDto);
    }

    @Override
    public Mono<SoRentalHelperDTO> update(SoRentalHelperDTO soRentalHelperDTO) {
        log.debug("Request to save SoRentalHelper : {}", soRentalHelperDTO);
        return soRentalHelperRepository.save(soRentalHelperMapper.toEntity(soRentalHelperDTO)).map(soRentalHelperMapper::toDto);
    }

    @Override
    public Mono<SoRentalHelperDTO> partialUpdate(SoRentalHelperDTO soRentalHelperDTO) {
        log.debug("Request to partially update SoRentalHelper : {}", soRentalHelperDTO);

        return soRentalHelperRepository
            .findById(soRentalHelperDTO.getSoRentalHelperId())
            .map(existingSoRentalHelper -> {
                soRentalHelperMapper.partialUpdate(existingSoRentalHelper, soRentalHelperDTO);

                return existingSoRentalHelper;
            })
            .flatMap(soRentalHelperRepository::save)
            .map(soRentalHelperMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SoRentalHelperDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoRentalHelpers");
        return soRentalHelperRepository.findAllBy(pageable).map(soRentalHelperMapper::toDto);
    }

    public Mono<Long> countAll() {
        return soRentalHelperRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SoRentalHelperDTO> findOne(Long id) {
        log.debug("Request to get SoRentalHelper : {}", id);
        return soRentalHelperRepository.findById(id).map(soRentalHelperMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SoRentalHelper : {}", id);
        return soRentalHelperRepository.deleteById(id);
    }
}
