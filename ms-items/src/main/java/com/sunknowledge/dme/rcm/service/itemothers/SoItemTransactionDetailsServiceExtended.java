package com.sunknowledge.dme.rcm.service.itemothers;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.service.SoItemTransactionDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;

import java.util.List;

public interface SoItemTransactionDetailsServiceExtended extends SoItemTransactionDetailsService {
    SoItemTransactionDetailsDTO getSOItemTransactionDetailsBysONoAndItemId(String soNo, int itemId);

    List<SoItemTransactionDetailsDTO> saveAll(List<SoItemTransactionDetailsDTO> soItemTransactionDetailsDTOS);
}
