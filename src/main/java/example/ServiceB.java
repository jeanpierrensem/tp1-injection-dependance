package example;

import annotations.Inject;

public class ServiceB {

    @Inject
    private ServiceA serviceA;

    public String run() {
        return serviceA.hello() + " + ServiceB";
    }
}