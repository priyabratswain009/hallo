package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.StockAdjustmentAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.StockAdjustmentAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.itemothers.StockAdjustmentServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("stockAdjustmentAuditLogServiceExtendedImpl")
public class StockAdjustmentAuditLogServiceExtendedImpl implements StockAdjustmentAuditLogServiceExtended {
    @Override
    public StockAdjustmentAuditLogDTO save(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        return null;
    }

    @Override
    public StockAdjustmentAuditLogDTO update(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<StockAdjustmentAuditLogDTO> partialUpdate(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<StockAdjustmentAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<StockAdjustmentAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    StockAdjustmentServiceExtended stockAdjustmentServiceExtended;
    @Autowired
    StockAdjustmentAuditLogQueryHandler stockAdjustmentAuditLogQueryHandler;

    @Override
    public List<AuditLogReportDTO> getStockAdjustmentAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> stockAdjustmentIdList = stockAdjustmentServiceExtended.getStockAdjustmentByItemId(itemId)
                .stream().map(StockAdjustmentDTO::getStockAdjustmentId).collect(Collectors.toList());


            StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new StockAdjustmentAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setStockAdjustmentIdList(stockAdjustmentIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setItemId(itemId);
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = stockAdjustmentAuditLogQueryHandler.
                getStockAdjustmentAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getStckAdjstentId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Stock Adjustment"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "stock_adjustment_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "stock_adjustment_id");
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
