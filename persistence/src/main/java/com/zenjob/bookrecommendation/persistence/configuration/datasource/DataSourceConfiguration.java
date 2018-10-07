package com.zenjob.bookrecommendation.persistence.configuration.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zenjob.bookrecommendation.persistence.config.PersistencePropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    private PersistencePropertiesConfiguration persistencePropertiesConfiguration;

    //region Constructors
    public DataSourceConfiguration() {
    }
    //endregion

    //region Configuration factory methods
    @Bean
    public DataSource createDataSource() {
        // Create the pool configuration
        final HikariConfig connectionPoolConfiguration = createConnectionConfiguration();
        // Create the connection pool
        final HikariDataSource hikariDataSource = new HikariDataSource(connectionPoolConfiguration);
        LOGGER.debug("Successfully created Hikari connection pool - {}", hikariDataSource);
        return hikariDataSource;
    }
    //endregion

    //region Utility methods
    private HikariConfig createConnectionConfiguration() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(persistencePropertiesConfiguration.getDatabaseUserName());
        hikariConfig.setPassword(persistencePropertiesConfiguration.getDatabasePassword());
        hikariConfig.setDriverClassName(persistencePropertiesConfiguration.getDatabaseDriverClassName());
        hikariConfig.setJdbcUrl(persistencePropertiesConfiguration.getDatabaseConnectionUrl());
        hikariConfig.setMaximumPoolSize(persistencePropertiesConfiguration.getConnectionPoolMaximumSize());
        LOGGER.debug("Successfully created Hiakri connection pool ");
        return hikariConfig;
    }
    //endregion
}
