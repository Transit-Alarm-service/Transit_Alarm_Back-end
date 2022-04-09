package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Area;
import com.transitAlarm.transitAlarm.transit.Transit;
import com.transitAlarm.transitAlarm.transit.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Commit
class TransitRepositoryTest {

    @Autowired
    TransitRepository transitRepository;
    @PersistenceContext
    EntityManager em;


    @Test
    void save() throws Exception {
        /**
        *  given : given condition
        */
        Transit transit1 = new Transit(Type.BUS, "9200", Area.INCHEON,
                LocalTime.of(9, 0), LocalTime.of(22, 0),
                60, true);
        Transit transit2 = new Transit(Type.BUS, "9200", Area.GYEONGGI,
                LocalTime.of(8, 0), LocalTime.of(23, 0),
                60, false);



        /**
        *  when : execution
        */
        transitRepository.save(transit1);
        transitRepository.save(transit2);

        em.flush();
        em.clear();

        List<Transit> findTransit = transitRepository.findAllByName("9200");


        /**
        *  then : result
        */
        assertThat(findTransit.size()).isEqualTo(2);
        Transit result = findTransit.get(0);

        assertThat(result.getName()).isEqualTo("9200");
    }

}