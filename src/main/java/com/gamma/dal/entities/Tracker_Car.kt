package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

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

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
    lateinit var car: Car

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
    lateinit var tracker: Tracker

    var startDate = Date()
    var endDate: Date? = null

    constructor(startDate: Date, endDate: Date? = null) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
