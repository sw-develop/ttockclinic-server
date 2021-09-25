package com.ewhaenonymous.ttockclinic;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.repository.PaperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final PaperRepository paperRepository;
    @Scheduled(cron = "0 0 0 * * *")	// 매일 00시 정각마다 db 삭제
    public void deletePaper() {
        Stack<Paper> paperStack = paperRepository.findByDeleted("N");
        while(!paperStack.isEmpty()){
            Paper p = paperStack.pop();
            p.setDeleted("Y");
            paperRepository.save(p);
        }
    }
}
