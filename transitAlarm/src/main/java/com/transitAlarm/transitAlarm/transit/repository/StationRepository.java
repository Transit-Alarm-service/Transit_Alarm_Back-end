package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {

    List<Station> findAllByName(String name);
}
