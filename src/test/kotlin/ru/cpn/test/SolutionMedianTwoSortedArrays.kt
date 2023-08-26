package ru.cpn.test

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class SolutionMedianTwoSortedArrays {

    @ParameterizedTest
    @MethodSource("testData")
    fun test(expected: Double, nums1: IntArray, nums2: IntArray) {
        assertEquals(expected, findMedianSortedArrays(nums1, nums2))
    }

    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val rNumsSize = nums1.size + nums2.size

        if (rNumsSize == 0) return 0.0
        if (rNumsSize == 1) return if (nums1.isEmpty()) nums2[0].toDouble() else nums1[0].toDouble()

        var index1 = 0
        var index1Last = nums1.lastIndex
        var index2 = 0
        var index2Last = nums2.lastIndex
        val medianIndex1 = if (rNumsSize % 2 == 1) rNumsSize / 2 else rNumsSize / 2 - 1
        val medianIndex2 = if (rNumsSize % 2 == 0) medianIndex1 + 1 else medianIndex1

        var rIndex = 0
        var rIndexLast = rNumsSize - 1
        var rLeft: Int
        var rRight: Int

        println("medianIndex1: $medianIndex1, medianIndex2: $medianIndex2")

        do {

            val min1 = nums1.getOrNull(index1) ?: Int.MAX_VALUE
            val min2 = nums2.getOrNull(index2) ?: Int.MAX_VALUE

            if (min1 < min2) {
                rLeft = min1
                index1++
            } else {
                rLeft = min2
                index2++
            }

            val max1 = nums1.getOrNull(index1Last) ?: Int.MIN_VALUE
            val max2 = nums2.getOrNull(index2Last) ?: Int.MIN_VALUE

            if (max1 > max2) {
                rRight = max1
                index1Last--
            } else {
                rRight = max2
                index2Last--
            }

            if (rIndex == medianIndex1 && rIndexLast == medianIndex2)
                break

            rIndex++
            rIndexLast--
        } while (true)


        return (rLeft + rRight) / 2.0
    }

    companion object {
        private fun testItem(expected: Double, nums1: IntArray, nums2: IntArray) = Arguments.of(expected, nums1, nums2)

        @JvmStatic
        fun testData(): Stream<Arguments> = Stream.of(
            testItem(1.5, intArrayOf(1), intArrayOf(2)),
            testItem(2.0, intArrayOf(1, 3), intArrayOf(2)),
            testItem(2.5, intArrayOf(1, 2), intArrayOf(3, 4)),
            testItem(-1.0, intArrayOf(3), intArrayOf(-2,-1))
        )
    }
}
