package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.UserActivityReportQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.UserActivityReportByDateQuery;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderAuditLogQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Primary
@Service
public class SalesOrderAuditLogServiceExtendedImpl implements SalesOrderAuditLogServiceExtended {
    @Autowired
    SalesOrderAuditLogQueryHandler salesOrderAuditLogQueryHandler;
    @Autowired
    UserActivityReportQueryHandler userActivityReportQueryHandler;
    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;

    @Override
    public Flux<AuditLogReportDTO> getSOAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            SalesOrderAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderAuditLogQueryHandler.
                getSOAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalsOdrId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_id");
            updateKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable)
            .log();
    }

    @Override
    public Flux<AuditLogReportDTO> getSOOverallAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            SalesOrderAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderAuditLogQueryHandler.
                getSOOverallAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getRefId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), x.getSection()))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_id");
            updateKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            updateKeys.put("userIdKey", "updated_by_id");
            updateKeys.put("userNameKey", "updated_by_name");
            updateKeys.put("dateTimeKey", "updated_date");

            auditLogReportDTOList = AuditTrailUtil.generateReportJSON(auditLogDTO,
                insertKeys, updateKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(auditLogReportDTOList).flatMapMany(Flux::fromIterable)
            .log();
    }

    @Override
    public Flux<UserActivityReportDTO> getSOUserActivityReportForAuditLog(UserActivityParameterDTO userActivityParameterDTO) {
        List<UserActivityReportDTO> userActivityReportDTOList = new ArrayList<>();
        try {
            UserActivityReportByDateQuery userActivityReportByDateQuery = new UserActivityReportByDateQuery();
            userActivityReportByDateQuery.setFromDate(userActivityParameterDTO.getFromDate());
            userActivityReportByDateQuery.setToDate(userActivityParameterDTO.getToDate());
            userActivityReportDTOList = userActivityReportQueryHandler.getSOUserActivityReportByQueryHandler(userActivityReportByDateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(userActivityReportDTOList).flatMapMany(Flux::fromIterable)
            .log();
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> save(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> update(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> partialUpdate(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderMasterAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
