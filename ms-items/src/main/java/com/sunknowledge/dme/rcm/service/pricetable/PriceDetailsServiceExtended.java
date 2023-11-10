package com.sunknowledge.dme.rcm.service.pricetable;

import com.sunknowledge.dme.rcm.service.PriceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;

import java.util.List;

public interface PriceDetailsServiceExtended extends PriceDetailsService {
    List<PriceDetailsDTO> getPriceDetailsByItemId(Long itemId);
}
