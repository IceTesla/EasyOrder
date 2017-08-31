package cn.IceTesla.easyorder.Data.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IceTesla on 2017/8/30.
 */

public class ShopcartModel implements Serializable {
    private String name;
    private ArrayList image;
    private int sale;
    private int good;
    private int price;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getImage() {
        return image;
    }

    public void setImage(ArrayList image) {
        this.image = image;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
