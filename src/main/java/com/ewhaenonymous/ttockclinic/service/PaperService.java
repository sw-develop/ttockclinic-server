package com.ewhaenonymous.ttockclinic.service;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.exception.InvalidQrException;
import com.ewhaenonymous.ttockclinic.exception.ResourceNotFoundException;
import com.ewhaenonymous.ttockclinic.payload.ResponseMessage;
import com.ewhaenonymous.ttockclinic.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;

    public Paper findPaperByPhoneAndName(String phone, String name){
        Paper paper = paperRepository.findByPhoneAndName(phone, name).orElse(null);
        return paper;
    }

    public Paper findPaperById(Long id){
        return paperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paper", "id", id));
    }

    public void validateValidQr(int qrUsageCount){ //QR 유효성 검사 메서드
        if(qrUsageCount >= 2)
            throw new InvalidQrException(ResponseMessage.INVALID_QR);
    }

    @Transactional
    public void updateQrUsageCount(Long id){
        Paper paper = this.findPaperById(id);
        validateValidQr(paper.getQrUsageCount());

        paper.setQrUsageCount(paper.getQrUsageCount() + 1);
        paperRepository.save(paper);
    }

}
