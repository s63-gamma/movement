package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator

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
    val uuid: String? = null

    var longitude: Double = 0.toDouble()
    var latitude: Double = 0.toDouble()
    var sequenceNumber: Int = 0

    @ManyToOne
    @JoinColumn(name = "regionId")
    var region: Region? = null

    @ManyToOne
    @JoinColumn(name = "tripId")
    var trip: Trip? = null

    @ManyToOne
    @JoinColumn(name = "trackerId")
    var tracker: Tracker? = null

    constructor(longitude: Double, latitude: Double, sequenceNumber: Int) {
        this.longitude = longitude
        this.latitude = latitude
        this.sequenceNumber = sequenceNumber
    }

    constructor() {}
}
