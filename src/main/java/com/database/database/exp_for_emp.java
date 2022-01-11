package com.database.database;

public class exp_for_emp {

    private int BillId;
    private int EId;
    private int BaseSalary;
    private int  OvertimePrice;



    public exp_for_emp(int BillId, int EId, int BaseSalary, int OvertimePrice) {
        this.setBillId(BillId);
        this.setEId(EId);
        this.setBaseSalary(BaseSalary);
        this.setOvertimePrice(OvertimePrice);

    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "BillId="+ getBillId()+" EId : "+ getEId()+" BaseSalary : "+ getBaseSalary()+" OvertimePrice : "+ getOvertimePrice();
    }


    public int getEId() {
        return EId;
    }



    public void setEId(int EId) {
        this.EId = EId;
    }



    public int getBillId() {
        return BillId;
    }



    public void setBillId(int BillId) {
        this.BillId = BillId;
    }



    public int getBaseSalary() {
        return BaseSalary;
    }



    public void setBaseSalary(int BaseSalary) {
        this.BaseSalary = BaseSalary;
    }



    public int getOvertimePrice() {
        return OvertimePrice;
    }



    public void setOvertimePrice(int OvertimePrice) {
        this.OvertimePrice = OvertimePrice;
    }

}



