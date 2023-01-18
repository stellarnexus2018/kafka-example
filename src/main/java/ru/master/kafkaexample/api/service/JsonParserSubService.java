package ru.master.kafkaexample.api.service;

import lombok.NonNull;

public interface JsonParserSubService {
  void runOneTask(@NonNull String jsonData);
  void runParse(@NonNull String jsonData);
}