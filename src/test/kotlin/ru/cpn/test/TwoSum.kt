package ru.cpn.test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ru.cpn.test.utils.TestUtils.lineNumber
import java.lang.IllegalArgumentException
import java.util.TreeSet
import java.util.stream.Stream

/**
 * Two Sum
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 */
class TwoSum {

    @ParameterizedTest
    @MethodSource("testData")
    @Suppress("UNUSED_PARAMETER")
    fun test(lineNumber: Int, expected: IntArray, nums: IntArray, target: Int) {
        Assertions.assertArrayEquals(expected, twoSumO1(nums, target))
    }

    fun twoSumO1(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { index, i ->
            val balance = map[target - i]
            if (balance != null)
                return intArrayOf(balance, index)
            map.putIfAbsent(i, index)
        }
        throw IllegalArgumentException("No solution")
    }

    fun twoSumON2(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size - 1)
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target)
                    return intArrayOf(i, j)
            }
        throw IllegalArgumentException("No solution")
    }

    companion object {
        private fun testItem(lineNumber: Int, expected: IntArray, nums: IntArray, target: Int) =
            Arguments.of(lineNumber, expected, nums, target)

        @JvmStatic
        fun testData(): Stream<Arguments> = Stream.of(
            testItem(lineNumber, intArrayOf(0, 1), intArrayOf(2, 7, 11, 15), 9),
            testItem(lineNumber, intArrayOf(1, 2), intArrayOf(3, 2, 4), 6),
            testItem(lineNumber, intArrayOf(0, 1), intArrayOf(3, 3), 6)
        )
    }
}