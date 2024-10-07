package br.inatel.dm110.rest;

import br.inatel.dm110.impl.StoreResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        //register the classes to publish the rest service
        classes.add(StoreResource.class);

        return classes;
    }
}
