package core;

public class BeanDefinition {
    private String name;
    private Class<?> type;

    public BeanDefinition(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public Class<?> getType() { return type; }
}