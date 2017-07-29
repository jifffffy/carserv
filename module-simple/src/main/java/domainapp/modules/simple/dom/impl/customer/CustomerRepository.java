package domainapp.modules.simple.dom.impl.customer;

import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

import lombok.Getter;
import lombok.Setter;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Customer.class
)
public class CustomerRepository {

    @Programmatic
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
    }

    @javax.inject.Inject
    org.apache.isis.applib.DomainObjectContainer container;
}
