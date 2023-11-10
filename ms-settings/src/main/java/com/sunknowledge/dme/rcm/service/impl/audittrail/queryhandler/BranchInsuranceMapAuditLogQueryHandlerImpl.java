package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.audittrail.BranchInsuranceMapAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.BranchInsuranceMapAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.BranchInsuranceMapAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchInsuranceMapAuditLogQueryHandlerImpl implements BranchInsuranceMapAuditLogQueryHandler {

    @Autowired
    BranchInsuranceMapAuditLogRepositoryExtended branchInsuranceMapAuditLogRepositoryExtended;
    @Autowired
    BranchInsuranceMapAuditLogMapper branchInsuranceMapAuditLogMapper;

    @Override
    public List<BranchInsuranceMapAuditLogDTO> getBranchInsuranceMapAuditTrailInfoByQueryHandler(BranchInsuranceMapAuditLogByBranchInsuranceIdAndUserIdAndDateQuery queryObj) {
        return  branchInsuranceMapAuditLogRepositoryExtended.findAll().stream()
            .map(branchInsuranceMapAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getBranchInsuranceMapIdList().contains(x.getBrachInsanceMapId()) && (
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
