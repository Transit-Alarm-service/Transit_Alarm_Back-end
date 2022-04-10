package com.transitAlarm.transitAlarm.transit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class StationDTO {

    @NotNull
    private Long uniqueNumber;

    @NotBlank
    private String name;

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
