package ru.master.kafkaexample.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Данные документа
 */
@Data
@Accessors(chain = true)
public class ApplicantDocument {

    /**
     * Тип ДУЛ
     */
    private String documentType;

    /**
     * Название документа
     */
    private String documentName;

    /**
     * Серия
     */
    private String documentSeries;

    /**
     * Номер
     */
    private String documentNumber;

    /**
     * Кем выдано
     */
    private String documentInstitution;

    /**
     * Когда выдано
     */
    private String documentDate;

    /**
     * Код подразделения
     */
    private String documentCodeIns;

}
