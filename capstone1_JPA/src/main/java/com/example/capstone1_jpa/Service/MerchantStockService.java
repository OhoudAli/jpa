package com.example.capstone1_jpa.Service;


import com.example.capstone1_jpa.Model.Merchant;
import com.example.capstone1_jpa.Model.MerchantStock;
import com.example.capstone1_jpa.Model.Product;
import com.example.capstone1_jpa.Repository.MerchantRepository;
import com.example.capstone1_jpa.Repository.MerchantStockRepository;
import com.example.capstone1_jpa.Repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public List<MerchantStock> getStock(){
        return merchantStockRepository.findAll();
    }

    public void addStock(MerchantStock merchantStock){
        merchantStockRepository.save(merchantStock);
    }


    public Boolean updateStock(Integer id , MerchantStock merchantStock){
        MerchantStock oldStock = merchantStockRepository.getById(id);

        if (oldStock == null){
            return false;
        }

        oldStock.setStock(merchantStock.getStock());
        merchantStockRepository.save(oldStock);
        return true;
    }


    public Boolean delete(Integer id){
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if(merchantStock == null){
            return false;
        }

        merchantStockRepository.delete(merchantStock);
        return true;
    }


//     public Boolean addNewStock(Integer productId, Integer merchantId, Integer amount){
//         Merchant merchant = merchantRepository.getById(merchantId);
//         Product product = productRepository.getById(productId);
//
//         if(merchant == null ){
//             System.out.println("merchant not found");
//             return false;
//         }
//         if(product== null){
//             System.out.println("product not found");
//             return false;
//         }
//         MerchantStock exist = merchantStockRepository.find
//
//     }

    public String addNewStock(Integer productId, Integer merchantId, int amount) {
        Merchant merchant = merchantRepository.findById(merchantId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (merchant == null || product == null) {
            return "Merchant or Product not found";
        }

        List<MerchantStock> existingStocks = getStock();
        for (MerchantStock stock : existingStocks) {
            if (stock.getProduct().getId().equals(productId) && stock.getMerchant().getId().equals(merchantId)) {
                stock.setStock(stock.getStock() + amount);
                merchantStockRepository.save(stock);
                return "Stock updated successfully";
            }
        }

        MerchantStock newStock = new MerchantStock();
        newStock.setMerchant(merchant);
        newStock.setProduct(product);
        newStock.setStock(amount);

        merchantStockRepository.save(newStock);
        return "New stock added successfully";
    }

    public boolean updateStockLevel(Integer productId, Integer merchantId, int amount) {
        for (MerchantStock stock : getStock()) {
            if (stock.getProduct().getId().equals(productId) && stock.getMerchant().getId().equals(merchantId)) {
                stock.setStock(amount);
                merchantStockRepository.save(stock);
                return true;
            }
        }
        return false;
    }

    public String buyProduct(Integer productId, Integer merchantId) {
        for (MerchantStock stock : getStock()) {
            if (stock.getProduct().getId().equals(productId) && stock.getMerchant().getId().equals(merchantId)) {
                if (stock.getStock() <= 0) {
                    return "Product is out of stock";
                }
                stock.setStock(stock.getStock() - 1);
                merchantStockRepository.save(stock);
                return "Product purchased successfully";
            }
        }
        return "Product not found";
    }

    public String checkStockLevel(Integer productId, Integer merchantId) {
        for (MerchantStock stock : getStock()) {
            if (stock.getProduct().getId().equals(productId) && stock.getMerchant().getId().equals(merchantId)) {
                return "Stock level for product " + productId + " is: " + stock.getStock();
            }
        }
        return "Product not found for this merchant";
    }
}
