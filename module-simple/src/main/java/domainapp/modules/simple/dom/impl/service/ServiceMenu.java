package domainapp.modules.simple.dom.impl.service;

import java.sql.Date;

import org.apache.isis.applib.annotation.*;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "ServiceMenu",
        repositoryFor = Service.class
)
@DomainServiceLayout(
        named = "Services",
        menuOrder = "10"
)
public class ServiceMenu {

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public java.util.List<Service> listAll() {
        return servicerepository.listAll();
    }

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public Service newService(Date bookedIn, Date estimatedReady){
        return servicerepository.newService(bookedIn, estimatedReady);
    }

    @javax.inject.Inject
    ServiceRepository servicerepository;
}
