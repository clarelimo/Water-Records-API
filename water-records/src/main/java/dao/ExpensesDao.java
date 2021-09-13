package dao;

import models.Expenses;

import java.util.List;

public interface ExpensesDao {

    //create
    void add(ExpensesDao bedRoom);

    //read
    List<Expenses> getAll();
    Expenses findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
