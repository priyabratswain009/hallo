package com.sunknowledge.dme.rcm.service.impl.patiententry.aggregate;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.WorkerCompensationEntryRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveWorkerCompensationCommand;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationMapper;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.WorkerCompensationEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class WorkerCompensationEntryAggregateImpl implements WorkerCompensationEntryAggregate {
    @Autowired
    WorkerCompensationEntryRepositoryExtended workerCompensationEntryRepositoryExtended;

    private final WorkersCompensationMapper workersCompensationMapper;

    public WorkerCompensationEntryAggregateImpl(WorkersCompensationMapper workersCompensationMapper) {
        this.workersCompensationMapper = workersCompensationMapper;
    }

    @Override
    public Mono<ResponseDTO> handleSaveWorkerCompensationCommand(SaveWorkerCompensationCommand saveWorkerCompensationCommand) {
        WorkersCompensationDTO workersCompensationDTO = new WorkersCompensationDTO();
        try {
            if (saveWorkerCompensationCommand.getWorkersCompensationId() == null || saveWorkerCompensationCommand.getWorkersCompensationId() == 0) {
                CommonUtilities.dtoTrimmer(saveWorkerCompensationCommand);
                BeanUtils.copyProperties(saveWorkerCompensationCommand, workersCompensationDTO, "createdDate", "updatedDate");
                workersCompensationDTO.setCreatedDate(LocalDate.now());
                workersCompensationDTO.setCreatedById(1L);
                workersCompensationDTO.setCreatedByName("Abhijit");
                workersCompensationDTO.setWorkersCompensationId(null);
                workersCompensationDTO.setStatus("active");
                workersCompensationDTO.setWorkersCompensationUuid(UUID.randomUUID());
                return workerCompensationEntryRepositoryExtended
                    .save(workersCompensationMapper.toEntity(workersCompensationDTO))
                    .map(workersCompensationMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", i));
            } else {
                return workerCompensationEntryRepositoryExtended
                    .findById(saveWorkerCompensationCommand.getWorkersCompensationId())
                    .map(existingObj -> {
                        existingObj.setUpdatedDate(LocalDate.now());
                        existingObj.setUpdatedById(1L);
                        existingObj.setUpdatedByName("Abhijit");
                        existingObj.setStatus("inactive");
                        return existingObj;
                    })
                    .flatMap(workerCompensationEntryRepositoryExtended::save)
                    .map(savedObj -> {
                        CommonUtilities.dtoTrimmer(saveWorkerCompensationCommand);
                        BeanUtils.copyProperties(saveWorkerCompensationCommand, savedObj, "createdDate", "updatedDate");
                        savedObj.setCreatedDate(LocalDate.now());
                        savedObj.setCreatedById(1L);
                        savedObj.setCreatedByName("Abhijit");
                        savedObj.setStatus("active");
                        savedObj.setWorkersCompensationId(null);
                        savedObj.setWorkersCompensationUuid(UUID.randomUUID());
                        return savedObj;
                    }).flatMap(workerCompensationEntryRepositoryExtended::save)
                    .map(updatedObj -> new ResponseDTO(true, "Successfully Saved", updatedObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(new ResponseDTO(false, "Data Error! Failed to Save.", null));
        }
    }

}
