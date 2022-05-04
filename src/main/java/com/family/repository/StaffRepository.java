package com.family.repository;

import com.family.dto.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StaffRepository extends JpaRepository<NhanVien, Long> {
    List<NhanVien> findAllByid(Long id);
}
