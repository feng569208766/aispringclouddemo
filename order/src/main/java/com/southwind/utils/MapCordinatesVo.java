package com.southwind.utils;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MapCordinatesVo {

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lon;
}
