package at.kocmana.kafkaplayground.customerproducer.customer;

import at.kocmana.kafkaplayground.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerFactory customerFactory;
  private final CustomerKafkaAdapter customerKafkaAdapter;

  public Customer generateRandomCustomer() {
    return customerFactory.generateCustomer();
  }

  void publishCustomer(Customer customer) {
    customerKafkaAdapter.publishCustomer(customer);
  }
}
