package com.database.database;

public class Employee {
    private int e_id;
    private String e_name;
    private String birthdate;
    private int phone;
    private int overtime_hours;
    private String r_id;

    public Employee(int e_id, String e_name, String birthdate, int phone, String r_id, int overtime_hours) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.overtime_hours = overtime_hours;
        this.r_id = r_id;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getOvertime_hours() {
        return overtime_hours;
    }

    public void setOvertime_hours(int overtime_hours) {
        this.overtime_hours = overtime_hours;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

}
