package com.oracle.model;

import java.math.BigInteger;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name="CustomerActivity")
public class CustomerActivity implements java.io.Serializable {
     
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private BigInteger id;
     
    @Column(name="customerId", nullable=false)
    private String customerId;
     
    @Column(name="activityDescription") 
    private String activityDescription;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="activityTime")
    private Date activityTime;
     
    @Column(name="activityType")
    private String activityType; //ActivityType
     
    @Column(name="systemName")
    private String systemName;
     
    @Column(name="systemUrl")
    private String systemUrl;
 
    public CustomerActivity(String customerId, String activityDescription, Date activityTime, String activityType, String systemName, String systemUrl) {
        this.customerId = customerId;
        this.activityDescription = activityDescription;
        this.activityTime = activityTime;
        this.activityType = activityType;
        this.systemName = systemName;
        this.systemUrl = systemUrl;
    }
 
    public BigInteger getId() {
        return id;
    }
 
    public void setId(BigInteger id) {
        this.id = id;
    }
 
    public String getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
 
    public String getActivityDescription() {
        return activityDescription;
    }
 
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
 
    public Date getActivityTime() {
        return activityTime;
    }
 
    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }
 
    public String getActivityType() {
        return activityType;
    }
 
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
 
    public String getSystemName() {
        return systemName;
    }
 
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
 
    public String getSystemUrl() {
        return systemUrl;
    }
 
    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }
}