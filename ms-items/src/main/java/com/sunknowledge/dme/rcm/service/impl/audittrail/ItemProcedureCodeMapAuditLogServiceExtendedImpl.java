package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemProcedureCodeMapAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemProcedureCodeMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemProcedureCodeMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemProcedureCodeMapAuditLogServiceExtendedImpl")
public class ItemProcedureCodeMapAuditLogServiceExtendedImpl implements ItemProcedureCodeMapAuditLogServiceExtended {

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemProcedureCodeMapServiceExtended itemProcedureCodeMapServiceExtended;
    @Autowired
    ItemProcedureCodeMapAuditLogQueryHandler itemProcedureCodeMapAuditLogQueryHandler;
    @Override
    public List<AuditLogReportDTO> getItemProcedureCodeMapAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> itemProcedureCodeMapIdList = itemProcedureCodeMapServiceExtended.getItemProcedureCodeMapById(itemId)
                .stream().map(ItemProcedureCodeMapDTO::getItemProcedureCodeMapId).collect(Collectors.toList());

            ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery queryObj =
                new ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setItemProcedureCodeMapIdList(itemProcedureCodeMapIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemProcedureCodeMapAuditLogQueryHandler.
                getItemProcedureCodeMapAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItemProcdreCdeMapId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Procedure Code Map"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_procedure_code_map");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_procedure_code_map");
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

    @Override
    public ItemProcedureCodeMapAuditLogDTO save(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        return null;
    }

    @Override
    public ItemProcedureCodeMapAuditLogDTO update(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemProcedureCodeMapAuditLogDTO> partialUpdate(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemProcedureCodeMapAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemProcedureCodeMapAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


}
