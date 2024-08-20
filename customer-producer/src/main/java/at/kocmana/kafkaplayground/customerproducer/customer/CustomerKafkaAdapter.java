package at.kocmana.kafkaplayground.customerproducer.customer;

import at.kocmana.kafkaplayground.Customer;
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
    var resultFuture = kafkaTemplate.sendDefault(customer);
    try {
      var result = resultFuture.get();
      log.info("Published customer '{}' with result '{}'", customer, result.getRecordMetadata().toString());
    } catch (InterruptedException e) {
      log.error("Interrupted while publishing customer '{}'", customer, e);
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      log.error("Error while publishing customer '{}': {}", customer, e.getMessage(), e);
    }
  }
}
