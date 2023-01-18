package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Смена портфеля
 */
@Data
@Accessors(chain = true)
public class ChangeBaseAsset {

    /**
     * Тип изменений в АС БС
     */
    private String applicationType;

    /**
     * Название нового портфеля
     */
    private String changeBA;

    /**
     * Код типа опции в АС БС
     */
    private String typeOptionCode;

    /**
     * Код нового портфеля
     */
    private String changeBACode;

    /**
     * Дата внесения изменений
     */
    private LocalDate changeDate;

}
