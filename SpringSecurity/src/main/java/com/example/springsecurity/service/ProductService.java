package com.example.springsecurity.service;

import com.example.springsecurity.model.Product;
import com.example.springsecurity.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Возвращаем список продуктов
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //Ищем продукты по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        return optionalProduct.orElse(null);

    }
    //Сохраняем продукты
    @Transactional
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    //Удаляем продукты по id
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);

    }

    //Обновляем  продукты
    @Transactional
    public void updateProduct(int id,Product product){
        product.setId(id);
        productRepository.save(product);

    }

}
