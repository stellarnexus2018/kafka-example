package ru.master.kafkaexample.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Данные по договору страхования
 */
@Data
@Accessors(chain = true)
public class AgreementInfo {

    /**
     * Серия договора страхования
     */
    private String agreementPolicySeries;

    /**
     * Номер договора страхования
     */
    private String agreementNumber;

    /**
     * Дата начала страхования
     */
    private LocalDate insuranceFromDate;

    /**
     * Дата окончания договора
     */
    private LocalDate insuranceToDate;

}
