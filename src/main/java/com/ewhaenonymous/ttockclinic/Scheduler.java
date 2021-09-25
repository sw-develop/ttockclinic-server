package com.ewhaenonymous.ttockclinic;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import com.ewhaenonymous.ttockclinic.repository.PaperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Stack;

@Component
@Slf4j
public class Scheduler {
    PaperRepository paperRepository;
    @Scheduled(cron = "0 0 0 * * *")	// 매일 00시 정각마다 db 삭제
    public void deleteData() throws Exception {
        Stack<Paper> paperStack = paperRepository.findByDeleted("N");
        while(!paperStack.isEmpty()){
            Paper p = paperStack.pop();
            p.setDeleted("Y");
        }
    }

    @Scheduled(cron = "0 30 1 * * *")	// 매일 00시 정각마다 db 삭제
    public void setDeletedYesForPaper() throws Exception {
        Stack<Paper> paperStack = paperRepository.findByDeleted("N");
        while(!paperStack.isEmpty()){
            Paper p = paperStack.pop();
            p.setDeleted("Y");
        }
    }
}
