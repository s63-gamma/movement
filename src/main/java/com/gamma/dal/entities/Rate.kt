package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

/**
 * Entity to represent trip database table
 */
@Entity
class Rate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var type = ""
    var cost = 0
    var name = ""

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(
            name = "car_rate",
            joinColumns =  arrayOf(JoinColumn(name = "rateId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "carId", columnDefinition = "uuid")))
    var cars: Collection<Car> = ArrayList()

    constructor(type: String, cost: Int, name: String, cars: Collection<Car> = ArrayList()) {
        this.type = type
        this.cost = cost
        this.name = name
        this.cars = cars
    }

    constructor()
}
