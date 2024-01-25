package com.sunknowledge.dme.rcm.service.pricetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.repository.priceTable.InsuranceMasterRepo;

import java.util.List;
import java.util.Map;

@Service
public class PriceTableServiceImpl implements PriceTableService {

	@Autowired
	private InsuranceMasterRepo insuranceMasterRepository;

	@Override
	public String getpriceTableData(String insuranceid) {

		InsuranceMaster objinsm = insuranceMasterRepository.getinsuranceDetails(Long.valueOf(insuranceid));

		System.out.println(objinsm.getPriceTableName());

		return objinsm.getPriceTableId()+"-"+objinsm.getPriceTableName();
	}

}
