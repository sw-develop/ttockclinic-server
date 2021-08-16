package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.exception.DuplicatedUserException;
import com.ewhaenonymous.ttockclinic.exception.InvalidQrException;
import com.ewhaenonymous.ttockclinic.exception.ResourceNotFoundException;
import com.ewhaenonymous.ttockclinic.payload.ResponseMessage;
import com.ewhaenonymous.ttockclinic.payload.request.CreateClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.request.UpdatePaperRequest;
import com.ewhaenonymous.ttockclinic.payload.response.PaperResponse;
import com.ewhaenonymous.ttockclinic.repository.ClinicRepository;
import com.ewhaenonymous.ttockclinic.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final ClinicRepository clinicRepository;

    public PaperResponse findPaperByPhoneAndName(String phone, String name){
        PaperResponse paperResponse = new PaperResponse(paperRepository.findByPhoneAndName(phone, name)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "phone and name", null)));
        return paperResponse;
    }

    public Paper findPaperById(Long id){
        return paperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "id", id));
    }

    public void validateUnDuplicatedUser(String phone){
        if(paperRepository.findByPhone(phone).isPresent())
            new DuplicatedUserException(ResponseMessage.DUPLICATED_USER);
    }

    public Paper paperRequestToEntity(CreateClinicRequest clinicRequest){
        Clinic clinic = clinicRepository.findByLatitudeAndLongitude(clinicRequest.getLatitude(), clinicRequest.getLongitude())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", "latitude and longitude", null));
        return Paper.builder()
                .name(clinicRequest.getName())
                .phone(clinicRequest.getPhone())
                .clinic(clinic)
                .build();
    }

    @Transactional
    public PaperResponse createNewPaper(CreateClinicRequest clinicRequest){
        validateUnDuplicatedUser(clinicRequest.getPhone());

        Paper paper = paperRepository.save(this.paperRequestToEntity(clinicRequest));
        return new PaperResponse(paper);
    }

    public void validateValidQr(int qrUsageCount){ //QR 유효성 검사 메서드
        if(qrUsageCount >= 2)
            throw new InvalidQrException(ResponseMessage.INVALID_QR);
    }

    @Transactional
    public PaperResponse updateQrUsageCount(UpdatePaperRequest paperRequest){
        Paper paper = this.findPaperById(paperRequest.getId());
        validateValidQr(paper.getQrUsageCount());

        paper.setQrUsageCount(paper.getQrUsageCount() + 1);
        Paper updatedPaper = paperRepository.save(paper);
        return new PaperResponse(updatedPaper);
    }

}
