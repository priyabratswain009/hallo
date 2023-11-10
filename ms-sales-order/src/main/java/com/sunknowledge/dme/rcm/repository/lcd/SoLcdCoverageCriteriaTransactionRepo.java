package com.sunknowledge.dme.rcm.repository.lcd;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import com.sunknowledge.dme.rcm.repository.SoLcdCoverageCriteriaTransactionRepository;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SoLcdCoverageCriteriaTransactionRepo extends SoLcdCoverageCriteriaTransactionRepository {
    @Query(value = "select * from t_so_lcd_coverage_criteria_transaction where so_id = :salesOrderId and item_id = :itemId and coverage_criteria_id = :coverageCriteriaId")
    Mono<SoLcdCoverageCriteriaTransaction> getCoverageCriteriaOnSalesOrderItemNCoverageCriteria(@Param("salesOrderId") Long salesOrderId, @Param("itemId") Long itemId, @Param("coverageCriteriaId") Long docRefId);

    @Query(value = "select * from t_so_lcd_coverage_criteria_transaction tslcct where tslcct.so_id = :salesOrderId and tslcct.hcpcs_code = :hcpcsCode")
    Flux<SoLcdCoverageCriteriaTransactionDTO> getSoLcdCoverageCriteriaTransactionsOnSalesOrderHcpcsCode(@Param("salesOrderId") Long salesOrderId, @Param("hcpcsCode") String hcpcsCode);
}
