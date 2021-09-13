package models;

import java.util.Objects;

public class Reports {
    private Sales sales;
    private Expenses expenses;
    private int totalDailyIncome;
    private int id;

    public Reports(Sales sales, Expenses expenses) {
        this.sales = sales;
        this.expenses = expenses;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    public int getTotalDailyIncome() {
        int income =this.sales.getTotalSales() - this.expenses.getAmount();
        return totalDailyIncome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reports reports = (Reports) o;
        return totalDailyIncome == reports.totalDailyIncome &&
                id == reports.id &&
                sales.equals(reports.sales) &&
                expenses.equals(reports.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sales, expenses, totalDailyIncome, id);
    }
}
