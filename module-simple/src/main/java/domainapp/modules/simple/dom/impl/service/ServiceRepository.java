package domainapp.modules.simple.dom.impl.service;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@org.apache.isis.applib.annotation.DomainService(
        nature = org.apache.isis.applib.annotation.NatureOfService.DOMAIN,
        repositoryFor = Service.class
)
public class ServiceRepository {

    @org.apache.isis.applib.annotation.Programmatic
    public java.util.List<Service> listAll() {
        return container.allInstances(Service.class);
    }

    @org.apache.isis.applib.annotation.Programmatic
    public Service findByName(
            final String name
    ) {
        return container.uniqueMatch(
                new org.apache.isis.applib.query.QueryDefault<>(
                        Service.class,
                        "findByName",
                        "name", name));
    }

    @org.apache.isis.applib.annotation.Programmatic
    public java.util.List<Service> findByNameContains(
            final String name
    ) {
        return container.allMatches(
                new org.apache.isis.applib.query.QueryDefault<>(
                        Service.class,
                        "findByNameContains",
                        "name", name));
    }

    @org.apache.isis.applib.annotation.Programmatic
    public Service create(final String name) {
        final Service service = container.newTransientInstance(Service.class);
        service.setName(name);
        container.persistIfNotAlready(service);
        return service;
    }

    @org.apache.isis.applib.annotation.Programmatic
    public Service findOrCreate(
            final String name
    ) {
        Service service = findByName(name);
        if (service == null) {
            service = create(name);
        }
        return service;
    }

    @javax.inject.Inject
    org.apache.isis.applib.DomainObjectContainer container;
}
