package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.items.ItemInventoryStatusRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.InventoryInputDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.InventoryStatusByItemIdAndItemLocationIdInputDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusZeroQtyParameterDTO;
import com.sunknowledge.dme.rcm.service.itemothers.SoItemTransactionDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemInventoryStatusServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemInventoryStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
public class ItemInventoryStatusServiceExtendedImpl implements ItemInventoryStatusServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemInventoryStatusServiceExtendedImpl.class);
    @Autowired
    ItemInventoryStatusRepositoryExtended itemInventoryStatusRepositoryExtended;
    @Autowired
    ItemInventoryStatusMapper itemInventoryStatusMapper;
    @Autowired
    SoItemTransactionDetailsServiceExtended soItemTransactionDetailsServiceExtended;
    @Override
    public List<ItemInventoryStatusDTO> getItemInventoryStatusByItemId(Long itemId) {
        return itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId,"active"));
    }

    @Override
    public ResponseDTO saveInventoryStatusManualQtyUpdate(ItemInventoryStatusParameterDTO itemInventoryStatusParameterDTO) {
        ItemInventoryStatusDTO itemInventoryStatusDTO = (itemInventoryStatusParameterDTO.getItemInventoryStatusId() == null ||
            itemInventoryStatusParameterDTO.getItemInventoryStatusId() == 0) ? new ItemInventoryStatusDTO() :
            (itemInventoryStatusRepositoryExtended.findById(itemInventoryStatusParameterDTO.getItemInventoryStatusId()).isPresent() ?
                itemInventoryStatusMapper.toDto(itemInventoryStatusRepositoryExtended.findById(itemInventoryStatusParameterDTO.getItemInventoryStatusId()).get()) :
                new ItemInventoryStatusDTO());
        BeanUtils.copyProperties(itemInventoryStatusParameterDTO, itemInventoryStatusDTO);
        if (itemInventoryStatusDTO.getItemInventoryStatusId() == null || itemInventoryStatusDTO.getItemInventoryStatusId() == 0) {
            itemInventoryStatusDTO.setItemInventoryStatusId(null);
            itemInventoryStatusDTO.setCreatedDate(LocalDate.now());
            itemInventoryStatusDTO.setCreatedById(1L);
            itemInventoryStatusDTO.setCreatedByName("Abhijit");
            itemInventoryStatusDTO.setItemInventoryStatusUuid(UUID.randomUUID());
        } else {
            itemInventoryStatusDTO.setUpdatedDate(LocalDate.now());
            itemInventoryStatusDTO.setUpdatedById(1L);
            itemInventoryStatusDTO.setUpdatedByName("Abhijit");
        }
        CommonUtilities.dtoTrimmer(itemInventoryStatusDTO);
        ItemInventoryStatusDTO savedItemInventoryStatusDTO = itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.save(itemInventoryStatusMapper.toEntity(itemInventoryStatusDTO))
        );

        return (new ResponseDTO(Boolean.TRUE,"Successfully Saved",List.of(savedItemInventoryStatusDTO)));
    }

    @Override
    public ResponseDTO saveInventoryStatusByZeroQtyUpdate(ItemInventoryStatusZeroQtyParameterDTO itemInventoryStatusParameterDTO) {
        ItemInventoryStatusDTO itemInventoryStatusDTO = (itemInventoryStatusParameterDTO.getItemInventoryStatusId() == null ||
            itemInventoryStatusParameterDTO.getItemInventoryStatusId() == 0) ? new ItemInventoryStatusDTO() :
            (itemInventoryStatusRepositoryExtended.findById(itemInventoryStatusParameterDTO.getItemInventoryStatusId()).isPresent() ?
                itemInventoryStatusMapper.toDto(itemInventoryStatusRepositoryExtended.findById(itemInventoryStatusParameterDTO.getItemInventoryStatusId()).get()) :
                new ItemInventoryStatusDTO());
        BeanUtils.copyProperties(itemInventoryStatusParameterDTO, itemInventoryStatusDTO);
        if (itemInventoryStatusDTO.getItemInventoryStatusId() == null || itemInventoryStatusDTO.getItemInventoryStatusId() == 0) {
            itemInventoryStatusDTO.setItemInventoryStatusId(null);
            itemInventoryStatusDTO.setCreatedDate(LocalDate.now());
            itemInventoryStatusDTO.setCreatedById(1L);
            itemInventoryStatusDTO.setCreatedByName("Abhijit");
            itemInventoryStatusDTO.setComittedQty(0L);
            itemInventoryStatusDTO.setInorderQty(0L);
            itemInventoryStatusDTO.setOnhandQty(0L);
            itemInventoryStatusDTO.setOnrentQty(0L);
            itemInventoryStatusDTO.setItemInventoryStatusUuid(UUID.randomUUID());
        } else {
            itemInventoryStatusDTO.setUpdatedDate(LocalDate.now());
            itemInventoryStatusDTO.setUpdatedById(1L);
            itemInventoryStatusDTO.setUpdatedByName("Abhijit");
        }
        CommonUtilities.dtoTrimmer(itemInventoryStatusDTO);
        ItemInventoryStatusDTO savedItemInventoryStatusDTO = itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.save(itemInventoryStatusMapper.toEntity(itemInventoryStatusDTO))
        );
        return (new ResponseDTO(Boolean.TRUE,"Successfully Saved",List.of(savedItemInventoryStatusDTO)));
    }

    @Override
    public ItemInventoryStatusDTO getInventoryStatusByItemInventoryStatusId(Long itemInventoryStatusId) {
        ItemInventoryStatus obj = itemInventoryStatusRepositoryExtended.findByItemInventoryStatusId(itemInventoryStatusId);
        return obj!=null?itemInventoryStatusMapper.toDto(obj):null;
    }

    @Override
    public List<ItemInventoryStatusDTO> getInventoryStatusByLocationId(Long locationId) {
        return itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.findByItemLocationIdAndStatusIgnoreCase(locationId, "active")
        );
    }

    @Override
    public List<ItemInventoryStatusDTO> getAllInventoryStatusData() {
        return itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.findAll()
        );
    }

    @Override
    public List<ItemInventoryStatusDTO> getInventoryStatusByStatus(String status) {
        return itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.findByStatusIgnoreCase("active")
        );
    }

    public List<ItemInventoryStatusDTO> getInventoryByItemIdAndLocationId(Long itemId, Long locationId){
        return itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.findByItemIdAndItemLocationIdAndStatusIgnoreCase(itemId, locationId, "active")
        );
    }

    @Override
    public ServiceOutcome saveInventoryStatusByItemIdAndItemLocationId(InventoryInputDTO inputDTOs) throws Exception {
        List<ItemInventoryStatusDTO> itemInventoryStatusDTOs = new ArrayList<>();
        //InventoryStatusByItemIdAndItemLocationIdInputDTO
        System.out.println("===========inputDTOs==========="+inputDTOs);
        List<InventoryStatusByItemIdAndItemLocationIdInputDTO> itemInventoryStatusDTOList = inputDTOs.getInventoryStatusByItemIdAndItemLocationIdInputDTO();
        for(InventoryStatusByItemIdAndItemLocationIdInputDTO inputDTO : itemInventoryStatusDTOList){
            List<ItemInventoryStatusDTO> itemInventoryStatusList = itemInventoryStatusMapper.toDto(
                itemInventoryStatusRepositoryExtended.findByItemIdAndItemLocationIdAndStatusIgnoreCase((long) inputDTO.getItemId(), (long) inputDTO.getItemLocationId(), "active")
            );

            if(itemInventoryStatusList.size() > 0){
                ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusList.get(0);
                System.out.println("=======itemInventoryStatusDTO======="+itemInventoryStatusDTO);
                Long comittedQty = 0L;
                Long inorderQty = 0L;
                if(itemInventoryStatusDTO.getItemInventoryStatusId()!=null && itemInventoryStatusDTO.getItemInventoryStatusId()!= 0){
                    Long onHandQty = itemInventoryStatusDTO.getOnhandQty()!= null && itemInventoryStatusDTO.getOnhandQty()!= 0 ? itemInventoryStatusDTO.getOnhandQty() : 0L;
                    inorderQty = itemInventoryStatusDTO.getInorderQty()!= null && itemInventoryStatusDTO.getInorderQty()!= 0 ? itemInventoryStatusDTO.getInorderQty() : 0L;
                    comittedQty = itemInventoryStatusDTO.getComittedQty()!= null && itemInventoryStatusDTO.getComittedQty()!= 0 ? itemInventoryStatusDTO.getComittedQty() : 0L;
                    System.out.println("ItemQty:: "+inputDTO.getItemQty()+", onHandQty:: "+onHandQty+", inorderQty:: "+inorderQty+", comittedQty:: "+comittedQty);
                    /*if((inputDTO.getItemQty() > (onHandQty - (inorderQty + comittedQty))) || (inputDTO.getItemQty() > inorderQty)){
                        return (new ServiceOutcome(null, false,"Insufficient Quantity."));
                        //throw new Exception("Insufficient Quantity.");
                    }else{*/
                        comittedQty = comittedQty+inputDTO.getItemQty();
                        inorderQty = inorderQty-inputDTO.getItemQty();

                        itemInventoryStatusDTO.setComittedQty(comittedQty);
                        itemInventoryStatusDTO.setInorderQty(inorderQty);
                        itemInventoryStatusDTO.setUpdatedDate(LocalDate.now());
                        itemInventoryStatusDTO.setUpdatedById(31L);
                        itemInventoryStatusDTO.setUpdatedByName("Falguni");
                        ItemInventoryStatusDTO savedItemInventoryStatusDTO = itemInventoryStatusMapper.toDto(
                            itemInventoryStatusRepositoryExtended.save(itemInventoryStatusMapper.toEntity(itemInventoryStatusDTO))
                        );
                        itemInventoryStatusDTOs.add(savedItemInventoryStatusDTO);
                        System.out.println("======itemInventoryStatusDTOs======"+itemInventoryStatusDTOs);
                        //return (new ServiceOutcome(savedItemInventoryStatusDTO, true, "Successfully Saved"));
                    //}
                }else{
                    return (new ServiceOutcome(null, false,"Data Error."));
                }
            }else{
                return (new ServiceOutcome(null, false,"Item Id And Item Location Id do not Exist."));
            }
        }
        return (new ServiceOutcome(itemInventoryStatusDTOs.size() > 0 ? itemInventoryStatusDTOs : null, itemInventoryStatusDTOs.size() > 0 ? true : false, itemInventoryStatusDTOs.size() > 0 ? "Successfully Saved" : "Failed To Update"));
    }

    @Override
    public ServiceOutcome saveInventoryStatusByItemIdAndItemLocationIdForDelivered(InventoryInputDTO inputDTOs) throws Exception {
        List<ItemInventoryStatusDTO> itemInventoryStatusDTOs = new ArrayList<>();
        List<SoItemTransactionDetailsDTO> soItemTransactionDetailsDTOS = new ArrayList<>();
        //InventoryStatusByItemIdAndItemLocationIdInputDTO
        System.out.println("===========inputDTOs==========="+inputDTOs);
        List<InventoryStatusByItemIdAndItemLocationIdInputDTO> itemInventoryStatusDTOList = inputDTOs.getInventoryStatusByItemIdAndItemLocationIdInputDTO();
        for(InventoryStatusByItemIdAndItemLocationIdInputDTO inputDTO : itemInventoryStatusDTOList){
            List<ItemInventoryStatusDTO> itemInventoryStatusList = itemInventoryStatusMapper.toDto(
                itemInventoryStatusRepositoryExtended.findByItemIdAndItemLocationIdAndStatusIgnoreCase((long) inputDTO.getItemId(), (long) inputDTO.getItemLocationId(), "active")
            );
            SoItemTransactionDetailsDTO soItemTransactionDetails = soItemTransactionDetailsServiceExtended.getSOItemTransactionDetailsBysONoAndItemId(inputDTO.getSoNo(),inputDTO.getItemId());

            if(itemInventoryStatusList.size() > 0){
                ItemInventoryStatusDTO itemInventoryStatusDTO = itemInventoryStatusList.get(0);
                System.out.println("=======itemInventoryStatusDTO======="+itemInventoryStatusDTO);
                Long comittedQty = 0L;
                Long inorderQty = 0L;
                Long onRentQty = 0L;
                Long onHandQty = 0L;
                Long onBackorder = 0L;
                if(itemInventoryStatusDTO.getItemInventoryStatusId()!=null && itemInventoryStatusDTO.getItemInventoryStatusId()!= 0){
                    onHandQty = itemInventoryStatusDTO.getOnhandQty()!= null && itemInventoryStatusDTO.getOnhandQty()!= 0 ? itemInventoryStatusDTO.getOnhandQty() : 0L;
                    inorderQty = itemInventoryStatusDTO.getInorderQty()!= null && itemInventoryStatusDTO.getInorderQty()!= 0 ? itemInventoryStatusDTO.getInorderQty() : 0L;
                    comittedQty = itemInventoryStatusDTO.getComittedQty()!= null && itemInventoryStatusDTO.getComittedQty()!= 0 ? itemInventoryStatusDTO.getComittedQty() : 0L;
                    onRentQty = itemInventoryStatusDTO.getOnrentQty()!= null && itemInventoryStatusDTO.getOnrentQty()!= 0 ? itemInventoryStatusDTO.getOnrentQty() : 0L;
                    onBackorder = itemInventoryStatusDTO.getOnBackorder()!= null && itemInventoryStatusDTO.getOnBackorder()!= 0 ? itemInventoryStatusDTO.getOnBackorder() : 0L;
                    System.out.println("ItemQty:: "+inputDTO.getItemQty()+", onHandQty:: "+onHandQty+", inorderQty:: "+inorderQty+", comittedQty:: "+comittedQty+", onBackorder:: "+onBackorder);
                    if(inputDTO.getIsDropshipFlag().equalsIgnoreCase("Y")){
                        if(onBackorder >= inputDTO.getItemQty()) {
                            onBackorder = onBackorder - inputDTO.getItemQty();
                        }else{
                            return (new ServiceOutcome(null, false, "Insufficient Quantity."));
                        }
                    }else {
                        if (inputDTO.getItemQty() > (onHandQty - (inorderQty + comittedQty)) || comittedQty < inputDTO.getItemQty()) {
                            return (new ServiceOutcome(null, false, "Insufficient Quantity."));
                        } else {
                            comittedQty = comittedQty - inputDTO.getItemQty();
                            onHandQty = onHandQty - inputDTO.getItemQty();

                            if (inputDTO.getSaleType() != null && inputDTO.getSaleType().equalsIgnoreCase("rental")) {
                                onRentQty = (onRentQty + inputDTO.getItemQty());
                            }
                        }
                    }
                    itemInventoryStatusDTO.setOnrentQty(onRentQty);
                    itemInventoryStatusDTO.setComittedQty(comittedQty);
                    itemInventoryStatusDTO.setInorderQty(inorderQty);
                    itemInventoryStatusDTO.setOnhandQty(onHandQty);
                    itemInventoryStatusDTO.setOnBackorder(onBackorder.intValue());
                    itemInventoryStatusDTO.setUpdatedDate(LocalDate.now());
                    itemInventoryStatusDTO.setUpdatedById(41L);
                    itemInventoryStatusDTO.setUpdatedByName("Abhay Api");

                    itemInventoryStatusDTOs.add(itemInventoryStatusDTO);
                    System.out.println("======itemInventoryStatusDTOs======" + itemInventoryStatusDTOs);
                    if(soItemTransactionDetails != null ) {
                        soItemTransactionDetails.setItemTransactionStatus("Delivered");
                        soItemTransactionDetailsDTOS.add(soItemTransactionDetails);
                    }else{
                        return (new ServiceOutcome(null, false,"Data Error."));
                    }
                }else{
                    return (new ServiceOutcome(null, false,"Data Error."));
                }
            }else{
                return (new ServiceOutcome(null, false,"Item Id And Item Location Id do not Exist."));
            }
        }
        List<ItemInventoryStatusDTO> savedItemInventoryStatusDTOs = itemInventoryStatusMapper.toDto(
            itemInventoryStatusRepositoryExtended.saveAll(itemInventoryStatusMapper.toEntity(itemInventoryStatusDTOs))
        );
        List<SoItemTransactionDetailsDTO> savedSoItemTransactionDetailsDTOs = new ArrayList<>();
        if(savedItemInventoryStatusDTOs.size()>0 && soItemTransactionDetailsDTOS.size()>0){
            savedSoItemTransactionDetailsDTOs = soItemTransactionDetailsServiceExtended.saveAll(soItemTransactionDetailsDTOS);
        }
        return (new ServiceOutcome(savedItemInventoryStatusDTOs.size() > 0 && savedSoItemTransactionDetailsDTOs.size() >0
            ? savedItemInventoryStatusDTOs : null, savedItemInventoryStatusDTOs.size() > 0 && savedSoItemTransactionDetailsDTOs.size() >0
            ? true : false, savedItemInventoryStatusDTOs.size() > 0 && savedSoItemTransactionDetailsDTOs.size() >0
            ? "Successfully Saved" : "Failed To Update"));
    }

    @Override
    public ResponseDTO setInventoryStatusById(Long itemInventoryStatusId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                ItemInventoryStatus itemInventoryStatus = itemInventoryStatusRepositoryExtended.findByItemInventoryStatusId(itemInventoryStatusId);
                itemInventoryStatus.setStatus(status);
                itemInventoryStatusRepositoryExtended.save(itemInventoryStatus);
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemInventoryStatus)));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }

    @Override
    public ItemInventoryStatusDTO save(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        return null;
    }

    @Override
    public ItemInventoryStatusDTO update(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        return null;
    }

    @Override
    public Optional<ItemInventoryStatusDTO> partialUpdate(ItemInventoryStatusDTO itemInventoryStatusDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemInventoryStatusDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemInventoryStatusDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
