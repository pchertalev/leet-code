package ru.cpn.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.cpn.test.utils.TestUtils.lineNumber
import java.util.stream.Stream

/**
 * 6. Zigzag Conversion
 * Medium
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
class ZigzagConversion {

    @ParameterizedTest
    @MethodSource("testData")
    @Suppress("UNUSED_PARAMETER")
    fun test(lineNumber: Int, expected: String, s: String, numRows: Int) {
        Assertions.assertEquals(expected, convert(s, numRows))
    }

    private fun convert(s: String, numRows: Int): String {
        val result = Array(numRows) { StringBuilder() }
        var index = 0
        var step = 1
        s.forEach {
            result[index].append(it)
            if (numRows > 1) {
                if (index == 0 && step < 0 || index + step == result.size) {
                    step = 0 - step
                }
                index += step
            }
        }
        return result.joinToString(separator = "") { it.toString() }
    }

    companion object {
        private fun testItem(lineNumber: Int, expected: String, s: String, numRows: Int) =
            Arguments.of(lineNumber, expected, s, numRows)

        @JvmStatic
        fun testData(): Stream<Arguments> = Stream.of(
            testItem(lineNumber, "PAHNAPLSIIGYIR", "PAYPALISHIRING", 3),
            testItem(lineNumber, "PINALSIGYAHRPI", "PAYPALISHIRING", 4),
            testItem(lineNumber, "A", "A", 1),
            testItem(lineNumber, "AB", "AB", 1)
        )
    }
}