package com.oracle;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.oracle.config.MongoRepositoryConfiguration;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={MongoRepositoryConfiguration.class})
public class MongoTemplateTest {
 
    @Inject MongoTemplate mongTemplate;
     
    @Test
    public void testMongoTemplate() {
        assertEquals(mongTemplate.getCollection("lc-activity-service").getName(), "lc-activity-service");
    }
}