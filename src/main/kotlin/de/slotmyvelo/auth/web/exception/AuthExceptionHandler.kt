package de.slotmyvelo.auth.web.exception

import de.slotmyvelo.auth.application.exception.EmailAlreadyUsedException
import de.slotmyvelo.auth.application.exception.InvalidCredentialsException
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentials(
        ex: InvalidCredentialsException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return buildError(HttpStatus.UNAUTHORIZED, ex.message, request)
    }

    @ExceptionHandler(EmailAlreadyUsedException::class)
    fun handleEmailAlreadyUsed(
        ex: EmailAlreadyUsedException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        return buildError(HttpStatus.CONFLICT, ex.message, request)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<Map<String, Any>> {
        val errors = ex.bindingResult.fieldErrors.map {
            ApiValidationError(field = it.field, message = it.defaultMessage ?: "Invalid value")
        }

        val response = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to HttpStatus.BAD_REQUEST.reasonPhrase,
            "message" to "Validation failed",
            "path" to request.requestURI,
            "errors" to errors
        )

        return ResponseEntity.badRequest().body(response)
    }

    private fun buildError(
        status: HttpStatus,
        message: String?,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val error = ApiError(
            status = status.value(),
            error = status.reasonPhrase,
            message = message ?: status.reasonPhrase,
            path = request.requestURI
        )
        return ResponseEntity.status(status).body(error)
    }
}
