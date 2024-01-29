package com.sunknowledge.dme.rcm.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ApplicationPropertiesSetup.CONFIGURATION_PROPERTY_PREFIX, ignoreUnknownFields = false)
public class ApplicationPropertiesSetup {
    static final String CONFIGURATION_PROPERTY_PREFIX = "application";
    private final Async async = new Async();
    public Async getAsync(){
        return async;
    }

    public static class Async{
        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Integer queueCapacity;

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public Integer getMaxPoolSize() {
            return maxPoolSize;
        }

        public Integer getQueueCapacity() {
            return queueCapacity;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public void setMaxPoolSize(Integer maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public void setQueueCapacity(Integer queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }
}
