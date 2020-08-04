package com.example.infs3605project;

public class Vouchers {

    private int id;
    private int image;
    private int cost;
    private int active;

    public Vouchers(int id, int image, int cost, int active) {
        this.id= id;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getVoucherCost() {
        return cost;
    }

    public void setVoucherCost(int cost) {
        this.cost = cost;
    }
}
