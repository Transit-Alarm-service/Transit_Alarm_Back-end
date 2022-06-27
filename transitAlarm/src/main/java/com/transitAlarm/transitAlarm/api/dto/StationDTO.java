package com.transitAlarm.transitAlarm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {

    private Long cityCode;
    private Double latitude;
    private Double longitude;
    private String stationId;
    private String stationName;
}
