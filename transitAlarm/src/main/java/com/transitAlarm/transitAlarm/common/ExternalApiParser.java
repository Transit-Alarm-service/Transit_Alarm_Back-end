package com.transitAlarm.transitAlarm.common;

import com.transitAlarm.transitAlarm.common.base.BaseException;
import com.transitAlarm.transitAlarm.common.base.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ExternalApiParser {

    public static JSONObject GetExternalApi(String URL, String method) throws BaseException {

        StringBuilder result = new StringBuilder();

        try {

            java.net.URL validateURL = new URL(URL);
            HttpURLConnection urlConnection = (HttpURLConnection) validateURL.openConnection();
            urlConnection.setRequestMethod(method);

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            String returnLine;
            while ((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse(result.toString());
        }
        catch (Exception e) {
            // Invalid YouTube Channel ID
            throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
        }
    }

}
