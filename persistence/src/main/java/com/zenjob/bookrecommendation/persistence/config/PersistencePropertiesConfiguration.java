package com.zenjob.bookrecommendation.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PersistencePropertiesConfiguration {

    @Value("${persistence.database.username}")
    private String databaseUserName;

    @Value("${persistence.database.password}")
    private String databasePassword;

    @Value("${persistence.database.url}")
    private String databaseConnectionUrl;

    @Value("${persistence.database.driverclassname}")
    private String databaseDriverClassName;

    @Value("${persistence.pool.maxsize}")
    private int connectionPoolMaximumSize;

    @Value("${persistence.jpa.persistenceunitname}")
    private String persistenceUnitName;

    @Value("${persistence.hibernate.outputsqlstatements}")
    private boolean outputSqlStatements;

    @Value("${persistence.hibernate.generateddl}")
    private boolean generateDataDefinitionLanguage;

    @Value("${persistence.hibernate.databasedialect}")
    private String databaseDialect;

    @Value("${persistence.hibernate.formatsql}")
    private String hibernateFormatSql;

    @Value("${persistence.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${persistence.hibernate.globally_quoted_identifiers}")
    private String globallyQuotedIdentifiers;

    public String getDatabaseUserName() {
        return databaseUserName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseConnectionUrl() {
        return databaseConnectionUrl;
    }

    public String getDatabaseDriverClassName() {
        return databaseDriverClassName;
    }

    public int getConnectionPoolMaximumSize() {
        return connectionPoolMaximumSize;
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public boolean isOutputSqlStatements() {
        return outputSqlStatements;
    }

    public boolean isGenerateDataDefinitionLanguage() {
        return generateDataDefinitionLanguage;
    }

    public String getDatabaseDialect() {
        return databaseDialect;
    }

    public String getHibernateFormatSql() {
        return hibernateFormatSql;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public String getGloballyQuotedIdentifiers() {
        return globallyQuotedIdentifiers;
    }
}
