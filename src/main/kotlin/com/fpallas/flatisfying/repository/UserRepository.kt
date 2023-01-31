package com.fpallas.flatisfying.repository

import com.fpallas.flatisfying.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
