package ru.master.kafkaexample.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InternalModel {

    /**
     * Идентификатор запроса из АС БС
     */
    private String rqUID;

    /**
     * ТИ
     */
    private Application application;

    /**
     * Данные по договору страхования
     */
    private AgreementInfo agreementInfo;

    /**
     * Информация по страхователю
     */
    private CustomerInfo customerInfo;

    /**
     * Данные о счете получателя платежа
     */
    private BankAccount bankAccount;

}
