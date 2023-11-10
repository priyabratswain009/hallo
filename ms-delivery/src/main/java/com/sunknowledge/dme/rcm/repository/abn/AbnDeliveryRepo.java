package com.sunknowledge.dme.rcm.repository.abn;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.AbnDelivery;
import com.sunknowledge.dme.rcm.repository.AbnDeliveryRepository;

public interface AbnDeliveryRepo extends AbnDeliveryRepository {

	@Query(value = "From AbnDelivery where abnDeliveryId=:abnDeliveryId")
	AbnDelivery getAbnDeliveryByabnDeliveryId(@Param("abnDeliveryId") Long abnDeliveryId);

	@Query(value = "From AbnDelivery where abnAeliveryUuid=:abnAeliveryUuid")
	AbnDelivery getAbnDeliveryByUUID(@Param("abnAeliveryUuid") UUID abnAeliveryUuid);

	@Query(value = "From AbnDelivery where abnDeliveryDataId=:abnDeliveryDataId")
	AbnDelivery getAbnDeliveryByabnDeliveryDataId(@Param("abnDeliveryDataId") Long abnDeliveryDataId);
	
}
