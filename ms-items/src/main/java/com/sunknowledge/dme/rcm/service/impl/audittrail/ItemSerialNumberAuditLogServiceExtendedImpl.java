package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ItemSerialNumberAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ItemSerialNumberAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ItemNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemProcedureCodeMapAuditLogByItemProcedureCodeMapIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterOutputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemSerialNumberServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service("itemSerialNumberAuditLogServiceExtendedImpl")
public class ItemSerialNumberAuditLogServiceExtendedImpl implements ItemSerialNumberAuditLogServiceExtended {

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;
    @Autowired
    ItemSerialNumberServiceExtended itemSerialNumberServiceExtended;
    @Autowired
    ItemSerialNumberAuditLogQueryHandler itemSerialNumberAuditLogQueryHandler;
    @Override
    public List<AuditLogReportDTO> getItemSerialNumberAuditLog(ItemNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long itemId = itemMasterServiceExtended.
                getItemByItemNo(String.valueOf(auditLogParameterDTO.getItemNo()))
                .stream().
                max(Comparator.comparing(ItemMasterOutputDTO::getItemId))
                .orElseThrow(NoSuchElementException::new).getItemId();

            List<Long> itemSerialNumberIdList = itemSerialNumberServiceExtended.getItemSerialNumberByItemId(itemId)
                .stream().map(ItemSerialNumberDTO::getItemSerialNumberId).collect(Collectors.toList());

            ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery queryObj =
                new ItemSerialNumberAuditLogByItemSerialNumberAndUserIdAndDateQuery();
            queryObj.setItemId(itemId);
            queryObj.setItemSerialNumberList(itemSerialNumberIdList);
            queryObj.setItemNo(auditLogParameterDTO.getItemNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = itemSerialNumberAuditLogQueryHandler.
                getItemSerialNumberAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getItmSrialNmberId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Item Serial Number"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "item_serial_number_id");
            insertKeys.put("noValue", auditLogParameterDTO.getItemNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "item_serial_number_id");
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
    public ItemSerialNumberAuditLogDTO save(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        return null;
    }

    @Override
    public ItemSerialNumberAuditLogDTO update(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ItemSerialNumberAuditLogDTO> partialUpdate(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemSerialNumberAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemSerialNumberAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }


}
