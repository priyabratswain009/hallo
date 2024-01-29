package com.sunknowledge.dme.rcm.repository.Availity;

import com.sunknowledge.dme.rcm.domain.PayerMaster;
import com.sunknowledge.dme.rcm.repository.PayerMasterRepository;
import org.springframework.data.r2dbc.repository.Query;


import java.util.List;

public interface PayerMasterRepo extends PayerMasterRepository{

	@Query(value="From PayerMaster")
	List<PayerMaster> getallPayers();
}
