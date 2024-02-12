package com.sunknowledge.dme.rcm.service.impl.insurance;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.repository.insurance.BranchInsuranceMapRepositoryExtended;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.*;
import com.sunknowledge.dme.rcm.service.helper.insurance.BranchInsuranceMapHelper;
import com.sunknowledge.dme.rcm.service.insurance.BranchInsuranceMapServiceExtended;
import com.sunknowledge.dme.rcm.service.insurance.InsuranceMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BranchInsuranceMapServiceExtendedImpl implements BranchInsuranceMapServiceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchInsuranceMapServiceExtendedImpl.class);
    @Autowired
    BranchInsuranceMapHelper branchInsuranceMapHelper;
    @Autowired
    BranchInsuranceMapRepositoryExtended branchInsuranceMapRepositoryExtended;
    @Autowired
    BranchInsuranceMapMapper branchInsuranceMapMapper;
    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;
    @Autowired
    InsuranceMasterServiceExtended insuranceMasterServiceExtended;

    @Override
    public BranchInsuranceMapDTO save(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        return null;
    }

    @Override
    public BranchInsuranceMapDTO update(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        return null;
    }

    @Override
    public Optional<BranchInsuranceMapDTO> partialUpdate(BranchInsuranceMapDTO branchInsuranceMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<BranchInsuranceMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<BranchInsuranceMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Object bulkUploadForBranchInsurance(MultipartFile documentFile) {
        Map<String,Object> branchInsuranceMapBothData;
        BranchInsuranceMapResponseDTO branchInsuranceMapResponseDTO = new BranchInsuranceMapResponseDTO();
        String message = "";
        try {
            branchInsuranceMapBothData = branchInsuranceMapHelper.csvToBranchInsuranceMapDTOMapper(documentFile.getInputStream());
            List<BranchInsuranceMapDTO> branchInsuranceMapDTOS = (List<BranchInsuranceMapDTO>) branchInsuranceMapBothData.get("branchInsuranceMapDTOS");

            branchInsuranceMapRepositoryExtended.saveAll(branchInsuranceMapMapper.toEntity(branchInsuranceMapDTOS));

            Integer successfullyUploaded = ((List<InsuranceMasterDTO>) branchInsuranceMapBothData.get("branchInsuranceMapDTOS")).size();
            Integer skipped = ((Map<String, InsuranceMasterRejectedDTO>) branchInsuranceMapBothData.get("SkippedBranchInsuranceMapDTO")).size();
            message = "Successfully Uploaded ("+successfullyUploaded+")";
            if(skipped > 0){
                message += " and Skipped " + skipped + " Rows";
            }

            branchInsuranceMapResponseDTO.setMessage(message);

            branchInsuranceMapResponseDTO.setSkippedData((Map<String, BranchInsuranceMapRejectedDTO>) branchInsuranceMapBothData.get("SkippedBranchInsuranceMapDTO"));

        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
        return branchInsuranceMapResponseDTO;
    }

    @Override
    public ResponseDTO saveBranchInsuranceMap(BranchInsuranceMapExtendedDTO branchInsuranceMapExtendedDTO) {
        try {
            if (branchInsuranceMapExtendedDTO.getInsuranceId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Insurance_Id)");
            }else if (branchInsuranceMapExtendedDTO.getBranchIdList() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            } else if (branchInsuranceMapExtendedDTO.getBranchIdList().size() == 0) {
                throw new InputMismatchException("Branch_Ids must be provided");
            }
            Set<Long> branchIdSet = branchInsuranceMapExtendedDTO.getBranchIdList().stream().filter(x->x!=0 && x!=null).collect(Collectors.toSet());
            List<Long> branchIdList = branchIdSet.stream().collect(Collectors.toList());
            List<BranchOfficeDTO> branchOfficeDTOS = branchOfficeServiceExtended.findByBranchIdIn(branchIdList);
            Optional<InsuranceMasterDTO> insuranceMasterDTO = insuranceMasterServiceExtended.findByInsuranceId(Long.valueOf(branchInsuranceMapExtendedDTO.getInsuranceId()));
            Set uniqueInsuranceId_BranchIdSet = branchInsuranceMapRepositoryExtended.findAll().stream().map(x -> x.getInsuranceId()+"_"+x.getBranchId()).collect(Collectors.toSet());
            List<BranchInsuranceMapDTO> branchInsuranceMapDTOs = new ArrayList<BranchInsuranceMapDTO>();
            Boolean isDataSaved = false;
            for(BranchOfficeDTO data : branchOfficeDTOS){
                if(!uniqueInsuranceId_BranchIdSet.contains(insuranceMasterDTO.get().getInsuranceId()+"_"+data.getBranchId())) {
                    BranchInsuranceMapDTO branchInsuranceMapDTO = new BranchInsuranceMapDTO();

                    branchInsuranceMapDTO.setBranchId(Long.valueOf(data.getBranchId()));
                    branchInsuranceMapDTO.setBranchName(data.getBranchName());
                    branchInsuranceMapDTO.setInsuranceId(Long.valueOf(insuranceMasterDTO.get().getInsuranceId()));
                    branchInsuranceMapDTO.setInsuranceName(insuranceMasterDTO.get().getInsuranceName());
                    branchInsuranceMapDTO.setInsuranceIdNo(insuranceMasterDTO.get().getInsuranceIdNo());
                    branchInsuranceMapDTO.setInsuranceStateName(insuranceMasterDTO.get().getState());
                    branchInsuranceMapDTO.setPriceTableId(insuranceMasterDTO.get().getPriceTableId());
                    branchInsuranceMapDTO.setPriceTableName(insuranceMasterDTO.get().getPriceTableName());
                    branchInsuranceMapDTO.setBranchNpi(data.getNpi());
                    branchInsuranceMapDTO.setBranchPtan(data.getPtan());
                    branchInsuranceMapDTO.setBranchTaxonomyCode(data.getTaxonomyCode());
                    branchInsuranceMapDTO.setEsubmitterPayorId(branchInsuranceMapExtendedDTO.getEsubmitterPayorId());
                    branchInsuranceMapDTO.setBillingProviderOverrideCompanyName(branchInsuranceMapExtendedDTO.getBillingProviderOverrideCompanyName());
                    branchInsuranceMapDTO.setBillingProviderOverrideTaxIdEin(branchInsuranceMapExtendedDTO.getBillingProviderOverrideTaxIdEin());
                    branchInsuranceMapDTO.setBillingProviderOverrideAddressLine1(branchInsuranceMapExtendedDTO.getBillingProviderOverrideAddressLine1());
                    branchInsuranceMapDTO.setBillingProviderOverrideAddressLine2(branchInsuranceMapExtendedDTO.getBillingProviderOverrideAddressLine2());
                    branchInsuranceMapDTO.setBillingProviderOverrideCity(branchInsuranceMapExtendedDTO.getBillingProviderOverrideCity());
                    branchInsuranceMapDTO.setBillingProviderOverrideState(branchInsuranceMapExtendedDTO.getBillingProviderOverrideCity());
                    branchInsuranceMapDTO.setBillingProviderOverrideZip(branchInsuranceMapExtendedDTO.getBillingProviderOverrideZip());
                    branchInsuranceMapDTO.setBillingProviderOverrideContact1(branchInsuranceMapExtendedDTO.getBillingProviderOverrideContact1());
                    branchInsuranceMapDTO.setBillingProviderOverrideContact2(branchInsuranceMapExtendedDTO.getBillingProviderOverrideContact2());
                    branchInsuranceMapDTO.setBillingProviderOverrideFax(branchInsuranceMapExtendedDTO.getBillingProviderOverrideFax());
                    branchInsuranceMapDTO.setBillingProviderOverrideEmail(branchInsuranceMapExtendedDTO.getBillingProviderOverrideEmail());
                    branchInsuranceMapDTO.setPayToProviderCompanyName(branchInsuranceMapExtendedDTO.getPayToProviderCompanyName());
                    branchInsuranceMapDTO.setPayToProviderTaxIdEin(branchInsuranceMapExtendedDTO.getPayToProviderTaxIdEin());
                    branchInsuranceMapDTO.setPayToProviderAddressLine1(branchInsuranceMapExtendedDTO.getPayToProviderAddressLine1());
                    branchInsuranceMapDTO.setPayToProviderAddressLine2(branchInsuranceMapExtendedDTO.getPayToProviderAddressLine2());
                    branchInsuranceMapDTO.setPayToProviderCity(branchInsuranceMapExtendedDTO.getPayToProviderCity());
                    branchInsuranceMapDTO.setPayToProviderState(branchInsuranceMapExtendedDTO.getPayToProviderState());
                    branchInsuranceMapDTO.setPayToProviderZip(branchInsuranceMapExtendedDTO.getPayToProviderZip());
                    branchInsuranceMapDTO.setPayToProviderContact1(branchInsuranceMapExtendedDTO.getPayToProviderContact1());
                    branchInsuranceMapDTO.setPayToProviderContact2(branchInsuranceMapExtendedDTO.getPayToProviderContact2());
                    branchInsuranceMapDTO.setPayToProviderFax(branchInsuranceMapExtendedDTO.getPayToProviderFax());
                    branchInsuranceMapDTO.setPayToProviderEmail(branchInsuranceMapExtendedDTO.getPayToProviderEmail());
                    branchInsuranceMapDTO.setSubmitterName(branchInsuranceMapExtendedDTO.getSubmitterName());
                    branchInsuranceMapDTO.setSubmitterContact1(branchInsuranceMapExtendedDTO.getSubmitterContact1());
                    branchInsuranceMapDTO.setSubmitterContact2(branchInsuranceMapExtendedDTO.getSubmitterContact2());
                    branchInsuranceMapDTO.setStatus("active");

                    if (branchInsuranceMapDTO.getBranchInsuranceMapId() == null || branchInsuranceMapDTO.getBranchInsuranceMapId() == 0) {
                        branchInsuranceMapDTO.setBranchInsuranceMapId(null);
                        branchInsuranceMapDTO.setCreatedDate(LocalDate.now());
                        branchInsuranceMapDTO.setCreatedById(1L);
                        branchInsuranceMapDTO.setCreatedByName("Abhijit");
                        branchInsuranceMapDTO.setBranchInsuranceMapUuid(UUID.randomUUID());
                    } else {
                        branchInsuranceMapDTO.setUpdatedDate(LocalDate.now());
                        branchInsuranceMapDTO.setUpdatedById(1L);
                        branchInsuranceMapDTO.setUpdatedByName("Abhijit");
                    }
                    branchInsuranceMapDTOs.add(branchInsuranceMapDTO);
                    isDataSaved = true;
                }
            }

            List<BranchInsuranceMapDTO> savedBranchInsuranceMapDTOs = branchInsuranceMapMapper.toDto(
                branchInsuranceMapRepositoryExtended.saveAll(branchInsuranceMapMapper.toEntity(branchInsuranceMapDTOs))
            );
            return (new ResponseDTO(isDataSaved,isDataSaved?"Successfully Saved":"Data already exist.", (savedBranchInsuranceMapDTOs),200));
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BranchInsuranceMapDTO> getBranchInsuranceMapByInsuranceId(Long insuranceId) {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByInsuranceIdAndStatusIgnoreCase(insuranceId+"","active");
        return branchInsuranceMapMapper.toDto(data);
    }

    @Override
    public List<BranchInsuranceMapDTO> getBranchInsuranceMapByBranchId(Long branchId) {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByBranchIdAndStatusIgnoreCase(branchId,"active");
        return branchInsuranceMapMapper.toDto(data);
    }

    @Override
    public List<BranchInsuranceMapDTO> getBranchInsuranceMapByInsuranceIdNo(String insuranceIdNo) {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByInsuranceIdNoAndStatusIgnoreCase(insuranceIdNo,"active");
        return branchInsuranceMapMapper.toDto(data);
    }

    @Override
    public List<BranchInsuranceMapDTO> getAllBranchInsuranceMapData() {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByStatusIgnoreCase("active");
        return branchInsuranceMapMapper.toDto(data);
    }

    @Override
    public List<BranchInsuranceMapDTO> getBranchInsuranceMapByStatus(String status) {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByStatusIgnoreCase(status);
        return branchInsuranceMapMapper.toDto(data);
    }

    @Override
    public ResponseDTO updateBranchInsuranceMap(BranchInsuranceMapExtendedForUpdateDTO branchInsuranceMapExtendedForUpdateDTO) {
        try {
            if (branchInsuranceMapExtendedForUpdateDTO.getInsuranceId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Insurance_Id)");
            }else if (branchInsuranceMapExtendedForUpdateDTO.getBranchId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Branch_Id)");
            }
            BranchOfficeDTO branchOfficeDTO = branchOfficeServiceExtended.findByBranchId(branchInsuranceMapExtendedForUpdateDTO.getBranchId());
            Optional<InsuranceMasterDTO> insuranceMasterDTO = insuranceMasterServiceExtended.findByInsuranceId(Long.valueOf(branchInsuranceMapExtendedForUpdateDTO.getInsuranceId()));
            BranchInsuranceMap updateDTO = branchInsuranceMapRepositoryExtended.findByInsuranceIdAndBranchIdAndStatusIgnoreCase(branchInsuranceMapExtendedForUpdateDTO.getInsuranceId(),branchInsuranceMapExtendedForUpdateDTO.getBranchId(),"active");
            if(updateDTO != null) {
                updateDTO.setStatus("inactive");
                updateDTO.updatedById(1L);
                updateDTO.updatedByName("Abhijit Update");
                updateDTO.updatedDate(LocalDate.now());
                branchInsuranceMapRepositoryExtended.save(updateDTO);

                BranchInsuranceMapDTO branchInsuranceMapDTO = new BranchInsuranceMapDTO();
                branchInsuranceMapDTO.setBranchId(Long.valueOf(updateDTO.getBranchId()));
                branchInsuranceMapDTO.setBranchName(updateDTO.getBranchName());
                branchInsuranceMapDTO.setInsuranceId(Long.valueOf(updateDTO.getInsuranceId()));
                branchInsuranceMapDTO.setInsuranceName(updateDTO.getInsuranceName());
                branchInsuranceMapDTO.setInsuranceIdNo(updateDTO.getInsuranceIdNo());
                branchInsuranceMapDTO.setInsuranceStateName(updateDTO.getInsuranceStateName());
                branchInsuranceMapDTO.setPriceTableId(updateDTO.getPriceTableId());
                branchInsuranceMapDTO.setPriceTableName(updateDTO.getPriceTableName());
                branchInsuranceMapDTO.setBranchNpi(updateDTO.getBranchNpi());
                branchInsuranceMapDTO.setBranchPtan(updateDTO.getBranchPtan());
                branchInsuranceMapDTO.setBranchTaxonomyCode(updateDTO.getBranchTaxonomyCode());
                //branchInsuranceMapDTO.setBranchInsuranceMapId(null);
                branchInsuranceMapDTO.setCreatedDate(updateDTO.getCreatedDate());
                branchInsuranceMapDTO.setCreatedById(updateDTO.getCreatedById());
                branchInsuranceMapDTO.setCreatedByName(updateDTO.getCreatedByName());
                branchInsuranceMapDTO.setBranchInsuranceMapUuid(updateDTO.getBranchInsuranceMapUuid());

                branchInsuranceMapDTO.setEsubmitterPayorId(branchInsuranceMapExtendedForUpdateDTO.getEsubmitterPayorId());
                branchInsuranceMapDTO.setBillingProviderOverrideCompanyName(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideCompanyName());
                branchInsuranceMapDTO.setBillingProviderOverrideTaxIdEin(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideTaxIdEin());
                branchInsuranceMapDTO.setBillingProviderOverrideAddressLine1(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideAddressLine1());
                branchInsuranceMapDTO.setBillingProviderOverrideAddressLine2(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideAddressLine2());
                branchInsuranceMapDTO.setBillingProviderOverrideCity(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideCity());
                branchInsuranceMapDTO.setBillingProviderOverrideState(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideCity());
                branchInsuranceMapDTO.setBillingProviderOverrideZip(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideZip());
                branchInsuranceMapDTO.setBillingProviderOverrideContact1(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideContact1());
                branchInsuranceMapDTO.setBillingProviderOverrideContact2(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideContact2());
                branchInsuranceMapDTO.setBillingProviderOverrideFax(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideFax());
                branchInsuranceMapDTO.setBillingProviderOverrideEmail(branchInsuranceMapExtendedForUpdateDTO.getBillingProviderOverrideEmail());
                branchInsuranceMapDTO.setPayToProviderCompanyName(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderCompanyName());
                branchInsuranceMapDTO.setPayToProviderTaxIdEin(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderTaxIdEin());
                branchInsuranceMapDTO.setPayToProviderAddressLine1(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderAddressLine1());
                branchInsuranceMapDTO.setPayToProviderAddressLine2(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderAddressLine2());
                branchInsuranceMapDTO.setPayToProviderCity(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderCity());
                branchInsuranceMapDTO.setPayToProviderState(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderState());
                branchInsuranceMapDTO.setPayToProviderZip(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderZip());
                branchInsuranceMapDTO.setPayToProviderContact1(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderContact1());
                branchInsuranceMapDTO.setPayToProviderContact2(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderContact2());
                branchInsuranceMapDTO.setPayToProviderFax(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderFax());
                branchInsuranceMapDTO.setPayToProviderEmail(branchInsuranceMapExtendedForUpdateDTO.getPayToProviderEmail());
                branchInsuranceMapDTO.setSubmitterName(branchInsuranceMapExtendedForUpdateDTO.getSubmitterName());
                branchInsuranceMapDTO.setSubmitterContact1(branchInsuranceMapExtendedForUpdateDTO.getSubmitterContact1());
                branchInsuranceMapDTO.setSubmitterContact2(branchInsuranceMapExtendedForUpdateDTO.getSubmitterContact2());
                branchInsuranceMapDTO.setStatus("active");
                if (branchInsuranceMapDTO.getBranchInsuranceMapId() == null || branchInsuranceMapDTO.getBranchInsuranceMapId() == 0 ) {
                    branchInsuranceMapDTO.setBranchInsuranceMapId(null);
                    branchInsuranceMapDTO.setCreatedDate(LocalDate.now());
                    branchInsuranceMapDTO.setCreatedById(1L);
                    branchInsuranceMapDTO.setCreatedByName("Abhijit");
                    branchInsuranceMapDTO.setBranchInsuranceMapUuid(UUID.randomUUID());
                } else {
                    branchInsuranceMapDTO.setUpdatedDate(LocalDate.now());
                    branchInsuranceMapDTO.setUpdatedById(1L);
                    branchInsuranceMapDTO.setUpdatedByName("Abhijit");
                }
                BranchInsuranceMapDTO savedBranchInsuranceMapDTO = branchInsuranceMapMapper.toDto(
                    branchInsuranceMapRepositoryExtended.save(branchInsuranceMapMapper.toEntity(branchInsuranceMapDTO))
                );
                return (new ResponseDTO(Boolean.TRUE,"Successfully Saved", savedBranchInsuranceMapDTO,200));
            }else {
                return (new ResponseDTO(Boolean.FALSE,"Failed to Save data", null,200));
            }
        }catch (InvalidAttributeValueException e) {
            log.error("=====>> Error : "+e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deactiveBranchInsurancemapByInsuranceIdAndBranchId(Long insuranceId, Long branchId) {
        BranchInsuranceMap updateDTO = branchInsuranceMapRepositoryExtended.findByInsuranceIdAndBranchIdAndStatusIgnoreCase(insuranceId,branchId,"active");
        if(updateDTO != null) {
            updateDTO.setStatus("inactive");
            branchInsuranceMapRepositoryExtended.save(updateDTO);
            return "Successfully Updated";
        }else{
            return "Data Not Found";
        }
    }

    @Override
    public BranchInsuranceMapDTO getBranchInsuranceMapByBranchInsuranceMapId(Long branchInsuranceMapId) {
        BranchInsuranceMap data = branchInsuranceMapRepositoryExtended.findByBranchInsuranceMapId(branchInsuranceMapId);
        return data!=null?branchInsuranceMapMapper.toDto(data):null;
    }

    @Override
    public ResponseDTO setBranchInsurancemapStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<BranchInsuranceMap> obj = branchInsuranceMapRepositoryExtended.findByBranchInsuranceMapUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    branchInsuranceMapRepositoryExtended.save(obj.get());
                    return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", obj.get(),200));
                }else{
                    return (new ResponseDTO(Boolean.FALSE, "Data Not Found",null,200));
                }
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",null,200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", null,200));
        }
    }

    public List<BranchInsuranceMapDTO> getBranchInsuranceMapByBranchIdAndInsuranceId(Long branchId, Long insuranceId)
    {
        List<BranchInsuranceMap> data = branchInsuranceMapRepositoryExtended.findByBranchIdAndStatusIgnoreCase(branchId,"active")
            .stream().filter(x-> x.getInsuranceId().equals(insuranceId)).collect(Collectors.toList());
        return branchInsuranceMapMapper.toDto(data);
    }
}
