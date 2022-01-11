package com.database.database;

public class Expenses {

    private int BillId;
    private String BillDate;
    private int TotalPay;


    public Expenses( int billId, String billDate, int totalPay) {
        this.BillId = billId;
        this.BillDate = billDate;
        this.TotalPay = totalPay;


    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "BillId="+ getBillId()+" BillDate : "+ getBillDate()+" TotalPay : "+ getTotalPay();
    }



    public int getBillId() {
        return BillId;
    }



    public void setBillId(int Billid) {
        BillId = Billid;
    }



    public String getBillDate() {
        return BillDate;
    }



    public void setBillDate(String Billdate) {
        BillDate = Billdate;
    }



    public int getTotalPay() {
        return TotalPay;
    }



    public void setTotalPay(int Totalpay) {
        TotalPay = Totalpay;
    }



}



