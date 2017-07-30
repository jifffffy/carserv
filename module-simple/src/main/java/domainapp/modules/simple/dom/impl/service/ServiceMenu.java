package domainapp.modules.simple.dom.impl.service;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@org.apache.isis.applib.annotation.DomainService(
        nature = org.apache.isis.applib.annotation.NatureOfService.VIEW_MENU_ONLY
)
@org.apache.isis.applib.annotation.DomainServiceLayout(
        named = "Services",
        menuOrder = "10"
)
public class ServiceMenu {

    @org.apache.isis.applib.annotation.Action(
            semantics = org.apache.isis.applib.annotation.SemanticsOf.SAFE,
            restrictTo = org.apache.isis.applib.annotation.RestrictTo.PROTOTYPING
    )
    @org.apache.isis.applib.annotation.ActionLayout(
            bookmarking = org.apache.isis.applib.annotation.BookmarkPolicy.AS_ROOT
    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "1")
    public java.util.List<Service> listAll() {
        return servicerepository.listAll();
    }

    @org.apache.isis.applib.annotation.Action(
            semantics = org.apache.isis.applib.annotation.SemanticsOf.SAFE
    )
    @org.apache.isis.applib.annotation.ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "2")
    public java.util.List<Service> findByName(
            final String name
    ) {
        return servicerepository.findByNameContains(name);
    }

    @org.apache.isis.applib.annotation.Action(
    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "3")
    public Service create(
            final String name) {
        return servicerepository.create(name);
    }

    @javax.inject.Inject
    ServiceRepository servicerepository;
}
