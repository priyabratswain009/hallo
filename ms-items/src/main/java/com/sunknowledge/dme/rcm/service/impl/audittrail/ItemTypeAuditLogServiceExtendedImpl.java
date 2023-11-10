package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemTypeAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemTypeAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemTypeIdAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemTypeServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemTypeAuditLogServiceExtendedImpl")
public class ItemTypeAuditLogServiceExtendedImpl implements ItemTypeAuditLogServiceExtended {

    @Autowired
    ItemTypeAuditLogQueryHandler itemTypeAuditLogQueryHandler;

    @Override
    public ItemTypeAuditLogDTO save(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        return null;
    }

    @Override
    public ItemTypeAuditLogDTO update(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemTypeAuditLogDTO> partialUpdate(ItemTypeAuditLogDTO itemTypeAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemTypeAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemTypeAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AuditLogReportDTO> getItemTypeAuditLog(ItemTypeIdAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery queryObj =
                new ItemTypeAuditLogByItemTypeIdAndUserIdAndDateQuery();
            queryObj.setItemTypeId(auditLogParameterDTO.getItemTypeId());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemTypeAuditLogQueryHandler.
                getItemTypeAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItmTypId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Type"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_type_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemTypeId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_type_id");
            updateKeys.put("noValue", auditLogParameterDTO.getItemTypeId());
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
