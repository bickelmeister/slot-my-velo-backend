package de.slotmyvelo.auth.application.exception

class EmailAlreadyUsedException(email: String) :
    RuntimeException("An account with email '$email' already exists.")
