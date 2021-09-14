package dao;

import models.Sales;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class Sql2oSalesDaoTest {
    private static Sql2oSalesDao salesDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        salesDao = new Sql2oSalesDao(DatabaseRule.sql2o);
        conn = DatabaseRule.sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        salesDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() {
        Sales sales = setupSales();
        assertEquals(1,sales.getId());
    }

    @Test
    public void getAll() {
        Sales sales = setupSales();
        Sales sales2 = setupSales();
        assertEquals(2,salesDao.getAll().size());
    }

    @Test
    public void deleteById() {
        Sales sales = setupSales();
        Sales sales2 = setupSales();
        salesDao.clearAll();
        assertEquals(0,salesDao.getAll().size());
    }

    @Test
    public void clearAll() {

        Sales sales = setupSales();
        Sales sales2 = setupSales();
        assertEquals(2,salesDao.getAll().size());
        salesDao.deleteById(sales.getId());
        assertEquals(1,salesDao.getAll().size());
    }

    public Sales setupSales(){
        Sales sales = new Sales(new Timestamp(new Date().getDay()), 700,300, 200,0,"my image",false,50,1);
        salesDao.add(sales);

        return  sales;
    }
}