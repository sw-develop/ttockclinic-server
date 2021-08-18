package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.exception.ResourceNotFoundException;
import com.ewhaenonymous.ttockclinic.payload.request.CreateClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.response.ClinicResponse;
import com.ewhaenonymous.ttockclinic.payload.response.PaperResponse;
import com.ewhaenonymous.ttockclinic.repository.ClinicRepository;
import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ClinicService {
    private final ClinicRepository clinicRepository;

    public ClinicResponse createNewClinic(CreateClinicRequest clinicRequest){
        validateDuplicateClinic(clinicRequest.getLatitude(), clinicRequest.getLongitude());
        Clinic clinic = clinicRepository.save(clinicRequest.toEntity());
        return new ClinicResponse(clinic);
    }

    public ClinicResponse returnwaiting(Clinic clinic){
        Optional<Clinic> c = clinicRepository.findByLatitudeAndLongitude(clinic.getLatitude(), clinic.getLongitude());
        return new ClinicResponse(c.get());
    }

    private void validateDuplicateClinic(String latitude, String longitude){    //선별진료소 중복 검사
        Optional<Clinic> findClinic = clinicRepository.findByLatitudeAndLongitude(latitude, longitude);
        if(!findClinic.isEmpty()){
            throw new IllegalStateException("이미 존재하는 선별진료소입니다.");
        }
    }
}
