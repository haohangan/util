/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.bkcopy.util;

/**
 *
 * @author Administrator
 */
public class MathUtils {

    private static final long NANOSECONDS_PER_MILLISECOND = 1000000;

    public static int signSafeMod(long dividend, int divisor) {
        int mod = (int) (dividend % divisor);

        if (mod < 0) {
            mod += divisor;
        }

        return mod;

    }

    /**
     * Current time from some arbitrary time base in the past, counting in
     * milliseconds, and not affected by settimeofday or similar system clock
     * changes. This is appropriate to use when computing how much longer to
     * wait for an interval to expire.
     *
     * NOTE: only use it for measuring.
     * http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#nanoTime%28%29
     *
     * @return current time in milliseconds.
     */
    public static long now() {
        return System.nanoTime() / NANOSECONDS_PER_MILLISECOND;
    }

    /**
     * Current time from some arbitrary time base in the past, counting in
     * nanoseconds, and not affected by settimeofday or similar system clock
     * changes. This is appropriate to use when computing how much longer to
     * wait for an interval to expire.
     *
     * NOTE: only use it for measuring.
     * http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#nanoTime%28%29
     *
     * @return current time in nanoseconds.
     */
    public static long nowInNano() {
        return System.nanoTime();
    }

    /**
     * Milliseconds elapsed since the time specified, the input is nanoTime the
     * only conversion happens when computing the elapsed time
     *
     * @param startNanoTime the start of the interval that we are measuring
     * @return elapsed time in milliseconds.
     */
    public static long elapsedMSec(long startNanoTime) {
        return (System.nanoTime() - startNanoTime) / NANOSECONDS_PER_MILLISECOND;
    }
}
