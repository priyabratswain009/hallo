package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.MemberElligibility;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityRepository;
import com.sunknowledge.dme.rcm.service.MemberElligibilityService;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityDTO;
import com.sunknowledge.dme.rcm.service.mapper.MemberElligibilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link MemberElligibility}.
 */
@Service
@Transactional
public class MemberElligibilityServiceImpl implements MemberElligibilityService {

    private final Logger log = LoggerFactory.getLogger(MemberElligibilityServiceImpl.class);

    private final MemberElligibilityRepository memberElligibilityRepository;

    private final MemberElligibilityMapper memberElligibilityMapper;

    public MemberElligibilityServiceImpl(
        MemberElligibilityRepository memberElligibilityRepository,
        MemberElligibilityMapper memberElligibilityMapper
    ) {
        this.memberElligibilityRepository = memberElligibilityRepository;
        this.memberElligibilityMapper = memberElligibilityMapper;
    }

    @Override
    public Mono<MemberElligibilityDTO> save(MemberElligibilityDTO memberElligibilityDTO) {
        log.debug("Request to save MemberElligibility : {}", memberElligibilityDTO);
        return memberElligibilityRepository
            .save(memberElligibilityMapper.toEntity(memberElligibilityDTO))
            .map(memberElligibilityMapper::toDto);
    }

    @Override
    public Mono<MemberElligibilityDTO> update(MemberElligibilityDTO memberElligibilityDTO) {
        log.debug("Request to update MemberElligibility : {}", memberElligibilityDTO);
        return memberElligibilityRepository
            .save(memberElligibilityMapper.toEntity(memberElligibilityDTO))
            .map(memberElligibilityMapper::toDto);
    }

    @Override
    public Mono<MemberElligibilityDTO> partialUpdate(MemberElligibilityDTO memberElligibilityDTO) {
        log.debug("Request to partially update MemberElligibility : {}", memberElligibilityDTO);

        return memberElligibilityRepository
            .findById(memberElligibilityDTO.getClaimElligibilityMasterId())
            .map(existingMemberElligibility -> {
                memberElligibilityMapper.partialUpdate(existingMemberElligibility, memberElligibilityDTO);

                return existingMemberElligibility;
            })
            .flatMap(memberElligibilityRepository::save)
            .map(memberElligibilityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<MemberElligibilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MemberElligibilities");
        return memberElligibilityRepository.findAllBy(pageable).map(memberElligibilityMapper::toDto);
    }

    public Mono<Long> countAll() {
        return memberElligibilityRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<MemberElligibilityDTO> findOne(Long id) {
        log.debug("Request to get MemberElligibility : {}", id);
        return memberElligibilityRepository.findById(id).map(memberElligibilityMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete MemberElligibility : {}", id);
        return memberElligibilityRepository.deleteById(id);
    }
}
