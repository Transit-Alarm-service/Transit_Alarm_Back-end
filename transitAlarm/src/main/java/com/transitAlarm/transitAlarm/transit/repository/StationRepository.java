package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    List<Station> findAllByName(String name);
    Optional<Station> findByUniqueNumber(Long uniqueNumber);
}
