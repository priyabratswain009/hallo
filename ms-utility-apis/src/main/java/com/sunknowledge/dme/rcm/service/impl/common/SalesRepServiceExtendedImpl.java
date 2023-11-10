package com.sunknowledge.dme.rcm.service.impl.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.SalesRep;
import com.sunknowledge.dme.rcm.repository.common.SalesRepRepositoryExtended;
import com.sunknowledge.dme.rcm.service.common.SalesRepServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
import com.sunknowledge.dme.rcm.service.dto.common.SalesRepParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesRepMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@Slf4j
public class SalesRepServiceExtendedImpl implements SalesRepServiceExtended {

    @Autowired
    SalesRepRepositoryExtended salesRepRepositoryExtended;
    @Autowired
    SalesRepMapper salesRepMapper;


    @Override
    public ServiceOutcome saveSalesRep(SalesRepParameterDTO salesRepParameterDTO) {
        ServiceOutcome outcome = new ServiceOutcome();
        if(salesRepParameterDTO.getFirstName() != null && salesRepParameterDTO.getFirstName() != "") {
            if(salesRepParameterDTO.getStatus().equalsIgnoreCase("active") || salesRepParameterDTO.getStatus().equalsIgnoreCase("inActive")) {
                CommonUtilities.dtoTrimmer(salesRepParameterDTO);
                SalesRepDTO salesRepData = (salesRepParameterDTO.getSalesRepUuid() == null) ? new SalesRepDTO() :
                    (salesRepRepositoryExtended.findBySalesRepIdAndStatusIgnoreCase(getIDByUUID(salesRepParameterDTO.getSalesRepUuid()),"active") == null ?
                        new SalesRepDTO() : salesRepMapper.toDto(
                        salesRepRepositoryExtended.findBySalesRepIdAndStatusIgnoreCase(getIDByUUID(salesRepParameterDTO.getSalesRepUuid()),"active")));
                BeanUtils.copyProperties(salesRepParameterDTO, salesRepData);

                if (salesRepData.getSalesRepUuid() == null) {
                    salesRepData.setSalesRepId(null);
                    salesRepData.setSalesRepUuid(UUID.randomUUID());
                    salesRepData.setCreatedDate(LocalDate.now());
                    salesRepData.setCreatedById(1L);
                    salesRepData.setCreatedByName("Abhijit");
                    salesRepData.setSalesRepNo(salesRepRepositoryExtended.getSalesRepNumber());
                } else {
                    salesRepData.setUpdatedDate(LocalDate.now());
                    salesRepData.setUpdatedById(1L);
                    salesRepData.setUpdatedByName("Abhijit");
                }
                SalesRep salesRepSaved = salesRepRepositoryExtended.save(salesRepMapper.toEntity(salesRepData));

                outcome.setData(salesRepSaved);
                outcome.setOutcome(true);
                outcome.setMessage("Data Successfully Saved.");
                return outcome;
            }
            else{
                outcome.setOutcome(false);
                outcome.setMessage("Status must be active/inactive");
                return outcome;
            }
        }else{
            outcome.setOutcome(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public ServiceOutcome getSalesRepByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "salesRepNo" : {
                SalesRepDTO salesRepDTO = salesRepMapper.toDto(salesRepRepositoryExtended.findBySalesRepNo(data.trim()));
                return new ServiceOutcome(salesRepDTO,salesRepDTO!=null?true:false,salesRepDTO!=null?"Data Successfully fetched":"Data Not Found.");
            }
            case "salesRepName" : {
                String[] name = data.trim().split(" ");
                if(data.trim() != "") {
                    String firstName = name[0], middleName = "", lastName = "";
                    middleName = name.length < 3 ? "" : name[1];
                    lastName = name.length > 2 ? name[2] : name.length > 1 ? name[1] : "";
                    List<SalesRep> salesRepList = middleName.equals("") ?
                        salesRepRepositoryExtended.
                            findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCaseAndStatusIgnoreCase(
                                "%" + firstName + "%", "%" + lastName + "%",
                                "active")
                        : salesRepRepositoryExtended.
                        findByFirstNameLikeIgnoreCaseAndMiddleNameLikeIgnoreCaseAndLastNameLikeIgnoreCaseAndStatusIgnoreCase(
                            "%" + firstName + "%", "%" + middleName + "%", "%" + lastName + "%",
                            "active");
                    List<SalesRepDTO> salesRepDTOS = salesRepMapper.toDto(salesRepList);
                    return new ServiceOutcome(salesRepDTOS, !salesRepDTOS.isEmpty(), salesRepDTOS.isEmpty() ? "Data Not Found." : "Data Successfully fetched.");
                }else{
                    return new ServiceOutcome(null, false, "Name Should Not be Blank.");
                }

            }
            case "salesRepUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (data != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<SalesRep> salesRepData = salesRepRepositoryExtended.findById(id);
                return new ServiceOutcome(salesRepData.isEmpty()?null:salesRepData.get(),
                    !salesRepData.isEmpty() ,
                    salesRepData.isEmpty()?"Data Not Found.":"Data Successfully fetched."
                );
            }
            default:{
                return new ServiceOutcome(null,false ,"Please give correct operationType.");
            }
        }
    }

    @Override
    public Long getIDByUUID(UUID d) {
        return salesRepRepositoryExtended.getIDByUUID(d);
    }

    @Override
    public ServiceOutcome getSalesRepInfoByUUID(UUID salesRepUuid) {
        SalesRep salesRep = salesRepRepositoryExtended.findBySalesRepUuid(salesRepUuid);
        return new ServiceOutcome(salesRep,salesRep!=null?true:false,salesRep!=null?"Data Successfully fetched":"Data Not Found.");
    }

    //getSalesRepInfoByName
    @Override
    public ServiceOutcome getSalesRepInfoByName(String name) {
        String fullName = name.replaceAll("\\s+", " ").trim();

        System.out.println("====fullName===="+fullName);

        List<SalesRep> salesRepList = salesRepRepositoryExtended.findByName("%" + fullName + "%");

        System.out.println("====salesRepList===="+salesRepList);

        return new ServiceOutcome(salesRepList,salesRepList!=null && salesRepList.size() > 0 ?true:false,salesRepList!=null?"Data Successfully fetched":"Data Not Found.");
    }

    @Override
    public SalesRepDTO save(SalesRepDTO salesRepDTO) {
        return null;
    }

    @Override
    public SalesRepDTO update(SalesRepDTO salesRepDTO) {
        return null;
    }

    @Override
    public Optional<SalesRepDTO> partialUpdate(SalesRepDTO salesRepDTO) {
        return Optional.empty();
    }

    @Override
    public Page<SalesRepDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<SalesRepDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
