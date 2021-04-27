package com.test.farm6.Buyer.ViewFarmer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.R;
import com.test.farm6.model.Stock;

import java.util.List;

public class BuyerViewFarmerStockAdapter extends RecyclerView.Adapter<BuyerViewFarmerStockAdapter.MyViewHolder> {
    private List<Stock> stockList;
    private ListItemClickListener listener;

    public BuyerViewFarmerStockAdapter(List<Stock> stockList, ListItemClickListener listener){
        this.stockList = stockList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_stock_row, parent, false);
        return new BuyerViewFarmerStockAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(stockList.get(position).getProduct().getName());
        holder.productPrice.setText("$ "+ String.format("%.2f", stockList.get(position).getProduct().getPrice()));
        holder.productQuantity.setText(String.valueOf(stockList.get(position).getWeight()) + " lb");
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ListItemClickListener listener;
        TextView productName;
        TextView productPrice;
        TextView productQuantity;

        public MyViewHolder(@NonNull View itemView, ListItemClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.stock_row_name);
            productPrice = itemView.findViewById(R.id.stock_row_price);
            productQuantity = itemView.findViewById(R.id.stock_row_quantity);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onItemClick(stockList.get(getAdapterPosition()));
            }
        }
    }

    public interface ListItemClickListener{
        void onItemClick(Stock stock);
    }
}