package com.sunknowledge.dme.rcm.service.pricetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sunknowledge.dme.rcm.domain.PriceMaster;
import com.sunknowledge.dme.rcm.repository.PriceMasterRepository;
import com.sunknowledge.dme.rcm.repository.pricetable.PriceMasterRepositoryExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.pricetable.PriceDetailsRepo;

@Service
public class PriceTableDetailsServiceImpl implements PriceTableDetailsService {

	@Autowired
	PriceDetailsRepo priceDetailsRepository;
    @Autowired
    PriceMasterRepositoryExtended priceMasterRepositoryExtended;

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

    public List<Map<String, Object>> getPriceMasterForDropdown() {
        List<PriceMaster> posMasterList = priceMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", p.getPriceTableId());
                map.put("title", p.getPriceTableName());
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }
}
