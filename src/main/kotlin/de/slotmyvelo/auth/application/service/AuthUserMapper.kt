package de.slotmyvelo.auth.application.service

import de.slotmyvelo.auth.domain.model.AuthUser
import de.slotmyvelo.auth.infrastructure.persistence.entity.AuthUserEntity

object AuthUserMapper {

    fun toDomain(entity: AuthUserEntity): AuthUser =
        AuthUser(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            passwordHash = entity.passwordHash,
            role = entity.role,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun toEntity(domain: AuthUser): AuthUserEntity =
        AuthUserEntity(
            id = domain.id,
            name = domain.name,
            email = domain.email,
            passwordHash = domain.passwordHash,
            role = domain.role,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
}