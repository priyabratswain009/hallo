package com.sunknowledge.dme.rcm.repository.inventory;

import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.repository.StockAdjustmentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StockAdjustmentRepo extends StockAdjustmentRepository {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL stockadjustment(:adjustmenttype, :itemid, :locationid, :branchid, :branchname, :itemqty, :whetherserialised, :serialnos, :lotnos, :mfgdates, :avgprice, :uid, :uname);", nativeQuery = true)
    void processStockAdjustmentOnRequestedParams(@Param("adjustmenttype") String adjustmenttype, @Param("itemid") Long itemid,
                                                 @Param("locationid") Long locationid,
                                                 @Param("branchid") Long branchid,
                                                 @Param("branchname") String branchname,
                                                 @Param("itemqty") Integer itemqty,
                                                 @Param("whetherserialised") String whetherserialised,
                                                 @Param("serialnos") String serialnos,
                                                 @Param("lotnos") String lotnos,
                                                 @Param("mfgdates") String mfgdates,
                                                 @Param("avgprice") Double avgprice,
                                                 @Param("uid") Long uid,
                                                 @Param("uname") String uname);

    @Query(value = "select * from t_stock_adjustment tsa where tsa.location_id = :locationId and tsa.item_id = :itemid and tsa.status = 'Active' order by tsa.adjustment_date desc limit 1", nativeQuery = true)
    StockAdjustment getStockAdjustmentOnLocationNitem(@Param("locationId") Long locationId, @Param("itemid") Long itemid);
}
