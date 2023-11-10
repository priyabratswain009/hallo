package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.BranchOfficeAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchOfficeAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.BranchOfficeAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchOfficeAuditLogQueryHandlerImpl implements BranchOfficeAuditLogQueryHandler {

    @Autowired
    BranchOfficeAuditLogRepositoryExtended branchOfficeAuditLogRepositoryExtended;
    @Autowired
    BranchOfficeAuditLogMapper branchOfficeAuditLogMapper;
    @Override
    public List<BranchOfficeAuditLogDTO> getBranchOfficeAuditTrailInfoByQueryHandler(BranchOfficeAuditLogByBranchNoAndUserIdAndDateQuery queryObj) {
        return  branchOfficeAuditLogRepositoryExtended.findAll().stream()
            .map(branchOfficeAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getBranchId().equals(x.getBrnchId()) && (
                        (((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("created_by_id").toString()).equals(String.valueOf(queryObj.getUserId()))
                            ||
                            (((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                                .parse(x.getNewRowData()))
                                .get("updated_by_id").toString()).equals(String.valueOf(queryObj.getUserId()))
                            ||
                            (x.getDmlTimestamp().compareTo(queryObj.getFromDate()) >= 0 &&
                                x.getDmlTimestamp().compareTo(queryObj.getToDate()) <= 0)
                                &&
                                !x.getDmlType().equals("DELETE")));
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
    }
}
