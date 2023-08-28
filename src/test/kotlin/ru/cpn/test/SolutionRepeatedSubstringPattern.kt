package ru.cpn.test

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.cpn.test.utils.TestUtils.lineNumber
import java.util.stream.Stream
import kotlin.test.assertEquals

class SolutionRepeatedSubstringPattern {

    @ParameterizedTest
    @MethodSource("testData")
    @Suppress("UNUSED_PARAMETER")
    fun test(lineNumber: Int, expected: Boolean, s: String) {
        assertEquals(expected, repeatedSubstringPattern(s))
    }

    private fun repeatedSubstringPattern(s: String): Boolean {
        for (i in 1..s.length / 2) {
            val ss = s.take(i)
            val target = ss.repeat(s.length / ss.length)
            if (target == s) return true
        }
        return false
    }

    companion object {
        private fun testItem(lineNumber: Int, expected: Boolean, s: String) = Arguments.of(lineNumber, expected, s)

        @JvmStatic
        fun testData(): Stream<Arguments> = Stream.of(
            testItem(lineNumber, true, "abab"), //It is the substring "ab" twice.
            testItem(lineNumber, false, "aba"),
            testItem(lineNumber, true, "abcabcabcabc"), //It is the substring "abc" four times or the substring "abcabc" twice.
        )
    }
}