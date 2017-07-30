package domainapp.modules.simple.dom.impl.vehicle;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Car.class
)
@DomainServiceLayout(
        named = "Cars",
        menuBar = DomainServiceLayout.MenuBar.PRIMARY,
        menuOrder = "20"
)
public class CarRepository {

    public String iconName(){
        return "Car";
    }

    //region > all cars
    public List<Car> allCars() {
        return container.allInstances(Car.class);
    }
    //endregion


    //region > newCar
    public Car newCar(String registrationNumber){
        Car car = container.newTransientInstance(Car.class);
        car.setRegistrationNumber(registrationNumber);
        container.persist(car);
        container.flush();
        return car;
    }
    //endregion

    @javax.inject.Inject
    private DomainObjectContainer container;
}
