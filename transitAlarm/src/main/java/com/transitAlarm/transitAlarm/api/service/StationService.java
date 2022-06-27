package com.transitAlarm.transitAlarm.api.service;

import com.transitAlarm.transitAlarm.api.dto.BusRouteDTO;
import com.transitAlarm.transitAlarm.api.dto.StationBusInfoDTO;
import com.transitAlarm.transitAlarm.api.dto.StationDTO;
import com.transitAlarm.transitAlarm.common.ExternalApiParser;
import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponseStatus;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StationService {

    @Value("${nearby-station-api-key}")
    private String apiKey;

    public List<BusRouteDTO> getAllRoutesByStation(StationBusInfoDTO dto) throws BaseException {
        List<BusRouteDTO> result = new ArrayList<>();

        String url = "http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getSttnThrghRouteList" +
                "?serviceKey=" + apiKey +
                "&nodeid=" + dto.getStationId() +
                "&cityCode=" + dto.getCityCode() +
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

        // 노선 정보
        try {
            String emptyResult = (String) ((JSONObject) jsonResult.get("body")).get("items");
            if (emptyResult.equals("")) {
                return result;
            }
        } catch (Exception ignored) { }

        JSONArray routeList = (JSONArray) ((JSONObject) ((JSONObject) jsonResult.get("body")).get("items")).get("item");
        for (Object obj : routeList) {
            JSONObject currentObj = (JSONObject) obj;

            BusRouteDTO busRouteDTO = new BusRouteDTO();
            busRouteDTO.setRouteId((String) currentObj.get("routeid"));
            busRouteDTO.setBusName(String.valueOf(currentObj.get("routeno")));
            busRouteDTO.setBusType((String) currentObj.get("routetp"));
            busRouteDTO.setEndPoint((String) currentObj.get("endnodenm"));
            busRouteDTO.setStartingPoint((String) currentObj.get("startnodenm"));
            result.add(busRouteDTO);

        }
        return result;

    }







}
