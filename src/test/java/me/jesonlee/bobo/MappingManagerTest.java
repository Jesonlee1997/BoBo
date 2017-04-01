package me.jesonlee.bobo;

import me.jesonlee.bobo.core.MappingManager;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
public class MappingManagerTest {


    MappingManager mappingManager = MappingManager.getMappingManager();

    @Test
    public void testInit() {
        mappingManager.init();
    }

}