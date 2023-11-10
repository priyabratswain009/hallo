package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderItemsReceivedAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderItemsReceivedAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsReceivedServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("purchaseOrderItemsReceivedAuditLogServiceExtendedImpl")
public class PurchaseOrderItemsReceivedAuditLogServiceExtendedImpl implements PurchaseOrderItemsReceivedAuditLogServiceExtended {
    @Override
    public PurchaseOrderItemsReceivedAuditLogDTO save(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO) {
        return null;
    }

    @Override
    public PurchaseOrderItemsReceivedAuditLogDTO update(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedAuditLogDTO> partialUpdate(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderItemsReceivedAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsReceivedAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    PurchaseOrderItemsReceivedServiceExtended purchaseOrderItemsReceivedServiceExtended;
    @Autowired
    PurchaseOrderItemsReceivedAuditLogQueryHandler purchaseOrderItemsReceivedAuditLogQueryHandler;
    @Override
    public List<AuditLogReportDTO> getPurchaseOrderItemsReceivedAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> poItemReceivedIdList = purchaseOrderItemsReceivedServiceExtended.getPurchaseOrderItemsReceivedByItemId(itemId)
                .stream().map(PurchaseOrderItemsReceivedDTO::getPoItemReceivedId).collect(Collectors.toList());


            PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new PurchaseOrderItemsReceivedAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setPOItemsReceivedIdList(poItemReceivedIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setItemId(itemId);
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = purchaseOrderItemsReceivedAuditLogQueryHandler.
                getPurchaseOrderItemsReceivedAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getPoItmRecivedId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Purchase Order Items Received"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "po_item_received_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "po_item_received_id");
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
