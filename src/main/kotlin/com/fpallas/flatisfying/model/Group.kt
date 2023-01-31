package com.fpallas.flatisfying.model

import com.fpallas.flatisfying.model.create.CreateGroupData
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "groups")
class Group(
    var name: String,

    var token: String = UUID.randomUUID().toString(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToMany(mappedBy = "group")
    val purchases: MutableSet<Purchase> = HashSet(),

    @OneToMany(mappedBy = "group")
    val shares: MutableSet<Share> = HashSet(),

    @OneToMany(mappedBy = "group")
    val users: MutableSet<User> = HashSet(),
) {
    constructor(data: CreateGroupData) : this(data.name)
}
