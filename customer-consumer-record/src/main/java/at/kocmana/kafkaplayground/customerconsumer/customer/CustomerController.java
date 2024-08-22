package at.kocmana.kafkaplayground.customerconsumer.customer;

import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC;

import at.kocmana.kafkaplayground.model.Customer;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CustomerController {

  @KafkaListener(topics = CUSTOMER_TOPIC)
  void retrieveCustomer(@Payload Customer customer, @Headers Map<String, Object> header) {
    var offset = header.get(KafkaHeaders.OFFSET);
    log.info("Customer with offset {} retrieved: {} ", offset, customer);
    if (!customer.getIsValid()) {
      log.warn("Customer with id {} is invalid", customer.getId());
      throw new IllegalArgumentException("Customer is not valid");
    }
  }

}
