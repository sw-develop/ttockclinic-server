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

@CrossOrigin(origins = {"http://localhost:19006", "http://localhost:3000"})
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class PaperController {
    private final PaperService paperService;

    @PostMapping("/paper")
    public ResponseEntity<?> postPaper(@RequestBody @Valid CreatePaperRequest paperRequest){
        paperService.createNewPaper(paperRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/paper")
    public ResponseEntity<?> getPaper(@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone){
        PaperResponse paperResponse = paperService.findPaperByPhoneAndNameAndDeleted(phone, name, "N");
        return new ResponseEntity<>(paperResponse, HttpStatus.OK);
    }

    @PatchMapping("/paper/qr-usage-count")
    public ResponseEntity<?> updatePaper(@RequestBody @Valid UpdatePaperRequest paperRequest){
        PaperResponse paperResponse = paperService.updateQrUsageCount(paperRequest);
        return new ResponseEntity<>(paperResponse, HttpStatus.OK);
    }
}
