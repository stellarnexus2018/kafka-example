package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Данные о счете получателя платежа
 */
@Data
@Accessors(chain = true)
public class BankAccount {

    /**
     * Счет
     */
    private String accountNumber;

    /**
     * БИК банка
     */
    private String bik;

    /**
     * Кор счет банка
     */
    private String accountCorresp;

}
