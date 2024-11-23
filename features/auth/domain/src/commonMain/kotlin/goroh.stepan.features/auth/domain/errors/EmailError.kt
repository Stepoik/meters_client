package goroh.stepan.features.auth.domain.errors

class EmailError(message: String = "Некорректная почта"): Error(message) {
}