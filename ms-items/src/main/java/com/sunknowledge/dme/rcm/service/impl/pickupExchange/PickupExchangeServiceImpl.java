package com.sunknowledge.dme.rcm.service.impl.pickupExchange;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.items.ItemSerialNumberRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.pickupExchange.ItemInventoryStatusRepo;
import com.sunknowledge.dme.rcm.repository.pickupExchange.SoItemTransactionDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SoItemTransactionDetailsMapper;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickupExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PickupExchangeServiceImpl implements PickupExchangeService {

	@Autowired
	private ItemSerialNumberRepositoryExtended itemSerialNumberRepositoryExtended;
	@Autowired
	private ItemInventoryStatusRepo itemInventoryStatusRepository;
	@Autowired
	private SoItemTransactionDetailsRepo soItemTransactionDetailsRepository;
	@Autowired
	private SoItemTransactionDetailsMapper soItemTransactionDetailsMapper;

	@Override
	public void updateInventoryStatusQtyandserialNo(String itemNo, String pickupSerialNumber,
			String exchangeserialNumber, Long itemId, String itemPickupExchangeType, Long itemLocationId, Long qty) {
		ItemSerialNumber pickupitemSerialNumber = itemSerialNumberRepositoryExtended.getItemSerialNumberByserialNo(pickupSerialNumber, itemId);
        if(pickupitemSerialNumber != null) {
            if (itemPickupExchangeType.equalsIgnoreCase("Pickup")) {
                ItemInventoryStatus itemInventoryStatus = itemInventoryStatusRepository.getItemItemInventoryStatusByitemNoIdLocation(itemNo, itemId, itemLocationId);
                setinventoryDataforPickup(itemInventoryStatus,qty);
                setSerialNumberData(pickupitemSerialNumber, "Available", "No");
            } else if (itemPickupExchangeType.equalsIgnoreCase("Exchange")) {
                ItemSerialNumber exchangeitemSerialNumber = itemSerialNumberRepositoryExtended.getItemSerialNumberByserialNo(exchangeserialNumber, itemId);
                setSerialNumberData(pickupitemSerialNumber, "Available", "No");
                setSerialNumberData(exchangeitemSerialNumber, "Not Available", "Yes");
            }
        }
	}

	public ItemSerialNumber setSerialNumberData(ItemSerialNumber itemSerialNumber, String onHandStatus, String onRentStatus) {
        itemSerialNumber.setOnHandStatus(onHandStatus);
        itemSerialNumber.setOnRentStatus(onRentStatus);
		itemSerialNumberRepositoryExtended.save(itemSerialNumber);
		return itemSerialNumber;
	}

	ItemInventoryStatus setinventoryDataforPickup(ItemInventoryStatus objitemInventoryStatus, Long qty) {

		objitemInventoryStatus.setOnhandQty(objitemInventoryStatus.getOnhandQty() + qty);
		objitemInventoryStatus.setOnrentQty(objitemInventoryStatus.getOnrentQty() - qty);

		itemInventoryStatusRepository.save(objitemInventoryStatus);

		return objitemInventoryStatus;

	}

	@Override
	public boolean validatePickupSerialNumber(String pickupSerialNumber, String itemNo) {
		// TODO Auto-generated method stub
		boolean output = false;
		ItemSerialNumber itemSerialNumber = itemSerialNumberRepositoryExtended
				.validatePickupSerialNumber(pickupSerialNumber, itemNo);

		if (itemSerialNumber != null) {
			output = true;
		}

		return output;
	}

	@Override
	public SoItemTransactionDetailsDTO itemTransactionDetailsDataUpdate(String pickupserialNumber, String exchserialNumber, String exchassetNumber, String itemNo, Long itemId, String sono,
			String pickupExchangeType) {
		// TODO Auto-generated method stub
		SoItemTransactionDetails soItemTransactionDetails = soItemTransactionDetailsRepository.getitemTransactionDetailsDataUpdate(pickupserialNumber, itemNo, itemId, sono);

		if(pickupExchangeType.equalsIgnoreCase("Pickup")) {
			soItemTransactionDetails.setTransactionDate(LocalDate.now());
			soItemTransactionDetails.setItemTransactionStatus("Pickedup");
			soItemTransactionDetails.setUpdatedById(Long.valueOf("7"));
			soItemTransactionDetails.setUpdatedByName("ANDROKTASIAI");
			soItemTransactionDetails.setUpdatedDate(LocalDate.now());

		}else {

			soItemTransactionDetails.setTransactionDate(LocalDate.now());
			soItemTransactionDetails.setItemTransactionStatus("Pickedup");
			soItemTransactionDetails.setUpdatedById(Long.valueOf("7"));
			soItemTransactionDetails.setUpdatedByName("ANDROKTASIAI");
			soItemTransactionDetails.setUpdatedDate(LocalDate.now());

			soItemTransactionDetailsRepository.save(soItemTransactionDetails);

			SoItemTransactionDetails soItemTransactionDetailsnew = new SoItemTransactionDetails();
			soItemTransactionDetailsnew.setSalesOrderNo(soItemTransactionDetails.getSalesOrderNo());
			soItemTransactionDetailsnew.setSaleType(soItemTransactionDetails.getSaleType());
			soItemTransactionDetailsnew.setItemId(soItemTransactionDetails.getItemId());
			soItemTransactionDetailsnew.setItemNo(soItemTransactionDetails.getItemNo());
			soItemTransactionDetailsnew.setItemName(soItemTransactionDetails.getItemName());
			soItemTransactionDetailsnew.setItemUom(soItemTransactionDetails.getItemUom());
			soItemTransactionDetailsnew.setItemQty(soItemTransactionDetails.getItemQty());
			soItemTransactionDetailsnew.setWheatherSerialized(soItemTransactionDetails.getWheatherSerialized());
			soItemTransactionDetailsnew.setSerialNo(exchserialNumber);
			soItemTransactionDetailsnew.setWheatherAssetTagged(soItemTransactionDetails.getWheatherAssetTagged());
			soItemTransactionDetailsnew.setAssetNo(exchassetNumber);
			soItemTransactionDetailsnew.setOriginalDos(soItemTransactionDetails.getOriginalDos());
			soItemTransactionDetailsnew.setBranchId(soItemTransactionDetails.getBranchId());
			soItemTransactionDetailsnew.setLocationId(soItemTransactionDetails.getLocationId());
			soItemTransactionDetailsnew.setLocationName(soItemTransactionDetails.getLocationName());
			soItemTransactionDetailsnew.setStatus(soItemTransactionDetails.getStatus());
			soItemTransactionDetailsnew.setTransactionDate(LocalDate.now());
			soItemTransactionDetailsnew.setTransactionNo(soItemTransactionDetailsRepository.getTransactionNumber());
			soItemTransactionDetailsnew.setCreatedById(Long.valueOf("7"));
			soItemTransactionDetailsnew.setCreatedByName("ANDROKTASIAI");
			soItemTransactionDetailsnew.setCreatedDate(LocalDate.now());
			soItemTransactionDetailsnew.setUpdatedById(null);
			soItemTransactionDetailsnew.setUpdatedDate(null);
			soItemTransactionDetailsnew.setUpdatedByName(null);
			soItemTransactionDetailsnew.setSoItemTransactionDetailsUuid(soItemTransactionDetails.getSoItemTransactionDetailsUuid());
			soItemTransactionDetailsnew.setIsDropship(soItemTransactionDetails.getIsDropship());
			soItemTransactionDetailsnew.setPoNo(soItemTransactionDetails.getPoNo());
			soItemTransactionDetailsnew.setItemTransactionStatus("Delivered");

			soItemTransactionDetailsRepository.save(soItemTransactionDetailsnew);

		}

		return soItemTransactionDetailsMapper.toDto(soItemTransactionDetails);
	}

}
