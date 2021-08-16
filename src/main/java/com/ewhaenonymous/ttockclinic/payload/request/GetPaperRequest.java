package com.ewhaenonymous.ttockclinic.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPaperRequest {

    private String name;
    private String phone;

}
