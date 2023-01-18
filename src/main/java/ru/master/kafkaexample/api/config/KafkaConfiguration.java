package ru.master.kafkaexample.api.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
//import ru.master.kafkaexample.api.component.Waiter;
import ru.sberinsur.insure.integrations.commons.Message;
import ru.sberinsur.insure.integrations.commons.chain.Waiter;
import ru.sberinsur.insure.integrations.commons.marshall.InsureKafkaDeserializer;
import ru.sberinsur.insure.integrations.commons.marshall.InsureKafkaSerializer;
import ru.sberinsur.insure.integrations.exception.InsureKafkaErrorHandler;
/*import ru.master.kafkaexample.api.utils.InsureKafkaDeserializer;
import ru.master.kafkaexample.api.utils.InsureKafkaErrorHandler;
import ru.master.kafkaexample.api.utils.InsureKafkaSerializer;
import ru.master.kafkaexample.api.utils.Message;*/

import java.util.HashMap;
import java.util.Map;

//@ComponentScan("ru.master.kafkaexample.api")
@ComponentScan("ru.sberinsur.insure.integrations")
@Configuration
@EnableKafka
public class KafkaConfiguration {

  private final KafkaConfig kafkaConfig;
  private final Waiter waiter;

  @Autowired
  public KafkaConfiguration(KafkaConfig kafkaConfig, Waiter waiter) {
    this.kafkaConfig = kafkaConfig;
    this.waiter = waiter;
  }

  @Bean
  public KafkaTemplate<String, Message> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, Message> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConfig.getBootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, InsureKafkaSerializer.class);

    if (!this.kafkaConfig.getProperties().isEmpty()) {
      props.putAll(this.kafkaConfig.getProperties());
    }
    //props.putAll(this.kafkaConfig.getProperties());  uncomment if need properties (e.g ssl configuration)
    return props;
  }

  @Bean
  KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> groupKafkaListenerContainerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConfig.getBootstrapServers());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InsureKafkaDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getGroupConsumer().getGroupId());
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    if (!this.kafkaConfig.getProperties().isEmpty()) {
      props.putAll(this.kafkaConfig.getProperties());
    }
    //props.putAll(this.kafkaConfig.getProperties());  uncomment if need properties (e.g ssl configuration)

    ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
    factory.setConcurrency(this.kafkaConfig.getGroupConsumer().getConcurrency());
    factory.getContainerProperties().setPollTimeout(this.kafkaConfig.getGroupConsumer().getPollTimeout());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    factory.setReplyTemplate(kafkaTemplate());
    factory.setErrorHandler(new InsureKafkaErrorHandler(kafkaTemplate(), this.kafkaConfig.getErrorTopic(), this.kafkaConfig.getGroupConsumer().getGroupId(), waiter));

    return factory;
  }

  /*@Bean
  KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> specificKafkaListenerContainerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConfig.getBootstrapServers());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InsureKafkaDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, this.kafkaConfig.getSpecificConsumer().getGroupId());
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    if (!this.kafkaConfig.getProperties().isEmpty()) {
      props.putAll(this.kafkaConfig.getProperties());
    }
    //props.putAll(this.kafkaConfig.getProperties());  uncomment if need properties (e.g ssl configuration)

    ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
    factory.setConcurrency(this.kafkaConfig.getSpecificConsumer().getConcurrency());
    factory.getContainerProperties().setPollTimeout(this.kafkaConfig.getSpecificConsumer().getPollTimeout());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    factory.setReplyTemplate(kafkaTemplate());
    factory.setErrorHandler(new InsureKafkaErrorHandler(kafkaTemplate(), this.kafkaConfig.getErrorTopic(), this.kafkaConfig.getSpecificConsumer().getGroupId(), waiter));

    return factory;
  }*/



}
