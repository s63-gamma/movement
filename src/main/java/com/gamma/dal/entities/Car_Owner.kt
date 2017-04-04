package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Represent car_owner table
 */
@Entity
class Car_Owner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, name = "ID")
    val uuid: UUID? = null

    var startDate = Date()
    var endDate = Date()

    constructor(startDate: Date, endDate: Date) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
