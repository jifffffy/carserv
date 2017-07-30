package domainapp.modules.simple.dom.impl.vehicle;

import java.util.Locale;

import org.apache.isis.applib.annotation.*;

import domainapp.modules.simple.dom.impl.customer.Customer;

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
    public java.util.List<Car> allCars() {
        return carrepository.allCars();
    }

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @MemberOrder(sequence = "3")
    public Car newCar(String registrationNumber) {
        return carrepository.newCar(registrationNumber);
    }

    public String validate0NewCar(String registrationNumber) {
        if (registrationNumber == null) return null;
        String country = Locale.getDefault().getCountry();
        int length = registrationNumber.length();
        if ( ( "US" .equals(country) && length > 7) ||
                ( "GB" .equals(country) && length > 7 ) ||
                length > 12) { // everywhere else
            return "Registration number is too long" ;
        }
        return null;
    }

    @javax.inject.Inject
    CarRepository carrepository;
}
