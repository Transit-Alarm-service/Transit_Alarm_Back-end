package com.transitAlarm.transitAlarm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {

    private Double xPos;
    private Double yPos;
}
