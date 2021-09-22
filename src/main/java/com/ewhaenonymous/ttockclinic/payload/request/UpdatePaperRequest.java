package com.ewhaenonymous.ttockclinic.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdatePaperRequest {
    @NotNull
    private Long id;
    @NotNull
    private String deleted;
}
