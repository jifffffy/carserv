package domainapp.modules.simple.dom.impl.customer;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.TitleBuffer;

import javax.jdo.annotations.*;

import domainapp.modules.simple.dom.impl.vehicle.Car;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(
        identityType = IdentityType.DATASTORE,
        schema = "customer",
        table = "Customer"
)
@DatastoreIdentity(
        strategy = IdGeneratorStrategy.IDENTITY,
        column = "id")
@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.customer.Customer "),
        @Query(
                name = "findByFirstNameContains", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.customer.Customer "
                        + "WHERE firstName.indexOf(:firstName) >= 0 "),
        @Query(
                name = "findByFirstName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.customer.Customer "
                        + "WHERE firstName == :firstName ")
})
@Unique(name = "Customer_firstName_UNQ", members = { "firstName" })
@DomainObject(
        editing = Editing.DISABLED,
        objectType = "Customer"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Customer implements Comparable<Customer> {

    @Column(allowsNull = "true", length = 10)
    @Property(
            editing = Editing.ENABLED
    )
    @MemberOrder(sequence = "1.0")
    @Getter @Setter
    private String title;

    @Column(allowsNull = "false", length = 10)
    @Property
    @PropertyLayout(
            describedAs = "The name given to this customer, or by which he/she is known"
    )
    @MemberOrder(sequence = "1.1")
    @Getter @Setter
    private String firstName;

    @Column(allowsNull = "false", length = 10)
    @Property
    @PropertyLayout(
            describedAs = "The name given to this customer, or by which he/she is known"

    )
    @MemberOrder(sequence = "1.2")
    @Getter @Setter
    private String lastName;

    @Column(allowsNull = "true", length = 255)
    @Property(
            editing = Editing.ENABLED,
            optionality = Optionality.OPTIONAL
    )
    @PropertyLayout(
            multiLine = 10
    )
    @MemberOrder(sequence = "1.3")
    @Getter @Setter
    private String notes;

    @Persistent(mappedBy = "owningCustomer", dependentElement = "false")
    @Collection(
            editing = Editing.ENABLED
    )
    @Getter @Setter
    private SortedSet<Car> cars = new TreeSet<Car>();


    public String title() {
        TitleBuffer buf = new TitleBuffer();
        buf.append(getTitle());
        buf.append(getFirstName()).append(getLastName());
        return buf.toString();
    }

    public List<String> choicesTitle() {
        return Arrays.asList("Mr", "Mrs", "Ms", "Miss");
    }

    //region > compareTo, toString
    @Override
    public int compareTo(final Customer other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "firstName");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "firstName");
    }
    //endregion

}
