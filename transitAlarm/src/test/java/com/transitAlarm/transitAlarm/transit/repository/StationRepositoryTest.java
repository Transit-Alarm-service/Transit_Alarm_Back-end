package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Area;
import com.transitAlarm.transitAlarm.transit.Station;
import com.transitAlarm.transitAlarm.transit.Transit;
import com.transitAlarm.transitAlarm.transit.Type;
import com.transitAlarm.transitAlarm.transit.dto.StationDTO;
import com.transitAlarm.transitAlarm.transit.dto.TransitDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class StationRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    StationRepository stationRepository;

    @Test
    void save() throws Exception {
        /**
         *  given : given condition
         */
        Station station1 =
                Station.createStation(new StationDTO(11122L, "인하대학교 정문", 37.36673247840618, 127.1081966687801));
        Station station2 =
                Station.createStation(new StationDTO(22233L, "정자역", 37.4514327, 126.651520));





        /**
         *  when  : execution
         */
        stationRepository.save(station1);
        stationRepository.save(station2);

        em.flush();
        em.clear();


        /**
         *  then  : result
         */
        List<Station> findStationList1 = stationRepository.findAllByName("인하대학교 정문");
        List<Station> findStationList2 = stationRepository.findAllByName("정자역");
        Optional<Station> findStation1 = stationRepository.findByUniqueNumber(11122L);

        assertThat(findStationList1.size()).isEqualTo(1);
        assertThat(findStationList2.size()).isEqualTo(1);

        Station ListStation1 = findStationList1.get(0);
        Station ListStation2 = findStationList2.get(0);

        assertThat(ListStation1.getName()).isEqualTo("인하대학교 정문");
        assertThat(ListStation2.getName()).isEqualTo("정자역");

        assertThat(findStation1.get().getName()).isEqualTo("인하대학교 정문");


    }
}
