package example;

import annotations.Component;

@Component
public class ServiceA {

    public String hello() {
        return "Hello from ServiceA";
    }
}