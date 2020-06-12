package org.ibs.cds.gode.entity.store.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.ibs.cds.gode.entity.store.condition.JPAStoreEnabler;
import org.ibs.cds.gode.system.GodeAppEnvt;
import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Conditional(JPAStoreEnabler.class)
@EnableJpaRepositories(basePackages = GodeConstant.ENTITY_BASE_PACKAGE_NAME)
@ComponentScan(GodeConstant.GODE_BASE_PACKAGE_NAME)
@PropertySource(GodeAppEnvt.GODE_PROPERTIES)
@EntityScan(GodeConstant.ENTITY_BASE_PACKAGE_ALL)
public class JPAStoreConfig {

    private final Environment behaviour;

    @Autowired
    public JPAStoreConfig(Environment behaviour){
        this.behaviour = behaviour;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"org.ibs.cds.gode.entity"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(behaviour.getProperty("gode.datastore.jpa.driver"));
        dataSource.setUrl(behaviour.getProperty("gode.datastore.jpa.datasource.url"));
        dataSource.setUsername(behaviour.getProperty("gode.datastore.jpa.datasource.username"));
        dataSource.setPassword(behaviour.getProperty("gode.datastore.jpa.datasource.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", behaviour.getProperty("gode.datastore.jpa.datasource.auto-schema-management"));
        properties.setProperty("hibernate.dialect", behaviour.getProperty("gode.datastore.jpa.dialect"));
        return properties;
    }
}
