package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
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
    val uuid: String = ""

    var startPoint: Double = 0.0
    var endPoint: Double = 0.0
    var totalDistance: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "carId")
    var car: Car? = null

    @OneToMany(mappedBy = "trip")
    var gpsPoints: List<GpsPoint>? = null

    constructor(startPoint: Double, endPoint: Double, totalDistance: Double) {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.totalDistance = totalDistance
    }

    constructor()
}
