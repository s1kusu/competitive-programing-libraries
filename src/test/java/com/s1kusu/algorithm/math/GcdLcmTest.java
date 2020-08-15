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

    @Nested
    @Disabled
    class SpeedTest{
        static final int N = 100_000_000;

        @Test
        void testSpeedGcd() {
            long startTime = System.currentTimeMillis();
            assertTimeoutPreemptively(ofMillis(1000), () -> {
                for (int i = 0; i < N; i++) {
                    GcdLcm.gcd(i+1, i+2);
                }
            });
            long endTime = System.currentTimeMillis();
            System.out.printf("time to run gcd() %,d times : %,d ms", N, endTime - startTime);
        }
    }
}
