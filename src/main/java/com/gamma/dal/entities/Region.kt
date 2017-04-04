package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

/**
 * Created by requinard on 2/21/17.
 */
@Entity
class Region {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var name: String = ""

    var longitude: Double = 0.0
    var latitude: Double = 0.0
    var radius: Int = 0
    var costMultiplier: Double = 0.0

    @OneToMany(mappedBy = "region")
    var gpsPoints: Collection<GpsPoint> = ArrayList()

    constructor(name: String, latitude: Double, longitude: Double, radius: Int, costMultiplier: Double, gpsPoints: Collection<GpsPoint> = ArrayList()) {
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.radius = radius
        this.costMultiplier = costMultiplier
        this.gpsPoints = gpsPoints
    }

    constructor()
}
