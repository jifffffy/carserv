package domainapp.modules.simple.dom.impl.car;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import domainapp.modules.simple.dom.impl.customer.Customer;
import lombok.Getter;
import lombok.Setter;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "CarMenu",
        repositoryFor = Car.class
)
@DomainServiceLayout(
        named = "Cars",
        menuOrder = "10"
)
public class CarMenu {

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public java.util.List<Car> listAll() {
        return carrepository.listAll();
    }


    @javax.inject.Inject
    CarRepository carrepository;
}
