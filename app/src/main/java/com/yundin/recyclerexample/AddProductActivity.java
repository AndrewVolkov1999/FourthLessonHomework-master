package com.yundin.recyclerexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yundin.recyclerexample.api.AddProductRequest;
import com.yundin.recyclerexample.api.AddProductResponse;
import com.yundin.recyclerexample.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Yundin Vladislav
 */
public class AddProductActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String ID = "id";
    public static final String IMAGE = "image";

    private EditText titleTV;
    private EditText priceTV;
    private EditText imageTV;
    private Button acceptBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pruduct);

        init();
    }

    private void init() {
        titleTV = findViewById(R.id.title);
        priceTV = findViewById(R.id.price);
        imageTV = findViewById(R.id.image);
        acceptBtn = findViewById(R.id.accept_btn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAcceptClicked();
            }
        });
    }

    private void onAcceptClicked() {
        if (!titleTV.getText().toString().equals("") && !priceTV.getText().toString().equals("")) {
            String title = titleTV.getText().toString();
            String priceStr = priceTV.getText().toString();
            String image = imageTV.getText().toString();
            int price;
            try {
                price = Integer.valueOf(priceStr);

                if (image.equals("")) {
                    makeRequest(title, price, null);
                } else {
                    makeRequest(title, price, image);
                }
            } catch (NumberFormatException e) {

                Toast.makeText(getApplicationContext(), "Цену числом плиз", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(getApplicationContext(), "Введи хотя бы название и цену", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeRequest(String title, int price, String image) {
        ApiService.getApiInterface()
                .addProduct(new AddProductRequest(title, price, image))
                .enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if (response.body() != null) {
                            onResponseReceived(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddProductResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Нет интернет соединения", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void onResponseReceived(AddProductResponse response) {
        Intent intent = new Intent();
        intent.putExtra(TITLE, response.getData().getTitle());
        intent.putExtra(PRICE, response.getData().getPrice());
        intent.putExtra(ID, response.getData().getId());
        intent.putExtra(IMAGE, response.getData().getImageUrl());
        setResult(0, intent);
        finish();
    }
}
