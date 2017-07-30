package domainapp.modules.simple.dom.impl.service;

import java.sql.Date;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Service.class
)
public class ServiceRepository {

    @Programmatic
    public java.util.List<Service> listAll() {
        return container.allInstances(Service.class);
    }

    @Programmatic
    public Service newService(Date bookedIn, Date estimatedReady){

        Service service = container.newTransientInstance(Service.class);
        service.setBookedIn(bookedIn);
        service.setEstimatedReady(estimatedReady);
        container.persist(service);
        container.flush();
        return service;
    }

    @javax.inject.Inject
    org.apache.isis.applib.DomainObjectContainer container;
}
