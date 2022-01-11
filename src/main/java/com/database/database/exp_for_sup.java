package com.database.database;

public class exp_for_sup {

    private int TypeId;
    private int BillId;
    private int PricePerUnit;
    private int  TypeQuant;


    public exp_for_sup( int BillId,int TypeId, int PricePerUnit, int TypeQuant) {
        this.setTypeId(TypeId);
        this.setBillId(BillId);
        this.setPricePerUnit(PricePerUnit);
        this.setTypeQuant(TypeQuant);

    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "TypeId="+ getTypeId()+" BillId : "+ getBillId()+" PricePerUnit : "+ getPricePerUnit()+" TypeQuant : "+ getTypeQuant();
    }



    public int getTypeId() {
        return TypeId;
    }



    public void setTypeId(int typeId) {
        this.TypeId = typeId;
    }



    public int getBillId() {
        return BillId;
    }



    public void setBillId(int BillId) {
        this.BillId = BillId;
    }



    public int getPricePerUnit() {
        return PricePerUnit;
    }



    public void setPricePerUnit(int PricePerUnit) {
        this.PricePerUnit = PricePerUnit;
    }



    public int getTypeQuant() {
        return TypeQuant;
    }



    public void setTypeQuant(int TypeQuant) {
        this.TypeQuant = TypeQuant;
    }

}



