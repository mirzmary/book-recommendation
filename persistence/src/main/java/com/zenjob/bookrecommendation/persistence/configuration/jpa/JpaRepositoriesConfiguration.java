package com.zenjob.bookrecommendation.persistence.configuration.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.zenjob.bookrecommendation.persistence")
public class JpaRepositoriesConfiguration {

    //region Constructors
    public JpaRepositoriesConfiguration() {
    }
    //endregion
}
