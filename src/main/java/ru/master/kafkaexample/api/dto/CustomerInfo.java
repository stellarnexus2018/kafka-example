package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Информация по страхователю
 */
@Data
@Accessors(chain = true)
public class CustomerInfo {

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * Почта для связи
     */
    private String email;

    /**
     * Набор документов заявителя
     */
    private ApplicantDocument applicantDocument;

}
