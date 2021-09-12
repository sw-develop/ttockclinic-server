package com.ewhaenonymous.ttockclinic.payload.response;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.domain.Paper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaperResponse {

    private long id;
    private String name;
    private String phone;
    private LocalDate date;
    private String deleted;
    private Clinic clinic;

    public PaperResponse(Paper paper){
        this(paper.getId(), paper.getName(), paper.getPhone(), paper.getDate(), paper.getDeleted(), paper.getClinic());
    }
}
