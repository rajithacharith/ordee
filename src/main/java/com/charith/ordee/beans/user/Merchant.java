package com.charith.ordee.beans.user;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Observable;
import java.util.Observer;

@Entity
public class Merchant implements Observer {
    @Id
    private String merchantID;
    private String merchantName;
    private String merchantAdderss;
    private String merchantTelephoneNo;
    public Merchant(){
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAdderss() {
        return merchantAdderss;
    }

    public void setMerchantAdderss(String merchantAdderss) {
        this.merchantAdderss = merchantAdderss;
    }

    public String getMerchantTelephoneNo() {
        return merchantTelephoneNo;
    }

    public void setMerchantTelephoneNo(String merchantTelephoneNo) {
        this.merchantTelephoneNo = merchantTelephoneNo;
    }

    public Merchant(String merchantID, String merchantName, String merchantAdderss, String merchantTelephoneNo) {
        this.merchantID = merchantID;
        this.merchantName = merchantName;
        this.merchantAdderss = merchantAdderss;
        this.merchantTelephoneNo = merchantTelephoneNo;
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
