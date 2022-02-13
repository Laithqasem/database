package com.database.database;

public class linesTable {
    private int line_id;
    private int O_id;
    private String m_name;
    private int quantity;

    public linesTable(int line_id, int o_id, String m_name, int quantity){
        this.line_id = line_id;
        O_id = o_id;
        this.m_name = m_name;
        this.quantity = quantity;
    }

    public int getLine_id(){
        return line_id;
    }

    public void setLine_id(int line_id){
        this.line_id = line_id;
    }

    public int getO_id(){
        return O_id;
    }

    public void setO_id(int o_id){
        O_id = o_id;
    }

    public String getM_name(){
        return m_name;
    }

    public void setM_name(String m_name){
        this.m_name = m_name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}