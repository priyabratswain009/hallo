package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.FunctionalAbilityEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveFunctionalAbilityCommand;
import com.sunknowledge.dme.rcm.service.mapper.FunctionalAbilityMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.FunctionalAbilityEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionalAbilityAggregateImpl implements FunctionalAbilityEntryAggregate {
    private final FunctionalAbilityMapper functionalAbilityMapper;
    @Autowired
    FunctionalAbilityEntryRepositoryExtended functionalAbilityEntryRepositoryExtended;

    public FunctionalAbilityAggregateImpl(FunctionalAbilityMapper functionalAbilityMapper) {
        this.functionalAbilityMapper = functionalAbilityMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSaveFunctionalAbilityCommand(SaveFunctionalAbilityCommand saveFunctionalAbilityCommand) {
        FunctionalAbilityDTO functionalAbilityDTO = new FunctionalAbilityDTO();
        List<Object> responseList = new ArrayList<>();
        try {
            if (saveFunctionalAbilityCommand.getFunctionalAbilityId() == null || saveFunctionalAbilityCommand.getFunctionalAbilityId() == 0) {
                CommonUtilities.dtoTrimmer(saveFunctionalAbilityCommand);
                BeanUtils.copyProperties(saveFunctionalAbilityCommand, functionalAbilityDTO, "createdDate", "updatedDate");
                functionalAbilityDTO.setCreatedDate(LocalDate.now());
                functionalAbilityDTO.setCreatedById(1L);
                functionalAbilityDTO.setCreatedByName("Test");
                functionalAbilityDTO.setFunctionalAbilityId(null);
                return functionalAbilityEntryRepositoryExtended
                    .save(functionalAbilityMapper.toEntity(functionalAbilityDTO))
                    .map(functionalAbilityMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return functionalAbilityEntryRepositoryExtended.findById(saveFunctionalAbilityCommand.getFunctionalAbilityId())
                    .map(existingFunctionalAbility -> {
                        CommonUtilities.dtoTrimmer(saveFunctionalAbilityCommand);
                        BeanUtils.copyProperties(saveFunctionalAbilityCommand, existingFunctionalAbility, "createdDate", "createdById", "createdByName");
                        existingFunctionalAbility.setUpdatedDate(LocalDate.now());
                        existingFunctionalAbility.setUpdatedByName("Abhijit");
                        existingFunctionalAbility.setUpdatedById(1L);
                        existingFunctionalAbility.setUpdatedDate(LocalDate.now());

                        return existingFunctionalAbility;
                    })
                    .flatMap(functionalAbilityEntryRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", responseList));
        }
    }

}
