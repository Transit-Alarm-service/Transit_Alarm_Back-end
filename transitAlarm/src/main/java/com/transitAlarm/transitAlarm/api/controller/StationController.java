package com.transitAlarm.transitAlarm.api.controller;

import com.transitAlarm.transitAlarm.api.dto.BusRouteDTO;
import com.transitAlarm.transitAlarm.api.dto.StationBusInfoDTO;
import com.transitAlarm.transitAlarm.api.service.StationService;
import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StationController {

    private final StationService stationService;

    @PostMapping("/station/bus")
    public BaseResponse<List<BusRouteDTO>> getAllBusRouteByStation(@RequestBody StationBusInfoDTO dto) throws BaseException {
        return new BaseResponse<>(stationService.getAllRoutesByStation(dto));
    }
}
