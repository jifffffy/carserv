package domainapp.modules.simple.dom.impl.customer;

import org.apache.isis.applib.annotation.*;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.CustomerMenu",
        repositoryFor = Customer.class
)
@DomainServiceLayout(
        named = "Customers",
        menuOrder = "10"
)
public class CustomerMenu {

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public java.util.List<Customer> listAll() {
        return customerrepository.listAll();
    }

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public java.util.List<Customer> findByFirstName(
            final String firstName
    ) {
        return customerrepository.findByFirstNameContains(firstName);
    }

    @Action(
    )
    @MemberOrder(sequence = "3")
    public Customer create(
            final String firstName) {
        return customerrepository.create(firstName);
    }

    @javax.inject.Inject
    CustomerRepository customerrepository;
}
