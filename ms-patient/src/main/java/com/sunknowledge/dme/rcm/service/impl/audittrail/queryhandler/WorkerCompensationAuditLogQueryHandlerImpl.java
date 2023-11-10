package com.sunknowledge.dme.rcm.service.impl.audittrail.queryhandler;

import com.sunknowledge.dme.rcm.repository.WorkersCompensationAuditLogRepositoryExtended;
import com.sunknowledge.dme.rcm.service.audittrail.queryhandler.WorkerCompensationAuditLogQueryHandler;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationAuditLogMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class WorkerCompensationAuditLogQueryHandlerImpl implements WorkerCompensationAuditLogQueryHandler {

    @Autowired
    WorkersCompensationAuditLogRepositoryExtended workersCompensationAuditLogRepositoryExtended;
    @Autowired
    WorkersCompensationAuditLogMapper workersCompensationAuditLogMapper;

    @Override
    public Flux<WorkersCompensationAuditLogDTO> getWorkerCompensationAuditTrailInfoByQueryHandler(WorkerCompensationAuditLogByPatientNoAndUserIdAndDateQuery queryObj) {
        return workersCompensationAuditLogRepositoryExtended.findAll()
            .map(workersCompensationAuditLogMapper::toDto).filter(x -> {
                try {
                    return (queryObj.getWorkerCompensationIdList().contains(x.getWorersComenationId())) && ((((JSONObject) new JSONParser()
                        .parse(x.getNewRowData()))
                        .get("created_by_id") == null ? "" : ((JSONObject) new JSONParser()
                        .parse(x.getNewRowData()))
                        .get("created_by_id").toString()).equals(String.valueOf(queryObj.getUserId())) ||
                        (((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("updated_by_id") == null ? "" : ((JSONObject) new JSONParser()
                            .parse(x.getNewRowData()))
                            .get("updated_by_id").toString()).equals(String.valueOf(queryObj.getUserId())) ||
                        (x.getDmlTimestamp().compareTo(queryObj.getFromDate()) >= 0 &&
                            x.getDmlTimestamp().compareTo(queryObj.getToDate()) <= 0) &&
                            !x.getDmlType().equals("DELETE"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
