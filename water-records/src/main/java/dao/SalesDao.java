package dao;

import models.Expenses;
import models.Sales;

import java.util.List;

public interface SalesDao {
    //create
    void add(Sales sales);

    //read
    List<Sales> getAll();

    Sales findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
