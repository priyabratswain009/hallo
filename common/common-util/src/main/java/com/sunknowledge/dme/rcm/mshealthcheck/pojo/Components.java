package com.sunknowledge.dme.rcm.mshealthcheck.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "clientConfigServer",
    "db",
    "discoveryComposite",
    "diskSpace",
    "hystrix",
    "livenessState",
    "ping",
    "reactiveDiscoveryClients",
    "readinessState",
    "refreshScope"
})
public class Components {
    @JsonProperty("clientConfigServer")
    private ClientConfigServer clientConfigServer;
    @JsonProperty("db")
    private Db db;
    @JsonProperty("discoveryComposite")
    private DiscoveryComposite discoveryComposite;
    @JsonProperty("diskSpace")
    private DiskSpace diskSpace;
    @JsonProperty("hystrix")
    private Hystrix hystrix;
    @JsonProperty("livenessState")
    private LivenessState livenessState;
    @JsonProperty("ping")
    private Ping ping;
    @JsonProperty("reactiveDiscoveryClients")
    private ReactiveDiscoveryClients reactiveDiscoveryClients;
    @JsonProperty("readinessState")
    private ReadinessState readinessState;
    @JsonProperty("refreshScope")
    private RefreshScope refreshScope;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("clientConfigServer")
    public ClientConfigServer getClientConfigServer() {
        return clientConfigServer;
    }

    @JsonProperty("clientConfigServer")
    public void setClientConfigServer(ClientConfigServer clientConfigServer) {
        this.clientConfigServer = clientConfigServer;
    }

    @JsonProperty("db")
    public Db getDb() {
        return db;
    }

    @JsonProperty("db")
    public void setDb(Db db) {
        this.db = db;
    }

    @JsonProperty("discoveryComposite")
    public DiscoveryComposite getDiscoveryComposite() {
        return discoveryComposite;
    }

    @JsonProperty("discoveryComposite")
    public void setDiscoveryComposite(DiscoveryComposite discoveryComposite) {
        this.discoveryComposite = discoveryComposite;
    }

    @JsonProperty("diskSpace")
    public DiskSpace getDiskSpace() {
        return diskSpace;
    }

    @JsonProperty("diskSpace")
    public void setDiskSpace(DiskSpace diskSpace) {
        this.diskSpace = diskSpace;
    }

    @JsonProperty("hystrix")
    public Hystrix getHystrix() {
        return hystrix;
    }

    @JsonProperty("hystrix")
    public void setHystrix(Hystrix hystrix) {
        this.hystrix = hystrix;
    }

    @JsonProperty("livenessState")
    public LivenessState getLivenessState() {
        return livenessState;
    }

    @JsonProperty("livenessState")
    public void setLivenessState(LivenessState livenessState) {
        this.livenessState = livenessState;
    }

    @JsonProperty("ping")
    public Ping getPing() {
        return ping;
    }

    @JsonProperty("ping")
    public void setPing(Ping ping) {
        this.ping = ping;
    }

    @JsonProperty("reactiveDiscoveryClients")
    public ReactiveDiscoveryClients getReactiveDiscoveryClients() {
        return reactiveDiscoveryClients;
    }

    @JsonProperty("reactiveDiscoveryClients")
    public void setReactiveDiscoveryClients(ReactiveDiscoveryClients reactiveDiscoveryClients) {
        this.reactiveDiscoveryClients = reactiveDiscoveryClients;
    }

    @JsonProperty("readinessState")
    public ReadinessState getReadinessState() {
        return readinessState;
    }

    @JsonProperty("readinessState")
    public void setReadinessState(ReadinessState readinessState) {
        this.readinessState = readinessState;
    }

    @JsonProperty("refreshScope")
    public RefreshScope getRefreshScope() {
        return refreshScope;
    }

    @JsonProperty("refreshScope")
    public void setRefreshScope(RefreshScope refreshScope) {
        this.refreshScope = refreshScope;
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
