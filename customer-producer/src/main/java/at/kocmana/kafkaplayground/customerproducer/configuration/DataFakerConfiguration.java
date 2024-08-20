package at.kocmana.kafkaplayground.customerproducer.configuration;

import java.util.Locale;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFakerConfiguration {

  @Bean
  public Faker dataFaker() {
    return new Faker(Locale.ENGLISH);
  }
}
