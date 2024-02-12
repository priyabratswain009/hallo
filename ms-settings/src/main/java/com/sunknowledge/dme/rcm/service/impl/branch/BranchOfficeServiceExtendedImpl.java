package com.sunknowledge.dme.rcm.service.impl.branch;

import com.sunknowledge.dme.rcm.domain.BranchOffice;
import com.sunknowledge.dme.rcm.repository.branch.BranchOfficeRepositoryExtended;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeRejectedDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.helper.branch.BranchOfficeHelper;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeMapper;
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
public class BranchOfficeServiceExtendedImpl implements BranchOfficeServiceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchOfficeServiceExtendedImpl.class);
    @Autowired
    BranchOfficeHelper branchOfficeHelper;
    @Autowired
    BranchOfficeMapper branchOfficeMapper;
    @Autowired
    BranchOfficeRepositoryExtended branchOfficeRepositoryExtended;
    @Override
    public BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO) {
        return null;
    }

    @Override
    public BranchOfficeDTO update(BranchOfficeDTO branchOfficeDTO) {
        return null;
    }

    @Override
    public Optional<BranchOfficeDTO> partialUpdate(BranchOfficeDTO branchOfficeDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchOfficeDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchOfficeDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO bulkUploadForBranchOffice(MultipartFile documentFile) {
        Map<String,Object> branchOfficeBothData;
        String message = "";
        try {
            branchOfficeBothData = branchOfficeHelper.csvToBranchOfficeDTOMapper(documentFile.getInputStream());
            List<BranchOfficeDTO> branchOfficeDTOs = (List<BranchOfficeDTO>) branchOfficeBothData.get("branchOfficeDTOS");

            branchOfficeRepositoryExtended.saveAll(branchOfficeMapper.toEntity(branchOfficeDTOs));

            Integer successfullyUploaded = ((List<BranchOfficeDTO>) branchOfficeBothData.get("branchOfficeDTOS")).size();
            Integer skipped = ((Map<String, BranchOfficeRejectedDTO>) branchOfficeBothData.get("SkippedBranchOfficeDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }
            return (new ResponseDTO(Boolean.TRUE,message,List.of(branchOfficeBothData.get("SkippedBranchOfficeDTO")),200));
        } catch (IOException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO saveBranchOffice(BranchOfficeParameterDTO branchOfficeParameterDTO) {
        Set uniqueNpiSet = branchOfficeRepositoryExtended.findAll().stream().map(x -> x.getNpi()).collect(Collectors.toSet());
        Set uniqueBranchIdSet = branchOfficeRepositoryExtended.findAll().stream().map(x -> x.getBranchId()).collect(Collectors.toSet());

        if (branchOfficeParameterDTO.getNpi() == null) {
            return (new ResponseDTO(Boolean.FALSE,"Invalid Attribute (Npi)",null,200));
            //throw new InvalidAttributeValueException("Invalid Attribute (Npi)");
        } else if (branchOfficeParameterDTO.getBranchName() == null) {
            return (new ResponseDTO(Boolean.FALSE,"Invalid Attribute (branch_name)",null,200));
            //throw new InvalidAttributeValueException("Invalid Attribute (branch_name)");
        } else if (branchOfficeParameterDTO.getNpi().trim() == "") {
            return (new ResponseDTO(Boolean.FALSE,"Npi must be provided",null,200));
            //throw new InputMismatchException("Npi must be provided");
        } else if (branchOfficeParameterDTO.getBranchName().trim() == "") {
            return (new ResponseDTO(Boolean.FALSE,"Branch_name must be provided",null,200));
            //throw new InputMismatchException("Branch_name must be provided");
        } else if((branchOfficeParameterDTO.getBranchId() == null || branchOfficeParameterDTO.getBranchId() == 0) && uniqueNpiSet.contains(branchOfficeParameterDTO.getNpi())){
            return (new ResponseDTO(Boolean.FALSE,"("+ branchOfficeParameterDTO.getNpi() +") "+"Npi already exist",null,200));
            //throw new InputMismatchException("("+ branchOfficeParameterDTO.getNpi() +") "+"Npi already exist");
        } else if(!uniqueBranchIdSet.contains(branchOfficeParameterDTO.getBranchId()) &&
            uniqueNpiSet.contains(branchOfficeParameterDTO.getNpi())){
            return (new ResponseDTO(Boolean.FALSE,"("+ branchOfficeParameterDTO.getNpi() +") "+"Npi already exist",null,200));
            //throw new InputMismatchException("("+ branchOfficeParameterDTO.getNpi() +") "+"Npi already exist");
        }
        BranchOfficeDTO branchOfficeDTO = (branchOfficeParameterDTO.getBranchId() == null ||
            branchOfficeParameterDTO.getBranchId() == 0) ? new BranchOfficeDTO() :
            (branchOfficeRepositoryExtended.findById(branchOfficeParameterDTO.getBranchId()).isPresent() ?
                branchOfficeMapper.toDto(branchOfficeRepositoryExtended.findById(branchOfficeParameterDTO.getBranchId()).get()) :
                new BranchOfficeDTO());

        BeanUtils.copyProperties(branchOfficeParameterDTO, branchOfficeDTO);
        branchOfficeDTO.setStatus("active");
        if (branchOfficeDTO.getBranchId() == null || branchOfficeDTO.getBranchId() == 0) {
            branchOfficeDTO.setBranchId(null);  //procedureCodeRepositoryExtended.findNextId()
            branchOfficeDTO.setCreatedDate(LocalDate.now());
            branchOfficeDTO.setCreatedById(1L);
            branchOfficeDTO.setCreatedByName("Abhijit");
            branchOfficeDTO.setBranchOfficeUuid(UUID.randomUUID());
            branchOfficeDTO.setBranchNo(branchOfficeRepositoryExtended.getBranchNumber());
        } else {
            branchOfficeDTO.setUpdatedDate(LocalDate.now());
            branchOfficeDTO.setUpdatedById(1L);
            branchOfficeDTO.setUpdatedByName("Abhijit");
        }
        BranchOfficeDTO savedBranchOfficeDTO = branchOfficeMapper.toDto(
            branchOfficeRepositoryExtended.save(branchOfficeMapper.toEntity(branchOfficeDTO))
        );
        return (new ResponseDTO(Boolean.TRUE,"Successfully Saved",savedBranchOfficeDTO,200));
    }

    @Override
    public BranchOfficeDTO getBranchOfficeById(Long branchId) {
        BranchOffice data = branchOfficeRepositoryExtended.findByBranchId(branchId);
        if(data != null){
            return branchOfficeMapper.toDto(data);
        }
        return null;
    }

    @Override
    public BranchOfficeDTO getActiveBranchOfficeById(Long branchId) {

        BranchOffice data = branchOfficeRepositoryExtended.findByBranchIdAndStatusIgnoreCase(branchId,"active");
        if(data != null){
            return branchOfficeMapper.toDto(data);
        }else {
            return null;
        }
    }

    @Override
    public ResponseDTO setBranchOfficeStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<BranchOffice> obj = branchOfficeRepositoryExtended.findByBranchOfficeUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    branchOfficeRepositoryExtended.save(obj.get());
                    return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", obj.get(),200));
                }else{
                    return (new ResponseDTO(Boolean.FALSE, "Data Not Found",null,200));
                }
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>(),200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>(),200));
        }
    }

    @Override
    public List<BranchOfficeDTO> getBranchOfficeByBranchName(String branchName) {
        List<BranchOffice> data = branchOfficeRepositoryExtended.findByBranchNameLikeIgnoreCaseAndStatusIgnoreCase("%"+branchName+"%", "active");
        return branchOfficeMapper.toDto(data);
    }

    @Override
    public List<BranchOfficeDTO> getBranchOfficeByBranchNo(String branchNo) {
        List<BranchOfficeDTO> dtoDataList = new ArrayList<BranchOfficeDTO>();
        BranchOffice data = branchOfficeRepositoryExtended.findByBranchNoAndStatusIgnoreCase(branchNo, "active");
        if(data != null){
            dtoDataList.add(branchOfficeMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<BranchOfficeExtendedDTO> getAllBranchOfficeData() {
        List<BranchOffice> data = branchOfficeRepositoryExtended.findByStatusIgnoreCase("active");
        List<BranchOfficeExtendedDTO> branchOfficeExtendedDTOS = new ArrayList<>();
        for(BranchOffice branchOffice:data){
            BranchOfficeExtendedDTO branchOfficeExtendedDTO = new BranchOfficeExtendedDTO();
            BeanUtils.copyProperties(branchOffice,branchOfficeExtendedDTO);
            branchOfficeExtendedDTO.setId(branchOffice.getBranchId());
            branchOfficeExtendedDTO.setTitle(branchOffice.getBranchName());
            branchOfficeExtendedDTOS.add(branchOfficeExtendedDTO);
        }
        return branchOfficeExtendedDTOS;
    }

    @Override
    public List<BranchOfficeDTO> getBranchOfficeByNpi(String npi) {
        List<BranchOfficeDTO> dtoDataList = new ArrayList<BranchOfficeDTO>();
        BranchOffice data = branchOfficeRepositoryExtended.findByNpiAndStatusIgnoreCase(npi, "active");
        if(data != null){
            dtoDataList.add(branchOfficeMapper.toDto(data));
        }
        return dtoDataList;
    }

    @Override
    public List<BranchOfficeDTO> getBranchOfficeByStatus(String status) {
        List<BranchOffice> data = branchOfficeRepositoryExtended.findByStatusIgnoreCase(status);
        return branchOfficeMapper.toDto(data);
    }

    @Override
    public List<BranchOfficeDTO> findByBranchIdIn(List<Long> branchIdList) {
        List<BranchOffice> data = branchOfficeRepositoryExtended.findByBranchIdInAndStatusIgnoreCase(branchIdList, "active");
        return branchOfficeMapper.toDto(data);
    }

    @Override
    public BranchOfficeDTO findByBranchId(Long branchId) {
        BranchOffice data = branchOfficeRepositoryExtended.findByBranchIdAndStatusIgnoreCase(branchId, "active");
        return branchOfficeMapper.toDto(data);
    }

    @Override
    public List<Map> getBranchDetailsByUserUUIDorUserName(String param, String optVal) {
        List<Map> list;
        try{
            switch (optVal){
                case "userUUID":
                    list = branchOfficeRepositoryExtended.getBranchDetailsByUserUuid(UUID.fromString(param));
                    break;
                case "userName":
                    list = branchOfficeRepositoryExtended.getBranchDetailsByUserName(param);
                    break;
                default:
                    list = null;
                    throw new InvalidAttributeValueException("Invalid Attribute opType must be userUUID or userName");
            }
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Map> getUserDetailsByBranchUUIDorBranchName(String param, String opType) {
        List<Map> list;
        try {
            switch (opType){
                case "branchUUID":
                    list = branchOfficeRepositoryExtended.getUserDetailsByBranchUUID(UUID.fromString(param));
                    break;
                case "branchName":
                    list = branchOfficeRepositoryExtended.getUserDetailsByBranchName(param);
                    break;
                default:
                    list = null;
                    throw new InvalidAttributeValueException("Invalid Attribute opType must be branchUUID or branchName");
            }
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Map> getLocationDetailsByBranchUUIDorBranchName(String param, String opType) {
        List<Map> list;
        try{
            switch (opType){
                case "branchUUID":
                    list = branchOfficeRepositoryExtended.getLocationDetailsByBranchUUID(UUID.fromString(param));
                    break;
                case "branchName":
                    list = branchOfficeRepositoryExtended.getLocationDetailsByBranchName(param);
                    break;
                default:
                    list = null;
                    throw new InvalidAttributeValueException("Invalid Attribute opType must be branchUUID or branchName");
            }
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
            return list;
    }

    @Override
    public List<Map> getInsuranceDetailsByBranchUUIDorBranchName(String param, String opType) {
        List<Map> list;
        try{
            switch (opType){
                case "branchUUID":
                    list = branchOfficeRepositoryExtended.getInsuranceDetailsByBranchUUID(UUID.fromString(param));
                    break;
                case "branchName":
                    list = branchOfficeRepositoryExtended.getInsuranceDetailsByBranchName(param);
                    break;
                default:
                    list = null;
                    throw new InvalidAttributeValueException("Invalid Attribute opType must be branchUUID or branchName");
            }
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
            return list;
    }

    @Override
    public List<Map> getCompanyDetailsByBranchUUIDorBranchName(String param, String opType) {
        List<Map> list;
        try{
            switch (opType){
                case "branchUUID":
                    list = branchOfficeRepositoryExtended.getCompanyDetailsByBranchUUID(UUID.fromString(param));
                    break;
                case "branchName":
                    list = branchOfficeRepositoryExtended.getCompanyDetailsByBranchName(param);
                    break;
                default:
                    list = null;
                    throw new InvalidAttributeValueException("Invalid Attribute opType must be branchUUID or branchName");
            }
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
            return list;
    }

	@Override
	public ServiceOutcome<BranchOfficeDTO> getBranchOfficeForABNById(Long branchId) {
        BranchOfficeDTO dtoDataList = new BranchOfficeDTO();
        ServiceOutcome<BranchOfficeDTO> outCome = new ServiceOutcome<BranchOfficeDTO>();
        BranchOffice data = branchOfficeRepositoryExtended.findByBranchId(branchId);
        if(data != null){
            dtoDataList=branchOfficeMapper.toDto(data);
            outCome.setData(dtoDataList);
            outCome.setMessage("Data Recieved Successfully");
            outCome.setOutcome(true);
        }else {
            outCome.setData(null);
            outCome.setMessage("Data not Recieved");
            outCome.setOutcome(false);
        }
        return outCome;
    }

}
