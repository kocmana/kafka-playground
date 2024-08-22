package at.kocmana.kafkaplayground.customerconsumer.configuration;

import static at.kocmana.kafkaplayground.customerconsumer.configuration.TopicConfigurationConstants.CONSUMER_DLT_TOPIC;
import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC;
import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC_PARTITION_COUNT;

import at.kocmana.kafkaplayground.model.Customer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.ExponentialBackOff;

@Configuration
@EnableKafka
public class KafkaConfiguration {

  @Bean
  public NewTopic dltTopic() {
    return TopicBuilder.name(CONSUMER_DLT_TOPIC)
        .partitions(CUSTOMER_TOPIC_PARTITION_COUNT)
        .replicas(1)
        .build();
  }

  @Bean
  BackOff backOff() {
    var defaultBackOff = new ExponentialBackOff(1000, 2);
    defaultBackOff.setMaxAttempts(1);
    return defaultBackOff;
  }

  @Bean
  ConsumerRecordRecoverer consumerRecordRecoverer(KafkaTemplate<String, Customer> kafkaTemplate) {
    return new DeadLetterPublishingRecoverer(kafkaTemplate,
        (customerRecord, exception) -> new TopicPartition(CONSUMER_DLT_TOPIC, customerRecord.partition()));
  }

  @Bean
  CommonErrorHandler commonErrorHandler(ConsumerRecordRecoverer recordRecoverer, BackOff backOff) {
    return new CustomErrorHandler(recordRecoverer, backOff);
  }

}
