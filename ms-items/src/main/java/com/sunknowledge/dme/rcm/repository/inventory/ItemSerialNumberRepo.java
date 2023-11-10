package com.sunknowledge.dme.rcm.repository.inventory;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.repository.ItemSerialNumberRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemSerialNumberRepo extends ItemSerialNumberRepository {
    @Query(value = "FROM ItemSerialNumber WHERE itemId = :itemId AND serialNumber = :serialNumber AND status in('active', 'Active')")
    ItemSerialNumber getItemSerialNumberOnItemNSerialNo(@Param("itemId") Long itemId, @Param("serialNumber") String serialNumber);
}
