package com.zzc.somedesigns;

import org.junit.Test;

/**
 * @auth zzc
 * @date 2017/12/25
 * @desc ${desc}
 */

public class SpecTest {
    private final int MODE_SHIFT = 30;
    private final int MODE_MASK = 0x3 << MODE_SHIFT;
    int UNSPECIFIED = 0 << MODE_SHIFT;

    int EXACTLY = 1 << MODE_SHIFT;

    int AT_MOST = 2 << MODE_SHIFT;

    @Test
    public void operation() {
        int i = 0 + UNSPECIFIED;
//        int i = (0 & ~MODE_MASK) | (UNSPECIFIED & MODE_MASK);
        System.out.println("i=" + i);
    }
}
