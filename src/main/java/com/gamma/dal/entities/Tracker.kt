package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent tracker table
 */
@Entity
class Tracker {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var authorisationCode: Int = 0
    var serialNumber: Int = 0
    var type = ""

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "tracker_id")
    var trackerCar: Collection<Tracker_Car> = ArrayList()

    constructor(authorisationCode: Int, serialNumber: Int, type: String, trackerCar: Collection<Tracker_Car>) {
        this.authorisationCode = authorisationCode
        this.serialNumber = serialNumber
        this.type = type
        this.trackerCar = trackerCar
    }

    constructor()
}
