package de.slotmyvelo.user.infrastructure.persistence.repository

import de.slotmyvelo.user.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>
