package core;

import xml.*;

public class ApplicationContext {

    private final DefaultBeanFactory factory = new DefaultBeanFactory();

    public ApplicationContext(String xmlPath) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.load(xmlPath);
        factory.initializeBeans();
    }

    public <T> T getBean(Class<T> type) {
        return factory.getBean(type);
    }

    public Object getBean(String name) {
        return factory.getBean(name);
    }
}