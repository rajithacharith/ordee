package com.charith.ordee.repository;

import com.charith.ordee.beans.user.Merchant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepository extends CrudRepository<Merchant, String> {
    Merchant save(Merchant merchant);
    Merchant getMerchantByMerchantID(String merchantID);
}
