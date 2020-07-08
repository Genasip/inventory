package com.interview.inventory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllById(String id){
        return productRepository.findAllById(List.of(id));
    }

    public String save(List<Product> products){
        products.stream().parallel().forEach(product -> {
            Optional<Product> savedProduct = productRepository.findById(product.getName());
            if (savedProduct.isPresent()) {
                product.setQuantity(product.getQuantity() + savedProduct.get().getQuantity());
            }
            productRepository.save(product);
        });
        return "Products were added to database";
    }

    public String take(Product product){
        Optional<Product> savedProduct = productRepository.findById(product.getName());
        if(savedProduct.isPresent()&&savedProduct.get().getQuantity()>=product.getQuantity()){
            int quantity = savedProduct.get().getQuantity() - product.getQuantity();
            savedProduct.get().setQuantity(quantity);
            productRepository.save(savedProduct.get());
            return "Products are taken successfully from database";
        } else {
            return "Not enough products to take";
        }
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
