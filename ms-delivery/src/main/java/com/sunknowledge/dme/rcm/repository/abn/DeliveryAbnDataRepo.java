package com.sunknowledge.dme.rcm.repository.abn;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.repository.DeliveryAbnDataRepository;

public interface DeliveryAbnDataRepo extends DeliveryAbnDataRepository {

	@Query(value = "From DeliveryAbnData where deliveryAbnDataUuid=:deliveryAbnDataUuid")
	DeliveryAbnData getAbnDeliveryByUUID(@Param("deliveryAbnDataUuid") UUID deliveryAbnDataUuid);

}
