package ru.master.kafkaexample.api.service.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.master.kafkaexample.api.dto.Bicycle;
import ru.master.kafkaexample.api.dto.Book;
import ru.master.kafkaexample.api.service.JsonParserSubService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JsonParserSubServiceImpl implements JsonParserSubService {
  @Value("classpath:primer.json")
  Resource jsonDataResource;

  @Autowired
  public JsonParserSubServiceImpl() {

  }

  public void runOneTask(@NonNull String jsonData) {


    //Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
    Configuration conf = Configuration.defaultConfiguration();



    //List<String> authors = JsonPath.read(jsonData, "$.store.book[*].author");
    //String author = JsonPath.read(jsonData, "$.store.book[0].author");
    /*log.info("Данные выборки: {}", authors);*/




    ReadContext ctx = JsonPath.parse(jsonData);

    List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
    log.info("Данные выборки 1: {}", authorsOfBooksWithISBN);


    List<Map<String, Object>> expensiveBooks = JsonPath
        .using(conf)
        .parse(jsonData)
        .read("$.store.book[?(@.price > 10)]", List.class);

    log.info("Данные выборки 2: {}", expensiveBooks);

    Book book = JsonPath.parse(jsonData).read("$.store.book[0]", Book.class);

    log.info("Данные выборки 3: {}", book);

    Bicycle bicycle = JsonPath.parse(jsonData).read("$.store.bicycle", Bicycle.class);

    log.info("Данные выборки 4: {}", bicycle);

  }


  public void runParse(@NonNull String jsonData) {
    String base = "CreateSOTIRqList.CreateSOTIRq.";

    ReadContext ctx = JsonPath.parse(jsonData);
    String RqUID      = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.RqUID", String.class);

    String lastName   = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.lastName", String.class);
    String firstName  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.firstName", String.class);
    String middleName = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.middleName", String.class);
    String email      = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.email", String.class);
    String docSeries  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.applicantDocumentsList.applicantDocument.documentSeries", String.class);
    String docNumber  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.CustomerInfo.applicantDocumentsList.applicantDocument.documentNumber", String.class);

    String AgrPolSer  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.AgreementInfo.AgreementPolicySeries", String.class);
    String AgrNumber  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.AgreementInfo.AgreementNumber", String.class);
    String InsFromDate= ctx.read("$.CreateSOTIRqList.CreateSOTIRq.AgreementInfo.InsuranceFromDate", String.class);
    String InsToDate  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.AgreementInfo.InsuranceToDate", String.class);

    String AppCrDate  = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationCreateDate", String.class);

    String ChangeBA   = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeBaseAsset.ChangeBA", String.class);
    String ChangeDate = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeBaseAsset.ChangeDate", String.class);

    //String ChangeSum = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeAdditionalFee.ChangeSum", String.class);
    //String Currency = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeAdditionalFee.Currency", String.class);
    //String ChangeDate = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeAdditionalFee.ChangeDate", String.class);

    //String ChangeValue = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeUnitsValue.ChangeValue", String.class);
    //String DateOfSurvival = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeUnitsValue.DateOfSurvival", String.class);
    //String Currency = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeUnitsValue.Currency", String.class);
    //String ChangeDate = ctx.read("$.CreateSOTIRqList.CreateSOTIRq.Application.ApplicationChangesType.ChangeUnitsValue.ChangeDate", String.class);








    log.info("lastName: {}",    lastName);
    log.info("firstName: {}",   firstName);
    log.info("middleName: {}",  middleName);
    log.info("email: {}",       email);
    log.info("RqUID: {}",       RqUID);
    log.info("AgrPolSer: {}",   AgrPolSer);
    log.info("AgrNumber: {}",   AgrNumber);
    log.info("ChangeBA: {}",    ChangeBA);
    log.info("docSeries: {}",   docSeries);
    log.info("docNumber: {}",   docNumber);
    log.info("InsFromDate: {}", InsFromDate);
    log.info("InsToDate: {}",   InsToDate);
    log.info("ApplicationCreateDate: {}",   AppCrDate);
    log.info("ChangeDate: {}",  ChangeDate);




    /*log.info("ChangeSum: {}",              ChangeSum);*/
    /*log.info("DateOfSurvival: {}",              DateOfSurvival);*/


  }
}