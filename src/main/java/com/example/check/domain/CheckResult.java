package com.example.check.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CheckResult implements Serializable {
    private Integer id;
    
    private String deviceName;
    
    private String vendor;

    private String category;

    private Integer totalcounts;

    private Integer correctnum;

    private Integer wrongnum;

    private Integer lossnum;

    private Integer redundantnum;

}