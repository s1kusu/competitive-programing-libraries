package com.s1kusu.algorithm.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    static int[] a = new int[] {1, 2, 3, 5, 5, 5, 7, 9};

    @Test
    void testLowerBound() {
        assertEquals(0, BinarySearch.lowerBound(a, 0));
        assertEquals(0, BinarySearch.lowerBound(a, 1));
        assertEquals(3, BinarySearch.lowerBound(a, 4));
        assertEquals(3, BinarySearch.lowerBound(a, 5));
    }

    @Test
    void testUpperBound() {
        assertEquals(0, BinarySearch.upperBound(a, 0));
        assertEquals(1, BinarySearch.upperBound(a, 1));
        assertEquals(3, BinarySearch.upperBound(a, 4));
        assertEquals(6, BinarySearch.upperBound(a, 5));
    }
}
