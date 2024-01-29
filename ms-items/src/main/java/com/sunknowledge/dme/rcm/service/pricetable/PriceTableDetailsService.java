package com.sunknowledge.dme.rcm.service.pricetable;

import java.util.List;
import java.util.Map;

public interface PriceTableDetailsService {

	String getPricingDetails(String item_id, String price_Type, String period);

    List<Map<String, Object>> getPriceMasterForDropdown();

}
