package com.example.simple_expenses;

public class ExpenseNotFoundException extends RuntimeException {
  ExpenseNotFoundException(String id) {
    super("Could not find expense with id: " + id + ".");
  }
}
