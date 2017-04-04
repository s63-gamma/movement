package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent car table
 */
@Entity
class Car {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, name = "ID")
    val uuid: UUID? = null

    var buildingYear = 0
    var licensePlate = ""
    var weight = 0.0
    var milage = 0.0
    var type = ""
    var isStolen = false

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "owner_id")
    var carOwner: Collection<Car_Owner> = ArrayList()

    @OneToOne
    @JoinColumn(name = "rate_id")
    var rate: Rate? = null

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "carTracker_id")
    var trackerCar: Collection<Tracker_Car> = ArrayList()

    constructor(buildingYear: Int, licensePlate: String, weight: Double, milage: Double, type: String, isStolen: Boolean, carOwner: Collection<Car_Owner>, rate: Rate, trackerCar: Collection<Tracker_Car>) {
        this.buildingYear = buildingYear
        this.licensePlate = licensePlate
        this.weight = weight
        this.milage = milage
        this.type = type
        this.isStolen = isStolen
        this.carOwner = carOwner
        this.rate = rate
        this.trackerCar = trackerCar
    }

    constructor()
}
