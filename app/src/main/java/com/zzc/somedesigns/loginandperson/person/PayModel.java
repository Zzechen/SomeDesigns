package com.zzc.somedesigns.loginandperson.person;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class PayModel {
    private String seller;
    private String time;
    private int status;
    private float price;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
