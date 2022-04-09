package com.transitAlarm.transitAlarm.transit;
import com.transitAlarm.transitAlarm.transit.dto.TransitDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Transit {

    @Id
    @GeneratedValue
    @Column(name = "transit_id")
    private Long Id;

    /**
     * transit type
     * ["BUS", "SUBWAY", "TRAIN", "AIRPORT_BUS"]
     */
    @Column(name = "transit_type")
    private Type type;

    /**
     * transit name
     * EX : 9200
     */
    @Column(name = "transit_name")
    private String name;


    /**
     * transit area
     * EX : SEOUL
     */
    @Column(name = "transit_area")
    @Enumerated(EnumType.STRING)
    private Area area;

    /**
     * 기점, 종점은 쿼리로
     */


    /**
     * transit start time & end time
     * EX : 09:00:00
     */
    @Column(name = "transit_start_time")
    private LocalTime startTime;
    @Column(name = "transit_end_time")
    private LocalTime endTime;

    /**
     * transit interval
     * minute by minute
     * EX : 30
     */
    @Column(name = "transit_interval")
    private Integer interval;


    /**
     * whether to rotate
     * EX : true
     */
    @Column(name = "transit_round")
    private Boolean round;


    public Transit(Type type, String name, Area area, LocalTime startTime,
                   LocalTime endTime, Integer interval, Boolean round) {
        this.type = type;
        this.name = name;
        this.area = area;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
        this.round = round;
    }

    public static Transit createTransit(TransitDTO dto) {

        /**
         * Time Format Example : HH:mm
         */
        LocalTime startTime =
                LocalTime.of(Integer.parseInt(dto.getStartTime().substring(0, 2)),
                        Integer.parseInt(dto.getStartTime().substring(2)));
        LocalTime endTime =
                LocalTime.of(Integer.parseInt(dto.getEndTime().substring(0, 2)),
                        Integer.parseInt(dto.getEndTime().substring(2)));

        return new Transit(dto.getType(), dto.getName(), dto.getArea(),
                startTime, endTime, dto.getInterval(), dto.getRound());

    }

}
