package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.exception.DuplicatedUserException;
import com.ewhaenonymous.ttockclinic.exception.InvalidQrException;
import com.ewhaenonymous.ttockclinic.exception.InvalidUserException;
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

    //유효한 사용자 체크 시 핸드폰번호 & 오늘 날짜에 해당하는 객체가 있는지 체크해줘야 함
    public void validateUnDuplicatedUser(String phone){
        if(paperRepository.findByPhoneAndDate(phone, LocalDate.now()).isPresent())
            throw new DuplicatedUserException(ResponseMessage.DUPLICATED_USER);
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

    public void validateQrByUsageCount(int qrUsageCount){ //QR 유효성 검사 메서드
        if(qrUsageCount >= 2)
            throw new InvalidQrException(ResponseMessage.INVALID_QR);
    }

    @Transactional
    public void updateClinicWaitings(Clinic clinic, int qrUsageCount){
        if(qrUsageCount == 1){ //현장예약 완료 - 대기자 수 증가 시키기
            clinic.setWaitings(clinic.getWaitings() + 1);
        }
        else if(qrUsageCount == 2){ //검사 완료 - 대기자 수 감소 시키기
            if(clinic.getWaitings() != 0) //대기자 수가 0이 아닐 때
                clinic.setWaitings(clinic.getWaitings() - 1);
        }
        clinicRepository.save(clinic);
    }

    public void validateUserByIdAndDeleted(Long id, String deleted){
        if(paperRepository.findByIdAndDeleted(id, deleted).isEmpty())
            throw new InvalidUserException(ResponseMessage.INVALID_USER);
    }

    @Transactional
    public PaperResponse updateQrUsageCount(UpdatePaperRequest paperRequest){
        //추가사항: qr 유효성 처리가 아니라 전달된 id랑 삭제 여부를 동시에 만족하는 객체가 있는지에 대한 유효성 처리를 해줘야 함
        //qr 유효성 처리는 어차피 앱(프론트)에서 1차적으로 해주니까 서버에서는 해당 객체가 실제 존재하는 애인지를 보면 됨
        validateUserByIdAndDeleted(paperRequest.getId(), paperRequest.getDeleted());

        Paper paper = this.findPaperById(paperRequest.getId());

        paper.setQrUsageCount(paper.getQrUsageCount() + 1);
        if (paper.getQrUsageCount() == 2){
            paper.setDeleted("Y");
        }
        Paper updatedPaper = paperRepository.save(paper);

        //선별진료소 대기자 수 업데이트
        this.updateClinicWaitings(updatedPaper.getClinic(), updatedPaper.getQrUsageCount());

        return new PaperResponse(updatedPaper);
    }

}
