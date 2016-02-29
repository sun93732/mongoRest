package com.oracle.repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.oracle.model.CustomerActivity;
/*
 * 
 * 要不要继承现有的mongo repository？ 貌似实现里面只也用了template,而没用repository的方法
 * */
public interface CustomerActivityRepository {
    void updateCustomerActivity(BigInteger id, String customerId, String activityDescription,
                    String activityType, String systemName, String systemUrl);

    void updateCustomerActivities(List<CustomerActivity> customersToUpdate);

    CustomerActivity getCustomerActivityById(BigInteger customerId);

    Collection<CustomerActivity> getAllCustomerActivities();

    void createCustomerActivity(String customerId, String activityDescription, Date activityTime,
                    String activityType, String systemName, String systemUrl);

    void deleteCustomerActivity(BigInteger customerId);

    void createCustomerActivity(CustomerActivity newCustomer);

    void bulkCreateCustomerActivity(List<CustomerActivity> customers);

    List<CustomerActivity> findCustomerActivityByQuery(String query);

    void deleteCustomerActivities(List<CustomerActivity> customerToDelete);

    List<CustomerActivity> getPaginatedCustomerActivities(Integer currentPage, Integer totalPerPage);

    int getTotalRecords();

    List<String> getPaginationSequence();

    List<CustomerActivity> findCustomerActivityByCustomerId(String customerid);
}