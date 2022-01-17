package com.database.database;

public class customer {
    private int C_id;
    private String C_name;
    private int Phone;
    private String Address;

    public customer(int c_id, String c_name, int phone, String address) {
        C_id = c_id;
        C_name = c_name;
        Phone = phone;
        Address = address;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
