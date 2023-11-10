package com.sunknowledge.dme.rcm.service.pricetabledata;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface PriceTableDetailsService {

	String getPriceTableDetails(String soId, String dos, String saleType, String itemId, int period);

    Mono<ResponseDTO>  getModifiersByHcpcsCodeAndItemNo(String hcpcsCode, String itemNo) throws ExecutionException, InterruptedException;

}
