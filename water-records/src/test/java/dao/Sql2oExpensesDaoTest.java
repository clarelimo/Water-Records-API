package dao;

import models.Expenses;
import models.Sales;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;

import java.awt.*;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oExpensesDaoTest {
    private static Sql2oExpensesDao expensesDao;
    private static Sql2oSalesDao salesDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        expensesDao = new Sql2oExpensesDao(DatabaseRule.sql2o);
        salesDao = new Sql2oSalesDao(DatabaseRule.sql2o);
        conn = DatabaseRule.sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        expensesDao.clearAll();
        salesDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        Expenses expenses = setupExpenses();
        assertEquals(1,expenses.getId());
    }

    @Test
    public void getAll() {
        Expenses expenses = setupExpenses();
        Expenses expense2 = setupExpenses();
        assertEquals(2,expensesDao.getAll().size());
    }

    @Test
    public void getAllExpenseForASale() {
        Sales sales = new Sales(new Timestamp(new Date().getDay()), 700,300, 200,0,"my image",false,50);
        salesDao.add(sales);

        Expenses expenses = new Expenses("tokens",200, sales.getId());
        Expenses expenses2 = new Expenses("salary",5000, sales.getId());

        expensesDao.add(expenses);
        expensesDao.add(expenses2);

        assertEquals(2, expensesDao.getAllExpenseForASale(sales.getId()));
    }

    @Test
    public void clearAll() {
        Expenses expenses = setupExpenses();
        Expenses expenses2 = setupExpenses();
        expensesDao.clearAll();
        assertEquals(0,expensesDao.getAll().size());
    }

    @Test
    public void deleteById() {
        Expenses expenses = setupExpenses();
        Expenses expenses2 = setupExpenses();
        assertEquals(2,expensesDao.getAll().size());
        expensesDao.deleteById(expenses.getId());
        assertEquals(1,expensesDao.getAll().size());
    }

    public Expenses setupExpenses(){
        Expenses expenses = new Expenses("Water refilled",4000,1);
        expensesDao.add(expenses);
        return expenses;
    }
}