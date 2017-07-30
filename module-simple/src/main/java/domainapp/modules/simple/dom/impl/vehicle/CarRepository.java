package domainapp.modules.simple.dom.impl.car;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Car.class
)
public class CarRepository {

    @Programmatic
    public java.util.List<Car> listAll() {
        return container.allInstances(Car.class);
    }


    @javax.inject.Inject
    org.apache.isis.applib.DomainObjectContainer container;
}
