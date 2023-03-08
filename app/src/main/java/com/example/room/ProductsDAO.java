package com.example.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
interface ProductsDAO {

    @Insert
    Completable insertProduct(Products products);

    @Query("SELECT * FROM products_table")
    Single<List<Products>> getProducts();

}
