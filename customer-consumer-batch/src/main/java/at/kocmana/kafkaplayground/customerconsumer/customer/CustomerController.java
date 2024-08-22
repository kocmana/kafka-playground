package at.kocmana.kafkaplayground.customerconsumer.customer;

import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC;

import at.kocmana.kafkaplayground.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CustomerController {

  @KafkaListener(topics = CUSTOMER_TOPIC)
  void retrieveCustomersBatched(ConsumerRecords<String, Customer> customerRecords) {
    log.info("Retrieved {} customer records.", customerRecords.count());

    for (var customerRecord : customerRecords) {
      var value = customerRecord.value();
      log.info("Received customer record: {}", value);
    }
  }

}
