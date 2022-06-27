package com.transitAlarm.transitAlarm.api.service;

import com.transitAlarm.transitAlarm.api.dto.PositionDTO;
import com.transitAlarm.transitAlarm.api.dto.StationDTO;
import com.transitAlarm.transitAlarm.api.response.NearByStationRes;
import com.transitAlarm.transitAlarm.common.ExternalApiParser;
import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  위도, 경도를 기준으로 반경 500m 내의 정류소 return
 */


@Service
@Slf4j
@RequiredArgsConstructor
public class NearByStationService {

    @Value("${nearby-station-api-key}")
    private String apiKey;

    public List<StationDTO> callApi(PositionDTO dto) throws BaseException {

        List<StationDTO> result = new ArrayList<>();

        String url = "http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList" +
                "?serviceKey=" + apiKey +
                "&gpsLati=" + dto.getLatitude() +
                "&gpsLong=" + dto.getLongitude() +
                "&_type=json";

        JSONObject jsonResult = (JSONObject) ExternalApiParser.GetExternalApi(url, "GET").get("response");

        // 응답 코드
        String resultCode = (String) ((JSONObject) jsonResult.get("header")).get("resultCode");
        if (resultCode.equals("99")) {
            throw new BaseException(BaseResponseStatus.REQUEST_ERROR);
        }
        else if (!resultCode.equals("00")) {
            throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
        }

        // 역 정보
        try {
            String emptyResult = (String) ((JSONObject) jsonResult.get("body")).get("items");
            if (emptyResult.equals("")) {
                return result;
            }
        } catch (Exception ignored) { }

        JSONArray stationList = (JSONArray) ((JSONObject) ((JSONObject) jsonResult.get("body")).get("items")).get("item");
        for (Object object : stationList) {
            JSONObject currentObject = (JSONObject) object;

            StationDTO stationDTO = new StationDTO();

            // 1. city code
            stationDTO.setCityCode((Long) currentObject.get("citycode"));
            // 2. latitude
            stationDTO.setLatitude((Double) currentObject.get("gpslati"));
            // 3. longitude
            stationDTO.setLongitude((Double) currentObject.get("gpslong"));
            // 4. station id
            stationDTO.setStationId((String) currentObject.get("nodeid"));
            // 5. station name
            stationDTO.setStationName((String) currentObject.get("nodenm"));
            result.add(stationDTO);
        }

        return result;

    }

}
