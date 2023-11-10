package com.sunknowledge.dme.rcm.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, "oAuth2Authentication");
            createCache(cm, com.sunknowledge.dme.rcm.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.sunknowledge.dme.rcm.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.sunknowledge.dme.rcm.domain.User.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.Authority.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.User.class.getName() + ".authorities");
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchGroup.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchOffice.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ItemLocation.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchInsuranceMap.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchItemLocationMap.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ClaimFormMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ClaimProgramMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.DocumentTypeMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.HoldReasonMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceDocument.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.NoteReasonMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.NoteTypeMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.PosMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.PosMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.SalesOrderClassification.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.StopReasonMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.TaskReasonMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.TaxZone.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.WipStatusMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.BranchUserMap.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.UserMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.Company.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.RoleMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.FunctionalityMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.RoleUserMap.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.TaxonomyDetails.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.EndpointMaster.class.getName());
            createCache(cm, com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
