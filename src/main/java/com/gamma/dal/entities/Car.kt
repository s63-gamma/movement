package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import kotlin.collections.*

/**
 * Represent car table
 */
@Entity
class Car {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var buildingYear = 0
    var licensePlate = ""
    var weight = 0.0
    var milage = 0.0
    var type: CarType? = null
    var isStolen = false

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "car_owner",
            joinColumns =  arrayOf(JoinColumn(name = "carId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "carOwnerId", columnDefinition = "uuid")))
    var carOwner: Collection<Car_Owner> = ArrayList()

    @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(
            name = "car_rate",
            joinColumns =  arrayOf(JoinColumn(name = "carId",columnDefinition = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "rateId", columnDefinition = "uuid")))
    var rate: Rate? = null

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "join_car_tracker",
            joinColumns = arrayOf(JoinColumn(name = "carId", referencedColumnName = "uuid")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "carTrackerId", referencedColumnName = "uuid"))
    )
    var trackerCar: Collection<Tracker_Car> = ArrayList()



    constructor(buildingYear: Int, licensePlate: String, weight: Double, milage: Double, type: CarType, isStolen: Boolean = false , carOwner: Collection<Car_Owner> = ArrayList(), rate: Rate, trackerCar: Collection<Tracker_Car> = ArrayList()) {
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
