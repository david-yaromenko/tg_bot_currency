package com.example.tg_example.model;

public class CurrencyModel {

    String name;
    String price;


    public CurrencyModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Курс гривні до "+ name + " = " + price;
    }

}
