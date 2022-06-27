package com.transitAlarm.transitAlarm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationBusInfoDTO {

    private Long cityCode;
    private String stationId;

}
