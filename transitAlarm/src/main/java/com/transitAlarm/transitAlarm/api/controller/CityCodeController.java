package com.transitAlarm.transitAlarm.api.controller;

import com.transitAlarm.transitAlarm.api.service.CityCodeService;
import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponse;
import com.transitAlarm.transitAlarm.common.base.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CityCodeController {

    private final CityCodeService cityCodeService;

    @GetMapping("/citycode/reload")
    private BaseResponse<String> reloadCityCode() throws BaseException {
        cityCodeService.reloadCityCodeList();
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
