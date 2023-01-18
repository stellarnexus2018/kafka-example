package ru.master.kafkaexample.api.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import ru.master.kafkaexample.api.service.JsonParserSubService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JsonParserSubServiceImplTest {
  @Autowired
  private JsonParserSubService jpss;

  @Value("classpath:primer.json")
  Resource jsonDataResource;

  @Value("classpath:1.json")
  Resource json1;

  @Value("classpath:2.json")
  Resource json2;

  @Value("classpath:3.json")
  Resource json3;

  @Test
  void runOneTask() {
    System.out.println("начинаем проверку");

    String jsonData = "";
    try {
      jsonData = new BufferedReader(new InputStreamReader(json1.getInputStream())).lines().collect(Collectors.joining());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    jpss.runOneTask(jsonData);

    System.out.println("закончили проверку");
  }

  @Test
  void runParse() {
    System.out.println("начинаем проверку");
    String jsonData = "";
    try {
      jsonData = new BufferedReader(new InputStreamReader(json1.getInputStream())).lines().collect(Collectors.joining());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    jpss.runParse(jsonData);
    System.out.println("закончили проверку");
  }
}