package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.ManufacturerAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.query.ManufacturerAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.ManufacturerNoAuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.itemothers.ManufacturerServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Primary
@Service("manufacturerAuditLogServiceExtendedImpl")
public class ManufacturerAuditLogServiceExtendedImpl implements ManufacturerAuditLogServiceExtended {

    @Autowired
    ManufacturerServiceExtended manufacturerServiceExtended;
    @Autowired
    ManufacturerAuditLogQueryHandler manufacturerAuditLogQueryHandler;
    @Override
    public ManufacturerAuditLogDTO save(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        return null;
    }

    @Override
    public ManufacturerAuditLogDTO update(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        return null;
    }

    @Override
    public Optional<ManufacturerAuditLogDTO> partialUpdate(ManufacturerAuditLogDTO manufacturerAuditLogDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ManufacturerAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ManufacturerAuditLogDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AuditLogReportDTO> getManufacturerAuditLog(ManufacturerNoAuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {

            Long manufacturerId = manufacturerServiceExtended.
                getManufacturerByManufacturerNo(String.valueOf(auditLogParameterDTO.getManufacturerNo()))
                .stream().
                max(Comparator.comparing(ManufacturerDTO::getManufacturerId))
                .orElseThrow(NoSuchElementException::new).getManufacturerId();

            ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery queryObj =
                new ManufacturerAuditLogByManufacturerNoAndUserIdAndDateQuery();
            queryObj.setManufacturerId(manufacturerId);
            queryObj.setManufacturerNo(auditLogParameterDTO.getManufacturerNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = manufacturerAuditLogQueryHandler.
                getManufacturerAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getMnufcturerId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Manufacturer"))
                .collect(Collectors.toList());


            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "manufacturer_id");
            insertKeys.put("noValue", auditLogParameterDTO.getManufacturerNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "manufacturer_id");
            updateKeys.put("noValue", auditLogParameterDTO.getManufacturerNo());
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
