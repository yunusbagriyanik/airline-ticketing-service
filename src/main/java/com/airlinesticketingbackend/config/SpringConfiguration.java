package com.airlinesticketingbackend.config;

import com.airlinesticketingbackend.base.mapper.DozerMapper;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(value = {"com.airlinesticketingbackend"})
@EnableWebMvc
@EnableWebSecurity
@EnableTransactionManagement
@EnableCaching
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = {"com.airlinesticketingbackend.repository"})
@EntityScan(basePackages = {"com.airlinesticketingbackend.entity"})
@EnableJpaAuditing(auditorAwareRef = "userIdAuditorAware", dateTimeProviderRef = "dateAuditProvider")
public class SpringConfiguration {

    @Bean
    public DozerMapper dozzerMapperUtility(Mapper mapper) throws Exception {
        DozerMapper dozerMapperUtility = new DozerMapper();
        dozerMapperUtility.setMapper(mapper);
        return dozerMapperUtility;
    }

    @Bean
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(List<ClassPathResource> classPathResources) {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        Resource[] resources = new Resource[classPathResources.size()];
        classPathResources.toArray(resources);
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }

    @Bean
    public List<ClassPathResource> dozerMapFiles() {
        List<ClassPathResource> classPathResources = new ArrayList<>();
        classPathResources.add(new ClassPathResource("/dozer/dozer.xml"));
        return classPathResources;
    }

    @Bean
    @Qualifier(value = "dateAuditProvider")
    public CurrentDateTimeProvider dateAuditProvider() {
        return CurrentDateTimeProvider.INSTANCE;
    }

    @Bean
    @Qualifier(value = "userIdAuditorAware")
    public AwareUserIdentityAuditor userIdAuditorAware() {
        return new AwareUserIdentityAuditor();
    }
}
