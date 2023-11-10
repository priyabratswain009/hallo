package com.sunknowledge.dme.rcm.service.impl.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import com.sunknowledge.dme.rcm.repository.common.MarketingReferalTypeMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.common.MarketingReferalTypeMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.MarketingReferalTypeMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.MarketingReferalTypeMasterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
@Slf4j
public class MarketingReferalTypeMasterServiceExtendedImpl implements MarketingReferalTypeMasterServiceExtended {

    @Autowired
    MarketingReferalTypeMasterRepositoryExtended marketingReferalTypeMasterRepositoryExtended;
    @Autowired
    MarketingReferalTypeMasterMapper marketingReferalTypeMasterMapper;

    @Override
    public MarketingReferalTypeMasterDTO save(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        return null;
    }

    @Override
    public MarketingReferalTypeMasterDTO update(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        return null;
    }

    @Override
    public Optional<MarketingReferalTypeMasterDTO> partialUpdate(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<MarketingReferalTypeMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<MarketingReferalTypeMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ServiceOutcome saveMarketingReferalTypeMaster(MarketingReferalTypeMasterExtendedDTO marketingReferalTypeMasterExtendedDTO) throws InvalidAttributeValueException {
        ServiceOutcome outcome = new ServiceOutcome();
        Set uniqueMarketingReferalTypeCodeSet = marketingReferalTypeMasterRepositoryExtended.findAll().stream().map(x -> x.getMarketingReferralTypeCode()).collect(Collectors.toSet());
        System.out.println("================uniqueMarketingReferalTypeCodeSet======================="+uniqueMarketingReferalTypeCodeSet);

        if (marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode() == null) {
            outcome.setOutcome(false);
            outcome.setMessage("Invalid Attribute (marketingReferralTypeCode)");
            return outcome;
        } else if (marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode().trim().equals("")) {
            outcome.setOutcome(false);
            outcome.setMessage("marketingReferralTypeCode must be provided");
            return outcome;
            //throw new InputMismatchException("marketingReferralTypeCode must be provided");
        }else if(marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID() == null && uniqueMarketingReferalTypeCodeSet.contains(marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode())){
            //Duplicate check during save operation
            outcome.setOutcome(false);
            outcome.setMessage("("+ marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode() +") "+"marketingReferralTypeCode already exist");
            return outcome;
        }else if(marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID() != null && !marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID().equals("")){
            //Duplicate check during update operation
            Set uniqueHoldReasonNameSetForUpdate = marketingReferalTypeMasterRepositoryExtended.findByMarketingReferalTypeMasterUUIDNot(marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID()).stream().map(x -> x.getMarketingReferralTypeCode()).collect(Collectors.toSet());
            if(uniqueHoldReasonNameSetForUpdate.contains(marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode())){
                outcome.setOutcome(false);
                outcome.setMessage("("+ marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode() +") "+"marketingReferralTypeCode already exist");
                return outcome;
            }
        }

        if(marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode() != null && marketingReferalTypeMasterExtendedDTO.getMarketingReferralTypeCode() != "") {
            if(marketingReferalTypeMasterExtendedDTO.getStatus().equalsIgnoreCase("active") || marketingReferalTypeMasterExtendedDTO.getStatus().equalsIgnoreCase("inActive")) {
                CommonUtilities.dtoTrimmer(marketingReferalTypeMasterExtendedDTO);

                MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = (marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID() == null) ? new MarketingReferalTypeMasterDTO() :
                    (marketingReferalTypeMasterRepositoryExtended.findByMarketingReferalTypeMasterUUID(marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID()) != null ?
                        marketingReferalTypeMasterMapper.toDto(marketingReferalTypeMasterRepositoryExtended.findByMarketingReferalTypeMasterUUID(marketingReferalTypeMasterExtendedDTO.getMarketingReferalTypeMasterUUID())) :
                        new MarketingReferalTypeMasterDTO());

                BeanUtils.copyProperties(marketingReferalTypeMasterExtendedDTO, marketingReferalTypeMasterDTO);

                if (marketingReferalTypeMasterDTO.getMarketingReferalTypeMasterUUID() == null) {
                    marketingReferalTypeMasterDTO.setMarketingReferralTypeId(null);
                    marketingReferalTypeMasterDTO.setMarketingReferalTypeMasterUUID(UUID.randomUUID());
                    marketingReferalTypeMasterDTO.setCreatedDate(LocalDate.now());
                    marketingReferalTypeMasterDTO.setCreatedById(31L);
                    marketingReferalTypeMasterDTO.setCreatedByName("Falguni");
                } else {
                    marketingReferalTypeMasterDTO.setUpdatedDate(LocalDate.now());
                    marketingReferalTypeMasterDTO.setUpdatedById(31L);
                    marketingReferalTypeMasterDTO.setUpdatedByName("Falguni");
                }

                MarketingReferalTypeMasterDTO savedMarketingReferalTypeMasterDTO = marketingReferalTypeMasterMapper.toDto(
                    marketingReferalTypeMasterRepositoryExtended.save(marketingReferalTypeMasterMapper.toEntity(marketingReferalTypeMasterDTO))
                );

                outcome.setData(savedMarketingReferalTypeMasterDTO);
                outcome.setOutcome(true);
                outcome.setMessage("Data Successfully Saved.");

            }
            else{
                outcome.setOutcome(false);
                outcome.setMessage("Status must be active/inactive");
            }
        }
        return outcome;
    }

    @Override
    public ServiceOutcome getAllMarketingReferalTypeMasterInfo() {
        List<MarketingReferalTypeMaster> marketingReferalTypeMasterList = marketingReferalTypeMasterRepositoryExtended.findByStatusIgnoreCase("active");
        return new ServiceOutcome(marketingReferalTypeMasterList,marketingReferalTypeMasterList!=null?true:false,marketingReferalTypeMasterList!=null?"Data Successfully fetched":"Data Not Found.");
    }

    @Override
    public ServiceOutcome getMarketingReferalTypeMasterInfoByUUID(UUID marketingReferalTypeMasterUUID) {
        MarketingReferalTypeMaster marketingReferalTypeMaster = marketingReferalTypeMasterRepositoryExtended.findByMarketingReferalTypeMasterUUID(marketingReferalTypeMasterUUID);
        return new ServiceOutcome(marketingReferalTypeMaster,marketingReferalTypeMaster!=null?true:false,marketingReferalTypeMaster!=null?"Data Successfully fetched":"Data Not Found.");
    }
}
