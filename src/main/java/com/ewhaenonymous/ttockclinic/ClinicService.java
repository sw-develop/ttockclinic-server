package com.ewhaenonymous.ttockclinic;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.exception.ResourceNotFoundException;
import com.ewhaenonymous.ttockclinic.payload.ClinicListResponse;
import com.ewhaenonymous.ttockclinic.payload.ClinicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public List<ClinicListResponse> findClinicsBySidoAndSiGunGu(String siDo, String siGunGu){   
        List<ClinicListResponse> clinicListResponses = null;
        List<Clinic> clinics = clinicRepository.findBySiDoAndSiGunGuContaining(siDo, siGunGu);
        for(Clinic clinic : clinics){
            ClinicListResponse clinicListResponse = new ClinicListResponse(clinic);
            clinicListResponses.add(clinicListResponse);
        }
        return clinicListResponses;
    }

    public ClinicResponse findClinicById(Long id){
        return new ClinicResponse(clinicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", "Id", null)));
    }
}
