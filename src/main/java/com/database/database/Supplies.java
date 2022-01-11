package com.database.database;

public class Supplies {

    private int TypeId;
    private String TypeName;
    private int Quantity;
    private String ExpireDate;


    public Supplies (int TypeId, String TypeName, int quantity, String ExpireDate) {
        this.setTypeId(TypeId);
        this.setTypeName(TypeName);
        this.setQuantity(quantity);
        this.setExpireDate(ExpireDate);

    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Type_ID="+ getTypeId()+" Type_Name : "+ getTypeName()+" Quantity : "+ getQuantity()+" ExpiredDate : "+ getExpireDate();
    }



    public int getTypeId() {
        return TypeId;
    }



    public void setTypeId(int typeId) {
        TypeId = typeId;
    }



    public String getTypeName() {
        return TypeName;
    }



    public void setTypeName(String typeName) {
        TypeName = typeName;
    }



    public int getQuantity() {
        return Quantity;
    }



    public void setQuantity(int quantity) {
        Quantity = quantity;
    }



    public String getExpireDate() {
        return ExpireDate;
    }



    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

}



