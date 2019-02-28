package com.charith.ordee.services;

import com.charith.ordee.beans.User.Customer;
import com.charith.ordee.beans.User.User;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.repository.CustomerRepository;
import com.charith.ordee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public ResponseEntity register(UserDTO userDTO){
        if(userDTO.getUserType().equals("customer")){
            System.out.println(userDTO.getUserAddress());
            if(userRepository.existsByUsername(userDTO.getUsername())){
                return new ResponseEntity("User Exists",HttpStatus.NOT_ACCEPTABLE);
            }
            int customerID = 1000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Customer customer = new Customer(Integer.toString(customerID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(customerID));
            customerRepository.save(customer);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            System.out.println(userDTO.getUserType());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
