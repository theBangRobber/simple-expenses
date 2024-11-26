package com.example.simple_expenses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

  private ArrayList<Expense> expenses = new ArrayList<>();

  // Create
  @PostMapping("")
  public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
    expenses.add(expense);
    return new ResponseEntity<>(expense, HttpStatus.CREATED);
  }

  // Get All Expenses
  @GetMapping("")
  public ResponseEntity<ArrayList<Expense>> getAllExpense() {
    return new ResponseEntity<>(expenses, HttpStatus.OK);
  }

  // Get One Expense
  @GetMapping("/{id}")
  public ResponseEntity<Expense> getExpense(@PathVariable String id) {
    try {
      return new ResponseEntity<>(expenses.get(getExpenseIndex(id)), HttpStatus.OK);
    } catch (ExpenseNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Optional Challenge 1
  // Get Expense with Specified Category

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<Expense> updateExpense(@PathVariable String id, @RequestBody Expense expense) {
    try {
      int index = getExpenseIndex(id);
      Expense updateExpense = expenses.get(index);
      updateExpense.setAmount(expense.getAmount());
      updateExpense.setDescription(expense.getDescription());
      updateExpense.setCategory(expense.getCategory());
      expenses.set(index, expense);
      return new ResponseEntity<>(updateExpense, HttpStatus.OK);
    } catch (ExpenseNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteExpense(@PathVariable String id) {
    try {
      int index = getExpenseIndex(id);
      expenses.remove(index);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (ExpenseNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Helper Method
  private int getExpenseIndex(String id) {
    for (Expense expense : expenses) {
      if (expense.getId().equals(id)) {
        return expenses.indexOf(expense);
      }
    }
    throw new ExpenseNotFoundException(id);
  }
}
