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

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "join_car_tracker",
            joinColumns = arrayOf(JoinColumn(name = "carTrackerId", referencedColumnName = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "carId", referencedColumnName = "uuid"))
    )
    lateinit var car: Car

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "join_tracker_car",
            joinColumns = arrayOf(JoinColumn(name = "carTrackerId", referencedColumnName = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "trackerId", referencedColumnName = "uuid"))
    )
    lateinit var tracker: Tracker

    var startDate = Date()
    var endDate: Date? = null

    constructor(startDate: Date, endDate: Date? = null) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
