package ru.master.kafkaexample.api.service;


import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.master.kafkaexample.api.config.KafkaConfig;
import ru.master.kafkaexample.api.dto.RequestMessageTemplateDto;
import ru.master.kafkaexample.api.exception.ServiceSysErrors;
import ru.sberinsur.insure.integrations.commons.Headers;
import ru.sberinsur.insure.integrations.commons.KafkaHeaderAccessor;
import ru.sberinsur.insure.integrations.commons.Message;
import ru.sberinsur.insure.integrations.dto.template.RequestTemplateDto;
import ru.sberinsur.insure.integrations.exception.InsureException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.sberinsur.insure.integrations.commons.KafkaHelper.intToBytes;
import static ru.sberinsur.insure.integrations.commons.KafkaHelper.uuidToBytes;

@Slf4j
@Service
public class TemplateService {

  public final String groupConsumerTopic = "kafka-example_group";
  public final String specificConsumerTopic = "root_specific";



  @Autowired
  private KafkaConfig kafkaConfig;

  @Autowired
  private KafkaTemplate<String, Message> kafkaTemplate;

  @Value("classpath:jwt-token.txt")
  Resource jwtTokenResource;

  /*@Scheduled(fixedRate = 10000)*/
  public void test() {
    SignedJWT signedJWT = null;

    // Генерируем индентификатор сообщения
    UUID messageId = UUID.randomUUID();

    // Устанавливаем вызываемый сервис
    String serviceName = "template";

    // Заполняем вызываемый метод сервиса
    String methodName = "getCustomer";

    // Заполняем Response DTO сервиса (описывается в int-commons - секция DTO)
    /*RequestTemplateDto request = new RequestTemplateDto();
    request.setApplicationId("Проверка!");*/


    RequestMessageTemplateDto request = RequestMessageTemplateDto
        .builder()
        .id("8a63517e-6fe9-4a85-a05a-119cfed6ca96")
        .rqUid("4a6fd48a-7df5-4487-bb35-7b373d0bfa2c")
        .changeType("Смена фонда")
        .deniedFlag(false)
        .deniedReason("")
        .deniedCode(0)
        .idPolicy(0L)
        .insuranceAmount(BigDecimal.TEN)
        .insurancePremium(BigDecimal.TEN)
        .policyNumber("ДСЖ-1_1501-18865")
        .insuranceFromDate(LocalDate.of(2022, 11, 11))
        .insuranceToDate(LocalDate.of(2022, 11, 10))
        .applicationCreateDate(LocalDate.of(2022, 11, 14))
        .changeDate(LocalDate.of(2022, 10, 31))
        .email("58DFDFGDG@XCVXFFD.RU")
        .customerLastname("ШТОК-ПЯТЬДЕСЯТВОСЬМОЙ")
        .customerFirstname("РЕВОЛЬД")
        .customerMiddlename("ТИУРАНОВИЧ")
        .dateOfSurvival(LocalDate.of(2022, 10, 31))
        .changeBaseAsset("ESG Сбалансированный высокий риск")
        .changeSum(BigDecimal.TEN)
        .unitChangeValue(BigDecimal.TEN)
        .changeCurrency("RUB")
        .bik("BIK")
        .accountCorresp("CORRESP")
        .accountNumber("NUMBER")
        .document("DOCUMENT")
        .redemptionAmount(BigDecimal.ONE)
        .insuranceAmountNew(BigDecimal.ONE)
        .insurancePremiumNew(BigDecimal.ONE)
        .preliminaryInsuranceAmount(BigDecimal.ONE)
        .storedApplicationList("KU-KU")
        .vDat(LocalDateTime.now())
        .build();




    // Мапим Producer - устанавливается в конфигурации сервиса
    KafkaConfig.Producer serviceProducer = this.kafkaConfig.getProducers().getKafkaExample();

    // Создаем сообщение
    //ProducerRecord<String, Message> producerRecord = new ProducerRecord<>(serviceProducer.getGroupTopic(), messageId.toString(), request);

    // Устанавливаем заголовки
    //producerRecord.headers().add(Headers.INSURE_SOURCE_INSTANCE_ID.name(), this.kafkaConfig.getSpecificConsumer().getGroupId().getBytes());
    /*producerRecord.headers().add(Headers.INSURE_SOURCE_INSTANCE_ID.name(), this.kafkaConfig.getGroupConsumer().getGroupId().getBytes());
    producerRecord.headers().add(Headers.INSURE_MESSAGE_ID.name(), uuidToBytes(messageId));
    producerRecord.headers().add(Headers.INSURE_VERSION.name(), intToBytes(1));
    producerRecord.headers().add(KafkaHeaders.REPLY_TOPIC, this.kafkaConfig.getGroupConsumer().getTopic().getBytes());*/

    String jwtToken;
    try {
      jwtToken = new BufferedReader(
          new InputStreamReader(jwtTokenResource.getInputStream())).lines().collect(Collectors.joining());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    KafkaHeaderAccessor kafkaHeaderAccessor = KafkaHeaderAccessor.createIdentified();
    //kafkaHeaderAccessor.sourceInstanceId(specificConsumerTopic);
    kafkaHeaderAccessor.sourceInstanceId(this.kafkaConfig.getGroupConsumer().getGroupId());
    kafkaHeaderAccessor.version(1);
    //kafkaHeaderAccessor.replyTopic(specificConsumerTopic);
    //kafkaHeaderAccessor.replyTopic(this.kafkaConfig.getGroupConsumer().getTopic());
    kafkaHeaderAccessor.replyTopic("igor-topic-1");
    kafkaHeaderAccessor.accessToken(jwtToken);
    kafkaHeaderAccessor.messageId(messageId);

    ProducerRecord<String, Message> producerRecord = new ProducerRecord<>(
            //kafkaConfig.getProducers().getKafkaExample().getGroupTopic(),
            kafkaConfig.getProducers().getIgorTopic1().getGroupTopic(),
            null,
            kafkaHeaderAccessor.messageId().toString(),
            request,
            kafkaHeaderAccessor.toKafkaHeaders());

    try {
      // Отправляем сообщение
      this.kafkaTemplate.send(producerRecord);
    } catch (Exception e) {
      throw new InsureException(e.getMessage(), e, ServiceSysErrors.BASE_ERROR);
    }
  }
}