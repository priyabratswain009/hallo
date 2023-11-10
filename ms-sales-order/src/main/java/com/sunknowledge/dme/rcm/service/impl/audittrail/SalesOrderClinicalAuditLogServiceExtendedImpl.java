package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderClinicalAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderClinicalAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderClinicalAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderClinicalDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class SalesOrderClinicalAuditLogServiceExtendedImpl implements SalesOrderClinicalAuditLogServiceExtended {
    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;
    @Autowired
    SalesOrderClinicalDetailsServiceExtended salesOrderClinicalDetailsServiceExtended;
    @Autowired
    SalesOrderClinicalAuditLogQueryHandler salesOrderClinicalAuditLogQueryHandler;

    @Override
    public Flux<AuditLogReportDTO> getSOClinicalAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            List<Long> salesOrderClinicalIDList = salesOrderClinicalDetailsServiceExtended.
                findBySalesOrderId(soId).collectList().toFuture().get().stream().map(SalesOrderClinicalDetails::getSalesOrderClinicalDetailsId).collect(Collectors.toList());

            SalesOrderClinicalAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderClinicalAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoClinicalID(salesOrderClinicalIDList);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderClinicalAuditLogQueryHandler.
                getSOClinicalAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalsOdrClincalDetilsId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order Clinical Details"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_clinical_details_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_clinical_details_id");
            updateKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable).log();
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> save(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> update(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> partialUpdate(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderClinicalDetailsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderClinicalDetailsAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
