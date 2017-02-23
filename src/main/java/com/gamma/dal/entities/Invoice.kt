package com.gamma.dal.entities

import org.hibernate.annotations.GeneratorType
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Represent row in Invoice table
 */
@Entity
class Invoice {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: String = ""

    var date: Date = Date()
    var distance = 0.0
    var priceTotal = 0.0
    var status = 1
    var paymentCode = ""

    constructor(date: Date, distance: Double, priceTotal: Double, status: Int, paymentCode: String) {
        this.date = date
        this.distance = distance
        this.priceTotal = priceTotal
        this.status = status
        this.paymentCode = paymentCode
    }

    constructor()
}
