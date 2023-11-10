package com.sunknowledge.dme.rcm.audittrailutil;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuditTrailUtil {

    public static List<AuditLogReportDTO> generateReportJSON(List<AuditLogDTO> auditLogDTO,
                                                             Map insertKeys, Map updateKeys) {
        List<AuditLogReportDTO> auditLogReportDTOList = new ArrayList<AuditLogReportDTO>();
        for (AuditLogDTO loopObj : auditLogDTO) {
            try {
                JSONObject oldRowJSON = (JSONObject) new JSONParser().parse(
                        loopObj.getOldRowData() == null ? "{}" : loopObj.getOldRowData());
                JSONObject newRowJSON = (JSONObject) new JSONParser().parse(
                        loopObj.getNewRowData() == null ? "{}" : loopObj.getNewRowData());

                AuditLogReportDTO reportDTO = new AuditLogReportDTO();
                if (loopObj.getDmlType().equals("INSERT")) {
                    reportDTO.setId(Long.parseLong(newRowJSON.get(insertKeys.get("idKey")).toString()));
                    reportDTO.setNo(insertKeys.get("noValue").toString());
                    reportDTO.setSection(insertKeys.get("sectionValue").toString());
                    reportDTO.setUserId(Long.parseLong(newRowJSON.get(insertKeys.get("userIdKey")) == null ? "0" : newRowJSON.get(insertKeys.get("userIdKey")).toString()));
                    reportDTO.setUserName(newRowJSON.get(insertKeys.get("userNameKey")) == null ? "" : newRowJSON.get(insertKeys.get("userNameKey")).toString());
                    reportDTO.setDateTime(newRowJSON.get(insertKeys.get("dateTimeKey")) == null ? null : LocalDateTime.parse(newRowJSON.get(insertKeys.get("dateTimeKey")).toString()));
                    reportDTO.setActivity(insertKeys.get("insertActivityValue").toString());
                } else if (loopObj.getDmlType().equals("UPDATE")) {
                    String updateDescription = getUpdateLogDescription(getJSONObjDiff(oldRowJSON.toString(), newRowJSON.toString()));
                    reportDTO.setId(Long.parseLong(newRowJSON.get(updateKeys.get("idKey")).toString()));
                    reportDTO.setNo(updateKeys.get("noValue").toString());
                    reportDTO.setSection(updateKeys.get("sectionValue").toString());
                    reportDTO.setUserId(Long.parseLong(newRowJSON.get(updateKeys.get("userIdKey")) == null ? "0" : newRowJSON.get(updateKeys.get("userIdKey")).toString()));
                    reportDTO.setUserName(newRowJSON.get(updateKeys.get("userNameKey")) == null ? "" : newRowJSON.get(updateKeys.get("userNameKey")).toString());
                    reportDTO.setDateTime(newRowJSON.get(updateKeys.get("dateTimeKey")) == null ? null : LocalDateTime.parse(newRowJSON.get(updateKeys.get("dateTimeKey")).toString()));
                    reportDTO.setActivity(updateDescription);
                }
                auditLogReportDTOList.add(reportDTO);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return auditLogReportDTOList;
    }

    public static MapDifference<String, Object> getJSONObjDiff(String jsonObj1, String jsonObj2) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> leftMap = gson.fromJson(jsonObj1, type);
        Map<String, Object> rightMap = gson.fromJson(jsonObj2, type);

        MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
        return difference;
    }

    public static String getUpdateLogDescription(MapDifference<String, Object> difference) {
        StringBuilder description = new StringBuilder();
        difference.entriesDiffering().forEach((key, value) -> description.append("=> " + key.toString().toUpperCase()
                + " is updated from \""
                + value.toString().replace("(", "").replace(")", "").split(", ")[0]
                + "\" to \""
                + value.toString().replace("(", "").replace(")", "").split(", ")[1]
                + "\". \n"));
        return description.toString();
    }
}
