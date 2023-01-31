package com.fpallas.flatisfying.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "shares")
class Share(
    var price: Double,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "groupId")
    val group: Group,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchaseId")
    val purchase: Purchase,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    val user: User,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
    @JsonGetter
    fun userId(): Long {
        return user.id
    }
}
