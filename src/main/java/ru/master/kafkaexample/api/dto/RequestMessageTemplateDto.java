package ru.master.kafkaexample.api.dto;

import lombok.Builder;
import lombok.Data;
import ru.sberinsur.insure.integrations.commons.Message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RequestMessageTemplateDto  implements Message {
  private String id;
  private String rqUid;
  private String changeType;
  private Boolean deniedFlag;
  private String deniedReason;
  private Integer deniedCode;
  private Long idPolicy;
  private BigDecimal insuranceAmount;
  private BigDecimal insurancePremium;
  private String policyNumber;
  private LocalDate insuranceFromDate;
  private LocalDate insuranceToDate;
  private LocalDate applicationCreateDate;
  private LocalDate changeDate;
  private String email;
  private String customerLastname;
  private String customerFirstname;
  private String customerMiddlename;
  private LocalDate dateOfSurvival;
  private String changeBaseAsset;
  private BigDecimal changeSum;
  private BigDecimal unitChangeValue;
  private String changeCurrency;
  private String bik;
  private String accountCorresp;
  private String accountNumber;
  private String document;
  private BigDecimal redemptionAmount;
  private BigDecimal insuranceAmountNew;
  private BigDecimal insurancePremiumNew;
  private BigDecimal preliminaryInsuranceAmount;
  private String storedApplicationList;
  private LocalDateTime vDat;
}
