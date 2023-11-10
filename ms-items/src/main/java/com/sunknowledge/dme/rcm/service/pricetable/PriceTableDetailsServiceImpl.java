package com.sunknowledge.dme.rcm.service.pricetable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.pricetable.PriceDetailsRepo;

@Service
public class PriceTableDetailsServiceImpl implements PriceTableDetailsService {
	
	@Autowired
	PriceDetailsRepo priceDetailsRepository;

	@Override
	public String getPricingDetails(String item_id, String price_Type, String period) {String data = "", priceTableId = "";

	PriceDetails objPriceDetails = new PriceDetails();
	
	List<PriceDetails> objList = priceDetailsRepository.getallPriceDetails();
	for(PriceDetails p:objList) {
		System.out.println(p.getHcpcs());
	}

	if (price_Type == "Rental") {
		objPriceDetails = priceDetailsRepository.getpricedetailsforRent(Long.valueOf(priceTableId),
				Long.valueOf(item_id), price_Type, Long.valueOf(period));
	} else {
		objPriceDetails = priceDetailsRepository.getpricedetailsforPurch(Long.valueOf(priceTableId),
				Long.valueOf(item_id), price_Type);
	}

	data = objPriceDetails.getPriceDetailsId() + String.valueOf(objPriceDetails.getEffectiveStartDate())
			+ String.valueOf(objPriceDetails.getEffectiveEndDate());

	return data;}

}
