package at.kocmana.kafkaplayground.customerproducer.customer;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomerController {

  private final CustomerService customerService;

  @Scheduled(initialDelay = 10, fixedRate = 1, timeUnit = TimeUnit.SECONDS)
  void produceCustomer() {
    log.info("Producing customer");
    var customer = customerService.generateRandomCustomer();
    customerService.publishCustomer(customer);
  }

}
