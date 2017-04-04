package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent car_owner table
 */
@Entity
class Car_Owner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var startDate = Date()
    var endDate: Date? = null

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
    var car: Car? = null

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
    var owner: Owner? = null

    constructor(startDate: Date, endDate: Date? = null) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
