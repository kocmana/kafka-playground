package at.kocmana.kafkaplayground.customerproducer.customer;

import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC;

import at.kocmana.kafkaplayground.model.Customer;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
class CustomerKafkaAdapter {

  private final KafkaTemplate<String, Customer> kafkaTemplate;

  void publishCustomer(Customer customer) {
    var resultFuture = kafkaTemplate.send(CUSTOMER_TOPIC, customer.getId(), customer);
    try {
      var result = resultFuture.get();
      log.info("Published customer '{}' with offset '{}'", customer, result.getRecordMetadata().offset());
    } catch (InterruptedException e) {
      log.error("Interrupted while publishing customer '{}'", customer, e);
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      log.error("Error while publishing customer '{}': {}", customer, e.getMessage(), e);
    }
  }
}
