package com.s1kusu.algorithm.math;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

public class PrimeTest {

    @Test
    void testIsPrime() {
        assertAll("isPrime",
                () -> assertFalse(Prime.isPrime(0)),
                () -> assertFalse(Prime.isPrime(1)),
                () -> assertTrue(Prime.isPrime(2)),
                () -> assertTrue(Prime.isPrime(3)),
                () -> assertFalse(Prime.isPrime(4)),
                () -> assertTrue(Prime.isPrime(1_000_000_007)),
                () -> assertFalse(Prime.isPrime(-2)),
                () -> assertTrue(Prime.isPrime(Integer.MAX_VALUE))
        );
    }

    @Test
    void testGetDivisor() {
        assertAll("getDivisor",
                () -> assertEquals(0, Prime.getDivisor(0).length),
                () -> assertEquals(0, Prime.getDivisor(-1).length),
                () -> {
                    int[] divs = Prime.getDivisor(1);
                    assertEquals(1, divs.length);
                    assertEquals(1, divs[0]);
                },
                () -> {
                    int[] divs = Prime.getDivisor(2);
                    assertEquals(2, divs.length);
                    assertEquals(1, divs[0]);
                    assertEquals(2, divs[1]);
                },
                () -> {
                    int[] divs = Prime.getDivisor(36);
                    assertEquals(9, divs.length);
                    assertEquals(1, divs[0]);
                    assertEquals(2, divs[1]);
                    assertEquals(3, divs[2]);
                    assertEquals(4, divs[3]);
                    assertEquals(6, divs[4]);
                    assertEquals(9, divs[5]);
                    assertEquals(12, divs[6]);
                    assertEquals(18, divs[7]);
                    assertEquals(36, divs[8]);
                },
                () -> {
                    int[] divs = Prime.getDivisor(Integer.MAX_VALUE);
                    assertEquals(2, divs.length);
                    assertEquals(1, divs[0]);
                    assertEquals(Integer.MAX_VALUE, divs[1]);
                }
        );
    }

    @Test
    void testGetPrimeFactors() {
        assertAll("getPrimeFactors",
                () -> assertEquals(0, Prime.getPrimeFactors(-2).size()),
                () -> assertEquals(0, Prime.getPrimeFactors(-1).size()),
                () -> assertEquals(0, Prime.getPrimeFactors(0).size()),
                () -> assertEquals(0, Prime.getPrimeFactors(1).size()),
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(2);
                    assertEquals(1, f.size());
                    assertEquals(1, f.get(2));
                },
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(36);
                    assertEquals(2, f.size());
                    assertEquals(2, f.get(2));
                    assertEquals(2, f.get(3));
                },
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(Integer.MAX_VALUE);
                    assertEquals(1, f.size());
                    assertEquals(1, f.get(Integer.MAX_VALUE));
                }
        );
    }

    @Test
    void testGetPrimeFactorsArray() {
        assertAll("getPrimeFactors (Array)",
                () -> assertEquals(0, Prime.getPrimeFactors(new int[] {-2, -1, 0, 1}).size()),
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(new int[] {-2, -1, 0, 1, 2});
                    assertEquals(1, f.size());
                    assertEquals(1, f.get(2));
                },
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(new int[] {2, 3, 4, 5, 6});
                    assertEquals(3, f.size());
                    assertEquals(4, f.get(2));
                    assertEquals(2, f.get(3));
                    assertEquals(1, f.get(5));
                },
                () -> {
                    TreeMap<Integer, Integer> f = Prime.getPrimeFactors(new int[] {2, 3, 4, 5, Integer.MAX_VALUE});
                    assertEquals(4, f.size());
                    assertEquals(3, f.get(2));
                    assertEquals(1, f.get(3));
                    assertEquals(1, f.get(5));
                    assertEquals(1, f.get(Integer.MAX_VALUE));
                }
        );
    }

    @Test
    void testGetPrimeNumbers() {
        assertAll("getPrimeNumbers",
                () -> assertEquals(0, Prime.getPrimeNumbers(10, 3).length),
                () -> assertEquals(0, Prime.getPrimeNumbers(0, 1).length),
                () -> assertEquals(0, Prime.getPrimeNumbers(1).length),
                () -> assertEquals(0, Prime.getPrimeNumbers(-5, -2).length),
                () -> assertEquals(0, Prime.getPrimeNumbers(-5).length),
                () -> {
                    int[] p = Prime.getPrimeNumbers(2, 2);
                    assertEquals(1, p.length);
                    assertEquals(2, p[0]);
                },
                () -> {
                    int[] p = Prime.getPrimeNumbers(2);
                    assertEquals(1, p.length);
                    assertEquals(2, p[0]);
                },
                () -> {
                    int[] p = Prime.getPrimeNumbers(-10, 5);
                    assertEquals(3, p.length);
                    assertEquals(2, p[0]);
                    assertEquals(3, p[1]);
                    assertEquals(5, p[2]);
                },
                () -> {
                    int[] p = Prime.getPrimeNumbers(5);
                    assertEquals(3, p.length);
                    assertEquals(2, p[0]);
                    assertEquals(3, p[1]);
                    assertEquals(5, p[2]);
                },
                () -> {
                    int[] p = Prime.getPrimeNumbers(10, 20);
                    assertEquals(4, p.length);
                    assertEquals(11, p[0]);
                    assertEquals(13, p[1]);
                    assertEquals(17, p[2]);
                    assertEquals(19, p[3]);
                }
        );
    }

}
