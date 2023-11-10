package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import com.sunknowledge.dme.rcm.repository.SoLcdDocRefTransactionRepository;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SoLcdDocRefTransactionRepo extends SoLcdDocRefTransactionRepository {
    @Query(value = "select * from t_so_lcd_doc_ref_transaction where so_id = :salesOrderId and item_id = :itemId and doc_ref_id = :docRefId")
    Mono<SoLcdDocRefTransaction> getDocumentReferenceOnSalesOrderItemNDocRef(@Param("salesOrderId") Long salesOrderId, @Param("itemId") Long itemId, @Param("docRefId") Long docRefId);

    @Query(value = "select * from t_so_lcd_doc_ref_transaction tsldrt where tsldrt.so_id = :salesOrderId and tsldrt.hcpcs_code = :hcpcsCode")
    Flux<SoLcdDocRefTransactionDTO> getSoLcdDocRefTransactionsOnSalesOrderHcpcsCode(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);
}
