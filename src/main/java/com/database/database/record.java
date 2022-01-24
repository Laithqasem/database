package com.database.database;

public class record {
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public record(int cid, int count) {
        this.cid = cid;
        this.count = count;
    }

    private int cid;
    private int count;
}
