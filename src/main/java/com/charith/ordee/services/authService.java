package com.charith.ordee.services;

import com.charith.ordee.beans.dto.UserDTO;
import com.charith.ordee.beans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class authService {
    @Autowired
    private UserRepository userRepository;
    private void register(UserDTO userDTO){
        if(userDTO.getUserType()=="customer"){
            System.out.println(userRepository.count());
        }
    }
}
