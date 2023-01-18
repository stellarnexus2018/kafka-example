package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Частичный вывод средств
 */
@Data
@Accessors(chain = true)
public class ChangeUnitsValue {

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
     * Количество выводимых юнитов
     */
    private BigDecimal changeValue;

    /**
     * Сумма частичного вывода
     */
    private BigDecimal withdrawPartSum;

    /**
     * Дата периодического дожития
     */
    private String dateOfSurvival;

    /**
     * Дата внесения изменений
     */
    private String changeDate;

    /**
     * Валюта вывода
     */
    private String currency;

}
