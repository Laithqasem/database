package com.database.database;

public class Role {

    private String role_id;
    private String role_name;
    private int base_salary;
    private int overtime_hours_price;


    public Role(String role_id, String role_name, int base_salary, int overtime_hours_price) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.base_salary = base_salary;
        this.overtime_hours_price =  overtime_hours_price;

    }

    public String getRole_name() {
        return role_name;
    }

    public int getBase_salary() {
        return base_salary;
    }

    public String getRole_id() {
        return role_id;
    }

    public int getOvertime_hours_price() {
        return overtime_hours_price;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public void setBase_salary(int base_salary) {
        this.base_salary = base_salary;
    }

    public void setOvertime_hours_price(int overtime_hours_price) {
        this.overtime_hours_price = overtime_hours_price;
    }
}