package com.transitAlarm.transitAlarm.transit.service;

import com.transitAlarm.transitAlarm.transit.Station;
import com.transitAlarm.transitAlarm.transit.Transit;
import com.transitAlarm.transitAlarm.transit.TransitStation;
import com.transitAlarm.transitAlarm.transit.dto.TransitStationDTO;
import com.transitAlarm.transitAlarm.transit.repository.StationRepository;
import com.transitAlarm.transitAlarm.transit.repository.TransitRepository;
import com.transitAlarm.transitAlarm.transit.repository.TransitStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransitStationService {

    private final TransitStationRepository transitStationRepository;
    private final TransitRepository transitRepository;
    private final StationRepository stationRepository;

    public TransitStation join(TransitStationDTO dto) {
        Optional<Transit> findTransit = transitRepository.findByUniqueNumber(dto.getTransitUniqueNumber());
        Optional<Station> findStation = stationRepository.findByUniqueNumber(dto.getStationUniqueNumber());

        if (findTransit.isEmpty() || findStation.isEmpty()) {
            /**
             * wrong transit number or station number
             * this service is only for DB, so it doesn't have to return exception object
             */
            log.error("ERROR : WRONG transit unique number or station unique number");
            return null;
        }

        TransitStation transitStation =
                new TransitStation(findTransit.get(), findStation.get(), dto.getSequence(),
                        dto.getDirection(), dto.getRound());

        transitStationRepository.save(transitStation);

        return transitStation;
    }

    public List<TransitStation> findAllByTransit(Long uniqueNum) {
        Optional<Transit> findTransit = transitRepository.findByUniqueNumber(uniqueNum);

        if (findTransit.isEmpty()) {
            log.error("ERROR : invalid transit unique number");
            return null;
        }

        return transitStationRepository.findAllByTransit(findTransit.get());
    }

    public List<TransitStation> findAllByStation(Long uniqueNum) {
        Optional<Station> findStation = stationRepository.findByUniqueNumber(uniqueNum);

        if(findStation.isEmpty()) {
            log.error("ERROR : invalid station unique number");
            return null;
        }

        return transitStationRepository.findAllByStation(findStation.get());
    }

}
