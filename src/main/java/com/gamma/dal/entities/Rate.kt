package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Entity to represent trip database table
 */
@Entity
class Rate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: String = ""

    var type = ""
    var cost = 0
    var name = ""

    constructor(type: String, cost: Int, name: String) {
        this.type = type
        this.cost = cost
        this.name = name
    }

    constructor()
}
