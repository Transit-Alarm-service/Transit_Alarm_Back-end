package com.transitAlarm.transitAlarm.api.controller;

import com.transitAlarm.transitAlarm.api.dto.PositionDTO;
import com.transitAlarm.transitAlarm.api.dto.StationDTO;
import com.transitAlarm.transitAlarm.api.service.NearByStationService;
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
public class NearByStationController {

    private final NearByStationService nearByStationService;


    @PostMapping("/station/nearby")
    public BaseResponse<List<StationDTO>> getNearbyStationList(@RequestBody PositionDTO dto) throws BaseException {
        return new BaseResponse<>(nearByStationService.callApi(dto));
    }
}
