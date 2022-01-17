package com.database.database;

public class OrderLine {
    private int line_id;
    private int O_id;
    private String m_id;
    private int quantity;

    public OrderLine(int line_id, int o_id, String m_id, int quantity){
        this.line_id = line_id;
        O_id = o_id;
        this.m_id = m_id;
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

    public String getM_id(){
        return m_id;
    }

    public void setM_id(String m_id){
        this.m_id = m_id;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
