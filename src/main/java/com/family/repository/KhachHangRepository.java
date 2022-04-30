package com.family.repository;

import com.family.dto.KhachHang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends CrudRepository<KhachHang, Long> {


    List<KhachHang> findByEmailAndMatKhau(String email, String matKhau);
    List<KhachHang> findKhachHangBy();


}