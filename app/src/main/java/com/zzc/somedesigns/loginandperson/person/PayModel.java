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

    String getSeller() {
        return seller;
    }

    void setSeller(String seller) {
        this.seller = seller;
    }

    String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    int getStatus() {
        return status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    float getPrice() {
        return price;
    }

    void setPrice(float price) {
        this.price = price;
    }
}
