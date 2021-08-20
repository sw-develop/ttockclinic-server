package com.ewhaenonymous.ttockclinic.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public final class UpdatePaperRequest {

    @NotNull
    private Long id;

    @NotNull
    private String deleted;
}
