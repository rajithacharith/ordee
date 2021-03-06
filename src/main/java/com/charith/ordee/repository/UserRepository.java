package com.charith.ordee.repository;

import com.charith.ordee.beans.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User save(User user);
    int countAllByAccountType(String type);
    Boolean existsByUsername(String username);
    User getByUsername(String username);
}
