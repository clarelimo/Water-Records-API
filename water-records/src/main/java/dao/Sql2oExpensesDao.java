package dao;

import models.Expenses;
import models.Sales;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oExpensesDao implements ExpensesDao {

    private final Sql2o sql2o;

    public Sql2oExpensesDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Expenses expenses) {
        String sql = "INSERT INTO expenses (description,amount,salesid) VALUES(:description,:amount,:salesId)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(expenses)
                    .executeUpdate()
                    .getKey();
            expenses.setId(id);

        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addExpenseToSales(Expenses expenses, Sales sales) {
        String sql = "INSERT INTO sales_expenses (salesid, expensesid) VALUES (:salesId, :expensesId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("salesId", sales.getId())
                    .addParameter("expensesId", expenses.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Expenses> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM expenses")
                    .executeAndFetch(Expenses.class);
        }
    }

    @Override
    public List<Expenses> getAllExpenseForASale(int salesId) {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM expenses WHERE salesId = :salesId")
                    .addParameter("salesId",salesId)
                    .executeAndFetch(Expenses.class);
        }
    }

    @Override
    public Expenses findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM expenses WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Expenses.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from expenses WHERE id = :id";
        try  (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from expenses";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
