package core;

import java.lang.reflect.Constructor;

public class ReflectionUtils {

    public static Object createInstance(Class<?> clazz) {
        try {
            Constructor<?> c = clazz.getDeclaredConstructor();
            c.setAccessible(true);
            return c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}