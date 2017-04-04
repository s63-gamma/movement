package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent Owner table in database
 */
@Entity
class Owner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, name = "ID")
    val uuid: UUID? = null

    var username = ""
    var emailadres = ""
    var name = ""
    var surname = ""
    var phoneNumber = ""
    var residence = ""

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "car_id")
    var carOwner: Collection<Car_Owner> = ArrayList()

    constructor(username: String, emailadres: String, name: String, surname: String, phoneNumber: String, residence: String, carOwner: Collection<Car_Owner>) {
        this.username = username
        this.emailadres = emailadres
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.residence = residence
        this.carOwner = carOwner
    }

    constructor()
}
