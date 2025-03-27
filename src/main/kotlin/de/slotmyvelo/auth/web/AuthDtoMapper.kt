package de.slotmyvelo.auth.web

import de.slotmyvelo.auth.domain.model.AuthUser
import de.slotmyvelo.auth.web.dto.AuthUserResponse
import de.slotmyvelo.auth.web.dto.RegisterRequest
import de.slotmyvelo.auth.application.port.`in`.RegisterCommand

object AuthDtoMapper {

    fun toCommand(dto: RegisterRequest): RegisterCommand =
        RegisterCommand(
            name = dto.name,
            email = dto.email,
            password = dto.password,
            role = dto.role
        )

    fun toResponse(user: AuthUser): AuthUserResponse =
        AuthUserResponse(
            id = user.id,
            name = user.name,
            email = user.email,
            role = user.role.name
        )
}
