package me.jesonlee.bobo.core;

import org.junit.Test;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class UrlPatternTest {
    @Test
    public void testCreateUrlPattern() {
        UrlPattern pattern = new UrlPattern("/jeson/(id)/(name)/");
    }
}