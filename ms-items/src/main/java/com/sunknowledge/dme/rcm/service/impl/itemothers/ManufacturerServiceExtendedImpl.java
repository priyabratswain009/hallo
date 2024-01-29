package com.sunknowledge.dme.rcm.service.impl.itemothers;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.Manufacturer;
import com.sunknowledge.dme.rcm.repository.itemothers.ManufacturerRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerParameterDTO;
import com.sunknowledge.dme.rcm.service.helper.itemothers.ManufacturerServiceImplHelper;
import com.sunknowledge.dme.rcm.service.itemothers.ManufacturerServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.ManufacturerRejectedDTO;
import com.sunknowledge.dme.rcm.service.mapper.ManufacturerMapper;
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

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class ManufacturerServiceExtendedImpl implements ManufacturerServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ManufacturerServiceExtendedImpl.class);

    @Autowired
    ManufacturerRepositoryExtended manufacturerRepositoryExtended;

    @Autowired
    ManufacturerMapper manufacturerMapper;

    @Autowired
    ManufacturerServiceImplHelper manufacturerServiceImplHelper;

    @Override
    public ManufacturerDTO save(ManufacturerDTO manufacturerDTO) {
        return null;
    }

    @Override
    public ManufacturerDTO update(ManufacturerDTO manufacturerDTO) {
        return null;
    }

    @Override
    public Optional<ManufacturerDTO> partialUpdate(ManufacturerDTO manufacturerDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ManufacturerDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ManufacturerDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public ResponseDTO saveManufacturer(ManufacturerParameterDTO manufacturerParameterDTO) {
        Set uniqueManufacturerNoSet = manufacturerRepositoryExtended.findAll().stream().map(x -> x.getManufacturerNo()).collect(Collectors.toSet());
        Set uniqueManufacturerIdSet = manufacturerRepositoryExtended.findAll().stream().map(x -> x.getManufacturerId()).collect(Collectors.toSet());

        try {
            if (manufacturerParameterDTO.getManufacturerName() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (manufacturer_name)");
            } else if (manufacturerParameterDTO.getManufacturerName().trim() == "") {
                throw new InputMismatchException("Manufacturer_name must be provided");
            }

            ManufacturerDTO manufacturerDTO = (manufacturerParameterDTO.getManufacturerId() == null ||
                manufacturerParameterDTO.getManufacturerId() == 0) ? new ManufacturerDTO() :
                (manufacturerRepositoryExtended.findById(manufacturerParameterDTO.getManufacturerId()).isPresent() ?
                    manufacturerMapper.toDto(manufacturerRepositoryExtended.findById(manufacturerParameterDTO.getManufacturerId()).get()) :
                    new ManufacturerDTO());

            BeanUtils.copyProperties(manufacturerParameterDTO, manufacturerDTO);
            if (manufacturerDTO.getManufacturerId() == null || manufacturerDTO.getManufacturerId() == 0) {
                manufacturerDTO.setManufacturerId(null);  //procedureCodeRepositoryExtended.findNextId()
                manufacturerDTO.setCreatedDate(LocalDate.now());
                manufacturerDTO.setCreatedById(1L);
                manufacturerDTO.setCreatedByName("Abhijit");
                manufacturerDTO.setManufacturerUuid(UUID.randomUUID());
                manufacturerDTO.setManufacturerNo(manufacturerRepositoryExtended.getManufacturerNumber());
            } else {
                manufacturerDTO.setUpdatedDate(LocalDate.now());
                manufacturerDTO.setUpdatedById(1L);
                manufacturerDTO.setUpdatedByName("Abhijit");
            }
            CommonUtilities.dtoTrimmer(manufacturerDTO);
            ManufacturerDTO savedManufacturerDTO = manufacturerMapper.toDto(
                manufacturerRepositoryExtended.save(manufacturerMapper.toEntity(manufacturerDTO))
            );
            return new ResponseDTO(true, "Successfully Saved.", List.of(savedManufacturerDTO), 200);
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO bulkUploadForManufacturer(MultipartFile documentFile) {
        Map<String,Object> manufacturerBothData;
        String message = "";
        try {
            manufacturerBothData = manufacturerServiceImplHelper.csvToManufacturerDTOMapper(documentFile.getInputStream());
            List<ManufacturerDTO> manufacturerDTOS = (List<ManufacturerDTO>) manufacturerBothData.get("manufacturerDTOS");
            manufacturerRepositoryExtended.saveAll(manufacturerMapper.toEntity(manufacturerDTOS));
            Integer successfullyUploaded = ((List<ManufacturerDTO>) manufacturerBothData.get("manufacturerDTOS")).size();
            Integer skipped = ((Map<String, ManufacturerRejectedDTO>) manufacturerBothData.get("SkippedManufacturerDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }
            return new ResponseDTO(true, message, List.of(manufacturerBothData.get("SkippedManufacturerDTO")), 200);
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public List<ManufacturerDTO> getManufacturerById(Long manufactureId) {
        List<ManufacturerDTO> dtoDataList = new ArrayList<ManufacturerDTO>();
        Manufacturer data = manufacturerRepositoryExtended.findByManufacturerId(manufactureId);
        if(data != null){
            dtoDataList.add(manufacturerMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<ManufacturerDTO> getManufacturerByManufacturerName(String manufactureName) {

        List<Manufacturer> data = manufacturerRepositoryExtended.findByManufacturerNameLikeIgnoreCaseAndStatusIgnoreCase("%"+manufactureName+"%","active");
        List<ManufacturerDTO> dtoDataList =manufacturerMapper.toDto(data);

        return dtoDataList;
    }

    @Override
    public List<ManufacturerDTO> getManufacturerByManufacturerNo(String manufacturerNo) {
        List<ManufacturerDTO> dtoDataList = new ArrayList<ManufacturerDTO>();
        Manufacturer data = manufacturerRepositoryExtended.findByManufacturerNoAndStatusIgnoreCase(manufacturerNo,"active");
        if(data != null){
            dtoDataList.add(manufacturerMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<ManufacturerDTO> getAllManufacturerData() {
        List<Manufacturer> manufacturerDTOS= manufacturerRepositoryExtended.findByStatusIgnoreCase("active");
        return manufacturerMapper.toDto(manufacturerDTOS);
    }

    @Override
    public List<ManufacturerDTO> getManufactureByStatus(String status) {
        List<ManufacturerDTO> dtoDataList = new ArrayList<ManufacturerDTO>();
        List<Manufacturer> data = manufacturerRepositoryExtended.findByStatusIgnoreCase(status);
        return manufacturerMapper.toDto(data);
    }

    @Override
    public ResponseDTO setManufacturerStatusById(Long manufactureById, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Manufacturer manufacturer = manufacturerRepositoryExtended.findByManufacturerId(manufactureById);
                manufacturer.setStatus(status);
                manufacturerRepositoryExtended.save(manufacturer);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(manufacturer), 200));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>(), 200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>(), 200));
        }
    }
}
