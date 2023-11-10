package com.sunknowledge.dme.rcm.repository.itemothers;

import com.sunknowledge.dme.rcm.domain.StockTransfer;
import com.sunknowledge.dme.rcm.repository.StockTransferRepository;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface StockTransferRepositoryExtended extends StockTransferRepository {
    List<StockTransfer> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<StockTransfer> findByItemId(Long itemId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.stocktransferinitiation(?1," +
        "?2, ?3, ?4, ?5," +
        "?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    Object initiateStockTransfer(Long itemId,
                                 Long sourceLocationId,
                                 Long targetLocationId,
                                 Long branchId,
                                 String branchName,
                                 Long itemQty,
                                 String whetherSerialised,
                                 String serialNos,
                                 Long userId,
                                 String userName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.stocktransfercompletion(?1, ?2, ?3, ?4," +
        "?5, ?6, ?7)", nativeQuery = true)
    void completeStockTransfer(Long itemId,
                               Long transferId,
                               Long itemQty,
                               String whetherSerialised,
                               String serialNos,
                               Long userId,
                               String userName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.stocktransfercancel(?1, ?2, ?3)", nativeQuery = true)
    void cancelStockTransfer(Long transferId,
                             Long userId,
                             String userName);

    List<StockTransfer> findByStockTransferId(Long transferId);

    List<StockTransfer> findBySourceLoactionIdAndStatusIgnoreCase(Long valueOf, String active);

    List<StockTransfer> findByTransferDateGreaterThanEqualAndTransferDateLessThanEqualAndTransferStatus(LocalDate fromDate, LocalDate toDate, String stockTransferStatus);
}
