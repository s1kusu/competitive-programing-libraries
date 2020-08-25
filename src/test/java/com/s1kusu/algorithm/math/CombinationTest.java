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

    @Test
    void testPerm() {
        Combination c = new Combination(200_000, 1_000_000_007);
        assertEquals(1, c.perm(1, 1));
        assertEquals(1, c.perm(1, 0));
        assertEquals(10, c.perm(10, 1));
        assertEquals(90, c.perm(10, 2));
        assertEquals(720, c.perm(10, 3));
        assertEquals(200_000, c.perm(200_000, 1));
        assertEquals(999899937, c.perm(100_000, 2));
    }

    @Test
    void testHom() {
        Combination c = new Combination(200_000, 1_000_000_007);
        assertEquals(1, c.hom(1, 1));
        assertEquals(1, c.hom(1, 0));
        assertEquals(10, c.hom(10, 1));
        assertEquals(55, c.hom(10, 2));
        assertEquals(220, c.hom(10, 3));
        assertEquals(200_000, c.hom(200_000, 1));
        assertEquals(49965, c.hom(100_000, 2));
    }

}
