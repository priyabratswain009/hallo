package com.sunknowledge.dme.rcm.repository.Availity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;

public interface PayerMasterRepo extends PayerMasterRepository{

	@Query(value="From PayerMaster")
	List<PayerMaster> getallPayers();
}
