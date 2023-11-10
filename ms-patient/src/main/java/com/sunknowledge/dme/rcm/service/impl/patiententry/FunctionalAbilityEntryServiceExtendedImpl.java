package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.FunctionalAbilityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveFunctionalAbilityCommand;
import com.sunknowledge.dme.rcm.service.patiententry.FunctionalAbilityEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.FunctionalAbilityEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("functionalAbilityEntryServiceExtended")
public class FunctionalAbilityEntryServiceExtendedImpl implements FunctionalAbilityEntryServiceExtended {
    @Autowired
    FunctionalAbilityEntryAggregate functionalAbilityEntryAggregate;

    @Override
    public Mono<ResponseDTO> saveFunctionalAbility(SaveFunctionalAbilityCommand obj) {

        return functionalAbilityEntryAggregate.handleSaveFunctionalAbilityCommand(obj);
    }
}
