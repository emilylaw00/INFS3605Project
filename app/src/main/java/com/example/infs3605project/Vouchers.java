package com.example.infs3605project;

public class Vouchers {

    private int id;
    private String title;
    private int cost;
    private boolean active;

    public Vouchers(String title, int cost, boolean active) {
        this.title = title;
        this.cost = cost;
        this.active = active;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoucherCost() {
        return cost;
    }

    public void setVoucherCost(int cost) {
        this.cost = cost;
    }
}
