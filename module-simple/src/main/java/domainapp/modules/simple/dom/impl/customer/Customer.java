package domainapp.modules.simple.dom.impl.customer;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

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
        editing = Editing.DISABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Customer implements Comparable<Customer> {

    @Column(allowsNull = "false")
    @Property()
    @Getter @Setter
    private String firstName;

    public Customer(String firstName){
        setFirstName(firstName);
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
