package com.sunknowledge.dme.rcm.service.dto.delivery;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hcpcsDoctypeId",
    "hcpcsCode",
    "documentId",
    "documentName",
    "fileType",
    "formName",
    "hcpcsDocTypeUuid"
})
public class DocumentData {
    @JsonProperty("hcpcsDoctypeId")
    private Long hcpcsDoctypeId;
    @JsonProperty("hcpcsCode")
    private String hcpcsCode;
    @JsonProperty("documentId")
    private Long documentId;
    @JsonProperty("documentName")
    private String documentName;
    @JsonProperty("fileType")
    private String fileType;
    @JsonProperty("formName")
    private String formName;
    @JsonProperty("hcpcsDocTypeUuid")
    private String hcpcsDocTypeUuid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("hcpcsDoctypeId")
    public Long getHcpcsDoctypeId() {
        return hcpcsDoctypeId;
    }

    @JsonProperty("hcpcsDoctypeId")
    public void setHcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.hcpcsDoctypeId = hcpcsDoctypeId;
    }

    @JsonProperty("hcpcsCode")
    public String getHcpcsCode() {
        return hcpcsCode;
    }

    @JsonProperty("hcpcsCode")
    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    @JsonProperty("documentId")
    public Long getDocumentId() {
        return documentId;
    }

    @JsonProperty("documentId")
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @JsonProperty("documentName")
    public String getDocumentName() {
        return documentName;
    }

    @JsonProperty("documentName")
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @JsonProperty("fileType")
    public String getFileType() {
        return fileType;
    }

    @JsonProperty("fileType")
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @JsonProperty("formName")
    public String getFormName() {
        return formName;
    }

    @JsonProperty("formName")
    public void setFormName(String formName) {
        this.formName = formName;
    }

    @JsonProperty("hcpcsDocTypeUuid")
    public String getHcpcsDocTypeUuid() {
        return hcpcsDocTypeUuid;
    }

    @JsonProperty("hcpcsDocTypeUuid")
    public void setHcpcsDocTypeUuid(String hcpcsDocTypeUuid) {
        this.hcpcsDocTypeUuid = hcpcsDocTypeUuid;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
