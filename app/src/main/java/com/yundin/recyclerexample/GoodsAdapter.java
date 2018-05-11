package com.yundin.recyclerexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yundin Vladislav
 */
public class GoodsAdapter extends RecyclerView.Adapter {

    private List<Product> data = new ArrayList<>();
    private Context context;
    private View.OnClickListener listener;

    public GoodsAdapter(List<Product> data, Context context, View.OnClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    public GoodsAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder productHolder = (ProductViewHolder) holder;
        Product item = data.get(position);

        productHolder.textView1.setText(item.getTitle());
        productHolder.textView2.setText(String.valueOf(item.getPrice()));
        productHolder.itemView.setTag(item.getPrice());
        productHolder.itemView.setOnClickListener(listener);

        Glide
                .with(context)
                .load(item.getImageUrl())
                .into(productHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void appendItemWithNotify(Product item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void updateDataWithNotify(List<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
        }
    }
}
