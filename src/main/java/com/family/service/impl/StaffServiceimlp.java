package com.family.service.impl;

import com.family.dto.NhanVien;
import com.family.repository.StaffRepository;
import com.family.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StaffServiceimlp implements StaffService {
    @Autowired
    StaffRepository staffRepository;
    @Override
    public List<NhanVien> getAllStaff(){
        return staffRepository.findAll();
    }
    @Override
    public void updatestaff(NhanVien staff) {
        staffRepository.save(staff);
    }
    @Override
    public void removestaffById(Long id) {
        staffRepository.deleteById(id);
    }
    @Override
    public Optional<NhanVien> getstaffById(Long id) {
        return staffRepository.findById(id);
}
}
