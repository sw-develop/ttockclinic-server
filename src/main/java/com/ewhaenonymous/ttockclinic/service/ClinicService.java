package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.payload.request.CreateClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.response.ClinicResponse;
import com.ewhaenonymous.ttockclinic.repository.ClinicRepository;
import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ClinicService {
    private final ClinicRepository clinicRepository;

    public Clinic clinicRequestToEntity(String latitude, String longitude){
        return Clinic.builder()
                .latitude(latitude)
                .longitude(longitude)
                .waitings(0)
                .build();
    }

    public void createNewClinic(CreateClinicRequest clinicRequest){
        boolean flag = validateDuplicateClinic(clinicRequest.getLatitude(), clinicRequest.getLongitude());
        if(!flag) {
            Clinic clinic = clinicRepository.save(this.clinicRequestToEntity(clinicRequest.getLatitude(), clinicRequest.getLongitude()));
        }
    }

    public ClinicResponse returnWaiting(String latitude, String longitude){
        Clinic c = clinicRepository.findByLatitudeAndLongitude(latitude, longitude).orElse(
                clinicRepository.save(this.clinicRequestToEntity(latitude, longitude))
        );
        return new ClinicResponse(c);
    }

    private boolean validateDuplicateClinic(String latitude, String longitude){    //선별진료소 중복 검사
        Optional<Clinic> findClinic = clinicRepository.findByLatitudeAndLongitude(latitude, longitude);
        if(findClinic.isPresent()){
            System.out.print("이미 존재하는 선별진료소입니다.");
            return true;
        }
        return false;
    }
}
