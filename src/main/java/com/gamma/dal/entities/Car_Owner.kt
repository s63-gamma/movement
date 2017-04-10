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

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "join_car_owner",
            joinColumns =  arrayOf(JoinColumn(name = "carOwnerId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "carId", columnDefinition = "uuid")))
    var car: Car? = null

    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "join_owner_car",
            joinColumns =  arrayOf(JoinColumn(name = "carOwnerId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "ownerId", columnDefinition = "uuid")))

    var owner: Owner? = null

    constructor(startDate: Date, endDate: Date? = null) {
        this.startDate = startDate
        this.endDate = endDate
    }

    constructor()
}
