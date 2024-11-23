package goroh.stepan.features.auth.domain

fun isPasswordValid(password: String): Boolean {
    return password.length >= 8
}

fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    return email.matches(Regex(emailRegex))
}