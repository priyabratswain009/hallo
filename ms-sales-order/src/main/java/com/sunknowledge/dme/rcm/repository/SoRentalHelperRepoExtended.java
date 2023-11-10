package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface SoRentalHelperRepoExtended extends SoRentalHelperRepository {

	@Query(value = "CALL so.so_rental_helper_creation(:salesOrderId)")
	Flux<T> getRentalHelperData(@Param("salesOrderId") Long salesOrderId);

}
