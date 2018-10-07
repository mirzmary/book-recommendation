package com.zenjob.bookrecommendation.service.common;

import com.zenjob.bookrecommendation.service.helper.ServiceIntegrationTestHelper;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, locations = "classpath:applicationContext-services-integrationtest.xml")
@ActiveProfiles("services-integrationtest")
@Ignore
public abstract class AbstractServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ServiceIntegrationTestHelper helper;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractServiceIntegrationTest() {
    }

    protected void flush() {
        entityManager.flush();
    }

    protected void clear() {
        entityManager.clear();
    }

    protected void flushAndClear() {
        flush();
        clear();
    }

    public ServiceIntegrationTestHelper getHelper() {
        return helper;
    }
}
