package com.fpallas.flatisfying.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "purchases")
class Purchase(
    var name: String,

    var price: Double,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "groupId")
    var group: Group,

    @OneToMany(mappedBy = "purchase")
    val shares: MutableSet<Share> = HashSet(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
)
