package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);

        ProductsDatabase database = ProductsDatabase.getInstance(this);

        button.setOnClickListener(v -> {
            database.productsDAO().
                    insertProduct(new Products("https://i.dummyjson.com/data/products/1/1.jpg", 549, "An apple mobile which is nothing like apple", "iPhone 9", "smartphones"))
                    .subscribeOn(Schedulers.computation())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

            // ...

            database.productsDAO().getProducts().subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<List<Products>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(List<Products> products) {

                            System.out.println("Size:" + products.size());
                            for (int i = 0; i < products.size(); i++) {
                                System.out.println("Id:" + products.get(i).getId());
                                System.out.println("Image:" + products.get(i).getImage());
                                System.out.println("Price:" + products.get(i).getPrice());
                                System.out.println("Title:" + products.get(i).getTitle());
                                System.out.println("Category:" + products.get(i).getCategory());
                                System.out.println("Description:" + products.get(i).getDescription());
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

        });


    }
}