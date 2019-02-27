package com.charith.ordee.beans.repository;

import com.charith.ordee.beans.User.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {
    Customer save(Customer customer);
    int countAllByCustomerId(String customerID);
}
