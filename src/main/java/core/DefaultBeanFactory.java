package core;

import java.util.*;

public class DefaultBeanFactory implements BeanFactory {

    private final Map<String, Object> singletons = new HashMap<>();
    private final Map<String, BeanDefinition> definitions = new HashMap<>();
    private final Injector injector = new Injector(this);

    public void registerBeanDefinition(BeanDefinition bd) {
        definitions.put(bd.getName(), bd);
    }

   /* public void initializeBeans() {
        for (BeanDefinition bd : definitions.values()) {
            Object bean = createBean(bd);
            singletons.put(bd.getName(), bean);
        }
    }*/

    private Object createBean(BeanDefinition bd) {
        try {
            Object instance = bd.getType().getDeclaredConstructor().newInstance();
            injector.inject(instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String name) {
        return singletons.get(name);
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return singletons.values()
                .stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }


    public void initializeBeans() {

        // 1. Instanciation de tous les beans
        for (BeanDefinition bd : definitions.values()) {
            Object instance = createInstanceOnly(bd);
            singletons.put(bd.getName(), instance);
        }

        // 2. Injection des dépendances
        for (Object bean : singletons.values()) {
            injector.inject(bean);
        }
    }

    private Object createInstanceOnly(BeanDefinition bd) {
        try {
            return bd.getType()
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erreur création bean: " + bd.getName(), e);
        }
    }
}