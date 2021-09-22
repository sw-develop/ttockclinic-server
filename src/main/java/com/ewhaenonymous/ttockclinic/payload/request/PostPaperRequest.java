package com.ewhaenonymous.ttockclinic.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostPaperRequest {
    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String phone;
}
