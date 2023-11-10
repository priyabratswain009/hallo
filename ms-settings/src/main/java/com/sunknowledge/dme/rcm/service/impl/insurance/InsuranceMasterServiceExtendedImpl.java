package com.sunknowledge.dme.rcm.service.impl.insurance;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.insurance.InsuranceMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.insurance.InsuranceMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.InsuranceMasterRejectedDTO;
import com.sunknowledge.dme.rcm.service.helper.insurance.InsuranceMasterHelper;
import com.sunknowledge.dme.rcm.service.insurance.InsuranceMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.InsuranceMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class InsuranceMasterServiceExtendedImpl implements InsuranceMasterServiceExtended {
    private final Logger log = LoggerFactory.getLogger(InsuranceMasterServiceExtendedImpl.class);
    @Autowired
    InsuranceMasterHelper insuranceMasterHelper;
    @Autowired
    InsuranceMasterRepositoryExtended insuranceMasterRepositoryExtended;
    @Autowired
    InsuranceMasterMapper insuranceMasterMapper;

    @Override
    public InsuranceMasterDTO save(InsuranceMasterDTO insuranceMasterDTO) {
        return null;
    }

    @Override
    public InsuranceMasterDTO update(InsuranceMasterDTO insuranceMasterDTO) {
        return null;
    }

    @Override
    public Optional<InsuranceMasterDTO> partialUpdate(InsuranceMasterDTO insuranceMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<InsuranceMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<InsuranceMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public ResponseDTO bulkUploadForInsuranceMaster(MultipartFile documentFile) {
        Map<String, Object> insuranceMasterBothData;
        String message = "";
        try {
            insuranceMasterBothData = insuranceMasterHelper.csvToInsuranceMasterDTOMapper(documentFile.getInputStream());
            List<InsuranceMasterDTO> insuranceMasterDTOS = (List<InsuranceMasterDTO>) insuranceMasterBothData.get("insuranceMasterDTOS");

            insuranceMasterRepositoryExtended.saveAll(insuranceMasterMapper.toEntity(insuranceMasterDTOS));

            Integer successfullyUploaded = ((List<InsuranceMasterDTO>) insuranceMasterBothData.get("insuranceMasterDTOS")).size();
            Integer skipped = ((Map<String, InsuranceMasterRejectedDTO>) insuranceMasterBothData.get("SkippedInsuranceMasterDTO")).size();
            message = "Successfully Uploaded (" + successfullyUploaded + ")";
            if (skipped > 0) {
                message += " and Skipped " + skipped + " Rows";
            }
            return (new ResponseDTO(Boolean.TRUE, message, List.of(insuranceMasterBothData.get("SkippedInsuranceMasterDTO"))));
        } catch (IOException e) {
            log.error("=====>> Error : " + e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }

    }

    @Override
    public ResponseDTO saveInsuranceMaster(InsuranceMasterParameterDTO insuranceMasterParameterDTO) {
        Set uniqueInsuranceIdNoSet = insuranceMasterRepositoryExtended.findAll().stream().map(x -> x.getInsuranceIdNo()).collect(Collectors.toSet());
        Set uniqueInsuranceIdSet = insuranceMasterRepositoryExtended.findAll().stream().map(x -> x.getInsuranceId()).collect(Collectors.toSet());

        if (insuranceMasterParameterDTO.getInsuranceName().trim() == "") {
            throw new InputMismatchException("Insurance_Name must be provided");
        }
        InsuranceMasterDTO insuranceMasterDTO = (insuranceMasterParameterDTO.getInsuranceId() == null ||
            insuranceMasterParameterDTO.getInsuranceId() == 0) ? new InsuranceMasterDTO() :
            (insuranceMasterRepositoryExtended.findById(insuranceMasterParameterDTO.getInsuranceId()).isPresent() ?
                insuranceMasterMapper.toDto(insuranceMasterRepositoryExtended.findById(insuranceMasterParameterDTO.getInsuranceId()).get()) :
                new InsuranceMasterDTO());


        BeanUtils.copyProperties(insuranceMasterParameterDTO, insuranceMasterDTO);
        if (insuranceMasterDTO.getInsuranceId() == null || insuranceMasterDTO.getInsuranceId() == 0) {
            insuranceMasterDTO.setInsuranceId(null);  //procedureCodeRepositoryExtended.findNextId()
            insuranceMasterDTO.setCreatedDate(LocalDate.now());
            insuranceMasterDTO.setCreatedById(1L);
            insuranceMasterDTO.setCreatedByName("Abhijit");
            insuranceMasterDTO.setInsuranceMasterUuid(UUID.randomUUID());
            insuranceMasterDTO.setInsuranceIdNo(insuranceMasterRepositoryExtended.getInsuranceIdNumber());
        } else {
            insuranceMasterDTO.setUpdatedDate(LocalDate.now());
            insuranceMasterDTO.setUpdatedById(1L);
            insuranceMasterDTO.setUpdatedByName("Abhijit");
        }
        InsuranceMasterDTO savedInsuranceMasterDTO = insuranceMasterMapper.toDto(
            insuranceMasterRepositoryExtended.save(insuranceMasterMapper.toEntity(insuranceMasterDTO))
        );

        return new ResponseDTO(true, "Successfully Saved.", List.of(savedInsuranceMasterDTO));
    }

    @Override
    public List<InsuranceMasterDTO> getInsuranceMasterById(Long insuranceId) {
        List<InsuranceMasterDTO> dtoDataList = new ArrayList<InsuranceMasterDTO>();
        InsuranceMaster data = insuranceMasterRepositoryExtended.findByInsuranceId(insuranceId);
        if (data != null) {
            dtoDataList.add(insuranceMasterMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<InsuranceMasterDTO> getInsuranceMasterByInsuranceName(String insuranceName) {
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByInsuranceNameLikeIgnoreCaseAndStatusIgnoreCase("%" + insuranceName + "%", "active");
        return insuranceMasterMapper.toDto(data);
    }

    @Override
    public List<InsuranceMasterDTO> getInsuranceMasterByInsuranceIdNo(String insuranceIdNo) {
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByInsuranceIdNoAndStatusIgnoreCase(insuranceIdNo, "active");
        return insuranceMasterMapper.toDto(data);
    }

    @Override
    public List<InsuranceMasterDTO> getAllInsuranceMasterData() {
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return insuranceMasterMapper.toDto(data);
    }

    @Override
    public Optional<InsuranceMasterDTO> findByInsuranceId(Long insuranceId) {
        Optional<InsuranceMaster> insuranceMaster = insuranceMasterRepositoryExtended.findById(insuranceId);
        return Optional.ofNullable(insuranceMasterMapper.toDto(insuranceMaster.get()));
    }

    @Override
    public ResponseDTO setInsuranceMasterStatus(Long insuranceId, String status) {
        if (status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try {
                InsuranceMaster obj = insuranceMasterRepositoryExtended.findByInsuranceId(insuranceId);
                obj.setStatus(status);
                insuranceMasterRepositoryExtended.save(obj);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(obj)));
            } catch (Exception e) {
                log.error("=====>> Error : " + e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error", new ArrayList<>()));
            }
        } else {
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }

    @Override
    public List<InsuranceMasterDTO> getInsuranceMasterByStatus(String status) {
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByStatusIgnoreCase(status);
        return insuranceMasterMapper.toDto(data);
    }

    @Override
    public List<InsuranceMasterDTO> getInsuranceMasterByInsuranceIdList(String insuranceIds) {
        List<String> listInt = Arrays.asList(insuranceIds.split(","));
        List<Long> listIns = listInt.stream().map(s -> Long.parseLong(s))
            .collect(Collectors.toList());
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByInsuranceIdIn(listIns);
        return insuranceMasterMapper.toDto(data);
    }

    @Override
    public ServiceOutcome getInsuranceByInsuranceNameForDropdown() {
        List<InsuranceMasterDTO> dtoList = getAllInsuranceMasterData();
        List<Map<String, Object>> list;
        if (dtoList != null && !dtoList.isEmpty()) {
            list = dtoList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getInsuranceId());
                map.put("title", p.getInsuranceName());
                return map;
            }).collect(Collectors.toList());
        } else list = new ArrayList<>();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found."));

    }

    @Override
    public List<InsuranceMasterDTO> fetchInsuranceByCmsCrossoverInsuranceIdNo(String cmsCrossoverInsuranceIdNo) {
        List<InsuranceMaster> data = insuranceMasterRepositoryExtended.findByCmsCrossoverInsuranceIdNoAndStatusIgnoreCase(cmsCrossoverInsuranceIdNo, "active");
        return insuranceMasterMapper.toDto(data);
    }
}