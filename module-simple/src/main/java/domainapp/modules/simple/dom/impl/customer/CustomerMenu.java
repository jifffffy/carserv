package domainapp.modules.simple.dom.impl.customer;

import java.util.List;

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

    /*

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

        */
    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Customer> allCustomers() {
        return customerrepository.allCustomers();
    }

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @MemberOrder(sequence = "3")
    public Customer newCustomer(
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "First Name")
                    String firstName,
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "Last Name")
                    String lastName
    ) {
        return customerrepository.newCustomer(firstName, lastName);
    }

    @javax.inject.Inject
    CustomerRepository customerrepository;
}
