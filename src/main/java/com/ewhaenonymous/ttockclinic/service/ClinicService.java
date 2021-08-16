package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.repository.ClinicRepository;
import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class ClinicService {
    private ClinicRepository clinicRepository;



    public Long createNewClinic(Clinic clinic){
        validateDuplicateClinic(clinic); //중복 검사
        clinicRepository.save(clinic);  //중복이 아닌 경우 save
        return clinic.getId();
    }

    public int returnwaiting(Clinic clinic){
        List<Clinic> c = clinicRepository.findByLatitutdeAndLongtitude(clinic.getLatitutde(), clinic.getLongtitude());
        return c.get(0).getWaitings();
    }

    private void validateDuplicateClinic(Clinic clinic){    //선별진료소 중복 검사
        List<Clinic> findClinic = clinicRepository.findByLatitutdeAndLongtitude(clinic.getLatitutde(), clinic.getLongtitude());
        if(!findClinic.isEmpty()){
            throw new IllegalStateException("이미 존재하는 선별진료소입니다.");
        }
    }
}
