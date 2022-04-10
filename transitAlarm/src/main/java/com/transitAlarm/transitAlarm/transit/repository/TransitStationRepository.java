package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Station;
import com.transitAlarm.transitAlarm.transit.Transit;
import com.transitAlarm.transitAlarm.transit.TransitStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransitStationRepository extends JpaRepository<TransitStation, Long> {

    List<TransitStation> findAllByTransit(Transit transit);
    List<TransitStation> findAllByStation(Station station);
}
