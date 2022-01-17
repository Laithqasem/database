package com.database.database;

public class mealQuantity {
    private String m_id;
    private int quantity;

    public mealQuantity(String m_id, int quantity) {
        this.m_id = m_id;
        this.quantity = quantity;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
