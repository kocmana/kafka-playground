package at.kocmana.kafkaplayground.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalTopicConfigurationConstants {

  public static final String CUSTOMER_TOPIC = "customers";
  public static final int CUSTOMER_TOPIC_PARTITION_COUNT = 6;

}
