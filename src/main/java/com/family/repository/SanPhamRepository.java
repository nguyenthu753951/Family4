package com.family.repository;


import com.family.dto.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends CrudRepository <Menu, Long> {
    List<Menu> findAll();

}
