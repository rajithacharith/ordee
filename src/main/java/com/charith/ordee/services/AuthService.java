package com.charith.ordee.services;

import com.charith.ordee.beans.user.Chef;
import com.charith.ordee.beans.user.Customer;
import com.charith.ordee.beans.user.Merchant;
import com.charith.ordee.beans.user.User;
import com.charith.ordee.beans.dto.LoginDTO;
import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.repository.ChefRepository;
import com.charith.ordee.repository.CustomerRepository;
import com.charith.ordee.repository.MerchantRepository;
import com.charith.ordee.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.charith.ordee.services.EmailService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service("authService")
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
    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private HttpHeaders headers = new HttpHeaders();

    public ResponseEntity register(UserDTO userDTO){
        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        String confirmation = RandomStringUtils.randomAlphabetic(8);
        if(userDTO.getUserType().equals("customer")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("user Exists"+userDTO.getUsername());
                return new ResponseEntity("user Exists Username : ",headers,HttpStatus.NOT_ACCEPTABLE);
            }
            int customerID = 1000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Customer customer = new Customer(Integer.toString(customerID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(customerID),confirmation,userDTO.getEmail());
            userRepository.save(user);
            emailService.sendConfirmation(user);
            customerRepository.save(customer);
            return new ResponseEntity(headers,HttpStatus.OK);
        }
        else if(userDTO.getUserType().equals("merchant")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("user Exists"+userDTO.getUsername());
                return new ResponseEntity("user Exists Username : ",headers,HttpStatus.NOT_ACCEPTABLE);
            }
            int merchantID = 3000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Merchant merchant = new Merchant(Integer.toString(merchantID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(merchantID),confirmation,userDTO.getEmail());
            userRepository.save(user);
            emailService.sendConfirmation(user);
            merchantRepository.save(merchant);
            return new ResponseEntity(headers,HttpStatus.OK);
        }
        else if(userDTO.getUserType().equals("chef")){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                logger.info("user Exists"+userDTO.getUsername());
                return new ResponseEntity("user Exists Username : ",headers,HttpStatus.NOT_ACCEPTABLE);
            }
            int chefID = 5000 + userRepository.countAllByAccountType(userDTO.getUserType());
            Chef chef = new Chef(Integer.toString(chefID),userDTO.getName(),userDTO.getUserAddress(),userDTO.getUserTel());
            User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getUserType(),Integer.toString(chefID),confirmation,userDTO.getEmail());
            userRepository.save(user);
            emailService.sendConfirmation(user);
            chefRepository.save(chef);
            return new ResponseEntity(headers,HttpStatus.OK);
        }
        else {
            logger.info("user Type not valid");
            return new ResponseEntity("user type not valid",headers,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity login(LoginDTO loginDTO){
        logger.info(loginDTO.getUsername() + " trying to log in using password : "+ loginDTO.getPassword());
        ResponseEntity res = null;
        if(userRepository.existsByUsername(loginDTO.getUsername())){
            User user = userRepository.getByUsername(loginDTO.getUsername());
            if(bCryptPasswordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
                String userType = user.getAccountType();
                String userId = user.getAccountId();
                if(userType.equals("customer")){
                    Customer customer = customerRepository.getCustomerByCustomerId(userId);
                    logger.info("Logged in successfully : "+ customer.getCustomerName());
                    res = new ResponseEntity(customer,headers,HttpStatus.OK);
                }else if(userType.equals("merchant")){
                    Merchant merchant = merchantRepository.getMerchantByMerchantID(userId);
                    logger.info("Logged in successfully : "+ merchant.getMerchantName());
                    res = new ResponseEntity(merchant,headers,HttpStatus.OK);
                }else if(userType.equals("chef")){
                    Chef chef = chefRepository.getChefByChefID(userId);
                    logger.info("Logged in successfully : "+ chef.getChefName());
                    res =  new ResponseEntity(chef,headers,HttpStatus.OK);
                }
            }else{
                res = new ResponseEntity("Invalid",headers,HttpStatus.BAD_REQUEST);
            }
        }else{
            res = new ResponseEntity("No user exists for this username",headers,HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
