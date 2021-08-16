package com.ewhaenonymous.ttockclinic.payload.request;

import com.ewhaenonymous.ttockclinic.domain.Paper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor //파라미터 없는 기본 생성자 생성
public final class CreatePaperRequest {

    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private Long clinicId;

    public Paper toEntity(){
        Clinic clinic = clinicRepository.findById(clinicId);
        return Paper.builder()
                .name(this.name)
                .phone(this.phone)
                .clinic(clinic)
                .build();
    }


}
