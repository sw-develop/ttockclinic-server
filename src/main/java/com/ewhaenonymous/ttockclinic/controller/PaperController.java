package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.payload.request.CreatePaperRequest;
import com.ewhaenonymous.ttockclinic.payload.request.GetPaperRequest;
import com.ewhaenonymous.ttockclinic.payload.request.UpdatePaperRequest;
import com.ewhaenonymous.ttockclinic.payload.response.PaperResponse;
import com.ewhaenonymous.ttockclinic.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class PaperController{

    private final PaperService paperService;

    @PostMapping("/paper")
    public ResponseEntity<?> createPaper(@RequestBody @Valid CreatePaperRequest paperRequest){
        PaperResponse paperResponse = paperService.createNewPaper(paperRequest);
        return new ResponseEntity<>(paperResponse, HttpStatus.OK);
    }

    @GetMapping("/paper")
    public ResponseEntity<?> getPaper(@RequestBody @Valid GetPaperRequest paperRequest){
        PaperResponse paperResponse = paperService.findPaperByPhoneAndName(paperRequest.getPhone(), paperRequest.getName());
        return new ResponseEntity<>(paperResponse, HttpStatus.OK);
    }

    @PatchMapping("/paper/{paperId}/qr-usage-count")
    public ResponseEntity<?> updatePaper(@RequestBody @Valid UpdatePaperRequest paperRequest){
        PaperResponse paperResponse = paperService.updateQrUsageCount(paperRequest);
        return new ResponseEntity<>(paperResponse, HttpStatus.OK);
    }

}
