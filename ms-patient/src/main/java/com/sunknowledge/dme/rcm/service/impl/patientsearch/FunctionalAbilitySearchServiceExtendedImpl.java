package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName;
import com.sunknowledge.dme.rcm.service.patientsearch.FunctionalAbilitySearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.FunctionalAbilitySearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service("functionalAbilitySearchServiceExtended")
public class FunctionalAbilitySearchServiceExtendedImpl implements FunctionalAbilitySearchServiceExtended {
    private final Logger log = LoggerFactory.getLogger(FunctionalAbilitySearchServiceExtendedImpl.class);

    @Autowired
    FunctionalAbilitySearchQueryHandler functionalAbilitySearchQueryHandler;

    @Override
    public Flux<FunctionalAbilityDTO> getFunctionalAbilityBySearchParameters(FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName obj) {
        return functionalAbilitySearchQueryHandler.getFunctionalAbilityBySearchParameters(obj);
    }

    @Override
    public Long getIDByUUID(UUID functionalAbilityUUID) {
        Long id = functionalAbilitySearchQueryHandler.getIDByUUID(functionalAbilityUUID);
        return id;
    }

    @Override
    public Flux<FunctionalAbilityDTO> getAllFunctionalAbility() {
        return functionalAbilitySearchQueryHandler.getAllFunctionalAbility();
    }

    @Override
    public Mono<Object> getAllFunctionalAbilityByUUID(Long functionalAbilityId) {
        return functionalAbilitySearchQueryHandler.getAllFunctionalAbilityByUUID(functionalAbilityId);
    }

    @Override
    public List<Map<String, Object>> getAllFunctionalAbilityForDropdown() throws ExecutionException, InterruptedException {
        List<FunctionalAbilityDTO> dtoList = getAllFunctionalAbility().collectList().toFuture().get();
        if (dtoList != null && !dtoList.isEmpty()) {
            return dtoList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getFunctionalAbilityId());
                map.put("title", p.getFunctionalAbilityName());
                return map;
            }).collect(Collectors.toList());
        } else return new ArrayList<>();
    }
}
