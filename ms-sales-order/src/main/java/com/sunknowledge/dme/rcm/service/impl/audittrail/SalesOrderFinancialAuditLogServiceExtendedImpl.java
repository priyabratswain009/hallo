package com.sunknowledge.dme.rcm.service.impl.audittrail;

import com.sunknowledge.dme.rcm.audittrailutil.AuditLogDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditLogReportDTO;
import com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.service.audittrail.SalesOrderFinancialAuditLogServiceExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.SalesOrderFinancialAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.UserActivityReportQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.AuditLogParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.SalesOrderFinancialAuditLogBySONoAndUserIdOrDateQuery;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderFinancialDetailsServiceExtended;
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
public class SalesOrderFinancialAuditLogServiceExtendedImpl implements SalesOrderFinancialAuditLogServiceExtended {

    @Autowired
    SalesOrderFinancialAuditLogQueryHandler salesOrderFinancialAuditLogQueryHandler;
    @Autowired
    UserActivityReportQueryHandler userActivityReportQueryHandler;
    @Autowired
    SalesOrderMasterSearchServiceExtended salesOrderMasterSearchServiceExtended;

    @Autowired
    SalesOrderFinancialDetailsServiceExtended salesOrderFinancialDetailsServiceExtended;

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> save(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> update(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> partialUpdate(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderFinancialDetailsAuditLogDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderFinancialDetailsAuditLogDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<AuditLogReportDTO> getSOFinancialAuditTrailInfo(AuditLogParameterDTO auditLogParameterDTO) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<>();
        try {
            Long soId = salesOrderMasterSearchServiceExtended.
                getSODetailsBySalesOrderNumber(auditLogParameterDTO.getSalesOrderNo())
                .collectList().toFuture().get().stream().
                max(Comparator.comparing(SalesOrderMasterSearchOutputDTO::getSid))
                .orElseThrow(NoSuchElementException::new).getSid();

            List<Long> salesOrderFinancialIDList = salesOrderFinancialDetailsServiceExtended.
                findBySalesOrderId(soId).collectList().toFuture().get().stream().map(SalesOrderFinancialDetails::getSalesOrderFinancialId).collect(Collectors.toList());

            SalesOrderFinancialAuditLogBySONoAndUserIdOrDateQuery queryObj = new SalesOrderFinancialAuditLogBySONoAndUserIdOrDateQuery();
            queryObj.setSoID(soId);
            queryObj.setSoFinancialID(salesOrderFinancialIDList);
            queryObj.setSoNo(auditLogParameterDTO.getSalesOrderNo());
            queryObj.setUserID(auditLogParameterDTO.getUserId());
            queryObj.setFromDate(auditLogParameterDTO.getFromDate());
            queryObj.setToDate(auditLogParameterDTO.getToDate());

            List<AuditLogDTO> auditLogDTO = salesOrderFinancialAuditLogQueryHandler.
                getSOFinancialAuditTrailInfoByQueryHandler(queryObj).stream()
                .map(x -> new AuditLogDTO(x.getId(), x.getSalsOrdrFincialId(), x.getOldRowData(), x.getNewRowData(),
                    x.getDmlType(), x.getDmlTimestamp(), x.getDmlCreatedBy(), "Sales Order Financial Details"))
                .collect(Collectors.toList());

            Map insertKeys = new HashMap();
            insertKeys.put("idKey", "sales_order_financial_id");
            insertKeys.put("noValue", auditLogParameterDTO.getSalesOrderNo());
            insertKeys.put("userIdKey", "created_by_id");
            insertKeys.put("userNameKey", "created_by_name");
            insertKeys.put("dateTimeKey", "created_date");

            Map updateKeys = new HashMap();
            updateKeys.put("idKey", "sales_order_financial_id");
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
}
