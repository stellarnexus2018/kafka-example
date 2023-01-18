package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplicationChangesType {

    /**
     * Изменение доп. взноса
     */
    private ChangeAdditionalFee changeAdditionalFee;

    /**
     * Смена портфеля
     */
    private ChangeBaseAsset changeBaseAsset;

    /**
     * Частичный вывод средств
     */
    private ChangeUnitsValue changeUnitsValue;

}
