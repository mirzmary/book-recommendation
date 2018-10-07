package com.zenjob.bookrecommendation.persistence.configuration.jpa;

import com.zenjob.bookrecommendation.persistence.config.PersistencePropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaEntityManagerFactoryConfiguration {

    //region Constants
    private static final String JPA_PACKAGES_TO_SCAN = "com.zenjob.bookrecommendation.persistence";
    //endregion

    //region Dependencies
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    @Qualifier("jpaVendorProperties")
    private Properties jpaVendorProperties;

    @Autowired
    private PersistencePropertiesConfiguration persistencePropertiesConfiguration;

    //endregion

    //region Constructors
    public JpaEntityManagerFactoryConfiguration() {
        // init Jpa Entity ManagerFactory
    }
    //endregion

    //region Configuration factory methods
    @Bean("entityManagerFactory")
    public EntityManagerFactory createEntityManagerFactory() {
        // Create and configure entity manager factory
        final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceUnitName(persistencePropertiesConfiguration.getPersistenceUnitName());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaProperties(jpaVendorProperties);
        entityManagerFactory.setPackagesToScan(JPA_PACKAGES_TO_SCAN);
        entityManagerFactory.afterPropertiesSet();
        // GRab the build factory
        return entityManagerFactory.getObject();
    }
    //endregion

    //region Properties getters and setters
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JpaVendorAdapter getJpaVendorAdapter() {
        return jpaVendorAdapter;
    }

    public void setJpaVendorAdapter(final JpaVendorAdapter jpaVendorAdapter) {
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    public Properties getJpaVendorProperties() {
        return jpaVendorProperties;
    }

    public void setJpaVendorProperties(final Properties jpaVendorProperties) {
        this.jpaVendorProperties = jpaVendorProperties;
    }

    //endregion
}
