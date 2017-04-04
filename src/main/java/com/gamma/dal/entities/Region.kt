package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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
    var radius: Double = 0.0
    var costMultiplier: Double = 0.0

    constructor(name: String, longitude: Double, latitude: Double, radius: Double, costMultiplier: Double) {
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.radius = radius
        this.costMultiplier = costMultiplier
    }

    constructor()
}
