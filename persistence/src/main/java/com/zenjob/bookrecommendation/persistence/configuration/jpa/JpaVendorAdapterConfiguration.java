package com.zenjob.bookrecommendation.persistence.configuration.jpa;

import com.zenjob.bookrecommendation.persistence.config.PersistencePropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
public class JpaVendorAdapterConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaVendorAdapterConfiguration.class);

    //region Constants
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

    private static final String HIBERNATE_GENERATE_HBM2DDL = "hibernate.hbm2ddl.auto";

    private static final String HIBERNATE_GENERATE_QUOT_IDENTIFIERS = "hibernate.globally_quoted_identifiers";

    //endregion

    @Autowired
    private PersistencePropertiesConfiguration persistencePropertiesConfiguration;

    //region Constructors
    public JpaVendorAdapterConfiguration() {
    }
    //endregion

    //region Configuration factory methods
    @Bean
    public JpaVendorAdapter createJpaVendorAdapter() {
        // Create hibernate JPA vendor adapter
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform(persistencePropertiesConfiguration.getDatabaseDialect());
        jpaVendorAdapter.setGenerateDdl(persistencePropertiesConfiguration.isGenerateDataDefinitionLanguage());
        jpaVendorAdapter.setShowSql(persistencePropertiesConfiguration.isOutputSqlStatements());
        LOGGER.debug("Successfully created JPA vendor adapter - {}", jpaVendorAdapter);
        return jpaVendorAdapter;
    }


    @Bean(name = "jpaVendorProperties")
    public Properties createJpaVendorProperties() {
        final Properties properties = new Properties();
        properties.setProperty(HIBERNATE_GENERATE_HBM2DDL, persistencePropertiesConfiguration.getHbm2ddl());
        properties.setProperty(HIBERNATE_FORMAT_SQL, persistencePropertiesConfiguration.getHibernateFormatSql());
        properties.setProperty(HIBERNATE_GENERATE_QUOT_IDENTIFIERS, persistencePropertiesConfiguration.getGloballyQuotedIdentifiers());
        properties.setProperty("spring.datasource.initialization-mode", "always");
        return properties;
    }
    //endregion
}
