package com.sunknowledge.dme.rcm.repository.items;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberRepository;

public interface ItemSerialNumberRepositoryExtended extends ItemSerialNumberRepository {
	List<ItemSerialNumber> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

	@Query(value = "From ItemSerialNumber where serialNumber=:serialNumber and itemId=:itemId")
	ItemSerialNumber getItemSerialNumberByserialNo(@Param("serialNumber") String serialNumber,
			@Param("itemId") Long itemId);

	@Query(value = "From ItemSerialNumber where serialNumber=:serialNumber and itemNo=:itemNo")
	ItemSerialNumber validatePickupSerialNumber(@Param("serialNumber") String serialNumber, @Param("itemNo") String itemNo);
}
