package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.UUID

/**
 * Model representing Region data table
 */
@Entity
class Region {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(unique = true)
    val uuid: String? = null

    var name: String? = null

    var longitude: Double = 0.0
    var latitude: Double = 0.0
    var radius: Double = 0.0
    var costMultiplier: Double = 0.0

    constructor(name: String, longitude: Double, latitude: Double, radius: Double, costMultiplier: Double) {
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.radius = radius
        this.costMultiplier = costMultiplier
    }

    constructor() {}
}
