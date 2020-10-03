package com.s1kusu.data_structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class DJSetTest {

    @Test
    void testUnion() {
        DJSet dj = new DJSet(10);
        assertFalse(dj.union(0, 0));
        assertTrue(dj.union(1, 2));
        assertTrue(dj.union(2, 3));
        assertTrue(dj.union(4, 3));
        assertTrue(dj.union(5, 3));
        assertFalse(dj.union(1, 5));
        assertTrue(dj.union(1, 6));
    }

    @Test
    void testRoot() {
        DJSet dj = new DJSet(10);
        dj.union(0, 1);
        dj.union(1, 2);
        dj.union(2, 3);
        dj.union(9, 5);
        assertEquals(0, dj.root(0));
        assertEquals(0, dj.root(1));
        assertEquals(0, dj.root(2));
        assertEquals(0, dj.root(3));
        assertEquals(4, dj.root(4));
        assertEquals(9, dj.root(5));
        assertEquals(6, dj.root(6));
        assertEquals(7, dj.root(7));
        assertEquals(8, dj.root(8));
        assertEquals(9, dj.root(9));
    }


    @Test
    void testSame() {
        DJSet dj = new DJSet(10);
        dj.union(0, 1);
        dj.union(1, 2);
        dj.union(2, 3);
        dj.union(9, 5);
        assertTrue(dj.same(0, 0));
        assertTrue(dj.same(0, 1));
        assertTrue(dj.same(0, 2));
        assertTrue(dj.same(0, 3));
        assertTrue(dj.same(3, 0));
        assertTrue(dj.same(1, 3));
        assertTrue(dj.same(5, 9));
        assertFalse(dj.same(0, 4));
        assertFalse(dj.same(0, 5));
        assertFalse(dj.same(0, 6));
        assertFalse(dj.same(0, 7));
        assertFalse(dj.same(0, 8));
        assertFalse(dj.same(0, 9));
    }

    @Test
    void testCountSet() {
        DJSet dj = new DJSet(10);
        assertEquals(10, dj.countSet());
        dj.union(0, 1);
        assertEquals(9, dj.countSet());
        dj.union(1, 2);
        assertEquals(8, dj.countSet());
        dj.union(2, 3);
        assertEquals(7, dj.countSet());
        dj.union(9, 5);
        assertEquals(6, dj.countSet());
        dj.union(0, 0);
        assertEquals(6, dj.countSet());
        dj.union(0, 1);
        assertEquals(6, dj.countSet());
        dj.union(0, 9);
        assertEquals(5, dj.countSet());
    }


    @Test
    void testCountElement() {
        DJSet dj = new DJSet(10);
        assertEquals(1, dj.countElement(0));
        dj.union(0, 1);
        assertEquals(2, dj.countElement(0));
        assertEquals(2, dj.countElement(1));
        assertEquals(1, dj.countElement(9));
        dj.union(1, 2);
        assertEquals(3, dj.countElement(0));
        assertEquals(3, dj.countElement(1));
        dj.union(2, 3);
        assertEquals(4, dj.countElement(0));
        dj.union(9, 5);
        assertEquals(4, dj.countElement(0));
        assertEquals(2, dj.countElement(9));
        dj.union(9, 0);
        assertEquals(6, dj.countElement(0));
    }

    @Test
    void testGroups() {
        DJSet dj = new DJSet(10);
        dj.union(0, 1);
        dj.union(1, 2);
        dj.union(2, 3);
        dj.union(9, 5);
        List<List<Integer>> groups = dj.groups();
        assertEquals(0, groups.get(0).get(0));
        assertEquals(1, groups.get(0).get(1));
        assertEquals(2, groups.get(0).get(2));
        assertEquals(3, groups.get(0).get(3));
        assertEquals(4, groups.get(1).get(0));
        assertEquals(5, groups.get(2).get(0));
        assertEquals(9, groups.get(2).get(1));
        assertEquals(6, groups.get(3).get(0));
        assertEquals(7, groups.get(4).get(0));
        assertEquals(8, groups.get(5).get(0));
    }

}
