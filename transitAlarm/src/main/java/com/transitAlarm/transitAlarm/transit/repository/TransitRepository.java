package com.transitAlarm.transitAlarm.transit.repository;

import com.transitAlarm.transitAlarm.transit.Transit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransitRepository extends JpaRepository<Transit, Long> {

    List<Transit> findAllByName(String name);
    Optional<Transit> findByUniqueNumber(Long uniqueNum);

}