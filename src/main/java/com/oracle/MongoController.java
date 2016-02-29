package com.oracle;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.oracle.model.CustomerActivity;
import com.oracle.repository.impl.CustomerActivityRepositoryImpl;
 
@Controller
@RequestMapping("/activity")
public class MongoController {
 
    @Autowired
    @Qualifier("mongoRepository")
    CustomerActivityRepositoryImpl mongoCustomerService;
     
    @RequestMapping("")
    public String returnMongoView(Map<String, Object> map){
        map.put("mongoCustomerList", mongoCustomerService.getAllCustomerActivities());
        return "mongo";
    }
     
    @RequestMapping(value="/insert")
    public String insertCustomersView(){
        return "insertmongo";
    }
     
    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public String insertCustomers(@RequestParam("customerId") String customerId, 
            @RequestParam("activityDescription") String activityDescription,
            @RequestParam("activityTime") String activityTime,
            @RequestParam("activityType") String activityType, 
            @RequestParam("systemName") String systemName,
            @RequestParam("systemUrl") String systemUrl) {
        mongoCustomerService.createCustomerActivity(customerId, activityDescription, new Date(), activityType, systemName, systemUrl);
        return "mongo";
    }
     
    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public String deleteCustomer(@PathVariable("id") String id, Map<String, Object> map){
        mongoCustomerService.deleteCustomerActivity(new BigInteger(id));
        return "mongo";
    }
     
    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public String viewUpdateCustomer(@PathVariable("id") String id, Map<String, Object> map){
        List<CustomerActivity> customer = Arrays.asList(mongoCustomerService.getCustomerActivityById(new BigInteger(id)));
        map.put("mongoList", customer);
        return "updatemongo";
    }
     
    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public String updateCustomer(@PathVariable("id") String id, 
            @RequestParam("customerId") String customerId, 
            @RequestParam("activityDescription") String activityDescription,
            @RequestParam("activityType") String activityType, 
            @RequestParam("systemName") String systemName,
            @RequestParam("systemUrl") String systemUrl) {
        mongoCustomerService.updateCustomerActivity(new BigInteger(id), customerId, activityDescription, activityType, systemName,systemUrl);
        return "mongo";
    }
}