package dao;

import models.Expenses;

import java.util.List;

public interface ExpensesDao {

    //create
    void add(ExpensesDao bedRoom);

    //read
    List<Expenses> getAll();
    List<Expenses> getAllExpenseForASale(int salesId);
    Expenses findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
