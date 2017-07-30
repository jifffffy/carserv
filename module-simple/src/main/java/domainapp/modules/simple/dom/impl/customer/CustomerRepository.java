package domainapp.modules.simple.dom.impl.customer;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Customer.class
)
@DomainServiceLayout(
        named = "Customers",
        menuBar = DomainServiceLayout.MenuBar.PRIMARY,
        menuOrder = "10"
)
public class CustomerRepository {

 /*   @Programmatic
    public java.util.List<Customer> listAll() {
        return container.allInstances(Customer.class);
    }

    @Programmatic
    public Customer findByFirstName(
            final String firstName
    ) {
        return container.uniqueMatch(
                new org.apache.isis.applib.query.QueryDefault<>(
                        Customer.class,
                        "findByFirstName",
                        "firstName", firstName));
    }

    @Programmatic
    public java.util.List<Customer> findByFirstNameContains(
            final String firstName
    ) {
        return container.allMatches(
                new org.apache.isis.applib.query.QueryDefault<>(
                        Customer.class,
                        "findByFirstNameContains",
                        "firstName", firstName));
    }

    @Programmatic
    public Customer create(final String firstName) {
        final Customer customer = container.newTransientInstance(Customer.class);
        customer.setFirstName(firstName);
        container.persistIfNotAlready(customer);
        return customer;
    }

    @Programmatic
    public Customer findOrCreate(
            final String firstName
    ) {
        Customer customer = findByFirstName(firstName);
        if (customer == null) {
            customer = create(firstName);
        }
        return customer;
    }*/

    public String iconName() {
        return "Customer";
    }

    //region > newCustomer
    public Customer newCustomer(String firstName, String lastName) {
        Customer customer = container.newTransientInstance(Customer.class);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        container.persist(customer);
        container.flush();
        return customer;
    }
    //endregion

    //region > allCustomers
    public List<Customer> allCustomers() {
        return container.allInstances(Customer.class);
    }
    //endregion

    @javax.inject.Inject
    private DomainObjectContainer container;
}
