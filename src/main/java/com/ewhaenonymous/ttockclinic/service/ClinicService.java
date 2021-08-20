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

    public ClinicResponse createNewClinic(CreateClinicRequest clinicRequest){
        validateDuplicateClinic(clinicRequest.getLatitude(), clinicRequest.getLongitude());
        Clinic clinic = clinicRepository.save(this.clinicRequestToEntity(clinicRequest.getLatitude(), clinicRequest.getLongitude()));
        return new ClinicResponse(clinic);
    }

    public ClinicResponse returnWaiting(String latitude, String longitude){
        Clinic c = clinicRepository.findByLatitudeAndLongitude(latitude, longitude).orElse(
                clinicRepository.save(this.clinicRequestToEntity(latitude, longitude))
        );
        return new ClinicResponse(c);
    }

    private void validateDuplicateClinic(String latitude, String longitude){    //선별진료소 중복 검사
        Optional<Clinic> findClinic = clinicRepository.findByLatitudeAndLongitude(latitude, longitude);
        if(findClinic.isPresent()){
            throw new IllegalStateException("이미 존재하는 선별진료소입니다.");
        }
    }
}
