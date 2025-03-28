package de.slotmyvelo.user.web

import de.slotmyvelo.user.domain.model.UserProfile
import de.slotmyvelo.user.web.dto.UserProfileResponse

object UserDtoMapper {
    fun toResponse(profile: UserProfile): UserProfileResponse =
        UserProfileResponse(
            id = profile.id,
            name = profile.name,
            email = profile.email,
            phoneNumber = profile.phoneNumber,
            role = profile.role,
            street = profile.street,
            city = profile.city,
            postalCode = profile.postalCode,
            country = profile.country,
            createdAt = profile.createdAt,
            updatedAt = profile.updatedAt
        )
}
