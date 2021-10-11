package ai.akun.core.extensions

fun Long.toSeconds(): Long {
    return this/1000
}

fun <T> merge(first: MutableList<T>, second: MutableList<T>): List<T> {
    return first + second
}