package at.kocmana.kafkaplayground.customerproducer.configuration;

import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC;
import static at.kocmana.kafkaplayground.kafka.GlobalTopicConfigurationConstants.CUSTOMER_TOPIC_PARTITION_COUNT;
import static org.apache.kafka.common.config.TopicConfig.RETENTION_MS_CONFIG;

import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaConfiguration {

  @Bean
  public NewTopic topic() {
    var topicProperties = Map.of(
        RETENTION_MS_CONFIG, "3600000"
    );

    return TopicBuilder.name(CUSTOMER_TOPIC)
        .partitions(CUSTOMER_TOPIC_PARTITION_COUNT)
        .replicas(1)
        .configs(topicProperties)
        .build();
  }

}
