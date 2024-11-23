package goroh.stepan.core.extensions

fun String.isEmptyOrBlank(): Boolean {
    return isEmpty() || isBlank()
}