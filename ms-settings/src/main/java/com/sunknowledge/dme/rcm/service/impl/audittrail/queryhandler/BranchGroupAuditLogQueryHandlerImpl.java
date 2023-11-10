package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.BranchGroupAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchGroupAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.BranchGroupAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchGroupAuditLogQueryHandlerImpl implements BranchGroupAuditLogQueryHandler {

    @Autowired
    BranchGroupAuditLogRepositoryExtended branchGroupAuditLogRepositoryExtended;
    @Autowired
    BranchGroupAuditLogMapper branchGroupAuditLogMapper;

    @Override
    public List<BranchGroupAuditLogDTO> getBranchGroupAuditTrailInfoByQueryHandler(BranchGroupAuditLogByBranchGroupIdAndUserIdAndDateQuery queryObj) {

        return  branchGroupAuditLogRepositoryExtended.findAll().stream()
            .map(branchGroupAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getBranchGroupId().equals(x.getBrnchGrpId()) && (
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
