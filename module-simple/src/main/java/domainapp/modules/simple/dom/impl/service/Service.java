package domainapp.modules.simple.dom.impl.service;

import java.sql.Date;
import java.util.Collection;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.TitleBuffer;

import javax.inject.Inject;
import javax.jdo.annotations.*;

import com.google.common.collect.Lists;

import domainapp.modules.simple.dom.impl.vehicle.Car;
import domainapp.modules.simple.dom.impl.vehicle.CarRepository;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "service",
        table = "Service"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@DomainObject(
        editing = Editing.DISABLED,
        objectType = "Service"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Service implements Comparable<Service> {

    @Column(allowsNull = "false")
    @Property()
    @Getter @Setter
    private Date bookedIn;

    @Column(allowsNull = "false")
    @Property()
    @Getter @Setter
    private Date estimatedReady;

    public String title() {
        TitleBuffer buf = new TitleBuffer();
        buf.append(getCar().getRegistrationNumber())
                .append( ":" , getBookedIn());
        return buf.toString();
    }

    @Column(allowsNull = "true")
    @Property(
            editing = Editing.ENABLED
    )
    @Getter @Setter
    private Car car;

    public Collection<Car> choicesCar() {
        return carRepository.allCars(); // TODO: return list of choices for property
    }

    //region > compareTo, toString
    @Override
    public int compareTo(final Service other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "bookedIn");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "bookedIn");
    }
    //endregion

    @Inject
    private CarRepository carRepository;
}
