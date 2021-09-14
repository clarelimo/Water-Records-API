import com.google.gson.Gson;
import dao.DB;
import dao.Sql2oExpensesDao;
import dao.Sql2oSalesDao;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.Expenses;
import models.Sales;
import org.sql2o.Connection;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oSalesDao salesDao;
        Sql2oExpensesDao expensesDao;
        Sql2oUserDao userDao;
        Gson gson = new Gson();
        Connection conn;

        salesDao = new Sql2oSalesDao(DB.sql2o);
        expensesDao = new Sql2oExpensesDao(DB.sql2o);
        userDao = new Sql2oUserDao(DB.sql2o);
        conn = DB.sql2o.open();

        get("/", "application/json", (req, res) -> {
            System.out.println(salesDao.getAll());

            if(salesDao.getAll().size() > 0){
                return gson.toJson(salesDao.getAll());
            }

            else {
                return "{\"message\":\"I'm sorry, but no sales are currently listed in the database.\"}";
            }
        });

        //CREATE
        post("/sales/:salesId/expenses/:expensesId", "application/json", (req, res) -> {

            int salesId = Integer.parseInt(req.params("salesId"));
            int expensesId = Integer.parseInt(req.params("expensesId"));
            Sales sales = salesDao.findById(salesId);
            Expenses expenses = expensesDao.findById(expensesId);

            if (expenses != null && sales != null){
                expensesDao.addExpenseToSales(expenses, sales);
                res.status(201);
                return gson.toJson(String.format("Sales '%s' and Expenses '%s' have been associated",sales.getDate(), expenses.getDescription()));
            }
            else {
                throw new ApiException(404, String.format("Restaurant or Foodtype does not exist"));
            }
        });

        //get all expenses for a sale
        get("/sales/:id/expenses", "application/json", (req, res) -> {
            int salesId = Integer.parseInt(req.params("id"));
            Sales salesToFind = salesDao.findById(salesId);
            if (salesToFind == null){
                throw new ApiException(404, String.format("No sales with the id: \"%s\" exists", req.params("id")));
            }
            else if (expensesDao.getAllExpenseForASale(salesId).size()==0){
                return "{\"message\":\"I'm sorry, but no expenses are listed for this sale.\"}";
            }
            else {
                return gson.toJson(expensesDao.getAllExpenseForASale(salesId ));
            }
        });

        //CREATE sales
        post("/sales/new", "application/json", (req, res) -> {
            Sales sales = gson.fromJson(req.body(), Sales.class);
            salesDao.add(sales);
            res.status(201);
            return gson.toJson(sales);
        });

        post("/expenses/new", "application/json", (req, res) -> {
            Expenses expenses = gson.fromJson(req.body(), Expenses.class);
            expensesDao.add(expenses);
            res.status(201);
            return gson.toJson(expenses);
        });

        //get all sales
        get("/sales", "application/json", (req, res) -> {
            System.out.println(salesDao.getAll());

            if(salesDao.getAll().size() > 0){
                return gson.toJson(salesDao.getAll());
            }

            else {
                return "{\"message\":\"I'm sorry, but no sales are currently listed in the database.\"}";
            }
        });

        // get all expenses
        get("/expenses", "application/json", (req, res) -> {
            System.out.println(expensesDao.getAll());

            if(expensesDao.getAll().size() > 0){
                return gson.toJson(expensesDao.getAll());
            }

            else {
                return "{\"message\":\"I'm sorry, but no expenses are currently listed in the database.\"}";
            }
        });

        //get sales per id
        get("/sales/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int salesId = Integer.parseInt(req.params("id"));
            Sales salesToFind = salesDao.findById(salesId);
            if (salesToFind == null){
                throw new ApiException(404, String.format("No sale with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(salesId);
        });


        after((req, res) ->{
            res.type("application/json");
        });

    }
}
