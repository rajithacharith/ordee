package com.charith.ordee.repository;

import com.charith.ordee.beans.user.Chef;
import org.springframework.data.repository.CrudRepository;

public interface ChefRepository extends CrudRepository<Chef,String> {
    Chef save(Chef chef);
    Chef getChefByChefID(String chefID);
}
