package com.oracle;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.oracle.model.CustomerActivity;
import com.oracle.repository.CustomerActivityRepository;
import com.oracle.repository.impl.CustomerActivityRepositoryImpl;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={CustomerActivityRepositoryImpl.class})
public class CustomerActivityServiceTest {
 
    @Inject CustomerActivityRepository customerActivityService;
     
    @Test
    public void testCreateCustomerActivity() {
        Calendar cal = Calendar.getInstance();
        customerActivityService.createCustomerActivity("123", "Email Sent For Customer", cal.getTime(), "Email Received", "Salesforce", "http://salesforce.com/123213");
        assertNotNull(customerActivityService.findCustomerActivityByCustomerId("123"));
    }
     
    @Test
    public void testUpdateCustomerActivity(){
        List<CustomerActivity> updatecas = new ArrayList<CustomerActivity>();
        List<CustomerActivity> cas = customerActivityService.findCustomerActivityByCustomerId("123");
        for (CustomerActivity ca :cas){
            ca.setActivityDescription("SMS Sent from Customer");
            updatecas.add(ca);
        }
        customerActivityService.updateCustomerActivities(updatecas);
    }
     
    @Test
    public void deleteAllCustomerActivity(){
        customerActivityService.deleteCustomerActivity(new BigInteger("123"));
    }
     
    @Test
    public void testGetAllCustomerActivities(){
        for (CustomerActivity customerActivities : customerActivityService.getAllCustomerActivities()){
            System.out.println(customerActivities.getId() + " | " + customerActivities.getCustomerId()
            + " | " +  customerActivities.getActivityDescription() + " | " +  customerActivities.getActivityTime()
            + " | " + customerActivities.getActivityType() + " | " + customerActivities.getSystemName() + " | " +
            customerActivities.getSystemUrl());
        }
        assertTrue(customerActivityService.getAllCustomerActivities().size()>=0);
    }
}