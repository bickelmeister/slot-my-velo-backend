package de.slotmyvelo.auth.web.controller

import de.slotmyvelo.auth.application.exception.InvalidCredentialsException
import de.slotmyvelo.auth.application.port.`in`.LoginCommand
import de.slotmyvelo.auth.application.port.`in`.LoginUseCase
import de.slotmyvelo.auth.application.port.`in`.RegisterUseCase
import de.slotmyvelo.auth.web.AuthDtoMapper
import de.slotmyvelo.auth.web.dto.*
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

private val logger = LoggerFactory.getLogger(AuthController::class.java)

@RestController
@RequestMapping("/auth")
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthUserResponse> {
        val command = AuthDtoMapper.toCommand(request)
        val user = registerUseCase.register(command)
        val response = AuthDtoMapper.toResponse(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = loginUseCase.login(
            LoginCommand(
                email = request.email,
                password = request.password
            )
        )
        return ResponseEntity.ok(token)
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(
        @Valid @RequestBody request: ForgotPasswordRequest
    ): ResponseEntity<Void> {
        loginUseCase.initiatePasswordReset(request.email)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/reset-password")
    fun resetPassword(
        @Valid @RequestBody request: ResetPasswordRequest
    ): ResponseEntity<Void> {
        loginUseCase.resetPassword(UUID.fromString(request.token), request.newPassword)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/new-password")
    fun newPassword(
        @Valid @RequestBody request: ChangePasswordRequest
    ): ResponseEntity<Void> {

        val loginCommand = LoginCommand(
            email = request.email,
            password = request.oldPassword
        )
        logger.info("User with email ${request.email} sets a new password.")
        loginUseCase.changePassword(loginCommand, request.oldPassword, request.newPassword)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/change-email-address")
    fun changeEmailAddress(
        @Valid @RequestBody request: ChangeEmailRequest
    ): ResponseEntity<Void> {
        val authentication = SecurityContextHolder.getContext().authentication
        val userId = authentication.name.toLongOrNull()
        ?: throw InvalidCredentialsException()
        loginUseCase.changeEmail(userId, request.newEmail)

        return ResponseEntity.noContent().build()
    }
}
