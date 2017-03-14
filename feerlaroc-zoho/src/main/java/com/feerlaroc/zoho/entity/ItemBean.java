package com.feerlaroc.zoho.entity;

/**
 * Created by root on 2016/12/07.
 */

public class ItemBean {

    private String item_id;
    private String name;
    private String status;
    private String description;
    private float rate;
    private String tax_id;
    private String tax_name;
    private String tax_percentage;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "item_id='" + item_id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", tax_id='" + tax_id + '\'' +
                ", tax_name='" + tax_name + '\'' +
                ", tax_percentage='" + tax_percentage + '\'' +
                '}';
    }
}
