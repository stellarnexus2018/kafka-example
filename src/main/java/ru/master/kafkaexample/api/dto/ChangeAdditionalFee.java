package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Изменение доп. взноса
 */
@Data
@Accessors(chain = true)
public class ChangeAdditionalFee {

    /**
     * Тип изменений в АС БС
     */
    private String applicationType;

    /**
     * Наименование СО
     */
    private String optionName;

    /**
     * Код типа опции в АС БС
     */
    private String typeOptionCode;

    /**
     * Значение взноса заданное клиентом
     */
    private BigDecimal changeSum;

    /**
     * Коэффициент М
     */
    private BigDecimal coefM;

    /**
     * Валюта взноса
     */
    private String currency;

    /**
     * Дата внесения изменений
     */
    private LocalDate changeDate;

}
