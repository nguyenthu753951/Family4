package com.family.service;

import com.family.dto.NhanVien;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StaffService {
    List<NhanVien> getAllStaff();

    void updatestaff(NhanVien staff);

    void removestaffById(Long id);

    Optional<NhanVien> getstaffById(Long id);
}
