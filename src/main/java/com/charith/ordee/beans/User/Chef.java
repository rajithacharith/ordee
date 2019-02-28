package com.charith.ordee.beans.User;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Chef {
    @Id
    private String chefID;
    private String chefName;
    private String chefAdderss;
    private String chefTelephoneNo;

    public Chef(String chefID, String chefName, String chefAdderss, String chefTelephoneNo) {
        this.chefID = chefID;
        this.chefName = chefName;
        this.chefAdderss = chefAdderss;
        this.chefTelephoneNo = chefTelephoneNo;
    }

    public String getChefID() {
        return chefID;
    }

    public void setChefID(String chefID) {
        this.chefID = chefID;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public String getChefAdderss() {
        return chefAdderss;
    }

    public void setChefAdderss(String chefAdderss) {
        this.chefAdderss = chefAdderss;
    }

    public String getChefTelephoneNo() {
        return chefTelephoneNo;
    }

    public void setChefTelephoneNo(String chefTelephoneNo) {
        this.chefTelephoneNo = chefTelephoneNo;
    }
}
