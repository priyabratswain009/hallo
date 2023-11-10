package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemGroupAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemGroupAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemGroupIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.items.ItemGroupServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemGroupAuditLogServiceExtendedImpl")
public class ItemGroupAuditLogServiceExtendedImpl implements ItemGroupAuditLogServiceExtended {

    @Autowired
    ItemGroupServiceExtended itemGroupServiceExtended;
    @Autowired
    ItemGroupAuditLogQueryHandler itemGroupAuditLogQueryHandler;
    @Override
    public ItemGroupAuditLogDTO save(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        return null;
    }

    @Override
    public ItemGroupAuditLogDTO update(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemGroupAuditLogDTO> partialUpdate(ItemGroupAuditLogDTO itemGroupAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemGroupAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemGroupAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public List<AuditLogReportDTO> getItemGroupAuditLog(ItemGroupIdAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery queryObj =
                new ItemGroupAuditLogByItemGroupIdAndUserIdAndDateQuery();
            queryObj.setItemGroupId(auditLogParameterDTO.getItemGroupId());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemGroupAuditLogQueryHandler.
                getItemGroupAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItemGrpId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Group"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_group_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemGroupId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_group_id");
            updateKeys.put("noValue", auditLogParameterDTO.getItemGroupId());
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
