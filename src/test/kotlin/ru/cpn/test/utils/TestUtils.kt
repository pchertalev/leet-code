package ru.cpn.test.utils

object TestUtils {

    val lineNumber: Int
        get() = Thread.currentThread().stackTrace[2].lineNumber
}
