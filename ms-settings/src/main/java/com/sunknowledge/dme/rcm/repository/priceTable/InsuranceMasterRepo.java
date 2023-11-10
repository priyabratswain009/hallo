package com.sunknowledge.dme.rcm.repository.priceTable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.InsuranceMasterRepository;

public interface InsuranceMasterRepo extends InsuranceMasterRepository {

	@Query(value="From InsuranceMaster where insuranceId=:insuranceid")
	InsuranceMaster getinsuranceDetails(@Param("insuranceid") Long insuranceid);
}
