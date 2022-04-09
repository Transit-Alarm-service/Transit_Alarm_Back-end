package com.transitAlarm.transitAlarm.transit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "station_id")
    private Long id;

    /**
     * station name
     * EX : 인하대학교
     */
    @Column(name = "station_name")
    private String name;

    /**
     * station location
     * latitude and longitude
     */
    @Column(name = "station_latitude")
    private Double latitude;
    @Column(name = "station_longitutde")
    private Double longitude;
}
