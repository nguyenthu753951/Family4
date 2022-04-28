package com.family.repository;

import com.family.dto.KhachHang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends CrudRepository<KhachHang, Long> {
    Optional<KhachHang> findUserByemail(String email);
    List<KhachHang> findKhachHangBy();


}