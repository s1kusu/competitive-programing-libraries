package com.s1kusu.algorithm.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CombinationTest {

    @Test
    void testComb() {
        Combination c = new Combination(200_000, 1_000_000_007);
        assertEquals(1, c.comb(1, 1));
        assertEquals(1, c.comb(1, 0));
        assertEquals(1, c.comb(200_000, 200_000));
        assertEquals(10, c.comb(10, 1));
        assertEquals(45, c.comb(10, 2));
        assertEquals(120, c.comb(10, 3));
        assertEquals(999949972, c.comb(100_000, 2));
    }

}
