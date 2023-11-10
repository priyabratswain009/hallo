package com.sunknowledge.dme.rcm.repository.pricetable;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.repository.PriceDetailsRepository;

public interface PriceDetailsRepo extends PriceDetailsRepository {

	@Query(value = "From PriceDetails where price_table_id=:priceTableId and item_id=:itemId and price_type=:priceType and :period between price_option_billing_period_start and price_option_billing_period_end")
	PriceDetails getpricedetailsforRent(@Param("priceTableId") Long priceTableId,@Param("itemId") Long itemId,@Param("priceType") String priceType,@Param("period") Long period);

	@Query(value = "From PriceDetails where price_table_id=:priceTableId and item_id=:itemId and price_type=:priceType")
	PriceDetails getpricedetailsforPurch(@Param("priceTableId") Long priceTableId,@Param("itemId") Long itemId,@Param("priceType") String priceType);

	@Query(value="From PriceDetails")
	List<PriceDetails> getallPriceDetails();

    List<PriceDetails> findByItemIdAndStatusIgnoreCase(Long itemId, String active);
}
