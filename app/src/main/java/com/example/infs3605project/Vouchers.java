package com.example.infs3605project;

public class Vouchers {

    private int id;
    private String title;
    private int cost;
    private int active;

    public Vouchers(int id, String title, int cost, int active) {
        this.id= id;
        this.title = title;
        this.cost = cost;
        this.active=active;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
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
