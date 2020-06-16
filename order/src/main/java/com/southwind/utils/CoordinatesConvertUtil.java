package com.southwind.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.lang.Math.*;

public class CoordinatesConvertUtil {

    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 高德地图坐标转化为百度坐标
     *
     * @param cordinatesVo
     */
    public static MapCordinatesVo bd_encrypt(MapCordinatesVo cordinatesVo) {
        if (cordinatesVo.getLat() == null || cordinatesVo.getLon() == null) {
            //log.info("地图坐标不能为空，{}", JSON.toJSONString(cordinatesVo));
            return cordinatesVo;
        }

        //log.info("地图坐标转化开始---》{}", JSON.toJSONString(cordinatesVo));
        double x = cordinatesVo.getLon().doubleValue(), y = cordinatesVo.getLat().doubleValue();

        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);

        double theta = atan2(y, x) + 0.000003 * cos(x * x_pi);

        double bd_lon = z * cos(theta) + 0.0065;

        double bd_lat = z * sin(theta) + 0.006;

        MapCordinatesVo mapCordinatesVo = new MapCordinatesVo();
        BigDecimal lat = new BigDecimal(bd_lat);
        BigDecimal lon = new BigDecimal(bd_lon);

        mapCordinatesVo.setLat(lat.setScale(6, BigDecimal.ROUND_HALF_DOWN));
        mapCordinatesVo.setLon(lon.setScale(6, BigDecimal.ROUND_HALF_DOWN));

        //log.info("地图坐标转换结束----》转化之前：{}；转化之后：{}", JSON.toJSONString(cordinatesVo),
        //      JSON.toJSONString(mapCordinatesVo));
        return mapCordinatesVo;
    }


    public static void bd_decrypt(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
        double theta = atan2(y, x) - 0.000003 * cos(x * x_pi);
        double gg_lon = z * cos(theta);
        double gg_lat = z * sin(theta);

        BigDecimal lat = new BigDecimal(gg_lat);
        BigDecimal lon = new BigDecimal(gg_lon);
        System.out.println(bd_lat + "," + bd_lon);
        System.out.println(gg_lat + "," + gg_lon);
        System.out.println(lat.setScale(6, BigDecimal.ROUND_HALF_DOWN) + "," + lon.setScale(6, BigDecimal.ROUND_HALF_DOWN));
    }

    public static void main(String[] args) {
        double bd_lat = 120.505089;
        double bd_lon = 36.172415;
        bd_decrypt(bd_lat,bd_lon);
    }
}
