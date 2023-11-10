package com.sunknowledge.dme.rcm.service.stocktransfer;

import com.sunknowledge.dme.rcm.service.StockTransferService;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferCompleteParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferInitiateParameterDTO;

import java.util.List;

public interface StockTransferServiceExtended extends StockTransferService {
    List<StockTransferDTO> getStockTransferByItemId(Long itemId);

    ResponseDTO initiateStockTransfer(StockTransferInitiateParameterDTO stockTransferInitiateParameterDTO);

    ResponseDTO completeStockTransfer(StockTransferCompleteParameterDTO stockTransferCompleteParameterDTO);

    ResponseDTO cancelStockTransfer(Long transferId);

    ResponseDTO getStockTransferData(String parameterValue, String opType);
}
