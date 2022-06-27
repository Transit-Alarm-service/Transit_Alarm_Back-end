package com.transitAlarm.transitAlarm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRouteDTO {

    private String routeId;
    private String busName;
    private String busType;
    private String endPoint;
    private String startingPoint;

}
