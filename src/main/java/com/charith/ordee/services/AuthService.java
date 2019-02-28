package com.charith.ordee.services;

import com.charith.ordee.beans.User.Chef;
import com.charith.ordee.beans.User.Customer;
import com.charith.ordee.beans.User.Merchant;
import com.charith.ordee.beans.User.User;
import com.charith.ordee.beans.dto.LoginDTO;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.repository.ChefRepository;
import com.charith.ordee.repository.CustomerRepository;
import com.charith.ordee.repository.MerchantRepository;
import com.charith.ordee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@Service
public class AuthService {
    private Logger logger = LogManager.getLogger();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ChefRepository chefRepository;
    public ResponseEntity register(UserDTO userDTO){
        if(userDTO.getUserType().equals("customer")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("User Exists"+userDTO.getUsername());
                return new ResponseEntity("User Exists Username : ",HttpStatus.NOT_ACCEPTABLE);
            }
            int customerID = 1000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Customer customer = new Customer(Integer.toString(customerID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(customerID));
            userRepository.save(user);
            customerRepository.save(customer);
            return new ResponseEntity(HttpStatus.OK);
        }
        else if(userDTO.getUserType().equals("merchant")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("User Exists"+userDTO.getUsername());
                return new ResponseEntity("User Exists Username : ",HttpStatus.NOT_ACCEPTABLE);
            }
            int merchantID = 3000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Merchant merchant = new Merchant(Integer.toString(merchantID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(merchantID));
            userRepository.save(user);
            merchantRepository.save(merchant);
            return new ResponseEntity(HttpStatus.OK);
        }
        else if(userDTO.getUserType().equals("chef")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("User Exists"+userDTO.getUsername());
                return new ResponseEntity("User Exists Username : ",HttpStatus.NOT_ACCEPTABLE);
            }
            int chefID = 5000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Chef chef = new Chef(Integer.toString(chefID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(chefID));
            userRepository.save(user);
            chefRepository.save(chef);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            logger.info("User Type not valid");
            return new ResponseEntity("User type not valid",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity login(LoginDTO loginDTO){
        logger.info(loginDTO.getUsername() + " trying to log in using password : "+ loginDTO.getPassword());

        if(userRepository.existsByUsername(loginDTO.getUsername())){
            User user = userRepository.getByUsername(loginDTO.getUsername());
            if(user.getPassword().equals(loginDTO.getPassword())){
                String userType = user.getAccountType();
                String userId = user.getAccountId();
                if(userType.equals("customer")){
                    Customer customer = customerRepository.getCustomerByCustomerId(userId);
                    logger.info("Logged in successfully : "+ customer.getCustomerName());
                    return new ResponseEntity(customer,HttpStatus.OK);
                }else if(userType.equals("merchant")){
                    Merchant merchant = merchantRepository.getMerchantByMerchantID(userId);
                    logger.info("Logged in successfully : "+ merchant.getMerchantName());
                    return new ResponseEntity(merchant,HttpStatus.OK);
                }else if(userType.equals("chef")){
                    Chef chef = chefRepository.getChefByChefID(userId);
                    logger.info("Logged in successfully : "+ chef.getChefName());
                    return new ResponseEntity(chef,HttpStatus.OK);
                }
            }else{
                return new ResponseEntity("Bad login credentials",HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity("No user exists for this username",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
