package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Area;
import com.transitAlarm.transitAlarm.transit.Transit;
import com.transitAlarm.transitAlarm.transit.Type;
import com.transitAlarm.transitAlarm.transit.dto.TransitDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;


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

        Transit transit1 = Transit.createTransit(new TransitDTO(22333L, Type.BUS, "9200", Area.INCHEON,
                "09:00", "22:00", 60, true));
        Transit transit2 = Transit.createTransit(new TransitDTO(22345L, Type.BUS, "9200", Area.GYEONGGI,
                "08:00", "23:00", 30, false));


        /**
        *  when : execution
        */
        transitRepository.save(transit1);
        transitRepository.save(transit2);

        em.flush();
        em.clear();

        List<Transit> findTransitList = transitRepository.findAllByName("9200");
        Optional<Transit> findTransit = transitRepository.findByUniqueNumber(22333L);


        /**
        *  then : result
        */
        assertThat(findTransitList.size()).isEqualTo(2);
        Transit result = findTransitList.get(0);

        assertThat(result.getName()).isEqualTo("9200");
        assertThat(findTransit.get().getName()).isEqualTo("9200");
    }

}