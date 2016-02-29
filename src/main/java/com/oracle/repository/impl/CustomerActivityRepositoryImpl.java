package com.oracle.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.config.MongoRepositoryConfiguration;
import com.oracle.model.CustomerActivity;
import com.oracle.repository.CustomerActivityRepository;

@Repository
@Import({ MongoRepositoryConfiguration.class })
@Qualifier("mongoRepository")
public class CustomerActivityRepositoryImpl implements CustomerActivityRepository {

    @Inject
    MongoTemplate mongoTemplate;

    Class<CustomerActivity> entityClass = CustomerActivity.class;

    Collection<CustomerActivity> customerActivity = new ArrayList<CustomerActivity>();

    public void updateCustomerActivity(BigInteger id, String customerId,
                    String activityDescription, String activityType, String systemName,
                    String systemUrl) {
        CustomerActivity updateCustomerActivity = this.getCustomerActivityById(id);
        updateCustomerActivity.setCustomerId(customerId);
        updateCustomerActivity.setActivityDescription(activityDescription);

        updateCustomerActivity.setActivityType(activityType);
        updateCustomerActivity.setSystemName(systemName);
        updateCustomerActivity.setSystemUrl(systemUrl);
        mongoTemplate.save(updateCustomerActivity);
    }

    public void updateCustomerActivities(List<CustomerActivity> customerActivitiesToUpdate) {
        for (CustomerActivity customerActivity : customerActivitiesToUpdate) {
            Update updateCustomerActivity = new Update();
            updateCustomerActivity.push("customerId", customerActivity.getCustomerId());
            updateCustomerActivity.push("activityDescription",
                            customerActivity.getActivityDescription());
            updateCustomerActivity.push("activityTime", customerActivity.getActivityTime());
            updateCustomerActivity.push("activityType", customerActivity.getActivityType());
            updateCustomerActivity.push("systemName", customerActivity.getSystemName());
            updateCustomerActivity.push("systemUrl", customerActivity.getSystemUrl());
            mongoTemplate.updateFirst(new Query(Criteria.where("Id").is(customerActivity.getId())),
                            updateCustomerActivity, CustomerActivity.class);
        }
    }

    public CustomerActivity getCustomerActivityById(BigInteger id) {
        return mongoTemplate.findById(id, CustomerActivity.class);
    }

    public Collection<CustomerActivity> getAllCustomerActivities() {
        try {
            List<CustomerActivity> allCustomers = mongoTemplate.findAll(entityClass);
            return allCustomers;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createCustomerActivity(String customerId, String activityDescription,
                    Date activityTime, String activityType, String systemName, String systemUrl) {
        try {
            customerActivity.add(new CustomerActivity(customerId, activityDescription,
                            activityTime, activityType, systemName, systemUrl));
            mongoTemplate.insert(customerActivity, entityClass);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BigInteger findLastIdInCollection() {
        return mongoTemplate.findOne(new Query().with(new Sort(Direction.DESC, "id")),
                        CustomerActivity.class).getId();
    }

    public void deleteCustomerActivity(BigInteger customerId) {
        mongoTemplate.remove(this.getCustomerActivityById(customerId));
    }

    public void createCustomerActivity(CustomerActivity newCustomer) {
        customerActivity.add(newCustomer);
        mongoTemplate.insert(customerActivity, entityClass);
    }

    public void bulkCreateCustomerActivity(List<CustomerActivity> customerActivities) {
        for (CustomerActivity newcustomerActivity : customerActivities) {
            customerActivity.add(new CustomerActivity(newcustomerActivity.getCustomerId(),
                            newcustomerActivity.getActivityDescription(), newcustomerActivity
                                            .getActivityTime(), newcustomerActivity
                                            .getActivityType(),
                            newcustomerActivity.getSystemName(), newcustomerActivity.getSystemUrl()));
        }
        mongoTemplate.insert(customerActivity, entityClass);
    }

    public List<CustomerActivity> findCustomerActivityByQuery(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public void deleteCustomerActivities(List<CustomerActivity> customerActivitiesToDelete) {
        for (CustomerActivity customerActivityToDelete : customerActivitiesToDelete) {
            mongoTemplate.remove(
                            new Query(Criteria.where("customerId").is(
                                            customerActivityToDelete.getCustomerId().toString())),
                            CustomerActivity.class);
        }
    }

    public List<CustomerActivity> getPaginatedCustomerActivities(Integer currentPage,
                    Integer totalPerPage) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getTotalRecords() {
        return getAllCustomerActivities().size();
    }

    public List<String> getPaginationSequence() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CustomerActivity> findCustomerActivityByCustomerId(String customerId) {
        return mongoTemplate
                        .find(new Query(Criteria.where("customerId").is(
                                        new Long(customerId).toString())), CustomerActivity.class);
    }
}