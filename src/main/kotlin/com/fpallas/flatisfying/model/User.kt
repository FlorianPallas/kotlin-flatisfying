package com.fpallas.flatisfying.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fpallas.flatisfying.model.create.CreateUserData
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var name: String,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "groupId", nullable = true)
    var group: Group? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val shares: MutableSet<Share> = HashSet(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
) {
    constructor(data: CreateUserData) : this(data.name)
}
