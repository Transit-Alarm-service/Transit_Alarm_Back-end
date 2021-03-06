package com.transitAlarm.transitAlarm.transit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TransitStation {

    @Id
    @GeneratedValue
    @Column(name = "transit_station_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transit_id")
    private Transit transit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    /**
     * sequence of the route
     *
     * EX : if it is 9200 bus route's third station,
     * sequence == 3
     */
    @Column(name = "transit_station_seq")
    private Integer sequence;

    /**
     * direction of the transit-station
     * ["ASCENDING", "DESCENDING"]
     */
    @Column(name = "transit_station_dir")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    /**
     * whether to round station
     */
    @Column(name = "transit_station_isRound")
    private Boolean round;

    public TransitStation(Transit transit, Station station, Integer sequence, Direction direction, Boolean round) {
        this.transit = transit;
        this.station = station;
        this.sequence = sequence;
        this.direction = direction;
        this.round = round;
    }




}
