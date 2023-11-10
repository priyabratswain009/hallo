package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemLocationAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.ItemLocationAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemLocationAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemLocationAuditLogServiceExtendedImpl")
public class ItemLocationAuditLogServiceExtendedImpl implements ItemLocationAuditLogServiceExtended {
    @Override
    public ItemLocationAuditLogDTO save(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        return null;
    }

    @Override
    public ItemLocationAuditLogDTO update(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemLocationAuditLogDTO> partialUpdate(ItemLocationAuditLogDTO itemLocationAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemLocationAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemLocationAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
    @Autowired
    ItemLocationAuditLogQueryHandler itemLocationAuditLogQueryHandler;
    @Override
    public List<AuditLogReportDTO> getItemLocationAuditLog(ItemLocationAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery queryObj = new ItemLocationAuditLogByItemLocationIdAndUserIdAndDateQuery();
            queryObj.setItemLocationId(auditLogParameterDTO.getItemLocationId());
            queryObj.setUserId(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemLocationAuditLogQueryHandler.
                getItemLocationAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItmLctionId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Location"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_location_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemLocationId());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_location_id");
            updateKeys.put("noValue", auditLogParameterDTO.getItemLocationId());
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
