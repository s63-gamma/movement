package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

/**
 * Represent car table
 */
@Entity
class Car {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: String = ""

    var buildingYear = 0
    var licensePlate = ""
    var weight = 0.0
    var milage = 0.0
    var type = ""

    @OneToMany(mappedBy = "car")
    var trips: List<Trip>? = null
    @ManyToOne
    @JoinColumn(name = "rateId")
    var rate: Rate? = null

    //todo trackers
    //todo owners

    constructor(buildingYear: Int, licensePlate: String, weight: Double, milage: Double, type: String) {
        this.buildingYear = buildingYear
        this.licensePlate = licensePlate
        this.weight = weight
        this.milage = milage
        this.type = type
    }

    constructor()
}
