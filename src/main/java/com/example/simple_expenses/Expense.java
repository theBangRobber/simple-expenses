package com.example.simple_expenses;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
  private String id = UUID.randomUUID().toString();
  private String description;
  private Double amount;
  private String category;
}
