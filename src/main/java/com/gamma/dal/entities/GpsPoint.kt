package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import java.util.*
import javax.persistence.*

/**
 * Model for GPS Points on a map
 */
@Entity
class GpsPoint {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var longitude: Double = 0.toDouble()
    var latitude: Double = 0.toDouble()
    var sequenceNumber: Int = 0
    var date: Instant = Instant.now()

    @ManyToOne
    @JoinTable(
            name = "region_gpspoint",
            joinColumns =  arrayOf(JoinColumn(name = "regionId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "gpsPointId", columnDefinition = "uuid")))
    var region: Region? = null

    @ManyToOne
    @JoinTable(
            name = "tracker_gpspoints",
            joinColumns =  arrayOf(JoinColumn(name = "trackerId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "gpsPointId", columnDefinition = "uuid")))
    var tracker: Tracker? = null

    constructor(longitude: Double, latitude: Double, sequenceNumber: Int, region: Region, tracker: Tracker, date: Instant = Instant.now()) {
        this.longitude = longitude
        this.latitude = latitude
        this.sequenceNumber = sequenceNumber
        this.region = region
        this.tracker = tracker
        this.date = date
    }

    constructor() {}
}
