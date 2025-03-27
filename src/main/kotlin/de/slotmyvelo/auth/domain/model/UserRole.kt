package de.slotmyvelo.auth.domain.model

enum class UserRole {
    CUSTOMER,
    BICYCLE_BUSINESS_OWNER,
    BICYCLE_BUSINESS_EMPLOYEE;

    companion object {
        fun fromString(role: String): UserRole {
            return entries.firstOrNull { it.name == role.uppercase() }
                ?: throw IllegalArgumentException("Unknown role: $role")
        }
    }
}
