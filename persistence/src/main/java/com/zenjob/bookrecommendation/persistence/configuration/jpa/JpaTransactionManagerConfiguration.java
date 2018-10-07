package com.zenjob.bookrecommendation.persistence.configuration.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class JpaTransactionManagerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaTransactionManagerConfiguration.class);

    //region Dependencies
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    //endregion

    //region Constructors
    public JpaTransactionManagerConfiguration() {
    }
    //endregion

    //region Configuration factory methods
    @Bean("transactionManager")
    public PlatformTransactionManager createTransactionManager() {
        // Create and configure transaction manager
        final JpaTransactionManager platformTransactionManager = new JpaTransactionManager();
        platformTransactionManager.setEntityManagerFactory(entityManagerFactory);
        LOGGER.debug("Successfully created transaction manager - {}", platformTransactionManager);
        return platformTransactionManager;
    }
    //endregion

    //region Properties getters and setters

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    //endregion
}
