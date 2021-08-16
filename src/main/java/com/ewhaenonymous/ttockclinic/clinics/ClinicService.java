package com.ewhaenonymous.ttockclinic.clinics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class ClinicService {
    private ClinicRepository clinicRepository;

    public Long firstc(Clinic clinic){
        validateDuplicateClinic(clinic); //중복 검사
        clinicRepository.save(clinic);  //중복이 아닌 경우 save
        return clinic.getId();
    }

    public void upwaiting(Clinic clinic){
        clinicRepository.updateWaitings(clinic.getWaitings() + 1);
    }

    public void downwaiting(Clinic clinic){
        if(clinic.getWaitings() == 0){
            throw new IllegalStateException("대기자가 없습니다.");
        }
        clinicRepository.updateWaitings(clinic.getWaitings() - 1);
    }

    public int returnwaiting(Clinic clinic){
        List<Clinic> c = clinicRepository.findByCnameAndLoc(clinic.getCname(), clinic.getLoc());
        return c.get(0).getWaitings();
    }

    private void validateDuplicateClinic(Clinic clinic){    //선별진료소 중복 검사
        List<Clinic> findClinic = clinicRepository.findByCnameAndLoc(clinic.getCname(), clinic.getLoc());
        if(!findClinic.isEmpty()){
            throw new IllegalStateException("이미 존재하는 선별진료소입니다.");
        }
    }
}
