package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.BranchItemLocationMapAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchItemLocationMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.BranchItemLocationMapAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchItemLocationMapAuditLogQueryHandlerImpl implements BranchItemLocationMapAuditLogQueryHandler {

    @Autowired
    BranchItemLocationMapAuditLogRepositoryExtended branchItemLocationMapAuditLogRepositoryExtended;
    @Autowired
    BranchItemLocationMapAuditLogMapper branchItemLocationMapAuditLogMapper;

    @Override
    public List<BranchItemLocationMapAuditLogDTO> getBranchItemLocationMapAuditTrailInfoByQueryHandler(BranchItemLocationMapAuditLogByBranchItemLocationIdAndUserIdAndDateQuery queryObj) {
        return  branchItemLocationMapAuditLogRepositoryExtended.findAll().stream()
            .map(branchItemLocationMapAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getBranchItemLocationMapIdList().contains(x.getBrnchItmLoctinMapId()) && (
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
