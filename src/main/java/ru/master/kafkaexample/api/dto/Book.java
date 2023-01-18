package ru.master.kafkaexample.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Book {
  private String category;
  private String author;
  private String title;
  private String isbn;
  private Double price;
}