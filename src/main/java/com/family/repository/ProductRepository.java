package com.family.repository;

import com.family.dto.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends CrudRepository<Menu, Long> {
    Optional<Menu> getMenuByid(Long id);
    Iterable<Menu> findByid(Long id);
}
