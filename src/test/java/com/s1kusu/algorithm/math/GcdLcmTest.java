package com.s1kusu.algorithm.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

import static java.time.Duration.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GcdLcmTest {

    @Test
    void testGcd() {
        assertAll("gcd",
                () -> assertEquals(6, GcdLcm.gcd(12, 18)),
                () -> assertEquals(6, GcdLcm.gcd(18, 12)),
                () -> assertEquals(1, GcdLcm.gcd(1000000007, 1000000009)),
                () -> assertEquals(1, GcdLcm.gcd(1, 4)),
                () -> assertEquals(6, GcdLcm.gcd(0, 6)),
                () -> assertEquals(14, GcdLcm.gcd(14, 0)),
                () -> assertEquals(0, GcdLcm.gcd(0, 0)),
                () -> assertEquals(1, GcdLcm.gcd(3, Integer.MAX_VALUE)),
                () -> assertEquals(Integer.MAX_VALUE, GcdLcm.gcd(Integer.MAX_VALUE, Integer.MAX_VALUE)),
                () -> assertEquals(2, GcdLcm.gcd(2, Integer.MIN_VALUE)),
                () -> assertEquals(6, GcdLcm.gcd(-12, -18))
        );
        assertThrows(IllegalArgumentException.class, () -> {GcdLcm.gcd(Integer.MIN_VALUE, Integer.MIN_VALUE);});
    }

    @Test
    void testLcm() {
        assertAll("lcm",
                () -> assertEquals(36, GcdLcm.lcm(12, 18)),
                () -> assertEquals(36, GcdLcm.lcm(18, 12)),
                () -> assertEquals(5, GcdLcm.lcm(1, 5)),
                () -> assertEquals(0, GcdLcm.lcm(0, 7)),
                () -> assertEquals(0, GcdLcm.lcm(0, 0)),
                () -> assertEquals(6, GcdLcm.lcm(-2, 3)),
                () -> assertEquals(1000000016000000063L, GcdLcm.lcm(1000000007, 1000000009)),
                () -> assertEquals(4611686011984936962L, GcdLcm.lcm(Integer.MAX_VALUE-1, Integer.MAX_VALUE)),
                () -> assertEquals(4611686016279904256L, GcdLcm.lcm(Integer.MIN_VALUE+1, Integer.MIN_VALUE)),
                () -> assertEquals(Integer.MIN_VALUE, GcdLcm.lcm(Integer.MIN_VALUE, Integer.MIN_VALUE))
        );
    }

    @Test
    void testGcdEx() {
        assertAll("gcdEx",
                () -> {
                    int[] res = GcdLcm.gcdEx(18, 12);
                    assertEquals(6, res[0]);
                    assertEquals(1, res[1]);
                    assertEquals(-1, res[2]);
                },
                () -> {
                    int[] res = GcdLcm.gcdEx(12, 18);
                    assertEquals(6, res[0]);
                    assertEquals(-1, res[1]);
                    assertEquals(1, res[2]);
                },
                () -> {
                    int[] res = GcdLcm.gcdEx(5, 7);
                    assertEquals(1, res[0]);
                    assertEquals(3, res[1]);
                    assertEquals(-2, res[2]);
                },
                () -> {
                    int[] res = GcdLcm.gcdEx(0, 42);
                    assertEquals(42, res[0]);
                    assertEquals(0, res[1]);
                    assertEquals(1, res[2]);
                },
                () -> {
                    int[] res = GcdLcm.gcdEx(42, 0);
                    assertEquals(42, res[0]);
                    assertEquals(1, res[1]);
                    assertEquals(0, res[2]);
                },
                () -> {
                    int[] res = GcdLcm.gcdEx(0, 0);
                    assertEquals(0, res[0]);
                    assertEquals(1, res[1]);
                    assertEquals(0, res[2]);
                }
        );
    }

    @Nested
    @Disabled
    class SpeedTest{
        static final int N = 20_000_000;

        @Test
        void testSpeedGcd() {
            long startTime = System.currentTimeMillis();
            assertTimeoutPreemptively(ofMillis(1000), () -> {
                for (int i = 0; i < N; i++) {
                    GcdLcm.gcd(i+1, i+2);
                }
            });
            long endTime = System.currentTimeMillis();
            System.out.printf("time to run gcd() %,d times : %,d ms%n", N, endTime - startTime);
        }

        @Test
        void testSpeedLcm() {
            long startTime = System.currentTimeMillis();
            assertTimeoutPreemptively(ofMillis(1000), () -> {
                for (int i = 0; i < N; i++) {
                    GcdLcm.lcm(i+1, i+2);
                }
            });
            long endTime = System.currentTimeMillis();
            System.out.printf("time to run lcm() %,d times : %,d ms%n", N, endTime - startTime);
        }

        @Test
        void testSpeedGdcEx() {
            long startTime = System.currentTimeMillis();
            assertTimeoutPreemptively(ofMillis(1000), () -> {
                for (int i = 0; i < N; i++) {
                    GcdLcm.gcdEx(i+1, i+2);
                }
            });
            long endTime = System.currentTimeMillis();
            System.out.printf("time to run gcdEx() %,d times : %,d ms%n", N, endTime - startTime);
        }
    }
}
