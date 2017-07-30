package domainapp.modules.simple.dom.impl.vehicle;

import java.util.Collection;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.TitleBuffer;

import javax.jdo.annotations.*;

import com.google.common.collect.Lists;

import domainapp.modules.simple.dom.impl.customer.Customer;
import domainapp.modules.simple.dom.impl.customer.CustomerRepository;
import domainapp.modules.simple.dom.impl.service.Service;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "car",
        table = "Car"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@DomainObject(
        editing = Editing.DISABLED,
        objectType = "Car"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Car implements Comparable<Car> {

    @Column(allowsNull = "false")
    @Property(

    )
    @Getter @Setter
    private String registrationNumber;

    public String title() {
        TitleBuffer buf = new TitleBuffer();
        buf.append(getRegistrationNumber());
        return buf.toString();
    }

    @Column(allowsNull = "true")
    @Property(
            optionality = Optionality.OPTIONAL,
            editing = Editing.ENABLED
    )
    @Getter @Setter
    private Customer owningCustomer;

    @Persistent(mappedBy = "car", dependentElement = "false")
    @org.apache.isis.applib.annotation.Collection()
    @Getter @Setter
    private SortedSet<Service> services = new TreeSet<Service>();

    public Collection<Customer> choicesOwningCustomer() {
        return customerRepository.allCustomers(); // TODO: return list of choices for property
    }


    //region > compareTo, toString
    @Override
    public int compareTo(final Car other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "registrationNumber");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "registrationNumber");
    }
    //endregion

    @javax.inject.Inject
    private CustomerRepository customerRepository;

}
