package com.transitAlarm.transitAlarm.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class seoulBusController {
    @RequestMapping("/seoul_bus_list")
    public String seoulBus() throws Exception{
        String key = "4d525874616a6f6831313567447a6364";
        String url = "http://openapi.seoul.go.kr:8088/"+key+"/xml/busStopLocationXyInfo/0/100/";
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(url);
        NodeList nList = doc.getElementsByTagName("row");
        String result= new String();
        for(int temp = 0; temp < nList.getLength(); temp++){
            Node nNode = nList.item(temp);
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                //System.out.println(eElement.getTextContent());
                result+=(getTagValue("STOP_NM",eElement));
                result+=" , ";
                log.info("정류장  : " + getTagValue("STOP_NM", eElement));
            }	// for end
        }	// if end
        return result;
    }
    public static String getTagValue(String tag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        result = nlList.item(0).getTextContent();

        return result;
    }
}