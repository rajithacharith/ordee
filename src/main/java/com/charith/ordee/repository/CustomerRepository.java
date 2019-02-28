package com.charith.ordee.repository;

import com.charith.ordee.beans.User.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
    Customer save(Customer customer);
    int countAllByCustomerId(String customerID);
    Customer getCustomerByCustomerId(String customerID);
}
