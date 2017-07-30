package domainapp.modules.simple.dom.impl.car;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

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
    @Property()
    @Getter @Setter
    private String registrationNumber;

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

}
