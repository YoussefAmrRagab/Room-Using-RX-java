package com.example.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products_table")
public class Products {
    private String image;
    private double price;
    private String description;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String category;

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }


    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Products(String image, double price, String description, String title, String category) {
        this.image = image;
        this.price = price;
        this.description = description;
        this.title = title;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }
}
