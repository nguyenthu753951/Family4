package com.family.repository;

import com.family.dto.DonHang;
import com.family.dto.GioHangItem;
import com.family.dto.KhachHang;
import com.family.dto.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ThanhToanRepository extends CrudRepository<Menu, Long> {
    Iterable<Menu> findByid(Long id);


}
