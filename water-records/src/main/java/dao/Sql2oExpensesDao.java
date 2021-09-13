package dao;

import models.Expenses;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oExpensesDao implements ExpensesDao {

    private final Sql2o sql2o;

    public Sql2oExpensesDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(ExpensesDao bedRoom) {

    }

    @Override
    public List<Expenses> getAll() {
        return null;
    }

    @Override
    public List<Expenses> getAllExpenseForASale(int salesId) {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM sales WHERE salesId = :salesId")
                    .addParameter("salesId",salesId)
                    .executeAndFetch(Expenses.class);
        }
    }

    @Override
    public Expenses findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
