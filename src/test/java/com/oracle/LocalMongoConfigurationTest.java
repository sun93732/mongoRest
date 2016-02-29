package com.oracle;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.oracle.config.MongoDataSourceConfiguration;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={MongoDataSourceConfiguration.class})
public class LocalMongoConfigurationTest {
 
    @Inject MongoDbFactory mongoDbFactory;
     
    @Test
    public void testMongoDbFactoryConnection() {
        assertTrue(mongoDbFactory.getDb().getMongo().getConnector().isOpen());
    }
}