package com.charith.ordee.repository;

import com.charith.ordee.beans.User.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, String> {
    Merchant save(Merchant merchant);
    Merchant getMerchantByMerchantID(String merchantID);
}
