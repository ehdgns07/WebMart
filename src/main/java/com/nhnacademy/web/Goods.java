package com.nhnacademy.web;

public class Goods {

    private String name;
    private int price;
    private int number;

        public Goods(String name, int number , int price){
            this.name = name;
            this.price = price;
            this.number = number;
        }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
