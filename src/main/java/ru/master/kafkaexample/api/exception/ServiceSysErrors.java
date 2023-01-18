package ru.master.kafkaexample.api.exception;

import ru.sberinsur.insure.integrations.exception.InsureErrorCode;

/**
 * Реестр системных ошибок
 */
public enum ServiceSysErrors implements InsureErrorCode {
  BASE_ERROR(0, "Базовая ошибка");

  private final Integer errorCode;
  private final String errorDescription;

  ServiceSysErrors(Integer errorCode, String errorDescription) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }

  @Override
  public Integer getErrorCode() {
    return errorCode;
  }

  @Override
  public String getErrorDescription() {
    return errorDescription;
  }
}