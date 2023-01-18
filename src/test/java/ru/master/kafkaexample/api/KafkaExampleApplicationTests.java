package ru.master.kafkaexample.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.master.kafkaexample.api.service.TemplateService;

@SpringBootTest
class KafkaExampleApplicationTests {
  @Autowired
  private TemplateService ts;

  @Test
  void contextLoads() {
    System.out.println("Проверка теста");
    Assert.isTrue(true, "не true");
  }

  @Test
  void sendMessageTest() {
    System.out.println("Проверка теста сообщения");
    ts.test();
    System.out.println("Проверка теста сообщения - успешно");
    Assert.isTrue(true, "не true");
  }
}