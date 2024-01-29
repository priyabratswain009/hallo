package com.sunknowledge.dme.rcm.service.impl.par;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.repository.par.ParRequestDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import com.sunknowledge.dme.rcm.service.par.ParRequestDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Service
public class ParRequestDetailsServiceExtendedImpl implements ParRequestDetailsServiceExtended {

    @Autowired
    ParRequestDetailsRepo parRequestDetailsRepo;

    @Override
    public Mono<ParRequestDetailsDTO> save(ParRequestDetailsDTO parRequestDetailsDTO) {
        return null;
    }

    @Override
    public Mono<ParRequestDetailsDTO> update(ParRequestDetailsDTO parRequestDetailsDTO) {
        return null;
    }

    @Override
    public Mono<ParRequestDetailsDTO> partialUpdate(ParRequestDetailsDTO parRequestDetailsDTO) {
        return null;
    }

    @Override
    public Flux<ParRequestDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<ParRequestDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Mono<String> getParRequestDetailsByFaxNo(String faxNo) {
        return parRequestDetailsRepo.getParRequestDetailsByFaxNo(faxNo)
            .map(dataList -> dataList);
    }
}
