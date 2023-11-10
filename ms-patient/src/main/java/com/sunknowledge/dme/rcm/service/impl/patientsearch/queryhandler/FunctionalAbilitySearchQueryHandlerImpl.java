package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.FunctionalAbilitySearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName;
import com.sunknowledge.dme.rcm.service.impl.patientsearch.FunctionalAbilitySearchServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.FunctionalAbilitySearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class FunctionalAbilitySearchQueryHandlerImpl implements FunctionalAbilitySearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(FunctionalAbilitySearchServiceExtendedImpl.class);

    @Autowired
    FunctionalAbilitySearchRepositoryExtended functionalAbilitySearchRepositoryExtended;

    private final FunctionalAbilityMapper functionalAbilityMapper;

    public FunctionalAbilitySearchQueryHandlerImpl(FunctionalAbilityMapper functionalAbilityMapper) {
        this.functionalAbilityMapper = functionalAbilityMapper;
    }

    @Override
    public Flux<FunctionalAbilityDTO> getFunctionalAbilityBySearchParameters(FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName obj) {
        try {
            if ((obj.getFunctionalAbilityCode() == null ||
                obj.getFunctionalAbilityCode().trim().equalsIgnoreCase("")) &&
                (obj.getFunctionalAbilityId() == null ||
                    obj.getFunctionalAbilityId() == 0) &&
                (obj.getFunctionalAbilityName() == null ||
                    obj.getFunctionalAbilityName().trim().equalsIgnoreCase(""))
            ) {
                return functionalAbilitySearchRepositoryExtended.findAll()
                    .map(functionalAbilityMapper::toDto)
                    .filter(x -> x.getStatus().trim().equalsIgnoreCase("active"));
            } else {
                return functionalAbilitySearchRepositoryExtended.findAll()
                    .map(functionalAbilityMapper::toDto)
                    .filter(x ->
                        (
                            (((obj.getFunctionalAbilityCode() == null || obj.getFunctionalAbilityCode().trim().equalsIgnoreCase(""))
                                && x.getFunctionalAbilityId().equals(obj.getFunctionalAbilityId())) ||
                                ((obj.getFunctionalAbilityId() == null ||
                                    obj.getFunctionalAbilityId() == 0) &&
                                    obj.getFunctionalAbilityCode().trim().equalsIgnoreCase(x.getFunctionalAbilityCode())) ||
                                ((obj.getFunctionalAbilityId() == null ||
                                    obj.getFunctionalAbilityId() == 0) &&
                                    (obj.getFunctionalAbilityCode() == null ||
                                        obj.getFunctionalAbilityCode().trim().equalsIgnoreCase("")) &&
                                    x.getFunctionalAbilityName().trim().contains(obj.getFunctionalAbilityName()))) &&
                                x.getStatus().trim().equalsIgnoreCase("active")
                        ));
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getIDByUUID(UUID functionalAbilityUUID)
    {
        try {
            return functionalAbilitySearchRepositoryExtended.getIDByUUID(functionalAbilityUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<FunctionalAbilityDTO> getAllFunctionalAbility(){
        try {
            return functionalAbilitySearchRepositoryExtended.findAll()
                .map(functionalAbilityMapper::toDto)
                .filter(x ->
                    x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getAllFunctionalAbilityByUUID(Long functionalAbilityId){
        try {
            return functionalAbilitySearchRepositoryExtended.findById(functionalAbilityId)
                .map(functionalAbilityMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
