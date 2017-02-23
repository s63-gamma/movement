package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Represent Owner table in database
 */
@Entity
class Owner {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: String = ""

    var username = ""
    var emailadres = ""
    var name = ""
    var surname = ""
    var phoneNumber = ""
    var residence = ""

    constructor(username: String, emailadres: String, name: String, surname: String, phoneNumber: String, residence: String) {
        this.username = username
        this.emailadres = emailadres
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.residence = residence
    }

    constructor()
}
