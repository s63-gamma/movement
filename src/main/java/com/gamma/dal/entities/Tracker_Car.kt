package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Represent tracker table
 */
@Entity
class Tracker_Car {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var startDate = Date()
    var endDate = Date()

    constructor(startDate: Date, endDate: Date) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
