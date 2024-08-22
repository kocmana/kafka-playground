package at.kocmana.kafkaplayground.customerconsumer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;

@Slf4j
public class CustomErrorHandler extends DefaultErrorHandler {

  public CustomErrorHandler(ConsumerRecordRecoverer recordRecoverer, BackOff backOff) {
    super(recordRecoverer, backOff);
  }

  @Override
  public boolean isAckAfterHandle() {
    var isAcking = true;
    log.info("Acking after handle: {}", isAcking);
    return isAcking;
  }
}
