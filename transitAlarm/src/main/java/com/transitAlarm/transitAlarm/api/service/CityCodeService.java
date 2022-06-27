package com.transitAlarm.transitAlarm.api.service;

import com.transitAlarm.transitAlarm.api.CityCode;
import com.transitAlarm.transitAlarm.api.repository.CityCodeRepository;
import com.transitAlarm.transitAlarm.common.ExternalApiParser;
import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CityCodeService {

    private final CityCodeRepository cityCodeRepository;

    @Value("${nearby-station-api-key}")
    private String apiKey;

    public void reloadCityCodeList() throws BaseException {

        // 기존 데이터 삭제
        cityCodeRepository.deleteAll();

        String url = "http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCtyCodeList" +
                "?serviceKey=" + apiKey +
                "&_type=" + "json";

        JSONObject jsonResult = (JSONObject) ExternalApiParser.GetExternalApi(url, "GET").get("response");

        // 응답 코드
        String resultCode = (String) ((JSONObject) jsonResult.get("header")).get("resultCode");
        if (resultCode.equals("99")) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
        else if (!resultCode.equals("00")) {
            throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
        }

        JSONArray codeList = (JSONArray) ((JSONObject) ((JSONObject) jsonResult.get("body")).get("items")).get("item");
        for (Object obj : codeList) {
            JSONObject currentObj = (JSONObject) obj;

            cityCodeRepository.save(CityCode.createCityCode((Long) currentObj.get("citycode"), (String) currentObj.get("cityname")));
        }


    }

    public String getCityNameFromCityCode(Long cityCode) {
        Optional<CityCode> findCityCode = cityCodeRepository.findByCityCode(cityCode);

        if (findCityCode.isEmpty()) {
            return null;
        }
        return findCityCode.get().getCityName();
    }
}
