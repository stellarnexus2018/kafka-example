package ru.master.kafkaexample.api.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/*import ru.master.kafkaexample.api.utils.DefaultKafkaConfig;*/
import ru.sberinsur.insure.integrations.config.DefaultKafkaConfig;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@EqualsAndHashCode(callSuper = true)
public class KafkaConfig extends DefaultKafkaConfig {
  private Producers producers;

  public KafkaConfig() {
    super();
  }

  @Data
  public static class Producer {
    private String groupTopic;
    /*private String specificTopic;*/
  }

  @Data
  public static class Producers {
    private Producer kafkaExample;
    private Producer igorTopic1;
  }

  public Producer getProducerByString(String name) {
    HashMap<String, Producer> producersMap = new HashMap<>();
    producersMap.put("kafka-example", producers.getKafkaExample());
    producersMap.put("igor-topic-1", producers.getIgorTopic1());
    if (producersMap.containsKey(name.toLowerCase())) {
      return producersMap.get(name.toLowerCase());
    }
    return null;
  }
}