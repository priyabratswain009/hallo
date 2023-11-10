package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.PurchaseOrderItemsAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.PurchaseOrderItemsAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderItemsServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("purchaseOrderItemsAuditLogServiceExtendedImpl")
public class PurchaseOrderItemsAuditLogServiceExtendedImpl implements PurchaseOrderItemsAuditLogServiceExtended {
    @Override
    public PurchaseOrderItemsAuditLogDTO save(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        return null;
    }

    @Override
    public PurchaseOrderItemsAuditLogDTO update(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsAuditLogDTO> partialUpdate(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<PurchaseOrderItemsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PurchaseOrderItemsAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    PurchaseOrderItemsServiceExtended purchaseOrderItemsServiceExtended;
    @Autowired
    PurchaseOrderItemsAuditLogQueryHandler purchaseOrderItemsAuditLogQueryHandler;

    @Override
    public List<AuditLogReportDTO> getPurchaseOrderItemsAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> poItemsIdList = purchaseOrderItemsServiceExtended.getPurchaseOrderItemsByItemId(itemId)
                .stream().map(PurchaseOrderItemsDTO::getPoItemsId).collect(Collectors.toList());


            PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery queryObj =
                new PurchaseOrderItemsAuditLogByItemNoAndUserIdAndDateQuery();
            queryObj.setPOItemsIdList(poItemsIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setItemId(itemId);
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = purchaseOrderItemsAuditLogQueryHandler.
                getPurchaseOrderItemsAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getPoItmsId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Purchase Order Items"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "po_items_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "po_items_id");
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
