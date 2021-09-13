package dao;

import models.Reports;

import java.util.List;

public interface ReportsDao {
    //create
    void add(Reports reports);

    //read
    List<Reports> getAll();
    Reports findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
