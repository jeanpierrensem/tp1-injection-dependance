package xml;

import core.BeanDefinition;
import core.DefaultBeanFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class XmlBeanDefinitionReader {

    private final DefaultBeanFactory factory;

    public XmlBeanDefinitionReader(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public void load(String path) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Beans.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();

            Beans beans = (Beans) unmarshaller.unmarshal(new File(path));

            for (Beans.Bean b : beans.getBeans()) {
                Class<?> clazz = Class.forName(b.getClassName());
                factory.registerBeanDefinition(
                        new BeanDefinition(b.getId(), clazz)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}