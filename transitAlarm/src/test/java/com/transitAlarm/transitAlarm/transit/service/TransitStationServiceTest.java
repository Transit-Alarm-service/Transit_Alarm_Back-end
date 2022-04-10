package com.transitAlarm.transitAlarm.transit.service;

import com.transitAlarm.transitAlarm.transit.*;
import com.transitAlarm.transitAlarm.transit.dto.StationDTO;
import com.transitAlarm.transitAlarm.transit.dto.TransitDTO;
import com.transitAlarm.transitAlarm.transit.dto.TransitStationDTO;
import com.transitAlarm.transitAlarm.transit.repository.StationRepository;
import com.transitAlarm.transitAlarm.transit.repository.TransitRepository;
import com.transitAlarm.transitAlarm.transit.repository.TransitStationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class TransitStationServiceTest {

    @Autowired
    TransitStationRepository transitStationRepository;
    @Autowired
    TransitRepository transitRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired TransitStationService transitStationService;
    @PersistenceContext EntityManager em;

    @Test
    void join() throws Exception {
        /**
         *  given : given condition
         */
        Transit transit1 = Transit.createTransit(new TransitDTO(22333L, Type.BUS, "9200", Area.INCHEON,
                "09:00", "22:00", 60, true));
        Transit transit2 = Transit.createTransit(new TransitDTO(22345L, Type.BUS, "9200", Area.GYEONGGI,
                "08:00", "23:00", 30, false));
        transitRepository.save(transit1);
        transitRepository.save(transit2);

        Station station1 =
                Station.createStation(new StationDTO(11122L, "인하대학교 정문", 37.36673247840618, 127.1081966687801));
        Station station2 =
                Station.createStation(new StationDTO(22233L, "정자역", 37.4514327, 126.651520));

        stationRepository.save(station1);
        stationRepository.save(station2);

        em.flush();
        em.clear();


        /**
         *  when  : execution
         */
        TransitStationDTO dto1 = new TransitStationDTO(22333L, 11122L, 1, Direction.ASCENDING,
                false);
        TransitStationDTO dto2 = new TransitStationDTO(22345L, 22233L, 1, Direction.DESCENDING,
                true);

        transitStationService.join(dto1);
        transitStationService.join(dto2);

        em.flush();
        em.clear();



        /**
         *  then  : result
         */
        List<TransitStation> findTransitStationListByTransit = transitStationRepository.findAllByTransit(transit1);
        List<TransitStation> findTransitStationListByStation = transitStationRepository.findAllByStation(station1);

        assertThat(findTransitStationListByTransit.size()).isEqualTo(1);
        assertThat(findTransitStationListByStation.size()).isEqualTo(1);


    }

}