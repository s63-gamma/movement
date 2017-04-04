package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent Trip table
 */
@Entity
class Trip {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var totalDistance: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "gpsPoint_start_id")
    var startPoint: GpsPoint? = null

    @ManyToOne
    @JoinColumn(name = "gpsPoint_end_id")
    var endPoint: GpsPoint? = null

    @ManyToOne
    var tracker: Tracker? = null

    constructor(startPoint: GpsPoint, endPoint: GpsPoint, totalDistance: Double, tracker: Tracker) {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.totalDistance = totalDistance
        this.tracker = tracker
    }

    constructor()
}
