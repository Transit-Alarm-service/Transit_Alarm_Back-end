package com.transitAlarm.transitAlarm.api.repository;

import com.transitAlarm.transitAlarm.api.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityCodeRepository extends JpaRepository<CityCode, Long> {

    void deleteAll();
    List<CityCode> findAll();
    Optional<CityCode> findByCityCode(Long cityCode);
}
