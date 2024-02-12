package com.sunknowledge.dme.rcm.service.impl.stocktransfer;

import com.sunknowledge.dme.rcm.repository.itemothers.StockTransferRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferCompleteParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferInitiateParameterDTO;
import com.sunknowledge.dme.rcm.service.impl.po.PurchaseOrderServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.items.ItemInventoryStatusServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.StockTransferMapper;
import com.sunknowledge.dme.rcm.service.stocktransfer.StockTransferServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StockTransferServiceExtendedImpl<T> implements StockTransferServiceExtended {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderServiceExtendedImpl.class);

    @Autowired
    StockTransferRepositoryExtended stockTransferRepositoryExtended;
    @Autowired
    ItemInventoryStatusServiceExtended itemInventoryStatusServiceExtended;
    @Autowired
    StockTransferMapper stockTransferMapper;

    @Override
    public StockTransferDTO save(StockTransferDTO stockTransferDTO) {
        return null;
    }

    @Override
    public StockTransferDTO update(StockTransferDTO stockTransferDTO) {
        return null;
    }

    @Override
    public Optional<StockTransferDTO> partialUpdate(StockTransferDTO stockTransferDTO) {
        return Optional.empty();
    }

    @Override
    public Page<StockTransferDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StockTransferDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<StockTransferDTO> getStockTransferByItemId(Long itemId) {
        return stockTransferMapper.toDto(stockTransferRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId, "active"));
    }

    public ResponseDTO initiateStockTransfer(StockTransferInitiateParameterDTO stockTransferInitiateParameterDTO) {
        try {
            Long itemId = stockTransferInitiateParameterDTO.getItemId();
            Long sourceLocationId = stockTransferInitiateParameterDTO.getSourceLocationId();
            Long targetLocationId = stockTransferInitiateParameterDTO.getTargetLocationId();
            Long branchId = stockTransferInitiateParameterDTO.getBranchId();
            String branchName = stockTransferInitiateParameterDTO.getBranchName();
            Long itemQty = stockTransferInitiateParameterDTO.getItemQty();
            String whetherSerialised = stockTransferInitiateParameterDTO.getWhetherSerialised().toUpperCase();
            String serialNos = stockTransferInitiateParameterDTO.getSerialNos();
            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name

            List<ItemInventoryStatusDTO> list = itemInventoryStatusServiceExtended.getInventoryByItemIdAndLocationId(itemId, sourceLocationId);

            if (list.size() > 0 && (list.get(0).getOnhandQty() - itemQty) > 0) {
                if (whetherSerialised.equals("Y") && serialNos.split(",").length != itemQty) {
                    return new ResponseDTO(false, "No sufficient serialised item(s).", new ArrayList(), 200);
                }
                else {
                    stockTransferRepositoryExtended.initiateStockTransfer(itemId, sourceLocationId, targetLocationId,branchId,
                        branchName, itemQty,
                        whetherSerialised, serialNos, userId, userName);
                    return new ResponseDTO(true, "Successfully Initiated", new ArrayList(), 200);
                }
            } else {
                return new ResponseDTO(false, "[On_Hand_Qty - Item_Qty] must be greater than 0 (zero)", new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Initiate :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO completeStockTransfer(StockTransferCompleteParameterDTO stockTransferCompleteParameterDTO) {
        try {
            Long itemId = stockTransferCompleteParameterDTO.getItemId();
            Long transferId = stockTransferCompleteParameterDTO.getTransferId();
            Long itemQty = stockTransferCompleteParameterDTO.getItemQty();
            String whetherSerialised = stockTransferCompleteParameterDTO.getWhetherSerialised();
            String serialNos = stockTransferCompleteParameterDTO.getSerialNos();
            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name

            List<StockTransferDTO> list = stockTransferMapper.toDto(
                stockTransferRepositoryExtended.findByStockTransferId(transferId));

            //-------- Miss-match Slnos checking --------
            Long countSlnos = 0L;
            String slnos[] = new String[0];
            if (list.size() > 0 && list.get(0).getSerialNo() != null
                && !list.get(0).getSerialNo().trim().equals("")) {
                slnos = list.get(0).getSerialNo().split(",");
                for (String slno : slnos) {
                    if (serialNos.contains(slno)) {
                        countSlnos++;
                    }
                }
            }
            Boolean matchedSlnoStat = false;
            if (countSlnos == slnos.length)
                matchedSlnoStat = true;

            if (whetherSerialised.equals("Y") && serialNos.split(",").length == itemQty) {
                if (list.size() > 0 && list.get(0).getTransferStatus().equals("Initiated")) {
                    if (whetherSerialised.equals("Y") && matchedSlnoStat == true) {
                        stockTransferRepositoryExtended.completeStockTransfer(itemId, transferId, itemQty,
                            whetherSerialised, serialNos, userId, userName);
                        return new ResponseDTO(true, "Successfully Accepted", new ArrayList(), 200);
                    } else {
                        return new ResponseDTO(false, "Miss-match Serial Nos", new ArrayList(), 200);
                    }
                } else {
                    return new ResponseDTO(false, "Failed! Stock_Transfer has already been Completed/Canceled", new ArrayList(), 200);
                }
            } else {
                return new ResponseDTO(false, "No sufficient serialised item(s)", new ArrayList(), 200);
            }

        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Accept :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO cancelStockTransfer(Long transferId) {
        try {
            Long userId = 1L;  // Get Login User ID
            String userName = "Abhijit Chowdhury"; // Get Login User Name

            List<StockTransferDTO> list = stockTransferMapper.toDto(
                stockTransferRepositoryExtended.findByStockTransferId(transferId));

            if (list.size() > 0 && list.get(0).getTransferStatus().equals("Initiated")) {
                stockTransferRepositoryExtended.cancelStockTransfer(transferId, userId, userName);
                return new ResponseDTO(true, "Successfully Canceled", new ArrayList(), 200);
            } else {
                return new ResponseDTO(false, "Failed! Stock_Transfer has already been Completed/Canceled", new ArrayList(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false, "Failed to Cancel :: Data Error", new ArrayList(), 200);
        }
    }

    public ResponseDTO getStockTransferData(String param, String opType) {
        try {
            if (opType.equals("StockTransferByTransferId")) {
                StockTransferDTO dataObj = stockTransferRepositoryExtended.findById(Long.valueOf(param)).isPresent() ?
                    stockTransferMapper.toDto(stockTransferRepositoryExtended.findById(Long.valueOf(param)).get())
                    : new StockTransferDTO();
                List<StockTransferDTO> listPO = new ArrayList<>();
                listPO.add(dataObj);
                List<T> list = (List<T>) listPO;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("StockTransferByLocationId")) {
                List<StockTransferDTO> dataObj = stockTransferMapper.toDto(
                    stockTransferRepositoryExtended.findBySourceLoactionIdAndStatusIgnoreCase(Long.valueOf(param), "active"));
                List<T> list = (List<T>) dataObj;
                return new ResponseDTO((list.size() > 0) ? true : false,
                    (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                    list, 200);
            } else if (opType.equals("StockTransferByFromDateAndToDateOrStockTransferStatus")) {
                String params[] = param.split(",");
//
                if (params.length == 3) {
                    LocalDate fromDate = LocalDate.parse(params[0]);
                    LocalDate toDate = LocalDate.parse(params[1]);
                    String stockTransferStatus = params[2];
                    List<StockTransferDTO> dataObj = stockTransferMapper.toDto(
                        stockTransferRepositoryExtended.findByTransferDateGreaterThanEqualAndTransferDateLessThanEqualAndTransferStatus(fromDate, toDate, stockTransferStatus));
                    List<T> list = (List<T>) dataObj;
                    return new ResponseDTO((list.size() > 0) ? true : false,
                        (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
                        list, 200);
                } else {
                    return new ResponseDTO(false, "Proper From_Date(yyyy-mm-dd), To_Date(yyyy-mm-dd) and Stock_Transfer_Status must be provided.",
                        new ArrayList(), 200);
                }
            } else {
                return new ResponseDTO(false,
                    "No Data Available",
                    new ArrayList<>(), 200);
            }
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            return new ResponseDTO(false,
                "Data Not Found Data :: Data Error",
                new ArrayList<>(), 200);
        }
    }
}
