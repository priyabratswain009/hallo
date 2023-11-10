package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemInventoryStatusAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemInventoryStatusAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemInventoryStatusServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemInventoryStatusAuditLogServiceExtendedImpl")
public class ItemInventoryStatusAuditLogServiceExtendedImpl implements ItemInventoryStatusAuditLogServiceExtended {

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemInventoryStatusServiceExtended itemInventoryStatusServiceExtended;
    @Autowired
    ItemInventoryStatusAuditLogQueryHandler itemInventoryStatusAuditLogQueryHandler;
    @Override
    public ItemInventoryStatusAuditLogDTO save(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        return null;
    }

    @Override
    public ItemInventoryStatusAuditLogDTO update(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemInventoryStatusAuditLogDTO> partialUpdate(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemInventoryStatusAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemInventoryStatusAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AuditLogReportDTO> getItemInventoryStatusAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();


            List<Long> itemInventoryStatusIdList = itemInventoryStatusServiceExtended.getItemInventoryStatusByItemId(itemId)
                .stream().map(ItemInventoryStatusDTO::getItemInventoryStatusId).collect(Collectors.toList());

            ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery queryObj = new ItemInventoryStatusAuditLogByItemInventoryIdAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setItemInventoryIdList(itemInventoryStatusIdList);
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemInventoryStatusAuditLogQueryHandler.
                getItemInventoryStatusAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItemInvtoryStausId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Inventory Status"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_id");
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
