package at.kocmana.kafkaplayground.customerproducer.customer;

import at.kocmana.kafkaplayground.Customer;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
class CustomerFactory {

  private static final String EMAIL_TEMPLATE = "%s.%s@%s";
  private final Faker faker;

  Customer generateCustomer() {

    var firstName = faker.name().firstName();
    var lastName = faker.name().lastName();
    var email = generateEmailAddress(firstName, lastName);

    return Customer.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .build();
  }

  private String generateEmailAddress(String firstName, String lastName) {
    return EMAIL_TEMPLATE.formatted(firstName,
            lastName,
            faker.internet().domainName())
        .toLowerCase();
  }

}