package com.transitAlarm.transitAlarm.transit;

import com.transitAlarm.transitAlarm.transit.dto.StationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "station_id")
    private Long id;

    /**
     * station unique number
     */
    @Column(name = "station_uniqueNum", unique = true)
    private Long uniqueNumber;

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
    @Column(name = "station_longitude")
    private Double longitude;

    private Station(Long uniqueNumber, String name, Double latitude, Double longitude) {
        this.uniqueNumber = uniqueNumber;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Station createStation(StationDTO dto) {
        return new Station(dto.getUniqueNumber(), dto.getName(), dto.getLatitude(), dto.getLongitude());
    }


}
