package at.kocmana.kafkaplayground.customerconsumer.customer;

import at.kocmana.kafkaplayground.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CustomerController {

  /*@KafkaListener(topics = "customers")
  void retrieveCustomer(@Payload Customer customer, @Headers Map<String, Object> header) {
    var offset = header.get(KafkaHeaders.OFFSET);
    log.info("Customer with offset {} retrieved: {} ", offset, customer);
  }
  */

  @KafkaListener(topics = "customers")
  void retrieveCustomersBatched(ConsumerRecords<String, Customer> records) {
    for (var record : records) {
      var value = record.value();
      log.info("Received customer record: {}", value);
    }
  }

}
