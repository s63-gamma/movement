package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

/**
 * Represent Owner table in database
 */
@Entity
class Owner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var username = ""
    var emailadres = ""
    var name = ""
    var surname = ""
    var phoneNumber = ""
    var residence = ""

    @OneToMany(cascade = arrayOf(CascadeType.MERGE), mappedBy = "owner")
    var carOwner: Collection<Car_Owner> = ArrayList()

    constructor(username: String, emailadres: String, name: String, surname: String, phoneNumber: String, residence: String, carOwner: Collection<Car_Owner> = ArrayList()) {
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
