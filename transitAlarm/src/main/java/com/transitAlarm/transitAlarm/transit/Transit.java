package com.transitAlarm.transitAlarm.transit;

import lombok.extern.java.Log;

import javax.persistence.*;

//@Entity
//@DiscriminatorValue("dtype")
public class Transit {

//    @Id
//    @GeneratedValue
//    @Column(name = "transit_id")
    private Long Id;

//    @Column(name = "transit_name")
    private String name;

//    @Column(name = "transit_area")
    private Area area;


}
