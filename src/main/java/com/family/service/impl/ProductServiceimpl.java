package com.family.service.impl;


import com.family.dto.Menu;
import com.family.repository.ProductRepository;
import com.family.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceimpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Menu> getAllProduct() {
        return (List<Menu>) productRepository.findAll();
    }//findAll

    @Override
    public Optional<Menu> getMenuByid() {
        return Optional.empty();
    }


    private static List<Menu> list=new ArrayList<>();
    @Override
    public Optional<Menu> getMenuByid(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public Menu findByID(int id){
        return list.get(id);
    }
    @Override
    public void updateProduct(Menu menu) {
        productRepository.save(menu);
    }//add or update (tuy vao pri-key)

    @Override
    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }//delete dua vao pri-key
}
