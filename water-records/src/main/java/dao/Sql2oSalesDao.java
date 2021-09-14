package dao;

import models.Expenses;
import models.Sales;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSalesDao implements SalesDao {
    private final Sql2o sql2o;

    public Sql2oSalesDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Sales sales) {
        String sql = "INSERT INTO sales (date,cashsales,mpesasales,litressold,emptybottlessold, machineimage,iswaterrefilled,balance) VALUES(:date,:cashSales,:mpesaSales,:litresSold,:emptyBottlesSold, :machineImage,:isWaterRefilled,:balance)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(sales)
                    .executeUpdate()
                    .getKey();
            sales.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Sales> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM sales")
                    .executeAndFetch(Sales.class);
        }
    }

    @Override
    public Sales findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM sales WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sales.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from sales WHERE id = :id";
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
        String sql = "DELETE from sales";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
