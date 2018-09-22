package com.example.check.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CheckResultDetail implements Serializable {
    private Integer id;

    private Integer checkresultid;

    private Integer stdlibid;

    private Integer culibid;

    private Boolean iscorrect;

    private Boolean iswrong;

    private Boolean isloss;

    private Boolean isredundant;
    
    private String remark;
}