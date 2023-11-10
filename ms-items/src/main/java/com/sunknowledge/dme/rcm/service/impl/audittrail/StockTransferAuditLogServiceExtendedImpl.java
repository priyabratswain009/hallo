package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.StockTransferAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.StockTransferAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockTransferAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.stocktransfer.StockTransferServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("stockTransferAuditLogServiceExtendedImpl")
public class StockTransferAuditLogServiceExtendedImpl implements StockTransferAuditLogServiceExtended {
    @Override
    public StockTransferAuditLogDTO save(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        return null;
    }

    @Override
    public StockTransferAuditLogDTO update(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<StockTransferAuditLogDTO> partialUpdate(StockTransferAuditLogDTO stockTransferAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<StockTransferAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StockTransferAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    StockTransferServiceExtended stockTransferServiceExtended;
    @Autowired
    StockTransferAuditLogQueryHandler stockTransferAuditLogQueryHandler;


    @Override
    public List<AuditLogReportDTO> getStockTransferAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> stockTransferIdList = stockTransferServiceExtended.getStockTransferByItemId(itemId)
                .stream().map(StockTransferDTO::getStockTransferId).collect(Collectors.toList());

            StockTransferAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new StockTransferAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setStockTransferIdList(stockTransferIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setItemId(itemId);
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = stockTransferAuditLogQueryHandler.
                getStockTransferAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getStckTrasferId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Stock Transfer"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "stock_transfer_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "stock_transfer_id");
            updateKeys.put("noValue", auditLogParameterDTO.getItemNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auditLogReportDTOList;
    }
}
