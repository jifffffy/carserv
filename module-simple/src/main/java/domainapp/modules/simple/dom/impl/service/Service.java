package domainapp.modules.simple.dom.impl.service;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@javax.jdo.annotations.PersistenceCapable(
        identityType = javax.jdo.annotations.IdentityType.DATASTORE,
        schema = "service",
        table = "Service"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column = "id")
@javax.jdo.annotations.Version(
        strategy = javax.jdo.annotations.VersionStrategy.VERSION_NUMBER,
        column = "version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.service.Service "),
        @javax.jdo.annotations.Query(
                name = "findByNameContains", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.service.Service "
                        + "WHERE name.indexOf(:name) >= 0 "),
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.impl.service.Service "
                        + "WHERE name == :name ")
})
@javax.jdo.annotations.Unique(name = "Service_name_UNQ", members = { "name" })
@org.apache.isis.applib.annotation.DomainObject(
        editing = Editing.DISABLED
)
@org.apache.isis.applib.annotation.DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Service implements Comparable<Service> {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @org.apache.isis.applib.annotation.Property()
    @lombok.Getter @lombok.Setter
    private String name;

    //region > compareTo, toString
    @Override
    public int compareTo(final Service other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "name");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "name");
    }
    //endregion

}
