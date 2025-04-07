package com.example.capstone1_jpa.Service;


import com.example.capstone1_jpa.Model.Merchant;
import com.example.capstone1_jpa.Repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {


    private final MerchantRepository merchantRepository;


    public List<Merchant> getAllMerchant(){
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(Integer id, Merchant merchant){
        Merchant oldMerchant = merchantRepository.getById(id);
        if(oldMerchant == null){
            return false;
        }
        oldMerchant.setMerchantRate(merchant.getMerchantRate());
        oldMerchant.setTopMerchant(merchant.getTopMerchant());
        oldMerchant.setName(merchant.getName());
        oldMerchant.setRating(merchant.getRating());

        merchantRepository.save(oldMerchant);
        return true;
    }

    public boolean deleteMerchant(Integer id){
        Merchant merchant = merchantRepository.getById(id);
        if(merchant == null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }
}
