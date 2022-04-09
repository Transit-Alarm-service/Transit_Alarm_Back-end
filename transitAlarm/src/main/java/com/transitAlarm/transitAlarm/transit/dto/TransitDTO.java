package com.transitAlarm.transitAlarm.transit.dto;

import com.transitAlarm.transitAlarm.transit.Area;
import com.transitAlarm.transitAlarm.transit.Type;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class TransitDTO {
    @NotNull
    private Type type;
    @NotBlank
    private String name;
    @NotNull
    private Area area;

    /**
     * have to parse to LocalTime
     */
    @Pattern(regexp = "^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$")
    private String startTime;
    @Pattern(regexp = "^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$")
    private String endTime;

    @NotNull
    private Integer interval;
    @NotNull
    private Boolean round;
}
