package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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

    constructor(authorisationCode: Int, serialNumber: Int, type: String) {
        this.authorisationCode = authorisationCode
        this.serialNumber = serialNumber
        this.type = type
    }

    constructor()
}
