package com.family.service;


import com.family.dto.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Menu> getAllProduct();
    Optional<Menu> getMenuByid();

    Optional<Menu> getMenuByid(Long id);

    Menu findByID(int id);
    void updateProduct(Menu product)//add or update (tuy vao pri-key)
     ;

    void removeProductById(long id)//delete dua vao pri-key
    ;
}
