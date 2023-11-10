package com.sunknowledge.dme.rcm.repository.itemothers;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;

import java.util.Optional;

public interface SoItemTransactionDetailsRepositoryExtended extends SoItemTransactionDetailsRepository {
    Optional<SoItemTransactionDetails> findBySalesOrderNoAndItemIdAndStatusIgnoreCase(String soNo, long itemId, String active);
}
