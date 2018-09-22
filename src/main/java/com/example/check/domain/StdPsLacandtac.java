package com.example.check.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StdPsLacandtac implements Serializable {
    private Integer id;

    private String provinceid;

    private String type;

    private String l1;

    private String l2;

    private String l3;

    private String l4;

    /**
     * 【自定义属性】成功匹配标志，true:和现网库存在匹配数据，false:和现网库没有匹配数据
     */
    private Boolean successMatch;
}