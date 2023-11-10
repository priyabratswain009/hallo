package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;
import com.sunknowledge.dme.rcm.repository.MemberElligibilityMasterRepository;
import com.sunknowledge.dme.rcm.service.MemberElligibilityMasterService;
import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.MemberElligibilityMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link MemberElligibilityMaster}.
 */
@Service
@Transactional
public class MemberElligibilityMasterServiceImpl implements MemberElligibilityMasterService {

    private final Logger log = LoggerFactory.getLogger(MemberElligibilityMasterServiceImpl.class);

    private final MemberElligibilityMasterRepository memberElligibilityMasterRepository;

    private final MemberElligibilityMasterMapper memberElligibilityMasterMapper;

    public MemberElligibilityMasterServiceImpl(
        MemberElligibilityMasterRepository memberElligibilityMasterRepository,
        MemberElligibilityMasterMapper memberElligibilityMasterMapper
    ) {
        this.memberElligibilityMasterRepository = memberElligibilityMasterRepository;
        this.memberElligibilityMasterMapper = memberElligibilityMasterMapper;
    }

    @Override
    public Mono<MemberElligibilityMasterDTO> save(MemberElligibilityMasterDTO memberElligibilityMasterDTO) {
        log.debug("Request to save MemberElligibilityMaster : {}", memberElligibilityMasterDTO);
        return memberElligibilityMasterRepository
            .save(memberElligibilityMasterMapper.toEntity(memberElligibilityMasterDTO))
            .map(memberElligibilityMasterMapper::toDto);
    }

    @Override
    public Mono<MemberElligibilityMasterDTO> update(MemberElligibilityMasterDTO memberElligibilityMasterDTO) {
        log.debug("Request to update MemberElligibilityMaster : {}", memberElligibilityMasterDTO);
        return memberElligibilityMasterRepository
            .save(memberElligibilityMasterMapper.toEntity(memberElligibilityMasterDTO))
            .map(memberElligibilityMasterMapper::toDto);
    }

    @Override
    public Mono<MemberElligibilityMasterDTO> partialUpdate(MemberElligibilityMasterDTO memberElligibilityMasterDTO) {
        log.debug("Request to partially update MemberElligibilityMaster : {}", memberElligibilityMasterDTO);

        return memberElligibilityMasterRepository
            .findById(memberElligibilityMasterDTO.getClaimElligibilityMasterId())
            .map(existingMemberElligibilityMaster -> {
                memberElligibilityMasterMapper.partialUpdate(existingMemberElligibilityMaster, memberElligibilityMasterDTO);

                return existingMemberElligibilityMaster;
            })
            .flatMap(memberElligibilityMasterRepository::save)
            .map(memberElligibilityMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<MemberElligibilityMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MemberElligibilityMasters");
        return memberElligibilityMasterRepository.findAllBy(pageable).map(memberElligibilityMasterMapper::toDto);
    }

    public Mono<Long> countAll() {
        return memberElligibilityMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<MemberElligibilityMasterDTO> findOne(Long id) {
        log.debug("Request to get MemberElligibilityMaster : {}", id);
        return memberElligibilityMasterRepository.findById(id).map(memberElligibilityMasterMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete MemberElligibilityMaster : {}", id);
        return memberElligibilityMasterRepository.deleteById(id);
    }
}
