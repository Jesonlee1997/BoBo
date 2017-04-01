package me.jesonlee.bobo.others;

import me.jesonlee.bobo.core.DispatcherServlet;
import me.jesonlee.bobo.core.ResolveClassesImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class ResolveMethodImplTest {
    private ResolveClassesImpl resolveMethod = new ResolveClassesImpl();
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