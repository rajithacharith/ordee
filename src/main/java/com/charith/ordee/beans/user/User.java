package com.charith.ordee.beans.user;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String accountType;
    private String accountId;
    private String confirmation;
    private String email;
    private boolean accountStatus = false;

    public User(){

    }

    public User(String username, String password, String accountType, String accountId,String confirmation,String mail) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.accountId = accountId;
        this.confirmation = confirmation;
        this.email = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
