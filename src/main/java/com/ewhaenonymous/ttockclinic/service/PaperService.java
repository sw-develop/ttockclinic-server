package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.exception.DuplicatedUserException;
import com.ewhaenonymous.ttockclinic.exception.InvalidQrException;
import com.ewhaenonymous.ttockclinic.exception.InvalidUserException;
import com.ewhaenonymous.ttockclinic.exception.ResourceNotFoundException;
import com.ewhaenonymous.ttockclinic.payload.ResponseMessage;
import com.ewhaenonymous.ttockclinic.payload.request.CreatePaperRequest;
import com.ewhaenonymous.ttockclinic.payload.request.UpdatePaperRequest;
import com.ewhaenonymous.ttockclinic.payload.response.PaperResponse;
import com.ewhaenonymous.ttockclinic.repository.ClinicRepository;
import com.ewhaenonymous.ttockclinic.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaperService {
    private final PaperRepository paperRepository;
    private final ClinicRepository clinicRepository;

    public PaperResponse findPaperByPhoneAndNameAndDeleted(String phone, String name, String deleted){
        return new PaperResponse(paperRepository.findByPhoneAndNameAndDeleted(phone, name, deleted)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "phone, name, deleted", null)));
    }

    public Paper findPaperById(Long id){
        return paperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "id", id));
    }

    public void validateUnDuplicatedUser(String phone){
        if(paperRepository.findByPhoneAndDate(phone, LocalDate.now()).isPresent())
            throw new DuplicatedUserException(ResponseMessage.DUPLICATED_USER);
    }

    public Paper paperRequestToEntity(CreatePaperRequest paperRequest){
        Clinic clinic = clinicRepository.findById(paperRequest.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", "id", paperRequest.getClinicId()));
        return Paper.builder()
                .name(paperRequest.getName())
                .phone(paperRequest.getPhone())
                .clinic(clinic)
                .build();
    }

    @Transactional
    public PaperResponse createNewPaper(CreatePaperRequest paperRequest){
        validateUnDuplicatedUser(paperRequest.getPhone());
        Paper paper = paperRepository.save(this.paperRequestToEntity(paperRequest));
        return new PaperResponse(paper);
    }

    public void validateQrByUsageCount(int qrUsageCount){ //QR 유효성 검사 메서드
        if(qrUsageCount >= 2)
            throw new InvalidQrException(ResponseMessage.INVALID_QR);
    }

    @Transactional
    public void updateClinicWaitings(Clinic clinic, int qrUsageCount){
        if(qrUsageCount == 1){ //현장접수 완료 - 대기자 수 증가 시키기
            clinic.setWaitings(clinic.getWaitings() + 1);
        }
        else if(qrUsageCount == 2){ //검사 완료 - 대기자 수 감소 시키기
            if(clinic.getWaitings() != 0)
                clinic.setWaitings(clinic.getWaitings() - 1);
        }
        clinicRepository.save(clinic);
    }

    public void validateUserByIdAndDeleted(Long id, String deleted){
        if(paperRepository.findByIdAndDeleted(id, deleted).isEmpty())
            throw new InvalidUserException(ResponseMessage.INVALID_USER);
    }

    public void validateUserByDate(Paper paper){    //날짜 유효성 검사
        LocalDate today = LocalDate.now();
        if(!paper.getDate().isEqual(today)){
            paper.setDeleted("Y");
        }
    }

    @Transactional
    public PaperResponse updateQrUsageCount(UpdatePaperRequest paperRequest){
        validateUserByDate(findPaperById(paperRequest.getId()));
        validateUserByIdAndDeleted(paperRequest.getId(), paperRequest.getDeleted());
        Paper paper = this.findPaperById(paperRequest.getId());

        validateQrByUsageCount(paper.getQrUsageCount());
        paper.setQrUsageCount(paper.getQrUsageCount() + 1);
        if (paper.getQrUsageCount() == 2){
            paper.setDeleted("Y");
        }
        Paper updatedPaper = paperRepository.save(paper);
        this.updateClinicWaitings(updatedPaper.getClinic(), updatedPaper.getQrUsageCount());

        return new PaperResponse(updatedPaper);
    }
}
