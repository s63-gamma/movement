package com.gamma.dal.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Represent row in Invoice table
 */
@Entity
class Invoice {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    val uuid: UUID? = null

    var date: Date = Date()
    var distance = 0.0
    var priceTotal = 0.0
    var status = 1
    var paymentCode = ""

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var owner: Owner? = null

    constructor(date: Date, distance: Double, priceTotal: Double, status: Int, paymentCode: String, owner: Owner) {
        this.date = date
        this.distance = distance
        this.priceTotal = priceTotal
        this.status = status
        this.paymentCode = paymentCode
        this.owner = owner
    }

    constructor()
}
