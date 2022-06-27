package com.transitAlarm.transitAlarm.api;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CityCode {

    @Id
    @GeneratedValue
    @Column(name = "city_code_id")
    private Long id;

    @Column(name = "city_code")
    private Long cityCode;

    @Column(name = "city_name")
    private String cityName;

    private void init(Long cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public static CityCode createCityCode(Long cityCode, String cityName) {
        CityCode result = new CityCode();

        result.init(cityCode, cityName);
        return result;
    }
}
