package ru.master.kafkaexample.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Application {

    /**
     * Режим обработки заявления
     */
    private String applicationMode;

    /**
     * Уникальный номер заявления в АС БС
     */
    private String applicationNo;

    /**
     * Дата создания заявления
     */
    private String applicationCreateDate;

    /**
     * Тип изменений
     */
    private ApplicationChangesType applicationChangesType;

}
