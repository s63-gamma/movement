package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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

    var startPoint: Double = 0.0
    var endPoint: Double = 0.0
    var totalDistance: Double = 0.0

    constructor(startPoint: Double, endPoint: Double, totalDistance: Double) {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.totalDistance = totalDistance
    }

    constructor()
}
