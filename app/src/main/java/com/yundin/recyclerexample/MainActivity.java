package com.yundin.recyclerexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.yundin.recyclerexample.api.ApiService;
import com.yundin.recyclerexample.api.GetAllGoodsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GoodsAdapter adapter;
    private Button addButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        init();
        updateData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == resultCode) {
            int id = data.getIntExtra(AddProductActivity.ID, -1);
            String title = data.getStringExtra(AddProductActivity.TITLE);
            int price = data.getIntExtra(AddProductActivity.PRICE, 0);
            String image = data.getStringExtra(AddProductActivity.IMAGE);
            adapter.appendItemWithNotify(new Product(id, image, price, title));
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (myToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void init() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        addButton = findViewById(R.id.add_button);
        swipeRefreshLayout = findViewById(R.id.Swipe);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        View.OnClickListener sumListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        adapter = new GoodsAdapter(getApplicationContext(), sumListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void updateData() {
        ApiService.getApiInterface()
                .getAllGoods()
                .enqueue(new Callback<GetAllGoodsResponse>() {
                    @Override
                    public void onResponse(Call<GetAllGoodsResponse> call, Response<GetAllGoodsResponse> response) {
                        if (response.body() != null) {
                            adapter.updateDataWithNotify(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllGoodsResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Нет интернет соединения", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
