package de.slotmyvelo.user.web.controller

import de.slotmyvelo.auth.application.exception.InvalidTokenException
import de.slotmyvelo.user.application.port.`in`.UserProfileUseCase
import de.slotmyvelo.user.web.UserDtoMapper
import de.slotmyvelo.user.web.dto.UpdateUserProfileRequest
import de.slotmyvelo.user.web.dto.UserProfileResponse
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userProfileUseCase: UserProfileUseCase
) {

    @GetMapping("/me")
    fun getUserProfile(@AuthenticationPrincipal jwt: Jwt): UserProfileResponse {
        val sub = jwt.subject ?: throw InvalidTokenException()
        val userId = sub.toLongOrNull() ?: throw InvalidTokenException()
        val profile = userProfileUseCase.getProfile(userId)
        return UserDtoMapper.toResponse(profile)
    }

    @PatchMapping("/me")
    fun updateUserProfile(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody request: UpdateUserProfileRequest
    ): UserProfileResponse {
        val sub = jwt.subject ?: throw InvalidTokenException()
        val userId = sub.toLongOrNull() ?: throw InvalidTokenException()
        val profile = userProfileUseCase.updateProfile(userId, request)
        return UserDtoMapper.toResponse(profile)
    }
}
