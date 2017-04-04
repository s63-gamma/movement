package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

/**
 * Represent tracker table
 */
@Entity
class Tracker {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var authorisationCode: Int = 0
    var serialNumber: Int = 0
    var type: TrackerType? = null

    @OneToMany(cascade = arrayOf(CascadeType.MERGE), mappedBy = "tracker")
    var trackerCar: Collection<Tracker_Car> = ArrayList()

    @OneToMany(mappedBy = "tracker")
    var gpsPoints: Collection<GpsPoint> = ArrayList()

    @OneToMany(cascade = arrayOf(CascadeType.MERGE), mappedBy = "tracker")
    var trips: Collection<Trip> = ArrayList()

    constructor(authorisationCode: Int, serialNumber: Int, type: TrackerType, trackerCar: Collection<Tracker_Car> = ArrayList(), trips: Collection<Trip> = ArrayList()) {
        this.authorisationCode = authorisationCode
        this.serialNumber = serialNumber
        this.type = type
        this.trackerCar = trackerCar
        this.trips = trips
    }

    constructor()
}
