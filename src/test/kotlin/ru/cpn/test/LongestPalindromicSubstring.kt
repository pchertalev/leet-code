package ru.cpn.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.cpn.test.utils.TestUtils.lineNumber
import java.util.stream.Stream

/**
5. Longest Palindromic Substring
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
 */
class LongestPalindromicSubstring {

    @ParameterizedTest
    @MethodSource("testData")
    @Suppress("UNUSED_PARAMETER")
    fun test(lineNumber: Int, expected: String, s: String) {
        Assertions.assertEquals(expected, longestPalindrome(s))
    }

    fun longestPalindrome(s: String): String {
        if (s.length <= 1) {
            return s
        }
        var maxLen = 1
        var maxStr = s.substring(0, 1)
        for (i in 0 until s.length) {
            for (j in i + maxLen..s.length) {
                if (j - i > maxLen && isPalindrome(s.substring(i, j))) {
                    maxLen = j - i
                    maxStr = s.substring(i, j)
                }
            }
        }
        return maxStr
    }

    private fun isPalindrome(str: String): Boolean {
        var left = 0
        var right = str.length - 1
        while (left < right) {
            if (str[left] != str[right]) {
                return false
            }
            left++
            right--
        }
        return true
    }

    companion object {
        private fun testItem(lineNumber: Int, expected: String, s: String) =
            Arguments.of(lineNumber, expected, s)

        @JvmStatic
        fun testData(): Stream<Arguments> = Stream.of(
            testItem(lineNumber, "bab", "babad"),
            testItem(lineNumber, "bb", "cbbd")
        )
    }
}