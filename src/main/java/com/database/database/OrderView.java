package com.database.database;

public class OrderView {

    private int O_id;
    private String C_name;
    private int total_price;
    private int discount;
    private String Notes;
    private double Elapsed_time;
    private int Table_No;

    public OrderView(int o_id, String c_id, int totalPrice, int discount, String notes, double elapsedTime, int tableNo) {
        O_id = o_id;
        C_name = c_id;
        this.total_price = totalPrice;
        this.discount = discount;
        Notes = notes;
        Elapsed_time = elapsedTime;
        this.Table_No = tableNo;
    }

    public int getO_id() {
        return O_id;
    }

    public void setO_id(int o_id) {
        O_id = o_id;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public double getElapsed_time() {
        return Elapsed_time;
    }

    public void setElapsed_time(double elapsed_time) {
        Elapsed_time = elapsed_time;
    }

    public int getTable_No() {
        return Table_No;
    }

    public void setTable_No(int table_No) {
        this.Table_No = table_No;
    }
}
