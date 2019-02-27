package com.charith.ordee.beans.repository;

import com.charith.ordee.beans.User.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User save(User user);

}
