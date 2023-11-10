package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.domain.TaxonomyDetails;
import com.sunknowledge.dme.rcm.repository.others.TaxonomyDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxonomyDetailsParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxonomyDetailsMapper;
import com.sunknowledge.dme.rcm.service.others.TaxonomyDetailsServiceExtended;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Primary
@Service
public class TaxonomyDetailsServiceExtendedImpl implements TaxonomyDetailsServiceExtended {

    @Autowired
    TaxonomyDetailsRepositoryExtended taxonomyDetailsRepositoryExtended;
    @Autowired
    TaxonomyDetailsMapper taxonomyDetailsMapper;

    @Override
    public TaxonomyDetailsDTO save(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        return null;
    }

    @Override
    public TaxonomyDetailsDTO update(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        return null;
    }

    @Override
    public Optional<TaxonomyDetailsDTO> partialUpdate(TaxonomyDetailsDTO taxonomyDetailsDTO) {
        return Optional.empty();
    }

    @Override
    public Page<TaxonomyDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<TaxonomyDetailsDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveUpdateTaxonomyDetails(TaxonomyDetailsParameterDTO taxonomyDetailsParameterDTO) {
        ResponseDTO outcome = new ResponseDTO();
        if(taxonomyDetailsParameterDTO.getTaxonomyCode() != null && taxonomyDetailsParameterDTO.getTaxonomyCode() != "") {
            Optional<TaxonomyDetails> isDuplicate = taxonomyDetailsRepositoryExtended.
                findByTaxonomyCodeAndStatusIgnoreCase(taxonomyDetailsParameterDTO.getTaxonomyCode(),"active");
            if(!isDuplicate.isPresent() || taxonomyDetailsParameterDTO.getTaxonomyDetailsUuid() != null) {
                CommonUtilities.dtoTrimmer(taxonomyDetailsParameterDTO);
                TaxonomyDetailsDTO taxonomyDetailsParameterDTOData = (taxonomyDetailsParameterDTO.getTaxonomyDetailsUuid() == null) ?
                    new TaxonomyDetailsDTO() :
                    (taxonomyDetailsRepositoryExtended.findById(getIDByUUID(taxonomyDetailsParameterDTO.getTaxonomyDetailsUuid())).isEmpty() ?
                        new TaxonomyDetailsDTO() : taxonomyDetailsMapper.toDto(
                        taxonomyDetailsRepositoryExtended.findById(getIDByUUID(taxonomyDetailsParameterDTO.getTaxonomyDetailsUuid())).get()));
                BeanUtils.copyProperties(taxonomyDetailsParameterDTO, taxonomyDetailsParameterDTOData);
                String isSaved = "";
                if (taxonomyDetailsParameterDTOData.getTaxonomyDetailsUuid() == null) {
                    taxonomyDetailsParameterDTOData.setTaxonomyDetailsId(null);
                    taxonomyDetailsParameterDTOData.setTaxonomyDetailsUuid(UUID.randomUUID());
                    taxonomyDetailsParameterDTOData.setCreatedDate(LocalDate.now());
                    taxonomyDetailsParameterDTOData.setCreatedById(1L);
                    taxonomyDetailsParameterDTOData.setCreatedByName("Abhijit");
                    isSaved = "Saved.";
                } else {
                    isSaved = "Updated.";
                    taxonomyDetailsParameterDTOData.setTaxonomyDetailsId(getIDByUUID(taxonomyDetailsParameterDTO.getTaxonomyDetailsUuid()));
                    taxonomyDetailsParameterDTOData.setUpdatedDate(LocalDate.now());
                    taxonomyDetailsParameterDTOData.setUpdatedById(1L);
                    taxonomyDetailsParameterDTOData.setUpdatedByName("Abhijit");
                }

                TaxonomyDetails savedTaxonomyDetails = taxonomyDetailsRepositoryExtended.save(taxonomyDetailsMapper.toEntity(taxonomyDetailsParameterDTOData));

                outcome.setData(List.of(savedTaxonomyDetails));
                outcome.setStatus(true);
                outcome.setMessage("Data Successfully "+isSaved);
                return outcome;
            }else{
                outcome.setData(null);
                outcome.setStatus(false);
                outcome.setMessage("Data Already Exist.");
                return outcome;
            }
        }else{
            outcome.setStatus(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public Long getIDByUUID(UUID taxonomDetailsUUID) {
        return taxonomyDetailsRepositoryExtended.getIDByUUID(taxonomDetailsUUID);
    }

    @Override
    public ResponseDTO getTaxonomyDetailsByNameOrCodeOrUUID(String data, String operationType) {

        switch (operationType){
            case "taxonomyUUID":{
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                TaxonomyDetails taxonomyDetails = taxonomyDetailsRepositoryExtended.findByTaxonomyDetailsIdAndStatusIgnoreCase(id,"active");
                if(taxonomyDetails == null){
                    return new ResponseDTO<>(false,"Data Not Found.",null );
                }else{
                    return new ResponseDTO<>(true,"Data Fetched Successfully.",List.of(taxonomyDetails));
                }
            }
            case "taxonomyCode":{
                Optional<TaxonomyDetails> taxonomyDetails = taxonomyDetailsRepositoryExtended.findByTaxonomyCodeAndStatusIgnoreCase(data,"active");
                if(!taxonomyDetails.isPresent()){
                    return new ResponseDTO<>(false,"Data Not Found.",null );
                }else{
                    return new ResponseDTO<>(true,"Data Fetched Successfully.",List.of(taxonomyDetails));
                }
            }
            case "taxonomyName":{
                data = data.trim();
                if(data != ""){
                    List<TaxonomyDetails> taxonomyDetails = new ArrayList<>();
                    if(data.length() < 5){
                        taxonomyDetails = taxonomyDetailsRepositoryExtended.findByTaxonomyNameIgnoreCaseAndStatusIgnoreCase(data,"active");
                    }else{
                        taxonomyDetails = taxonomyDetailsRepositoryExtended.findByTaxonomyNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data+"%","active");
                    }

                    if(taxonomyDetails.size()==0){
                        return new ResponseDTO<>(false,"Data Not Found.",null );
                    }else{
                        return new ResponseDTO<>(true,"Data Fetched Successfully.",taxonomyDetails);
                    }
                }
            }
            default:{
                return new ResponseDTO(false,"Please Give Correct OperationType",null);
            }
        }

    }
}
