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
}
