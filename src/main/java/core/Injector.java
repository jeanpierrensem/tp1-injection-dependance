package core;

import annotations.Inject;

import java.lang.reflect.*;

public class Injector {

    private final BeanFactory factory;

    public Injector(BeanFactory factory) {
        this.factory = factory;
    }

    public void inject(Object instance) {
        injectFields(instance);
        injectMethods(instance);
    }

    // FIELD INJECTION
    private void injectFields(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Object dependency = factory.getBean(field.getType());
                try {
                    field.setAccessible(true);
                    field.set(obj, dependency);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // SETTER INJECTION
    private void injectMethods(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                Class<?>[] params = method.getParameterTypes();
                Object[] args = new Object[params.length];

                for (int i = 0; i < params.length; i++) {
                    args[i] = factory.getBean(params[i]);
                }

                try {
                    method.invoke(obj, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}