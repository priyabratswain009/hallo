package com.sunknowledge.dme.rcm.service.impl.itemothers;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.repository.itemothers.VendorMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.helper.itemothers.VendorMasterServiceImplHelper;
import com.sunknowledge.dme.rcm.service.itemothers.VendorMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterRejectedDTO;
import com.sunknowledge.dme.rcm.service.mapper.VendorMasterMapper;
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
public class VendorMasterServiceExtendedImpl implements VendorMasterServiceExtended {
    private final Logger log = LoggerFactory.getLogger(VendorMasterServiceExtendedImpl.class);
    @Autowired
    VendorMasterRepositoryExtended vendorMasterRepositoryExtended;

    @Autowired
    VendorMasterMapper vendorMasterMapper;

    @Autowired
    VendorMasterServiceImplHelper vendorMasterServiceImplHelper;


    @Override
    public VendorMasterDTO save(VendorMasterDTO vendorMasterDTO) {
        return null;
    }

    @Override
    public VendorMasterDTO update(VendorMasterDTO vendorMasterDTO) {
        return null;
    }

    @Override
    public Optional<VendorMasterDTO> partialUpdate(VendorMasterDTO vendorMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<VendorMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<VendorMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public ResponseDTO bulkUploadForVendor(MultipartFile file) {
        Map<String,Object> vendorMasterBothData;
        String message = "";
        try {
            vendorMasterBothData = vendorMasterServiceImplHelper.csvToVendorMasterDTOMapper(file.getInputStream());
            List<VendorMasterDTO> vendorMasterDTOs = (List<VendorMasterDTO>) vendorMasterBothData.get("vendorMasterDTOS");
            vendorMasterRepositoryExtended.saveAll(vendorMasterMapper.toEntity(vendorMasterDTOs));
            Integer successfullyUploaded = ((List<VendorMasterDTO>) vendorMasterBothData.get("vendorMasterDTOS")).size();
            Integer skipped = ((Map<String, VendorMasterRejectedDTO>) vendorMasterBothData.get("SkippedVendorMasterDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }

            return (new ResponseDTO(Boolean.TRUE,message,List.of(vendorMasterBothData.get("SkippedVendorMasterDTO"))));
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }

    }


    @Override
    public ResponseDTO saveVendor(VendorMasterParameterDTO vendorMasterParameterDTO) {
        Set uniqueVendorNoSet = vendorMasterRepositoryExtended.findAll().stream().map(x -> x.getVendorNo()).collect(Collectors.toSet());
        Set uniqueVendorIdSet = vendorMasterRepositoryExtended.findAll().stream().map(x -> x.getVendorId()).collect(Collectors.toSet());

        try {
            if (vendorMasterParameterDTO.getVendorName() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (vendor_name)");
            } else if (vendorMasterParameterDTO.getVendorName().trim() == "") {
                throw new InputMismatchException("Vendor_name must be provided");
            }
            VendorMasterDTO vendorMasterDTO = (vendorMasterParameterDTO.getVendorId() == null ||
                vendorMasterParameterDTO.getVendorId() == 0) ? new VendorMasterDTO() :
                (vendorMasterRepositoryExtended.findById(vendorMasterParameterDTO.getVendorId()).isPresent() ?
                    vendorMasterMapper.toDto(vendorMasterRepositoryExtended.findById(vendorMasterParameterDTO.getVendorId()).get()) :
                    new VendorMasterDTO());

            BeanUtils.copyProperties(vendorMasterParameterDTO, vendorMasterDTO);
            if (vendorMasterDTO.getVendorId() == null || vendorMasterDTO.getVendorId() == 0) {
                vendorMasterDTO.setVendorId(null);  //procedureCodeRepositoryExtended.findNextId()
                vendorMasterDTO.setCreatedDate(LocalDate.now());
                vendorMasterDTO.setCreatedById(1L);
                vendorMasterDTO.setCreatedByName("Abhijit");
                vendorMasterDTO.setVendorMasterUuid(UUID.randomUUID());
                vendorMasterDTO.setVendorNo(vendorMasterRepositoryExtended.getVendorNumber());
            } else {
                vendorMasterDTO.setUpdatedDate(LocalDate.now());
                vendorMasterDTO.setUpdatedById(1L);
                vendorMasterDTO.setUpdatedByName("Abhijit");
            }
            CommonUtilities.dtoTrimmer(vendorMasterDTO);
            VendorMasterDTO savedVendorMasterDTO = vendorMasterMapper.toDto(
                vendorMasterRepositoryExtended.save(vendorMasterMapper.toEntity(vendorMasterDTO))
            );
            return new ResponseDTO(true, "Successfully Saved.", List.of(savedVendorMasterDTO));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VendorMasterDTO> getVendorByVendorNo(String vendorNo) {
        List<VendorMasterDTO> dtoDataList = new ArrayList<VendorMasterDTO>();
        VendorMaster data = vendorMasterRepositoryExtended.findByVendorNoAndStatusIgnoreCase(vendorNo, "active");
        if(data != null){
            dtoDataList.add(vendorMasterMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<VendorMasterDTO> getVendorByVendorName(String vendorName) {
        List<VendorMaster> data = vendorMasterRepositoryExtended.findByVendorNameLikeIgnoreCaseAndStatusIgnoreCase("%"+vendorName+"%", "active");
        List<VendorMasterDTO> dtoDataList = (vendorMasterMapper.toDto(data));
        return dtoDataList;
    }

    @Override
    public List<VendorMasterDTO> getVendorById(Long vendorId) {
        List<VendorMasterDTO> dtoDataList = new ArrayList<VendorMasterDTO>();
        VendorMaster data = vendorMasterRepositoryExtended.findByVendorId(vendorId);
        if(data != null){
            dtoDataList.add(vendorMasterMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<VendorMasterDTO> getVendorByVendorDescription(String vendorDescription) {
        List<VendorMasterDTO> dtoDataList = new ArrayList<VendorMasterDTO>();
        List<VendorMaster> data = vendorMasterRepositoryExtended.findByVendorDescriptionLikeIgnoreCaseAndStatusIgnoreCase("%"+vendorDescription+"%", "active");
        if(data != null){
            dtoDataList = (vendorMasterMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<VendorMasterDTO> getAllVendorMasterData() {
        List<VendorMaster> vendorMasters= vendorMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return vendorMasterMapper.toDto(vendorMasters);
    }

    @Override
    public List<VendorMasterDTO> getVendorByStatus(String status) {
        List<VendorMaster> data = vendorMasterRepositoryExtended.findByStatusIgnoreCase(status);
        return vendorMasterMapper.toDto(data);
    }

    @Override
    public Optional<VendorMasterDTO> findByVendorId(Long vendorId) {
        VendorMaster vendorMaster = vendorMasterRepositoryExtended.findByVendorId(vendorId);
        return Optional.ofNullable(vendorMasterMapper.toDto(vendorMaster));
    }

    @Override
    public ResponseDTO setVendorMasterStatusById(Long id, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                VendorMaster vendorMaster = vendorMasterRepositoryExtended.findByVendorId(id);
                vendorMaster.setStatus(status);
                vendorMasterRepositoryExtended.save(vendorMaster);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(vendorMaster)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }
}
