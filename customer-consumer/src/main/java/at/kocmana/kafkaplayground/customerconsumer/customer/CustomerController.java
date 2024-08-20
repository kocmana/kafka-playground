package at.kocmana.kafkaplayground.customerconsumer.customer;

import at.kocmana.kafkaplayground.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CustomerController {

  @KafkaListener(topics = "customers")
  void retrieveCustomer(Customer customer) {
    log.info("Customer retrieved: {}", customer);
  }

}
