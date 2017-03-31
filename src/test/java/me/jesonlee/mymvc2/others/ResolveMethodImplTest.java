package me.jesonlee.mymvc2.others;

import me.jesonlee.mymvc2.core.DispatcherServlet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class ResolveMethodImplTest {
    private ResolveMethodImpl resolveMethod = new ResolveMethodImpl();
    @Test
    public void resolve() throws Exception {
        long l1 = System.currentTimeMillis();
        List<Class> classes = new ArrayList<Class>() {{
            add(TestA.class);
            add(DispatcherServlet.class);
        }};
        resolveMethod.resolve(classes);
        System.out.println(System.currentTimeMillis() - l1);
    }

    static class TestA{
        public String test1(String s1, int i2, int i3, String s4) {
            return null;
        }

        public void test2(int int1, int int2, String string3) {}
    }

}