package com.transitAlarm.transitAlarm.transit.dto;

import com.transitAlarm.transitAlarm.transit.Direction;
import com.transitAlarm.transitAlarm.transit.Station;
import com.transitAlarm.transitAlarm.transit.Transit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class TransitStationDTO {

    @NotNull
    private Long transitUniqueNumber;

    @NotNull
    private Long stationUniqueNumber;

    @NotNull
    private Integer sequence;

    @NotNull
    private Direction direction;

    @NotNull
    private Boolean round;
}
